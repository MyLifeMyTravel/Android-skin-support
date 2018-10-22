package com.example.skin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import skin.support.SkinCompatManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_load_skin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinCompatManager.getInstance().loadSkin("purple.skin", CustomAssetsCardLoader.SKIN_LOADER_STRATEGY_SDCARD);
            }
        });

        findViewById(R.id.btn_reset_skin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinCompatManager.getInstance().restoreDefaultTheme();
            }
        });

        findViewById(R.id.btn_load_extra_res).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinCompatManager.getInstance().loadExtraRes("string.skin");
            }
        });

        findViewById(R.id.btn_reset_extra_res).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinCompatManager.getInstance().loadExtraRes("");
            }
        });
    }
}
