package J12;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

class Bank {
    private final BlockingQueue<Client> queue = new LinkedBlockingQueue<>();
    private final AtomicLong cashInBank = new AtomicLong(10000);
    private final AtomicLong storage = new AtomicLong(50000);
    final int cashLimit = 20000;
    final int cashLowThreshold = 5000;

    public void addClient(Client client) {
        queue.offer(client);
    }

    public void processClients() {
        while (true) {
            try {
                Client client = queue.take();
                client.performTransaction(this);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void monitorCash() throws InsufficientFundsException {
        while (true) {
            try {
                Thread.sleep(1000);
                long currentCash = cashInBank.get();

                if (currentCash > cashLimit) {
                    long excess = currentCash - cashLimit;
                    cashInBank.addAndGet(-excess);
                    storage.addAndGet(excess);
                    System.out.println("Переведено " + excess + " в хранилище.");
                } else if (currentCash < cashLowThreshold) {
                    long replenishment = Math.min(storage.get(), cashLimit - currentCash);
                    storage.addAndGet(-replenishment);
                    cashInBank.addAndGet(replenishment);
                    System.out.println("Пополнено наличных на сумму " + replenishment + " из хранилища.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void withdraw(long amount) throws InsufficientFundsException {
        if (cashInBank.get() < amount) {
            throw new InsufficientFundsException("Недостаточно средств.");
        }
        cashInBank.addAndGet(-amount);
    }



    public void deposit(long amount) {
        cashInBank.addAndGet(amount);
    }

    public long getBalance() {
        return cashInBank.get();
    }
}




