package ru.bluebasooo.bomber.scrapper.client;

import jakarta.mail.*;
import jakarta.mail.event.MessageCountEvent;
import jakarta.mail.event.MessageCountListener;
import lombok.RequiredArgsConstructor;
import ru.bluebasooo.bomber.consumer.MailConsumer;
import ru.bluebasooo.bomber.scrapper.dto.UserInfo;

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

    private Properties initProps() {
        var props = new Properties();
        props.put("mail.store.protocol", "imap");
        props.put("mail.imap.host", userInfo.emailHost());
        props.put("mail.imap.port", "993");
        props.put("mail.imap.user", userInfo.username());
        props.put("mail.imap.auth", "true");
        props.put("mail.imap.auth.mechanisms", "XOAUTH2");
        props.put("mail.imaps.starttls.enable", "true");
        props.put("mail.imap.ssl.enable", "true");
        props.put("mail.debug", "true");
        return props;
    }

    public MailClientImpl createConnection() throws MessagingException {
        var session = Session.getInstance(initProps());

        var store = session.getStore("imap");
        store.connect(userInfo.emailHost(), userInfo.username(), userInfo.token());

        inbox = store.getFolder("INBOX");

        return this;
    }
    public MailClientImpl receiveMessagesBy(MailConsumer consumer) {

        inbox.addMessageCountListener(new MessageCountListener() {
            @Override
            public void messagesAdded(MessageCountEvent e) {
                consumer.supplyMessage(
                       e.getMessages()
                );
            }

            @Override
            public void messagesRemoved(MessageCountEvent e) {}
        });

        return this;
    }

}
