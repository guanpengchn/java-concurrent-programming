package ch2;

public class PriorityDemo {
    public static class HighPriority extends Thread{
        static int count = 0;

        public void run() {
            while (true){
                synchronized (PriorityDemo.class){
                    count ++ ;
                    if(count > 10000000){
                        System.out.println("HighPriority finished");
                        break;
                    }
                }
            }
        }
    }

    public static class LowPriority extends Thread{
        static int count = 0;

        public void run() {
            while (true){
                synchronized (PriorityDemo.class){
                    count ++ ;
                    if(count > 10000000){
                        System.out.println("LowPriority finished");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String []args){
        Thread high = new HighPriority();
        Thread low = new LowPriority();
        high.setPriority(Thread.MAX_PRIORITY);
        low.setPriority(Thread.MIN_PRIORITY);
        high.start();
        low.start();
    }
}
