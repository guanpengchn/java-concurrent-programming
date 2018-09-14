package ch6.s5;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Example4 {
    public static Integer calc(Integer para){
        return para / 0;
    }

    public static void main(String []args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> fu = CompletableFuture
                .supplyAsync(()->calc(50))
                .exceptionally(ex -> {
                    System.out.println(ex.toString());
                    return 0;
                })
                .thenApply((i)->Integer.toString(i))
                .thenApply((str)->"\""+str+"\"")
                .thenAccept(System.out::println);
        fu.get();
    }
}
