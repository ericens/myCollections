package org.zlx.prometheus;

import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import io.prometheus.client.exporter.HTTPServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



/**
 * Created by @author linxin on 13/09/2017.  <br>
 *
 *     //开始消费, 开启3 个partition消费线程
 *     //每个partitin消费线程里，在放到20个  record记录处理线程处理每个消息，先提交该消费线程的所有任务处理后，在进行拉取。
 *
 *

 https://github.com/prometheus/client_java

 *
 *
 */
@Slf4j
public class RawConsumer {

    static final Counter requests_counter = Counter.build()
            .name("kafka_request_counter").help("Total requests_counter.").register();

    static final Summary requestLatency = Summary.build()
            .name("kafka_requests_latency_seconds").help("Request latency in seconds.").register();

    static final Histogram requestLatencyHIs = Histogram.build()
            .name("requests_latency_seconds").help("Request latency in seconds.").register();




    public static void processMessage(){

        requests_counter.inc();
        Summary.Timer requestTimer = requestLatency.startTimer();
        Histogram.Timer requestTimer2 = requestLatencyHIs.startTimer();
        try {
            log.debug("receive a msg");
            Thread.sleep(RandomUtils.nextInt(10,101));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            requestTimer.observeDuration();
            requestTimer2.observeDuration();
        }
    }



    public static void main(String[] args) throws InterruptedException, IOException {

        HTTPServer server = new HTTPServer(1234);


        ExecutorService executorService= Executors.newFixedThreadPool(8);

        for(int i=0;i<100000;i++){

            executorService.submit(
                    new Runnable() {
                        @Override
                        public void run() {
                            processMessage();
                        }
                    }
            );
            Thread.sleep(RandomUtils.nextInt(10,100));
        }
        log.info("task send over");


    }




}
