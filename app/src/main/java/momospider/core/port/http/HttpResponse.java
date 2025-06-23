package momospider.core.port.http;

public final record HttpResponse(int statusCode, String body) {
    public boolean raiseForStatus() {
        return statusCode >= 200 && statusCode < 300;
    }
}
