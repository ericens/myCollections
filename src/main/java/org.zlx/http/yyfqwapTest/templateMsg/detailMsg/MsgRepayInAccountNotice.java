package org.zlx.http.yyfqwapTest.templateMsg.detailMsg;


import org.zlx.http.yyfqwapTest.templateMsg.AbstractTemplateWeixinMsg;
import org.zlx.http.yyfqwapTest.templateMsg.TemplateMsgPart.TemplateValue;

/**
 <br>Created by ${linxin} on 28/07/2017.

 <p>
 <br>模板ID: C4_PgmsVV6EJ8DO1yjMIEZtP6UQ81mZpsjQAPJW4p8I
 <br>标题：还款入账通知

 <p>
 <br>微信参数
 <br>{{first.DATA}}
 <br>还款时间：{{keyword1.DATA}}
 <br>还款金额：{{keyword2.DATA}}
 <br>{{remark.DATA}}

 <p>
 <br>示例消息
 <br>尊敬的张先生，您信用卡最新还款信息如下：
 <br>还款时间：2017年5月12号
 <br>还款金额：100元
 <br>感谢您的使用
 <br>
 */
public class MsgRepayInAccountNotice extends AbstractTemplateWeixinMsg {

    TemplateValue reapyTime;
    TemplateValue repayAmt;

    public TemplateValue getReapyTime() {
        return reapyTime;
    }

    /**
     *
     * @param reapyTime 还款时间
     */
    public void setReapyTime(TemplateValue reapyTime) {
        this.reapyTime = reapyTime;
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
        template_id="C4_PgmsVV6EJ8DO1yjMIEZtP6UQ81mZpsjQAPJW4p8I";
        data.put("first",first);

        data.put("keyword1", reapyTime);
        data.put("keyword2",repayAmt);

        data.put("remark",remark);

        remark=null;
        first=null;

        reapyTime =null;
        repayAmt=null;

    }
}
