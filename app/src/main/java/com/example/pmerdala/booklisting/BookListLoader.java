package com.example.pmerdala.booklisting;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by PMerd_000 on 2017-12-28.
 */

public class BookListLoader extends AsyncTaskLoader<Iterable<Book>> {
    final String queryString;
    final BookDataProvider bookDataProvider;

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
//        forceLoad();
    }

    @Override
    public Iterable<Book> loadInBackground() {
        try {
            return bookDataProvider.fetchBookList(queryString);
        } catch (BookDataProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BookListLoader(BookDataProvider bookDataProvider,Context context,String queryString) {
        super(context);
        this.queryString = queryString;
        this.bookDataProvider = bookDataProvider;
    }
}
