package ch6.s5;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Example6 {
    public static Integer calc(Integer para){
        return para/2;
    }

    public static void main(String []args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> intFuture = CompletableFuture.supplyAsync(()->calc(50));
        CompletableFuture<Integer> intFuture2 = CompletableFuture.supplyAsync(()->calc(25));
        CompletableFuture<Void> fu = intFuture.thenCombine(intFuture2,(i,j)->(i+j))
                .thenApply((str)->"\""+str+"\"")
                .thenAccept(System.out::println);
        fu.get();
    }
}
