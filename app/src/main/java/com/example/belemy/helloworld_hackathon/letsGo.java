package com.example.belemy.helloworld_hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class letsGo extends AppCompatActivity {
    ArrayList<String> genres;
    int QuestionNumber;
    int time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_go);
        if(savedInstanceState!=null) {
            genres = savedInstanceState.getStringArrayList("genres");
            QuestionNumber = savedInstanceState.getInt("questions");
            time = savedInstanceState.getInt("time");
        }
    }
    public void nextActivity(View view){
        Bundle bundle= new Bundle();
        bundle.putInt("time",time);
        bundle.putInt("questions", QuestionNumber);
        bundle.putStringArrayList("genres",genres);
        Intent intent = new Intent(this, letsGo.class);
        startActivity(intent,bundle);
    }
}
