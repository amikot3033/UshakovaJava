package J12;


import java.util.Random;

class Client implements Runnable {
    private final String name;
    private final Random random = new Random();
    private long personalBalance = 5000;

    public Client(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " в очереди.");
    }

    public void performTransaction(Bank bank) {
        int action = random.nextInt(2);
        long amount = 100 + random.nextInt(900);

        try {
            switch (action) {
                case 0 -> {
                    bank.deposit(amount);
                    personalBalance += amount;
                    System.out.println(name + " пополнил " + amount + ". Баланс клиента: " + personalBalance);
                }
                case 1 -> {
                    if (personalBalance >= amount) {
                        bank.withdraw(amount);
                        personalBalance -= amount;
                        System.out.println(name + " снял " + amount + ". Баланс клиента: " + personalBalance);
                    } else {
                        System.out.println(name + " попытался снять " + amount + ", но недостаточно средств. Баланс клиента: " + personalBalance);
                    }
                }
            }
        } catch (InsufficientFundsException e) {
            System.out.println(name + ": Ошибка транзакции. " + e.getMessage());
        }
    }
}