package com.example.belemy.helloworld_hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class wrong extends AppCompatActivity {
    ArrayList<String> genres;
    int QuestionNumber;
    int time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            genres = bundle.getStringArrayList("genres");
            QuestionNumber = bundle.getInt("questions");
            time = bundle.getInt("time");
        }//if
    }
    public void nextActivity(View view){

        Intent intent = new Intent(this, Questions.class);
        intent.putExtra("genres",genres);
        intent.putExtra("questions", QuestionNumber);
        intent.putExtra("time", time);
        startActivity(intent);
    }//nextactivity
}
