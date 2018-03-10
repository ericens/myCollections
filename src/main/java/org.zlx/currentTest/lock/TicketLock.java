package org.zlx.currentTest.lock;

/**
 * Created by @author linxin on 14/10/2017.  <br>
 */
import java.util.concurrent.atomic.AtomicInteger;

/**
 Ticket Lock 虽然解决了公平性的问题，但是多处理器系统上，
 每个进程/线程占用的处理器都在读写同一个变量serviceNum ，
 每次读写操作都必须在多个处理器缓存之间进行缓存同步，
 这会导致繁重的系统总线和内存的流量，大大降低系统整体的性能。

 */
public class TicketLock {
    private AtomicInteger serviceNum = new AtomicInteger(); // 服务号
    private AtomicInteger ticketNum = new AtomicInteger(); // 排队号

    public int lock() {
        // 首先原子性地获得一个排队号
        int myTicketNum = ticketNum.getAndIncrement();

        // 只要当前服务号不是自己的就不断轮询
        while (serviceNum.get() != myTicketNum) {
        }

        return myTicketNum;
    }

    public void unlock(int myTicketNum) {
        // 只有当前线程拥有者才能释放锁
        int next = myTicketNum + 1;

        serviceNum.compareAndSet(myTicketNum, next);
    }
}
