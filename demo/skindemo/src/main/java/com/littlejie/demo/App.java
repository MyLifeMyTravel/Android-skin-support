package com.littlejie.demo;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import skin.support.SkinCompatManager;
import skin.support.app.SkinAppCompatViewInflater;

/**
 * Copyright (c) 2018, Bongmi
 * All rights reserved
 * Author: lishengjie@bongmi.com.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SkinCompatManager.withoutActivity(this)
                .addInflater(new SkinAppCompatViewInflater())
                .setSkinStatusBarColorEnable(true)
                .setSkinWindowBackgroundEnable(true)
                .loadSkin();

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
