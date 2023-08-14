package ru.bluebasooo.bomber.reconstructor;


import jakarta.mail.Message;

import java.util.stream.Stream;

/**
 * Reconstructs mails to need format
 */
public interface Reconstructor {
    Stream<Mail> reconstruct(Stream<Message> mails);
}
