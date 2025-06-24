package momospider.core.port.http;

import java.io.IOException;

public interface HttpClientPort {
    HttpResponse get(String url) throws IOException;
}
