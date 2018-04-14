package com.freecreator.whiteforest.ui.animation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.ui.TaskActivity;
import com.freecreator.whiteforest.ui.fragments.fragmentNormalTask;
import com.freecreator.whiteforest.ui.fragments.fragmentTimerTask;
import com.freecreator.whiteforest.ui.utils.AdjustSize;
import com.freecreator.whiteforest.ui.utils.Size;

import org.json.JSONObject;

import static com.freecreator.whiteforest.ui.TaskActivity.TYPE_NORMAL_TASK;
import static com.freecreator.whiteforest.ui.TaskActivity.TYPE_TIMER_TASK;
import static com.freecreator.whiteforest.utils.JsonUtils.jsonPut;

/**
 * Created by niko on 2018/3/12.
 */

public class animAchevement {

    private AppCompatActivity m_parent = null;
    private RelativeLayout mDialog = null;
    private ImageView image_player = null;
    private AnimationDrawable animaition = null;


    public animAchevement(AppCompatActivity parent, RelativeLayout attachment){
        m_parent = parent;

        mDialog = (RelativeLayout)m_parent.getLayoutInflater().inflate(R.layout.anim_achevement, null);

        Size refSize = AdjustSize.getImageWidthHeight(m_parent, R.drawable.anim01);
        Size bgSize = AdjustSize.getScreenSize(m_parent);
        float h  = (float)bgSize.width * (float)refSize.height / (float)refSize.width;

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(bgSize.width, (int)h);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        UI_init();
        attachment.addView(mDialog, params);

        mDialog.setVisibility(View.INVISIBLE);
    }

    private void UI_init() {
        image_player = (ImageView)mDialog.findViewById(R.id.image_player);
        image_player.setBackgroundResource(R.drawable.anim_play_image);
        animaition = (AnimationDrawable)image_player.getBackground();
        animaition.setOneShot(true);
    }


    public void show(){
        mDialog.setVisibility(View.VISIBLE);
        mDialog.requestFocus();
        animaition.stop();
        animaition.start();
    }

    public void hide(){
        mDialog.setVisibility(View.INVISIBLE);
        animaition.stop();
    }

}
