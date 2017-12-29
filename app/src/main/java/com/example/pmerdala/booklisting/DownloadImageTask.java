package com.example.pmerdala.booklisting;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by merdala on 2017-12-29.
 */

public class DownloadImageTask extends AsyncTask<String,Void,Bitmap> {
    final ImageView imageView;
    final HashMap<String,Bitmap> images;
    final String id;

    public DownloadImageTask(ImageView imageView,HashMap<String,Bitmap> images,String id) {
        this.imageView = imageView;
        this.images = images;
        this.id = id;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap!=null) {
            images.put(id, bitmap);
        }
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        if (strings==null || strings.length==0)
            return null;
        Bitmap bitmap =null;
        try {
            URL url = new URL(strings[0]);
            InputStream in = url.openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
