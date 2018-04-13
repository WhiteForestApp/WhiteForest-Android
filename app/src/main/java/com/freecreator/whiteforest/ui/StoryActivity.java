package com.freecreator.whiteforest.ui;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.ui.utils.AdjustSize;
import com.freecreator.whiteforest.ui.animation.*;

import static com.freecreator.whiteforest.common.Debug._Debug;

/**
 * Created by niko on 2018/3/14.
 */

public class StoryActivity extends AppCompatActivity {

    private ImageView ImageView_story_header = null;
    private ImageView ImageView_story_bottom = null;

    private animDukeConversation animDuck = null;
    private animSkyMountainAppear animSkyMountain = null;
    private animConversationDialog animConversation = null;
    private animConversationDialog animMeConversation = null;
    private animClickPlease animClickToGoOn = null;
    private animMeConversation animMe = null;

    private TextView user_name = null;
    private TextView user_name_2 = null;

    private RelativeLayout parent_page = null;

    private boolean first_time = true;
    private int step_of_play = 0;
    private boolean block_clicked = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        UI_init();
        setListeners();
    }

    private void setListeners() {
        parent_page.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(block_clicked)
                    return;

                if(1 == step_of_play) {

                    animClickToGoOn.hide();
                    animConversation.hide();
                    animDuck.hide();

                    block_clicked = true;
                    step_of_play = 2;

                    new Thread() {
                        public void run() {
                            try{

                                sleep(800);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animMe.show();
                                        animMeConversation.setOrientation("right");
                                        animMeConversation.setText("黑水王国?");
                                        animMeConversation.show();
                                    }
                                });

                                sleep(1000);

                                block_clicked = false;

                            }catch (InterruptedException e){

                            }
                        }
                    }.start();
                }
                else if(2 == step_of_play){

                    block_clicked = true;
                    step_of_play = 3;

                    animMeConversation.hide();
                    animMe.hide();
                }
            }
        });
    }

    private void UI_init() {

        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
/*
        user_name = (TextView)findViewById(R.id.user_name);
        user_name_2 = (TextView)findViewById(R.id.user_name_2);

*/

        parent_page =  (RelativeLayout) findViewById(R.id.story_attachment);

        animDuck = new animDukeConversation(this, parent_page );
        animSkyMountain = new animSkyMountainAppear(this, parent_page);
        animConversation = new animConversationDialog(this, parent_page, 450);
        animMeConversation = new animConversationDialog(this, parent_page, 340);
        animClickToGoOn = new animClickPlease(this, parent_page);
        animMe = new animMeConversation(this, parent_page);
    }

    private void UI_adjust(){
        // 尺寸自适应 根据图片的宽高 来调整view高度 [宽度不调整]
        //AdjustSize.adjustHeight(ImageView_story_header, AdjustSize.getImageWidthHeight(this, R.drawable.story_header));
        //AdjustSize.adjustHeight(ImageView_story_bottom, AdjustSize.getImageWidthHeight(this, R.drawable.story_bottom));
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);

        UI_adjust();

        if(first_time){
            first_time = false;

            new Thread(){
                public void run(){
                    try{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                animSkyMountain.show();
                            }
                        });

                        sleep(500);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                animDuck.show();
                            }
                        });


                        sleep(500);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                animConversation.setText("niko 你来啦");
                                animConversation.show();
                            }
                        });

                        sleep(1500);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                animConversation.hide();

                            }
                        });


                        sleep(200);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                animConversation.setText("我是弗里曼王爵的心灵意识\n欢迎来到黑水王国");
                                animConversation.show();

                                step_of_play = 1;
                            }
                        });


                        sleep(2000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                block_clicked = false;
                                animClickToGoOn.show();
                            }
                        });


                    }
                    catch (InterruptedException e){

                    }
                }
            }.start();
        }
    }
}
