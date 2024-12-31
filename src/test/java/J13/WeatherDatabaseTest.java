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
        assertEquals(2, results.size(), "Should return two records for 'Москва'");

        assertEquals("2024-12-18", results.get(0).getDate().toString(), "First record should match the date '2024-12-18'");
        assertEquals(-5.0, results.get(0).getTemperature(), "First record should have a temperature of -5.0");
        assertEquals("Снег", results.get(0).getPrecipitation(), "First record should have precipitation 'Снег'");

        assertEquals("2024-12-19", results.get(1).getDate().toString(), "Second record should match the date '2024-12-19'");
        assertEquals(2.0, results.get(1).getTemperature(), "Second record should have a temperature of 2.0");
        assertEquals("Дождь", results.get(1).getPrecipitation(), "Second record should have precipitation 'Дождь'");
    }

    @Test
    void testFindSnowyDaysWithLowTemperature() throws SQLException {
        List<Weather> results = WeatherDatabase.findSnowyDaysWithLowTemperature(connection, "Москва", -5);
        assertEquals(1, results.size(), "Should return one snowy day below -5 for 'Москва'");

        assertEquals("2024-12-18", results.get(0).getDate().toString(), "Date should be '2024-12-18'");
        assertEquals(-5.0, results.get(0).getTemperature(), "Temperature should be -5.0");
        assertEquals("Снег", results.get(0).getPrecipitation(), "Precipitation should be 'Снег'");
    }

    @Test
    void testFindWeatherForLanguage() throws SQLException {
        List<Weather> results = WeatherDatabase.findWeatherForLanguage(connection, "Немецкий");
        assertEquals(1, results.size(), "Should return one record for regions with 'Немецкий' language");

        assertEquals("2024-12-18", results.get(0).getDate().toString(), "Date should be '2024-12-18'");
        assertEquals(-10.0, results.get(0).getTemperature(), "Temperature should be -10.0");
        assertEquals("Снег", results.get(0).getPrecipitation(), "Precipitation should be 'Снег'");
    }

    @Test
    void testFindAllTemperaturesForLargeRegions() throws SQLException {
        List<Weather> results = WeatherDatabase.findAllTemperaturesForLargeRegions(connection, 1000);
        assertEquals(2, results.size(), "Should return two records for regions with area > 1000");
        assertEquals("2024-12-18", results.get(0).getDate().toString(), "First record should match the date '2024-12-18'");
        assertEquals("2024-12-19", results.get(1).getDate().toString(), "Second record should match the date '2024-12-19'");
    }

    @Test
    void testFindAverageTemperatureForLargeRegions() throws SQLException {
        List<String> results = WeatherDatabase.findAverageTemperatureForLargeRegions(connection, 1000);
        assertEquals(1, results.size(), "Should return one record for regions with area > 1000");
        assertTrue(results.get(0).contains("Регион: Москва"));
        assertTrue(results.get(0).contains("Средняя температура:"));
    }
}
