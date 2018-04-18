package com.freecreator.whiteforest.base;

import android.content.Context;

import com.freecreator.whiteforest.common.cache.LocalCache;
import com.freecreator.whiteforest.common.details.UserDetails;
import com.freecreator.whiteforest.utils.MD5;

import org.json.JSONObject;

import static com.freecreator.whiteforest.utils.JsonUtils.jsonPut;

/**
 * Created by niko on 2018/4/18.
 */

public class User {

    private Context m_context;
    private JSONObject m_data;

    public User(Context context){
        m_context = context;
        m_data = null;
    }

    public User(Context context, String hash){
        m_context = context;

        LocalCache cache =  new LocalCache(m_context);
        UserDetails details = cache.getUserDetails(hash);

        if(null != details){
            m_data = details.toJSONObject();
        }
    }

    public boolean isValid(){
        if(null == m_data)
            return false;

        return true;
    }

    public User(Context context, String username, String password){
        m_context = context;

        String hash =  MD5.MD5(username + password);
        LocalCache cache =  new LocalCache(m_context);
        UserDetails details = cache.getUserDetails(hash);

        if(null != details){
            m_data = details.toJSONObject();
        }
    }

    public void saveData(){
        if(null == m_data)
            return;

        String hash = m_data.optString("hash", "");

        if(!hash.equals("")){
            UserDetails details = new UserDetails(m_data);
            LocalCache cache =  new LocalCache(m_context);
            cache.setUserDetails(details);
        }
    }

    public static User createUser(Context context, String username, String password, String nickname){
        User user = new User(context);

        user.m_data = new JSONObject();
        jsonPut(user.m_data, "hash", MD5.MD5(username + password));
        jsonPut(user.m_data, "username", username);
        jsonPut(user.m_data, "password", password);
        jsonPut(user.m_data, "nickname", nickname);

        user.saveData();

        return user;
    }

    public JSONObject getData(){
        return m_data;
    }
}
