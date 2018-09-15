package com.example.belemy.helloworld_hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int[] ids = new int[]{R.id.citiesSwitch, R.id.sportsSwitch, R.id.moviesSwitch, R.id.historySwitch, R.id.scienceSwitch};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //savedInstanceState.getStringArrayList("genres");
    }
    public void nextActivity(View view){
        ArrayList<String> genres = new ArrayList<>();
        for (int id : ids){
            Switch genreSwitch = findViewById(id);
            if (genreSwitch.isChecked()){
                genres.add(genreSwitch.getText().toString());
            }
        }
        Bundle bundlegenres = new Bundle();
        bundlegenres.putStringArrayList("genres", genres);

        Intent intent = new Intent(this, Questions_Activity.class);
        startActivity(intent, bundlegenres);
    }
}
