package com.example.pmerdala.booklisting;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by merdala on 2017-12-28.
 */

@RunWith(MockitoJUnitRunner.class)
public class GoogleBookDataProviderTest {

    @Mock
    RestServiceRawDataProvider rawDataProvider;


    @Test
    public void testFetchBookList() throws BookDataProviderException,RawDataProviderException{
        ArrayList<Book> booksExpect = new ArrayList<>();
        ComparisonComparableIterator<Book> comparison = new ComparisonComparableIterator<>();
        booksExpect.add(BookExampleTestData.getBook());
        Properties prop = new Properties();
        prop.setProperty(RestServiceRawDataProvider.URL_PROPERTY,"https://www.googleapis.com/books/v1/volumes?q=android&maxResults=1");
            when(rawDataProvider.fetchData(prop))
                    .thenReturn(JsonTestData.jsonString);
        BookDataProvider dataProvider = new GoogleBookDataProvider(rawDataProvider,new GoogleJsonBookParser(), "https://www.googleapis.com/books/v1/volumes",1);
        Iterable<Book> books = dataProvider.fetchBookList("android");
        Assert.assertEquals(0,comparison.compare(booksExpect,books));
    }
}
