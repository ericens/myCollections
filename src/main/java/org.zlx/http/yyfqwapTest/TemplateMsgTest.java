package org.zlx.http.yyfqwapTest;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zlx.http.HttpClient;
import org.zlx.http.yyfqwapTest.templateMsg.TemplateMsgPart.TemplateValue;
import org.zlx.http.yyfqwapTest.templateMsg.TemplateReceiveMsg;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by @author linxin on 14/09/2017.  <br>
 */
public class TemplateMsgTest {

    static Logger logger= LoggerFactory.getLogger(TemplateMsgTest.class);

    static String url="http://localhost:8080/templateMsg/send";

    public static TemplateReceiveMsg getMsg(){

        TemplateReceiveMsg msg=new TemplateReceiveMsg();
        Map data=new HashMap<String,TemplateValue>();

        data.put("first",new TemplateValue("恭喜您，您的信用额度获得提高"));
        data.put("headinfo",new TemplateValue("5000"));
        data.put("payDate",new TemplateValue("100"));
        data.put("payMoney",new TemplateValue("2015年8月21日"));
        data.put("remark",new TemplateValue("点击下方的“业务办理”-“取现金”，或直接点击详情，即可全额取现！"));

        msg.setData(data);
        msg.setTemplateId("If-1my5GfurIWYZtTJPoStEmT8Efd8pEvxXNTlagymM");
        msg.setUrl("https://www.baidu.com-1234");

        String x= UUID.randomUUID().toString();
        msg.setSerialNo( x.substring( x.length()-20,x.length()-1)) ;

        String userID="1281073018920";
        msg.setUserId(userID);

        return msg;
    }



    public static void main(String []argx){

        logger.info("send start......2");
        logger.error("send start......3");
        logger.debug("send start......4");



//        threadSend();
    }

    public static void threadSend(){
        int poolSize=10;
        ExecutorService executorService= Executors.newFixedThreadPool(poolSize);
        for(int j=0;j<poolSize;j++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<2;i++){
                        String response= HttpClient.sendPostJson(url, JSON.toJSONString(getMsg()));
                        System.out.println("response:"+response);
//                        logger.info("response:"+response);
                    }
                }
            });
        }
    }

}
