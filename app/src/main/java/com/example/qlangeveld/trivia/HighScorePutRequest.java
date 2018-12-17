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

import javax.security.auth.callback.Callback;

public class HighScorePutRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context myContext;
    private Callback myActivity;
    private JSONObject myObject = new JSONObject();

    public interface Callback {
        void gotPutHighScore(ArrayList<HighScore> HighScores);
        void gotPutHighScoreError(String message);
    }

    public HighScorePutRequest(Context context) {
        this.myContext = context;
    }


    public void getPutHighScore(Callback activity, JSONObject newHighScore) {
        this.myActivity = activity;

        RequestQueue queue = Volley.newRequestQueue(myContext);

        String url = "http://ide50-quintlang.cs50.io:8080/list";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, newHighScore, this, this);

        queue.add(jsonObjectRequest);
    }


    public void setScore(int score) {
        try {
            myObject.put("score", score);
        }
        catch (JSONException e) {
            String message = e.getMessage();
            myActivity.gotPutHighScoreError(message);
        }
    }

    public void setName(String name) {
        try {
            myObject.put("name", name);
        }
        catch (JSONException e) {
            String message = e.getMessage();
            myActivity.gotPutHighScoreError(message);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
