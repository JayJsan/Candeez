package com.application.project2java;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ImageUtils {
    // Define an interface to handle the bitmap retrieval result
    public interface BitmapCallback {
        void onBitmapLoaded(Bitmap bitmap);

        void onBitmapFailed(Exception e);
    }

    //https://stackoverflow.com/questions/3870638/how-to-use-setimageuri-on-android/
    public static void getImageBitmapAsync(String url, final BitmapCallback callback) {
        Handler handler = new Handler(Looper.getMainLooper());
        Thread thread = new Thread(() -> {
            Bitmap bitmap = null;
            try {
                URL aURL = new URL(url);
                URLConnection conn = aURL.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                bitmap = BitmapFactory.decodeStream(bis);
                bis.close();
                is.close();
            } catch (IOException e) {
                handler.post(() -> callback.onBitmapFailed(e));
                return;
            }

            Bitmap finalBitmap = bitmap;
            handler.post(() -> callback.onBitmapLoaded(finalBitmap));
        });
        thread.start();
    }
}

