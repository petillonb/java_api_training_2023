package fr.lernejo.navy_battle.HTTPClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPClientService {

    final private String adversaryUrl;

    public HTTPClientService(String adversaryUrl) {
        this.adversaryUrl = adversaryUrl;

    }

    public void post(String path, String body) throws IOException, InterruptedException {
        URI uri = URI.create(adversaryUrl + path);
        HttpRequest requetePost = HttpRequest.newBuilder()
            .uri(uri)
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(requetePost, HttpResponse.BodyHandlers.ofString());

    }

}
