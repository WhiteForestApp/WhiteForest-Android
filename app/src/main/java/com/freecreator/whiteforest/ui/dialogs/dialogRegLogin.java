package com.freecreator.whiteforest.ui.dialogs;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.ui.fragments.fragmentLogin;
import com.freecreator.whiteforest.ui.fragments.fragmentRegister;
import com.freecreator.whiteforest.ui.views.FontTextView;

/**
 * Created by niko on 2018/3/19.
 */

public class dialogRegLogin {


    private AppCompatActivity m_parent = null;
    private RelativeLayout mDialog = null;

    private FragmentManager manager = null;
    private FragmentTransaction transaction = null;

    private fragmentLogin fragLogin = null;
    private fragmentRegister fragRegister = null;

    private TextView textLogin = null;
    private TextView textRegister = null;

    private FontTextView btnLogin = null;
    private FontTextView btnReg = null;

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

        manager = m_parent.getSupportFragmentManager();

        fragLogin = new fragmentLogin();
        fragRegister = new fragmentRegister();

        textLogin = (TextView) mDialog.findViewById(R.id.textLogin);
        textRegister = (TextView) mDialog.findViewById(R.id.textRegister);



        transaction = manager.beginTransaction();
        transaction.add(R.id.fragment, fragLogin);
        transaction.add(R.id.fragment, fragRegister);
        transaction.show(fragLogin);
        transaction.hide(fragRegister);
        transaction.commit();

    }

    private void setListeners() {
        mDialog.findViewById(R.id.btn_close_dialog).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialogRegLogin.this.hide();
            }
        });
        textLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textLogin.setTextColor(m_parent.getResources().getColor(R.color.dark3));
                textRegister.setTextColor(m_parent.getResources().getColor(R.color.dark9));

                transaction = manager.beginTransaction();
                transaction.show(fragLogin);
                transaction.hide(fragRegister);
                transaction.commit();
            }

        });

        textRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textRegister.setTextColor(m_parent.getResources().getColor(R.color.dark3));
                textLogin.setTextColor(m_parent.getResources().getColor(R.color.dark9));

                transaction = manager.beginTransaction();
                transaction.show(fragRegister);
                transaction.hide(fragLogin);
                transaction.commit();
            }

        });

        btnLogin = (FontTextView)m_parent.findViewById(R.id.btn_login);
        btnReg = (FontTextView)m_parent.findViewById(R.id.btn_reg);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }catch (Exception e){

                }

                m_parent.startActivity(new Intent(m_parent,com.freecreator.whiteforest.ui.mainpage.class));
            }
        }).start();

                /*
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(m_parent,"开始登录",Toast.LENGTH_SHORT).show();
                //LoginThread loginThread = new LoginThread(m_parent,)
                m_parent.startActivity(new Intent(m_parent,com.freecreator.whiteforest.ui.mainpage.class));
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(m_parent,"开始注册",Toast.LENGTH_SHORT).show();
                //RegisterThread registerThread = new RegisterThread(m_parent,);
                m_parent.startActivity(new Intent(m_parent,com.freecreator.whiteforest.ui.mainpage.class));
            }
        });
        */
    }

    public void show(){
        mDialog.setVisibility(View.VISIBLE);
        mDialog.requestFocus();
    }

    public void hide(){
        mDialog.setVisibility(View.INVISIBLE);
    }
}
