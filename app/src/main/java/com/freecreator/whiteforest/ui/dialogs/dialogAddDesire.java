package com.freecreator.whiteforest.ui.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.ui.DesireActivity;
import com.freecreator.whiteforest.ui.utils.Size;
import com.freecreator.whiteforest.ui.utils.UIUtils;

import org.json.JSONObject;

import static com.freecreator.whiteforest.ui.DesireActivity.TYPE_NORMAL_DESIRE;
import static com.freecreator.whiteforest.utils.JsonUtils.jsonPut;
import static com.freecreator.whiteforest.utils.MD5.MD5;

/**
 * Created by niko on 2018/3/12.
 */

public class dialogAddDesire extends Dialog {

    private Activity m_parent = null;
    private RelativeLayout mDialog = null;
    private Context context;

    private TextView confirm_btn2 = null;

    public dialogAddDesire(Context context) {
        super(context);
        this.context = context;
    }

    public dialogAddDesire(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    public dialogAddDesire(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialog = (RelativeLayout) View.inflate(context, R.layout.dialog_add_desire_item, null);
        setContentView(mDialog);

        setCanceledOnTouchOutside(false);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        Size screen =  UIUtils.getScreenSize(context);
        lp.height = screen.height;
        lp.width = screen.width;
        win.setAttributes(lp);

        if(context instanceof Activity)
            m_parent = (Activity)context;

        UI_init();
        setListeners();
    }

    /*
    public dialogAddDesire(Activity parent, RelativeLayout attachment){
        m_parent = parent;

        mDialog = (RelativeLayout)m_parent.getLayoutInflater().inflate(R.layout.dialog_add_desire_item, null);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT );

        UI_init();
        attachment.addView(mDialog, params);

        mDialog.setVisibility(View.INVISIBLE);

        setListeners();
    }
    */

    private void UI_init() {
        confirm_btn2 = (TextView)mDialog.findViewById(R.id.confirm_btn2);
    }

    private void setListeners() {
        mDialog.findViewById(R.id.btn_close_dialog).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialogAddDesire.this.hide();
            }
        });
        confirm_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null == mDialog)
                    return;

                EditText edit = (EditText) mDialog.findViewById(R.id.edit_consumecoins);

                String consumeCoins = edit.getText().toString();

                if(null == consumeCoins || consumeCoins.equals("")){
                    Toast.makeText(m_parent.getBaseContext(), m_parent.getResources().getString(R.string.tips_input_coins), Toast.LENGTH_SHORT).show();
                    return;
                }

                edit = (EditText) mDialog.findViewById(R.id.edit_desirecontent);

                String desireContent = edit.getText().toString();

                if(null == desireContent || desireContent.equals("")){
                    Toast.makeText(m_parent.getBaseContext(), m_parent.getResources().getString(R.string.tips_input_content), Toast.LENGTH_SHORT).show();
                    return;
                }

                JSONObject desire_obj = new JSONObject();
                jsonPut(desire_obj, "hash",  MD5(System.currentTimeMillis() + consumeCoins + desireContent));
                jsonPut(desire_obj, "type", TYPE_NORMAL_DESIRE);
                jsonPut(desire_obj, "title", desireContent);
                jsonPut(desire_obj, "scores", consumeCoins );

                if(m_parent instanceof DesireActivity){
                    DesireActivity desireActivity = (DesireActivity)m_parent;
                    desireActivity.UI_addItem(-1, desire_obj);
                }

                dialogAddDesire.this.hide();
            }
        });


    }

    @Override
    public void hide(){

        View v =  mDialog;
        EditText edit = (EditText)v.findViewById(R.id.edit_consumecoins);
        edit.setText("");
        edit = (EditText)v.findViewById(R.id.edit_desirecontent);
        edit.setText("");

        super.hide();
    }
}
