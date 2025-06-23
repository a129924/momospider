package momospider.core.port.http;

public interface HttpClient {
    HttpResponse get(String url);
}
