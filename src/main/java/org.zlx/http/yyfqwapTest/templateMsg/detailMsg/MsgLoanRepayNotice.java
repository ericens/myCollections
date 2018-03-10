package org.zlx.http.yyfqwapTest.templateMsg.detailMsg;


import org.zlx.http.yyfqwapTest.templateMsg.AbstractTemplateWeixinMsg;
import org.zlx.http.yyfqwapTest.templateMsg.TemplateMsgPart.TemplateValue;

/**
 * Created by linxin on 28/07/2017.<br>

<p>
<br>模板ID: If-1my5GfurIWYZtTJPoStEmT8Efd8pEvxXNTlagymM
<br>标题：贷款还款提醒

<p>
<br>微信参数：
<br> {{first.DATA}}
<br> {{headinfo.DATA}}
<br> 还款日期：{{payDate.DATA}}
<br> 还款金额：{{payMoney.DATA}}
<br> {{remark.DATA}}


<p> 示例消息：ßßßß
<br>尊敬的刘先生：

<br>您尾号8105招行一卡通的贷款还款日快到了
<br>还款日期：2014年3月6日
<br>还款金额：人民币100000.00

<br>☆温馨提示☆ 为了您的贷款能按时还款，您可以点击全文登录手机银行查看理财日历详情，并做好相关资金安排。

 */
public class MsgLoanRepayNotice extends AbstractTemplateWeixinMsg {

    TemplateValue headinfo;
    TemplateValue payDate;
    TemplateValue payMoney;

    public TemplateValue getHeadinfo() {
        return headinfo;
    }

    /**
     *
     * @param headinfo 提示头信息
     */
    public void setHeadinfo(TemplateValue headinfo) {
        this.headinfo = headinfo;
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


    @Override
    protected void transform() {

        template_id="If-1my5GfurIWYZtTJPoStEmT8Efd8pEvxXNTlagymM";

        data.put("first",first);
        data.put("headinfo",headinfo);
        data.put("payDate",payDate);
        data.put("payMoney",payMoney);
        data.put("remark",remark);

        first=null;
        headinfo=null;
        payDate=null;
        payMoney=null;
        remark=null;


    }



}
