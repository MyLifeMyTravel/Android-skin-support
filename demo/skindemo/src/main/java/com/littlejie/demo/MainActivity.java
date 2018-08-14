package com.littlejie.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import skin.support.SkinCompatManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_replace_skin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SkinCompatManager.getInstance().loadSkin("night.skin", SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS);
            }
        });

        findViewById(R.id.btn_reload_skin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SkinCompatManager.getInstance().restoreDefaultTheme();
            }
        });

        findViewById(R.id.btn_open_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }
}
