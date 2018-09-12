## 第4章　锁的优化及注意事项	138

### 4.1　有助于提高“锁”性能的几点建议	139

#### 4.1.1　减小锁持有时间	139

- JDK1.7源代码：[Pattern](https://github.com/guanpengchn/JDK/blob/master/JDK1.7/src/java/util/regex/Pattern.java)

#### 4.1.2　减小锁粒度	140

- 对整个HashMap加锁粒度过大，对于ConcurrentHashMap内部细分若干个HashMap，称之为段，被分成16个段
- 现根据hashcode得到应该存放到哪个段中，然后对该段加锁
- JDK8中改变了实现方式，使用CAS来做实现，区别可见[文章](https://blog.csdn.net/Gavin__Zhou/article/details/76792071)

#### 4.1.3　读写分离锁来替换独占锁	142

- [ReadWriteLock](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch3/s1/ReadWriteLockDemo.java)

#### 4.1.4　锁分离	142

- 在LinkedBlockingQueue中，take和put函数分别作用于队列的前端和尾端，不冲突，如果使用独占锁就无法并发，所以实现中使用了两把锁
- JDK1.7源代码：[LinkedBlockingQueue](https://github.com/guanpengchn/JDK/blob/master/JDK1.7/src/java/util/concurrent/LinkedBlockingQueue.java)

#### 4.1.5　锁粗化	144

- 虚拟机在遇到一连串的锁请求和释放，会优化合并成一次，叫做锁的粗化
- 在循环内使用锁往往可以考虑优化成在循环外

### 4.2　Java虚拟机对锁优化所做的努力	146

#### 4.2.1　锁偏向	146

- 如果一个线程获得了锁，就进入了偏向模式，之后该线程再去连续申请就无须做其他操作
- 但是如果不同线程来回切换，效果反而差，不如不开启锁偏向
- Java虚拟机参数：-XX:+IseBiasedLocking

#### 4.2.2　轻量级锁	146

- 偏向锁升级为轻量级锁，轻量级锁，升级为重量级锁
- 这里书中写的比较简略，可以看文章[java 中的锁 -- 偏向锁、轻量级锁、自旋锁、重量级锁](https://blog.csdn.net/zqz_zqz/article/details/70233767)

#### 4.2.3　自旋锁	146

- 虚拟机假设当前线程还可以获得锁，不马上挂起线程，让当前线程做几个空循环，如果能获得就进入临界区，如果不行则挂起

#### 4.2.4　锁消除	146

- 在编译过程中，去掉不可能共享资源的锁，比如局部变量
- 锁消除的一项关键技术叫做逃逸分析
- 逃逸分析必须在-server模式下，虚拟机参数：-XX:+DoEscapeAnalysis打开逃逸分析，-XX:+EliminateLocks打开锁消除

### 4.3　人手一支笔：ThreadLocal	147

#### 4.3.1　ThreadLocal的简单使用	148

- 这个demo测试没有ThreadLocal，仅仅是对象在run内部new出来也行呀，不是很懂
- [ThreadLocalDemo](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch4/s3/ThreadLocalDemo.java)

#### 4.3.2　ThreadLocal的实现原理	149

- ThreadLocalMap我在jdk1.8下运行不出效果来，作者在书中也提到了，二者实现方式不同
- [ThreadLocalDemo_Gc](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch4/s3/ThreadLocalDemo_Gc.java)
- WeakHashMap和HashMap的区别可以见该[文章](http://mzlly999.iteye.com/blog/1126049)和代码[WeakVsHashMap](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch4/s3/WeakVsHashMap.java)
- 理解弱引用和强引用概念，可参考[Java 7之基础 - 强引用、弱引用、软引用、虚引用](https://blog.csdn.net/mazhimazh/article/details/19752475)

#### 4.3.3　对性能有何帮助	155

- 见下面demo可得ThreadLocal的效率还是很高的
- [ThreadLocalPerformance](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch4/s3/ThreadLocalPerformance.java)

### 4.4　无锁	157

- 无锁策略使用一种叫做比较交换的技术（CAS Compare And Swap）

#### 4.4.1　与众不同的并发策略：比较交换（CAS）	158

- 天生免疫死锁，没有锁竞争和线程切换的开销
- CAS(V,E,N)，V表示要更新的变量，E表示预期值，N表示新值，当V=E时，才会将V值设为N，如果不相等则该线程被告知失败，可以再次尝试
- 硬件层面现代处理器已经支持原子化的CAS指令

#### 4.4.2　无锁的线程安全整数：AtomicInteger	159

- atomic包中实现了直接使用CAS的线程安全类型
- [AtomicIntegerDemo](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch4/s4/AtomicIntegerDemo.java)

#### 4.4.3　Java中的指针：Unsafe类	161

- native方法是不用java实现的
- [compareAndSet](https://github.com/guanpengchn/JDK/blob/master/JDK1.7/src/java/util/concurrent/atomic/AtomicInteger.java#L134-L136)
- Unsafe类在rt.jar中，jdk无法找到
- JDK开发人员并不希望大家使用Unsafe类，下面的代码，会检查调用getUnsafe函数的类，如果这个类的ClassLoader不为空，直接抛出异常拒绝工作，这使得自己的程序无法直接调用Unsafe类

```java
public static Unsafe getUnsafe() {
    Class cc = Reflection.getCallerClass();
    if (cc.getClassLoader() != null)
        throw new SecurityException("Unsafe");
    return theUnsafe;
}
```

- 注意：根据Java类加载器的原理，应用程序的类由App Loader加载，而系统核心的类，如rt.jar中的由Bootstrap类加载器加载。Bootstrap加载器没有java对象的对象，因此试图获得该加载器会返回null，所以当一个类的类加载器为null时，说明是由Bootstrap加载的，这个类也极可能是rt.jar中的类

#### 4.4.4　无锁的对象引用：AtomicReference	162

- AtomicInteger是对整数的封装，AtomicReference是对对象的封装
- 运行下面demo可以见到错误，进行了多次充值，原因是状态可能不同，但是值却可能相同，所以不应该用值来判断状态
- [AtomicReferenceDemo](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch4/s4/AtomicReferenceDemo.java)

#### 4.4.5　带有时间戳的对象引用：AtomicStampedReference	165

- 对象值和时间戳必须都一样才能修改成功，所以只会充值一次
- [AtomicStampedReferenceDemo](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch4/s4/AtomicStampedReferenceDemo.java)

#### 4.4.6　数组也能无锁：AtomicIntegerArray	168

- [AtomicIntegerArrayDemo](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch4/s4/AtomicIntegerArrayDemo.java)

#### 4.4.7　让普通变量也享受原子操作：AtomicIntegerFieldUpdater	169

- 可以包装普通变量，让其也具有原子操作
- [AtomicIntegerFieldUpdaterDemo](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch4/s4/AtomicIntegerFieldUpdaterDemo.java)
- 变量必须可见，不能为private
- 为确保变量被正确读取，需要有volatile
- CAS通过偏移量赋值，不支持static（Unsafe, objectFieldOffset不支持静态变量）

#### 4.4.8　挑战无锁算法：无锁的Vector实现	171

- N_BUCKET为30，相当于有30个数组，第一个数组大小FIRST_BUCKET_SIZE为8，但是Vector之后会不断翻倍，第二个数组就是16个，最终能2^33左右
- 这段比较复杂，多看

#### 4.4.9　让线程之间互相帮助：细看SynchronousQueue的实现	176

### 4.5　有关死锁的问题	179

- [DeadLock](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch4/s5/DeadLock.java)

### 4.6　参考文献	183