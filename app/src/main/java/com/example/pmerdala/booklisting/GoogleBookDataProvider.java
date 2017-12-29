package com.example.pmerdala.booklisting;

import java.util.List;
import java.util.Properties;

/**
 * Created by merdala on 2017-12-28.
 */

/* TODO: zwracać coś lepszego niż Iteratable<Book>, należy zwracać objekt którego jednym z elementów będzie lista pozostałe pola to status żądania, komunikaty itp */
public class GoogleBookDataProvider implements BookDataProvider {

    private final String baseUrl;
    public final static int MAX_FETCH = 40;
    private int maxFetch = MAX_FETCH;
    private final RawDataProvider<String> rawDataProvider;
    private final BookParser<String> bookParser;

    public GoogleBookDataProvider(RawDataProvider<String> rawDataProvider, BookParser<String> bookParser, String baseUrl) {
        this.baseUrl = baseUrl;
        this.rawDataProvider = rawDataProvider;
        this.bookParser = bookParser;
    }

    public GoogleBookDataProvider(RawDataProvider<String> rawDataProvider, BookParser<String> bookParser, String baseUrl, int maxFetch) {
        this(rawDataProvider, bookParser, baseUrl);
        if (maxFetch > MAX_FETCH) {
            this.maxFetch = MAX_FETCH;
        } else {
            this.maxFetch = maxFetch;
        }
    }

    public int getMaxFetch() {
        return maxFetch;
    }

    public void setMaxFetch(int maxFetch) {
        if (maxFetch > MAX_FETCH) {
            this.maxFetch = MAX_FETCH;
        } else {
            this.maxFetch = maxFetch;
        }

    }

    public String getBaseUrl() {
        return baseUrl;
    }

    @Override
    public Iterable<Book> fetchBookList(String searchString) throws BookDataProviderException {
        String jsonString = fetchStringDataForBookList(searchString);
        List<Book> books = parserJsonForBookList(jsonString);
        return books;
    }


    private String fetchStringDataForBookList(String searchString) throws BookDataProviderException {
        String urlString = buildUrlStringForBookList(searchString);
        Properties properties = buildProperties(urlString);
        try {
            return rawDataProvider.fetchData(properties);
        } catch (RawDataProviderException e) {
            throw new BookDataProviderException("nie można pobrać danych z serwisu url=" + urlString, e);
        }
    }

    private Properties buildProperties(String urlString) {
        Properties prop = new Properties();
        prop.setProperty(RestServiceRawDataProvider.URL_PROPERTY, urlString);
        return prop;
    }

    private String buildUrlStringForBookList(String searchString) {
        StringBuilder sb = new StringBuilder(getBaseUrl());
        sb.append("?");
        if (searchString != null && !searchString.isEmpty()) {
            sb.append("q=").append(searchString).append("&");
        }
        sb.append("maxResults=").append(getMaxFetch());
        return sb.toString();
    }

    private List<Book> parserJsonForBookList(String jsonString) throws BookDataProviderException {
        try {
            return bookParser.listBookParser(jsonString, null);
        } catch (BookParserException e) {
            throw new BookDataProviderException("nie można sparsować json=" + jsonString, e);
        }
    }


}
