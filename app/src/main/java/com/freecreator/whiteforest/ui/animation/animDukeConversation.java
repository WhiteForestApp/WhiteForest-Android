package com.freecreator.whiteforest.ui.animation;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.ui.utils.AdjustSize;
import com.freecreator.whiteforest.ui.utils.Size;
import com.freecreator.whiteforest.ui.utils.UIUtils;

/**
 * Created by niko on 2018/4/13.
 */

public class animDukeConversation {


    private AppCompatActivity m_parent = null;
    private RelativeLayout mDialog = null;
    private ImageView image_player = null;

    private int charactoer_height;


    public animDukeConversation(AppCompatActivity parent, RelativeLayout attachment){
        m_parent = parent;

        mDialog = (RelativeLayout)m_parent.getLayoutInflater().inflate(R.layout.anim_duke_conversation, null);

        Size refSize = AdjustSize.getImageWidthHeight(m_parent, R.drawable.duke);
        Size bgSize = AdjustSize.getScreenSize(m_parent);
        bgSize.width = (int)(bgSize.width * 0.5);
        float h  = (float)bgSize.width * (float)refSize.height / (float)refSize.width;
        charactoer_height = (int)h;

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(bgSize.width, (int)h);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.setMargins(0,0,0, -charactoer_height - 2);

        UI_init();
        attachment.addView(mDialog, params);

        mDialog.setVisibility(View.INVISIBLE);
    }

    private void UI_init() {
        image_player = (ImageView)mDialog.findViewById(R.id.image_player);
    }

    public void show(){
        mDialog.setVisibility(View.VISIBLE);
        mDialog.requestFocus();

        ObjectAnimator animator = ObjectAnimator.ofFloat(mDialog,"translationY",- (int)(charactoer_height * 0.743));
        animator.setDuration(500);
        animator.start();
    }

    public void hide(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(mDialog,"translationY", (int)(charactoer_height * 0.743));
        animator.setDuration(500);
        animator.start();
    }
}
