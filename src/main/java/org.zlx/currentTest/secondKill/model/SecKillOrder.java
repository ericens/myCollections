package org.zlx.currentTest.secondKill.model;

public class SecKillOrder {
    private Integer id;

    private String consumer;

    private Integer goodsid;

    private Integer num;

    public SecKillOrder(String consumer, int goodsId, Integer num) {
        this.consumer=consumer;
        this.goodsid=goodsId;
        this.num=num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer == null ? null : consumer.trim();
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
