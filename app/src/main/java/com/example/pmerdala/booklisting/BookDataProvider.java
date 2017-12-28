package com.example.pmerdala.booklisting;

/**
 * Created by merdala on 2017-12-28.
 */

public interface BookDataProvider {

    public Iterable<Book> fetchBookList(String searchString) throws BookDataProviderException;
}
