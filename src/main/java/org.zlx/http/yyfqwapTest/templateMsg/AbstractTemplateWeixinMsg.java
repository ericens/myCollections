package org.zlx.http.yyfqwapTest.templateMsg;


import org.zlx.http.yyfqwapTest.templateMsg.TemplateMsgPart.MiniProgram;
import org.zlx.http.yyfqwapTest.templateMsg.TemplateMsgPart.TemplateValue;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ${linxin} on 2017/7/21.
 */
public  abstract  class AbstractTemplateWeixinMsg implements Serializable{

    private static final long serialVersionUID = -5167853734906166337L;

    protected String touser;// 发送给谁  ，openID

    /**
     *  模板ID
     */
    protected String template_id; //templateID

    /**
     *  跳转链接，可选
     */
    protected String url; // 链接

    /**
     * 跳转小程序，可选
     */
    protected MiniProgram miniprogram; //小程序

    protected Map data=new HashMap(); //参数

    String from;//发给谁




    String userID; // 我们系统的用户ID



    String msgID;//微信生成的ID

    String sendMsgID; //调用端生成消息ID


    Timestamp createTime;

    /**
     * 一般作为参数的第一个字段, 具体参考每个消息的参数列表
     */
    protected TemplateValue first;

    /**
     * 一般作为参数的最后一个字段, 具体参考每个消息的参数列表
     */
    protected TemplateValue remark;


    /**
     * 发送消息前，调用子类的 transform 函数，把各个参数设置到data 里面
     */
    public void convert() {

        transform();
    }


    public String getUserID() {
        return userID;
    }

    /**
     *
     * @param userID 把消息发送给谁，userID
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    protected abstract void transform();


    public TemplateValue getFirst() {
        return first;
    }

    /**
     *
     * @param first 一般作为参数的第一个字段, 具体参考每个消息的参数列表
     */
    public void setFirst(TemplateValue first) {
        this.first = first;
    }

    public TemplateValue getRemark() {
        return remark;
    }

    /**
     *
     * @param remark  一般作为参数的最后一个字段, 具体参考每个消息的参数列表
     */
    public void setRemark(TemplateValue remark) {
        this.remark = remark;
    }


    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url 跳转链接，可选
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public MiniProgram getMiniprogram() {
        return miniprogram;
    }

    /**
     *
     * @param miniprogram 跳转小程序，可选
     */
    public void setMiniprogram(MiniProgram miniprogram) {
        this.miniprogram = miniprogram;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMsgID() {
        return msgID;
    }

    public void setMsgID(String msgID) {
        this.msgID = msgID;
    }

    public String getSendMsgID() {
        return sendMsgID;
    }

    public void setSendMsgID(String sendMsgID) {
        this.sendMsgID = sendMsgID;
    }



    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setMsgUUID(){
        UUID uuid=UUID.randomUUID();
        this.sendMsgID=uuid.toString();
    }
}
