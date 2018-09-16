package com.example.belemy.helloworld_hackathon;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
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
        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);
        setContentView(R.layout.activity_main);


    }

    public void nextActivity(View view) {
        ArrayList<String> genres = new ArrayList<>();
        for (int id : ids) {
            Switch genreSwitch = findViewById(id);
            if (genreSwitch.isChecked()) {
                genres.add(genreSwitch.getText().toString());
            }
        }
        // GameParameters.genres = genres;
        //System.out.println(genres.toString());
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("genres", genres);
        Intent intent = new Intent(this, Questions_Activity.class);
        if (genres != null) {
            startActivity(intent, bundle);
        }
    }

    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon = new ServiceConnection() {

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder) binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService() {
        bindService(new Intent(this, MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService() {
        if (mIsBound) {
            unbindService(Scon);
            mIsBound = false;
        }
    }
}