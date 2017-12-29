package com.example.pmerdala.booklisting;

import android.support.annotation.NonNull;
import android.widget.ProgressBar;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

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
    private final String publishedDateString;
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

    private Calendar getCalendar(String dateString,String pattern){
        if (dateString==null) return null;
        DateFormat dataParser = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = dataParser.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c =null;
        if (date!=null) {
            c = Calendar.getInstance();
            c.setTime(date);
        }
        return c;
    }

    public Calendar getPublishedCalendar() {
        if (publishedDateString==null) return null;
        Calendar c = getCalendar(publishedDateString,"yyyy-mm-dd");
        if (c == null) c = getCalendar(publishedDateString,"yyyy");
        return c;
    }

    public String getPublishedDate() {
        return  publishedDateString;
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

    public Book(@NonNull String id, @NonNull String title, String subtitle, Collection<String> authors, String description, String imageUrl, String linkUrl, String publisher, String publishedDate, String isbn13, String isbn10) {
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
        this.publishedDateString = publishedDate;
        this.isbn13 = isbn13;
        this.isbn10 = isbn10;
    }

    @Override
    public int compareTo(@NonNull Book o) {
        return getId().compareTo(o.getId());
    }

}
