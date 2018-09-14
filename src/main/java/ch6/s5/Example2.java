package ch6.s5;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Example2 {
    public static Integer calc(Integer para){
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return para * para;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->calc(50));
        System.out.println(future.get());
    }
}
