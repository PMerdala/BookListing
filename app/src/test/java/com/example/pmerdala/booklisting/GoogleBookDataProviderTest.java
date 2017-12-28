package com.example.pmerdala.booklisting;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by merdala on 2017-12-28.
 */

public class GoogleBookDataProviderTest {

    @Test
    public void testFetchBookList() throws BookDataProviderException{
        ArrayList<Book> booksExpect = new ArrayList<>();
        ComparisonComparableIterator<Book> comparison = new ComparisonComparableIterator<>();
        booksExpect.add(BookExampleTestData.getBook());
        BookDataProvider dataProvider = new GoogleBookDataProvider(new RestServiceRawDataProvider(),new GoogleJsonBookParser(), "https://www.googleapis.com/books/v1/volumes",1);
        Iterable<Book> books = dataProvider.fetchBookList("android");
        Assert.assertEquals(0,comparison.compare(booksExpect,books));
    }
}
