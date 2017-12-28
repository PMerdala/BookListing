package com.example.pmerdala.booklisting;

import junit.framework.Assert;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

import static org.junit.Assert.assertEquals;


/**
 * Created by merdala on 2017-12-28.
 */

public class BookTest {


    @Test
    public void testCreateAndGet(){
        Book book = BookExampleTestData.getBook();
        ComparisonComparableIterator<String> iteratable = new ComparisonComparableIterator<>();
        assertEquals(0,iteratable.compare(BookExampleTestData.getAuthors(),book.getAuthors()));
        assertEquals(BookExampleTestData.getAuthor(),book.getAuthors().toArray()[0]);
        assertEquals(BookExampleTestData.getDescription(),book.getDescription());
        assertEquals(BookExampleTestData.getId(),book.getId());
        assertEquals(BookExampleTestData.getImageUrl(),book.getImageUrl());
        assertEquals(BookExampleTestData.getIsbn10(),book.getIsbn10());
        assertEquals(BookExampleTestData.getIsbn13(),book.getIsbn13());
        assertEquals(BookExampleTestData.getLinkUrl(),book.getLinkUrl());
        assertEquals(BookExampleTestData.getPublishedDate(),book.getPublishedDate());
        assertEquals(BookExampleTestData.getPublisher(),book.getPublisher());
        assertEquals(BookExampleTestData.getTitle(),book.getTitle());
        assertEquals(BookExampleTestData.getSubtitle(),book.getSubtitle());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testChangeAuthorsCollection(){
        Book book = BookExampleTestData.getBook();
        Collection<String> authors = book.getAuthors();
        authors.add("Test Autor");
    }

    @Test
    public void testChangePublishedDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Calendar cPublished = BookExampleTestData.getPublishedDate();
        Book book = new Book(BookExampleTestData.getId(),BookExampleTestData.getTitle(),BookExampleTestData.getSubtitle(),BookExampleTestData.getAuthors(),BookExampleTestData.getDescription(),BookExampleTestData.getImageUrl(),BookExampleTestData.getLinkUrl(),BookExampleTestData.getPublisher(),cPublished,BookExampleTestData.getIsbn13(),BookExampleTestData.getIsbn10());
        cPublished.set(Calendar.YEAR,2222);
        assertEquals(BookExampleTestData.getPublishedDateString(),dateFormat.format(book.getPublishedDate().getTime()));
        cPublished = book.getPublishedDate();
        cPublished.set(Calendar.YEAR,2221);
        assertEquals(BookExampleTestData.getPublishedDateString(),dateFormat.format(book.getPublishedDate().getTime()));
    }

