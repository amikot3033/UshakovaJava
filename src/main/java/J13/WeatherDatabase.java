package J13;
import java.sql.*;

public class WeatherDatabase {
    private static final String DB_URL = "jdbc:h2:~/weatherDB";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            System.out.println("Connected to H2 database!");

            createTables(connection);

            insertTestData(connection);

            printWeatherInRegion(connection, "Москва");
            printSnowyDaysWithLowTemperature(connection, "Москва", -5);
            printWeatherForLanguage(connection, "Русский");
            printAverageTemperatureForLargeRegions(connection, 1000);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void createTables(Connection connection) throws SQLException {
        String createInhabitantsTypeTable = """
                CREATE TABLE IF NOT EXISTS inhabitants_type (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(255) NOT NULL,
                    language VARCHAR(255) NOT NULL
                );
            """;

        String createRegionTable = """
                CREATE TABLE IF NOT EXISTS region (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(255) NOT NULL,
                    area DOUBLE NOT NULL,
                    inhabitants_type_id INT NOT NULL,
                    FOREIGN KEY (inhabitants_type_id) REFERENCES inhabitants_type(id)
                );
            """;

        String createWeatherTable = """
                CREATE TABLE IF NOT EXISTS weather (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    region_id INT NOT NULL,
                    date DATE NOT NULL,
                    temperature DOUBLE NOT NULL,
                    precipitation VARCHAR(50) NOT NULL,
                    FOREIGN KEY (region_id) REFERENCES region(id)
                );
            """;

        try (Statement statement = connection.createStatement()) {
            statement.execute(createInhabitantsTypeTable);
            statement.execute(createRegionTable);
            statement.execute(createWeatherTable);
        }
    }

    static void insertTestData(Connection connection) throws SQLException {
        String insertInhabitantsType = """
                INSERT INTO inhabitants_type (name, language) VALUES
                ('Русские', 'Русский'),
                ('Немцы', 'Немецкий'),
                ('Казахи', 'Казахский');
            """;

        String insertRegions = """
                INSERT INTO region (name, area, inhabitants_type_id) VALUES
                ('Москва', 2561.0, 1),
                ('Берлин', 891.0, 2),
                ('Алматы', 682.0, 3);
            """;

        String insertWeather = """
                INSERT INTO weather (region_id, date, temperature, precipitation) VALUES
                (1, '2024-12-18', -5.0, 'Снег'),
                (1, '2024-12-19', 2.0, 'Дождь'),
                (2, '2024-12-18', -10.0, 'Снег'),
                (3, '2024-12-20', -15.0, 'Снег');
            """;

        try (Statement statement = connection.createStatement()) {
            statement.execute(insertInhabitantsType);
            statement.execute(insertRegions);
            statement.execute(insertWeather);
        }
    }

    private static void printWeatherInRegion(Connection connection, String regionName) throws SQLException {
        String query = """
                SELECT w.date, w.temperature, w.precipitation
                FROM weather w
                JOIN region r ON w.region_id = r.id
                WHERE r.name = ?
            """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, regionName);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Погода в регионе " + regionName + ":");
            while (resultSet.next()) {
                Date date = resultSet.getDate("date");
                double temperature = resultSet.getDouble("temperature");
                String precipitation = resultSet.getString("precipitation");
                System.out.printf("Дата: %s, Температура: %.1f, Осадки: %s%n", date, temperature, precipitation);
            }
        }
    }

    private static void printSnowyDaysWithLowTemperature(Connection connection, String regionName, double maxTemperature) throws SQLException {
        String query = """
                SELECT w.date
                FROM weather w
                JOIN region r ON w.region_id = r.id
                WHERE r.name = ? AND w.precipitation = 'Снег' AND w.temperature < ?
            """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, regionName);
            preparedStatement.setDouble(2, maxTemperature);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Даты, когда в регионе " + regionName + " шел снег и температура была ниже " + maxTemperature + ":");
            while (resultSet.next()) {
                Date date = resultSet.getDate("date");
                System.out.println(date);
            }
        }
    }

    private static void printWeatherForLanguage(Connection connection, String language) throws SQLException {
        String query = """
                SELECT r.name AS region, w.date, w.temperature, w.precipitation
                FROM weather w
                JOIN region r ON w.region_id = r.id
                JOIN inhabitants_type it ON r.inhabitants_type_id = it.id
                WHERE it.language = ? AND w.date >= DATEADD('DAY', -7, CURRENT_DATE)
            """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, language);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Погода за прошедшую неделю в регионах, где жители говорят на " + language + ":");
            while (resultSet.next()) {
                String region = resultSet.getString("region");
                Date date = resultSet.getDate("date");
                double temperature = resultSet.getDouble("temperature");
                String precipitation = resultSet.getString("precipitation");
                System.out.printf("Регион: %s, Дата: %s, Температура: %.1f, Осадки: %s%n", region, date, temperature, precipitation);
            }
        }
    }

    private static void printAverageTemperatureForLargeRegions(Connection connection, double minArea) throws SQLException {
        String query = """
                SELECT r.name AS region, AVG(w.temperature) AS avg_temperature
                FROM weather w
                JOIN region r ON w.region_id = r.id
                WHERE w.date >= DATEADD('DAY', -7, CURRENT_DATE) AND r.area > ?
                GROUP BY r.name
            """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, minArea);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Средняя температура за прошедшую неделю в регионах с площадью больше " + minArea + ":");
            while (resultSet.next()) {
                String region = resultSet.getString("region");
                double avgTemperature = resultSet.getDouble("avg_temperature");
                System.out.printf("Регион: %s, Средняя температура: %.2f%n", region, avgTemperature);
            }
        }
    }
}
