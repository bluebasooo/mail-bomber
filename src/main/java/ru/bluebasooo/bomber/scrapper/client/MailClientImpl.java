package ru.bluebasooo.bomber.scrapper.client;

import jakarta.mail.*;
import jakarta.mail.event.MessageCountEvent;
import jakarta.mail.event.MessageCountListener;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Properties;


/**
 * Class abstracts work with mailing
 * Uses to connect to mail and receive new messages
 */
@RequiredArgsConstructor
public class MailClientImpl implements MailClient {

    private final UserInfo userInfo;

    private Folder inbox;

    private Properties initProps(UserInfo info) {
        var props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.host", "smtp.yandex.ru");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.user", info.username());
        props.put("mail.smtp.ssl.enable", true);
        props.put("mail.smtp.auth", true);
        props.put("mail.debug", true);
        return props;
    }

    public MailClientImpl createConnection() throws MessagingException {
        var session = Session.getInstance(
                initProps(userInfo),
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                userInfo.username(),
                                userInfo.userPassword()
                        );
                    }
                }
        );

        inbox = session.getStore().getFolder("INBOX");



        return this;
    }
    public MailClientImpl receiveMessagesBy(MailConsumer consumer) {

        inbox.addMessageCountListener(new MessageCountListener() {
            @Override
            public void messagesAdded(MessageCountEvent e) {
                consumer.supplyMessages(
                        Arrays.stream(e.getMessages())
                );
            }

            @Override
            public void messagesRemoved(MessageCountEvent e) {}
        });

        return this;
    }

}
