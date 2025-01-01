package J13;

import java.sql.*;

public class WeatherApp {
    private static final String DB_URL = "jdbc:h2:~/weatherDB";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            System.out.println("Connected to H2 database!");

            String query = """
            SELECT w.date, w.temperature, w.precipitation
            FROM weather w
            JOIN region r ON w.region_id = r.id
            WHERE r.name = ?
        """;

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, "Москва");
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Date date = resultSet.getDate("date");
                    double temperature = resultSet.getDouble("temperature");
                    String precipitation = resultSet.getString("precipitation");

                    System.out.printf("Дата: %s, Температура: %.1f, Осадки: %s%n",
                            date, temperature, precipitation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

