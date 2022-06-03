package pl.britenet.campus.service.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class DatabaseService {

    private final HikariDataSource dataSource;

    public DatabaseService() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/shopnowy");
        config.setUsername("root");
        config.setPassword("");
        this.dataSource = new HikariDataSource(config);
    }

    public void performDML(String dmlQuery) {
        try (Connection connection = this.dataSource.getConnection() ;
             PreparedStatement statement = connection.prepareStatement(dmlQuery)) {

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public void performDML(String dmlQuery) {
//        try (Connection connection = this.dataSource.getConnection() ;
//             PreparedStatement statement = connection.prepareStatement(dmlQuery, Statement.RETURN_GENERATED_KEYS)) {
//
//            statement.executeUpdate();
//            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    System.out.println((generatedKeys.getLong(1)));
//                }
//                else {
//                    System.out.println("Creating user failed, no ID obtained.");
////                    throw new SQLException("Creating user failed, no ID obtained.");
//                }
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public<T> T performQuery(String sqlQuery, ResultParser<T> parser) {
        try (Connection connection = this.dataSource.getConnection() ;
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            ResultSet resultSet = statement.executeQuery();
            return parser.parse(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
