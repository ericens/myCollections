package org.zlx.currentTest.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by @author linxin on 14/10/2017.  <br>
 *
 *     CLH锁即Craig, Landin, and Hagersten (CLH) locks。CLH锁是一个自旋锁。能确保无饥饿性。提供先来先服务的公平性。

 相比自旋锁的优点：

 CLH锁也是一种基于链表的可扩展、高性能、公平的自旋锁，申请线程仅仅在本地变量上自旋，它不断轮询前驱的状态，假设发现前驱释放了锁就结束自旋。

 */

public class CLHLock  {

    AtomicReference<QNode> tail = new AtomicReference<QNode>(new QNode());
    ThreadLocal<QNode> myPred;
    ThreadLocal<QNode> myNode;

    public CLHLock() {
        tail = new AtomicReference<QNode>(new QNode());
        myNode = new ThreadLocal<QNode>() {
            protected QNode initialValue() {
                return new QNode();
            }
        };
        myPred = new ThreadLocal<QNode>() {
            protected QNode initialValue() {
                return null;
            }
        };
    }

    /**
     * 从代码中能够看出lock方法中有一个while循环，
     * 这 是在等待前趋结点的locked域变为false。这是一个自旋等待的过程。
     */
    public void lock() {
        QNode qnode = myNode.get();
        qnode.locked = true;
        QNode pred = tail.getAndSet(qnode);
        myPred.set(pred);
        while (pred.locked) {
        }
    }

    /**
     * unlock方法非常easy。仅仅须要将自己的locked域设置为false就可以。
     */

    public void unlock() {
        QNode qnode = myNode.get();
        qnode.locked = false;
        myNode.set(myPred.get());
    }




    class QNode{
        // 默认是在等待锁
        public boolean locked=true;
    }

    /**
     过程推演

     当线程A，B分别执行lock和unlock操作的时候。

     A lock：设置本线程中的本地myNode为一个新的QNode，设置locked标志为true，同时设置tail为本node并且获得tail之前设置的值（对于A线程就是一个崭新的QNode），并且把这个值设置为myPred，并且开始在myPred上自旋（实际上不会自旋）。

     B lock：开始和A线程一样，只是B线程从tail中获取的值是A线程的myNode的引用，而之前A线程将它的locked标识设置为true，所以B线程会自旋

     A unlock: 设置本地的myNode为false，并且回收myNode（实际上是设置myNode.set(new QNode())）。此时B的中的myPred（实际就是A的myNode）的locked标识为false，自旋结束

     B unlock：执行和A一样的操作，用来解除对之后可能的C，D，E线程的锁

     */


}
