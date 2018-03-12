package com.freecreator.whiteforest.ui.dialogs;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.freecreator.whiteforest.R;

/**
 * Created by niko on 2018/3/12.
 */

public class dialogAddTask {

    private Activity m_parent = null;
    private RelativeLayout mDialog = null;

    public dialogAddTask(Activity parent, RelativeLayout attachment){
        m_parent = parent;

        mDialog = (RelativeLayout)m_parent.getLayoutInflater().inflate(R.layout.dialog_add_task_item, null);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT );

        attachment.addView(mDialog, params);
        mDialog.setVisibility(View.INVISIBLE);
    }

    public void show(){
        mDialog.setVisibility(View.VISIBLE);
    }

    public void hide(){
        mDialog.setVisibility(View.INVISIBLE);
    }
}
