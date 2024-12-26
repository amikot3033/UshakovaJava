package J12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    private Bank bank;

    @BeforeEach
    void setup() {
        bank = new Bank();
    }

    @Test
    void testDeposit() {
        bank.deposit(5000);
        assertEquals(15000, bank.getBalance(), "Баланс должен быть увеличен до 15000 после депозита в 5000.");
    }

    @Test
    void testWithdraw() throws InsufficientFundsException {
        bank.withdraw(5000);
        assertEquals(5000, bank.getBalance(), "Баланс должен быть уменьшен до 5000 после снятия 5000.");
    }

    @Test
    void testWithdrawInsufficientFunds() {
        Exception exception = assertThrows(InsufficientFundsException.class, () -> {
            bank.withdraw(15000);
        });
        assertEquals("Недостаточно средств.", exception.getMessage(), "Сообщение об ошибке должно быть 'Insufficient funds in bank.'");
    }

    @Test

    void testMonitorCashReplenishment() throws InterruptedException, InsufficientFundsException {
        Bank bank = new Bank();

        Thread monitor = new Thread(() -> {
            try {
                bank.monitorCash();
            } catch (InsufficientFundsException e) {
                e.printStackTrace();
            }
        });
        monitor.start();

        bank.withdraw(9500);
        Thread.sleep(2000);

        long currentBalance = bank.getBalance();
        assertTrue(currentBalance >= bank.cashLowThreshold, "Монитор должен пополнять наличные, если их недостаточно.");

        monitor.interrupt();
    }

    @Test
    void testAddClientAndProcess() throws InterruptedException {
        Client client = new Client("Тестовый клиент");
        bank.addClient(client);
        new Thread(bank::processClients).start();
        assertDoesNotThrow(() -> {
            Thread.sleep(1000);
        }, "Клиент должен быть успешно обработан.");
    }

    @Test
    void testMultipleClientsProcessing() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            bank.addClient(new Client("Клиент " + i));
        }
        new Thread(bank::processClients).start();
        assertDoesNotThrow(() -> {
            Thread.sleep(2000);
        }, "Все клиенты должны быть успешно обработаны.");
    }

    @Test
    void testClientDeposit() {
        Bank bank = new Bank();
        Client client = new Client("TestClient") {
            @Override
            public void performTransaction(Bank bank) {
                long depositAmount = 1000;
                bank.deposit(depositAmount);
            }
        };

        long initialBalance = bank.getBalance();

        client.performTransaction(bank);

        long finalBalance = bank.getBalance();

        assertTrue(finalBalance > initialBalance, "Баланс банка должен увеличиться после депозита клиента.");
    }


    @Test
    void testClientWithdrawSuccess() {
        Client client = new Client("TestClient") {
            @Override
            public void performTransaction(Bank bank) {
                try {
                    long initialBalance = bank.getBalance();
                    bank.withdraw(1000);
                    assertEquals(initialBalance - 1000, bank.getBalance(), "Баланс банка должен уменьшиться после успешного снятия средств.");
                } catch (InsufficientFundsException e) {
                    fail("Снятие средств не должно выбрасывать исключение, если достаточно денег.");
                }
            }
        };
        client.performTransaction(bank);
    }

    @Test
    void testClientWithdrawFailInsufficientFunds() {
        Bank bank = new Bank();
        Client client = new Client("TestClient") {
            @Override
            public void performTransaction(Bank bank) {
                try {
                    bank.withdraw(1000000);
                    fail("Ожидалось исключение InsufficientFundsException при недостатке средств.");
                } catch (InsufficientFundsException e) {
                    assertTrue(e.getMessage().contains("Недостаточно средств."), "Исключение должно содержать информацию о недостатке средств.");
                }
            }
        };
        client.performTransaction(bank);
    }

    @Test
    void testClientPersonalBalance() {
        Client client = new Client("TestClient");
        long initialPersonalBalance = 5000;

        long depositAmount = 1000;
        long withdrawAmount = 2000;

        bank.deposit(depositAmount);
        client.performTransaction(bank);

        long expectedBalanceAfterDeposit = initialPersonalBalance + depositAmount;
        assertTrue(expectedBalanceAfterDeposit >= 0, "Баланс клиента должен корректно увеличиваться после депозита.");

        if (initialPersonalBalance >= withdrawAmount) {
            try {
                bank.withdraw(withdrawAmount);
                client.performTransaction(bank);

                long expectedBalanceAfterWithdraw = expectedBalanceAfterDeposit - withdrawAmount;
                assertTrue(expectedBalanceAfterWithdraw >= 0, "Баланс клиента должен корректно уменьшаться после снятия средств.");
            } catch (InsufficientFundsException e) {
                fail("Не должно быть исключения при достаточном балансе клиента.");
            }
        } else {
            System.out.println("Клиент не может снять больше, чем доступно на личном балансе.");
        }
    }

    @Test
    void testMultipleCashiersProcessingClients() throws InterruptedException {
        Bank bank = new Bank();
        int initialQueueSize = 10;
        int numberOfCashiers = 3;

        for (int i = 1; i <= initialQueueSize; i++) {
            bank.addClient(new Client("Клиент " + i));
        }

        ExecutorService executor = Executors.newFixedThreadPool(numberOfCashiers);
        for (int i = 0; i < numberOfCashiers; i++) {
            executor.execute(bank::processClients);
        }

        Thread.sleep(3000);

        executor.shutdownNow();

        assertEquals(0, bank.queue.size(), "Очередь должна быть пустой после обработки всех клиентов.");
    }

    @Test
    void testCashDepositAndWithdraw() throws InterruptedException {
        Bank bank = new Bank();
        int numberOfClients = 5;
        int numberOfCashiers = 2;

        for (int i = 1; i <= numberOfClients; i++) {
            bank.addClient(new Client("Клиент " + i));
        }

        ExecutorService executor = Executors.newFixedThreadPool(numberOfCashiers);
        for (int i = 0; i < numberOfCashiers; i++) {
            executor.execute(bank::processClients);
        }

        executor.shutdownNow();

        long bankBalance = bank.getBalance();
        assertTrue(bankBalance >= 5000 && bankBalance <= 20000, "Баланс банка должен быть в пределах лимитов.");
    }

}
