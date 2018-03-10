package org.zlx.http.yyfqwapTest.templateMsg.detailMsg;

import org.zlx.http.yyfqwapTest.templateMsg.AbstractTemplateWeixinMsg;
import org.zlx.http.yyfqwapTest.templateMsg.TemplateMsgPart.TemplateValue;

/**
 * Created by linxin on 27/07/2017. <br><br>

 <p>
 模板ID: MLosFzG3lRtYRSy8tQG2IWUHxFsoo22AsLVYRo1ipb8 <br>
 标题：提额通知 <br>

 <p>
 模板参数：<br><br>

 {{first.DATA}}  <br>
 目前额度：{{keyword1.DATA}}  <br>
 提高金额：{{keyword2.DATA}}  <br>
 提额日期：{{keyword3.DATA}}  <br>
 {{remark.DATA}}   <br>

 <p>
 示例消息：<br><br>

 恭喜您，您的信用额度获得提高  <br>
 目前额度：5000   <br>
 提高金额：100    <br>
 提额日期：2015年8月21日  <br>
 感谢您关注么么贷  <br>


 */
public class MsgLimitAdvance extends AbstractTemplateWeixinMsg {

//    目前额度：{{keyword1.DATA}}
//    提高金额：{{keyword2.DATA}}
//    提额日期：{{keyword3.DATA}}


    TemplateValue currentLimit;
    TemplateValue advanceLimit;
    TemplateValue advanceDate;

    public TemplateValue getCurrentLimit() {
        return currentLimit;
    }

    /**
     *
     * @param currentLimit 目前额度
     */
    public void setCurrentLimit(TemplateValue currentLimit) {
        this.currentLimit = currentLimit;
    }

    public TemplateValue getAdvanceLimit() {
        return advanceLimit;
    }

    /**
     *
     * @param advanceLimit  提高金额
     */
    public void setAdvanceLimit(TemplateValue advanceLimit) {
        this.advanceLimit = advanceLimit;
    }

    public TemplateValue getAdvanceDate() {
        return advanceDate;
    }


    /**
     *
     * @param advanceDate 提额日期
     */
    public void setAdvanceDate(TemplateValue advanceDate) {
        this.advanceDate = advanceDate;
    }

    public void checkParam(){



    }

    @Override
    protected void transform() {
        template_id="MLosFzG3lRtYRSy8tQG2IWUHxFsoo22AsLVYRo1ipb8";
        data.put("first",first);
        data.put("keyword1",currentLimit);
        data.put("keyword2",advanceLimit);
        data.put("keyword3",advanceDate);
        data.put("remark",remark);

        first=null;
        currentLimit=null;
        advanceDate=null;
        advanceLimit=null;
        remark=null;

    }
}
