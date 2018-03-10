package org.zlx.weakReference;

import java.lang.ref.WeakReference;

/**
 * Created by @author linxin on 06/10/2017.  <br>
 */
public class WeakReferenceExample {


    /**
     *  A对象的引用a置空了，a不再指向对象A的地址，我们都知道当一个对象不再被其他对象引用的时候，
     *  是会被GC回收的，很显然及时a=null，
     *  那么A对象也是不可能被回收的，因为B依然依赖与A，在这个时候，造成了内存泄漏！
     */
    public void strong_leak(){
        A a = new A();

        B b = new B(a);

        a = null;
    }


    /**
     * 这个时候B对象再也没有被任何引用，A对象只被B对象引用，
     * GC也是可以同时回收他们俩的，因为他们处于不可到达区域。
     */
    public void strong_without_leak(){
        A a = new A();

        B b = new B(a);

        a = null;

        b = null;
    }

    /**
     * 这个时候A只被弱引用依赖，那么GC会立刻回收A这个对象，这就是弱引用的好处！
     * 他可以在你对对象结构和拓扑不是很清晰的情况下，
     * 帮助你合理的释放对象，造成不必要的内存泄漏！！
     */
    public void weak(){
        A a = new A();

        WeakReference wr = new WeakReference(a);

        a=null;
    }


    /**
     * 弱引用的继承 有待考究
     */
    static class Entry extends WeakReference<A> {
        A a;
        String value;
        Entry(A a,String value) {
            super(a);
            this.a = a;
            this.value=value;
        }
    }

    public void weakExtends(){

        for(int i=0;i<4;i++){
            A a=new A();
            Entry e=new Entry(a,"s"+i);
        }


    }






    static class A{

    }
    static class B{
        A a;
        public  B(A a){
            this.a=a;
        }
    }
}
