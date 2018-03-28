package com.freecreator.whiteforest.thread;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.freecreator.whiteforest.base.BaseHttpClient;
import com.freecreator.whiteforest.common.cache.LocalCache;
import com.freecreator.whiteforest.common.details.DesireCatalog;
import com.freecreator.whiteforest.common.details.TaskCatalog;
import com.freecreator.whiteforest.common.details.UserDetails;

import org.json.JSONObject;

import java.util.SortedMap;
import java.util.TreeMap;


/**
 * Created by JackYanx on 2018/3/25.
 * 客户端登录线程
 */

public class LoginThread implements Runnable {
    //类标识
    public static final String TAG = LoginThread.class.getPackage().getName() + "LoginThread";
    //服务端用户登录API接口
    public static final String USER_LOGIN_URL = "http://0.0.0.0/userlogin";
    //错误信息
    public static final String[] errMsg = {
            "成功",
            "请求参数有误",
            "网络请求失败",
            "数据解析失败",
            "信息构造有误",
            "数据缓存失败",
            "",
            ""
    };
    private Context mContext;
    private Handler mHandler;
    private String userName;
    private String password;
    private BaseHttpClient baseHttpClient;

    public LoginThread(Context context, Handler handler, String userName, String password){
        this.mContext = context;
        this.mHandler = handler;
        this.userName = userName;
        this.password = password;
        this.baseHttpClient = new BaseHttpClient();
    }

    @Override
    public void run(){
        //此变量记录注册进度
        int processCode = 1;
        try{
            //构造Post参数表
            SortedMap<String,String> params = new TreeMap<>();
            params.put("userName",userName);
            params.put("password",password);
            processCode = 2;

            //发送Post请求
            String result = baseHttpClient.post(USER_LOGIN_URL,params);
            processCode = 3;

            //解析为json数据
            JSONObject userData = new JSONObject(result).optJSONObject("userData");
            JSONObject taskData = new JSONObject(result).optJSONObject("taskData");
            JSONObject desireData = new JSONObject(result).optJSONObject("desireData");
            processCode = 4;

            //生成相关对象
            UserDetails userDetails = new UserDetails(userData);
            TaskCatalog taskCatalog = new TaskCatalog(taskData);
            DesireCatalog desireCatalog = new DesireCatalog(desireData);
            processCode = 5;

            //缓存相关数据
            LocalCache cache = new LocalCache(mContext);
            cache.clear();
            cache.setUserDetails(userDetails);
            cache.setTaskCatalog(taskCatalog);
            cache.setDesireCatalog(desireCatalog);
            processCode = 0;

        }catch (Exception e){
            Log.d(TAG,errMsg[processCode] + e.getMessage());
        }finally {
            sendMessage(processCode,errMsg[0]);
        }
    }

    private void sendMessage(int what, Object object) {
        Message message = mHandler.obtainMessage();
        message.what = what;
        message.obj = object;
        mHandler.sendMessage(message);
    }
}
