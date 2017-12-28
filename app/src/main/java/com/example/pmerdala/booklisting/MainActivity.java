package com.example.pmerdala.booklisting;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Iterable<Book>>{

    private final static String QUERY_STRING_KEY = "queryString";
    TextView search_src_text;


    @Override
    public Loader<Iterable<Book>> onCreateLoader(int i, Bundle bundle) {
        String queryString = bundle.getString(QUERY_STRING_KEY);
        return BookFactory.getBookListLoader(this,queryString);
    }

    @Override
    public void onLoadFinished(Loader<Iterable<Book>> loader, Iterable<Book> book) {

    }

    @Override
    public void onLoaderReset(Loader<Iterable<Book>> loader) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_src_text = findViewById(R.id.search_src_text);
    }

    public void search(View view) {
    }
}
