package ru.bluebasooo.bomber.scrapper.reconstructor;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import ru.bluebasooo.bomber.scrapper.reconstructor.util.TextFromMessageUtils;

import java.io.IOException;

class ConverterToPojo {

    public static Mail reconstruct(Message message) throws MessagingException, IOException {
        return new Mail(
                message.getFrom().toString(),
                TextFromMessageUtils.getTextFromMessage(message),
                null
        );
    }
}
