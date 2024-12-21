package J4.B;

abstract class Transport {
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