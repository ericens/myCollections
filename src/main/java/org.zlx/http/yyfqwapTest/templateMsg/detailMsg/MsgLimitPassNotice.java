package org.zlx.http.yyfqwapTest.templateMsg.detailMsg;

import org.zlx.http.yyfqwapTest.templateMsg.AbstractTemplateWeixinMsg;
import org.zlx.http.yyfqwapTest.templateMsg.TemplateMsgPart.TemplateValue;

/**
 * Created by linxin on 27/07/2017.
 <p>
 标题: 额度审批通过提醒   <br>
 模板ID: _UHU-GuyzXquR3Po31J833R3AbQYcT__THKwjWOrk-U  <br><br/>

 <p><p/>
 微信参数：    <br>
 {{first.DATA}}  <br>
 额度类型：{{keyword1.DATA}}  <br>
 可用额度：{{keyword2.DATA}} <br>
 {{remark.DATA}}  <br>

 <p></p>
 您申请的薪易通项下质押贷款业务已审核通过  <br>
 额度类型：质押额度  <br>
 可用额度：￥50,000  <br>
 您可以通过“贷款查询”功能查询贷款的详细额度信息 <br>

 */
public class MsgLimitPassNotice extends AbstractTemplateWeixinMsg {

    TemplateValue limitType;
    TemplateValue availableLimit;


    @Override
    protected void transform() {
        template_id="_UHU-GuyzXquR3Po31J833R3AbQYcT__THKwjWOrk-U";

        data.put("first",first);
        data.put("keyword1",limitType);
        data.put("keyword2", availableLimit);
        data.put("remark",remark);

        remark=null;
        first=null;
        limitType=null;
        availableLimit=null;
    }


    public TemplateValue getLimitType() {
        return limitType;
    }

    /**
     *
     * @param limitType  额度类型
     */
    public void setLimitType(TemplateValue limitType) {
        this.limitType = limitType;
    }

    public TemplateValue getAvailableLimit() {
        return availableLimit;
    }

    /**
     *
     * @param availableLimit 可用额度
     */
    public void setAvailableLimit(TemplateValue availableLimit) {
        this.availableLimit = availableLimit;
    }
}
