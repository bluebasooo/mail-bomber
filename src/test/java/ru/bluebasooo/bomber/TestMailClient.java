package ru.bluebasooo.bomber;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import ru.bluebasooo.bomber.consumer.MailConsumer;
import ru.bluebasooo.bomber.scrapper.client.MailClientImpl;
import ru.bluebasooo.bomber.scrapper.dto.UserInfo;

import java.io.IOException;
import java.util.stream.Stream;

public class TestMailClient {

    @Test
    public void testMailClient() throws MessagingException, IOException {
        var consumer = new MailConsumer() {
            Message[] message = new Message[0];
            @Override
            public void supplyMessage(Message[] message) {
                this.message = message;
            }
        };

        var client = new MailClientImpl(new UserInfo(
                "imap.yandex.ru",
                "blyayachapoveshuci@yandex.ru",
                System.getenv("OAUTH_TOKEN")
        )).createConnection()
          .receiveMessagesBy(consumer);

        while (consumer.message.length == 0) { }

        System.out.println(consumer.message[0].getContent().toString());
    }
}
