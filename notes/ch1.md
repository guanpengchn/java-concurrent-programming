## 第1章　走入并行世界	1

> Give it up. The whole "parallel computing is the future" is a bunch of crock.

### 1.1　何去何从的并行计算 1

#### 1.1.1　忘掉那该死的并行	2

- BitKeeper真惨
- Git真好用
- 并行计算只有在图像处理和服务器编程2个领域可以使用

#### 1.1.2　可怕的现实：摩尔定律的失效	4

- CPU遇到了不可逾越的瓶颈

#### 1.1.3　柳暗花明：不断地前进	5

- CPU核心在不断变多

#### 1.1.4　光明或是黑暗	6

### 1.2　你必须知道的几个概念	6

#### 1.2.1　同步（Synchronous）和异步（Asynchronous）	7

- 见书中图

#### 1.2.2　并发（Concurrency）和并行（Parallelism）	8

- 见书中图

#### 1.2.3　临界区	9
#### 1.2.4　阻塞（Blocking）和非阻塞（Non-Blocking）	9
#### 1.2.5　死锁（Deadlock）、饥饿（Starvation）和活锁（Livelock）	9

### 1.3　并发级别	11

#### 1.3.1　阻塞（Blocking）	11
#### 1.3.2　无饥饿（Starvation-Free）	11
#### 1.3.3　无障碍（Obstruction-Free）	12
#### 1.3.4　无锁（Lock-Free）	12
#### 1.3.5　无等待（Wait-Free）	13

### 1.4　有关并行的两个重要定律	13

#### 1.4.1　Amdahl定律	13
#### 1.4.2　Gustafson定律	16
#### 1.4.3　Amdahl定律和Gustafson定律是否相互矛盾	16

### 1.5　回到Java：JMM	17

#### 1.5.1　原子性（Atomicity）	18
#### 1.5.2　可见性（Visibility）	20
#### 1.5.3　有序性（Ordering）	22
#### 1.5.4　哪些指令不能重排：Happen-Before规则	27

### 1.6　参考文献	27