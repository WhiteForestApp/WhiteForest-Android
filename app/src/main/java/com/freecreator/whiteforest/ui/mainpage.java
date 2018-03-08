package com.freecreator.whiteforest.ui;

/**
 * Created by niko on 2018/3/8.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.freecreator.whiteforest.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class mainpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        UI_init();


    }

    private void UI_init() {
        ImageView ImageView_background = (ImageView)findViewById(R.id.ImageView_background);
        ImageView_background.setImageResource(R.drawable.background);
    }
}
