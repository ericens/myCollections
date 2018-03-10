package org.zlx.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by @author linxin on 12/09/2017.  <br>
 */
public class HttpClient {

    public static String METHOD_GET ="GET";
    public static String MEHOD_POST ="POST";
    public static String MEHOD_PUT ="PUT";
    public static String MEHOD_DELETE ="DELETE";




    public  static void main(String arsg[]){
        String dataJson = "{\"data\":{\"first\":{\"color\":\"#134793\",\"value\":\"恭喜您，您的信用额度获得提高\"},\"keyword1\":{\"color\":\"#134793\",\"value\":\"5000\"},\"keyword2\":{\"color\":\"#134793\",\"value\":\"100\"},\"keyword3\":{\"color\":\"#134793\",\"value\":\"2015年8月21日\"},\"remark\":{\"color\":\"#134793\",\"value\":\"点击下方的“业务办理”-“取现金”，或直接点击详情，即可全额取现！\"}},\"serialNo\":\"4d-9117-fb98ac220fe\",\"templateId\":\"MLosFzG3lRtYRSy8tQG2IWUHxFsoo22AsLVYRo1ipb8\",\"userId\":\"1281073018920\"}";
        System.out.println("dataJson======>");
        System.out.println(dataJson);

        String url="http://localhost:8080/templateMsg/send";
        String reulst= sendPostJson(url, dataJson);
        System.out.println("reulst "+reulst);

    }




    public static String sendPostJson(String url, String param) {
        return sendPost(url,param,true);
    }
    public static String sendPostForm(String url, String param) {
        return sendPost(url,param,false);
    }

    public static String sendGetJson(String url, String param) {
        return sendGet(url,param,true);
    }
    public static String sendGetForm(String url, String param) {
        return sendGet(url,param,false);
    }



    public static String sendGet(String url, String param,boolean isJson) {
        return dosend(METHOD_GET,url,param,isJson);
    }

    public static String sendPost(String url, String param, boolean isJson) {
        return dosend(MEHOD_POST,url,param,isJson);
    }

    public static String sendPut(String url, String param, boolean isJson) {
        return dosend(MEHOD_PUT,url,param,isJson);
    }


    public static String dosend(String method,String url, String param,boolean isJson) {
        OutputStream out=null;
        BufferedReader in = null;
        StringBuilder sb = new StringBuilder();


        try {

            byte[] data = param.getBytes("UTF-8");

            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();




            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("timestamp", "2014-02-25 14:31:35");

            // 设置维持长连接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集:
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件长度
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));

            conn.setRequestMethod(method);
            // 发送POST,PUT DELETE 请求必须设置如下两行
            if(!method.equals(METHOD_GET)){
                conn.setDoOutput(true);
                conn.setDoInput(true);

            }


            // 设置文件类型:
            if(isJson){
                //JSON
                conn.setRequestProperty("Content-Type","application/json;charset=UTF-8");
            }else {
                //form 表单
                conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            }
            // 设置接收类型否则返回415错误
            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
            conn.setRequestProperty("accept","application/json");

//            System.out.println("the connection properties is "+ JSON.toJSONString(conn));

            // 开始连接请求
            conn.connect();

            if(!method.equals(METHOD_GET)){
                // 获取URLConnection对象对应的输出流
                out=conn.getOutputStream();
                // 发送请求参数
                out.write(data);
                // flush输出流的缓冲
                out.flush();
            }
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

//            System.out.println("content-encode："+conn.getContentEncoding());
//            System.out.println("content-length："+conn.getContentLength());
//            System.out.println("content-type："+conn.getContentType());
//            System.out.println("date："+conn.getDate());

            String line = "";
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            System.out.println("发送 "+method+" 请求出现异常！"+e);
            e.printStackTrace();
        } finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }

        return sb.toString();
    }
}
