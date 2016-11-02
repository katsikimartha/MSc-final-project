package com.example.marthakat.sirarthurconandoyleinportsmouth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
//This is a Splash Screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int timeout = 5000; // makes the activity visible for 5 seconds

        Timer timer = new Timer();

        //when the time is over we move to the MainMenu activity
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent homepage = new Intent(MainActivity.this, MainMenu.class);
                startActivity(homepage);
            }
        },timeout);
    }
}
