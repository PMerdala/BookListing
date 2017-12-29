package com.example.pmerdala.booklisting;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by PMerd_000 on 2017-12-28.
 */

public class BookListAdaper extends ArrayAdapter<Book> {

    final HashMap<String,Bitmap> images = new HashMap<>();

    public BookListAdaper(Context context,List<Book> books) {
        super(context, 0,books);
    }

    @Override
    public void clear() {
        super.clear();
        images.clear();
    }

    @NonNull
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
        ImageView image = view.findViewById(R.id.book_image);
        prepareImage(book,image);
    }

    private void prepareImage(Book book, ImageView imageView){
        if (book.getImageUrl()==null){
            imageView.setImageResource(0);
            return;
        }
        Bitmap imageBitmap = images.get(book.getId());
        if (imageBitmap!=null){
            imageView.setImageBitmap(imageBitmap);
            return;
        }
        new DownloadImageTask(imageView,images,book.getId()).execute(book.getImageUrl());
    }
}
