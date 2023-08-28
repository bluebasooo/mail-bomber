package ru.bluebasooo.bomber.store.db;

import ru.bluebasooo.bomber.store.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class Database {

    private final String JDBC_URL = "jdbc:postgres://%s:%s/%s?sslmode=disable".formatted(
            System.getenv("DB_HOST"),
            System.getenv("DB_PORT"),
            System.getenv("DB_NAME")
    );

    private Connection connection;

    public Database tryConnection() {
        try {
            connection = DriverManager.getConnection(
                            JDBC_URL,
                            System.getenv("DB_USERNAME"),
                            System.getenv("DB_PASSWORD")
                    );

        } catch (SQLException e) {}

        return this;
    }

    public Optional<List<User>> executeStatment(String query) {
        try (Statement statement = connection.createStatement()) {
            var result = statement.executeQuery(query);

            return Optional.of(ResultSetConverter.getUsers(result));
        } catch (SQLException e) {}

        return Optional.empty();
    }

    public Optional<User> executeStatementWithOneResult(String query) {
        try (Statement statement = connection.createStatement()) {
            var result = statement.executeQuery(query);

            return Optional.of(
                    new User (
                          result.getString("telegram_id"),
                          result.getString("email"),
                          result.getString("password")
                    )
            );

        } catch (SQLException e) {}

        return Optional.empty();
    }

}
