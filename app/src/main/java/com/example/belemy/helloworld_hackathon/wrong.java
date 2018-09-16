package com.example.belemy.helloworld_hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class wrong extends AppCompatActivity {
    ArrayList<String> genres;
    int QuestionNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            genres = bundle.getStringArrayList("genres");
            QuestionNumber = bundle.getInt("questions");
            TextView answer;
            answer=findViewById(R.id.button6);
            answer.setText(bundle.getString("answer"));
        }//if
    }
    public void nextActivity(View view){

        if(QuestionNumber>0){
            Intent intent = new Intent(this, Questions.class);
            intent.putExtra("genres",genres);
            intent.putExtra("questions", QuestionNumber);
           // intent.putExtra("time", time);
            //correct++;
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, Score.class);
            //intent.putExtra("genres",genres);
            //intent.putExtra("questions", QuestionNumber);
            //intent.putExtra("time", time);
            //correct++;
            startActivity(intent);
        }
    }//nextactivity
}
