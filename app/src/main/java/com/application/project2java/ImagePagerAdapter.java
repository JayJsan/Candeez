package com.application.project2java;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class ImagePagerAdapter extends FragmentStatePagerAdapter {
    private List<String> imageUris;

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

