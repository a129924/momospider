package momospider.core.exception.http;

import java.io.IOException;

public class HttpException extends IOException {
    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(Throwable cause) {
        super(cause);
    }
}