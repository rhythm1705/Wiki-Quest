package com.example.belemy.helloworld_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

public class areYouReady extends AppCompatActivity {
    ArrayList<String> genres;
    int QuestionNumber;
    //int time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areyou_ready);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            genres = bundle.getStringArrayList("genres");
            QuestionNumber = bundle.getInt("questions");
            //time = bundle.getInt("time");
        }//if
    }//oncreate
    public void nextActivity(View view){
        Intent intent = new Intent(this, letsGo.class);
        intent.putExtra("genres",genres);
        intent.putExtra("questions", QuestionNumber);
        //intent.putExtra("time", time);
        startActivity(intent);
    }//nextactivity
}//class
