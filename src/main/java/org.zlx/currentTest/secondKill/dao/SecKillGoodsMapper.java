package org.zlx.currentTest.secondKill.dao;

import org.apache.ibatis.annotations.*;
import org.zlx.currentTest.secondKill.model.SecKillGoods;

public interface SecKillGoodsMapper {
    //直接扣减库存，没有判断库存里面是否还有库存，至少在数据库层面没有控制
    //@Update("update sec_kill_goods g set g.remain_num = g.remain_num - #{num} where g.id=#{id}")

    //没有写参数对应的情况下报错，Caused by: org.apache.ibatis.binding.BindingException: Parameter 'remainNum' not found. Available parameters are [0, 1, param1, param2]
    //int reduceStock(int id, int num);

    //数据库层面控制，只有再库存 大于 购买量时才扣减,此时保证了库存扣减不会为负数
    @Update("update sec_kill_goods g set g.remain_num = g.remain_num - #{num} where g.id=#{id} and g.remain_num >= #{num}")
    int reduceStock(@Param("id") int id, @Param("num") int num);


    @Insert("insert into sec_kill_goods (remain_num,goods_name) values( #{remainNum},#{goodsName} ) ")
    int insert(SecKillGoods record);

    @Delete("delete from sec_kill_goods")
    int deleteAll();

    @Select("select id, remain_num as remainNum, goods_name as goodsName from sec_kill_goods where id =#{id}")
    SecKillGoods selectById(int id);

}
