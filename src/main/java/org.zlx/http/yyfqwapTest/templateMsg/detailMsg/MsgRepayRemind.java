package org.zlx.http.yyfqwapTest.templateMsg.detailMsg;


import org.zlx.http.yyfqwapTest.templateMsg.AbstractTemplateWeixinMsg;
import org.zlx.http.yyfqwapTest.templateMsg.TemplateMsgPart.TemplateValue;

/**
 Created by ${linxin} on 27/07/2017.

 还款提醒
 65ddLOUFZh1ousW3h0CwmGTAMxpizGbFagfyKNQZb0g

 {{first.DATA}}

 {{productTpye.DATA}}
 还款时间：{{time.DATA}}
 还款金额：{{payAmount.DATA}}
 还款卡号：{{cardNOLast4.DATA}}
 {{remark.DATA}}



 尊敬的金先生：

 您招行个人卡账户最新还款信息:

 还款时间：01月23日 9：15
 还款金额：人民币1000元
 还款卡号：8888
 您个人卡账户01月账单已还清。账户当前可用额度为￥50000元。

 */
public class MsgRepayRemind extends AbstractTemplateWeixinMsg {

    TemplateValue productTpye;
    TemplateValue time;
    TemplateValue payAmount;
    TemplateValue cardNOLast4;



    @Override
    protected void transform() {
        template_id="65ddLOUFZh1ousW3h0CwmGTAMxpizGbFagfyKNQZb0g";
        data.put("first",first);

        data.put("productTpye",productTpye);
        data.put("time",time);
        data.put("payAmount",payAmount);
        data.put("cardNOLast4",cardNOLast4);


        data.put("remark",remark);

        remark=null;
        first=null;

        productTpye=null;
        time=null;
        payAmount=null;
        cardNOLast4=null;

    }






    public TemplateValue getProductTpye() {
        return productTpye;
    }

    public void setProductTpye(TemplateValue productTpye) {
        this.productTpye = productTpye;
    }

    public TemplateValue getTime() {
        return time;
    }

    public void setTime(TemplateValue time) {
        this.time = time;
    }

    public TemplateValue getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(TemplateValue payAmount) {
        this.payAmount = payAmount;
    }

    public TemplateValue getCardNOLast4() {
        return cardNOLast4;
    }

    public void setCardNOLast4(TemplateValue cardNOLast4) {
        this.cardNOLast4 = cardNOLast4;
    }



}
