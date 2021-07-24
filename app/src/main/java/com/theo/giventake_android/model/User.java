package com.theo.giventake_android.model;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    public static User getUser(JSONObject jsonObject) throws JSONException {
        String username = jsonObject.getString("name");
        String token = jsonObject.getString("token");
        User user = new User(username, token);

        return user;
    }

    private String username;
    private String token;

    public User (String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;

        if (o != null && o instanceof User) {
            User that = (User) o;
            if(this.username.equalsIgnoreCase(that.username)){
                result = true;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return this.username + "(" + this.username + ")";
    }
}
