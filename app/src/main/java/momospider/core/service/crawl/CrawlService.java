package momospider.core.service.crawl;

import java.util.List;

import momospider.core.exception.http.HttpException;
import momospider.core.models.ProductRecord;
import momospider.core.port.http.HtmlProcessor;
import momospider.core.port.http.HttpFetcher;

public class CrawlService {
    private final HttpFetcher httpFetcher;
    private final HtmlProcessor<ProductRecord> htmlProcessor;

    public CrawlService(HttpFetcher httpFetcher, HtmlProcessor<ProductRecord> htmlProcessor) {
        this.httpFetcher = httpFetcher;
        this.htmlProcessor = htmlProcessor;
    }

    public List<ProductRecord> crawl(String url) throws HttpException {
        String html = httpFetcher.getHtml(url);

        return htmlProcessor.parseAll(html);
    }
}
