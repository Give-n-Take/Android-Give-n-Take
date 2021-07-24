package com.theo.giventake_android.model.api;

import android.app.Application;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.theo.giventake_android.model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class WebApi implements API {


    public static final String BASE_URL = "https://give-n-takee.herokuapp.com/";

    private final Application mApplication;
    private RequestQueue mRequestQueue;

    public WebApi(Application application) {
        mApplication = application;
        mRequestQueue = Volley.newRequestQueue(application);
    }

    public void login(String username, String password) {
        String url = BASE_URL + "signin";
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);


            Response.Listener<JSONObject> successListener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        User user = User.getUser(response);
                        Toast.makeText(mApplication, "Utilisateur " + user.getUsername() + " est connecté", Toast.LENGTH_LONG).show();

                    }

                    catch (JSONException e) {
                        Toast.makeText(mApplication, "Erreur JSON", Toast.LENGTH_LONG).show();
                    }

                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(mApplication, "Erreur login", Toast.LENGTH_LONG).show();


                }
            };


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, successListener, errorListener);
            mRequestQueue.add(request);

        } catch (JSONException e) {
            Toast.makeText(mApplication, "Erreur JSON", Toast.LENGTH_LONG).show();


        }

    }

    public void create(String username, String password) {
        String url = BASE_URL + "signup";
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);


            Response.Listener<JSONObject> successListener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(mApplication, "Succès create", Toast.LENGTH_LONG).show();

                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(mApplication, "Erreur create", Toast.LENGTH_LONG).show();


                }
            };


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, successListener, errorListener);
            mRequestQueue.add(request);

        } catch (JSONException e) {
            Toast.makeText(mApplication, "Erreur JSON", Toast.LENGTH_LONG).show();


        }

    }
}
