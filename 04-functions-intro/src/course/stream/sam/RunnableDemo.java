package course.stream.sam;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

class RocketLiftOff implements Runnable {
    private int n;

    public RocketLiftOff(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        for (int i = 10; i > 0; i--) {
            System.out.printf("Rocket %d: Countdown: %d%n", n, i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.printf("Countdown interrupted for rocket %d%n", n);
            }
        }
        System.out.printf("Rocket %d: Liftoff!!!%n", n);
    }
}

public class RunnableDemo {
    public static BiConsumer<Integer, ExecutorService> rocketStart = (Integer n, ExecutorService executor) -> { // Closure
        executor.execute(() -> {
            for (int i = 10; i > 0; i--) {
                System.out.printf("Rocket %d: Countdown: %d%n", n, i); // accessed from closure
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.printf("Countdown interrupted for rocket %d%n", n);
                }
            }
            System.out.printf("Rocket %d: Liftoff!!!%n", n);
        });
    };

    public static void main(String[] args) {
        var executor = Executors.newFixedThreadPool(4);
        for (int k = 1; k <= 10; k++) {
            rocketStart.accept(k, executor);
        }
    }
}
