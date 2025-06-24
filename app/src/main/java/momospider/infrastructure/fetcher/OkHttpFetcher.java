package momospider.infrastructure.fetcher;

import java.io.IOException;

import momospider.core.exception.http.HttpException;
import momospider.core.port.http.HttpFetcher;
import momospider.core.port.http.HttpResponse;
import momospider.infrastructure.http.okhttp.HttpClient;

public final class OkHttpFetcher implements HttpFetcher {
    private final HttpClient httpClient;

    public OkHttpFetcher(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public String getHtml(String url) throws HttpException {
        try {
            HttpResponse response = httpClient.get(url);

            if (response.statusCode() != 200) {
                throw new HttpException("Failed to fetch HTML" + "response: " + response.body() + "status code: "
                        + response.statusCode());
            }

            return response.body();
        } catch (IOException e) {
            throw new HttpException("Failed to fetch HTML", e);
        }
    }
}
