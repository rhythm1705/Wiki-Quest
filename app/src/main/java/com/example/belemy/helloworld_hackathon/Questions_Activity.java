package com.example.belemy.helloworld_hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import java.util.ArrayList;

import backend.GameParameters;

public class Questions_Activity extends AppCompatActivity {

    int[] ids = new int[]{R.id.Question3, R.id.Question5, R.id.Question10, R.id.Question15};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_);
        //savedInstanceState.getStringArrayList("genres");
    }
    public void nextActivity(View view){
        ArrayList<String> QuestionNumber = new ArrayList<>();
        for (int id : ids){
            Switch Question = findViewById(id);
            if (Question.isChecked()){
                GameParameters.num_Questions=Integer.parseInt((String.valueOf(id)));
                System.out.println(GameParameters.num_Questions);
                break;
            }
        }
        Bundle bundlequestions = new Bundle();
        bundlequestions.putStringArrayList("QuestionNumber", QuestionNumber);

        Intent intent = new Intent(this, TimeperQActivity.class);
        startActivity(intent, bundlequestions);
    }

}
