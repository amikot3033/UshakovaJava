package J12;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        Thread cashier = new Thread(bank::processClients);
        cashier.start();

        Thread cashier2 = new Thread(bank::processClients);
        cashier2.start();

        Thread cashier3 = new Thread(bank::processClients);
        cashier3.start();

        Thread cashier4 = new Thread(bank::processClients);
        cashier4.start();

        Thread monitor = new Thread(() -> {
            try {
                bank.monitorCash();
            } catch (Exception e) {
                System.out.println("Ошибка в мониторе: " + e.getMessage());
            }
        });
        monitor.start();

        for (int i = 1; i <= 10; i++) {
            Client client = new Client("Клиент " + i);
            bank.addClient(client);
        }
    }
}
