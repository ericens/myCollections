package org.zlx.kafka;

/**
 * Created by @author linxin on 10/03/2018.  <br>
 */
public class kafkaCommon {

    public static String topic="topic.yyfq.wap.weixin.templatemsg.devTest";
    public static String servers="127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9094";
    //消费group
    public static String group="dev";

    //大于2000条是，一次性取1865条
    //200条是，一次性200，偶尔65条
    //1000条，一次1000，一次65条,
    // 有时候65，编程  23 等等
    public static int maxPollRecords=400;

}
