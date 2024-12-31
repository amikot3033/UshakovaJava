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
        List<String> results = WeatherDatabase.findWeatherInRegion(connection, "Москва");
        assertEquals(2, results.size(), "Should return two records for 'Москва'");
        assertTrue(results.get(0).contains("Дата: 2024-12-18"));
        assertTrue(results.get(1).contains("Дата: 2024-12-19"));
    }

    @Test
    void testFindSnowyDaysWithLowTemperature() throws SQLException {
        List<String> results = WeatherDatabase.findSnowyDaysWithLowTemperature(connection, "Москва", -5);
        assertEquals(1, results.size(), "Should return one snowy day below -5 for 'Москва'");
        assertTrue(results.get(0).contains("2024-12-18"));
    }

    @Test
    void testFindWeatherForLanguage() throws SQLException {
        List<String> results = WeatherDatabase.findWeatherForLanguage(connection, "Немецкий");
        assertEquals(1, results.size(), "Should return one record for regions with 'Немецкий' language");
        assertTrue(results.get(0).contains("Регион: Берлин"));
    }

    @Test
    void testFindAverageTemperatureForLargeRegions() throws SQLException {
        List<String> results = WeatherDatabase.findAverageTemperatureForLargeRegions(connection, 1000);
        assertEquals(1, results.size(), "Should return one record for regions with area > 1000");
        assertTrue(results.get(0).contains("Регион: Москва"));
    }
}
