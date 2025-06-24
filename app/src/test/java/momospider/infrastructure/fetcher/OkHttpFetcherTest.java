package momospider.infrastructure.fetcher;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import momospider.core.exception.http.HttpException;
import momospider.infrastructure.config.ConfigLoader;
import momospider.infrastructure.http.okhttp.HttpClient;

public class OkHttpFetcherTest {
    private HttpClient httpClient;
    private OkHttpFetcher fetcher;

    @BeforeEach
    public void setUp() throws IOException {
        Map<String, String> headers = ConfigLoader.loadConfig("src/test/resources/header.properties");

        httpClient = new HttpClient(Optional.of(headers));
        fetcher = new OkHttpFetcher(httpClient);
    }

    @AfterEach
    public void tearDown() {
        if (httpClient != null) {
            httpClient.close();
        }
    }

    @Test
    public void testFetchHtml() throws HttpException {

        String html = fetcher.getHtml("https://www.google.com/");
        Assertions.assertNotNull(html);
        Assertions.assertTrue(html.contains("<html"));
    }

    @Test
    public void testFetchMomoShop() throws HttpException, IOException {

        String html = fetcher.getHtml(
                "https://www.momoshop.com.tw/search/searchShop.jsp?keyword=%E5%AC%B0%E5%85%92%E8%BB%8A&_isFuzzy=0&searchType=1");

        Assertions.assertNotNull(html);
        Assertions.assertTrue(html.contains("<html"));
    }
}
