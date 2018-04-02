package com.freecreator.whiteforest.common.details;

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

    /*向本列表中添加欲望项目,以desireID为标识记录,如有重复则无法添加*/
    public boolean addDesireDetails(DesireDetails desireDetails){
        if(null == desireDetails || !desireDetails.isValid()|| isInList(desireDetails.getDesireID())){
            return false;
        }
        long tempTime = desireDetails.getDesireAddTime();
        desireDetailsList.add(desireDetails);
        if(itemNum == 0){
            itemEarliestAddTime = tempTime;
        }
        itemNum++;
        itemEarliestAddTime = itemEarliestAddTime > tempTime ? tempTime : itemEarliestAddTime;
        itemLatestAddTime = itemLatestAddTime < tempTime ? tempTime : itemLatestAddTime;
        return true;
    }

    /*从本列表中删除欲望项目*/
    public boolean delTaskDetails(long desireID){
        if(false == isInList(desireID)){
            return false;
        }
        DesireDetails tmp;
        for (int i = 0; i < desireDetailsList.size(); i++) {
            tmp = desireDetailsList.get(i);
            if(tmp != null && tmp.getDesireID() == desireID){
                desireDetailsList.remove(tmp);
                itemNum--;
                /*
                long tempTime = tmp.getTaskCreateTime();
                itemEarliestCreateTime = itemEarliestCreateTime > tempTime ? tempTime : itemEarliestCreateTime;
                itemLatestCreateTime = itemLatestCreateTime < tempTime ? tempTime : itemLatestCreateTime;
                */
                return true;
            }
        }
        return false;
    }

    /*判断具有desireID值的欲望是否在当前列表中*/
    public boolean isInList(long desireID){
        if(null == desireDetailsList || desireDetailsList.size() == 0){
            return false;
        }
        DesireDetails tmp;
        for (int i = 0; i < desireDetailsList.size(); i++) {
            tmp = desireDetailsList.get(i);
            if(tmp != null && tmp.getDesireID() == desireID){
                return true;
            }
        }
        return false;
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
