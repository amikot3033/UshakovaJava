package J10.B;

import java.io.Serializable;

abstract class Transport implements Serializable {
    private static final long serialVersionUID = 1L;

    private int passengerCapacity;
    private int baggageCapacity;

    public Transport(int passengerCapacity, int baggageCapacity) {
        this.passengerCapacity = passengerCapacity;
        this.baggageCapacity = baggageCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public int getBaggageCapacity() {
        return baggageCapacity;
    }

    public abstract String getComfortLevel();
}
