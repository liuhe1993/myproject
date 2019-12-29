package com.eric.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.eric.newsfeed.R;
import com.eric.newsfeed.ui.fragment.GankFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(new GankFragment(), "gank").commit();
    }
}
