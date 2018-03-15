package com.freecreator.whiteforest.ui;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.ui.utils.AdjustSize;

import static com.freecreator.whiteforest.common.Debug._Debug;

/**
 * Created by niko on 2018/3/14.
 */

public class StoryActivity extends AppCompatActivity {

    private ImageView ImageView_story_header = null;
    private ImageView ImageView_story_bottom = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);


        UI_init();
    }

    private void UI_init() {

        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);

        ImageView_story_header = (ImageView)findViewById(R.id.ImageView_story_header);
        ImageView_story_bottom = (ImageView)findViewById(R.id.ImageView_story_bottom);

    }

    private void UI_adjust(){
        // 尺寸自适应 根据图片的宽高 来调整view高度 [宽度不调整]
        AdjustSize.adjustHeight(ImageView_story_header, AdjustSize.getImageWidthHeight(this, R.drawable.story_header));
        AdjustSize.adjustHeight(ImageView_story_bottom, AdjustSize.getImageWidthHeight(this, R.drawable.story_bottom));
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);

        UI_adjust();
    }
}
