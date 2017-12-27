package com.example.pmerdala.booklisting;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by merdala on 2017-12-27.
 */

public class Book implements Serializable {
    final String id;
    final String title;
    final String subtitle;
    final Iterable<String> authors;
    final String description;
    final String imageUrl;
    final String linkUrl;
    final String publisher;
    final Calendar publishedDate;
    final String isbn13;
    final String isbn10;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Iterable<String> getAuthors() {
        return authors;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public String getPublisher() {
        return publisher;
    }

    public Calendar getPublishedDate() {
        return publishedDate;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public Book(String id, String title, String subtitle, Iterable<String> authors, String description, String imageUrl, String linkUrl, String publisher, Calendar publishedDate, String isbn13, String isbn10) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.authors = authors;
        this.description = description;
        this.imageUrl = imageUrl;
        this.linkUrl = linkUrl;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.isbn13 = isbn13;
        this.isbn10 = isbn10;
    }
}
