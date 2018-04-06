package com.freecreator.whiteforest.ui.dialogs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.ui.TaskActivity;
import com.freecreator.whiteforest.ui.fragments.fragmentNormalTask;
import com.freecreator.whiteforest.ui.fragments.fragmentTimerTask;

import org.json.JSONObject;

import static com.freecreator.whiteforest.ui.TaskActivity.TYPE_NORMAL_TASK;
import static com.freecreator.whiteforest.ui.TaskActivity.TYPE_TIMER_TASK;
import static com.freecreator.whiteforest.utils.JsonUtils.jsonPut;

/**
 * Created by niko on 2018/3/12.
 */

public class dialogAddTask {

    private AppCompatActivity m_parent = null;
    private RelativeLayout mDialog = null;
    private RelativeLayout task_option_bg = null;
    private TextView confirm_btn = null;
    private TextView text_normal_task = null;
    private TextView text_timer_task = null;

    private FragmentManager manager = null;
    private FragmentTransaction transaction = null;

    private fragmentNormalTask fragNormalTask = null;
    private fragmentTimerTask fragTimerTask = null;
    private Fragment fragCurrent = null;

    public dialogAddTask(AppCompatActivity parent, RelativeLayout attachment){
        m_parent = parent;

        mDialog = (RelativeLayout)m_parent.getLayoutInflater().inflate(R.layout.dialog_add_task_item, null);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT );

        UI_init();
        attachment.addView(mDialog, params);

        mDialog.setVisibility(View.INVISIBLE);

        setListeners();
    }

    private void UI_init() {

        manager = m_parent.getSupportFragmentManager();

        task_option_bg = (RelativeLayout)mDialog.findViewById(R.id.task_option_bg);
        text_normal_task = (TextView) mDialog.findViewById(R.id.text_normal_task);
        text_timer_task = (TextView)mDialog.findViewById(R.id.text_timer_task);
        confirm_btn = (TextView)mDialog.findViewById(R.id.confirm_btn);

        fragNormalTask = new fragmentNormalTask();
        fragTimerTask = new fragmentTimerTask();

        transaction = manager.beginTransaction();
        transaction.add(R.id.fragment, fragNormalTask);
        transaction.add(R.id.fragment, fragTimerTask);
        transaction.commit();

        setCurrentFrag(fragNormalTask);

    }

    private void setListeners() {
        mDialog.findViewById(R.id.btn_close_dialog).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialogAddTask.this.hide();
            }
        });

        confirm_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(null == fragCurrent)
                    return;

                View v =  fragCurrent.getView();
                EditText edit = (EditText)v.findViewById(R.id.edit_exp);
                String strExp = edit.getText().toString();

                if(null == strExp || strExp.equals("")){
                    Toast.makeText(m_parent.getBaseContext(), m_parent.getResources().getString(R.string.tips_input_exp), Toast.LENGTH_SHORT).show();
                    return;
                }

                edit = (EditText)v.findViewById(R.id.edit_coins);
                String strCoins = edit.getText().toString();

                if(null == strCoins || strCoins.equals("")){
                    Toast.makeText(m_parent.getApplicationContext(), m_parent.getResources().getText(R.string.tips_input_coins), Toast.LENGTH_SHORT).show();
                    return;
                }

                edit = (EditText)v.findViewById(R.id.edit_content);
                String strContent = edit.getText().toString();

                if(null == strContent || strContent.equals("")){
                    Toast.makeText(m_parent.getApplicationContext(), m_parent.getResources().getText(R.string.tips_input_content), Toast.LENGTH_SHORT).show();
                    return;
                }

                if(fragCurrent == fragTimerTask){

                    //  { "type" : 1,  "title" : "blablabla", "scores" : 6, "exp" : 13}
                    JSONObject task_obj = new JSONObject();
                    jsonPut(task_obj, "type", TYPE_TIMER_TASK);
                    jsonPut(task_obj, "title", strContent);
                    jsonPut(task_obj, "scores", strCoins );
                    jsonPut(task_obj, "exp", strExp );

                    if(m_parent instanceof TaskActivity){
                        TaskActivity taskActivity = (TaskActivity)m_parent;
                        taskActivity.UI_addItem(-1, task_obj);
                    }

                }else if(fragCurrent == fragNormalTask){

                    JSONObject task_obj = new JSONObject();
                    jsonPut(task_obj, "type", TYPE_NORMAL_TASK);
                    jsonPut(task_obj, "title", strContent);
                    jsonPut(task_obj, "scores", strCoins );
                    jsonPut(task_obj, "exp", strExp );

                    if(m_parent instanceof TaskActivity){
                        TaskActivity taskActivity = (TaskActivity)m_parent;
                        taskActivity.UI_addItem(-1, task_obj);
                    }
                }

                dialogAddTask.this.hide();
            }
        });


        text_normal_task.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setCurrentFrag(fragNormalTask);
            }

        });

        text_timer_task.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setCurrentFrag(fragTimerTask);
            }

        });
    }

    private void setCurrentFrag( Fragment frag){
        if(null == frag)
            return;

        if(fragCurrent == frag)
            return;

        if(frag == fragNormalTask){
            transaction = manager.beginTransaction();
            transaction.show(fragNormalTask);
            transaction.hide(fragTimerTask);
            transaction.commit();

            fragCurrent = fragNormalTask;
            text_normal_task.setTextColor(m_parent.getResources().getColor(R.color.whitef1));
            text_timer_task.setTextColor(m_parent.getResources().getColor(R.color.whitecf));

        }else if(frag == fragTimerTask){

            transaction = manager.beginTransaction();
            transaction.show(fragTimerTask);
            transaction.hide(fragNormalTask);
            transaction.commit();

            fragCurrent =  fragTimerTask;
            text_timer_task.setTextColor(m_parent.getResources().getColor(R.color.whitef1));
            text_normal_task.setTextColor(m_parent.getResources().getColor(R.color.whitecf));
        }
    }

    public void show(){
        mDialog.setVisibility(View.VISIBLE);
        mDialog.requestFocus();
    }

    public void hide(){
        mDialog.setVisibility(View.INVISIBLE);

        // Clear the editText content

        View v =  fragNormalTask.getView();
        EditText edit = (EditText)v.findViewById(R.id.edit_exp);
        edit.setText("");
        edit = (EditText)v.findViewById(R.id.edit_coins);
        edit.setText("");
        edit = (EditText)v.findViewById(R.id.edit_content);
        edit.setText("");

        v =  fragTimerTask.getView();
        edit = (EditText)v.findViewById(R.id.edit_exp);
        edit.setText("");
        edit = (EditText)v.findViewById(R.id.edit_coins);
        edit.setText("");
        edit = (EditText)v.findViewById(R.id.edit_content);
        edit.setText("");

    }
}
