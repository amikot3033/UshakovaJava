package J10.B;

class Carriage extends Transport {
    private static final long serialVersionUID = 1L;
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
                "Вместимость пассажиров=" + getPassengerCapacity() +
                ", Вместимость багажа=" + getBaggageCapacity() +
                ", Уровень комфорта='" + comfortLevel + '\'' +
                '}';
    }
}