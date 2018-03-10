package org.zlx.currentTest.semaphore;

import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by @author linxin on 08/10/2017.  <br>
 * 防止  同时  超过 N 个线程进入
 * 最后，Semaphore除了控制资源的多个副本的并发访问控制，
 * 也可以使用二进制信号量来实现类似synchronized关键字和Lock锁的并发访问控制功能
 *
 *  比如有 N个打印机，同时允许并发数就为N
 */
public class SemaphoreDemo {

    int sequence=0;
    @Test
    public void test(){


        //如果把 N 设置为1，那么可以起到同步的效果，也就是串行化
        //Semaphore semaphore=new Semaphore(1);

        //最终结果就不是1000了
        Semaphore semaphore=new Semaphore(10);

        for(int i=0;i<1000;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(semaphore.tryAcquire(2, TimeUnit.SECONDS)){
                            sequence++;
                            System.out.println("线程获取了信号量 "+ Thread.currentThread().getName()+" get semaphore"+sequence);

                            semaphore.release();
                        }else {
                            System.out.println("线程未获取信号量 "+ Thread.currentThread().getName()+" get no semaphore");
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();

        }

    }



}
