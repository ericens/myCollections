package org.zlx.http.yyfqwapTest.templateMsg;

/**
 * Created by ${linxin} on 24/07/2017.
 */
public class WeixinResult {
    long errcode;
    String errmsg;
    Long msgid;


    public static int MSG_NOT_SEND=-2;
    public static int MSG_SEND_FAIL=-1;
    public static int MSG_SEND_SUCESS=0;



    Object data;

    public WeixinResult(){

    }

    public WeixinResult(long errcode,String errmsg){
        this(errcode,errmsg,null);
    }
    public WeixinResult(long errcode, String errmsg, Object data){
        this.errcode=errcode;
        this.errmsg=errmsg;
        this.data=data;
    }


    public static WeixinResult getDefaultFailResult(){
      return  new WeixinResult(MSG_SEND_FAIL,"发送失败");
    }


    public static WeixinResult getDefaultSucessResult(){
        return  new WeixinResult(MSG_SEND_SUCESS,"发送成功");
    }




    public long getErrcode() {
        return errcode;
    }

    public void setErrcode(long errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Long getMsgid() {
        return msgid;
    }

    public void setMsgid(Long msgid) {
        this.msgid = msgid;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }



}
