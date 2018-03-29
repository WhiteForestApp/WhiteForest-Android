package com.freecreator.whiteforest.thread;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.freecreator.whiteforest.base.BaseHttpClient;
import com.freecreator.whiteforest.common.cache.LocalCache;
import com.freecreator.whiteforest.common.details.UserDetails;

import org.json.JSONObject;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by JackYanx on 2018/3/25.
 * 客户端注册线程
 */

public class RegisterThread implements Runnable {
    //类标识
    public static final String TAG = RegisterThread.class.getPackage().getName() + "RegisterThread";
    //服务端用户注册API接口
    public static final String USER_REG_URL = "http://0.0.0.0/userreg";
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
    private String nickName;
    private String motto;
    private BaseHttpClient baseHttpClient;

    public RegisterThread(Context context,Handler handler, String userName, String password, String nickName, String motto){
        this.mContext = context;
        this.mHandler = handler;
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.motto = motto;
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
            params.put("nickName",nickName);
            params.put("motto",motto);
            processCode = 2;

            //发送Post请求
            //String result = baseHttpClient.post(USER_REG_URL,params);
            processCode = 3;

            /*
            * 以下为模拟注册返回内容
            * */

            String result = "{\n" +
                    "    \"errCode\":\"0\",\n" +
                    "    \"userData\":{\n" +
                    "      \"birthday\":\"\",\n" +
                    "      \"finishedTaskNum\":\"0\",\n" +
                    "      \"userStatus\":\"1\",\n" +
                    "      \"level\":\"0\",\n" +
                    "      \"nickName\":\"wftest\",\n" +
                    "      \"sex\":\"\",\n" +
                    "      \"costExperienceValue\":\"0\",\n" +
                    "      \"totalTaskNum\":\"0\",\n" +
                    "      \"userName\":\"wftest\",\n" +
                    "      \"uid\":\"100001\",\n" +
                    "      \"realName\":\"\",\n" +
                    "      \"password\":\"wftpwd\",\n" +
                    "      \"remainExperienceValue\":\"0\",\n" +
                    "      \"phoneNumber\":\"0\",\n" +
                    "      \"motto\":\"Practice Makes Perfect\",\n" +
                    "      \"totalDesireNum\":\"0\",\n" +
                    "      \"segmentLevel\":\"0\",\n" +
                    "      \"totalExperienceValue\":\"0\"\n" +
                    "    }\n" +
                    "}";

            /*
            * 以上为模拟注册返回内容
            * */

            //解析为json数据
            JSONObject userData = new JSONObject(result).optJSONObject("userData");
            processCode = 4;

            //生成UserDetail对象
            UserDetails userDetails = new UserDetails(userData);
            processCode = 5;

            //缓存用户数据
            LocalCache cache = new LocalCache(mContext);
            cache.clear();
            cache.setUserDetails(userDetails);
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
