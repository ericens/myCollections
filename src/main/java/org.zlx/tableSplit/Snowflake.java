package org.zlx.tableSplit;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by @author linxin on 09/03/2018.  <br>
 */
@Data
@Slf4j
public class Snowflake
{

    //工作机器id的bit段拆分为前5个bit标记workerid，后5个bit标记datacenterid
    static int WorkerIdBits = 5;
    static int DatacenterIdBits = 5;
    //序列号bit数
    static int SequenceBits = 12;
    //最大编号限制
    static long MaxWorkerId = -1L ^ (-1L << WorkerIdBits);
    static long MaxDatacenterId = -1L ^ (-1L << DatacenterIdBits);

    private static long SequenceMask = -1L ^ (-1L << SequenceBits);


    //位左运算移动量
    public static int WorkerIdShift = SequenceBits;
    public static int DatacenterIdShift = SequenceBits + WorkerIdBits;
    public static int TimestampLeftShift = SequenceBits + WorkerIdBits + DatacenterIdBits;

    //序列号记录
    private static  long sequence = 0L;
    //时间戳记录
    private static  long lastTimestamp = -1L;
    static  long WorkerId;
    static   long DatacenterId;

    public Snowflake(long workerId, long datacenterId, long sequence) throws Exception {
        WorkerId = workerId;
        DatacenterId = datacenterId;
        sequence = sequence;

        // sanity check for workerId
        if (workerId > MaxWorkerId || workerId < 0)
        {
            throw new Exception( "worker Id can't be greater than "+MaxWorkerId+" or less than 0");
        }

        if (datacenterId > MaxDatacenterId || datacenterId < 0)
        {
            throw new Exception( "datacenterId can't be greater than "+MaxDatacenterId+" or less than 0");
        }

    }

    public  static String NextId() throws Exception {

        Long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp)
        {
            throw new Exception(
                    "发现最新时间戳少{0}毫秒的异常"+ (lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp)
        {
            synchronized (Snowflake.class){
                sequence = (sequence + 1) & SequenceMask;
                if (sequence == 0)
                {
                    //序列号超过限制，重新取时间戳
                    timestamp = TilNextMillis(lastTimestamp);
                }
            }
        }
        else
        {
            sequence = 0;
        }

        lastTimestamp = timestamp;

        //snowflake算法
        //snowflake算法
//        long id = (timestamp << TimestampLeftShift) |
//                (DatacenterId << DatacenterIdShift) |
//                (WorkerId << WorkerIdShift) | sequence;
        return timestamp+":"+sequence;

    }
    /// <summary>
    /// 重新取时间戳
    /// </summary>
    protected static long TilNextMillis(long lastTimestamp)
    {
        Long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp)
        {
            //新的时间戳要大于旧的时间戳，才算作有效时间戳
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        for(int j=0;j<100;j++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<100;i++){
                        try {
                            log.info("{}",Snowflake.NextId());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

    }
}
