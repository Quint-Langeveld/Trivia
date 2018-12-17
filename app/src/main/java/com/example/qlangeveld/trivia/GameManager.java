package com.example.qlangeveld.trivia;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class GameManager implements Serializable {

    private ArrayList<TriviaQuestion> ArrayListTriviaQuestions;
    private TriviaQuestion currentTriviaQuestion;
    private int Counter;
    private ArrayList<String> answers = new ArrayList<>();
    private int score;

    public GameManager(ArrayList<TriviaQuestion> arrayListTriviaQuestions) {
        ArrayListTriviaQuestions = arrayListTriviaQuestions;
        Log.d("arrayList length", "GameManager: " + ArrayListTriviaQuestions.size());
        Counter = 0;
    }

    public int getCounter() {
        return Counter;
    }

    public Boolean isNextQuestPossible() {
        if (Counter == ArrayListTriviaQuestions.size()) {
            return true;
        } else {
            return false;
        }
    }

    public TriviaQuestion getNextQuestion() {
        currentTriviaQuestion = ArrayListTriviaQuestions.get(Counter);
        Counter++;
        return currentTriviaQuestion;

    }

    public void putAnswer(String answer) {
        Log.d("answer", "putAnswer: " + answer);
        answers.add(answer);

        if (answer.equals(currentTriviaQuestion.getCorrect_answer())) {
            if (currentTriviaQuestion.getDifficulty().equals("easy")) {
                score += 1;
            } else if (currentTriviaQuestion.getDifficulty().equals("medium")) {
                    score += 2;
            } else if (currentTriviaQuestion.getDifficulty().equals("hard")) {
                score += 3;
            }
        }
    }

    public int getHighScore() {
        return score;
    }
}
