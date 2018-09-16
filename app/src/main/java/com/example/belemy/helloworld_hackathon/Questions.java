package com.example.belemy.helloworld_hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import java.util.Random;

import org.w3c.dom.Text;

import java.util.ArrayList;

import backend.TriviaGame;
import backend.TriviaQuestion;

public class Questions extends AppCompatActivity {
    ArrayList<String> genres;
    int QuestionNumber;
    int time;
    TriviaQuestion Q;
    int correctId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            genres = bundle.getStringArrayList("genres");
            QuestionNumber = bundle.getInt("questions");
            time = bundle.getInt("time");
            setQuestion();
        }//if
    }//oncreate

    public void setQuestion (){
        TriviaGame game= TriviaGame.getInstance(genres, getResources());
        Q=game.getQuestion(QuestionNumber);
        TextView ques;
        ques=(TextView)findViewById(R.id.textView2);
        ques.setText(Q.getText());
        RadioButton ans,wrongans;
        Random rand = new Random();
        int randint=rand.nextInt(4);
        switch(randint){
            case 0: ans=(RadioButton)findViewById(R.id.radioButton9);
                ans.setText(Q.getAns());
                correctId=R.id.radioButton9;
                break;
            case 1: ans=(RadioButton)findViewById(R.id.radioButton10);
                ans.setText(Q.getAns());
                correctId=R.id.radioButton10;
                break;
            case 2: ans=(RadioButton)findViewById(R.id.radioButton11);
                correctId=R.id.radioButton11;
                ans.setText(Q.getAns());
                break;
            case 3: ans=(RadioButton)findViewById(R.id.radioButton12);
                ans.setText(Q.getAns());
                correctId=R.id.radioButton12;
                break;
            default: ans=(RadioButton)findViewById(R.id.radioButton9);
                ans.setText(Q.getAns());
        }//switch
        if(randint==0){
            wrongans=(RadioButton)findViewById(R.id.radioButton10);
            wrongans.setText(Q.getWrongAnswers().get(0));
            wrongans=(RadioButton)findViewById(R.id.radioButton11);
            wrongans.setText(Q.getWrongAnswers().get(1));
            wrongans=(RadioButton)findViewById(R.id.radioButton12);
            wrongans.setText(Q.getWrongAnswers().get(2));
        }else if(randint==1){
            wrongans=(RadioButton)findViewById(R.id.radioButton9);
            wrongans.setText(Q.getWrongAnswers().get(0));
            wrongans=(RadioButton)findViewById(R.id.radioButton11);
            wrongans.setText(Q.getWrongAnswers().get(1));
            wrongans=(RadioButton)findViewById(R.id.radioButton12);
            wrongans.setText(Q.getWrongAnswers().get(2));
        }else if(randint==2){
            wrongans=(RadioButton)findViewById(R.id.radioButton10);
            wrongans.setText(Q.getWrongAnswers().get(0));
            wrongans=(RadioButton)findViewById(R.id.radioButton9);
            wrongans.setText(Q.getWrongAnswers().get(1));
            wrongans=(RadioButton)findViewById(R.id.radioButton12);
            wrongans.setText(Q.getWrongAnswers().get(2));
        }else if(randint==3){
            wrongans=(RadioButton)findViewById(R.id.radioButton10);
            wrongans.setText(Q.getWrongAnswers().get(0));
            wrongans=(RadioButton)findViewById(R.id.radioButton11);
            wrongans.setText(Q.getWrongAnswers().get(1));
            wrongans=(RadioButton)findViewById(R.id.radioButton9);
            wrongans.setText(Q.getWrongAnswers().get(2));
        }//else
    }//setquestion

    public void nextActivity(View view){
        if(QuestionNumber>0) {
            if(view.getId()==correctId) {
                Intent intent = new Intent(this, correct.class);
                intent.putExtra("genres",genres);
                intent.putExtra("questions", QuestionNumber--);
                intent.putExtra("time", time);
                startActivity(intent);
            }else{
                Intent intent = new Intent(this, wrong.class);
                intent.putExtra("genres",genres);
                intent.putExtra("questions", QuestionNumber--);
                intent.putExtra("time", time);
                startActivity(intent);
            }//else
        }else{
            Intent intent = new Intent(this, Score.class);
            startActivity(intent);
        }//else
    }//nextactivity
}//class
