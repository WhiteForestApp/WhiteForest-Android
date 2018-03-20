package com.freecreator.whiteforest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.ui.dialogs.dialogAddDesire;
import com.freecreator.whiteforest.ui.dialogs.dialogRegLogin;

/**
 * Created by niko on 2018/3/15.
 */

public class MainloginActivity  extends AppCompatActivity {

    private dialogRegLogin dialogRL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlogin);

        UI_init();
    }

    private void UI_init() {

        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);

        dialogRL = new dialogRegLogin(this, (RelativeLayout) findViewById(R.id.parent) );
        dialogRL.show();
    }

}
