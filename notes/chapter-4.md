## 第4章　锁的优化及注意事项	138

### 4.1　有助于提高“锁”性能的几点建议	139

#### 4.1.1　减小锁持有时间	139

- JDK1.7源代码：[Pattern](https://github.com/guanpengchn/JDK/blob/master/JDK1.7/src/java/util/regex/Pattern.java)

#### 4.1.2　减小锁粒度	140

- 对整个HashMap加锁粒度过大，对于ConcurrentHashMap内部细分若干个HashMap，称之为段，被分成16个段
- 现根据hashcode得到应该存放到哪个段中，然后对该段加锁
- JDK8中改变了实现方式，使用CAS来做实现，区别可见[文章](https://blog.csdn.net/Gavin__Zhou/article/details/76792071)

#### 4.1.3　读写分离锁来替换独占锁	142

- [ReadWriteLock](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/chapter3/section1/ReadWriteLockDemo.java)

#### 4.1.4　锁分离	142

- 在LinkedBlockingQueue中，take和put函数分别作用于队列的前端和尾端，不冲突，如果使用独占锁就无法并发，所以实现中使用了两把锁
- JDK1.7源代码：[LinkedBlockingQueue](https://github.com/guanpengchn/JDK/blob/master/JDK1.7/src/java/util/concurrent/LinkedBlockingQueue.java)

#### 4.1.5　锁粗化	144



### 4.2　Java虚拟机对锁优化所做的努力	146

#### 4.2.1　锁偏向	146
#### 4.2.2　轻量级锁	146
#### 4.2.3　自旋锁	146
#### 4.2.4　锁消除	146

### 4.3　人手一支笔：ThreadLocal	147

#### 4.3.1　ThreadLocal的简单使用	148
#### 4.3.2　ThreadLocal的实现原理	149
#### 4.3.3　对性能有何帮助	155

### 4.4　无锁	157

#### 4.4.1　与众不同的并发策略：比较交换（CAS）	158
#### 4.4.2　无锁的线程安全整数：AtomicInteger	159
#### 4.4.3　Java中的指针：Unsafe类	161
#### 4.4.4　无锁的对象引用：AtomicReference	162
#### 4.4.5　带有时间戳的对象引用：AtomicStampedReference	165
#### 4.4.6　数组也能无锁：AtomicIntegerArray	168
#### 4.4.7　让普通变量也享受原子操作：AtomicIntegerFieldUpdater	169
#### 4.4.8　挑战无锁算法：无锁的Vector实现	171
#### 4.4.9　让线程之间互相帮助：细看SynchronousQueue的实现	176

### 4.5　有关死锁的问题	179
### 4.6　参考文献	183