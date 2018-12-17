package com.example.qlangeveld.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class StartActivity extends AppCompatActivity implements TriviaRequest.Callback {

    private String amountOfQuestions = "10";
    private String difficulty;
    private String Type = "multiple";
    private TriviaRequest triviaRequest;
    private ArrayList<TriviaQuestion> ArrayListTriviaQuestions;
    private String UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        SeekBar seekBar = findViewById(R.id.questionBar);
        seekBar.setOnSeekBarChangeListener(new SeekBarClickListener());
    }

    @Override
    public void gotTrivia(ArrayList<TriviaQuestion> triviaQuestions) {
        this.ArrayListTriviaQuestions = triviaQuestions;


        Intent intent = new Intent(StartActivity.this, TriviaActivity.class);
        intent.putExtra("TriviaQuestions", ArrayListTriviaQuestions);
        intent.putExtra("UserName", UserName);

        startActivity(intent);
    }

    @Override
    public void gotTriviaError(String message) {

    }


    public void onDifficultyClicked(View view) {
        Log.d("haaaai", "onDifficultyClicked: ");

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radioButtoneasy:
                if (checked) {
                    difficulty = "easy";
                }
                break;

            case R.id.radioButtonmedium:
                if (checked) {
                    difficulty = "medium";
                }
                break;

            case R.id.radioButtonhard:
                if (checked) {
                    difficulty = "hard";
                }
                break;
        }
    }


    public void onTypeClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButtonMultipleChoice:
                if (checked) {
                    Type = "multiple";
                }
                break;

            case R.id.radioButtonTrueFalse:
                if (checked) {
                    Type = "boolean";
                }
                break;
        }
    }


    private class SeekBarClickListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            SeekBar mySeekBar = findViewById(R.id.questionBar);
            mySeekBar.setProgress(progress);

            TextView nrOfQuestions = findViewById(R.id.NrOfQuestion);
            String amountString = Integer.toString(progress);
            nrOfQuestions.setText(amountString);
            amountOfQuestions = amountString;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }


    public void onStartClicked(View view) {
        TextView Name = findViewById(R.id.name);
        UserName = Name.getText().toString();

        String url = "https://opentdb.com/api.php?";
        String finalUrl = url;

        SeekBar seekBar = findViewById(R.id.questionBar);
        if (seekBar.getProgress() > 0) {
            finalUrl = url + "amount=" + amountOfQuestions;
        }

        RadioGroup radioGroup = findViewById(R.id.radioGroupDif);
        if (radioGroup.getCheckedRadioButtonId() != -1) {
            finalUrl = finalUrl + "&difficulty=" + difficulty;
        }

        RadioGroup radioGroupType = findViewById(R.id.radioGroupType);
        if (radioGroupType.getCheckedRadioButtonId() != -1) {
            finalUrl = finalUrl + "&type=" + Type;
        }

        triviaRequest = new TriviaRequest(this);
        triviaRequest.getTrivia(this, finalUrl);

        Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show();
    }

}