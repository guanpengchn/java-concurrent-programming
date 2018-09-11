package ch4.s3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo_Gc {
    static volatile ThreadLocal<SimpleDateFormat> t1 =new ThreadLocal<SimpleDateFormat>(){

        protected void finalize() throws Throwable {
            System.out.println(this.toString() + " is gc");
        }
    };
    static volatile CountDownLatch cd =new CountDownLatch(10000);
    public static class ParseDate implements Runnable{
        int i=0;
        public ParseDate(int i){
            this.i = i;
        }

        public void run() {
            try{
                if(t1.get()==null){
                    t1.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"){

                        protected void finalize() throws Throwable {
                            System.out.println(this.toString() + " is gc");
                        }
                    });
                    System.out.println(Thread.currentThread().getId() + ":create SimpleDateFormat");
                }
                Date t =t1.get().parse("2015-03-29 19:29:"+i%60);
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                cd.countDown();
            }
        }
    }
    public static void main(String []args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for(int i=0;i<10000;i++){
            es.execute(new ParseDate(i));
        }
        cd.wait();
        System.out.println("mission complete!!");
        t1=null;
        System.gc();
        System.out.println("first GC complete!!");
        t1= new ThreadLocal<SimpleDateFormat>();
        cd =new CountDownLatch(10000);
        for(int i=0;i<10000;i++){
            es.execute(new ParseDate(i));
        }
        cd.wait();
        Thread.sleep(1000);
        System.gc();
        System.out.println("second GC complete!!");
    }
}
