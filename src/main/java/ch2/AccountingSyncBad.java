package ch2;

public class AccountingSyncBad implements Runnable{

    static int i = 0;

//    public synchronized void increase() {
//        i++;
//    }

    // 加上static相当于请求类的锁，所以没问题
    public synchronized static void increase() {
        i++;
    }

    public void run() {
        for (int j = 0; j < 10000000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AccountingSyncBad());
        Thread t2 = new Thread(new AccountingSyncBad());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

}
