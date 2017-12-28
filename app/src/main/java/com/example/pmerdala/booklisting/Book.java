package com.example.pmerdala.booklisting;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by merdala on 2017-12-27.
 */

public class Book implements Serializable, Comparable<Book> {
    private final String id;

    private final String title;
    private final String subtitle;
    private final Collection<String> authors;
    private final String description;
    private final String imageUrl;
    private final String linkUrl;
    private final String publisher;
    private final Calendar publishedDate;
    private final String isbn13;
    private final String isbn10;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Collection<String> getAuthors() {
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
        return  publishedDate ==null ? null : (Calendar) publishedDate.clone();
    }

    public String getIsbn13() {
        return isbn13;
    }

    public String getIsbn10() {
        return isbn10;
    }


    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null){
            return false;
        }
        if (!(obj instanceof Book)){
            return false;
        }
        Book book = (Book)obj;
        return getId().equals(book.getId());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getId()).append(" - ").append(getTitle()).append(", ").append(getSubtitle()).append(", ").append(getAuthors().toString());
        return sb.toString();
    }

    public Book(@NonNull String id, @NonNull String title, String subtitle, Collection<String> authors, String description, String imageUrl, String linkUrl, String publisher, Calendar publishedDate, String isbn13, String isbn10) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        if (authors!=null) {
            this.authors = Collections.unmodifiableCollection(authors);
        }else{
            this.authors = null;
        }
        this.description = description;
        this.imageUrl = imageUrl;
        this.linkUrl = linkUrl;
        this.publisher = publisher;
        if(publishedDate!=null) {
            this.publishedDate = (Calendar) publishedDate.clone();
        }else{
            this.publishedDate = null;
        }
        this.isbn13 = isbn13;
        this.isbn10 = isbn10;
    }

    @Override
    public int compareTo(@NonNull Book o) {
        return getId().compareTo(o.getId());
    }

}
