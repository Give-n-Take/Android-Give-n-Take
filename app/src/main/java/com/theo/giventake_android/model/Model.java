package com.theo.giventake_android.model;

import android.app.Application;

import com.theo.giventake_android.model.api.API;
import com.theo.giventake_android.model.api.WebApi;

public class Model {

    private static Model sInstance = null;
    private final API mApi;

    public static Model getInstance(Application application){
        if (sInstance == null){
            sInstance = new Model(application);
        }
        return sInstance;
    }


    private final Application mApplication;

    private Model(Application application) {
        mApplication = application;
        mApi = new WebApi(mApplication);

    }

    public Application getApplication() {
        return mApplication;
    }

    public void login(String username, String password){
        mApi.login(username, password);

    }

    public void create(String username, String password){
        mApi.create(username, password);

    }
}
