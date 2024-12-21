package J4.B;

class Carriage extends Transport {
    private String comfortLevel;

    public Carriage(int passengerCapacity, int baggageCapacity, String comfortLevel) {
        super(passengerCapacity, baggageCapacity);
        this.comfortLevel = comfortLevel;
    }

    @Override
    public String getComfortLevel() {
        return comfortLevel;
    }

    @Override
    public String toString() {
        return "Вагон{" +
                "Вместимость ассажиров=" + getPassengerCapacity() +
                ", вместимость багажа=" + getBaggageCapacity() +
                ", уровень комфорта='" + getComfortLevel() + '\'' +
                '}';
    }
}