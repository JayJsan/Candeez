package com.application.project2java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.project2java.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBottomNavigationBar();
    }
    private void setupBottomNavigationBar() {
        Navigation navigationFragment = (Navigation) getSupportFragmentManager().findFragmentById(R.id.navigation_fragment);

        // Retrieve the BottomNavigationView from the Navigation fragment
        BottomNavigationView bottomNavigationView = navigationFragment.getView().findViewById(R.id.bottom_navigation);



        bottomNavigationView.setOnItemSelectedListener(item -> {
            Class<?> destinationClass = null;
            int itemId = item.getItemId();
            if (itemId == R.id.home){
                destinationClass = MainActivity.class;
            } else if (itemId == R.id.search){
                destinationClass = DetailsActivity.class;
            }
            if (destinationClass != null) {
                startActivity(new Intent(this, destinationClass));
                return true;
            }
            return false;
        });
    }

}