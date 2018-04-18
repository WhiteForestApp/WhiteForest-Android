package com.freecreator.whiteforest.common.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.freecreator.whiteforest.base.Application;
import com.freecreator.whiteforest.common.details.DesireCatalog;
import com.freecreator.whiteforest.common.details.TaskCatalog;
import com.freecreator.whiteforest.common.details.UserDetails;

import org.json.JSONObject;

import static com.freecreator.whiteforest.utils.JsonUtils.optStrToJsonObject;

/**
 * Created by JackYanx on 2018/3/18.
 * LocalCache类,用于处理用户信息缓存,任务列表缓存,欲望列表缓存
 * 基于SharedPreferences
 */

public class LocalCache extends AbstractCache{

    public static final String USERDETAILSINFO = "USERDETAILSINFO";
    public static final String TASKCATALOGINFO = "TASKCATALOGINFO";
    public static final String DESIRECATALOGINFO = "DESIRECATALOGINFO";

    /*
    * 构造函数,传入Context
    * */
    public LocalCache(Context context){
        super(context);
    }

    /*
    * 清除LocalCache模块下的全部缓存(用户信息缓存,任务列表缓存,欲望列表缓存)
    */
    @Override
    public void clear() {
        clearUserDetails();
        clearTaskCatalog();
        clearDesireCatalog();
    }

    /*
    * 从缓存中获取用户详细信息
    */
    @Nullable
    public UserDetails getUserDetails(String user_hash){
        UserDetails userDetails = null;
        String str = sharedPreferences.getString(user_hash, "");
        if(null == str || str.equals(""))
            return null;

        JSONObject jsonObject = optStrToJsonObject(str);
        userDetails = new UserDetails(jsonObject);
        return userDetails;
    }

    /*
    * 更新缓存中用户详细信息
    * */
    public void setUserDetails( UserDetails userDetails){
        if(null == userDetails || !userDetails.isValid()){
            return;
        }

        String user_hash = userDetails.getHash();
        if(user_hash.equals(""))
            return;

        String str = userDetails.toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(user_hash, str);
        editor.apply();
    }

    /*
    *清除缓存中用户详细信息
    * */
    public void clearUserDetails(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Application.getUser().getData().optString("hash"), "");
        editor.apply();
    }

    /*
    * 从缓存中获取任务列表信息
    */
    @Nullable
    public TaskCatalog getTaskCatalog(){
        String user_hash = Application.getUser().getData().optString("hash");

        TaskCatalog taskCatalog = null;
        String str = sharedPreferences.getString(user_hash + TASKCATALOGINFO, "");
        JSONObject jsonObject = optStrToJsonObject(str);
        taskCatalog = new TaskCatalog(jsonObject);
        return taskCatalog;
    }

    /*
    * 更新缓存中任务列表信息
    * */
    public void setTaskCatalog(TaskCatalog taskCatalog){
        if(null == taskCatalog){
            return;
        }
        String user_hash = Application.getUser().getData().optString("hash");

        String str = taskCatalog.toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(user_hash + TASKCATALOGINFO, str);
        editor.apply();
    }

    /*
    *清除缓存中任务列表信息
    * */
    public void clearTaskCatalog(){
        String user_hash = Application.getUser().getData().optString("hash");

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(user_hash + TASKCATALOGINFO, "");
        editor.apply();
    }

    /*
    * 从缓存中获取欲望列表信息
    */
    @Nullable
    public DesireCatalog getDesireCatalog(){
        DesireCatalog desireCatalog = null;
        String user_hash = Application.getUser().getData().optString("hash");

        String str = sharedPreferences.getString(user_hash+ DESIRECATALOGINFO, "");
        JSONObject jsonObject = optStrToJsonObject(str);
        desireCatalog = new DesireCatalog(jsonObject);
        return desireCatalog;
    }

    /*
    * 更新缓存中欲望列表信息
    * */
    public void setDesireCatalog(DesireCatalog desireCatalog){
        if(null == desireCatalog){
            return;
        }
        String user_hash = Application.getUser().getData().optString("hash");

        String str = desireCatalog.toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(user_hash+DESIRECATALOGINFO, str);
        editor.apply();
    }

    /*
    *清除缓存中欲望列表信息
    * */
    public void clearDesireCatalog(){

        String user_hash = Application.getUser().getData().optString("hash");

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(user_hash+ DESIRECATALOGINFO, "");
        editor.apply();
    }
}
