package J10.B;

class Locomotive extends Transport {
    private static final long serialVersionUID = 1L;

    public Locomotive() {
        super(0, 0);
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