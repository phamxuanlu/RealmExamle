package com.framgia.lupx.realm.tracking;

import android.app.Application;

import io.realm.RealmConfiguration;

/**
 * Created by LUPX on 4/24/2016.
 */
public class App extends Application {
    public static RealmConfiguration configuration;

    @Override
    public void onCreate() {
        super.onCreate();
        configuration = new RealmConfiguration.Builder(getApplicationContext()).build();
    }

    public static RealmConfiguration getRealmConfig() {
        return configuration;
    }
}