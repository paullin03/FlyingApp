package com.example.android.flyingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openCheckWeight(View v){
        startActivity(new Intent(MainActivity.this,CheckWeight.class));
    }

    public void openChecklist(View v){
        startActivity(new Intent(MainActivity.this,Checklist.class));
    }
}
