package ru.bluebasooo.bomber.store.db;

import ru.bluebasooo.bomber.store.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class ResultSetConverter {
    static List<User> getUsers(ResultSet result) throws SQLException {
        List<User> users = new ArrayList<>();
        while(result.next()) {
            users.add(
                    new User(
                            result.getString("telegram_id"),
                            result.getString("email"),
                            result.getString("password")
                    )
            );
        }
        return users;
    }

}
