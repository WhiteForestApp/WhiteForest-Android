package com.freecreator.whiteforest.ui.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.ui.utils.AdjustSize;
import com.freecreator.whiteforest.ui.utils.Size;

/**
 * Created by niko on 2018/4/13.
 */

public class animConversationDialog {


    private AppCompatActivity m_parent = null;
    private RelativeLayout mDialog = null;



    public animConversationDialog(AppCompatActivity parent, RelativeLayout attachment){
        m_parent = parent;

        mDialog = (RelativeLayout)m_parent.getLayoutInflater().inflate(R.layout.anim_conversation_dialog, null);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        UI_init();
        attachment.addView(mDialog, params);

        mDialog.setVisibility(View.INVISIBLE);
    }

    private void UI_init() {

    }

    public void show(){
        mDialog.setVisibility(View.VISIBLE);
        mDialog.requestFocus();

        View view = mDialog;
        view.setPivotX(view.getWidth()/2);  // X方向中点
        view.setPivotY(view.getHeight());   // Y方向底边
        AnimatorSet animatorSet = new AnimatorSet();  //组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0f, 1.0f);
        animatorSet.setDuration(500);  //动画时间
        animatorSet.setInterpolator(new DecelerateInterpolator());  //设置插值器
        animatorSet.play(scaleX).with(scaleY);  //同时执行
        animatorSet.start();  //启动动画
    }

    public void hide(){
        mDialog.setVisibility(View.INVISIBLE);
    }
}
