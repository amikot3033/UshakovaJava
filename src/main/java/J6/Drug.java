package J6;

class Drug implements DrugManagement {
    private String activeIngredient;
    private double quantity;
    private String status;

    public Drug(String activeIngredient, double quantity, String status) {
        this.activeIngredient = activeIngredient;
        this.quantity = quantity;
        this.status = status;
    }

    @Override
    public void addActiveIngredient(String name, double quantity) {
        this.activeIngredient = name;
        this.quantity = quantity;
    }

    @Override
    public double calculateDosage(double weight, double age) {
        return (weight * age * quantity) / 1000; // Пример формулы расчета
    }

    @Override
    public void conductResearch(String researchDetails) {
        System.out.println("Проведено исследование: " + researchDetails);
    }

    @Override
    public void changeStatus(String status) {
        this.status = status;
    }

    @Override
    public String getActiveIngredientInfo() {
        return "Действующее вещество: " + activeIngredient + ", количество: " + quantity;
    }

    @Override
    public void updateActiveIngredientInfo(String name, double quantity) {
        this.activeIngredient = name;
        this.quantity = quantity;
    }
}