package J13;

import org.junit.jupiter.api.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WeatherDatabaseTest {
    private Connection connection;

    @BeforeAll
    void setUpDatabase() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        WeatherDatabase.createTables(connection);
        WeatherDatabase.insertTestData(connection);
    }

    @AfterAll
    void tearDownDatabase() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void testPrintWeatherInRegion() throws SQLException {
        String query = """
                SELECT w.date, w.temperature, w.precipitation
                FROM weather w
                JOIN region r ON w.region_id = r.id
                WHERE r.name = 'Москва'
            """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            assertTrue(resultSet.next());
            assertEquals(Date.valueOf("2024-12-18"), resultSet.getDate("date"));
            assertEquals(-5.0, resultSet.getDouble("temperature"));
            assertEquals("Снег", resultSet.getString("precipitation"));
        }
    }

    @Test
    void testPrintSnowyDaysWithLowTemperature() throws SQLException {
        String query = """
                SELECT w.date
                FROM weather w
                JOIN region r ON w.region_id = r.id
                WHERE r.name = 'Москва' AND w.precipitation = 'Снег' AND w.temperature < -5
            """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            assertFalse(resultSet.next());
        }
    }

    @Test
    void testPrintWeatherForLanguage() throws SQLException {
        String query = """
                SELECT r.name AS region, w.date, w.temperature, w.precipitation
                FROM weather w
                JOIN region r ON w.region_id = r.id
                JOIN inhabitants_type it ON r.inhabitants_type_id = it.id
                WHERE it.language = 'Русский' AND w.date >= DATEADD('DAY', -7, CURRENT_DATE)
            """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            assertTrue(resultSet.next());
            assertEquals("Москва", resultSet.getString("region"));
        }
    }

    @Test
    void testPrintAverageTemperatureForLargeRegions() throws SQLException {
        String query = """
                SELECT r.name AS region, AVG(w.temperature) AS avg_temperature
                FROM weather w
                JOIN region r ON w.region_id = r.id
                WHERE w.date >= DATEADD('DAY', -7, CURRENT_DATE) AND r.area > 1000
                GROUP BY r.name
            """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            assertTrue(resultSet.next());
            assertEquals("Москва", resultSet.getString("region"));
            assertEquals(-1.5, resultSet.getDouble("avg_temperature"), 0.01); // Средняя температура (-5 + 2)/2
        }
    }
}
