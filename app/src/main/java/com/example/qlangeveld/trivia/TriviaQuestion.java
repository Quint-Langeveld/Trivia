package com.example.qlangeveld.trivia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class TriviaQuestion implements Serializable {
    private String category, difficulty, type, question, correct_answer;
    private ArrayList<Dictionary> incorrect_answers;

    public TriviaQuestion(String category, String difficulty, String type, String question, String correct_answer, ArrayList<Dictionary> incorrect_answers) {
        this.category = category;
        this.difficulty = difficulty;
        this.type = type;
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
    }
}
