package com.freecreator.whiteforest.ui;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.ui.utils.AdjustSize;
import com.freecreator.whiteforest.ui.utils.Size;
import com.freecreator.whiteforest.ui.utils.UIUtils;
import com.freecreator.whiteforest.ui.views.FontTextView;

import org.json.JSONException;
import org.json.JSONObject;

import static com.freecreator.whiteforest.common.Debug._Debug;

/**
 * Created by niko on 2018/3/10.
 */

public class TaskActivity extends AppCompatActivity {

    private ImageView ImageView_icon_task = null;

    private final static int TYPE_NORMAL_TASK = 1;
    private final static int TYPE_NORMAL_FINISHED_TASK = 2;
    private final static int TYPE_TIMER_TASK = 3;

    // list_task 是可滑动区域的layout, 往list_task 添加view, 就可以添加到可滑动区域进去
    // 可以添加的view布局有两种
    // 1. item_timer_task  这种布局是 时间投资型 任务的item布局
    //  该布局的 标题TextView 的 id 是 text_title
    //  该布局的 "加号" 按钮的 LinearLayout 的 id 是 btn_add_timer
    // 2.  item_normal_task   这种布局是普通任务的布局
    //   该布局的 标题 TextView 的 id 是 task_content
    // 3.  item_finished_task   这种布局是普通任务被完成时 显示出来的布局
    //   该布局的标题 TextView 的 id 是 task_content
    private LinearLayout list_task = null;
    private ImageView ImageView_btn_add = null;


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

        ImageView_btn_add = (ImageView) findViewById(R.id.ImageView_btn_add);
        ImageView_btn_add.setImageResource(R.drawable.btn_add);

