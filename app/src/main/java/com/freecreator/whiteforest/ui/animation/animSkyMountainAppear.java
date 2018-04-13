package com.freecreator.whiteforest.ui.animation;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.ui.utils.AdjustSize;
import com.freecreator.whiteforest.ui.utils.Size;

/**
 * Created by niko on 2018/4/13.
 */

public class animSkyMountainAppear {


    private AppCompatActivity m_parent = null;

    private RelativeLayout v_sky = null;
    private RelativeLayout v_mountain = null;

    private final int transitionOffset = 300;

    public animSkyMountainAppear(AppCompatActivity parent, RelativeLayout attachment){
        m_parent = parent;

        v_sky = (RelativeLayout)m_parent.getLayoutInflater().inflate(R.layout.anim_sky_fall, null);

        Size refSize = AdjustSize.getImageWidthHeight(m_parent, R.drawable.story_header);
        Size bgSize = AdjustSize.getScreenSize(m_parent);

        float h  = (float)bgSize.width * (float)refSize.height / (float)refSize.width;

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(bgSize.width, (int)h);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.setMargins(0,-transitionOffset,0,0);

        attachment.addView(v_sky, params);
        v_sky.setVisibility(View.INVISIBLE);


        v_mountain = (RelativeLayout)m_parent.getLayoutInflater().inflate(R.layout.anim_mountain_popup, null);

        refSize = AdjustSize.getImageWidthHeight(m_parent, R.drawable.story_bottom);

        h  = (float)bgSize.width * (float)refSize.height / (float)refSize.width;

        params = new RelativeLayout.LayoutParams(bgSize.width, (int)h);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.setMargins(0,0,0,-transitionOffset);

        attachment.addView(v_mountain, params);
        v_mountain.setVisibility(View.INVISIBLE);

        UI_init();
    }

    private void UI_init() {


    }

    public void show(){
        v_mountain.setVisibility(View.VISIBLE);
        v_sky.setVisibility(View.VISIBLE);

        ObjectAnimator animator = ObjectAnimator.ofFloat(v_mountain,"translationY",-transitionOffset);
        animator.setDuration(600);
        animator.start();

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(v_sky,"translationY",transitionOffset);
        animator2.setDuration(600);
        animator2.start();
    }

    public void hide(){

        v_mountain.setVisibility(View.INVISIBLE);
        v_sky.setVisibility(View.INVISIBLE);
    }
}
