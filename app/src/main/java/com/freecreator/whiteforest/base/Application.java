package com.freecreator.whiteforest.base;

import android.content.Context;

import com.freecreator.whiteforest.common.details.UserDetails;

/**
 * Created by niko on 2018/4/18.
 */

public class Application {

    private static User m_user;

    public static void init(Context context){
        m_user = new User(context, "123", "123");

        if(!m_user.isValid()){
            m_user = User.createUser(context, "123", "123", "niko");
            m_user.saveData();
        }
    }

    public static User getUser(){
        return m_user;
    }
}
