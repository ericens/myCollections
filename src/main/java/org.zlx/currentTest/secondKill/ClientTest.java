package org.zlx.currentTest.secondKill;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zlx.currentTest.secondKill.dao.SecKillGoodsMapper;
import org.zlx.currentTest.secondKill.dao.SecKillOrderMapper;
import org.zlx.currentTest.secondKill.model.SecKillGoods;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by @author linxin on 08/10/2017.  <br>
 */
public class ClientTest {

    private  static Logger logger= LoggerFactory.getLogger(ClientTest.class);


    @Test
    //插入数据
    public void testInsert(){
        SqlSessionFactory factory=MybatisUtils.getFactory();
        SqlSession session=factory.openSession(true);
        //使用反射的方法
        SecKillGoodsMapper mapper=session.getMapper(SecKillGoodsMapper.class);
        mapper.insert(new SecKillGoods(1, 100, "好袜子"));
        SecKillGoods secKillGoods= mapper.selectById(1);
        logger.info("goods:{}", JSON.toJSONString(secKillGoods));

        session.close();
    }



    @Test
    public void sigleTest() throws InterruptedException {
        KillAction killAction=new KillAction();
        KillService killService=new KillService();


        SqlSessionFactory factory=MybatisUtils.getFactory();
        SqlSession session=factory.openSession(true);
        //使用反射的方法
        SecKillGoodsMapper secKillGoodsMapper=session.getMapper(SecKillGoodsMapper.class);
        SecKillOrderMapper secKillOrderMapper=session.getMapper(SecKillOrderMapper.class);

        killService.setSecKillGoodsDao(secKillGoodsMapper);
        killService.setSecKillOrderDao(secKillOrderMapper);
        killAction.setKillService(killService);
        int num=new Random().nextInt(3)+1;
        String result=killAction.secKill("小花",2, num);

        logger.info("{} 购买数量{}",result,num);

        session.close();
    }




    @Test
    public void mutilUserTest(){
        //如果开1000个线程去访问数据库，那么报错### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException: Data source rejected establishment of connection,  message from server: "Too many connections"
        //数据库 没有那么多连接，200个 连也不行
        //ExecutorService service= Executors.newFixedThreadPool(1000);

        ExecutorService service= Executors.newFixedThreadPool(100);
        for(int i=0;i<100;i++){
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        sigleTest();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        // 立即返回，然后结束
        //service.shutdown();

        try {
            service.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    @Test
    public void mutilUserTest_withQueue(){
        //如果开1000个线程去访问数据库，那么报错### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException: Data source rejected establishment of connection,  message from server: "Too many connections"
        //数据库 没有那么多连接，200个 连也不行
        //ExecutorService service= Executors.newFixedThreadPool(1000);

        ExecutorService service= Executors.newFixedThreadPool(10);
        KillAction killAction=new KillAction();
        KillService killService=new KillService();
        killAction.setKillService(killService);

        for(int i=0;i<100000;i++){
            final int x=i;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    int num=new Random().nextInt(3)+1;
                    String result=killAction.secKill_withQueue("小花",2, num);
                    logger.info("{}:{}购买数量:{}",x,result,num);

                }
            });
        }
        // 立即返回，然后结束
        //service.shutdown();

        try {
            service.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
