## 第5章　并行模式与算法	184

### 5.1　探讨单例模式	184

- 频繁使用的对象省略new花费的时间
- 减轻GC压力，缩短GC停顿时间
- [Singleton](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch5/section1/Singleton.java)，demo有一个问题，创建和调用不是一致的
- [LazySingleton](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch5/section1/LazySingleton.java)
- [StaticSingleton](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch5/section1/StaticSingleton.java)，内部类没被调用前不会被加载

### 5.2　不变模式	187

- 不变模式的主要场景
    - 对象创建后，内部状态和数据不再发生变化
    - 对象需要共享，被多线程访问
- 注意点
    - 去除setter以及一切修改属性的方法
    - 将所有属性私有，并设为final，确保数据只能被构造一次，类不能有子类
    - 确保没有子类可以重载修改他
    - 有一个可以创建完整对象的构造函数
- [Product](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch5/section2/Product.java)，内部类没被调用前不会被加载
- 基本数据类型和String类型是不变模式实现的
  
### 5.3　生产者-消费者模式	190

- 共享内存缓存区，解耦，缓解时间差
- [代码](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch5/section3)

### 5.4　高性能的生产者-消费者：无锁的实现	194

- ConcurrentLinkedQueue是一个高性能的队列，BlockingQueue只是为了方便数据共享
- ConcurrentLinkedQueue使用了CAS，现成的Disruptor实现了

#### 5.4.1　无锁的缓存框架：Disruptor	195



#### 5.4.2　用Disruptor实现生产者-消费者案例	196
#### 5.4.3　提高消费者的响应时间：选择合适的策略	199
#### 5.4.4　CPU Cache的优化：解决伪共享问题	200

### 5.5　Future模式	204

#### 5.5.1　Future模式的主要角色	206
#### 5.5.2　Future模式的简单实现	207
#### 5.5.3　JDK中的Future模式	210

### 5.6　并行流水线	212
### 5.7　并行搜索	216
### 5.8　并行排序	218

#### 5.8.1　分离数据相关性：奇偶交换排序	218
#### 5.8.2　改进的插入排序：希尔排序	221

### 5.9　并行算法：矩阵乘法	226
### 5.10　准备好了再通知我：网络NIO	230

#### 5.10.1　基于Socket的服务端的多线程模式	230
#### 5.10.2　使用NIO进行网络编程	235
#### 5.10.3　使用NIO来实现客户端	243

### 5.11　读完了再通知我：AIO	245

#### 5.11.1　AIO EchoServer的实现	245
#### 5.11.2　AIO Echo客户端实现	248

### 5.12　参考文献	249