        list_task = (LinearLayout) findViewById(R.id.list_task);

    }


    private void UI_adjust(){
        // 尺寸自适应 根据图片的宽高 来调整view高度 [宽度不调整]
        AdjustSize.adjustHeight(ImageView_icon_task, AdjustSize.getImageWidthHeight(this, R.drawable.task));
        AdjustSize.adjustHeight(ImageView_btn_add, AdjustSize.getImageWidthHeight(this, R.drawable.btn_add));
    }

    /**
     * Why i use "JSONObject" as the param instead of "int type, String title, ..." ?
     * Because in this way we can devide the UI part and the data part
     * in long run, this kind of model (UI / Data part) will be convenient.
     * @param position the position to insert a new item view
     * @param data Json data. An sample is below:
     *             { "type" : 1,  "title" : "blablabla", "scores" : 6, "exp" : 13}
     */
    private void UI_addItem(int position, JSONObject data){
        if(null == data)
            return;

        int type = data.optInt("type");
        switch(type){
            case TYPE_NORMAL_FINISHED_TASK:{
                LinearLayout item = (LinearLayout) TaskActivity.this.getLayoutInflater().inflate(R.layout.item_finished_task, null);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                String title = data.optString("title");
                if(title != null && !title.equals("")){
                    TextView text = (TextView)item.findViewById(R.id.task_content);
                    text.setText(title);

                    // set a mid line on the text
                    text.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
                    text.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
                    text.getPaint().setAntiAlias(true);
                }

                list_task.addView(item, position, params);

                LinearLayout space = (LinearLayout) TaskActivity.this.getLayoutInflater().inflate(R.layout.item_space, null);
                Size list_task_size = AdjustSize.getViewSize(list_task);

                Size refSize = new Size();
                refSize.height = 30;
                refSize.width = 500;
                float h  = (float)list_task_size.width * (float)refSize.height / (float)refSize.width;
                params = new LinearLayout.LayoutParams(list_task_size.width, (int)h);
                list_task.addView(space,position + 1, params);
                break;
            }

            case TYPE_NORMAL_TASK:{
                LinearLayout item = (LinearLayout) TaskActivity.this.getLayoutInflater().inflate(R.layout.item_normal_task, null);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                String title = data.optString("title");
                if(title != null && !title.equals("")){
                    TextView text = (TextView)item.findViewById(R.id.task_content);
                    text.setText(title);
                }

                list_task.addView(item, position, params);

                LinearLayout space = (LinearLayout) TaskActivity.this.getLayoutInflater().inflate(R.layout.item_space, null);
                Size list_task_size = AdjustSize.getViewSize(list_task);

                Size refSize = new Size();
                refSize.height = 30;
                refSize.width = 500;
                float h  = (float)list_task_size.width * (float)refSize.height / (float)refSize.width;
                params = new LinearLayout.LayoutParams(list_task_size.width, (int)h);
                list_task.addView(space, position + 1, params);

                break;
            }

            case TYPE_TIMER_TASK:{

                LinearLayout item = (LinearLayout) TaskActivity.this.getLayoutInflater().inflate(R.layout.item_timer_task, null);
                Size list_task_size = AdjustSize.getViewSize(list_task);
                Size refSize = AdjustSize.getImageWidthHeight(TaskActivity.this, R.drawable.item_timer_task);
                float h  = (float)list_task_size.width * (float)refSize.height / (float)refSize.width;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(list_task_size.width, (int)h);

                String title = data.optString("title");
                if(title != null && !title.equals("")){
                    TextView text = (TextView)item.findViewById(R.id.text_title);
                    text.setText(title);
                }

                list_task.addView(item,position, params);

                LinearLayout space = (LinearLayout) TaskActivity.this.getLayoutInflater().inflate(R.layout.item_space, null);

                refSize.height = 15;
                refSize.width = 500;
                h  = (float)list_task_size.width * (float)refSize.height / (float)refSize.width;
                params = new LinearLayout.LayoutParams(list_task_size.width, (int)h);
                list_task.addView(space, position + 1, params);

                break;
            }

        }

    }

    private void mockDataForTest(){

        // { "type" : 1,  "title" : "blablabla", "scores" : 6, "exp" : 13}
        JSONObject obj = new JSONObject();
        try {

            obj.put("type", TYPE_TIMER_TASK);
            obj.put("title", "阿萨德阿萨德受到法律框架的疯狂攻击所发生的空间");
            UI_addItem(0,obj);

            obj.put("type", TYPE_TIMER_TASK);
            obj.put("title", "阅读书籍");
            UI_addItem(2,obj);

            obj.put("type", TYPE_NORMAL_TASK);
            obj.put("title", "阿萨德阿萨德受到法律框架的疯狂攻击所发生的空间");
            UI_addItem(4,obj);

            obj.put("type", TYPE_NORMAL_TASK);
            obj.put("title", "msflkj阿斯顿也斯蒂");
            UI_addItem(6,obj);

            obj.put("type", TYPE_NORMAL_TASK);
            obj.put("title", "斯蒂芬斯多夫电风扇阿萨德斯蒂芬斯多夫阿斯顿也斯蒂");
            UI_addItem(8,obj);

            obj.put("type", TYPE_NORMAL_TASK);
            obj.put("title", "阿萨德阿萨德受到法律框架的疯狂攻击所发生的空间");
            UI_addItem(10,obj);

            obj.put("type", TYPE_NORMAL_FINISHED_TASK);
            obj.put("title", "msflkj阿斯顿也斯蒂");
            UI_addItem(12,obj);

            obj.put("type", TYPE_NORMAL_FINISHED_TASK);
            obj.put("title", "斯蒂芬斯多夫电风扇阿萨德斯蒂芬斯多夫阿斯顿也斯蒂");
            UI_addItem(14,obj);

            obj.put("type", TYPE_NORMAL_FINISHED_TASK);
            obj.put("title", "阿萨德阿萨德受到法律框架的疯狂攻击所发生的空间");
            UI_addItem(16,obj);

            obj.put("type", TYPE_NORMAL_FINISHED_TASK);
            obj.put("title", "msflkj阿斯顿也斯蒂");
            UI_addItem(18,obj);

            obj.put("type", TYPE_NORMAL_FINISHED_TASK);
            obj.put("title", "斯蒂芬斯多夫电风扇阿萨德斯蒂芬斯多夫阿斯顿也斯蒂");
            UI_addItem(20,obj);



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);

        UI_adjust();

        if(_Debug)
            mockDataForTest();

    }
}
