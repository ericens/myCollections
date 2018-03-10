package org.zlx.kafka.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.zlx.http.yyfqwapTest.templateMsg.TemplateReceiveMsg;
import org.zlx.kafka.kafkaCommon;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by @author linxin on 31/07/2017.  <br>
 */





@Service
public class SpringConsumer implements AcknowledgingMessageListener<String,String> {


    private static final Logger logger = LoggerFactory.getLogger(SpringConsumer.class);


//    @Value("${topic.yyfq.wap.weixin.templatemsg}")
    private String topic= kafkaCommon.topic;

    ThreadPoolExecutor recordExecutor;


    public SpringConsumer(){
        recordExecutor  = new ThreadPoolExecutor(20,
                80,
                300L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(500),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
    @Override
    public void onMessage(ConsumerRecord<String, String> data, Acknowledgment acknowledgment) {
        processMessage(data,acknowledgment);
    }

    public void processMessage(ConsumerRecord<String, String>  consumerRecord, Acknowledgment acknowledgment){
        if(consumerRecord!=null && consumerRecord.value()!=null && consumerRecord.topic()!=null){
            logger.info("receive.the.topic  topic={},value={}, partition={},offset={}", JSON.toJSONString(consumerRecord.topic()),
                    consumerRecord.value(), consumerRecord.partition(),consumerRecord.offset());

            if(topic.equals(consumerRecord.topic())){
                TemplateReceiveMsg msg = JSONObject.parseObject(consumerRecord.value(),TemplateReceiveMsg.class);
                if(msg != null ){
                    //提交一次
                    recordExecutor.execute(new KafkaConsumerTask(msg,acknowledgment));
                    return;
                }else {
                    logger.warn("the message is null");
                }

            }
        }
        //提交
        acknowledgment.acknowledge();
        return ;

    }

    public void doProcess(TemplateReceiveMsg msg, Acknowledgment acknowledgment){
        long start=System.currentTimeMillis();

        logger.info("process.msg--->{}",JSON.toJSONString(msg));

        long end=System.currentTimeMillis();
        acknowledgment.acknowledge();
        logger.info("kafka.msg.cost.time userid---->{},time--->{}",msg.getUserId(),(end-start));
    }

    class KafkaConsumerTask implements Runnable {

        TemplateReceiveMsg msg;
        Acknowledgment acknowledgment;
        public KafkaConsumerTask(TemplateReceiveMsg receiveMsg, Acknowledgment acknowledgment){
            this.msg=receiveMsg;
            this.acknowledgment=acknowledgment;
        }


        @Override
        public void run() {
            doProcess(msg,acknowledgment);

        }
    }

}
