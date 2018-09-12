## 第5章　并行模式与算法	184

### 5.1　探讨单例模式	184

- 频繁使用的对象省略new花费的时间
- 减轻GC压力，缩短GC停顿时间
- [Singleton](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch5/s1/Singleton.java)，demo有一个问题，创建和调用不是一致的
- [LazySingleton](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch5/s1/LazySingleton.java)
- [StaticSingleton](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch5/s1/StaticSingleton.java)，内部类没被调用前不会被加载

### 5.2　不变模式	187

- 不变模式的主要场景
    - 对象创建后，内部状态和数据不再发生变化
    - 对象需要共享，被多线程访问
- 注意点
    - 去除setter以及一切修改属性的方法
    - 将所有属性私有，并设为final，确保数据只能被构造一次，类不能有子类
    - 确保没有子类可以重载修改他
    - 有一个可以创建完整对象的构造函数
- [Product](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch5/s2/Product.java)，内部类没被调用前不会被加载
- 基本数据类型和String类型是不变模式实现的
  
### 5.3　生产者-消费者模式	190

- 共享内存缓存区，解耦，缓解时间差
- [代码](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch5/s3/Main.java)

### 5.4　高性能的生产者-消费者：无锁的实现	194

- ConcurrentLinkedQueue是一个高性能的队列，BlockingQueue只是为了方便数据共享
- ConcurrentLinkedQueue使用了CAS，现成的Disruptor实现了

#### 5.4.1　无锁的缓存框架：Disruptor	195

- LMAX公司开发
- 使用了环形队列（RingBuffer）
- 大小为2的整数次方

#### 5.4.2　用Disruptor实现生产者-消费者案例	196

- [代码](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch5/s4/Main.java)

#### 5.4.3　提高消费者的响应时间：选择合适的策略	199

- BlockingWaitStrategy
- SleepingWaitStrategy
- YieldingWaitStrategy
- BusySpinWaitStrategy

#### 5.4.4　CPU Cache的优化：解决伪共享问题	200

- 如果两个变量在同一个缓存行，其中一个被修改后另一个也会失效，所以要想办法通过占位来不让其在同一个缓存行
- [代码](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch5/s4/FalseSharing.java)
- Disruptor的Sequence组件基本结构

```java
class LhsPadding{
    protected long p1, p2, p3, p4, p5, p6, p7;
}

class Value extends LhsPadding{
    protected volatile long value;
}

class RhsPadding extends Value{
    protected long p9, p10, p11, p12, p13, p14, p15;
}

public class Sequence extends RhsPadding{
    //...
}
```


### 5.5　Future模式	204

- 核心思想是异步调用

#### 5.5.1　Future模式的主要角色	206

|参与者|作用|
|:---:|:---:|
|Main|系统启动，调用Client发出请求|
|Client|返回Data对象，立即返回FutureData，并开启ClientThread线程装配RealData|
|Data|返回数据的接口|
|FutureData|Future数据，构造很快，但是是一个虚拟的数据，需要装配RealData|
|RealData|真实数据，其构造是比较慢的|

#### 5.5.2　Future模式的简单实现	207

- [代码](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch5/s5/s2/Main.java)

#### 5.5.3　JDK中的Future模式	210

- 主要的几个类：Runnable，Future，RunnableFuture，FutureTask，Sync，Callable，类图见书中
- [代码](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch5/s5/s3)

### 5.6　并行流水线	212

- 并非所有的计算都可以改造成并发的形式
- 不能直接并发的可以考虑改成流水线的形式
- [代码](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch5/s6)

### 5.7　并行搜索	216

- 将数据按照期望的线程数进行分割
- [代码](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch5/s7)

### 5.8　并行排序	218

- 暂时没看懂，先跳过，之后回来读

#### 5.8.1　分离数据相关性：奇偶交换排序	218
#### 5.8.2　改进的插入排序：希尔排序	221

### 5.9　并行算法：矩阵乘法	226
### 5.10　准备好了再通知我：网络NIO	230

- Java NIO是New IO的简称
- 涉及的基础内容有通道（Channel）和缓冲区（Buffer），文件IO和网络IO

#### 5.10.1　基于Socket的服务端的多线程模式	230
#### 5.10.2　使用NIO进行网络编程	235
#### 5.10.3　使用NIO来实现客户端	243

### 5.11　读完了再通知我：AIO	245

#### 5.11.1　AIO EchoServer的实现	245
#### 5.11.2　AIO Echo客户端实现	248

### 5.12　参考文献	249