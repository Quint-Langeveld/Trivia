package com.example.qlangeveld.trivia;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;
import javax.sql.StatementEvent;

public class TriviaRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context myContext;
    private Callback activity;
    private String myUrl;

    public interface Callback {
        void gotTrivia(ArrayList<TriviaQuestion> menu);
        void gotTriviaError(String message);
    }


    public TriviaRequest (Context myContext) {
        this.myContext = myContext;
    }


    public void getTrivia(final Callback activity, String url) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(myContext);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray JSONArrayQuestions = new JSONArray();
        try {
            JSONArrayQuestions = response.getJSONArray("results");
        } catch (JSONException e) {
            String message = e.getMessage();
            activity.gotTriviaError(message);
        }

        Log.d("hoeveel JSONArrays", "onResponse: " + JSONArrayQuestions.length());

        for (int i=0; i < JSONArrayQuestions.length(); i++) {
            JSONObject newTriviaQuestion;

            try {
                newTriviaQuestion = JSONArrayQuestions.getJSONObject(i);

                String category = newTriviaQuestion.getString("category");
                String type = newTriviaQuestion.getString("type");
                String difficulty = newTriviaQuestion.getString("difficulty");
                String


            }
            catch (JSONException e) {
                String message = e.getMessage();
                activity.gotTriviaError(message);
            }

        }
    }
}



