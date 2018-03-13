package org.zlx.kafka.kafkaSender;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.zlx.http.yyfqwapTest.TemplateMsgTest;
import org.zlx.http.yyfqwapTest.templateMsg.TemplateReceiveMsg;
import org.zlx.kafka.kafkaCommon;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by @author linxin on 07/09/2017.  <br>
 */
@Slf4j
public class KafkaSenderTest  {
    Producer<String, String> producer;

    public void buildKafka(){
        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaCommon.servers);
        props.put("acks", "1");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(props);

    }

    public void  closeKafka() {
        producer.close();
    }

    public void BatchTest()  {
        int poolSize=1;
        ExecutorService service= Executors.newFixedThreadPool(poolSize);

        for(int j=0;j<poolSize;j++){

            service.submit(new Runnable() {
                @Override
                public void run() {
                    long start=System.currentTimeMillis();
                    for(long i=0;i<10000000;i++){
                        try {
                            MsgLimitAdvanceKafkaTest();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(i%10000==0){
                            log.info("thread {} complete send, elpase:{},{}/s", Thread.currentThread().getName(),i*1000/(System.currentTimeMillis()-start));

                        }
                    }
                    log.info("thread {} complete send, elpase:{} ", Thread.currentThread().getName(),(System.currentTimeMillis()-start));
                }



            });
        }




    }

    TemplateReceiveMsg msg= TemplateMsgTest.getMsg();
    String msgStr= JSON.toJSONString(msg);

    public void MsgLimitAdvanceKafkaTest() throws IOException, InterruptedException {

        producer.send(new ProducerRecord<String, String>(kafkaCommon.topic, RandomUtils.nextInt()+"",  msgStr));

    }


    public static void main(String ars[]) throws InterruptedException {
        KafkaSenderTest client=new KafkaSenderTest();
        client.buildKafka();
        client.BatchTest();
        Thread.sleep(1000*60*3);
        client.closeKafka();

    }

}

