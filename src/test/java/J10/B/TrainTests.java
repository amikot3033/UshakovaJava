package J10.B;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainTests {
    private Train train;
    private Locomotive locomotive;

    @BeforeEach
    void setUp() {
        locomotive = new Locomotive();
        train = new Train(locomotive);
        train.addCarriage(new Carriage(50, 100, "Economy"));
        train.addCarriage(new Carriage(30, 60, "Business"));
        train.addCarriage(new Carriage(70, 150, "Economy"));
        train.addCarriage(new Carriage(20, 50, "First Class"));
    }

    @Test
    void testTotalPassengerCapacity() {
        int totalCapacity = train.getTotalPassengerCapacity();
        assertEquals(170, totalCapacity, "Общая вместимость пассажиров должна быть 170.");
    }

    @Test
    void testTotalBaggageCapacity() {
        int totalBaggageCapacity = train.getTotalBaggageCapacity();
        assertEquals(360, totalBaggageCapacity, "Общая вместимость багажа должна быть 360.");
    }

    @Test
    void testSortCarriagesByComfortLevel() {
        train.sortCarriagesByComfortLevel();
        List<Carriage> sortedCarriages = train.findCarriagesByPassengerRange(0, Integer.MAX_VALUE);

        assertEquals("Business", sortedCarriages.get(0).getComfortLevel(), "Первый вагон после сортировки должен быть 'Business'.");
        assertEquals("Economy", sortedCarriages.get(1).getComfortLevel(), "Второй вагон после сортировки должен быть 'Economy'.");
        assertEquals("Economy", sortedCarriages.get(2).getComfortLevel(), "Третий вагон после сортировки должен быть 'Economy'.");
        assertEquals("First Class", sortedCarriages.get(3).getComfortLevel(), "Последний вагон после сортировки должен быть 'First Class'.");
    }

    @Test
    void testFindCarriagesByPassengerRange() {
        List<Carriage> filteredCarriages = train.findCarriagesByPassengerRange(30, 60);
        assertEquals(2, filteredCarriages.size(), "Должно быть 2 вагона с вместимостью от 30 до 60 пассажиров.");
        assertTrue(filteredCarriages.stream().allMatch(c -> c.getPassengerCapacity() >= 30 && c.getPassengerCapacity() <= 60),
                "Все найденные вагоны должны соответствовать диапазону пассажиров.");
    }

    @Test
    void testTrainSerialization() {
        TrainFileConnector connector = new TrainFileConnector();
        String fileName = "test_train.dat";

        // Сохранение поезда
        connector.saveTrainToFile(train, fileName);

        // Загрузка поезда
        Train loadedTrain = connector.loadTrainFromFile(fileName);

        assertNotNull(loadedTrain, "Загруженный поезд не должен быть null.");
        assertEquals(train.toString(), loadedTrain.toString(), "Загруженный поезд должен совпадать с сохраненным.");
    }
}
