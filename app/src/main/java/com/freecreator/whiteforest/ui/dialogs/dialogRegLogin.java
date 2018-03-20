package com.freecreator.whiteforest.ui.dialogs;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.freecreator.whiteforest.R;

/**
 * Created by niko on 2018/3/19.
 */

public class dialogRegLogin {


    private Activity m_parent = null;
    private RelativeLayout mDialog = null;

    public dialogRegLogin(Activity parent, RelativeLayout attachment){
        m_parent = parent;

        mDialog = (RelativeLayout)m_parent.getLayoutInflater().inflate(R.layout.dialog_reglogin, null);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT );

        UI_init();
        attachment.addView(mDialog, params);

        mDialog.setVisibility(View.INVISIBLE);

        setListeners();
    }

    private void UI_init() {
    }

    private void setListeners() {
        mDialog.findViewById(R.id.btn_close_dialog).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialogRegLogin.this.hide();
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
