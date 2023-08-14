package ru.bluebasooo.bomber.reconstructor;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import ru.bluebasooo.bomber.reconstructor.util.TextFromMessageUtils;

import java.io.IOException;
import java.util.stream.Stream;

public class ReconstructorToJSON implements Reconstructor {

    @Override
    public Stream<Mail> reconstruct(Stream<Message> mails) {
        return mails.map((mail) -> {
            try {
                return new Mail(
                        mail.getFrom().toString(),
                        TextFromMessageUtils.getTextFromMessage(mail),
                        null
                );
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
