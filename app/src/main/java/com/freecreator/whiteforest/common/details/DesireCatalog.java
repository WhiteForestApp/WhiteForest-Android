package com.freecreator.whiteforest.common.details;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.freecreator.whiteforest.common.details.AbstractDetails.jsonPut;

/**
 * Created by JackYanx on 2018/3/15.
 * 欲望目录列表类
 */

public class DesireCatalog {
    private long uid;
    private long itemNum;
    private long itemEarliestAddTime;
    private long itemLatestAddTime;
    private List<DesireDetails> desireDetailsList = new ArrayList<>();

    public DesireCatalog(){}

    /*
    * 使用JSON初始化欲望目录列表
    * */
    public DesireCatalog(JSONObject jsonObject) throws JSONException{
        if(null == jsonObject) return;
        JSONArray jsonArray = jsonObject.getJSONArray("desireDetailsList");
        itemNum = jsonArray.length();
        long tempTime = 0;
        for(int i = 0;i < itemNum;i++){
            DesireDetails desireDetails = new DesireDetails(jsonArray.getJSONObject(i));
            tempTime = desireDetails.getDesireAddTime();
            desireDetailsList.add(desireDetails);
            if(i == 0){
                itemEarliestAddTime = tempTime;
            }
            itemEarliestAddTime = itemEarliestAddTime > tempTime ? tempTime : itemEarliestAddTime;
            itemLatestAddTime = itemLatestAddTime < tempTime ? tempTime : itemLatestAddTime;
        }

    }

    public void setUID(long uid){
        this.uid = uid;
    }

    public long getItemNum(){
        return itemNum;
    }
    public long getItemEarliestAddTime(){
        return itemEarliestAddTime;
    }
    public long getItemLatestAddTime(){
        return itemLatestAddTime;
    }
    public List<DesireDetails> getDesireDetailsList(){
        return desireDetailsList;
    }

    /*向本列表中添加欲望项目*/
    public boolean addDesireDetails(DesireDetails desireDetails){
        if(null == desireDetails || desireDetails.isInvalid()){
            return false;
        }
        desireDetailsList.add(desireDetails);
        return true;
    }

    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonPut(jsonObject, "uid", ""+uid);
        jsonPut(jsonObject, "itemNum", ""+itemNum);
        jsonPut(jsonObject, "itemEarliestAddTime", ""+itemEarliestAddTime);
        jsonPut(jsonObject, "itemLatestAddTime", ""+itemLatestAddTime);
        for(int i = 0;i < desireDetailsList.size();i++){
            jsonArray.put(desireDetailsList.get(i).toString());
        }
        jsonPut(jsonObject, "desireDetailsList",jsonArray.toString());
        return jsonObject.toString();
    }

}
