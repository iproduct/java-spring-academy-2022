package course.java.mutithreading.simple;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class ComputeResult implements Callable<String> {
    private String keywords;
    private Random random = new Random();

    public ComputeResult(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String call() throws Exception {
        System.out.printf("Inside method call with keywords: %s [%s]%n", keywords,
                Thread.currentThread().getName());
        Thread.sleep(random.nextInt(3000));
        return String.format("Search result for keywords: %s%n", keywords);
    }
}

public class CallablesDemo {
    public static void main(String[] args) {
        // using thread pool executors
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<String>> futures = new ArrayList<>();
        CompletionService<String> cs = new ExecutorCompletionService(executor);
        for(int i = 0; i< 10; i++) {
            Future<String> f = cs.submit(new ComputeResult("keywords" + i));
            futures.add(f);
        }
        for(int i = 0; i< 10; i++) {
            try {
                String result = cs.take().get();
                System.out.println(result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }


//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        executor.shutdownNow();
    }
}
