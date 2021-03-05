package com.example.applicationpharma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import static com.example.applicationpharma.R.id.fragment_map;

public class Voirmap extends AppCompatActivity {

    private Fragment mapsActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voirmap);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        this.mapsActivity = fragmentManager.findFragmentById(R.id.fragment_map);
    }
}