package com.freecreator.whiteforest.common.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.freecreator.whiteforest.base.Application;
import com.freecreator.whiteforest.common.details.DesireCatalog;
import com.freecreator.whiteforest.common.details.TaskCatalog;
import com.freecreator.whiteforest.common.details.UserDetails;
import com.freecreator.whiteforest.common.details.UserList;

import org.json.JSONObject;

import static com.freecreator.whiteforest.utils.JsonUtils.optStrToJsonObject;

/**
 * Created by JackYanx on 2018/3/18.
 * LocalCache类,用于处理用户信息缓存,任务列表缓存,欲望列表缓存
 * 基于SharedPreferences
 */

public class LocalCache extends AbstractCache{

    private static final String USERDETAILSINFO = "USERDETAILSINFO";
    private static final String TASKCATALOGINFO = "TASKCATALOGINFO";
    private static final String DESIRECATALOGINFO = "DESIRECATALOGINFO";
    private static final String USER_LIST = "USER_LIST";

    /*
    * 构造函数,传入Context
    * 第一个参数 Application access 的用途在于控制权限, 用于保证有且仅有 Application 类才可以构造本类
    * 这样, 所有的数据存取操作都要经过 Application 类
    * */
    public LocalCache(Application access, Context context){
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
    public boolean setUserDetails( UserDetails userDetails){
        if(null == userDetails || !userDetails.isValid()){
            return false;
        }

        String user_hash = userDetails.getUserName();
        if(user_hash.equals(""))
            return false;

        String str = userDetails.toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(user_hash, str);
        editor.apply();

        return true;
    }

    /*
    *清除缓存中用户详细信息
    * */
    public void clearUserDetails(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Application.getCurrentUser(context).getUserName(), "");
        editor.apply();
    }

    /*
    * 从缓存中获取任务列表信息
    */
    @Nullable
    public TaskCatalog getTaskCatalog(){
        String user_hash = Application.getCurrentUser(context).getUserName();

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
        String user_hash = Application.getCurrentUser(context).getUserName();

        String str = taskCatalog.toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(user_hash + TASKCATALOGINFO, str);
        editor.apply();
    }

    /*
    *清除缓存中任务列表信息
    * */
    public void clearTaskCatalog(){
        String user_hash = Application.getCurrentUser(context).getUserName();

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
        String user_hash = Application.getCurrentUser(context).getUserName();

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
        String user_hash = Application.getCurrentUser(context).getUserName();

        String str = desireCatalog.toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(user_hash+DESIRECATALOGINFO, str);
        editor.apply();
    }

    /*
    *清除缓存中欲望列表信息
    * */
    public void clearDesireCatalog(){

        String user_hash = Application.getCurrentUser(context).getUserName();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(user_hash+ DESIRECATALOGINFO, "");
        editor.apply();
    }


    @Nullable
    public UserList getUserList(){

        String str = sharedPreferences.getString(USER_LIST, "");
        JSONObject jsonObject = optStrToJsonObject(str);
        UserList userlist = new UserList(jsonObject);
        return userlist;
    }

    /*
    * 更新缓存中欲望列表信息
    * */
    public void setUserList(UserList userlist){
        if(null == userlist){
            return;
        }
        String str = userlist.toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_LIST, str);
        editor.apply();
    }

    /*
    *清除缓存中欲望列表信息
    * */
    public void clearUserList(){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_LIST, "");
        editor.apply();
    }
}
