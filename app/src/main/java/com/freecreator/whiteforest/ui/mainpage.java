package com.freecreator.whiteforest.ui;

/**
 * Created by niko on 2018/3/8.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.ui.utils.AdjustSize;

public class mainpage extends AppCompatActivity {

    private ImageView ImageView_background = null;
    private ImageView ImageView_story_banner = null;
    private ImageView ImageView_character = null;
    private ImageView ImageView_task = null;
    private ImageView ImageView_desire = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        UI_init();
        setListeners();
    }

    private void setListeners() {
        ImageView_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mainpage.this , TaskActivity.class));
            }
        });
        ImageView_desire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mainpage.this , DesireActivity.class));
            }
        });
        ImageView_story_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mainpage.this , StoryActivity.class));
            }
        });
        ImageView_character.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mainpage.this , PersonalCenterActivity.class));
            }
        });
    }

    private void UI_init() {

        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);

        ImageView_background = (ImageView)findViewById(R.id.ImageView_background);
        ImageView_background.setImageResource(R.drawable.background);

        ImageView_story_banner = (ImageView)findViewById(R.id.ImageView_story_banner);
        ImageView_story_banner.setImageResource(R.drawable.banner);

        // 建立阴影
        //ImageView shadow_story_banner = (ImageView)findViewById(R.id.shadow_story_banner);
        //shadow_story_banner.setImageResource(R.drawable.shadow);

        ImageView_character = (ImageView) findViewById(R.id.ImageView_character);
        ImageView_character.setImageResource(R.drawable.character);

        ImageView_task = (ImageView) findViewById(R.id.ImageView_task);
        ImageView_task.setImageResource(R.drawable.task);

        ImageView_desire = (ImageView) findViewById(R.id.ImageView_desire);
        ImageView_desire.setImageResource(R.drawable.desire);

    }

    private void UI_adjust(){
        // 尺寸自适应 根据图片的宽高 来调整view高度 [宽度不调整]
        AdjustSize.adjustHeight(ImageView_story_banner, AdjustSize.getImageWidthHeight(this, R.drawable.banner));
        AdjustSize.adjustHeight(ImageView_character, AdjustSize.getImageWidthHeight(this, R.drawable.character));
        AdjustSize.adjustHeight(ImageView_desire, AdjustSize.getImageWidthHeight(this, R.drawable.desire));
        AdjustSize.adjustHeight(ImageView_task, AdjustSize.getImageWidthHeight(this, R.drawable.task));
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);

        UI_adjust();
    }
}
