package com.freecreator.whiteforest.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freecreator.whiteforest.R;

/**
 * Created by niko on 2018/3/20.
 */

public class fragmentLogin extends Fragment {

    private Context mContext = null;
    private View fragmentView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        return fragmentView;
    }

}
