package com.freecreator.whiteforest.ui.dialogs;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;

import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.base.Application;
import com.freecreator.whiteforest.common.details.TaskDetails;
import com.freecreator.whiteforest.common.details.UserDetails;
import com.freecreator.whiteforest.ui.TaskActivity;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.freecreator.whiteforest.utils.JsonUtils.jsonPut;


/**
 * Created by niko on 2018/4/12.
 */

public class controllerTimePicker {

    private AppCompatActivity m_parent = null;
    private OptionsPickerView pvCustomOptions = null;

    private ArrayList<String> data_days = new ArrayList<>();
    private ArrayList<String> data_hours = new ArrayList<>();
    private ArrayList<String> data_minutes = new ArrayList<>();

    private TaskDetails task_data = null;

    public controllerTimePicker(AppCompatActivity parent){
        m_parent = parent;

        for(int i = 0; i< 7; i++){
            data_days.add(i  + " 天");
        }

        for(int i = 0; i< 24; i++){
            data_hours.add(i  + " 小时");
        }

        for(int i = 0; i < 60; i++){
            data_minutes.add(i+ " 分钟");
        }

        pvCustomOptions = new OptionsPickerBuilder(m_parent, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

                if(null != task_data && m_parent instanceof TaskActivity){

                    int use_time = options1 * 60 * 60 + options2* 60 + options3+1;
                    task_data.addTimerRecord(use_time);
                    Application.getTaskCatalog(m_parent).addTaskDetails(task_data);
                    UserDetails user = Application.getCurrentUser(m_parent);
                    user.setCoins(user.getCoins() * use_time);
                    user.setRemainExperienceValue(user.getRemainExperienceValue() * use_time);
                }
                task_data = null;
            }
        }).setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
            @Override
            public void customLayout(View v) {
                final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                final TextView tvAdd = (TextView) v.findViewById(R.id.tv_add);
                ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                tvSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pvCustomOptions.returnData();
                        pvCustomOptions.dismiss();
                    }
                });

                ivCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pvCustomOptions.dismiss();
                        task_data = null;
                    }
                });

                tvAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 添加加油卡
                    }
                });

            }
        })
                .isDialog(true)
                .build();

        pvCustomOptions.setNPicker(data_days, data_hours, data_minutes);//添加数据
    }

    public void show(TaskDetails obj){
        task_data = obj;
        pvCustomOptions.show();
    }

    public void hide(){
        pvCustomOptions.dismiss();
    }

}
