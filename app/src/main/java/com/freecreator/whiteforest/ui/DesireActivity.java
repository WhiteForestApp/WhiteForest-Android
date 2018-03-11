package com.freecreator.whiteforest.ui;

import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.ui.utils.AdjustSize;
import com.freecreator.whiteforest.ui.utils.Size;

import org.json.JSONException;
import org.json.JSONObject;

import static com.freecreator.whiteforest.common.Debug._Debug;

/**
 * Created by niko on 2018/3/11.
 */


public class DesireActivity extends AppCompatActivity {

    private ImageView ImageView_icon_desire = null;

    // list_task 是可滑动区域的layout, 往list_task 添加view, 就可以添加到可滑动区域进去
    // 可以添加的view布局有两种
    // 1. item_timer_task  这种布局是 时间投资型 任务的item布局
    //  该布局的 标题TextView 的 id 是 text_title
    //  该布局的 "加号" 按钮的 LinearLayout 的 id 是 btn_add_timer
    // 2.  item_normal_task   这种布局是普通任务的布局
    //   该布局的 标题 TextView 的 id 是 task_content
    // 3.  item_finished_task   这种布局是普通任务被完成时 显示出来的布局
    //   该布局的标题 TextView 的 id 是 task_content
    private LinearLayout list_desire = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desire);

        UI_init();
    }

    private void UI_init() {

        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);

        ImageView ImageView_background = (ImageView)findViewById(R.id.ImageView_background);
        ImageView_background.setImageResource(R.drawable.background);

        ImageView_icon_desire = (ImageView)findViewById(R.id.ImageView_icon_desire);
        ImageView_icon_desire.setImageResource(R.drawable.desire);

        ImageView ImageView_btn_add = (ImageView) findViewById(R.id.ImageView_btn_add);
        ImageView_btn_add.setImageResource(R.drawable.btn_add);

        list_desire = (LinearLayout) findViewById(R.id.list_desire);

    }


    private void UI_adjust(){
        // 尺寸自适应 根据图片的宽高 来调整view高度 [宽度不调整]
        AdjustSize.adjustHeight(ImageView_icon_desire, AdjustSize.getImageWidthHeight(this, R.drawable.desire));
    }

    /**
     * Why i use "JSONObject" as the param instead of params like "int type, String title, ..." ?
     * Because in this way we can devide the UI part and the data part
     * in long run, this kind of model (UI / Data part) will be convenient.
     * @param position the position to insert a new item view
     * @param data Json data. An sample is below:
     *             {  "title" : "blablabla", "scores" : 6 }
     */
    private void UI_addItem(int position, JSONObject data){


        FrameLayout item = (FrameLayout) DesireActivity.this.getLayoutInflater().inflate(R.layout.item_desire, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        String title = data.optString("title");
        if(title != null && !title.equals("")){
            int scores = data.optInt("scores");
            title = "$" + scores + " " + title;
            TextView text = (TextView)item.findViewById(R.id.text_title);
            text.setText(title);
        }

        list_desire.addView(item, position, params);

        LinearLayout space = (LinearLayout) DesireActivity.this.getLayoutInflater().inflate(R.layout.item_space, null);
        Size list_desire_size = AdjustSize.getViewSize(list_desire);
        Size refSize = new Size();
        refSize.height = 8;
        refSize.width = 500;
        float h  = (float)list_desire_size.width * (float)refSize.height / (float)refSize.width;
        params = new LinearLayout.LayoutParams(list_desire_size.width, (int)h);
        list_desire.addView(space, position + 1, params);

    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);

        UI_adjust();

        if(_Debug)
            mockDataForTest();
    }


    private void mockDataForTest(){

        // { "type" : 1,  "title" : "blablabla", "scores" : 6, "exp" : 13}
        JSONObject obj = new JSONObject();
        try {

            obj.put("scores", 6);
            obj.put("title", "阿萨德阿萨德受到法律框架的疯狂攻击所发生的空间");
            UI_addItem(0,obj);

            obj.put("scores", 6);
            obj.put("title", "阅读书籍");
            UI_addItem(0,obj);

            obj.put("scores", 6);
            obj.put("title", "阿萨德阿萨德受到法律框架的疯狂攻击所发生的空间");
            UI_addItem(0,obj);

            obj.put("scores", 6);
            obj.put("title", "阅读书籍");
            UI_addItem(0,obj);

            obj.put("scores", 6);
            obj.put("title", "阿萨德阿萨德受到法律框架的疯狂攻击所发生的空间萨德阿萨德受到法律框架的疯狂攻击所发生的空间萨德阿萨德受到法律框架的疯狂攻击所发生的空间");
            UI_addItem(0,obj);

            obj.put("scores", 6);
            obj.put("title", "阅读书籍");
            UI_addItem(0,obj);

            obj.put("scores", 6);
            obj.put("title", "阿萨德阿萨德受到法律框架萨德阿萨德受到法律框架的疯狂攻击所发生的空间框架萨德阿萨德受到法律框架的疯狂攻击所发生的空间框架萨德阿萨德受到法律框架的疯狂攻击所发生的空间的疯狂攻击所发生的空间");
            UI_addItem(0,obj);

            obj.put("scores", 6);
            obj.put("title", "阅读书籍");
            UI_addItem(0,obj);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
