## 第3章　JDK并发包	70

### 3.1　多线程的团队协作：同步控制	70
    
#### 3.1.1　synchronized的功能扩展：重入锁	71

- [ReenterLock](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/chapter3/ReenterLock.java)
- 可重入锁代表着一个线程可以多次获得同一把锁

#### 3.1.2　重入锁的好搭档：Condition条件	80
#### 3.1.3　允许多个线程同时访问：信号量（Semaphore）	83
#### 3.1.4　ReadWriteLock读写锁	85
#### 3.1.5　倒计时器：CountDownLatch	87
#### 3.1.6　循环栅栏：CyclicBarrier	89
#### 3.1.7　线程阻塞工具类：LockSupport	92
        
### 3.2　线程复用：线程池	95
    
#### 3.2.1　什么是线程池	96
#### 3.2.2　不要重复发明轮子：JDK对线程池的支持	97
#### 3.2.3　刨根究底：核心线程池的内部实现	102
#### 3.2.4　超负载了怎么办：拒绝策略	106
#### 3.2.5　自定义线程创建：ThreadFactory	109
#### 3.2.6　我的应用我做主：扩展线程池	110
#### 3.2.7　合理的选择：优化线程池线程数量	112
#### 3.2.8　堆栈去哪里了：在线程池中寻找堆栈	113
#### 3.2.9　分而治之：Fork/Join框架	117
        
### 3.3　不要重复发明轮子：JDK的并发容器	121
    
#### 3.3.1　超好用的工具类：并发集合简介	121
#### 3.3.2　线程安全的HashMap	122
#### 3.3.3　有关List的线程安全	123
#### 3.3.4　高效读写的队列：深度剖析ConcurrentLinkedQueue	123
#### 3.3.5　高效读取：不变模式下的CopyOnWriteArrayList	129
#### 3.3.6　数据共享通道：BlockingQueue	130
#### 3.3.7　随机数据结构：跳表（SkipList）	134
        
### 3.4　参考资料	136