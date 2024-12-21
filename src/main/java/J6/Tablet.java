package J6;

class Tablet extends Drug {
    private int tabletCount;

    public Tablet(String activeIngredient, double quantity, String status, int tabletCount) {
        super(activeIngredient, quantity, status);
        this.tabletCount = tabletCount;
    }

    public int getTabletCount() {
        return tabletCount;
    }

    public void setTabletCount(int tabletCount) {
        this.tabletCount = tabletCount;
    }
}
