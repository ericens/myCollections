package org.zlx.currentTest.secondKill.model;

/**
 * Created by @author linxin on 09/10/2017.  <br>
 *
 * 封装前端的请求，标示客户consumer 想要购买 num件 商品goodsid，
 */
public class Request
{

    private String consumer;

    private Integer goodsid;

    private Integer num;

    public Request() {
    }

    public Request(String consumer, Integer goodsid, Integer num) {
        this.consumer = consumer;
        this.goodsid = goodsid;
        this.num = num;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
