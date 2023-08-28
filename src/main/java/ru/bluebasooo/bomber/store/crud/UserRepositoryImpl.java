package ru.bluebasooo.bomber.store.crud;

import lombok.RequiredArgsConstructor;
import ru.bluebasooo.bomber.store.UserRepository;
import ru.bluebasooo.bomber.store.db.Database;
import ru.bluebasooo.bomber.store.entity.User;

import java.util.Optional;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final Database db;

    @Override
    public Optional<User> createUser(User user) {
        return db.executeStatementWithOneResult(
            CRUDUtils.generateCreateStatement(user)
        );
    }

    @Override
    public Optional<User> getUser(String telegramId) {
        return db.executeStatementWithOneResult(
                CRUDUtils.generateGetStatement(telegramId)
        );
    }

    @Override
    public Optional<User> updateUser(String telegramId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> deleteUser(String telegramId) {
        return Optional.empty();
    }
}
