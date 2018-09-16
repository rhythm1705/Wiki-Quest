package com.example.belemy.helloworld_hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import backend.TriviaGame;

public class Score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        TextView score;
        score=(TextView)findViewById(R.id.textView6);
        score.setText("Your score  is " + correct.correct);
    }
    public void reset(){
        correct.correct = 0;
        TriviaGame.resetInstance();
    }

    public void nextActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        reset();
        startActivity(intent);
    }//nextactivity
}
