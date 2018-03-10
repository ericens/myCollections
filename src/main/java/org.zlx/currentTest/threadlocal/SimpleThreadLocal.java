package org.zlx.currentTest.threadlocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by @author linxin on 06/10/2017.  <br>
 * 虽然代码中，这个ThreadLocal实现版本显得比较幼稚，但它和JDK所提供的ThreadLocal类在实现思路上是相近的。
 *
 *  没有内存泄露的处理，
 *      弱引用
 *      key=null时，value的释放
 *
 */
public class SimpleThreadLocal {

    private Map valueMap = Collections.synchronizedMap(new HashMap());

    public void set(Object newValue) {

        valueMap.put(Thread.currentThread(), newValue);//①键为线程对象，值为本线程的变量副本

    }

    public Object get() {

        Thread currentThread = Thread.currentThread();

        Object o = valueMap.get(currentThread);//②返回本线程对应的变量

        if (o == null && !valueMap.containsKey(currentThread)) {
            //③如果在Map中不存在，放到Map中保存起来。

            o = initialValue();

            valueMap.put(currentThread, o);

        }

        return o;

    }

    public void remove() {

        valueMap.remove(Thread.currentThread());

    }

    public Object initialValue() {

        return null;

    }

}
