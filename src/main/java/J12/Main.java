package J12;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        Thread cashier = new Thread(bank::processClients);
        cashier.start();

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
