package course.java.mutithreading.synchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private String id;
    private long ballanceInCents;
    private Lock lock = new ReentrantLock();

    public Account() {
    }

    public Account(String id, long ballanceInCents) {
        this.id = id;
        this.ballanceInCents = ballanceInCents;
    }

    public long getBallanceInCents() {
        return ballanceInCents;
    }

    public String getId() {
        return id;
    }

    public void setBallanceInCents(long ballanceInCents) {
        this.ballanceInCents = ballanceInCents;
    }

    public Lock getLock() {
        return lock;
    }
}
