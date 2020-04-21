package com.example.belemy.helloworld_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

public class time_Activity extends AppCompatActivity {
    ArrayList<String> genres;
    int QuestionNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            genres = bundle.getStringArrayList("genres");
            QuestionNumber = bundle.getInt("questions");
        }//if
    }//oncreate
    public void nextActivity(View view){
        int time;
        switch(view.getId()) {
            case R.id.radioButton:
                time=3;
                break;
            case R.id.radioButton2:
                time=5;
                break;
            case R.id.radioButton3:
                time=10;
                break;
            case R.id.radioButton4:
                time=15;
                break;
            default:
                time=10;
        }//switch
        Intent intent = new Intent(this, areYouReady.class);
        intent.putExtra("genres",genres);
        intent.putExtra("questions", QuestionNumber);
        intent.putExtra("time", time);
        startActivity(intent);
    }//nextactivity
}//class
