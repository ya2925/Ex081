package com.example.ex081;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;

/**
 * @author Yanir Aton
 * @version 1.0
 * @since 21/05/2023
 * This activity will be the main activity of the game
 * It will contain the timer and the buttons of the players
 * It will send the scores to the winner activity
 */
public class MainActivity extends AppCompatActivity {

    Timer timer;
    int counter_player1 = 0, counter_player2 = 0;
    int current_time = 10;
    Button player1,player2;
    TextView timer1,timer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the views from the xml
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        timer1 = findViewById(R.id.timer1);
        timer2 = findViewById(R.id.timer2);

        // create a long click listener for player 1
        player1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                counter_player1 += 2;
                Log.i("PSCORE", "LONG PRESS " + "Player 1: " + counter_player1 + " Player 2: " + counter_player2); // Log to the console
                return true;
            }
        });

        // create a short click listener for player 2
        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter_player2++;
                Log.i("PSCORE", "SHORT PRESS " + "Player 1: " + counter_player1 + " Player 2: " + counter_player2); // Log to the console
            }
        });
    }

    /**
     * This function will be called when you get back from the winner activity
     * It will reset the scores and the timer
     */
    @Override
    protected void onResume() {
        super.onResume();
        counter_player1 = 0;
        counter_player2 = 0;
        current_time = 10;
        startTimer();
    }

    /**
     * This function will be called when you go to the winner activity
     * It will cancel the timer
     */
    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }

    /**
     * This function will create a new timer and will start it
     * The timer will count down from 10 to 0 at a fixed rate of 1 second
     * on each tick the timer will update the textview
     * when the timer will reach 0 it will call the goTiWinner function
     */
    public void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new java.util.TimerTask() {
            @Override
            public void run() {
                // Update the textview and the current time
                current_time--;
                timer1.setText(String.valueOf(current_time));
                timer2.setText(String.valueOf(current_time));
                if(current_time == 0){
                    goTiWinner();
                }
            }
        }, 0, 1000);
    }

    /**
     * This function will be called when the timer will reach 0
     * It will open the winner activity and send the scores
     */
    public void goTiWinner(){
        Intent intent = new Intent(this, winner.class);
        intent.putExtra("player1", counter_player1);
        intent.putExtra("player2", counter_player2);

        startActivity(intent);
    }

}