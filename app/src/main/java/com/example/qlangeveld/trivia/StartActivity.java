package com.example.qlangeveld.trivia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    private String amountOfQuestions = "10";
    private String difficulty;
    private String Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        SeekBar seekBar = findViewById(R.id.questionBar);
        seekBar.setOnSeekBarChangeListener(new SeekBarClickListener());
    }


    public void onDifficultyClicked(View view) {
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

        Intent intent = new Intent(StartActivity.this, TriviaActivity.class);
        intent.putExtra("url", finalUrl);
        startActivity(intent);
    }


}