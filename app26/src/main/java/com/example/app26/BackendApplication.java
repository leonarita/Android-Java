package com.example.app26;

import android.app.Application;

import com.backendless.Backendless;

class BackendApplication extends Application {

    public static final String APPLICATION_ID = "F17D40CA-EACA-6295-FF3B-3F6822AEE400";
    public static final String API_KEY = "EA2755BD-C187-49A8-A9BD-443972EC0089";

    public static final String SERVER_URL = "https://api.backendless.com";

    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.setUrl(SERVER_URL);
        Backendless.initApp(getApplicationContext(), APPLICATION_ID, API_KEY);
    }
}
