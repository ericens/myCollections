package org.zlx.currentTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


/*
 Java SE5的java.util.concurrent包中的执行器（Executor）将为你管理Thread对象，从而简化了并发编程。
 Executor在客户端和执行任务之间提供了一个间接层，Executor代替客户端执行任务。
 Executor允许你管理异步任务的执行，而无须显式地管理线程的生命周期。Executor在Java SE5/6中时启动任务的优选方法。
 Executor引入了一些功能类来管理和使用线程Thread，其中包括线程池，Executor，Executors，ExecutorService，CompletionService，Future，Callable等

 创建线程池
Executors类，提供了一系列工厂方法用于创先线程池，返回的线程池都实现了ExecutorService接口。

public static ExecutorService newFixedThreadPool(int nThreads)
创建固定数目线程的线程池。
public static ExecutorService newCachedThreadPool()
创建一个可缓存的线程池，调用execute 将重用以前构造的线程（如果线程可用）。如果现有线程没有可用的，则创建一个新线程并添加到池中。终止并从缓存中移除那些已有 60 秒钟未被使用的线程。
public static ExecutorService newSingleThreadExecutor()
创建一个单线程化的Executor。
public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize)
创建一个支持定时及周期性的任务执行的线程池，多数情况下可用来替代Timer类。

见类图，接口Executor只有一个方法execute，接口ExecutorService扩展了Executor并添加了一些生命周期管理的方法，如shutdown、submit等。一个Executor的生命周期有三种状态，运行 ，关闭 ，终止。


 Callable，Future用于返回结果
Future<V>代表一个异步执行的操作，通过get()方法可以获得操作的结果，如果异步操作还没有完成，则，get()会使当前线程阻塞。
FutureTask<V>实现了Future<V>和Runable<V>。
Callable代表一个有返回值得操作。
实例：并行计算求和

 */
public class ConcurrentSum {

    private int coreCpuNum;
    private ExecutorService  executor;
    private List<FutureTask<Long>> tasks = new ArrayList<FutureTask<Long>>();
    public ConcurrentSum(){
        coreCpuNum = Runtime.getRuntime().availableProcessors();
        executor = Executors.newFixedThreadPool(coreCpuNum);
    }
    class SumCalculator implements Callable<Long>{
        int nums[];
        int start;
        int end;
        String nameString;
        public SumCalculator(String name,final int nums[],int start,int end){
            this.nums = nums;
            this.start = start;
            this.end = end;
            this.nameString=name;
        }
        @Override
        public Long call() throws Exception {
            long sum =0;
            for(int i=start;i<end;i++){
                sum += nums[i];
            }
            System.out.println(nameString+":"+sum);
            return sum;
        }
    }
    public long sum(int[] nums){
        int start,end,increment;
        // 根据CPU核心个数拆分任务，创建FutureTask并提交到Executor
        for(int i=0;i<coreCpuNum;i++){
            increment = nums.length / coreCpuNum+1;
            start = i*increment;
            end = start+increment;
            if(end > nums.length){
                end = nums.length;
            }
            SumCalculator calculator = new SumCalculator(i+"name",nums, start, end);
            FutureTask<Long> task = new FutureTask<Long>(calculator);
            tasks.add(task);
            if(!executor.isShutdown()){
                executor.submit(task);
            }
        }
        return getPartSum();
    }
    public long getPartSum(){
        long sum = 0;
        for(int i=0;i<tasks.size();i++){
            try {
                sum += tasks.get(i).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }
    public void close(){
        executor.shutdown();
    }

    public static void main(String[] args) {
        int arr[] = new int[]{1, 22, 33, 4, 52, 61, 7, 48, 10, 11,111 };
        long sum = new ConcurrentSum().sum(arr);
        System.out.println("sum: " + sum);
        long sum2 = new ConcurrentSum2().sum(arr);
        System.out.println("sum2: " + sum2);

    }
}
