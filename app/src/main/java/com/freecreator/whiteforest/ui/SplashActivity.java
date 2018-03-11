package com.freecreator.whiteforest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.freecreator.whiteforest.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);

        ImageView imgView = (ImageView)findViewById(R.id.splash_iv);
        imgView.setImageResource(R.drawable.splash);

        new Thread(){
            @Override
            public void run(){
                try{  sleep(3000);
                }
                catch (InterruptedException e){ }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this, DesireActivity.class));
                        SplashActivity.this.finish();
                    }
                });
            }

        }.start();
    }
}
