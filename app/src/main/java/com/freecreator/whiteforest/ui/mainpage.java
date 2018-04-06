package com.freecreator.whiteforest.ui;

/**
 * Created by niko on 2018/3/8.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.ui.utils.AdjustSize;
import com.freecreator.whiteforest.ui.views.ScrollViewSmooth;

public class mainpage extends AppCompatActivity {

    private ImageView ImageView_background = null;
    private ImageView ImageView_story_banner = null;
    private ImageView ImageView_character = null;
    private ImageView ImageView_task = null;
    private ImageView ImageView_desire = null;
    private ScrollViewSmooth ScrollViewSmooth_main = null;

    private float mPosX;
    private float mPosY;
    private float mCurPosX;
    private float mCurPosY;
    private int sx;
    private int sy;
    private int osx;
    private int osy;


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
                overridePendingTransition(R.anim.activity_anim_story_in, R.anim.activity_anim_story_out);
            }
        });
        ImageView_character.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainpage.this , PersonalCenterActivity.class));

            }
        });

        ScrollViewSmooth_main.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                sx = scrollX;
                sy = scrollY;
                osx = oldScrollX;
                osy = oldScrollY;
                Log.d("jyky","sx:"+scrollX+" sy:" + scrollY + " osx:" + oldScrollX + " osy:"+oldScrollY);
            }
        });

        ScrollViewSmooth_main.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mPosX = event.getX();
                        mPosY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurPosX = event.getX();
                        mCurPosY = event.getY();

                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("jyyyy",""+mCurPosX+"  666  " + mCurPosY);

                        if (mCurPosY - mPosY > 0 && (Math.abs(mCurPosY - mPosY) > 50)) {
                            //向下滑動
                            if(sy == 0){
                                Toast.makeText(getApplicationContext(), "!!!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(mainpage.this , StoryActivity.class));
                                overridePendingTransition(R.anim.activity_anim_story_in, R.anim.activity_anim_story_out);
                            }
                            Log.d("jy","向下");

                        } else if (mCurPosY - mPosY < 0 && (Math.abs(mCurPosY - mPosY) > 25)) {
                            //向上滑动

                            Log.d("jy","向上");
                        }
                        if (mCurPosX - mPosX > 0 && (Math.abs(mCurPosX - mPosX) > 25)) {
                            //向左滑動
                            Log.d("jy","向右");

                        } else if (mCurPosX - mPosX < 0 && (Math.abs(mCurPosX - mPosX) > 25)) {
                            //向右滑动

                            Log.d("jy","向左");
                        }
                        break;
                }

                return false;
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
        ImageView_story_banner.setImageResource(R.drawable.sc_button);
        //ImageView_story_banner.setImageResource(R.drawable.banner);

        // 建立阴影
        //ImageView shadow_story_banner = (ImageView)findViewById(R.id.shadow_story_banner);
        //shadow_story_banner.setImageResource(R.drawable.shadow);

        ImageView_character = (ImageView) findViewById(R.id.ImageView_character);
        ImageView_character.setImageResource(R.drawable.character);

        ImageView_task = (ImageView) findViewById(R.id.ImageView_task);
        ImageView_task.setImageResource(R.drawable.task);

        ImageView_desire = (ImageView) findViewById(R.id.ImageView_desire);
        ImageView_desire.setImageResource(R.drawable.desire);

        ScrollViewSmooth_main = (ScrollViewSmooth) findViewById(R.id.ScrollView_main);

    }

    private void UI_adjust(){
        // 尺寸自适应 根据图片的宽高 来调整view高度 [宽度不调整]
        AdjustSize.adjustHeight(ImageView_story_banner, AdjustSize.getImageWidthHeight(this, R.drawable.sc_button));
        //AdjustSize.adjustHeight(ImageView_story_banner, AdjustSize.getImageWidthHeight(this, R.drawable.banner));
        AdjustSize.adjustHeight(ImageView_character, AdjustSize.getImageWidthHeight(this, R.drawable.character));
        AdjustSize.adjustHeight(ImageView_desire, AdjustSize.getImageWidthHeight(this, R.drawable.desire));
        AdjustSize.adjustHeight(ImageView_task, AdjustSize.getImageWidthHeight(this, R.drawable.task));
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);

        UI_adjust();
    }
/*
    private void performAnim2(final View view){
        //View是否显示的标志
        show = !show;
        //属性动画对象
        ValueAnimator va ;
        if(show){
            //显示view，高度从0变到height值
            va = ValueAnimator.ofInt(0,height);
        }else{
            //隐藏view，高度从height变为0
            va = ValueAnimator.ofInt(height,0);
        }
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //获取当前的height值
                int h =(Integer)valueAnimator.getAnimatedValue();
                //动态更新view的高度
                view.getLayoutParams().height = h;
                view.requestLayout();
            }
        });
        va.setDuration(1000);
        //开始动画
        va.start();
    }
    */
}
