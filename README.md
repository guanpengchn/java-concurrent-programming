# 《实战Java高并发程序设计》笔记和源码

## 笔记

- 《实战Java高并发程序设计》中有很多代码范例，适合初学者通过实践入门并发编程
- 笔者做了相关笔记并整理源代码，欢迎交流和 [Star](https://github.com/guanpengchn/java-concurrent-programming)
- 由于除了jdk外，需要引入第三方包，故而使用maven来进行包管理，建议运行代码前安装好maven环境
- 笔记在 [notes](https://github.com/guanpengchn/java-concurrent-programming/blob/master/notes)，源代码在 [src/main/java](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java)
- 如有需要电子书，可去我的博客中[下载](https://guanpengchn.github.io/#/book)，或者直接访问[aaron.books](https://github.com/guanpengchn/aaron.books)仓库
- part-部，chapter-章，section-节，paragraph/fragment/segment-完整的一段，passage-篇
- 笔记中使用的JDK源代码[仓库](https://github.com/guanpengchn/JDK)，fork于[zxiaofan的JDK仓库](https://github.com/zxiaofan/JDK)
- [彻底删除git中的大文件](https://www.jianshu.com/p/780161d32c8e)

## 书籍概况

- 作者: 葛一鸣 / 郭超
- 出版社: 电子工业出版社
- 出版年: 2015-11
- 页数: 352
- 定价: 69.00元
- ISBN: 9787121273049

## 目录

- 第1章　走入并行世界	1

    - 1.1　何去何从的并行计算 1
    
        - 1.1.1　忘掉那该死的并行	2
        - 1.1.2　可怕的现实：摩尔定律的失效	4
        - 1.1.3　柳暗花明：不断地前进	5
        - 1.1.4　光明或是黑暗	6
        
    - 1.2　你必须知道的几个概念	6
    
        - 1.2.1　同步（Synchronous）和异步（Asynchronous）	7
        - 1.2.2　并发（Concurrency）和并行（Parallelism）	8
        - 1.2.3　临界区	9
        - 1.2.4　阻塞（Blocking）和非阻塞（Non-Blocking）	9
        - 1.2.5　死锁（Deadlock）、饥饿（Starvation）和活锁（Livelock）	9
        
    - 1.3　并发级别	11
    
        - 1.3.1　阻塞（Blocking）	11
        - 1.3.2　无饥饿（Starvation-Free）	11
        - 1.3.3　无障碍（Obstruction-Free）	12
        - 1.3.4　无锁（Lock-Free）	12
        - 1.3.5　无等待（Wait-Free）	13
        
    - 1.4　有关并行的两个重要定律	13
    
        - 1.4.1　Amdahl定律	13
        - 1.4.2　Gustafson定律	16
        - 1.4.3　Amdahl定律和Gustafson定律是否相互矛盾	16
        
    - 1.5　回到Java：JMM	17
    
        - 1.5.1　原子性（Atomicity）	18
        - 1.5.2　可见性（Visibility）	20
        - 1.5.3　有序性（Ordering）	22
        - 1.5.4　哪些指令不能重排：Happen-Before规则	27
        
    - 1.6　参考文献	27
    
- 第2章　Java并行程序基础	29

    - 2.1　有关线程你必须知道的事	29
    - 2.2　初始线程：线程的基本操作	32
    
        - 2.2.1　新建线程	32
        - 2.2.2　终止线程	34
        - 2.2.3　线程中断	38
        - 2.2.4　等待（wait）和通知（notify）	41
        - 2.2.5　挂起（suspend）和继续执行（resume）线程	44
        - 2.2.6　等待线程结束（join）和谦让（yield）	48
        
    - 2.3　volatile与Java内存模型（JMM）	50
    - 2.4　分门别类的管理：线程组	52
    - 2.5　驻守后台：守护线程（Daemon）	54
    - 2.6　先干重要的事：线程优先级	55
    - 2.7　线程安全的概念与synchronized	57
    - 2.8　程序中的幽灵：隐蔽的错误	61
    
        - 2.8.1　无提示的错误案例	61
        - 2.8.2　并发下的ArrayList	62
        - 2.8.3　并发下诡异的HashMap	63
        - 2.8.4　初学者常见问题：错误的加锁	66
        
    - 2.9　参考文献	68
    
- 第3章　JDK并发包	70

    - 3.1　多线程的团队协作：同步控制	70
    
        - 3.1.1　synchronized的功能扩展：重入锁	71
        - 3.1.2　重入锁的好搭档：Condition条件	80
        - 3.1.3　允许多个线程同时访问：信号量（Semaphore）	83
        - 3.1.4　ReadWriteLock读写锁	85
        - 3.1.5　倒计时器：CountDownLatch	87
        - 3.1.6　循环栅栏：CyclicBarrier	89
        - 3.1.7　线程阻塞工具类：LockSupport	92
        
    - 3.2　线程复用：线程池	95
    
        - 3.2.1　什么是线程池	96
        - 3.2.2　不要重复发明轮子：JDK对线程池的支持	97
        - 3.2.3　刨根究底：核心线程池的内部实现	102
        - 3.2.4　超负载了怎么办：拒绝策略	106
        - 3.2.5　自定义线程创建：ThreadFactory	109
        - 3.2.6　我的应用我做主：扩展线程池	110
        - 3.2.7　合理的选择：优化线程池线程数量	112
        - 3.2.8　堆栈去哪里了：在线程池中寻找堆栈	113
        - 3.2.9　分而治之：Fork/Join框架	117
        
    - 3.3　不要重复发明轮子：JDK的并发容器	121
    
        - 3.3.1　超好用的工具类：并发集合简介	121
        - 3.3.2　线程安全的HashMap	122
        - 3.3.3　有关List的线程安全	123
        - 3.3.4　高效读写的队列：深度剖析ConcurrentLinkedQueue	123
        - 3.3.5　高效读取：不变模式下的CopyOnWriteArrayList	129
        - 3.3.6　数据共享通道：BlockingQueue	130
        - 3.3.7　随机数据结构：跳表（SkipList）	134
        
    - 3.4　参考资料	136
    
- 第4章　锁的优化及注意事项	138

    - 4.1　有助于提高“锁”性能的几点建议	139
    
        - 4.1.1　减小锁持有时间	139
        - 4.1.2　减小锁粒度	140
        - 4.1.3　读写分离锁来替换独占锁	142
        - 4.1.4　锁分离	142
        - 4.1.5　锁粗化	144
        
    - 4.2　Java虚拟机对锁优化所做的努力	146
    
        - 4.2.1　锁偏向	146
        - 4.2.2　轻量级锁	146
        - 4.2.3　自旋锁	146
        - 4.2.4　锁消除	146
        
    - 4.3　人手一支笔：ThreadLocal	147
    
        - 4.3.1　ThreadLocal的简单使用	148
        - 4.3.2　ThreadLocal的实现原理	149
        - 4.3.3　对性能有何帮助	155
        
    - 4.4　无锁	157
    
        - 4.4.1　与众不同的并发策略：比较交换（CAS）	158
        - 4.4.2　无锁的线程安全整数：AtomicInteger	159
        - 4.4.3　Java中的指针：Unsafe类	161
        - 4.4.4　无锁的对象引用：AtomicReference	162
        - 4.4.5　带有时间戳的对象引用：AtomicStampedReference	165
        - 4.4.6　数组也能无锁：AtomicIntegerArray	168
        - 4.4.7　让普通变量也享受原子操作：AtomicIntegerFieldUpdater	169
        - 4.4.8　挑战无锁算法：无锁的Vector实现	171
        - 4.4.9　让线程之间互相帮助：细看SynchronousQueue的实现	176
        
    - 4.5　有关死锁的问题	179
    - 4.6　参考文献	183
    
- 第5章　并行模式与算法	184

    - 5.1　探讨单例模式	184
    - 5.2　不变模式	187
    - 5.3　生产者-消费者模式	190
    - 5.4　高性能的生产者-消费者：无锁的实现	194
    
        - 5.4.1　无锁的缓存框架：Disruptor	195
        - 5.4.2　用Disruptor实现生产者-消费者案例	196
        - 5.4.3　提高消费者的响应时间：选择合适的策略	199
        - 5.4.4　CPU Cache的优化：解决伪共享问题	200
        
    - 5.5　Future模式	204
    
        - 5.5.1　Future模式的主要角色	206
        - 5.5.2　Future模式的简单实现	207
        - 5.5.3　JDK中的Future模式	210
        
    - 5.6　并行流水线	212
    - 5.7　并行搜索	216
    - 5.8　并行排序	218
    
        - 5.8.1　分离数据相关性：奇偶交换排序	218
        - 5.8.2　改进的插入排序：希尔排序	221
        
    - 5.9　并行算法：矩阵乘法	226
    - 5.10　准备好了再通知我：网络NIO	230
    
        - 5.10.1　基于Socket的服务端的多线程模式	230
        - 5.10.2　使用NIO进行网络编程	235
        - 5.10.3　使用NIO来实现客户端	243
        
    - 5.11　读完了再通知我：AIO	245
    
        - 5.11.1　AIO EchoServer的实现	245
        - 5.11.2　AIO Echo客户端实现	248
        
    - 5.12　参考文献	249
    
- 第6章　Java 8与并发	251

    - 6.1　Java 8的函数式编程简介	251
    
        - 6.1.1　函数作为一等公民	252
        - 6.1.2　无副作用	252
        - 6.1.3　申明式的（Declarative）	253
        - 6.1.4　不变的对象	254
        - 6.1.5　易于并行	254
        - 6.1.6　更少的代码	254
        
    - 6.2　函数式编程基础	255
    
        - 6.2.1　FunctionalInterface注释	255
        - 6.2.2　接口默认方法	256
        - 6.2.3　lambda表达式	259
        - 6.2.4　方法引用	260
        
    - 6.3　一步一步走入函数式编程	263
    - 6.4　并行流与并行排序	267
    
        - 6.4.1　使用并行流过滤数据	267
        - 6.4.2　从集合得到并行流	268
        - 6.4.3　并行排序	268
        
    - 6.5　增强的Future：CompletableFuture	269
    
        - 6.5.1　完成了就通知我	269
        - 6.5.2　异步执行任务	270
        - 6.5.3　流式调用	272
        - 6.5.4　CompletableFuture中的异常处理	272
        - 6.5.5　组合多个CompletableFuture	273
        
    - 6.6　读写锁的改进：StampedLock	274
    
        - 6.6.1　StampedLock使用示例	275
        - 6.6.2　StampedLock的小陷阱	276
        - 6.6.3　有关StampedLock的实现思想	278
        
    - 6.7　原子类的增强	281
    
        - 6.7.1　更快的原子类：LongAdder	281
        - 6.7.2　LongAdder的功能增强版：LongAccumulator	287
        
    - 6.8　参考文献	288
    
- 第7章　使用Akka构建高并发程序	289

    - 7.1　新并发模型：Actor	290
    - 7.2　Akka之Hello World	290
    - 7.3　有关消息投递的一些说明	293
    - 7.4　Actor的生命周期	295
    - 7.5　监督策略	298
    - 7.6　选择Actor	303
    - 7.7　消息收件箱（Inbox）	303
    - 7.8　消息路由	305
    - 7.9　Actor的内置状态转换	308
    - 7.10　询问模式：Actor中的Future	311
    - 7.11　多个Actor同时修改数据：Agent	313
    - 7.12　像数据库一样操作内存数据：软件事务内存	316
    - 7.13　一个有趣的例子：并发粒子群的实现	319
    
        - 7.13.1　什么是粒子群算法	320
        - 7.13.2　粒子群算法的计算过程	320
        - 7.13.3　粒子群算法能做什么	322
        - 7.13.4　使用Akka实现粒子群	323
        
    - 7.14　参考文献	330
    
- 第8章　并行程序调试	331

    - 8.1　准备实验样本	331
    - 8.2　正式起航	332
    - 8.3　挂起整个虚拟机	334
    - 8.4　调试进入ArrayList内部	336
