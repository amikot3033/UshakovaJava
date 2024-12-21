package J6;

public interface DrugManagement {
    void addActiveIngredient(String name, double quantity);
    double calculateDosage(double weight, double age);
    void conductResearch(String researchDetails);
    void changeStatus(String status);
    String getActiveIngredientInfo();
    void updateActiveIngredientInfo(String name, double quantity);
}
