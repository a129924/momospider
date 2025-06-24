package momospider.core.port.http;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * HtmlParser is a interface that parses HTML.
 * 
 * @author Andrew
 */
public interface HtmlParser {

    Stream<String> texts(String selector);

    Stream<String> attrs(String selector, String attribute);

    Optional<String> text(String selector);

    Optional<String> attr(String selector, String attribute);

}
