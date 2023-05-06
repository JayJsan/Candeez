package com.application.project2java;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.project2java.R;

public class ListActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        BottomNavigationUtils.setupBottomNavigationView(this);
        BottomNavigationUtils.setCurrentItem(this);
    }
}