package momospider.infrastructure.html.parser.jsoup;

import java.util.Optional;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import momospider.core.port.http.HtmlParser;

/**
 * JsoupParser is a class that parses HTML using Jsoup.
 * 
 * @author Andrew
 */
public final class JsoupParser implements HtmlParser {

    private final Document document;

    public JsoupParser(String html) {
        this.document = Jsoup.parse(html);
    }

    /**
     * Returns all elements that match the selector.
     * 
     * @param selector the selector to match
     * @return all elements that match the selector
     */
    public Elements select(String selector) {
        return document.select(selector);
    }

    public Optional<Element> parseFirst(String selector) {
        return Optional.ofNullable(select(selector).first());
    }

    /**
     * Returns all elements that match the selector.
     * 
     * @param selector the selector to match
     * @return all elements that match the selector
     */
    public Optional<Elements> parseAll(String selector) {
        return Optional.ofNullable(select(selector));
    }

    /**
     * Returns the text of the first element that matches the selector.
     * 
     * @param selector the selector to match
     * @return the text of the first element that matches the selector
     */
    @Override
    public Optional<String> text(String selector) {
        return parseFirst(selector).map(Element::text);
    }

    /**
     * Returns the text of all elements that match the selector.
     * 
     * @param selector the selector to match
     * @return the text of all elements that match the selector
     */
    @Override
    public Stream<String> texts(String selector) {
        return select(selector).stream().map(Element::text);
    }

    /**
     * Returns the attribute of the first element that matches the selector.
     * 
     * @param selector  the selector to match
     * @param attribute the attribute to get
     * @return the attribute of the first element that matches the selector
     */
    @Override
    public Optional<String> attr(String selector, String attribute) {
        return parseFirst(selector).map(element -> element.attr(attribute));
    }

    /**
     * Returns the attribute of all elements that match the selector.
     * 
     * @param selector  the selector to match
     * @param attribute the attribute to get
     * @return the attribute of all elements that match the selector
     */
    @Override
    public Stream<String> attrs(String selector, String attribute) {
        return select(selector).stream().map(element -> element.attr(attribute));
    }
}
