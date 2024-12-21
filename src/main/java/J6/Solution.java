package J6;

class Solution extends Drug {
    private double volume;

    public Solution(String activeIngredient, double quantity, String status, double volume) {
        super(activeIngredient, quantity, status);
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}