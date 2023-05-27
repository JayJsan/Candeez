package com.application.project2java;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import com.example.project2java.R;
import com.google.android.material.imageview.ShapeableImageView;

public class ImageObjectFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        String uri = args.getString("URI");
        ShapeableImageView image = view.findViewById(R.id.swiper_display_image);
        ResourceUtils.getImageBitmapAsync(uri, new ResourceUtils.BitmapCallback() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap) {
                image.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e) {
                image.setImageResource(R.drawable.baseline_image_not_supported_24);
                DrawableCompat.setTint(
                        DrawableCompat.wrap(image.getDrawable()),
                        ContextCompat.getColor(App.getAppContext(), R.color.md_theme_light_primaryContainer)
                );
                Log.d("DEBUG", e.getLocalizedMessage());
            }
        });
    }

}
