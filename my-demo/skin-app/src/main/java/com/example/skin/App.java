package com.example.skin;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

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
                .addStrategy(new CustomNoneLoader())
                .addStrategy(new CustomAssetsCardLoader())          // 自定义加载策略，指定SDCard路径
                .addInflater(new SkinAppCompatViewInflater())   // 基础控件换肤
                .load();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
