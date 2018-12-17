package com.example.qlangeveld.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class HighScoreActivity extends AppCompatActivity implements HighScoreRequest.Callback {

    private ArrayList<HighScore> ArrayListHighScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        HighScoreRequest highScoreRequest = new HighScoreRequest(this);
        highScoreRequest.getHighScore(this);

        Log.d("ff checken", "onCreate: ");
    }


    public void onPlayAgainClicked(View view) {
        Intent intent = new Intent(HighScoreActivity.this, StartActivity.class);
        startActivity(intent);
        finish();
    }


    private void checkHighScores() {
        TextView player1 = findViewById(R.id.player1);
        TextView player2 = findViewById(R.id.player2);
        TextView player3 = findViewById(R.id.player3);

        TextView score1 = findViewById(R.id.score1);
        TextView score2 = findViewById(R.id.score2);
        TextView score3 = findViewById(R.id.score3);

        HighScore highScore1 = ArrayListHighScores.get(0);
        HighScore highScore2 = ArrayListHighScores.get(1);
        HighScore highScore3 = ArrayListHighScores.get(2);

        player1.setText(highScore1.getName());
        player2.setText(highScore2.getName());
        player3.setText(highScore3.getName());

        int scoreEEN = highScore1.getScore();
        int scoreTWEE = highScore2.getScore();
        int scoreDRIE = highScore3.getScore();

        String ScoreEEN = Integer.toString(scoreEEN);
        String ScoreTWEE = Integer.toString(scoreTWEE);
        String SCoreDRIE = Integer.toString(scoreDRIE);

        score1.setText(ScoreEEN);
        score2.setText(ScoreTWEE);
        score3.setText(SCoreDRIE);
    }


    @Override
    public void gotHighScore(ArrayList<HighScore> HighScores) {
        this.ArrayListHighScores = HighScores;
        Log.d("de scores zijn binnen!", "gotHighScore: " + ArrayListHighScores.size());
        checkHighScores();
    }

    @Override
    public void gotHighScoreError(String message) {
    }

}
