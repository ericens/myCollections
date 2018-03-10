package org.zlx.http.yyfqwapTest.templateMsg.TemplateMsgPart;

/**
 * Created by ${linxin} on 24/07/2017.
 *
 *
 * 用于构造微信消息里面的一个数据项
 *
 *
 *
 */
public class TemplateValue {
    String value; //参数值,必输
    String color; //字体颜色，可选

    public TemplateValue (){

    }

    /**
     * 设置消息内容，字体颜色使用默认值#134793
     *
     * @param value 为消息内容
     */


    public TemplateValue (String value){
        this.value=value;
        color="#134793";
    }


    /**
     * Implements Map.get and related methods
     *
     * @param value 内容
     * @param color 为设置颜色
     * @return the node, or null if none
     */
    public TemplateValue (String value, String color){
        this.value=value;
        this.color=color;

    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
