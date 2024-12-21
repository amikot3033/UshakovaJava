package J4.B;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Train {
    private Locomotive locomotive;
    private List<Carriage> carriages;

    public Train(Locomotive locomotive) {
        this.locomotive = locomotive;
        this.carriages = new ArrayList<>();
    }

    public void addCarriage(Carriage carriage) {
        carriages.add(carriage);
    }

    public int getTotalPassengerCapacity() {
        return carriages.stream().mapToInt(Carriage::getPassengerCapacity).sum();
    }

    public int getTotalBaggageCapacity() {
        return carriages.stream().mapToInt(Carriage::getBaggageCapacity).sum();
    }

    public void sortCarriagesByComfortLevel() {
        carriages.sort(Comparator.comparing(Carriage::getComfortLevel));
    }

    public List<Carriage> findCarriagesByPassengerRange(int minPassengers, int maxPassengers) {
        return carriages.stream()
                .filter(c -> c.getPassengerCapacity() >= minPassengers && c.getPassengerCapacity() <= maxPassengers)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Поезд: \n").append(locomotive).append("\n");
        for (Carriage carriage : carriages) {
            builder.append(carriage).append("\n");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Locomotive locomotive = new Locomotive();
        Train train = new Train(locomotive);

        train.addCarriage(new Carriage(50, 100, "Economy"));
        train.addCarriage(new Carriage(30, 60, "Business"));
        train.addCarriage(new Carriage(70, 150, "Economy"));
        train.addCarriage(new Carriage(20, 50, "First Class"));

        System.out.println("Первоначальный состав поезда:");
        System.out.println(train);

        System.out.println("\nВсего пассажиров: " + train.getTotalPassengerCapacity());
        System.out.println("Всего багажа: " + train.getTotalBaggageCapacity());

        train.sortCarriagesByComfortLevel();
        System.out.println("\nСостав поезда после сортировки по комфортности:");
        System.out.println(train);

        System.out.println("\nВагоны вместимостью от 30 до 60 пассажиров:");
        List<Carriage> filteredCarriages = train.findCarriagesByPassengerRange(30, 60);
        filteredCarriages.forEach(System.out::println);
    }
}