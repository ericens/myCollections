package org.zlx.http.yyfqwapTest.templateMsg.detailMsg;


import org.zlx.http.yyfqwapTest.templateMsg.AbstractTemplateWeixinMsg;
import org.zlx.http.yyfqwapTest.templateMsg.TemplateMsgPart.TemplateValue;

/**
 * Created by ${linxin} on 27/07/2017.

 r3UTRUoeD1WtRF4pDVaXueJyQ3ghDHDiYdtKtNIOo3Y

 交易提醒

 {{first.DATA}}
 银行卡号：{{keyword1.DATA}}
 交易时间：{{keyword2.DATA}}
 {{remark.DATA}}


 您尾号3016的卡11月20日13：46支出300.00元，手续费2.00元,保证金1000.00元，余额5700.00元
 银行卡号：3016
 交易时间：5月12日12:21
 多种类型混合支出

 */
public class MsgTradeNotice extends AbstractTemplateWeixinMsg {

    TemplateValue bankNO;
    TemplateValue tradeTime;


    public void setBankNO(TemplateValue bankNO) {
        this.bankNO = bankNO;
    }

    public void setTradeTime(TemplateValue tradeTime) {
        this.tradeTime = tradeTime;
    }

    @Override
    protected void transform() {
        template_id="r3UTRUoeD1WtRF4pDVaXueJyQ3ghDHDiYdtKtNIOo3Y";

        data.put("first",first);
        data.put("keyword1",bankNO);
        data.put("keyword2",tradeTime);
        data.put("remark",remark);

        remark=null;
        first=null;
        bankNO=null;
        tradeTime=null;
    }



}
