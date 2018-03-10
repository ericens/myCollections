package org.zlx.currentTest.secondKill.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.zlx.currentTest.secondKill.model.SecKillOrder;

public interface SecKillOrderMapper {

    @Delete("delete from sec_kill_order")
    int  deleteAll();


    @Insert("insert into sec_kill_order (consumer,goodsId,num) values(#{consumer},#{goodsid},#{num})")
    int insert(SecKillOrder secKillOrder);


}
