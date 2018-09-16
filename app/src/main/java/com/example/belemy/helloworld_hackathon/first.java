package com.example.belemy.helloworld_hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import java.util.ArrayList;

public class first extends AppCompatActivity {
    public static String pak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        pak = getPackageName();
    }
    public void nextActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }//nextactivity
}
