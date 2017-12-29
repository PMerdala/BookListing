package com.example.pmerdala.booklisting;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by PMerd_000 on 2017-12-28.
 */

public class BookFormatter {

    private final Book book;

    public BookFormatter(@NonNull Book book) {
        this.book = book;
    }
    public Book getBook() {
        return book;
    }

    public String getFullTitle(){
        StringBuilder sb = new StringBuilder(book.getTitle());
        if (book.getSubtitle()!=null && !book.getSubtitle().isEmpty()){
            sb.append(", ").append(book.getSubtitle());
        }
        return sb.toString();
    }

    public String getIsbns(){
        if ((book.getIsbn10()==null || book.getIsbn10().isEmpty()) && (book.getIsbn13()==null || book.getIsbn13().isEmpty()))
            return null;
        StringBuilder sb = new StringBuilder();
        if (book.getIsbn13()!=null && !book.getIsbn13().isEmpty()){
            sb.append(book.getIsbn13());
            if (book.getIsbn10()!=null && !book.getIsbn10().isEmpty()){
                sb.append(", ").append(book.getIsbn10());
            }
        }else{
            sb.append(book.getIsbn10());
        }
        return sb.toString();
    }


    public String getFullPublished(){
        if (book.getPublishedDate()==null && (book.getPublisher()==null || book.getPublisher().isEmpty())){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String formatDate = book.getPublishedDate();
        if (book.getPublisher()!=null && !book.getPublisher().isEmpty()){
            sb.append(book.getPublisher());
            if (formatDate!=null){
                sb.append(", ").append(formatDate);
            }
        }else{
            sb.append(formatDate);
        }
        return sb.toString();
    }

    public String getAuthors(){
        if (book.getAuthors()==null || book.getAuthors().isEmpty()){
            return null;
        }
        boolean addComma=false;
        StringBuilder sb = new StringBuilder();
        for(String author:book.getAuthors()){
            if (addComma) {
                sb.append(", ");
            }else{
                addComma = true;
            }
            sb.append(author);
        }
        return sb.toString();
    }

}
