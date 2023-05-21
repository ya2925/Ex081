package com.example.ex081;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * @author Yanir Aton
 * @version 1.0
 * @since 21/05/2023
 *
 * This activity will be the winner activity of the game
 * It will contain the scores of the players
 * It will announce the winner
 * It will have a button to start a new game
 */
public class winner extends AppCompatActivity {
    TextView winnerAnnouncement, player1Score, player2Score,playerSign;
    Button newGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        // get the views from the xml
        winnerAnnouncement = findViewById(R.id.winnerAnnouncement);
        player1Score = findViewById(R.id.player1Score);
        player2Score = findViewById(R.id.player2Score);
        playerSign = findViewById(R.id.playerSign);
        newGame = findViewById(R.id.newGame);


        // get the scores from the intent
        Intent intent = getIntent();
        int player1 = intent.getIntExtra("player1",0);
        int player2 = intent.getIntExtra("player2",0);

        // check who won
        if (player1 > player2) {
            winnerAnnouncement.setText("Player 1 won!");
            // change color to green
            setActivityBackgroundColor(Color.parseColor("#36AE7C"));
            newGame.setBackgroundColor(Color.parseColor("#36AE7C"));
            playerSign.setText(">");

        } else if (player2 > player1) {
            winnerAnnouncement.setText("Player 2 won!");
            // change color to blue
            setActivityBackgroundColor(Color.parseColor("#187498"));
            newGame.setBackgroundColor(Color.parseColor("#187498"));
            playerSign.setText("<");
        } else {
            winnerAnnouncement.setText("It's a tie!");
            // change color to red
            setActivityBackgroundColor(Color.parseColor("#EB5353"));
            newGame.setBackgroundColor(Color.parseColor("#EB5353"));
            playerSign.setText("=");
        }
        player1Score.setText(player1 + "");
        player2Score.setText(player2 + "");

        newGame.setOnClickListener(v -> {
            // go back to main activity
            finish();
        });
    }

    /**
     * set the background color of the activity
     * @param color the color to set the background to
     */
    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
}