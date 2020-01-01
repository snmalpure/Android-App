package com.example.pictinsights;


import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;




public class SplashScreen extends AppCompatActivity {
private static int Splash_time=4000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent homeintent=new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(homeintent);
                finish();
            }
        },Splash_time);

    }
}
