package J6;

class Ointment extends Drug {
    private String applicationArea;

    public Ointment(String activeIngredient, double quantity, String status, String applicationArea) {
        super(activeIngredient, quantity, status);
        this.applicationArea = applicationArea;
    }

    public String getApplicationArea() {
        return applicationArea;
    }

    public void setApplicationArea(String applicationArea) {
        this.applicationArea = applicationArea;
    }
}

