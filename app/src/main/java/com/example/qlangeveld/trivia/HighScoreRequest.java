package com.example.qlangeveld.trivia;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HighScoreRequest implements Response.Listener<JSONArray>, Response.ErrorListener  {

    private Context myContext;
    private Callback activity;
    private ArrayList<HighScore> ArrayListHighScores;


    public interface Callback {
        void gotHighScore(ArrayList<HighScore> HighScores);
        void gotHighScoreError(String message);
    }


    public HighScoreRequest(Context myContext) {
        this.myContext = myContext;
    }


    public void getHighScore(Callback activity) {
        this.activity = activity;
        Log.d("hier wordt de url belangrijk", "getHighScore: ");

        RequestQueue queue = Volley.newRequestQueue(myContext);
        String url = "http://ide50-quintlang.cs50.io:8080/list";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,  this, this);

//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,this, this);

        queue.add(jsonArrayRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        String message = error.getMessage();
        activity.gotHighScoreError(message);
    }


    @Override
    public void onResponse(JSONArray response) {
        JSONArray JSONOArrayScores = response;

        for (int i=0; i<JSONOArrayScores.length(); i++) {
            JSONObject newHighScore;
            String name;
            int score;

            try {
                newHighScore = response.getJSONObject(i);
                name = newHighScore.getString("name");
                score = newHighScore.getInt("score");

                // add a new HighScores to the ArrayListHighScores
                HighScore currentHighScore = new HighScore(name, score);
                ArrayListHighScores.add(currentHighScore);

            } catch (JSONException e) {
                String message = e.getMessage();
                activity.gotHighScoreError(message);
            }
        }
        Log.d("zit er wat in de arrayList", "onResponse: " + ArrayListHighScores.size());
        activity.gotHighScore(ArrayListHighScores);
    }
}
