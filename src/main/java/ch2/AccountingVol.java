package ch2;

public class AccountingVol implements Runnable {
    static AccountingVol instance = new AccountingVol();
    static volatile int i = 0;

//    public static void increase() {
//        i++;
//    }
//
//
//    public void run() {
//        for (int j = 0; j < 10000000; j++) {
//            increase();
//        }
//    }

//
//    public void run() {
//        for (int j = 0; j < 10000000; j++) {
//            synchronized (instance){
//                i++;
//            }
//        }
//    }

    public synchronized static void increase() {
        i++;
    }

    public void run() {
        for (int j = 0; j < 10000000; j++) {
            increase();
        }
    }

    // synchronized锁的是对象，不是函数
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
