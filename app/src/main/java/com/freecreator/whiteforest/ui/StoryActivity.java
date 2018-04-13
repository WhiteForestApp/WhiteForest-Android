package com.freecreator.whiteforest.ui;

import android.content.Intent;
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

    private animDukeConversation animDuck = null;
    private animSkyMountainAppear animSkyMountain = null;
    private animConversationDialog animConversation = null;
    private animConversationDialog animMeConversation = null;
    private animClickPlease animClickToGoOn = null;
    private animMeConversation animMe = null;
    private animWhiteForestPopup animWhiteforest = null;
    private animGodOfTask animGodTask = null;
    private animGodOfDesire animGodDesire = null;

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


                    new Thread() {
                        public void run() {
                            try{

                                sleep(800);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animDuck.show();
                                        animConversation.setText("是的,我们从历史说起吧\n从你的历史说起");
                                        animConversation.show();
                                    }
                                });


                                sleep(2500);
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

                                        animConversation.setText("在新世纪418年的秋天\n你出生在我们黑水王国\n一个叫白色森林的地方");
                                        animConversation.show();

                                    }
                                });


                                sleep(5500);
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

                                        animConversation.setText("白色森林里面的每一棵白寒树高达几百米,直耸天际.");
                                        animConversation.show();

                                    }
                                });


                                sleep(3500);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animClickToGoOn.show();
                                    }
                                });

                                block_clicked = false;

                            }catch (InterruptedException e){

                            }
                        }
                    }.start();
                }
                else if(3 == step_of_play) {

                    block_clicked = true;
                    step_of_play = 4;

                    animDuck.hide();
                    animConversation.hide();
                    animClickToGoOn.hide();

                    new Thread() {
                        public void run() {
                            try{

                                sleep(800);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animMe.show();
                                        animMeConversation.setText("白色森林? 白寒树?");
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
                else if(4 == step_of_play) {

                    block_clicked = true;
                    step_of_play = 5;

                    animMe.hide();
                    animMeConversation.hide();



                    new Thread() {
                        public void run() {
                            try{

                                sleep(800);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animDuck.show();
                                        animConversation.setText("是的...");
                                        animConversation.show();
                                    }
                                });


                                sleep(2700);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animConversation.hide();
                                        animSkyMountain.hide_mountain();
                                    }
                                });

                                sleep(200);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animWhiteforest.show();
                                    }
                                });

                                sleep(3300);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        animConversation.setText("你看,这就是白色森林.");
                                        animConversation.show();

                                    }
                                });

                                sleep(2500);
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

                                        animConversation.setText("白寒树每年秋天\n都会结出许多果实.\n我们都是从白寒树的果实\n生长出来的");
                                        animConversation.show();

                                    }
                                });



                                sleep(3500);


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animClickToGoOn.show();
                                    }
                                });

                                block_clicked = false;

                            }catch (InterruptedException e){

                            }
                        }
                    }.start();

                }
                else if(5 == step_of_play) {

                    block_clicked = true;
                    step_of_play = 6;

                    animDuck.hide();
                    animConversation.hide();
                    animClickToGoOn.hide();

                    animSkyMountain.show_mountain();
                    animWhiteforest.hide();

                    new Thread() {
                        public void run() {
                            try{

                                sleep(800);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animMe.show();
                                        animMeConversation.setText("我是从树上的果实长出来的?");
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
                else if(6 == step_of_play) {

                    block_clicked = true;
                    step_of_play = 7;

                    animMeConversation.hide();
                    animMe.hide();

                    new Thread() {
                        public void run() {
                            try{

                                sleep(800);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animDuck.show();
                                        animConversation.setText("是的,在果实中, \n你沉睡了三十年\n在睡梦中你完成了\n语言的学习和基础教育");
                                        animConversation.show();
                                    }
                                });


                                sleep(5500);
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

                                        animConversation.setText("直到..你苏醒过来,\n并见到了我\n\n至于我是谁?\n我只是你的领路人\n弗里曼王爵");
                                        animConversation.show();

                                    }
                                });


                                sleep(7500);
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

                                        animConversation.setText("这是我们这个世界的传统\n每个年满300周岁的长者\n都要给新生儿做领路人");
                                        animConversation.show();

                                    }
                                });


                                sleep(3500);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animClickToGoOn.show();
                                    }
                                });

                                block_clicked = false;

                            }catch (InterruptedException e){

                            }
                        }
                    }.start();
                }
                else if(7 == step_of_play) {

                    block_clicked = true;
                    step_of_play = 8;

                    animDuck.hide();
                    animConversation.hide();
                    animClickToGoOn.hide();

                    new Thread() {
                        public void run() {
                            try{

                                sleep(800);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animMe.show();
                                        animMeConversation.setText("听起来\n令人感到难以置信");
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
                else if(8 == step_of_play) {

                    block_clicked = true;
                    step_of_play = 9;

                    animMeConversation.hide();
                    animMe.hide();

                    new Thread() {
                        public void run() {
                            try{

                                sleep(800);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animDuck.show();
                                        animConversation.setText("关于你的历史,\n就这么多了.\n我们聊一聊这个世界吧");
                                        animConversation.show();
                                    }
                                });

                                sleep(5500);
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

                                        animConversation.setText("我们这个世界\n有个永恒的主题,\"超越\"\n每个人都可以从平民\n不断超越\n成为贵族,神族..");
                                        animConversation.show();
                                    }
                                });

                                sleep(3500);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animClickToGoOn.show();
                                    }
                                });

                                block_clicked = false;

                            }catch (InterruptedException e){

                            }
                        }
                    }.start();
                }
                else if(9 == step_of_play) {

                    block_clicked = true;
                    step_of_play = 10;

                    animDuck.hide();
                    animConversation.hide();
                    animClickToGoOn.hide();

                    new Thread() {
                        public void run() {
                            try{

                                sleep(800);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animMe.show();
                                        animMeConversation.setText("超越? 我该怎么做?");
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

                else if(10 == step_of_play) {

                    block_clicked = true;
                    step_of_play = 11;

                    animMeConversation.hide();
                    animMe.hide();

                    new Thread() {
                        public void run() {
                            try{

                                sleep(800);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animDuck.show();
                                        animConversation.setText("这世界上有两个神:\n使命之神,欲望之神");
                                        animConversation.show();
                                    }
                                });

                                sleep(5500);
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
                                        animGodTask.show();
                                    }
                                });


                                sleep(600);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animGodDesire.show();
                                    }
                                });


                                sleep(1700);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        animConversation.setText("向使命之神\n承诺你的任务\n并且努力去完成它.\n你将会获得金钱和心灵能量");
                                        animConversation.show();
                                    }
                                });

                                sleep(7500);

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

                                        animConversation.setText("积累下来的心灵能量\n将会帮助你超越,\n让你成为贵族 皇族 神族..");
                                        animConversation.show();
                                    }
                                });

                                sleep(7500);


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

                                        animConversation.setText("用赚来的金钱\n可以请求欲望之神\n实现你的心愿");
                                        animConversation.show();
                                    }
                                });
                                sleep(4500);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animClickToGoOn.show();
                                    }
                                });

                                block_clicked = false;

                            }catch (InterruptedException e){

                            }
                        }
                    }.start();
                }

                else if(11 == step_of_play) {

                    block_clicked = true;
                    step_of_play = 12;

                    animDuck.hide();
                    animConversation.hide();
                    animClickToGoOn.hide();
                    animGodDesire.hide();
                    animGodTask.hide();

                    new Thread() {
                        public void run() {
                            try{

                                sleep(800);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animMe.show();
                                        animMeConversation.setText("原来如此...");
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

                else if(12 == step_of_play) {

                    block_clicked = true;
                    step_of_play = 13;

                    animMe.hide();
                    animMeConversation.hide();

                    new Thread() {
                        public void run() {
                            try{

                                sleep(800);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animDuck.show();
                                        animConversation.setText("去向使命之神\n承诺你的任务吧\n去不断勤奋地耕作吧");
                                        animConversation.show();
                                    }
                                });


                                sleep(6700);

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

                                        animConversation.setText("不久\n你将赢得你的荣耀\n也将一定会\n结识一群志同道合的伙伴");
                                        animConversation.show();

                                    }
                                });

                                sleep(6500);

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

                                        animConversation.setText("现在, 你知道怎么做了吗");
                                        animConversation.show();

                                    }
                                });

                                sleep(3500);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animClickToGoOn.show();
                                    }
                                });

                                block_clicked = false;

                            }catch (InterruptedException e){

                            }
                        }
                    }.start();

                }

                else if(13 == step_of_play) {

                    block_clicked = true;
                    step_of_play = 14;

                    animDuck.hide();
                    animConversation.hide();
                    animClickToGoOn.hide();

                    new Thread() {
                        public void run() {
                            try{

                                sleep(800);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animMe.show();
                                        animMeConversation.setText("是的, 我知道了\n谢谢您,弗里曼王爵");
                                        animMeConversation.show();
                                    }
                                });

                                sleep(1500);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animClickToGoOn.show();
                                    }
                                });

                                block_clicked = false;

                            }catch (InterruptedException e){

                            }
                        }
                    }.start();
                }

                else if(14 == step_of_play) {

                    block_clicked = true;
                    step_of_play = 15;

                    animMe.hide();
                    animMeConversation.hide();
                    animClickToGoOn.hide();

                    new Thread() {
                        public void run() {
                            try{

                                sleep(300);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        animSkyMountain.hide_mountain();
                                        animSkyMountain.hide_sky();
                                    }
                                });


                                sleep(700);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(StoryActivity.this, mainpage.class);
                                        startActivity(intent);
                                        StoryActivity.this.finish();
                                    }
                                });

                                block_clicked = false;
                            }catch (InterruptedException e){

                            }
                        }
                    }.start();
                }

                else if(15 == step_of_play) {

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

        animSkyMountain = new animSkyMountainAppear(this, parent_page);
        animWhiteforest = new animWhiteForestPopup(this, parent_page);
        animGodTask = new animGodOfTask(this, parent_page);
        animGodDesire = new animGodOfDesire(this, parent_page);
        animDuck = new animDukeConversation(this, parent_page );
        animMe = new animMeConversation(this, parent_page);
        animConversation = new animConversationDialog(this, parent_page, 450);
        animMeConversation = new animConversationDialog(this, parent_page, 340);
        animClickToGoOn = new animClickPlease(this, parent_page);
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
