package com.sytniky.networkdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.ivImage);
        new DownloadTask().execute();
    }

    private class DownloadTask extends AsyncTask<Void, Void, Bitmap> {

        private final String IMAGE_URL = "https://pixabay.com/static/uploads/photo/2015/11/15/20/21/cat-1044750_1280.jpg";

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL url = new URL(IMAGE_URL);
                URLConnection connection = url.openConnection();
                connection.connect();
                BufferedInputStream bufferedInputStream
                        = new BufferedInputStream(connection.getInputStream());
                Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }
}
