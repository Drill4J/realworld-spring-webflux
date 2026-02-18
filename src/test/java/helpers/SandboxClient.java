package helpers;

import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.function.Consumer;

/**
 * Test helper compatibility wrapper for SandboxClient used by some tests.
 *
 * Provides a simple get(...) method delegating to an underlying WebTestClient.
 */
public class SandboxClient {
    private final WebTestClient client;

    public SandboxClient(WebTestClient client) {
        this.client = client;
    }

    public SandboxClient() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        this.client = WebTestClient.bindToServer().baseUrl(baseUrl).build();
    }

    public WebTestClient.ResponseSpec get(String uri) {
        return client.get().uri(uri).exchange();
    }

    public WebTestClient.ResponseSpec get(String uri, Consumer<HttpHeaders> headers) {
        return client.get().uri(uri).headers(headers).exchange();
    }

    public WebTestClient.ResponseSpec get(String uri, HttpHeaders headersObj) {
        return client.get().uri(uri).headers(h -> h.addAll(headersObj)).exchange();
    }
}
