package org.zlx.weakReference;

import java.lang.ref.WeakReference;

/**
 * Created by @author linxin on 06/10/2017.  <br>
 */
public class Demo
{
    /**
     * 当然为了更清楚地看到GC的效果，
     * 设置虚拟机参数"-XX:+PrintGCDetails"：
     *
     *
     *
     *
     * WeakReferenceCar's Car is alive for 62137, loop - org.zlx.currentTest.df$WeakReferenceCar@1eb44e46
     WeakReferenceCar's Car is alive for 62138, loop - org.zlx.currentTest.df$WeakReferenceCar@1eb44e46
     [GC (Allocation Failure) [PSYoungGen: 34979K->1684K(38400K)] 34995K->1708K(125952K), 0.0012761 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     WeakReferenceCar's Car is alive for 62139, loop - org.zlx.currentTest.df$WeakReferenceCar@1eb44e46
     WeakReferenceCar's Car has bean collected
     Heap
     PSYoungGen      total 38400K, used 2556K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
     eden space 33280K, 2% used [0x0000000795580000,0x0000000795659f78,0x0000000797600000)
     from space 5120K, 32% used [0x0000000797600000,0x00000007977a50e8,0x0000000797b00000)
     to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
     ParOldGen       total 87552K, used 24K [0x0000000740000000, 0x0000000745580000, 0x0000000795580000)
     object space 87552K, 0% used [0x0000000740000000,0x0000000740006000,0x0000000745580000)
     Metaspace       used 3340K, capacity 4500K, committed 4864K, reserved 1056768K
     class space    used 355K, capacity 388K, committed 512K, reserved 1048576K
     * @param args
     */
    public static void main(String[] args)
    {
        Car car = new Car(2000.0, "red");
        WeakReferenceCar wrc = new WeakReferenceCar(car);

        int i = 0;
        while (true)
        {
            if (wrc.get() != null)
            {
                i++;
                System.out.println("WeakReferenceCar's Car is alive for " + i + ", loop - " + wrc);
            }
            else
            {
                System.out.println("WeakReferenceCar's Car has bean collected");
                break;
            }
        }
    }

    static class Car
    {
        private double     price;
        private String    color;

        public Car(double price, String color)
        {
            this.price = price;
            this.color = color;
        }

        public double getPrice()
        {
            return price;
        }

        public String getColor()
        {
            return color;
        }

        public String toString()
        {
            return "This car is a " + this.color + " car, costs $" + price;
        }
    }


    static class WeakReferenceCar extends WeakReference<Car>
    {
        public WeakReferenceCar(Car car)
        {
            super(car);
        }
    }



}
