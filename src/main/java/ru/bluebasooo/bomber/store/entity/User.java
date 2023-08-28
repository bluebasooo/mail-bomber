package ru.bluebasooo.bomber.store.entity;

public record User(
        String email,
        String password,
        String telegramId
) { }
