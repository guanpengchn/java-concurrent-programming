## 第7章　使用Akka构建高并发程序	289

- Akka提供了一种称为Actor的并发模型，粒度比线程更小
- 其次Akka中提供了一套容错机制，允许在Actor出现异常时进行一些恢复或者重置操作
- Akka不仅可以在单机上构建高并发程序，也可以在网络中构件分布式程序

### 7.1　新并发模型：Actor	290

- 完全忘记线程，使用Actor

### 7.2　Akka之Hello World	290

- [代码](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch7/s2)
- 多个不同的Actor有可能会被同一个县城执行，一个Actor也有可能被不同县城执行
- 不要在一个Actor中执行耗时的代码

### 7.3　有关消息投递的一些说明	293

- 消息满足不可变性
- [ImmutableMessage](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch7/s3/ImmutableMessage.java)
- 消息投递3种策略：
    - 至多一次投递，每条消息最多会被投递一次，偶尔丢失消息
    - 至少一次投递，每条消息至少会被投递一次，直到成功为止，会收到重复消息但不会丢失消息
    - 精确的消息投递，所有消息保证被精确地投递并成功接收一次，既不丢失，也不重复
- Akka一定程度保证顺序性，消息投递不具备可传递性

### 7.4　Actor的生命周期	295

- [代码](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch7/s4)

### 7.5　监督策略	298

- OneForOneStrategy，默认策略
- AllForOneStrategy

### 7.6　选择Actor	303
### 7.7　消息收件箱（Inbox）	303
### 7.8　消息路由	305
### 7.9　Actor的内置状态转换	308
### 7.10　询问模式：Actor中的Future	311
### 7.11　多个Actor同时修改数据：Agent	313
### 7.12　像数据库一样操作内存数据：软件事务内存	316
### 7.13　一个有趣的例子：并发粒子群的实现	319

#### 7.13.1　什么是粒子群算法	320
#### 7.13.2　粒子群算法的计算过程	320
#### 7.13.3　粒子群算法能做什么	322
#### 7.13.4　使用Akka实现粒子群	323

### 7.14　参考文献	330