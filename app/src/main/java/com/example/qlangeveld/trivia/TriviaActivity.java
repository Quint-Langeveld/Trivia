package com.example.qlangeveld.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class TriviaActivity extends AppCompatActivity {

    private ArrayList<TriviaQuestion> ArrayListTriviaQuestions;
    private GameManager gameManager;
    public TriviaQuestion currentTriviaQuestion;
    private String clickedAnswer;
    private String UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        Intent intent = getIntent();
        ArrayListTriviaQuestions = (ArrayList<TriviaQuestion>) intent.getSerializableExtra("TriviaQuestions");
        UserName = (String) intent.getSerializableExtra("UserName");
        gameManager = new GameManager(ArrayListTriviaQuestions);

        checkQuestion();
    }


    private void checkQuestion() {

        currentTriviaQuestion = gameManager.getNextQuestion();

        TextView Nr = findViewById(R.id.textViewQuestion);
        TextView Text = findViewById(R.id.TriviaText);

        TextView questionA = findViewById(R.id.textViewA);
        TextView questionB = findViewById(R.id.textViewB);
        TextView questionC = findViewById(R.id.textViewC);
        TextView questionD = findViewById(R.id.textViewD);

        // create an Arraylist of all answers
        ArrayList<String> allAnswers = new ArrayList<>();
        allAnswers.add(currentTriviaQuestion.getCorrect_answer());
        ArrayList<String> incorrectAnswers = currentTriviaQuestion.getIncorrect_answers();
        for (int i=0; i<incorrectAnswers.size(); i++) {
            String nextAnswer = incorrectAnswers.get(i);
            allAnswers.add(nextAnswer);
        }

        // and put them in a random order in the textViews
        int answerA = random(0,4);
        Log.d("answerA", "checkQuestion: " + answerA);

        int answerB = random(0,4);
        while (answerB == answerA) {
            answerB = random(0,4);
        }
        Log.d("answerB", "checkQuestion: " + answerB);

        int answerC = random(0,4);
        while (answerC == answerA || answerC == answerB ) {
            answerC = random(0,4);
        }
        Log.d("answerC", "checkQuestion: " + answerC);

        int answerD = random(0,4);
        while (answerD == answerA || answerD == answerB || answerD == answerC) {
            answerD = random(0,4);
        }
        Log.d("answerD", "checkQuestion: " + answerD);

        // set TextViews
        String nr = Integer.toString(gameManager.getCounter());
        String finalNr = "Question " + nr;
        Nr.setText(finalNr);
        Text.setText(currentTriviaQuestion.getQuestion());

        questionA.setText(allAnswers.get(answerA));
        questionB.setText(allAnswers.get(answerB));
        questionC.setText(allAnswers.get(answerC));
        questionD.setText(allAnswers.get(answerD));
    }


    private static int random(int min, int max) {
        return (int) (min + (Math.random() * (max - min)));
    }


    public void onAnswerClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radioButtonA:
                if (checked) {
                    TextView answerA = findViewById(R.id.textViewA);
                    clickedAnswer = answerA.getText().toString();
                    Log.d("on A clicked", "onAnswerClicked: ");
                    Log.d("answer", "onAnswerClicked: " + clickedAnswer);
                }
                break;

            case R.id.radioButtonB:
                if (checked) {
                    TextView answerB = findViewById(R.id.textViewB);
                    clickedAnswer = answerB.getText().toString();
                    Log.d("on B clicked", "onAnswerClicked: ");
                    Log.d("answer", "onAnswerClicked: " + clickedAnswer);
                }
                break;

            case R.id.radioButtonC:
                if (checked) {
                    TextView answerC = findViewById(R.id.textViewC);
                    clickedAnswer = answerC.getText().toString();
                    Log.d("on C clicked", "onAnswerClicked: ");
                    Log.d("answer", "onAnswerClicked: " + clickedAnswer);
                }
                break;

            case R.id.radioButtonD:
                if (checked) {
                    TextView answerD = findViewById(R.id.textViewD);
                    clickedAnswer = answerD.getText().toString();
                    Log.d("on D clicked", "onAnswerClicked: ");
                    Log.d("answer", "onAnswerClicked: " + clickedAnswer);
                }
                break;
        }
    }


    public void onNextClicked(View view) {
        // when no questions are left
        if (gameManager.isNextQuestPossible()) {

            if (clickedAnswer.isEmpty()) {
                gameManager.putAnswer("none given");
            } else {
                gameManager.putAnswer(clickedAnswer);
            }

            // hier een put request naar de server!!!!

            int highScore = gameManager.getHighScore();
            Intent intent = new Intent(TriviaActivity.this, HighScoreActivity.class);
            intent.putExtra("name", UserName);
            intent.putExtra("score", highScore);

            startActivity(intent);
            finish();

        } else {

            if (clickedAnswer.isEmpty()) {
                gameManager.putAnswer("none given");
                checkQuestion();
            } else {
                gameManager.putAnswer(clickedAnswer);
                checkQuestion();
            }

            RadioButton buttonA = findViewById(R.id.radioButtonA);
            RadioButton buttonB = findViewById(R.id.radioButtonB);
            RadioButton buttonC = findViewById(R.id.radioButtonC);
            RadioButton buttonD = findViewById(R.id.radioButtonD);

            buttonA.setChecked(false);
            buttonB.setChecked(false);
            buttonC.setChecked(false);
            buttonD.setChecked(false);

        }
    }
}
