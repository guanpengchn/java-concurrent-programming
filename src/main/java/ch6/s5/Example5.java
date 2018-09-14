package ch6.s5;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Example5 {
    public static Integer calc(Integer para){
        return para/2;
    }

    public static void main(String []args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> fu = CompletableFuture.supplyAsync(()->calc(50))
                .thenCompose((i)->CompletableFuture.supplyAsync(()->calc(i)))
                .thenApply((str)->"\""+str+"\"")
                .thenAccept(System.out::println);
        fu.get();
    }
}
