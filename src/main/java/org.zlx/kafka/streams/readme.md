0.  目前可以，运行streams ，知道stream的概念，知道streams 的作用，意义。

1.  没有验证 streams 和 自己写的consumer的效率差别

2.  没有分析 streams 的线程模型。因为他仅仅是客户端的类库。
http://www.infoq.com/cn/articles/kafka-analysis-part-7
   所以，也就是客户端 
    1.   怎么去消费broker上的消息，
    2.   怎么去心跳的？

如果某个Stream的输入Topic有多个(比如2个Topic，1个Partition数为4，另一个Partition数为3)，
则总的Task数等于Partition数最多的那个Topic的Partition数（max(4,3)=4）。
这是因为Kafka Stream使用了Consumer的Rebalance机制，每个Partition对应一个Task.

注意，这里要保证两个进程的StreamsConfig.APPLICATION_ID_CONFIG完全一样。
因为Kafka Stream将APPLICATION_ID_CONFI作为隐式启动的Consumer的Group ID
