package com.example.qlangeveld.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class TriviaActivity extends AppCompatActivity {

    private ArrayList<TriviaQuestion> ArrayListTriviaQuestions;
    private int ArrayCouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        Intent intent = new Intent();
        ArrayListTriviaQuestions = (ArrayList<TriviaQuestion>) intent.getSerializableExtra("TriviaQuestions");
        ArrayCouter = 0;

        checkLayout();

    }


    private void checkLayout() {
        if (ArrayCouter == 0) {
            TextView triviaText = findViewById(R.id.TriviaText);
            String setText = "Are you ready?";
            triviaText.setText(setText);
        } else {
            checkAnswer();
        }
    }


    private void checkAnswer() {
        // Print correct or incorrect to the textView!
    }




}
