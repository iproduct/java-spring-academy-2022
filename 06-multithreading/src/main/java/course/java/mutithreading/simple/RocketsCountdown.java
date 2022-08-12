package course.java.mutithreading.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class RocketCountdown implements Runnable {
    private int rocketNumber;

    public RocketCountdown(int rocketNumber) {
        this.rocketNumber = rocketNumber;
    }

    @Override
    public void run() {
        try {
            for (int i = 10; i > 0; i--) {
                System.out.printf("Rocket %d: Countdown: %d [%s]%n", rocketNumber, i,
                        Thread.currentThread().getName());
                Thread.sleep(1000);
            }
            System.out.printf("Rocket %d: Liftoff! [%s]%n", rocketNumber,
                    Thread.currentThread().getName());
        } catch(InterruptedException ex) {
            System.out.println("The thread was interrupted - finishing.");
        }
    }
}

public class RocketsCountdown {
    public static void main(String[] args) {
        // using threads
//        for(int i = 0; i< 6; i++) {
//            new Thread(new RocketCountdown(i)).start();
//        }

        // using thread pool executors
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);
        for(int i = 0; i< 1; i++) {
            executor.scheduleWithFixedDelay(new RocketCountdown(i), 2000, 1000, TimeUnit.MILLISECONDS);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executor.shutdownNow();
    }
}
