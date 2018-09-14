package ch6.s5;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Example1 {
    public static class AskThread implements Runnable{
        CompletableFuture<Integer> re=null;

        public AskThread(CompletableFuture<Integer> re) {
            this.re = re;
        }

        @Override
        public void run() {
            int myRe = 0;
            try{
                myRe = re.get() * re.get(); // 这里会阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(myRe);
        }
    }

    public static void main(String []args) throws InterruptedException {
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread(new AskThread(future)).start();
        Thread.sleep(1000);
        future.complete(60);
    }
}
