package org.zlx.currentTest;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*

 在上述例子中，getResult()方法的实现过程中，迭代了FutureTask的数组，如果任务还没有完成则当前线程会阻塞，
 如果我们希望任意任务完成后就把其结果加到result中，而不用依次等待每个任务完成，可以使用CompletionService。
它与ExecutorService最主要的区别在于submit的task不一定是按照加入时的顺序完成的。
CompletionService对ExecutorService进行了包装，内部维护一个保存Future对象的BlockingQueue。
只有当这个Future对象状态是结束的时候，才会加入到这个Queue中，take()方法其实就是Producer-Consumer中的Consumer。
它会从Queue中取出Future对象，如果Queue是空的，就会阻塞在那里，直到有完成的Future对象加入到Queue中。所以，先完成的必定先被取出。
这样就减少了不必要的等待时间。


 */
public class ConcurrentSum2 {
    private int coreCpuNum;
    private ExecutorService  executor;
    private CompletionService<Long> completionService;

    public ConcurrentSum2(){
    	 coreCpuNum = Runtime.getRuntime().availableProcessors();
         executor = Executors.newFixedThreadPool(coreCpuNum);
         completionService=new ExecutorCompletionService(executor);

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
            SumCalculator task = new SumCalculator("name"+i,nums, start, end);

            if(!executor.isShutdown()){
                completionService.submit(task);
            }
        }
        return getPartSum();
    }
    public long getPartSum(){
        long sum = 0;
        for(int i=0;i<coreCpuNum;i++){
            try {
                sum += completionService.take().get();
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
}