    @Test
    public void testHashCode() {
        Book book = BookExampleTestData.getBook();
        assertEquals(BookExampleTestData.getId().hashCode(),book.hashCode());
    }
    @Test
    public void testEquals() {
        Book bookExpect = BookExampleTestData.getBook();
        Book book = BookExampleTestData.getBook();
        Assert.assertNotSame(bookExpect,book);
        assertEquals(bookExpect,book);
        Calendar cPublished = BookExampleTestData.getPublishedDate();
        cPublished.set(Calendar.YEAR,2222);
        Collection<String> authors = BookExampleTestData.getAuthors();
        authors.add("Test Author");
        book = new Book(BookExampleTestData.getId(),BookExampleTestData.getTitle()+"A",BookExampleTestData.getSubtitle(),BookExampleTestData.getAuthors(),BookExampleTestData.getDescription(),BookExampleTestData.getImageUrl(),BookExampleTestData.getLinkUrl(),BookExampleTestData.getPublisher(),BookExampleTestData.getPublishedDate(),BookExampleTestData.getIsbn13(),BookExampleTestData.getIsbn10());
        assertEquals(bookExpect,book);
        book = new Book(BookExampleTestData.getId(),BookExampleTestData.getTitle(),BookExampleTestData.getSubtitle()+"A",BookExampleTestData.getAuthors(),BookExampleTestData.getDescription(),BookExampleTestData.getImageUrl(),BookExampleTestData.getLinkUrl(),BookExampleTestData.getPublisher(),BookExampleTestData.getPublishedDate(),BookExampleTestData.getIsbn13(),BookExampleTestData.getIsbn10());
        assertEquals(bookExpect,book);
        book = new Book(BookExampleTestData.getId(),BookExampleTestData.getTitle(),BookExampleTestData.getSubtitle(),authors,BookExampleTestData.getDescription(),BookExampleTestData.getImageUrl(),BookExampleTestData.getLinkUrl(),BookExampleTestData.getPublisher(),BookExampleTestData.getPublishedDate(),BookExampleTestData.getIsbn13(),BookExampleTestData.getIsbn10());
        assertEquals(bookExpect,book);
        book = new Book(BookExampleTestData.getId(),BookExampleTestData.getTitle(),BookExampleTestData.getSubtitle(),BookExampleTestData.getAuthors(),BookExampleTestData.getDescription()+"A",BookExampleTestData.getImageUrl(),BookExampleTestData.getLinkUrl(),BookExampleTestData.getPublisher(),BookExampleTestData.getPublishedDate(),BookExampleTestData.getIsbn13(),BookExampleTestData.getIsbn10());
        assertEquals(bookExpect,book);
        book = new Book(BookExampleTestData.getId(),BookExampleTestData.getTitle(),BookExampleTestData.getSubtitle(),BookExampleTestData.getAuthors(),BookExampleTestData.getDescription(),BookExampleTestData.getImageUrl()+"A",BookExampleTestData.getLinkUrl(),BookExampleTestData.getPublisher(),BookExampleTestData.getPublishedDate(),BookExampleTestData.getIsbn13(),BookExampleTestData.getIsbn10());
        assertEquals(bookExpect,book);
        book = new Book(BookExampleTestData.getId(),BookExampleTestData.getTitle(),BookExampleTestData.getSubtitle(),BookExampleTestData.getAuthors(),BookExampleTestData.getDescription(),BookExampleTestData.getImageUrl(),BookExampleTestData.getLinkUrl()+"A",BookExampleTestData.getPublisher(),BookExampleTestData.getPublishedDate(),BookExampleTestData.getIsbn13(),BookExampleTestData.getIsbn10());
        assertEquals(bookExpect,book);
        book = new Book(BookExampleTestData.getId(),BookExampleTestData.getTitle(),BookExampleTestData.getSubtitle(),BookExampleTestData.getAuthors(),BookExampleTestData.getDescription(),BookExampleTestData.getImageUrl(),BookExampleTestData.getLinkUrl(),BookExampleTestData.getPublisher()+"A",BookExampleTestData.getPublishedDate(),BookExampleTestData.getIsbn13(),BookExampleTestData.getIsbn10());
        assertEquals(bookExpect,book);
        book = new Book(BookExampleTestData.getId(),BookExampleTestData.getTitle(),BookExampleTestData.getSubtitle(),BookExampleTestData.getAuthors(),BookExampleTestData.getDescription(),BookExampleTestData.getImageUrl(),BookExampleTestData.getLinkUrl(),BookExampleTestData.getPublisher(),cPublished,BookExampleTestData.getIsbn13(),BookExampleTestData.getIsbn10());
        assertEquals(bookExpect,book);
        book = new Book(BookExampleTestData.getId(),BookExampleTestData.getTitle(),BookExampleTestData.getSubtitle(),BookExampleTestData.getAuthors(),BookExampleTestData.getDescription(),BookExampleTestData.getImageUrl(),BookExampleTestData.getLinkUrl(),BookExampleTestData.getPublisher(),BookExampleTestData.getPublishedDate(),BookExampleTestData.getIsbn13()+"A",BookExampleTestData.getIsbn10());
        assertEquals(bookExpect,book);
        book = new Book(BookExampleTestData.getId(),BookExampleTestData.getTitle(),BookExampleTestData.getSubtitle(),BookExampleTestData.getAuthors(),BookExampleTestData.getDescription(),BookExampleTestData.getImageUrl(),BookExampleTestData.getLinkUrl(),BookExampleTestData.getPublisher(),BookExampleTestData.getPublishedDate(),BookExampleTestData.getIsbn13(),BookExampleTestData.getIsbn10()+"A");
        assertEquals(bookExpect,book);
        book = new Book(BookExampleTestData.getId()+"A",BookExampleTestData.getTitle(),BookExampleTestData.getSubtitle(),BookExampleTestData.getAuthors(),BookExampleTestData.getDescription(),BookExampleTestData.getImageUrl(),BookExampleTestData.getLinkUrl(),BookExampleTestData.getPublisher(),BookExampleTestData.getPublishedDate(),BookExampleTestData.getIsbn13(),BookExampleTestData.getIsbn10());
        Assert.assertFalse("Expect false Object not Equals",bookExpect.equals(book));
        Assert.assertFalse("Expect false Object is not Equals to null",book.equals(null));
        Assert.assertFalse("Expect false Book not equals to String",book.equals("Book"));

    }
    @Test
    public void testToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(BookExampleTestData.getId()).append(" - ").append(BookExampleTestData.getTitle()).append(", ").append(BookExampleTestData.getSubtitle()).append(", ").append(BookExampleTestData.getAuthors().toString());
        Book book = BookExampleTestData.getBook();
        assertEquals(sb.toString(),book.toString());
    }
}