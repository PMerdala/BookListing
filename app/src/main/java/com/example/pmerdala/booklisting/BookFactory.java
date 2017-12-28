package com.example.pmerdala.booklisting;

/**
 * Created by merdala on 2017-12-28.
 */

public class BookFactory {
    public static final String GOOGLE_BASE_URL = "https://www.googleapis.com/books/v1/volumes";
    public static  BookDataProvider getBookDataProvider(String name){
        return new GoogleBookDataProvider(new RestServiceRawDataProvider(),new GoogleJsonBookParser(),GOOGLE_BASE_URL,100);
    }
}
