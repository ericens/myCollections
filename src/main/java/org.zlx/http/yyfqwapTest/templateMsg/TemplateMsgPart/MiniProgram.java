package org.zlx.http.yyfqwapTest.templateMsg.TemplateMsgPart;

/**
 * Created by ${linxin} on 24/07/2017.
 */
public class MiniProgram {
    String appid;
    String pagepath;

    public MiniProgram(){

    }

    public MiniProgram(String appid,  String pagepath){
        this.appid=appid;
        this.pagepath=pagepath;

    }
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }
}
