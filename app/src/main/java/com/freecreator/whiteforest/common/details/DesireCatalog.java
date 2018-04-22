package com.freecreator.whiteforest.common.details;

import com.freecreator.whiteforest.base.Application;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.freecreator.whiteforest.utils.JsonUtils.jsonArrayPut;
import static com.freecreator.whiteforest.utils.JsonUtils.jsonPut;

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
    public DesireCatalog(JSONObject jsonObject){
        if(null == jsonObject) return;
        uid = jsonObject.optLong("uid",0);
        JSONArray jsonArray = jsonObject.optJSONArray("desireDetailsList");
        itemNum = jsonArray.length();
        long tempTime = 0;
        for(int i = 0;i < itemNum;i++){
            DesireDetails desireDetails = new DesireDetails(jsonArray.optJSONObject(i));
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
        Application.saveData(this);
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
        if(null == desireDetails || !desireDetails.isValid()){
            return false;
        }

        for(int i = 0; i< desireDetailsList.size();i++){
            DesireDetails item = desireDetailsList.get(i);
            if(item.getHash().equals(desireDetails.getHash())){
                if(!desireDetails.equals(item))
                    desireDetailsList.set(i, desireDetails);
                Application.saveData(this);
                return true;
            }
        }

        long tempTime = desireDetails.getDesireAddTime();
        desireDetailsList.add(desireDetails);
        if(itemNum == 0){
            itemEarliestAddTime = tempTime;
        }
        itemNum++;
        itemEarliestAddTime = itemEarliestAddTime > tempTime ? tempTime : itemEarliestAddTime;
        itemLatestAddTime = itemLatestAddTime < tempTime ? tempTime : itemLatestAddTime;
        Application.saveData(this);
        return true;
    }

    public void deleteDesireDetails(String hash){

        for(int i = 0; i< desireDetailsList.size();i++){
            DesireDetails item = desireDetailsList.get(i);
            if(item.getHash().equals(hash)){
                desireDetailsList.remove(i);
                Application.saveData(this);
                return;
            }
        }
    }

    @Override
    public String toString(){
        return toJSONObject().toString();
    }

    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonPut(jsonObject, "uid", ""+uid);
        jsonPut(jsonObject, "itemNum", ""+itemNum);
        jsonPut(jsonObject, "itemEarliestAddTime", ""+itemEarliestAddTime);
        jsonPut(jsonObject, "itemLatestAddTime", ""+itemLatestAddTime);
        for(int i = 0;i < desireDetailsList.size();i++){
            jsonArray.put(desireDetailsList.get(i).toJSONObject());
        }
        jsonArrayPut(jsonObject,"desireDetailsList",jsonArray);
        //jsonPut(jsonObject, "desireDetailsList",jsonArray);
        return jsonObject;
    }

}
