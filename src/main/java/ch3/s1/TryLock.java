package ch3.s1;

import java.util.concurrent.locks.ReentrantLock;

public class TryLock implements Runnable {
    // 这里书中没有提到，这儿使用static，所以创建两个TryLock对象才会共用lock1和lock2
    // 如果没有static会很快结束程序，因为使用的是自己的lock1和lock2不会互相锁住
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public TryLock(int lock) {
        this.lock = lock;
    }


    public void run() {
        if (lock == 1) {
            while (true) {
                if(lock1.tryLock()){
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (lock2.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getId() + " my job done");
                                return;
                            } finally {
                                lock2.unlock();
                            }
                        }
                    }finally {
                        lock1.unlock();
                    }
                }
            }
        } else {
            while (true) {
                if(lock2.tryLock()){
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (lock1.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getId() + " my job done");
                                return;
                            } finally {
                                lock1.unlock();
                            }
                        }
                    }finally {
                        lock2.unlock();
                    }
                }
            }
        }
    }

    public static void main(String [] args){
        TryLock lock1 = new TryLock(1);
        TryLock lock2 = new TryLock(2);
        Thread t1 = new Thread(lock1);
        Thread t2 = new Thread(lock2);
        t1.start();
        t2.start();
    }
}
