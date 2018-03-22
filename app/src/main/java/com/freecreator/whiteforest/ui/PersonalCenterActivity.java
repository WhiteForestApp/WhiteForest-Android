package com.freecreator.whiteforest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.ui.dialogs.dialogRegLogin;
import com.freecreator.whiteforest.ui.utils.AdjustSize;
import com.freecreator.whiteforest.ui.utils.ResourceUtils;
import com.freecreator.whiteforest.ui.utils.Size;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by niko on 2018/3/21.
 */

public class PersonalCenterActivity  extends AppCompatActivity {

    private ImageView ImageView_story_header = null;
    private ImageView ImageView_story_bottom = null;
    private ImageView ImageView_character = null;
    private LinearLayout list_unattained = null;
    private LinearLayout list_attained = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        UI_init();
    }

    private void UI_init() {

        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        ImageView_story_header = (ImageView)findViewById(R.id.ImageView_story_header);
        ImageView_story_bottom = (ImageView)findViewById(R.id.ImageView_story_bottom);

        ImageView_character = (ImageView) findViewById(R.id.ImageView_character);
        ImageView_character.setImageResource(R.drawable.character);

        list_unattained = (LinearLayout)findViewById(R.id.list_unattained);
        list_attained = (LinearLayout)findViewById(R.id.list_attained);

    }


    private void UI_adjust(){
        // 尺寸自适应 根据图片的宽高 来调整view高度 [宽度不调整]
        AdjustSize.adjustHeight(ImageView_story_header, AdjustSize.getImageWidthHeight(this, R.drawable.story_header));
        AdjustSize.adjustHeight(ImageView_story_bottom, AdjustSize.getImageWidthHeight(this, R.drawable.story_bottom));
        AdjustSize.adjustHeight(ImageView_character, AdjustSize.getImageWidthHeight(this, R.drawable.character));
    }

    /**
     *
     * @param position
     * @param data   { "medal_name": "xxx",  "medal_description" : "xxx" , "medal_image" : "medal_30days"}
     */
    private void UI_addUnattainedMedal(int position, JSONObject data){
        if(position < 0)
            return;

        if(null == data)
            return;

        LinearLayout item = (LinearLayout) PersonalCenterActivity.this.getLayoutInflater().inflate(R.layout.item_unattained_medal, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        String medal_name = data.optString("medal_name");
        if(medal_name != null && !medal_name.equals("")){
            TextView text = (TextView)item.findViewById(R.id.text_medal_name);
            text.setText(medal_name);
        }


        String medal_description = data.optString("medal_description");
        if(medal_description != null && !medal_description.equals("")){
            TextView text = (TextView)item.findViewById(R.id.text_medal_description);
            text.setText(medal_description);
        }

        list_unattained.addView(item, position, params);

        String image = data.optString("medal_image");
        if(image != null && !image.equals("")){
            ImageView ImageView_medal = (ImageView)item.findViewById(R.id.ImageView_medal);
            int Resid = ResourceUtils.getImageId(image);
            if(Resid != 0 ){
                ImageView_medal.setImageResource(Resid);

                Size list_task_size = AdjustSize.getMeasuredSize(item);
                Size refSize = AdjustSize.getImageWidthHeight(PersonalCenterActivity.this, Resid);

                float w = (float)list_task_size.height * (float)refSize.width / (float)refSize.height;

                ViewGroup.LayoutParams lp;
                lp = ImageView_medal.getLayoutParams();
                lp.height = (int)w;
                ImageView_medal.setLayoutParams(lp);
            }
        }
    }


    /**
     *
     * @param position
     * @param data   { "medal_image" : "medal_30days"}
     */
    private void UI_addAttainedMedal(int position, JSONObject data){
        if(position < 0)
            return;

        if(null == data)
            return;

        String image = data.optString("medal_image");
        if(image == null || image.equals(""))
            return;

        LinearLayout item = (LinearLayout) PersonalCenterActivity.this.getLayoutInflater().inflate(R.layout.item_attained_medal, null);

        ImageView image_medal = (ImageView)item.findViewById(R.id.image_medal);
        int Resid = ResourceUtils.getImageId(image);
        if(Resid == 0 )
            return;

        image_medal.setImageResource(Resid);

        Size list_size = AdjustSize.getViewSize(list_attained);
        Size refSize = AdjustSize.getImageWidthHeight(PersonalCenterActivity.this, Resid);

        float w  = (float)list_size.height * (float)refSize.width / (float)refSize.height;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)w, list_size.height);

        list_attained.addView(item, position, params);
    }



    private void mockDataForTest(){
        try{
            JSONObject obj1 = new JSONObject();
            obj1.put("medal_name", "耳濡目染");
            obj1.put("medal_description", "连续30天坚持使用");
            obj1.put("medal_image" , "medal_30days");

            JSONObject obj2 = new JSONObject();
            obj2.put("medal_name", "五十而立");
            obj2.put("medal_description", "连续50天坚持使用");
            obj2.put("medal_image" , "medal_50days");

            UI_addUnattainedMedal(0, obj1);
            UI_addUnattainedMedal(0, obj2);
            UI_addUnattainedMedal(0, obj1);
            UI_addUnattainedMedal(0, obj1);
            UI_addUnattainedMedal(0, obj2);
            UI_addUnattainedMedal(0, obj1);
            UI_addUnattainedMedal(0, obj1);
            UI_addUnattainedMedal(0, obj2);
            UI_addUnattainedMedal(0, obj1);
            UI_addUnattainedMedal(0, obj1);
            UI_addUnattainedMedal(0, obj2);
            UI_addUnattainedMedal(0, obj1);


            UI_addAttainedMedal(0, obj1);
            UI_addAttainedMedal(0, obj2);
            UI_addAttainedMedal(0, obj1);
            UI_addAttainedMedal(0, obj1);
            UI_addAttainedMedal(0, obj2);
            UI_addAttainedMedal(0, obj1);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);

        UI_adjust();
        mockDataForTest();
    }
}