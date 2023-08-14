package ru.bluebasooo.bomber.scrapper.client;

import jakarta.mail.Message;

import java.util.stream.Stream;

public interface MailConsumer {
    void supplyMessages(Stream<Message> messages);
}
