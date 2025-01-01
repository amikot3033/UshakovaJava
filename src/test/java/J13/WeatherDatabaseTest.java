package J13;

import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WeatherDatabaseTest {
    private Connection connection;

    @BeforeAll
    void setupDatabase() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
        WeatherDatabase.createTables(connection);
    }

    @BeforeEach
    void insertTestData() throws SQLException {
        WeatherDatabase.clearTables(connection);
        WeatherDatabase.insertTestData(connection);
    }

    @AfterAll
    void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void testFindWeatherInRegion() throws SQLException {
        List<Weather> results = WeatherDatabase.findWeatherInRegion(connection, "Москва");
        assertEquals(2, results.size(), "Должно быть возвращено два результата для региона 'Москва'");

        assertEquals("2024-12-18", results.get(0).getDate().toString(), "Первая запись должна соответствовать дате '2024-12-18'");
        assertEquals(-5.0, results.get(0).getTemperature(), "Первая запись должна иметь температуру -5.0");
        assertEquals("Снег", results.get(0).getPrecipitation(), "Первая запись должна иметь осадки 'Снег'");

        assertEquals("2024-12-19", results.get(1).getDate().toString(), "Вторая запись должна соответствовать дате '2024-12-19'");
        assertEquals(2.0, results.get(1).getTemperature(), "Вторая запись должна иметь температуру 2.0");
        assertEquals("Дождь", results.get(1).getPrecipitation(), "Вторая запись должна иметь осадки 'Дождь'");
    }

    @Test
    void testFindSnowyDaysWithLowTemperature() throws SQLException {
        List<Weather> results = WeatherDatabase.findSnowyDaysWithLowTemperature(connection, "Москва", -5);
        assertEquals(1, results.size(), "Должен быть возвращен один снежный день с температурой ниже -5 для региона 'Москва'");

        assertEquals("2024-12-18", results.get(0).getDate().toString(), "Дата должна быть '2024-12-18'");
        assertEquals(-5.0, results.get(0).getTemperature(), "Температура должна быть -5.0");
        assertEquals("Снег", results.get(0).getPrecipitation(), "Осадки должны быть 'Снег'");
    }

    @Test
    void testFindWeatherForLanguage() throws SQLException {
        List<Weather> results = WeatherDatabase.findWeatherForLanguage(connection, "Немецкий");
        assertEquals(1, results.size(), "Должен быть возвращен один результат для регионов с языком 'Немецкий'");

        assertEquals("2024-12-18", results.get(0).getDate().toString(), "Дата должна быть '2024-12-18'");
        assertEquals(-10.0, results.get(0).getTemperature(), "Температура должна быть -10.0");
        assertEquals("Снег", results.get(0).getPrecipitation(), "Осадки должны быть 'Снег'");
    }

    @Test
    void testFindAllTemperaturesForLargeRegions() throws SQLException {
        List<Weather> results = WeatherDatabase.findAllTemperaturesForLargeRegions(connection, 1000);
        assertEquals(2, results.size(), "Должно быть возвращено два результата для регионов с площадью > 1000");
        assertEquals("2024-12-18", results.get(0).getDate().toString(), "Первая запись должна соответствовать дате '2024-12-18'");
        assertEquals("2024-12-19", results.get(1).getDate().toString(), "Вторая запись должна соответствовать дате '2024-12-19'");
    }

    @Test
    void testFindAverageTemperatureForLargeRegions() throws SQLException {
        List<String> results = WeatherDatabase.findAverageTemperatureForLargeRegions(connection, 1000);
        assertEquals(1, results.size(), "Должен быть возвращен один результат для регионов с площадью > 1000");
        assertTrue(results.get(0).contains("Регион: Москва"), "Результат должен содержать 'Регион: Москва'");
        assertTrue(results.get(0).contains("Средняя температура:"), "Результат должен содержать 'Средняя температура:'");
    }
}
