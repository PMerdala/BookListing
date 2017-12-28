package com.example.pmerdala.booklisting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by PMerd_000 on 2017-12-28.
 */

public class BookListAdaper extends ArrayAdapter<Book> {
    public BookListAdaper(Context context,List<Book> books) {
        super(context, 0,books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.book_item_list,parent,false);
        }
        Book book = getItem(position);
        makeItemView(listItemView,book);
        return listItemView;
    }

    protected void makeItemView(View view, Book book){
        BookFormatter bookFormatter = new BookFormatter(book);
        TextView textView = view.findViewById(R.id.book_title);
        textView.setText(bookFormatter.getFullTitle());
        textView = view.findViewById(R.id.book_authors);
        textView.setText(bookFormatter.getAuthors());
        textView = view.findViewById(R.id.book_publish_info);
        textView.setText(bookFormatter.getFullPublished());
        textView = view.findViewById(R.id.book_isbn);
        textView.setText(bookFormatter.getIsbns());
    }
}
