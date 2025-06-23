package momospider.core.port.http;

import momospider.core.exception.http.HttpException;

public interface HttpFetcher {
    String getHtml(String url) throws HttpException;
}
