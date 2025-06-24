package momospider.infrastructure.html.parser.jsoup;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JsoupParserTest {

    private JsoupParser parser;

    @BeforeEach
    public void setUp() {
        parser = new JsoupParser(
                "<html><body><p class='p1'>Hello, world!</p><p class='p2'>Hello, world!2</p></body></html>");
    }

    @Test
    public void testFirstElementText() {
        Optional<String> text = parser.text("body p");

        // check if the text is present
        Assertions.assertTrue(text.isPresent());

        // check if the text is correct
        Assertions.assertEquals("Hello, world!", text.get());

    }

    @Test
    public void testAllElementTexts() {
        Stream<String> texts = parser.texts("body p");

        List<String> checkList = Arrays.asList("Hello, world!", "Hello, world!2");

        Assertions.assertEquals(checkList, texts.toList());
    }

    @Test
    public void testFirstElementAttr() {
        Optional<String> attr = parser.attr("body p", "class");

        Assertions.assertTrue(attr.isPresent());
        Assertions.assertEquals("p1", attr.get());
    }

    @Test
    public void testAllElementAttrs() {
        Stream<String> attrs = parser.attrs("body p", "class");

        List<String> checkList = Arrays.asList("p1", "p2");

        Assertions.assertEquals(checkList, attrs.toList());
    }
}
