## 第6章　Java 8与并发	251

### 6.1　Java 8的函数式编程简介	251

#### 6.1.1　函数作为一等公民	252

- 函数可以作为另外一个函数的返回值

#### 6.1.2　无副作用	252

- 函数的副作用指的是函数在调用过程中，除了给出了返回值外，还修改了函数外部的状态
- 函数的副作用应该被尽量避免
- 显示函数指函数与外界交换数据的唯一渠道就是参数和返回值，显示函数不会去读取或者修改函数的外部状态

#### 6.1.3　申明式的（Declarative）	253

- 函数式编程是申明式的编程方式
- [Example1](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch6/s1/Example1.java)

#### 6.1.4　不变的对象	254

- [Example2](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch6/s1/Example2.java)

#### 6.1.5　易于并行	254

- 由于对象都处于不变的状态，因此函数式编程更加易于并行，这样没有同步和锁机制，性能也会比较好

#### 6.1.6　更少的代码	254

- [Example3](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch6/s1/Example3.java)

### 6.2　函数式编程基础	255

#### 6.2.1　FunctionalInterface注释	255

- 函数式接口是只定义单一抽象方法的接口
- 只能有一个抽象方法，而不是只能有一个方法，任何被java.lang.Object实现的方法都不能视为抽象方法

#### 6.2.2　接口默认方法	256

- Java8之前的版本，接口只能包含抽象方法，但从Java8之后，接口也可以包含若干个实例方法
- default关键字可以在接口内定义实例方法，这个方法并非抽象方法，而是拥有特定逻辑的具体实例方法
- Comparator接口新增了若干个默认方法

#### 6.2.3　lambda表达式	259

- 外部的num在lambda表达式中必须申明为final，如果不申明Java8也会默认处理成final
- [Example1](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch6/s2/Example1.java)

#### 6.2.4　方法引用	260

|类型|使用方法|
|:---:|:---:|
|静态方法引用|ClassName::methodName|
|实例上的实例方法引用|instanceReference::methodName|
|超类上的实例方法引用|super::methodName|
|类型上的实例方法引用|ClassName::methodName|
|构造方法引用|Class::new|
|数组构造方法引用|TypeName[]::new|

- [InstanceMethodRef](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch6/s2/InstanceMethodRef.java)
- [BadMethodRef](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch6/s2/BadMethodRef.java)
- [BadMethodRef](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch6/s2/BadMethodRef.java)

### 6.3　一步一步走入函数式编程	263

- forEach原始实现

```java
static int[] arr = {1,2,3,4,5,6,7,8,9,10};

public static void main(String[] args){
    Arrays.stream(arr).forEach(new IntConsumer(){
        @Override
        public void accept(int value){
            System.out.println(value);
        }
    });
}
```

- 由于forEach()函数的参数是可以从上下文推导出来的，所以可以改写如下

```java
static int[] arr = {1,2,3,4,5,6,7,8,9,10};

public static void main(String[] args){
    Arrays.stream(arr).forEach((final int x)->{
        System.out.println(x);
    });
}
```

- 参数类型也可以推导，进而简化如下

```java
static int[] arr = {1,2,3,4,5,6,7,8,9,10};

public static void main(String[] args){
    Arrays.stream(arr).forEach((x)->{
        System.out.println(x);
    });
}
```

- 放到一行

```java
static int[] arr = {1,2,3,4,5,6,7,8,9,10};

public static void main(String[] args){
    Arrays.stream(arr).forEach((x)->System.out.println(x));
}
```

- lambda表达式可以使用更流畅的流式API对组件进行自由的装配

```java
static int[] arr={1,3,4,5,6,7,8,9,10};

public static void main(String[] args){
    IntConsumer outprintln=System.out::println;
    IntConsumer errprintln=System.err::println;
    Arrays.stream(arr).forEach(outprintln.andThen(errprintln));
}
```

### 6.4　并行流与并行排序	267

#### 6.4.1　使用并行流过滤数据	267

- [PrimeUtil](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch6/s4/PrimeUtil.java)

#### 6.4.2　从集合得到并行流	268

```java
List<Student> ss =new ArrayList<Student>();
double ave=ss.stream().mapToInt(s->s.score).average().getAsDouble();

double ave=ss.parallelStream().mapToInt(s->s.score).average().getAsDouble();
```

#### 6.4.3　并行排序	268

- 并行排序

```java
int []arr=new int[10000000];
Arrays.parallelSort(arr);
```

- 并行随机值

```java
Random r =new Random();
Arrays.setAll(arr,(i)->r.nextInt());

Arrays.parallelSetAll(arr,(i)->r.nextInt());
```

### 6.5　增强的Future：CompletableFuture	269

- 为函数式编程中的流式调用准备

#### 6.5.1　完成了就通知我	269

- [Example1](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch6/s5/Example1.java)

#### 6.5.2　异步执行任务	270

- [Example2](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch6/s5/Example2.java)

#### 6.5.3　流式调用	272

- [Example3](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch6/s5/Example3.java)

#### 6.5.4　CompletableFuture中的异常处理	272

- [Example4](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch6/s5/Example4.java)

#### 6.5.5　组合多个CompletableFuture	273

- [Example5](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch6/s5/Example5.java)
- [Example6](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch6/s5/Example6.java)

### 6.6　读写锁的改进：StampedLock	274

#### 6.6.1　StampedLock使用示例	275

- [Point](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch6/s6/Point.java)

#### 6.6.2　StampedLock的小陷阱	276

- [StampedLockCPUDemo](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch6/s6/StampedLockCPUDemo.java)

#### 6.6.3　有关StampedLock的实现思想	278

- 内部实现基于CLH锁，CLH是一种自旋锁，保证没有饥饿发生并且保证FIFO的服务顺序

### 6.7　原子类的增强	281

#### 6.7.1　更快的原子类：LongAdder	281

- 热点分离，类似于ConcurrentHashMap
- 性能更好，好于AtomicInteger，而且使用了@sun.misc.Contended来避免伪共享

#### 6.7.2　LongAdder的功能增强版：LongAccumulator	287

- [LongAccumulatorDemo](https://github.com/guanpengchn/java-concurrent-programming/blob/master/src/main/java/ch6/s7/LongAccumulatorDemo.java)

### 6.8　参考文献	288