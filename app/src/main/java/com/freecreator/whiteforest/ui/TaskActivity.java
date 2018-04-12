package com.freecreator.whiteforest.ui;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.common.cache.LocalCache;
import com.freecreator.whiteforest.common.details.TaskCatalog;
import com.freecreator.whiteforest.common.details.TaskDetails;
import com.freecreator.whiteforest.ui.dialogs.animAchevement;
import com.freecreator.whiteforest.ui.dialogs.dialogAddTask;
import com.freecreator.whiteforest.ui.dialogs.controllerTimePicker;
import com.freecreator.whiteforest.ui.utils.AdjustSize;
import com.freecreator.whiteforest.ui.utils.Size;
import com.freecreator.whiteforest.ui.utils.UIUtils;
import com.freecreator.whiteforest.ui.views.FontTextView;
import com.freecreator.whiteforest.utils.MD5;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.freecreator.whiteforest.common.Debug._Debug;
import static com.freecreator.whiteforest.utils.JsonUtils.jsonArrayPut;
import static com.freecreator.whiteforest.utils.JsonUtils.jsonPut;
import static com.freecreator.whiteforest.utils.JsonUtils.optStrToJsonObject;

/**
 * Created by niko on 2018/3/10.
 */

public class TaskActivity extends AppCompatActivity {

    private ImageView ImageView_icon_task = null;

    public final static int TYPE_NORMAL_TASK = 1;
    public final static int TYPE_NORMAL_FINISHED_TASK = 2;
    public final static int TYPE_TIMER_TASK = 3;

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

    private dialogAddTask dialogTask = null;
    private animAchevement animGainAchevement = null;
    private controllerTimePicker conTimerPicker = null;

    private int task_normal_total = 0;
    private int task_normal_finished_total = 0;
    private int task_timer_total = 0;

    private TaskCatalog task_catalog = null;
    private LocalCache local_cache = null;

    // view 对应 着 item 的view 和 JSONObject
    private HashMap<View, ArrayList<Object>> view_info = new HashMap<>();
    private boolean first_time = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        local_cache =  new LocalCache(this);
        task_catalog = local_cache.getTaskCatalog();

