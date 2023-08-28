package ru.bluebasooo.bomber.store.crud;

import ru.bluebasooo.bomber.store.entity.User;

class CRUDUtils {

    private static final String TABLE_NAME = "users";
    static String generateCreateStatement(User user) {
        return """
               INSERT INTO %s(telegram_id, email, password)
               VALUES (%s, %s, %s)
               RETURNING telegram_id, email, password;
               """.formatted(TABLE_NAME, user.telegramId(), user.email(), user.password());
    }

    static String generateGetStatement(String telegramId) {
        return """
               SELECT telegram_id, email, password
               FROM %s
               WHERE telegram_id = %s;
               """.formatted(TABLE_NAME, telegramId);
    }

    static String generateUpdateStatement(String telegramId) {
        //TODO
        return null;
    }

    static String generateDeleteStatement(String telegramId) {
        //TODO
        return null;
    }

}
