package com.example.pmerdala.booklisting;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;

/**
 * Created by merdala on 2017-12-28.
 */

public class GoogleJsonBookParserTest implements JsonTestData {


    @Test
    public void testListBookParser() throws BookParserException{
        Book bookExpects = BookExampleTestData.getBook();
        BookParser<String> parser = new GoogleJsonBookParser();
        List<Book> books = parser.listBookParser(jsonString,null);
        assertEquals(1,books.size());
        assertEquals(bookExpects,books.get(0));
    }
    @Test(expected = BookParserException.class)
    public void testListBookParserNullInput() throws BookParserException{
        BookParser<String> parser = new GoogleJsonBookParser();
        List<Book> books = parser.listBookParser(null,null);
    }
}
