package com.application.project2java.utils;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;

import androidx.core.content.ContextCompat;

import com.application.project2java.App;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * ResourceUtils is a helper class that provides methods to modify any resource related elements.
 */
public class ResourceUtils {
    //https://stackoverflow.com/questions/3870638/how-to-use-setimageuri-on-android/

    /**
     * Loads the image according to the link
     * @param url The link to the image
     * @param callback Bitmap interface for retrieving results
     */
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

    /**
     * Returns the current color state list of the specified resource.
     * @param resourceId The id of the resource.
     * @return The color state list of the resource passed in.
     */
    public static ColorStateList getColorStateList(int resourceId) {
        return ContextCompat.getColorStateList(App.getAppContext(), resourceId);
    }

    /**
     * An interface to hande the bitmap retrieval result.
     */
    public interface BitmapCallback {
        void onBitmapLoaded(Bitmap bitmap);

        void onBitmapFailed(Exception e);
    }
}

