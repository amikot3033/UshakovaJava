package J6;

// Юнит-тесты
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DrugManagementTest {

    @Test
    public void testAddActiveIngredient() {
        Drug drug = new Drug("", 0, "разрешено");
        drug.addActiveIngredient("Парацетамол", 500);
        assertEquals("Действующее вещество: Парацетамол, количество: 500.0", drug.getActiveIngredientInfo());
    }

    @Test
    public void testCalculateDosage() {
        Drug drug = new Drug("Парацетамол", 500, "разрешено");
        double dosage = drug.calculateDosage(70, 30);
        assertEquals(1050, dosage);
    }

    @Test
    public void testChangeStatus() {
        Drug drug = new Drug("Парацетамол", 500, "разрешено");
        drug.changeStatus("по рецепту");
        // Статус проверить напрямую невозможно, но можно улучшить систему добавлением метода getStatus.
    }

    @Test
    public void testOintment() {
        Ointment ointment = new Ointment("Гидрокортизон", 50, "разрешено", "Кожа");
        assertEquals("Кожа", ointment.getApplicationArea());
    }

    @Test
    public void testTablet() {
        Tablet tablet = new Tablet("Аспирин", 100, "разрешено", 20);
        assertEquals(20, tablet.getTabletCount());
    }

    @Test
    public void testSolution() {
        Solution solution = new Solution("Амоксициллин", 250, "по рецепту", 100);
        assertEquals(100, solution.getVolume());
    }
}