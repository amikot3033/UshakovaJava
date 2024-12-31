package J13;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WeatherDatabase {
    private static final String DB_URL = "jdbc:h2:~/weatherDB";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            System.out.println("Connected to H2 database!");

            createTables(connection);

            clearTables(connection);

            insertTestData(connection);

            System.out.println("Погода в регионе 'Москва':");
            findWeatherInRegion(connection, "Москва").forEach(System.out::println);

            System.out.println("\nДни со снегом и температурой ниже -4 в регионе 'Москва':");
            findSnowyDaysWithLowTemperature(connection, "Москва", -5).forEach(System.out::println);

            System.out.println("\nПогода в регионах, где говорят на немецком языке");
            findWeatherForLanguage(connection, "Немецкий").forEach(System.out::println);

            System.out.println("\nСредняя температура в регионах с площадью больше 1000:");
            findAverageTemperatureForLargeRegions(connection, 1000).forEach(System.out::println);



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

    static void clearTables(Connection connection) throws SQLException {
        String deleteWeather = "DELETE FROM weather";
        String deleteRegion = "DELETE FROM region";
        String deleteInhabitantsType = "DELETE FROM inhabitants_type";

        try (Statement statement = connection.createStatement()) {
            statement.execute(deleteWeather);
            statement.execute(deleteRegion);
            statement.execute(deleteInhabitantsType);
        }
    }

    static void insertTestData(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            // Очистка таблиц в обратном порядке зависимости
            statement.execute("DELETE FROM weather");
            statement.execute("DELETE FROM region");
            statement.execute("DELETE FROM inhabitants_type");

            statement.execute("""
            INSERT INTO inhabitants_type (id, name, language) VALUES
            (1, 'Русские', 'Русский'),
            (2, 'Немцы', 'Немецкий'),
            (3, 'Казахи', 'Казахский');
        """);

            statement.execute("""
            INSERT INTO region (id, name, area, inhabitants_type_id) VALUES
            (1, 'Москва', 2561.0, 1),
            (2, 'Берлин', 891.0, 2),
            (3, 'Алматы', 682.0, 3);
        """);

            // Проверка вставки данных в region
//            ResultSet regionSet = statement.executeQuery("SELECT * FROM region");
//            while (regionSet.next()) {
//                System.out.println("ID: " + regionSet.getInt("id") +
//                        ", Name: " + regionSet.getString("name"));
//            }

            statement.execute("""
            INSERT INTO weather (region_id, date, temperature, precipitation) VALUES
            (1, '2024-12-18', -5.0, 'Снег'),
            (1, '2024-12-19', 2.0, 'Дождь'),
            (2, '2024-12-18', -10.0, 'Снег'),
            (3, '2024-12-20', -15.0, 'Снег');
        """);
        }
    }



    public static List<Weather> findWeatherInRegion(Connection connection, String regionName) throws SQLException {
        String query = """
                SELECT w.date, w.temperature, w.precipitation
                FROM weather w
                JOIN region r ON w.region_id = r.id
                WHERE r.name = ?
            """;
        List<Weather> results = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, regionName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                results.add(new Weather(
                        resultSet.getDate("date"),
                        resultSet.getDouble("temperature"),
                        resultSet.getString("precipitation")
                ));
            }
        }
        return results;
    }


    public static List<Weather> findSnowyDaysWithLowTemperature(Connection connection, String regionName, double temperatureThreshold) throws SQLException {
        String query = """
                SELECT w.date, w.temperature, w.precipitation
                FROM weather w
                JOIN region r ON w.region_id = r.id
                WHERE r.name = ? AND w.precipitation = 'Снег' AND w.temperature <= ?
            """;
        List<Weather> results = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, regionName);
            preparedStatement.setDouble(2, temperatureThreshold);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                results.add(new Weather(
                        resultSet.getDate("date"),
                        resultSet.getDouble("temperature"),
                        resultSet.getString("precipitation")
                ));
            }
        }
        return results;
    }


    public static List<Weather> findWeatherForLanguage(Connection connection, String language) throws SQLException {
        String query = """
                SELECT w.date, w.temperature, w.precipitation
                FROM weather w
                JOIN region r ON w.region_id = r.id
                JOIN inhabitants_type it ON r.inhabitants_type_id = it.id
                WHERE it.language = ?
            """;
        List<Weather> results = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, language);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                results.add(new Weather(
                        resultSet.getDate("date"),
                        resultSet.getDouble("temperature"),
                        resultSet.getString("precipitation")
                ));
            }
        }
        return results;
    }

    public static List<Weather> findAllTemperaturesForLargeRegions(Connection connection, double areaThreshold) throws SQLException {
        String query = """
                SELECT w.date, w.temperature, w.precipitation
                FROM weather w
                JOIN region r ON w.region_id = r.id
                WHERE r.area > ?
            """;
        List<Weather> results = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, areaThreshold);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                results.add(new Weather(
                        resultSet.getDate("date"),
                        resultSet.getDouble("temperature"),
                        resultSet.getString("precipitation")
                ));
            }
        }
        return results;
    }

    public static List<String> findAverageTemperatureForLargeRegions(Connection connection, double areaThreshold) throws SQLException {
        String query = """
                SELECT r.name AS region, AVG(w.temperature) AS avg_temperature
                FROM weather w
                JOIN region r ON w.region_id = r.id
                WHERE r.area > ?
                GROUP BY r.name
            """;
        List<String> results = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, areaThreshold);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                results.add(String.format("Регион: %s, Средняя температура: %.1f",
                        resultSet.getString("region"),
                        resultSet.getDouble("avg_temperature")));
            }
        }
        return results;
    }


}
