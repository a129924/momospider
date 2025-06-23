package momospider.core.port.http;

import java.util.List;

public interface HtmlProcessor<T> {
    T parse(String html);

    List<T> parseAll(String html);
}
