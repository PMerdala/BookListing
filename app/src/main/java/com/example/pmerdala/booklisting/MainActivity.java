package com.example.pmerdala.booklisting;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Iterable<Book>>{

    private final static String QUERY_STRING_KEY = "queryString";
    TextView searchSrcText;
    TextView statusTextView;
    BookListAdaper bookListAdaper;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            Intent settingIntent = new Intent(this,SettingsActivity.class);
            startActivity(settingIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    ProgressBar progressBar;
    private final static int HIDE_STATUS=0;
    private final static int INIT_STATUS=1;
    private final static int LOADING_STATUS=2;
    private final static int NON_INTERNET_STATUS=3;
    private final static int LOADER_ID = 0;

    private String getGoogleUrlPreference(){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String url = sharedPrefs.getString(getString(R.string.settings_google_api_base_address_key),getString(R.string.settings_google_api_base_address_default));
        return url;
    }

    @Override
    public Loader<Iterable<Book>> onCreateLoader(int i, Bundle bundle) {
        if (bundle==null) {
            return null;
        }
        String queryString = bundle.getString(QUERY_STRING_KEY);
        if (queryString==null){
            return null;
        }
        String url = getGoogleUrlPreference();
        return BookFactory.getBookListLoader(this,url,queryString);
    }

    @Override
    public void onLoadFinished(Loader<Iterable<Book>> loader, Iterable<Book> book) {
        prepareStatusMessage(HIDE_STATUS);
        bookListAdaper.clear();
        if(book!=null && book.iterator().hasNext()){
            for (Book b:book){
                bookListAdaper.add(b);
            }
        }
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<Iterable<Book>> loader) {
        bookListAdaper.clear();
        prepareStatusMessage(INIT_STATUS);
        progressBar.setVisibility(View.GONE);
    }

    private void prepareStatusMessage(int statusId){
        String msg=null;
        switch (statusId){
            case INIT_STATUS: msg = "Wprowadź zapytanie conajmniej 4 znaki i naciśnij szukaj";break;
            case LOADING_STATUS: msg = "Ladowanie danych...";break;
            case NON_INTERNET_STATUS: msg = "Brak połączenia z internetem";break;
        }
        statusTextView.setText(msg);
        if (statusId==HIDE_STATUS){
            statusTextView.setVisibility(View.GONE);
        } else{
            statusTextView.setVisibility(View.VISIBLE);
        }
    }

    protected void prepareListView(){
        bookListAdaper = new BookListAdaper(this,new ArrayList<Book>());
        ListView listView = findViewById(R.id.book_list);
        listView.setAdapter(bookListAdaper);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        listView.setEmptyView(statusTextView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchSrcText = findViewById(R.id.search_src_text);
        statusTextView = findViewById(R.id.status_text_view);
        prepareStatusMessage(INIT_STATUS);
        progressBar = findViewById(R.id.loading_progress_bar);
        progressBar.setVisibility(View.GONE);
        prepareListView();
        getLoaderManager().initLoader(LOADER_ID,null,this);
        if (!NetworkUtil.isNetworkConnection(this)){
            bookListAdaper.clear();
            Toast.makeText(this, "brak połączenia z internetem", Toast.LENGTH_SHORT).show();
            prepareStatusMessage(NON_INTERNET_STATUS);

        }
    }

    public void search(View view) {
        String queryString = searchSrcText.getText().toString();
        if (queryString==null || queryString.isEmpty() || queryString.length()<3) {
            bookListAdaper.clear();
            Toast.makeText(this, "wprowadź conajmniej 4 znaki", Toast.LENGTH_SHORT).show();
            prepareStatusMessage(INIT_STATUS);
        }else if (!NetworkUtil.isNetworkConnection(this)){
            bookListAdaper.clear();
            Toast.makeText(this, "brak połączenia z internetem", Toast.LENGTH_SHORT).show();
            prepareStatusMessage(NON_INTERNET_STATUS);

        }else{
            Bundle bundle = new Bundle();
            bundle.putString(QUERY_STRING_KEY,queryString);
            getLoaderManager().destroyLoader(LOADER_ID);
            getLoaderManager().initLoader(LOADER_ID,bundle,this).forceLoad();
            progressBar.setVisibility(View.VISIBLE);
        }
    }
}
