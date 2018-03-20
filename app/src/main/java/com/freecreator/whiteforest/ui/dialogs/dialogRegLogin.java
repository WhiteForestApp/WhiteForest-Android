package com.freecreator.whiteforest.ui.dialogs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.ui.fragments.fragmentLogin;

/**
 * Created by niko on 2018/3/19.
 */

public class dialogRegLogin {


    private AppCompatActivity m_parent = null;
    private RelativeLayout mDialog = null;

    private FragmentManager manager = null;
    private FragmentTransaction transaction = null;

    private fragmentLogin fragLogin = null;

    public dialogRegLogin(AppCompatActivity parent, RelativeLayout attachment){
        m_parent = parent;

        mDialog = (RelativeLayout)m_parent.getLayoutInflater().inflate(R.layout.dialog_reglogin, null);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT );

        UI_init();
        attachment.addView(mDialog, params);

        mDialog.setVisibility(View.INVISIBLE);

        setListeners();
    }

    private void UI_init() {

        fragLogin = new fragmentLogin();

        manager = m_parent.getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.fragment, fragLogin);
        transaction.commit();

        transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, fragLogin);
        transaction.commit();
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
