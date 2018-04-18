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
import android.widget.Toast;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.base.Application;
import com.freecreator.whiteforest.base.User;
import com.freecreator.whiteforest.common.cache.LocalCache;
import com.freecreator.whiteforest.common.details.DesireCatalog;
import com.freecreator.whiteforest.common.details.DesireDetails;
import com.freecreator.whiteforest.common.details.TaskCatalog;
import com.freecreator.whiteforest.common.details.TaskDetails;
import com.freecreator.whiteforest.ui.dialogs.dialogAddDesire;
import com.freecreator.whiteforest.ui.dialogs.dialogCustom;
import com.freecreator.whiteforest.ui.utils.AdjustSize;
import com.freecreator.whiteforest.ui.utils.ResourceUtils;
import com.freecreator.whiteforest.ui.utils.Size;
import com.freecreator.whiteforest.ui.utils.UIUtils;
import com.freecreator.whiteforest.utils.MD5;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.freecreator.whiteforest.common.Debug._debug2;
import static com.freecreator.whiteforest.utils.JsonUtils.jsonPut;

/**
 * Created by niko on 2018/3/11.
 */


public class DesireActivity extends AppCompatActivity {

    public final static int TYPE_NORMAL_DESIRE = 1;

    private ImageView ImageView_icon_desire = null;

    private LinearLayout list_desire = null;
    private ImageView ImageView_btn_add = null;
    private TextView text_current_coins = null;

    private DesireCatalog desire_catalog = null;
    private LocalCache local_cache = null;
    private boolean first_time = false;
    private User user = null;
    private int user_coins = -2;

    // view 是被点击的 view, Object 第一个元素是jsonObject 第二个元素是 整栏的view
    private HashMap<View, ArrayList<Object>> view_info = new HashMap<>();
    private HashMap<View, ArrayList<Object>> view_info_for_trash_btn = new HashMap<>();

    private dialogAddDesire dialogDesire = null;
    private dialogCustom dialogTips = null;


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
        text_current_coins = (TextView) findViewById(R.id.text_current_coins);

        //dialogDesire = new dialogAddDesire(this, (RelativeLayout) findViewById(R.id.desire_page) );
        dialogDesire = new dialogAddDesire(this, R.style.Dialog);

        user = Application.getUser();

        new Thread(){
            @Override
            public void run(){
                try {
                    while(1==1){

                        sleep(1000);
                        int coins = user.getData().optInt("coins", 0);

                        if(coins != user_coins){
                            user_coins = coins;

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String text = getResources().getString(R.string.store_coins_tip);
                                    text = String.format(text, user_coins);
                                    text_current_coins.setText(text);
                                }
                            });
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
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

            TextView text = (TextView)item.findViewById(R.id.text_title);
            text.setText(title);

            TextView text_coin_num = (TextView)item.findViewById(R.id.text_coin_num);
            int scores = data.optInt("scores");
            text_coin_num.setText(""+ scores);

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
        View clickable = item.findViewById(R.id.linear_item);
        view_info.put(clickable, value);

        clickable.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v ){
                UI_clickEvent(v);
            }
        });

        view_info_for_trash_btn.put(item.findViewById(R.id.image_trash), value);

        item.findViewById(R.id.image_trash).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v ){
                ArrayList<Object> value = view_info_for_trash_btn.get(v);
                JSONObject data = (JSONObject)value.get(0);

                DesireDetails desire = new DesireDetails(data);

                String hash = data.optString("hash");
                desire_catalog.deleteDesireDetails(hash);
                local_cache.setDesireCatalog(desire_catalog);

                View item = (View)value.get(1);
                list_desire.removeView(item);
            }
        });
    }

    private void UI_clickEvent(View v) {
        ArrayList<Object> value = view_info.get(v);
        if(null == value)
            return;

        JSONObject data = (JSONObject)value.get(0);
        if(null == data)
            return;

        Size refSize = AdjustSize.getImageWidthHeight(this, R.drawable.custom_dialog);
        Size size = UIUtils.getScreenSize(this);
        size.height =  (int)((float)size.width * (float)refSize.height / (float)refSize.width);

        dialogCustom.Builder builder = new dialogCustom.Builder(this);
        dialogTips = builder
                .style(R.style.Dialog)
                .heightpx(size.height)
                .widthpx(size.width)
                .cancelTouchout(true)
                .view(R.layout.dialog_common)
                .build();

        TextView text_title = (TextView)dialogTips.getView().findViewById(R.id.text_dialog_title);
        String text = getResources().getString(R.string.desire_use_tip);
        text = String.format(text, data.optInt("scores", 0));
        text_title.setText(text);

        dialogTips.addExtraData(value);
        dialogTips.addViewOnclick(R.id.cancel, new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dialogTips.dismiss();
            }
        });
        dialogTips.addViewOnclick(R.id.confirm, new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ArrayList<Object> obj = (ArrayList<Object>)dialogTips.getExtraData();

                JSONObject data = (JSONObject)obj.get(0);
                if(null != data){
                    int coins_cost = data.optInt("scores", 0);
                    int coins = user.getData().optInt("coins", 0);

                    if(coins - coins_cost < 0){
                        Toast.makeText(DesireActivity.this, getResources().getString(R.string.coins_not_enough), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        jsonPut(user.getData(), "coins", coins - coins_cost);
                        user.saveData();
                    }
                    dialogTips.dismiss();
                    return;
                }

            }
        });
        dialogTips.show();

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

            obj.put("type", 1);
            obj.put("scores", 6);
            obj.put("title", "打一局王者荣耀");
            obj.put("hash", MD5.MD5(""+ System.currentTimeMillis()));
            UI_addItem(0,obj);

            obj.put("type", 1);
            obj.put("scores", 6);
            obj.put("title", "逛商场买衣服");
            obj.put("hash", MD5.MD5(""+ System.currentTimeMillis()));
            UI_addItem(0,obj);

            obj.put("type", 1);
            obj.put("scores", 4);
            obj.put("title", "读一本小说");
            obj.put("hash", MD5.MD5(""+ System.currentTimeMillis()));
            UI_addItem(0,obj);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
