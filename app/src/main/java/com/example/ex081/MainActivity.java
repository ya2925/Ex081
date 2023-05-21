package com.example.ex081;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    Timer timer;
    int counter_player1 = 0, counter_player2 = 0;
    int current_time = 0;
    Button player1,player2;
    TextView timer1,timer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        timer1 = findViewById(R.id.timer1);
        timer2 = findViewById(R.id.timer2);
        //add an event listener to the button of player 1 for long press that will add 1 to the counter of player 1
        //add an event listener to the button of player 2 for short press that will add 1 to the counter of player 2
        player1.setOnLongClickListener(v -> {
            counter_player1++;
            return true;
        });
        player2.setOnClickListener(v -> {
            counter_player2++;
        });

        timer = new Timer();

    }
    // create on resume function that will reset the game and the timer
    @Override
    protected void onResume() {
        super.onResume();
        counter_player1 = 0;
        counter_player2 = 0;
        current_time = 0;
        startTimer();

    }

    // create on pause function that will stop the timer
    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }

    // create on stop function that will start the timer
    public void startTimer() {
        timer.scheduleAtFixedRate(new java.util.TimerTask() {
            @Override
            public void run() {
                // here you can update your textview with the time left
                current_time++;
                timer1.setText(String.valueOf(current_time));
                timer2.setText(String.valueOf(current_time));
                if(current_time == 10){

                    Intent intent = new Intent(this, credit.class);
                    intent.putExtra("winner", result);
                    startActivity(intent);
                }
            }
        }, 0, 1000);
    }

}