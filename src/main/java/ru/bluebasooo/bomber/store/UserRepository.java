package ru.bluebasooo.bomber.store;

import ru.bluebasooo.bomber.store.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> createUser(User user);

    Optional<User> getUser(String telegramId);

    Optional<User> updateUser(String telegramId);

    Optional<User> deleteUser(String telegramId);
}
