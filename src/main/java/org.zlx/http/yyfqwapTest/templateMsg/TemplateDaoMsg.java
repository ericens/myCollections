package org.zlx.http.yyfqwapTest.templateMsg;


import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by @author linxin on 31/07/2017.  <br>
 */
public class TemplateDaoMsg implements Serializable{

    private static final long serialVersionUID = 8897370266757424996L;

    private long id;
    private long userId;
    private String openId;
    private String msgContentId;
    private Timestamp createTime;
    private Timestamp updateTime;
    private int status;
    private long weixinMsgId;
    private String errorMsg;
    private Timestamp toSendTime;
    private String serialNo;
    private TemplateReceiveMsg templateReceiveMsg;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public TemplateDaoMsg(){
        status=-2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getMsgContentId() {
        return msgContentId;
    }

    public void setMsgContentId(String msgContentId) {
        this.msgContentId = msgContentId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getWeixinMsgId() {
        return weixinMsgId;
    }

    public void setWeixinMsgId(long weixinMsgId) {
        this.weixinMsgId = weixinMsgId;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Timestamp getToSendTime() {
        return toSendTime;
    }

    public void setToSendTime(Timestamp toSendTime) {
        this.toSendTime = toSendTime;
    }

    public TemplateReceiveMsg getTemplateReceiveMsg() {
        return templateReceiveMsg;
    }

    public void setTemplateReceiveMsg(TemplateReceiveMsg templateReceiveMsg) {
        this.templateReceiveMsg = templateReceiveMsg;
    }

    @Override
    public String toString() {
        return "TemplateDaoMsg{" +
                "id=" + id +
                ", userId=" + userId +
                ", openId='" + openId + '\'' +
                ", msgContentId='" + msgContentId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", weixinMsgId=" + weixinMsgId +
                ", errorMsg='" + errorMsg + '\'' +
                ", toSendTime=" + toSendTime +
                ", templateReceiveMsg=" + templateReceiveMsg +
                '}';
    }
}

