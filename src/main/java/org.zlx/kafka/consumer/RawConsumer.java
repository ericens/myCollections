package org.zlx.kafka.consumer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.zlx.kafka.kafkaCommon;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by @author linxin on 13/09/2017.  <br>
 *
 *     //开始消费, 开启3 个partition消费线程
 *     //每个partitin消费线程里，在放到20个  record记录处理线程处理每个消息，先提交该消费线程的所有任务处理后，在进行拉取。
 */
@Slf4j
public class RawConsumer {


    private String topic= kafkaCommon.topic;

    ExecutorService recordExecutor;

    Properties props;

    //<partition,Maxoffset> 判单是否重复消费
    Map<Integer,Long> partitionOffsetMap=new ConcurrentHashMap ();

    public  RawConsumer(){

        props = new Properties();
        props.put("bootstrap.servers", kafkaCommon.servers);
        props.put("group.id", kafkaCommon.group);
        props.put("session.timeout.ms", "30000");
        props.put("enable.auto.commit", "false");
        props.put("max.poll.records", kafkaCommon.maxPollRecords);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");


        //开始消费, 开启partition 个消费线程
        for (int i=0;i<8;i++ ) {
            PartitionConsumer task=new PartitionConsumer();
            new Thread(task).start();
        }


        /*
        queue的大小最好不要小于 线程数，免得产生竞争
        1时候，10的时候，20的时候。刚刚好
        40的时候，留有一点余地
        */
        recordExecutor  = new ThreadPoolExecutor(20,
                20,
                300L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(40),
                new ThreadPoolExecutor.CallerRunsPolicy());


    }


    class PartitionConsumer implements Runnable {

        KafkaConsumer<String, String> consumer;
        ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();


        public PartitionConsumer() {

            consumer = new KafkaConsumer(props);
            consumer.subscribe(Arrays.asList(topic));
            log.info("{} :PartitionConsumer: {}", Thread.currentThread().getName(),consumer.toString());
        }

        @Override
        public void run() {

            while (true) {
                ConsumerRecords<String, String> records=null;
                rwl.writeLock().lock();
                try{
                    // 即使在 consumer.poll 时候，也需要进行同步  或者锁定
                    records = consumer.poll(5000);
                }finally {
                    rwl.writeLock().unlock();
                }


                if(records.count()>0){
                    //判断是否重复消费
                    ConsumerRecord consumerRecord=records.iterator().next();
                    if(partitionOffsetMap.get(consumerRecord.partition())!=null&& partitionOffsetMap.get(consumerRecord.partition())>=consumerRecord.offset()){
                        log.debug("the same record found topic:{},partition:{},offset:{}",consumerRecord.topic(),consumerRecord.partition(),consumerRecord.offset());
                        return ;
                    }
                    partitionOffsetMap.put(consumerRecord.partition(),consumerRecord.offset());


                    log.info("poll the record->threadname:{},count:{},partition:{},offset:{}", Thread.currentThread().getName(), records.count(),consumerRecord.partition(),consumerRecord.offset());
                    dealRecords(records);
                }
                else {
                    log.info("poll the record->threadname:{},count:{}", Thread.currentThread().getName(), records.count());
                }

            }

        }




        public void dealRecords(ConsumerRecords<String, String> records){
            //找出需要处理的record
            for (ConsumerRecord record : records) {
                if (record != null || record.value() != null || record.topic() != null) {

                    log.debug("receive.the.topic  topic={},value={}, partition={},offset={}", JSON.toJSONString(record.topic()),
                            record.value(), record.partition(), record.offset());

                    if (topic.equals(record.topic())) {
                        recordExecutor.execute(new RecordProcessTask(consumer, record,rwl ));
                        continue;
                    }
                }


                // 即使在 consumer 时候，也需要进行同步或者锁定
                rwl.writeLock().lock();
                try{
                    consumer.commitAsync();
                }
                finally {
                    rwl.writeLock().unlock();
                }

            }
        }
    }
    class RecordProcessTask implements Runnable {

        ConsumerRecord<String, String>  consumerRecord;
        KafkaConsumer<String, String> consumer;
        ReentrantReadWriteLock rwl;
        public RecordProcessTask(KafkaConsumer<String, String> consumer,ConsumerRecord<String, String>  consumerRecord,ReentrantReadWriteLock rwl ){
            this.consumer =consumer;
            this.consumerRecord=consumerRecord;
            this.rwl=rwl;
        }


        @Override
        public void run() {
            //processMessage处理语句没有必要，放在同步段里面。否者2秒钟编程1分钟
            processMessage(consumerRecord);
            rwl.writeLock().lock();
            try{
                consumer.commitAsync();
            }finally {
                rwl.writeLock().unlock();
            }
        }
        public void processMessage(ConsumerRecord<String, String>  consumerRecord){

            /*
            最大消费数记录的判断不能放在这里。因为一个partition里面是多线程的消费，线程没有先后，不是先消费的offset就一定更小。应该放在pull里面
            已经消费的大于>需要消费的
            if(partitionOffsetMap.get(consumerRecord.partition())!=null&& partitionOffsetMap.get(consumerRecord.partition())>=consumerRecord.offset()){
                log.debug("the same record found topic:{},partition:{},offset:{}",consumerRecord.topic(),consumerRecord.partition(),consumerRecord.offset());
                return ;
            }
            partitionOffsetMap.put(consumerRecord.partition(),consumerRecord.offset());
            */



            log.debug("process.message is -->{}",Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        RawConsumer  con=new RawConsumer();

        Thread.sleep(1000*60*20);

    }


}
