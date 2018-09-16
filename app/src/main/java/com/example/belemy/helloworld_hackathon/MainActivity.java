package com.example.belemy.helloworld_hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import java.util.ArrayList;

import backend.GameParameters;

public class MainActivity extends AppCompatActivity {

    int[] ids = new int[]{R.id.citiesSwitch, R.id.sportsSwitch, R.id.moviesSwitch, R.id.historySwitch, R.id.scienceSwitch};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void nextActivity(View view){
        ArrayList<String> genres = new ArrayList<>();
        for (int id : ids){
            Switch genreSwitch = findViewById(id);
            if (genreSwitch.isChecked()){
                genres.add(genreSwitch.getText().toString());
            }
        }
       // GameParameters.genres = genres;
        //System.out.println(genres.toString());
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("genres", genres);
        Intent intent = new Intent(this, Questions_Activity.class);
        if(genres!=null) {
            startActivity(intent, bundle);
        }
    }
}
