package com.devism.decisio;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, LoginRegisterActivity.class);
                startActivity(i);
            }
        }, 500);
    }
}