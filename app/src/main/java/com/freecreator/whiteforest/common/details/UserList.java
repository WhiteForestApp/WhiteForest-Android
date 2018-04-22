package com.freecreator.whiteforest.common.details;

import com.freecreator.whiteforest.base.Application;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.freecreator.whiteforest.utils.JsonUtils.jsonArrayPut;
import static com.freecreator.whiteforest.utils.JsonUtils.jsonPut;

/**
 * Created by niko on 2018/4/19.
 */

public class UserList extends AbstractDetails {

    private String default_user = "";
    private ArrayList<String> userlist = new ArrayList<>();

    public String getDefaultUser(){
        return  default_user;
    }

    public ArrayList<String> getUserList(){
        return userlist;
    }

    public void setDefaultUser(String name){
        default_user = name;
        Application.saveData(this);
    }

    public boolean isValid(){
        return true;
    }

    public void build(JSONObject jsonObject){

        if(jsonObject == null)
            return;

        default_user = jsonObject.optString("default_user",UNDEF);

        userlist = new ArrayList<>();
        JSONArray a = jsonObject.optJSONArray("user_list");
        if(null != a){
            for(int i = 0; i < a.length(); i++){
                userlist.add(a.optString(i));
            }
        }
    }

    @Override
    public String toString(){
        return toJSONObject().toString();
    }

    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        jsonPut(jsonObject, "default_user", default_user);

        JSONArray a = new JSONArray();
        for(int i = 0; i < userlist.size(); i++){
            a.put(userlist.get(i));
        }
        jsonArrayPut(jsonObject, "user_list", a);

        return jsonObject;
    }

    public UserList(JSONObject json){
        build(json);
    }
}
