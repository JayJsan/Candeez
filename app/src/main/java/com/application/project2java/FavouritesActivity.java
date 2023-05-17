package com.application.project2java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.project2java.R;

public class FavouritesActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        BottomNavigationUtils.setupBottomNavigationView(this);
        BottomNavigationUtils.setCurrentItem(this);
    }
}