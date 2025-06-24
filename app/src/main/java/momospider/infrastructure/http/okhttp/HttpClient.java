package momospider.infrastructure.http.okhttp;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import momospider.core.port.http.HttpClientPort;
import momospider.core.port.http.HttpResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public final class HttpClient implements HttpClientPort {
    private final OkHttpClient client;

    /**
     * Create a new HttpClient with the given headers.
     * 
     * @param headers the headers to use for the client
     */
    public HttpClient(Optional<Map<String, String>> headers) {
        this.client = createHttpClient(headers.orElse(Map.of()));
    }

    private static OkHttpClient createHttpClient(Map<String, String> headers) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        headers.forEach((key, value) -> builder.addInterceptor(chain -> chain.proceed(
                chain.request().newBuilder().addHeader(key, value).build())));

        return builder.build();
    }

    public void close() {
        client.dispatcher().executorService().shutdown();
        client.connectionPool().evictAll();
    }

    /**
     * Get the response from the given URL.
     * 
     * @param url the URL to get the response from
     * @return the response from the given URL
     * @throws IOException if an I/O error occurs
     */
    @Override
    public HttpResponse get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();

        ResponseBody body = response.body();

        if (body == null) {
            throw new IOException("Response body is null");
        }

        return new HttpResponse(response.code(), body.string());
    }
}
