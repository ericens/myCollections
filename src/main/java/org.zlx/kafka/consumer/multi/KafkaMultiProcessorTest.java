package org.zlx.kafka.consumer.multi;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.TopicPartition;
import org.zlx.kafka.kafkaCommon;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by @author linxin on 14/09/2017.  <br>
 */
@Slf4j
public class KafkaMultiProcessorTest {

    //kafka消费者配置
    private Map<String, Object> consumerConfig;
    private Thread[] threads;
    //保存处理任务和线程的map
    private ConcurrentHashMap<TopicPartition, RecordProcessor> recordProcessorTasks = new ConcurrentHashMap<>();
    private ConcurrentHashMap<TopicPartition, Thread> recordProcessorThreads = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        KafkaMultiProcessorTest test = new KafkaMultiProcessorTest();
        //....省略设置topic和group的代码
        test.init();
    }

    public void init() {
        consumerConfig = getConsumerConfig();
        log.debug("get kafka consumerConfig: " + consumerConfig.toString());
        //创建threadsNum个线程用于读取kafka消息, 且位于同一个group中, 这个topic有12个分区, 最多12个consumer进行读取
        int threadsNum = 3;
        log.debug("create " + threadsNum + " threads to consume kafka warn msg");
        threads = new Thread[threadsNum];
        for (int i = 0; i < threadsNum; i++) {
            MsgReceiver msgReceiver = new MsgReceiver(consumerConfig, kafkaCommon.topic, recordProcessorTasks, recordProcessorThreads);
            Thread thread = new Thread(msgReceiver);
            threads[i] = thread;
            thread.setName("alarm msg consumer " + i);
        }
        //启动这几个线程
        for (int i = 0; i < threadsNum; i++) {
            threads[i].start();
        }
        log.debug("finish creating" + threadsNum + " threads to consume kafka warn msg");
    }

    //销毁启动的线程
    public void destroy() {
        closeRecordProcessThreads();
        closeKafkaConsumer();
    }

    private void closeRecordProcessThreads() {
        log.debug("start to interrupt record process threads");
        for (Map.Entry<TopicPartition, Thread> entry : recordProcessorThreads.entrySet()) {
            Thread thread = entry.getValue();
            thread.interrupt();
        }
        log.debug("finish interrupting record process threads");
    }

    private void closeKafkaConsumer() {
        log.debug("start to interrupt kafka consumer threads");
        //使用interrupt中断线程, 在线程的执行方法中已经设置了响应中断信号
        for (int i = 0; i < threads.length; i++) {
            threads[i].interrupt();
        }
        log.debug("finish interrupting consumer threads");
    }

    //kafka consumer配置
    private Map<String, Object> getConsumerConfig() {
        return ImmutableMap.<String, Object>builder()
                .put("bootstrap.servers", kafkaCommon.servers)
                .put("group.id", kafkaCommon.group)
                .put("enable.auto.commit", "false")
                .put("session.timeout.ms", "30000")
                .put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
                .put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
                .put("max.poll.records", 1000)
                .build();
    }



}
