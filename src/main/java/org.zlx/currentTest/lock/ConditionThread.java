package org.zlx.currentTest.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by @author linxin on 15/10/2017.  <br>
 */
public class ConditionThread implements Runnable {

    /**
     * @author 五月的仓颉http://www.cnblogs.com/xrq730/p/7067904.html
     */
    public static void main(String args[]) throws Exception {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        // 线程0的作用是signal
        Runnable runnable0 = new ConditionThread(lock, condition);
        Thread thread0 = new Thread(runnable0);
        thread0.setName("线程0");
        // 线程1的作用是await
        Runnable runnable1 = new ConditionThread(lock, condition);
        Thread thread1 = new Thread(runnable1);
        thread1.setName("线程1");
        // 线程2的作用是lock
        Runnable runnable2 = new ConditionThread(lock, condition);
        Thread thread2 = new Thread(runnable2);
        thread2.setName("线程2");

        thread1.start();
        Thread.sleep(1000);
        thread0.start();
        Thread.sleep(1000);
        thread2.start();

        thread1.join();
    }


    private Lock lock;

    private Condition condition;



    public ConditionThread(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {

        if ("线程0".equals(Thread.currentThread().getName())) {
            thread0Process();
        } else if ("线程1".equals(Thread.currentThread().getName())) {
            thread1Process();
        } else if ("线程2".equals(Thread.currentThread().getName())) {
            thread2Process();
        }

    }

    private void thread0Process() {
        try {
            lock.lock();
            System.out.println("线程0休息5秒");

            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            condition.signal();
            System.out.println("线程0唤醒等待线程");
        } finally {
            lock.unlock();
        }
    }

    private void thread1Process() {
        try {
            lock.lock();
            System.out.println("线程1阻塞");
            condition.await();
            System.out.println("线程1被唤醒");
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }

    private void thread2Process() {
        try {
            System.out.println("线程2想要获取锁");
            lock.lock();
            System.out.println("线程2获取锁成功");
        } finally {
            lock.unlock();
        }
    }



}
