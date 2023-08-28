package ru.bluebasooo.bomber.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.bluebasooo.bomber.scrapper.reconstructor.Mail;

import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public interface Sender {
    CompletableFuture<HttpResponse<String>> sendMail(Mail mail) throws JsonProcessingException, URISyntaxException;
}
