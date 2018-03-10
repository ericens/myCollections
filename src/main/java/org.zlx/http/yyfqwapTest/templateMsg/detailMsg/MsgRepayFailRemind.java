package org.zlx.http.yyfqwapTest.templateMsg.detailMsg;


import org.zlx.http.yyfqwapTest.templateMsg.AbstractTemplateWeixinMsg;
import org.zlx.http.yyfqwapTest.templateMsg.TemplateMsgPart.TemplateValue;

/**
 * Created by linxin on 27/07/2017.
 <p>
 <br>模板ID: Hap_Z4fwuSz7RHKYS2UgC7CjwRQETq-4fyHLIwQaqQY
 <br>标题：交易失败提醒

 <p>
 <br>微信参数：

 <br>{{name.DATA}}：

 <br>{{productTpye.DATA}}
 <br>交易时间：{{time.DATA}}
 <br>交易金额：{{number.DATA}}
 <br>{{remark.DATA}}


 <p>
 <br>示例消息：
 <br>尊敬的金先生：

 <br>您尾号1234的信用卡最新交易信息：
 <br>交易时间：12月19日20时20分
 <br>交易金额：美金1000元

 <br>交易未成功，如有需要可重新支付。
 <br>回复“额度”，立即查阅您当前可用余额。

 */
public class MsgRepayFailRemind extends AbstractTemplateWeixinMsg {

    TemplateValue name;
    TemplateValue productTpye;
    TemplateValue time;
    TemplateValue number;



    @Override
    protected void transform() {
        template_id="Hap_Z4fwuSz7RHKYS2UgC7CjwRQETq-4fyHLIwQaqQY";

        data.put("name",name);
        data.put("productTpye",productTpye);
        data.put("time",time);
        data.put("number",number);
        data.put("remark",remark);


        name=null;
        productTpye=null;
        time=null;
        number=null;
        remark=null;


    }


    public TemplateValue getName() {
        return name;
    }

    /**
     *
     * @param name 名称提示
     */
    public void setName(TemplateValue name) {
        this.name = name;
    }

    public TemplateValue getProductTpye() {
        return productTpye;
    }

    /**
     *
     * @param productTpye 产品类型信息
     */
    public void setProductTpye(TemplateValue productTpye) {
        this.productTpye = productTpye;
    }

    public TemplateValue getTime() {
        return time;
    }

    /**
     *
     * @param time 交易时间
     */
    public void setTime(TemplateValue time) {
        this.time = time;
    }

    public TemplateValue getNumber() {
        return number;
    }

    /**
     *
     * @param number 交易金额
     */
    public void setNumber(TemplateValue number) {
        this.number = number;
    }
}
