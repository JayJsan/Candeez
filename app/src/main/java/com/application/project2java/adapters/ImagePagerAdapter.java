package com.application.project2java.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.application.project2java.fragments.ImageObjectFragment;

import java.util.List;

public class ImagePagerAdapter extends FragmentStatePagerAdapter {
    private final List<String> imageUris;

    public ImagePagerAdapter(FragmentManager fm, List<String> imageUris) {
        super(fm);
        this.imageUris = imageUris;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new ImageObjectFragment();
        Bundle args = new Bundle();
        args.putString("URI", imageUris.get(position));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return imageUris.size();
    }
}

