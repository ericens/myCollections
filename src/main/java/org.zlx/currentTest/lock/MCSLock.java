package org.zlx.currentTest.lock;

/**
 * Created by @author linxin on 14/10/2017.  <br>
 */

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 *
 差异：

 从代码实现来看，CLH比MCS要简单得多。
 从自旋的条件来看，CLH是在前驱节点的属性上自旋，而MCS是在本地属性变量上自旋。
 从链表队列来看，CLH的队列是隐式的，CLHNode并不实际持有下一个节点；MCS的队列是物理存在的。
 CLH锁释放时只需要改变自己的属性，MCS锁释放则需要改变后继节点的属性。
 */
public class MCSLock {

    public static class MCSNode {
        volatile MCSNode next;
        volatile boolean isBlock = true; // 默认是在等待锁
    }

    volatile MCSNode tail;// 指向最后一个申请锁的MCSNode



    private static final AtomicReferenceFieldUpdater UPDATER = AtomicReferenceFieldUpdater
            .newUpdater(MCSLock.class, MCSNode.class, "tail");

    public void lock(MCSNode currentThread) {

        MCSNode predecessor = (MCSNode)UPDATER.getAndSet(this, currentThread);// step 1
        if (predecessor != null) {
            predecessor.next = currentThread;// step 2

            while (currentThread.isBlock) {// step 3
            }
        }else { // 只有一个线程在使用锁，没有前驱来通知它，所以得自己标记自己为非阻塞
            currentThread. isBlock = false;
        }
    }

    public void unlock(MCSNode currentThread) {
        if (currentThread.isBlock) {// 锁拥有者进行释放锁才有意义
            return;
        }

        if (currentThread.next == null) {// 检查是否有人排在自己后面
            if (UPDATER.compareAndSet(this, currentThread, null)) {// step 4
                // compareAndSet返回true表示确实没有人排在自己后面
                return;
            } else {
                // 突然有人排在自己后面了，可能还不知道是谁，下面是等待后续者
                // 这里之所以要忙等是因为：step 1执行完后，step 2可能还没执行完
                while (currentThread.next == null) { // step 5
                }
            }
        }

        currentThread.next.isBlock = false;
        currentThread.next = null;// for GC
    }
}