        UI_init();
        setListeners();

    }

    private void setListeners() {
        if(null != ImageView_btn_add){
            ImageView_btn_add.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    dialogTask.show();
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

        ImageView_icon_task = (ImageView)findViewById(R.id.ImageView_icon_task);
        ImageView_icon_task.setImageResource(R.drawable.task);

        ImageView_btn_add = (ImageView) findViewById(R.id.ImageView_btn_add);
        ImageView_btn_add.setImageResource(R.drawable.btn_add);

        list_task = (LinearLayout) findViewById(R.id.list_task);

        dialogTask = new dialogAddTask(this, (RelativeLayout) findViewById(R.id.task_page));
        animGainAchevement = new animAchevement(this, (RelativeLayout) findViewById(R.id.task_page));
        conTimerPicker = new controllerTimePicker(this);
    }

    private void UI_adjust(){
        // 尺寸自适应 根据图片的宽高 来调整view高度 [宽度不调整]
        AdjustSize.adjustHeight(ImageView_icon_task, AdjustSize.getImageWidthHeight(this, R.drawable.task));
        AdjustSize.adjustHeight(ImageView_btn_add, AdjustSize.getImageWidthHeight(this, R.drawable.btn_add));
    }

    private void UI_clickEvent(View v){

        ArrayList<Object> value = view_info.get(v);

        if(null == value)
            return;

        JSONObject obj = (JSONObject) value.get(0);
        if(null == obj || !(obj instanceof JSONObject))
            return;

        int type = obj.optInt("type");
        switch(type){
            case TYPE_NORMAL_FINISHED_TASK:{
                break;
            }
            case TYPE_NORMAL_TASK:{

                View item = (View) value.get(1);
                if(null == item || !(item instanceof View))
                    return;

                View space = (View) value.get(2);
                if(null == space || !(space instanceof View))
                    return;

                task_normal_total--;
                list_task.removeView(item);
                list_task.removeView(space);

                animGainAchevement.show();
                task_catalog.deleteTaskDetails(obj.optString("hash"));
                local_cache.setTaskCatalog(task_catalog);

                JSONObject item_finished = optStrToJsonObject(obj.toString());
                jsonPut(item_finished, "hash", MD5.MD5("" + System.currentTimeMillis()));
                jsonPut(item_finished, "type", TYPE_NORMAL_FINISHED_TASK);
                UI_addItem(-1, item_finished);

                break;
            }
            case TYPE_TIMER_TASK:{
                conTimerPicker.show(obj);
                break;
            }
        }
    }

    public void data_addTimerRecord(JSONObject data, int minutes){

        int used_minutes = data.optInt("total_time", 0);
        used_minutes += minutes;
        jsonPut(data, "total_time", used_minutes);

        JSONObject new_record = new JSONObject();
        jsonPut(new_record, "timestamp", (new Timestamp(System.currentTimeMillis() )).toString());
        jsonPut(new_record, "add_minutes", minutes);

        JSONArray record_array = data.optJSONArray("use_record");

        if(null == record_array){
            record_array = new JSONArray();
            record_array.put(new_record);

            jsonArrayPut(data, "use_record", record_array);
        }
        else{
            record_array.put(new_record);
            jsonArrayPut(data, "use_record", record_array);
        }

        task_catalog.addTaskDetails(new TaskDetails(data));
        local_cache.setTaskCatalog(task_catalog);
    }

    /**
     * Why i use "JSONObject" as the param instead of "int type, String title, ..." ?
     * Because in this way we can devide the UI part and the data part
     * in long run, this kind of model (UI / Data part) will be convenient.
     * @param position the position to insert a new item view
     * @param data Json data. An sample is below:
     *             { "type" : 1,  "title" : "blablabla", "scores" : 6, "exp" : 13}
     */
    public void UI_addItem(int position, JSONObject data){
        if(null == data)
            return;

        task_catalog.addTaskDetails(new TaskDetails(data));
        local_cache.setTaskCatalog(task_catalog);

        int type = data.optInt("type");
        switch(type){
            case TYPE_NORMAL_FINISHED_TASK:{

                if(position < 0)
                    position = task_timer_total * 2 + task_normal_total * 2 ;

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
                list_task.addView(space, position + 1, params);
                //list_task.addView(space,  params);

                task_normal_finished_total++;

                break;
            }

            case TYPE_NORMAL_TASK:{

                if(position < 0)
                    position = task_timer_total * 2 ;

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
                //list_task.addView(space,  params);

                task_normal_total++;

                ArrayList<Object> value = new ArrayList<>();
                value.add(data);
                value.add(item);
                value.add(space);
                view_info.put(item.findViewById(R.id.checkbox), value);
                item.findViewById(R.id.checkbox).setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v ){
                        UI_clickEvent(v);
                    }
                });

                break;
            }

            case TYPE_TIMER_TASK:{

                if(position < 0)
                    position = 0;

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


                ArrayList<Object> value = new ArrayList<>();
                value.add(data);
                value.add(item);
                value.add(space);
                view_info.put(item.findViewById(R.id.btn_add_timer), value);
                item.findViewById(R.id.btn_add_timer).setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v ){
                        UI_clickEvent(v);
                    }
                });

                task_timer_total++;

                break;
            }

        }

    }

    private void UI_initList(){

        List<TaskDetails> task = task_catalog.getTaskDetailsList();

        if(task == null){
            return;
        }

        for(int i = 0; i< task.size(); i++){
            TaskDetails details = task.get(i);
            if(null == details)
                continue;

            UI_addItem(-1, details.toJSONObject());
        }
    }

    private void mockDataForTest(){

        // { "type" : 1,  "title" : "blablabla", "scores" : 6, "exp" : 13}
        JSONObject obj = new JSONObject();
        try {

            obj.put("type", TYPE_TIMER_TASK);
            obj.put("hash", MD5.MD5(""+ System.currentTimeMillis()));
            obj.put("title", "阅读书籍");
            UI_addItem(-1,obj);

            obj.put("type", TYPE_NORMAL_FINISHED_TASK);
            obj.put("hash", MD5.MD5(""+ System.currentTimeMillis()));
            obj.put("title", "安装[白色森林]");
            UI_addItem(-1,obj);

            obj.put("type", TYPE_NORMAL_FINISHED_TASK);
            obj.put("hash", MD5.MD5(""+ System.currentTimeMillis()));
            obj.put("title", "了解白色森林的历史");
            UI_addItem(-1,obj);

            obj.put("type", TYPE_NORMAL_FINISHED_TASK);
            obj.put("hash", MD5.MD5(""+ System.currentTimeMillis()));
            obj.put("title", "添加一个普通的任务");
            UI_addItem(-1,obj);

            obj.put("type", TYPE_NORMAL_FINISHED_TASK);
            obj.put("hash", MD5.MD5(""+ System.currentTimeMillis()));
            obj.put("title", "添加一个时间投资计划的任务");
            UI_addItem(-1,obj);


            obj.put("type", TYPE_NORMAL_FINISHED_TASK);
            obj.put("hash", MD5.MD5(""+ System.currentTimeMillis()));
            obj.put("title", "打开人物页面页 查看我所获得的奖牌");
            UI_addItem(-1,obj);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);

        UI_adjust();

        if(false == first_time){

            first_time = true;
            UI_initList();

            if(task_catalog.getItemNum() == 0)
                mockDataForTest();

        }
    }
}
