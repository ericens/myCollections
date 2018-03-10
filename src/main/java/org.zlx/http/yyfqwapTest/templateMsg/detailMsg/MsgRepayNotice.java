package org.zlx.http.yyfqwapTest.templateMsg.detailMsg;


import org.zlx.http.yyfqwapTest.templateMsg.AbstractTemplateWeixinMsg;
import org.zlx.http.yyfqwapTest.templateMsg.TemplateMsgPart.TemplateValue;

/**
 <br>Created by linxin on 27/07/2017.

 <p>
 <br>标题：还款通知
 <br>模板ID: B0f47SK1bU8nvngw0Ay_MunrCWHm4KTc1BL12McAt-E

 <p>
 <br>微信消息:
 <br>{{first.DATA}}
 <br>还款金额：{{pay_money.DATA}}
 <br>还款日期：{{pay_date.DATA}}
 <br>{{remark.DATA}}

 <p>
 <br>示例消息
 <br>您的信用卡（尾号3202）还款成功
 <br>还款金额：235.53 元
 <br>还款日期：2013-11-12 18：19
 <br>微信还信用卡，弹指间轻松搞定。

 */
public class MsgRepayNotice extends AbstractTemplateWeixinMsg {

    TemplateValue payMoney;
    TemplateValue payDate;

    public TemplateValue getPayMoney() {
        return payMoney;
    }

    /**
     *
     * @param payMoney 还款金额
     */
    public void setPayMoney(TemplateValue payMoney) {
        this.payMoney = payMoney;
    }

    public TemplateValue getPayDate() {
        return payDate;
    }


    /**
     *
     * @param payDate 还款日期
     */
    public void setPayDate(TemplateValue payDate) {
        this.payDate = payDate;
    }


    @Override
    protected void transform() {
        template_id="B0f47SK1bU8nvngw0Ay_MunrCWHm4KTc1BL12McAt-E";
        data.put("first",first);

        data.put("pay_money",payMoney);
        data.put("pay_date",payDate);

        data.put("remark",remark);

        remark=null;
        first=null;

        payMoney=null;
        payDate=null;
    }



}
