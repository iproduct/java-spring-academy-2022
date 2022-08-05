package course.stream.sam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class SearchTask implements Callable<String> {
    private String keywords;

    public SearchTask(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String call() throws Exception {
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            System.out.printf("Computation interrupted for '%d'%n", keywords);
        }
        return String.format("Serach results for '%s' ...%n", keywords);
    }
}

public class CallableDemo {
    public static void main(String[] args) {
        var executor = Executors.newCachedThreadPool();
        ExecutorCompletionService<String> cs = new ExecutorCompletionService<>(executor);
        List<Future<String>> results = new ArrayList<>();
        List<String> inputs = List.of("java", "jdk", "CompletableFuture", "multithreading", "lambdas", "Stream API");
        for(var input: inputs){
            results.add(cs.submit(() -> {
                try {
                    Thread.sleep(new Random().nextInt(10000));
                } catch (InterruptedException e) {
                    System.out.printf("Computation interrupted for '%d'%n", input);
                }
                return String.format("Serach results for '%s' ...%n", input);
            }));
        }
        for(int i = 0; i < results.size(); i++) {
            try {
                System.out.println(cs.take().get());
            } catch (InterruptedException | ExecutionException e) {
                System.out.printf("Getting the search results interrupted.%n");
            }
        }
        System.out.println("Demo finished.");
    }
}
