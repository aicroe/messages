package org.example.messagesclient.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientFacade {

    private HttpClient client;

    public HttpClientFacade() {
        client = HttpClient.newHttpClient();
    }

    public String get(String uri) throws AgentException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .GET()
            .build();

        return send(request);
    }

    public String post(String uri, String payload) throws AgentException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(payload))
            .build();

        return send(request);
    }

    public String put(String uri, String payload) throws AgentException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .header("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(payload))
            .build();

        return send(request);
    }

    public String delete(String uri) throws AgentException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .DELETE()
            .build();

        return send(request);
    }

    private String send(HttpRequest request) throws AgentException {
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new AgentException(response.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new AgentException(e.getMessage());
        }
    }
}
