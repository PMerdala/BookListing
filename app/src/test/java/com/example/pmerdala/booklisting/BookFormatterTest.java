package com.example.pmerdala.booklisting;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by PMerd_000 on 2017-12-28.
 */

public class BookFormatterTest {
    @Test
    public void testGetFullTitle(){
        BookFormatter book = new BookFormatter(BookExampleTestData.getBook());
        assertEquals("Android for Work, Productivity for Professionals",book.getFullTitle());
        book = new BookFormatter(new Book("1","test title",null,null,null,null,null,null,null,null,null));
        assertEquals("test title",book.getFullTitle());
        book = new BookFormatter(new Book("1","test title","",null,null,null,null,null,null,null,null));
        assertEquals("test title",book.getFullTitle());
    }

    @Test
    public void testGetIsbns(){
        BookFormatter book = new BookFormatter(BookExampleTestData.getBook());
        assertEquals("9781430230007, 1430230002",book.getIsbns());
        book = new BookFormatter(new Book("1","test title",null,null,null,null,null,null,null,null,null));
        Assert.assertNull(book.getIsbns());
        book = new BookFormatter(new Book("1","test title",null,null,null,null,null,null,null,"isbn13",null));
        assertEquals("isbn13",book.getIsbns());
        book = new BookFormatter(new Book("1","test title",null,null,null,null,null,null,null,null,"isbn10"));
        assertEquals("isbn10",book.getIsbns());
        book = new BookFormatter(new Book("1","test title",null,null,null,null,null,null,null,"isbn13",""));
        assertEquals("isbn13",book.getIsbns());
        book = new BookFormatter(new Book("1","test title",null,null,null,null,null,null,null,"","isbn10"));
        assertEquals("isbn10",book.getIsbns());
    }
    @Test
    public void testGetFormatPublishedDate(){
        BookFormatter book = new BookFormatter(BookExampleTestData.getBook());
        assertEquals("2010-09-01",book.getFormatPublishedDate("yyyy-mm-dd"));
        book = new BookFormatter(new Book("1","test title",null,null,null,null,null,null,null,null,null));
        Assert.assertNull(book.getFormatPublishedDate("yyyy-mm-dd"));
        Calendar c = BookExampleTestData.getPublishedDate();
        c.set(Calendar.YEAR,2022);
        book = new BookFormatter(new Book("1","test title",null,null,null,null,null,null,c,null,null));
        assertEquals("2022-09-01",book.getFormatPublishedDate("yyyy-mm-dd"));
    }
    @Test
    public void testGetFullPublished(){
        BookFormatter book = new BookFormatter(BookExampleTestData.getBook());
        assertEquals("Apress, 2010-09-01",book.getFullPublished());
        book = new BookFormatter(new Book("1","test title",null,null,null,null,null,null,null,null,null));
        Assert.assertNull(book.getFullPublished());
        Calendar c = BookExampleTestData.getPublishedDate();
        c.set(Calendar.YEAR,2022);
        book = new BookFormatter(new Book("1","test title",null,null,null,null,null,null,c,null,null));
        assertEquals("2022-09-01",book.getFullPublished());
        book = new BookFormatter(new Book("1","test title",null,null,null,null,null,"Publisher",null,null,null));
        assertEquals("Publisher",book.getFullPublished());

    }

    @Test
    public void testGetAuthors(){
        BookFormatter book = new BookFormatter(BookExampleTestData.getBook());
        assertEquals("Marziah Karch",book.getAuthors());
        book = new BookFormatter(new Book("1","test title",null,null,null,null,null,null,null,null,null));
        Assert.assertNull(book.getAuthors());
        ArrayList<String> authors = new ArrayList<>();
        book = new BookFormatter(new Book("1","test title",null,authors,null,null,null,null,null,null,null));
        Assert.assertNull(book.getAuthors());
        authors.add("Author 1");
        authors.add("Author 2");
        book = new BookFormatter(new Book("1","test title",null,authors,null,null,null,null,null,null,null));
        assertEquals("Author 1, Author 2",book.getAuthors());
    }
}
