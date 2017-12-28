package com.example.pmerdala.booklisting;

import android.content.Context;

/**
 * Created by merdala on 2017-12-28.
 */

public class BookFactory {
    private static final String GOOGLE_BASE_URL = "https://www.googleapis.com/books/v1/volumes";
    private final BookDataProvider bookDataProvider;
    private final static BookFactory INSTANCE = new BookFactory();

    private BookFactory(){
        bookDataProvider = new GoogleBookDataProvider(new RestServiceRawDataProvider(), new GoogleJsonBookParser(), GOOGLE_BASE_URL, 100);
    }

    public static  BookDataProvider getBookDataProvider(String name){
        return  INSTANCE.bookDataProvider;
    }

    public static BookListLoader getBookListLoader(Context context,String queryString){
        return new BookListLoader(BookFactory.getBookDataProvider("Google"),context,queryString);
    }
}
