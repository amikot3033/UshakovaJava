package J10.B;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Train implements Serializable {
    private static final long serialVersionUID = 1L;

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
}