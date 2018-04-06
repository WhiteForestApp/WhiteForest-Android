package com.freecreator.whiteforest.ui.dialogs;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.ui.views.FontTextView;

/**
 * Created by niko on 2018/3/12.
 */

public class dialogAddDesire {

    private Activity m_parent = null;
    private RelativeLayout mDialog = null;
    private EditText money=null;
    private EditText exp=null;
    private String s1 = null;
    private String s2 = null;
    private FontTextView right=null;
    public dialogAddDesire(Activity parent, RelativeLayout attachment){

        m_parent = parent;

        mDialog = (RelativeLayout)m_parent.getLayoutInflater().inflate(R.layout.dialog_add_desire_item, null);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT );

        UI_init();
        attachment.addView(mDialog, params);

        mDialog.setVisibility(View.INVISIBLE);

        setListeners();
    }

    private void UI_init() {
        money=(EditText) mDialog.findViewById(R.id.btn_money);
        exp = (EditText) mDialog.findViewById(R.id.exp_btn);
        right = (FontTextView)mDialog.findViewById(R.id.fontTextView);
    }

    private void setListeners() {
        mDialog.findViewById(R.id.btn_close_dialog).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialogAddDesire.this.hide();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = money.getText().toString();
                s2 = exp.getText().toString();
                if("".equals(s1) || "".equals(s2)){
                    Toast.makeText(m_parent,"kongzfc",Toast.LENGTH_SHORT).show();

                }
                else{
                    money.setText("");
                    exp.setText(" ");
                    Toast.makeText(m_parent,"成功",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void show(){
        mDialog.setVisibility(View.VISIBLE);
        mDialog.requestFocus();
    }

    public void hide(){
        mDialog.setVisibility(View.INVISIBLE);
    }
}
