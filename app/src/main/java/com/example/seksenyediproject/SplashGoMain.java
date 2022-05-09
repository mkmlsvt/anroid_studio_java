package com.example.seksenyediproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashGoMain extends AppCompatActivity {
    Handler hndlr2 = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_go_main);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        hndlr2.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashGoMain.this,MainPage.class);
                startActivity(i);
                finish();
            }
        },4000);
    }
}