package org.zlx.currentTest.secondKill.model;

public class SecKillGoods {
    private Integer id;

    private Integer remainNum;

    private String goodsName;

    public SecKillGoods() {
    }

    public SecKillGoods(Integer id, Integer remainNum, String goodsName) {
        this.id = id;
        this.remainNum = remainNum;
        this.goodsName = goodsName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(Integer remainNum) {
        this.remainNum = remainNum;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }
}
