package course.java.mutithreading.synchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountLocks {
    private String id;
    private long ballanceInCents;
    private Lock lock = new ReentrantLock();

    public AccountLocks() {
    }

    public AccountLocks(String id, long ballanceInCents) {
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
