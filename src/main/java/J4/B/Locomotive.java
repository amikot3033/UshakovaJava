package J4.B;

class Locomotive extends Transport {
    public Locomotive() {
        super(0, 0); // Локомотив не перевозит пассажиров и багаж
    }

    @Override
    public String getComfortLevel() {
        return "N/A";
    }

    @Override
    public String toString() {
        return "Локомотив{}";
    }
}