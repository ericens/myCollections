package org.zlx.currentTest.secondKill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zlx.currentTest.secondKill.model.Request;
import org.zlx.currentTest.secondKill.model.SecKillGoods;
import org.zlx.currentTest.secondKill.model.SecKillOrder;

import java.util.concurrent.*;

/**
 * Created by @author linxin on 08/10/2017.  <br>
 */

@Controller
public class KillAction {
    @Autowired
    KillService killService;

    volatile boolean remains=true;

    public KillAction() {
        init();
    }

    ExecutorService service = Executors.newFixedThreadPool(50);

//    ConcurrentLinkedQueue queue=new ConcurrentLinkedQueue();//无界

    /**
     * 后端每次只处理5000个请求
     */
    ArrayBlockingQueue<Request> queue=new ArrayBlockingQueue<Request>(500);

    public KillService getKillService() {
        return killService;
    }

    public void setKillService(KillService killService) {
        this.killService = killService;
    }


    /**
     * 最最原始的方式，从应用层面到数据库层面，没有一个地方进行过并发控制。库存扣减为负数
     * @param consumer
     * @param goodsId
     * @param num
     * @return
     * @throws InterruptedException
     */
    public String secKill(String consumer, int goodsId, Integer num) throws InterruptedException {
        //查找出用户要买的商品
        SecKillGoods goods = killService.selectByPrimaryKey(goodsId);
        //如果有这么多库存
        if(goods.getRemainNum()>=num){
            //模拟网络延时
            Thread.sleep(1000);
            //先减去库存
            if(killService.reduceStock(goods.getId(),num)>0){
                //保存订单
                killService.generateOrder(new SecKillOrder(consumer,goodsId,num));
                return "购买成功";
            }

        }
        return "购买失败,库存不足";
    }


    /**
     * 通过队列，进行限流，正真同时在后端处理的，只有50个线程
     * @param consumer
     * @param goodsId
     * @param num
     * @return
     */
    public String secKill_withQueue(String consumer, int goodsId, Integer num)  {
        if(!remains){
            return "活动结束";
        }
        Request request=new Request(consumer,goodsId,num);

        try {
            queue.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "处理中........";

    }

    public void init() {
        //后台处理  放50个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Request request  = queue.take();
                        Future<String> future=service.submit(new MyTask(request));
                        String str=future.get();
                        System.out.println("result:"+str);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }


    /**
     * 怎么通知前端呢？使用Callable，而不是Runnable
     */

    class MyTask implements Callable{
        Request request=new Request();
        public MyTask ( Request request){
            this.request=request;

        }

        @Override
        public String call() {

            try {
                MybatisUtils.getSession();
                SecKillGoods goods = killService.selectByPrimaryKey(request.getGoodsid());
                if(goods.getRemainNum()==0){
                    remains=false;
                }
                //如果有这么多库存
                if(goods.getRemainNum()>=request.getNum()){
                    //模拟网络延时

                    Thread.sleep(1000);

                    //先减去库存
                    if(killService.reduceStock(goods.getId(),request.getNum())>0){
                        //保存订单
                        killService.generateOrder(new SecKillOrder(request.getConsumer(),request.getGoodsid(),request.getNum()));
                        return "购买成功";
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                MybatisUtils.close();
            }
            return "购买失败,库存不足";
        }
    }

}
