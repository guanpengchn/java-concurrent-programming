package ch6.s5;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Example3 {
    public static Integer calc(Integer para){
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return para * para;
    }

    public static void main(String []args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> fu=CompletableFuture.supplyAsync(()->calc(50))
                .thenApply((i)->Integer.toString(i))
                .thenApply((str)->"\""+str+"\"")
                .thenAccept(System.out::println);
        fu.get();
    }
}
