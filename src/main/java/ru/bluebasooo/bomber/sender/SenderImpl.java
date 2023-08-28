package ru.bluebasooo.bomber.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bluebasooo.bomber.scrapper.reconstructor.Mail;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class SenderImpl implements Sender {

    private final HttpClient client = HttpClient.newHttpClient();

    private final ObjectMapper mapper = new ObjectMapper();

    private final String baseURL = "https://api.telegram.org/bot%s/".formatted(System.getenv("TOKEN"));
    @Override
    public CompletableFuture<HttpResponse<String>> sendMail(Mail mail) throws JsonProcessingException, URISyntaxException {
        var mailJson = mapper.writeValueAsBytes(mail);

        var request = HttpRequest.newBuilder()
                .uri(new URI(baseURL + "sendMail"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofByteArray(mailJson))
                .build();

        return client.sendAsync(
                request,
                HttpResponse.BodyHandlers.ofString());
    }
}
