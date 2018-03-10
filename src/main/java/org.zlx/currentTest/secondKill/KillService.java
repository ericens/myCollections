package org.zlx.currentTest.secondKill;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zlx.currentTest.secondKill.dao.SecKillGoodsMapper;
import org.zlx.currentTest.secondKill.dao.SecKillOrderMapper;
import org.zlx.currentTest.secondKill.model.SecKillGoods;
import org.zlx.currentTest.secondKill.model.SecKillOrder;

import javax.annotation.PostConstruct;

/**
 * Created by @author linxin on 08/10/2017.  <br>
 *  DAO  的设计，需要获取session进行数据库，有两个问题
 *      1. 何时开启一个session
 *      2. 何时关闭一个session，那么其中的事务处理呢？
 *      3. 如何区别不同的线程调用的 是不同的session。
 *  答案
 *      1. 应该有应用层控制session 的开启和关闭，让他来处理事务。
 *          因为只有它知道哪些数据库操作是应该在同一个 链接里面
 */

@Service
public class KillService {

    @Autowired
    SecKillGoodsMapper secKillGoodsDao;

    @Autowired
    SecKillOrderMapper secKillOrderDao;

    public SecKillGoodsMapper getSecKillGoodsDao() {
        return secKillGoodsDao;
    }

    public void setSecKillGoodsDao(SecKillGoodsMapper secKillGoodsDao) {
        this.secKillGoodsDao = secKillGoodsDao;
    }

    public SecKillOrderMapper getSecKillOrderDao() {
        return secKillOrderDao;
    }

    public void setSecKillOrderDao(SecKillOrderMapper secKillOrderDao) {
        this.secKillOrderDao = secKillOrderDao;
    }



    /**
     * 程序启动时：
     * 初始化秒杀商品，清空订单数据
     */
    @PostConstruct
    public void initSecKillEntity(){
        secKillGoodsDao.deleteAll();
        secKillOrderDao.deleteAll();
        SecKillGoods secKillGoods = new SecKillGoods();
        secKillGoods.setId(12345);
        secKillGoods.setGoodsName("秒杀产品");
        secKillGoods.setRemainNum(10);
        secKillGoodsDao.insert(secKillGoods);
    }



    /**
     * 购买成功,保存订单
     */
    public void generateOrder(SecKillOrder order) {
        SqlSession sqlSession=MybatisUtils.getSession();
        SecKillOrderMapper secKillOrderDao=sqlSession.getMapper(SecKillOrderMapper.class);
        secKillOrderDao.insert(order);
    }


    /**
     *  扣减库存
     * @param id  商品标号ID
     * @param num 购买数量
     */
    public int reduceStock(int id,int num){
        SqlSession sqlSession=MybatisUtils.getSession();
        SecKillGoodsMapper secKillGoodsDao=sqlSession.getMapper(SecKillGoodsMapper.class);
        return secKillGoodsDao.reduceStock(id,num);
    }


    public SecKillGoods selectByPrimaryKey(int goodsId) {
        SqlSession sqlSession=MybatisUtils.getSession();
        SecKillGoodsMapper secKillGoodsDao=sqlSession.getMapper(SecKillGoodsMapper.class);
        return secKillGoodsDao.selectById(goodsId);
    }
}
