package course.java.mutithreading.synchronization;

import course.java.util.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ClientLocks implements Runnable {
    private long id;
    private Bank bank;
    private Account from, to;
    private Random rand = new Random();
    private volatile boolean canceled;

    public ClientLocks(long id, Bank bank, Account from, Account to) {
        this.id = id;
        this.bank = bank;
        this.from = from;
        this.to = to;
    }

    public void cancel() {
        canceled = true;
    }

    @Override
    public void run() {
        try {
            while (!canceled) {
                bank.transferAmountInCents(rand.nextInt(100) + 1L, from, to);
                Thread.sleep(rand.nextInt(100));
            }
        } catch (InterruptedException e) {
            System.out.printf("Client No. %d was interrupted.", id);
        }
        System.out.printf("!!!!!!!!!!!!!!! Client No. %d was finished.", id);
    }
}

public class BankLocks {
    public static final long ACCOUNT_A_AMOUNT = 100000;
    public static final long ACCOUNT_B_AMOUNT = 300000;
    private AccountLocks accountA = new AccountLocks("A", ACCOUNT_A_AMOUNT);
    private AccountLocks accountB = new AccountLocks("B", ACCOUNT_B_AMOUNT);

    public AccountLocks getAccountA() {
        return accountA;
    }

    public AccountLocks getAccountB() {
        return accountB;
    }

    public boolean transferAmountInCents(long amount, AccountLocks from, AccountLocks to) {
        if (from.getId().compareTo(to.getId()) < 0) {
            from.getLock().lock();
            try {
                if (from.getBallanceInCents() < amount) {
                    System.out.printf("Insufficient ballance in account %s : %d cents%n",
                            from.getId(), from.getBallanceInCents());
                    return false; // not enough money
                }
                to.getLock().lock();
                try {
                    from.setBallanceInCents(from.getBallanceInCents() - amount);
                    to.setBallanceInCents(to.getBallanceInCents() + amount);
                } finally {
                    to.getLock().unlock();
                }
            } finally {
                from.getLock().unlock();
            }
        } else {
            to.getLock().lock();
            try {
                from.getLock().lock();
                try {
                    if (from.getBallanceInCents() < amount) {
                        System.out.printf("Insufficient ballance in account %s : %d cents%n",
                                from.getId(), from.getBallanceInCents());
                        return false; // not enough money
                    }
                    from.setBallanceInCents(from.getBallanceInCents() - amount);
                    to.setBallanceInCents(to.getBallanceInCents() + amount);
                } finally {
                    from.getLock().unlock();
                }
            } finally {
                to.getLock().unlock();
            }
        }

        System.out.printf("Transfer from %s to %s: %8.2f Euro%n",
                from.getId(), to.getId(), amount / 100.0);
        return true;
    }

    public Tuple<Long, Long> checkAmountsTotal() {
        synchronized (accountA) {
            synchronized (accountB) {
                return new Tuple(
                        accountA.getBallanceInCents() + accountB.getBallanceInCents(),
                        ACCOUNT_A_AMOUNT + ACCOUNT_B_AMOUNT);
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        var bank = new Bank();
        var executor = Executors.newCachedThreadPool();
        List<Tuple<ClientLocks, Future<?>>> clientData = new ArrayList<>();
        for (long i = 0; i < 5; i++) {
            var clientTask = new ClientLocks(i, bank, bank.getAccountA(), bank.getAccountB());
            var future = executor.submit(clientTask);
            clientData.add(new Tuple<>(clientTask, future));
        }
        for (long i = 10; i < 15; i++) {
            var clientTask = new ClientLocks(i, bank, bank.getAccountB(), bank.getAccountA());
            var future = executor.submit(clientTask);
            clientData.add(new Tuple<>(clientTask, future));
        }
        var checkerFuture = executor.submit(() -> {
            try {
                while (!Thread.interrupted()) {
                    var totals = bank.checkAmountsTotal();
                    if (!totals.getV1().equals(totals.getV2())) {
                        System.out.printf("PANIC: Constant money constraint violated: should be: %d cents, actual %d cents%n",
                                totals.getV2(), totals.getV1());
                    }

                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println("Checker thread is interrupted - finishing ...");
            }
            System.out.println("Checker thread is finishing ...");
        });
        Thread.sleep(10000);
        for (int i = 0; i < clientData.size(); i++) {
            clientData.get(i).getV1().cancel();
            clientData.get(i).getV2().cancel(true);
            System.out.printf("Client %s canceled.", clientData.get(i).getV1());
        }
        checkerFuture.cancel(true);
        executor.shutdown();
    }
}