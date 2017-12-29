package com.example.pmerdala.booklisting;

import android.content.Context;

/**
 * Created by merdala on 2017-12-28.
 */

public class BookFactory {
//    private static final String GOOGLE_BASE_URL = "https://www.googleapis.com/books/v1/volumes";

    private BookFactory(){
    }

    public static  BookDataProvider getBookDataProvider(String url){
        return  new GoogleBookDataProvider(new RestServiceRawDataProvider(), new GoogleJsonBookParser(), url, GoogleBookDataProvider.MAX_FETCH);
    }

    public static BookListLoader getBookListLoader(Context context,String url,String queryString){
        return new BookListLoader(BookFactory.getBookDataProvider(url),context,queryString);
    }
}
