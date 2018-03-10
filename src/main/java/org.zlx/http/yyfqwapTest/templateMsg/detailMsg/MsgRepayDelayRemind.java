package org.zlx.http.yyfqwapTest.templateMsg.detailMsg;


import org.zlx.http.yyfqwapTest.templateMsg.AbstractTemplateWeixinMsg;
import org.zlx.http.yyfqwapTest.templateMsg.TemplateMsgPart.TemplateValue;

/**
 <br>Created by linxin on 28/07/2017.
 <p>
 <br>模板ID: 8GtfBJTCENlAdyylLztw2Z67qYyuTpzXpsmweDr4wKg
 <br>标题：贷款逾期提醒

 <p>
 <br>微信参数：
 <br>{{first.DATA}}
 <br>逾期天数：{{keyword1.DATA}}
 <br>还款金额：{{keyword2.DATA}}
 <br>{{remark.DATA}}

 <p>
 <br>示例消息
 <br>尊敬的xxx先生，您的360借条已逾期，为避免影响到您的信用记录和产生罚息，请及时足额还款，如已还款请忽略！
 <br>逾期天数：15天
 <br>还款金额：2000元
 <br>详询：400-9200-360


 */
public class MsgRepayDelayRemind extends AbstractTemplateWeixinMsg {

    TemplateValue delayDays;
    TemplateValue repayAmt;

    public TemplateValue getDelayDays() {
        return delayDays;
    }

    /**
     *
     * @param delayDays 逾期天数
     */
    public void setDelayDays(TemplateValue delayDays) {
        this.delayDays = delayDays;
    }

    public TemplateValue getRepayAmt() {
        return repayAmt;
    }

    /**
     *
     * @param repayAmt 还款金额
     */
    public void setRepayAmt(TemplateValue repayAmt) {
        this.repayAmt = repayAmt;
    }

    @Override
    protected void transform() {
        template_id="8GtfBJTCENlAdyylLztw2Z67qYyuTpzXpsmweDr4wKg";
        data.put("first",first);

        data.put("keyword1",delayDays);
        data.put("keyword2",repayAmt);

        data.put("remark",remark);

        remark=null;
        first=null;

        delayDays=null;
        repayAmt=null;

    }
}
