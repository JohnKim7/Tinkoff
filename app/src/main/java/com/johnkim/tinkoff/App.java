package com.johnkim.tinkoff;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by john on 29.04.17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
