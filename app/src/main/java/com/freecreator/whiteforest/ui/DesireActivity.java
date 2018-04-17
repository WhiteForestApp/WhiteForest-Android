package com.freecreator.whiteforest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.common.cache.LocalCache;
import com.freecreator.whiteforest.common.details.DesireCatalog;
import com.freecreator.whiteforest.common.details.DesireDetails;
import com.freecreator.whiteforest.common.details.TaskCatalog;
import com.freecreator.whiteforest.common.details.TaskDetails;
import com.freecreator.whiteforest.ui.dialogs.dialogAddDesire;
import com.freecreator.whiteforest.ui.utils.AdjustSize;
import com.freecreator.whiteforest.ui.utils.Size;
import com.freecreator.whiteforest.utils.MD5;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.freecreator.whiteforest.common.Debug._debug2;

/**
 * Created by niko on 2018/3/11.
 */


public class DesireActivity extends AppCompatActivity {

    public final static int TYPE_NORMAL_DESIRE = 1;

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
    private ImageView ImageView_btn_add = null;

    private DesireCatalog desire_catalog = null;
    private LocalCache local_cache = null;
    private boolean first_time = false;
    // view 是被点击的 view, Object 第一个元素是jsonObject 第二个元素是 整栏的view
    private HashMap<View, ArrayList<Object>> view_info = new HashMap<>();

    private dialogAddDesire dialogDesire = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desire);


        local_cache =  new LocalCache(this);
        desire_catalog = local_cache.getDesireCatalog();

        UI_init();
        setListeners();
    }

    private void setListeners() {
        if(null != ImageView_btn_add){
            ImageView_btn_add.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    dialogDesire.show();
                }
            });
        }
    }

    private void UI_init() {

        //全屏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);

        ImageView ImageView_background = (ImageView)findViewById(R.id.ImageView_background);
        ImageView_background.setImageResource(R.drawable.background);

        ImageView_icon_desire = (ImageView)findViewById(R.id.ImageView_icon_desire);
        ImageView_icon_desire.setImageResource(R.drawable.desire);

        ImageView_btn_add = (ImageView) findViewById(R.id.ImageView_btn_add);
        ImageView_btn_add.setImageResource(R.drawable.btn_add);

        list_desire = (LinearLayout) findViewById(R.id.list_desire);

        dialogDesire = new dialogAddDesire(this, (RelativeLayout) findViewById(R.id.desire_page) );
    }


    private void UI_adjust(){
        // 尺寸自适应 根据图片的宽高 来调整view高度 [宽度不调整]
        AdjustSize.adjustHeight(ImageView_icon_desire, AdjustSize.getImageWidthHeight(this, R.drawable.desire));
        AdjustSize.adjustHeight(ImageView_btn_add, AdjustSize.getImageWidthHeight(this, R.drawable.btn_add));
    }

    /**
     * Why i use "JSONObject" as the param instead of params like "int type, String title, ..." ?
     * Because in this way we can devide the UI part and the data part
     * in long run, this kind of model (UI / Data part) will be convenient.
     * @param position the position to insert a new item view
     * @param data Json data. An sample is below:
     *             {  "title" : "blablabla", "scores" : 6 }
     */
    public void UI_addItem(int position, JSONObject data){

        desire_catalog.addDesireDetails(new DesireDetails(data));
        local_cache.setDesireCatalog(desire_catalog);

        FrameLayout item = (FrameLayout) DesireActivity.this.getLayoutInflater().inflate(R.layout.item_desire, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        String title = data.optString("title");
        if(title != null && !title.equals("")){
            int scores = data.optInt("scores");
            title = "$" + scores + " " + title;
            TextView text = (TextView)item.findViewById(R.id.text_title);
            text.setText(title);
        }

        list_desire.addView(item, 0, params);

        //LinearLayout space = (LinearLayout) DesireActivity.this.getLayoutInflater().inflate(R.layout.item_space, null);
        Size list_desire_size = AdjustSize.getViewSize(list_desire);
        Size refSize = new Size();
        refSize.height = 18;
        refSize.width = 500;
        float h  = (float)list_desire_size.width * (float)refSize.height / (float)refSize.width;
        params = new LinearLayout.LayoutParams(list_desire_size.width, (int)h);
        //list_desire.addView(space, 1, params);

        ArrayList<Object> value = new ArrayList<>();
        value.add(data);
        value.add(item);
        //value.add(space);
        view_info.put(item, value);
        item.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v ){
                UI_clickEvent(v);
            }
        });

    }

    private void UI_clickEvent(View v) {
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        UI_adjust();


        if(false == first_time){

            first_time = true;
            UI_initList();

            if(desire_catalog.getItemNum() == 0)
                mockDataForTest();

        }

    }

    private void UI_initList() {

        List<DesireDetails> desire = desire_catalog.getDesireDetailsList();

        if(desire == null){
            return;
        }

        for(int i = 0; i< desire.size(); i++){
            DesireDetails details = desire.get(i);
            if(null == details)
                continue;

            UI_addItem(-1, details.toJSONObject());
        }
    }


    private void mockDataForTest(){

        // { "type" : 1,  "title" : "blablabla", "scores" : 6, "exp" : 13}
        JSONObject obj = new JSONObject();
        try {

            obj.put("scores", 6);
            obj.put("title", "打一局王者荣耀");
            obj.put("hash", MD5.MD5(""+ System.currentTimeMillis()));
            UI_addItem(0,obj);

            obj.put("scores", 6);
            obj.put("title", "逛商场买衣服");
            obj.put("hash", MD5.MD5(""+ System.currentTimeMillis()));
            UI_addItem(0,obj);

            obj.put("scores", 6);
            obj.put("title", "读一本小说");
            obj.put("hash", MD5.MD5(""+ System.currentTimeMillis()));
            UI_addItem(0,obj);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
