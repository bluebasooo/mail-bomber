package ru.bluebasooo.bomber.consumer;

import jakarta.mail.Message;

public interface MailConsumer {
    void supplyMessage(Message[] messages);
}
