package com.freecreator.whiteforest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.ui.utils.AdjustSize;

/**
 * Created by niko on 2018/3/10.
 */

public class TaskActivity extends AppCompatActivity {

    private ImageView ImageView_icon_task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        UI_init();
    }

    private void UI_init() {

        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);

        ImageView ImageView_background = (ImageView)findViewById(R.id.ImageView_background);
        ImageView_background.setImageResource(R.drawable.background);

        ImageView_icon_task = (ImageView)findViewById(R.id.ImageView_icon_task);
        ImageView_icon_task.setImageResource(R.drawable.task);

        ImageView ImageView_btn_add = (ImageView) findViewById(R.id.ImageView_btn_add);
        ImageView_btn_add.setImageResource(R.drawable.btn_add);
    }


    private void UI_adjust(){
        // 尺寸自适应 根据图片的宽高 来调整view高度 [宽度不调整]
        AdjustSize.adjustHeight(ImageView_icon_task, AdjustSize.getImageWidthHeight(this, R.drawable.task));
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);

        UI_adjust();
    }
}
