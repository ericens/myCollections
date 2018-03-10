package org.zlx.http.yyfqwapTest.templateMsg;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.zlx.http.yyfqwapTest.templateMsg.TemplateMsgPart.MiniProgram;
import org.zlx.http.yyfqwapTest.templateMsg.TemplateMsgPart.TemplateValue;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *

 template_id 模板ID，必输
 data 为参数列表，参考该模板的参数列表设置
 参数项有 value 和color 构成，
 value必输， 为字符串
 color 为颜色，可选
 userid 用户id，必输
 url  跳转链接，可选
 miniprogram 跳转小程序，可选

 * Created by @author linxin on 01/08/2017.  <br>
 */
@Document
public class TemplateReceiveMsg implements Serializable{

    private static final long serialVersionUID = 4004537781446733585L;

    @Id
    String id;

    Map data=new HashMap<String ,TemplateValue>(); //为参数列表，参考该模板的参数列表设置

    String templateId;   //模板ID，必输

    String url;    //跳转链接，可选

    String userId;  //用户id，必输

    MiniProgram miniprogram; //跳转小程序，可选

    String toSendTime;// 定时发送时间 yyyyMMddhhmm，如果不需要定时，则为空

    String msgSendId; //消息唯一标示

    String weixinID; //微信号标示，给哪个微信号发送消息， 预留扩展用，可选

    String serialNo;//业务流水号

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeixinID() {
        return weixinID;
    }

    public void setWeixinID(String weixinID) {
        this.weixinID = weixinID;
    }


    public String getMsgSendId() {
        return msgSendId;
    }

    public void setMsgSendId(String msgSendId) {
        this.msgSendId = msgSendId;
    }


    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public MiniProgram getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(MiniProgram miniprogram) {
        this.miniprogram = miniprogram;
    }

    public String getToSendTime() {
        return toSendTime;
    }

    public void setToSendTime(String toSendTime) {
        this.toSendTime = toSendTime;
    }


    @Override
    public String toString() {
        return "TemplateReceiveMsg{" +
                "id='" + id + '\'' +
                ", data=" + data +
                ", templateId='" + templateId + '\'' +
                ", url='" + url + '\'' +
                ", userId='" + userId + '\'' +
                ", miniprogram=" + miniprogram +
                ", toSendTime='" + toSendTime + '\'' +
                ", msgSendId='" + msgSendId + '\'' +
                ", weixinID='" + weixinID + '\'' +
                ", serialNo='" + serialNo + '\'' +
                '}';
    }
}
