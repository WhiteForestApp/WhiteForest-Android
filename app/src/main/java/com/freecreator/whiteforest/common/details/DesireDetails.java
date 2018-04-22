package com.freecreator.whiteforest.common.details;

import org.json.JSONException;
import org.json.JSONObject;

import static com.freecreator.whiteforest.utils.JsonUtils.jsonPut;

/**
 * Created by JackYanx on 2018/3/15.
 * 欲望详细信息类
 */

public class DesireDetails extends AbstractDetails{


    private JSONObject data = null;
    private String hash = null;

    /*欲望拥有者(用户)唯一标识*/
    //private long uid;
    /*欲望唯一标识*/
    private long desireID;
    /*欲望标题*/
    private String desireTitle;
    /*欲望描述*/
    private String desireDescription;
    /*欲望类型*/
    private String desireType;
    /*欲望添加时间*/
    private long desireAddTime;
    /*欲望实现时间*/
    private long desireFulfillmentTime;
    /*欲望状态*/
    private int desireStatus;
    /*欲望实现所消耗经验值*/
    private long desireCostExperienceValue;

    /*
    public void setUID(long uid){
        this.uid = uid;
    }
    */
    public void setDesireID(long desireID){
        this.desireID = desireID;
    }
    public void setDesireTitle(String desireTitle){
        this.desireTitle = desireTitle;
    }
    public void setDesireDescription(String desireDescription){
        this.desireDescription = desireDescription;
    }
    public void setDesireType(String desireType){
        this.desireType = desireType;
    }
    public void setDesireAddTime(long desireAddTime){
        this.desireAddTime = desireAddTime;
    }
    public void setDesireFulfillmentTime(long desireFulfillmentTime){
        this.desireFulfillmentTime = desireFulfillmentTime;
    }
    public void setDesireStatus(int desireStatus){
        this.desireStatus = desireStatus;
    }
    public void setDesireCostExperienceValue(long desireCostExperienceValue){
        this.desireCostExperienceValue = desireCostExperienceValue;
    }

    /*
    public long getUID(){
        return uid;
    }
    */
    public long getDesireID(){
        return desireID;
    }
    public String getDesireTitle(){
        return isNull(desireTitle) ? UNDEF : desireTitle;
    }
    public String getDesireDescription(){
        return isNull(desireDescription) ? UNDEF : desireDescription;
    }
    public String getDesireType(){
        return isNull(desireType) ? UNDEF : desireType;
    }
    public long getDesireAddTime(){
        return desireAddTime;
    }
    public long getDesireFulfillmentTime(){
        return desireFulfillmentTime;
    }
    public int getDesireStatus(){
        return desireStatus;
    }
    public long getDesireCostExperienceValue(){
        return desireCostExperienceValue;
    }

    public DesireDetails(){}

    public DesireDetails(JSONObject jsonObject){
        if(jsonObject == null) return;


        try{
            data = new JSONObject(jsonObject.toString());
            hash = data.optString("hash");

        }catch (JSONException e){
            e.printStackTrace();
        }

        //uid = jsonObject.getLong("uid");
        /*
        desireID = jsonObject.optLong("desireID", 0);
        desireTitle = jsonObject.optString("desireTitle", UNDEF);
        desireDescription = jsonObject.optString("desireDescription", UNDEF);
        desireType = jsonObject.optString("desireType", UNDEF);
        desireAddTime = jsonObject.optLong("desireAddTime", 0);
        desireFulfillmentTime = jsonObject.optLong("desireFulfillmentTime", 0);
        desireStatus = jsonObject.optInt("desireStatus", ERRSTATUS);
        desireCostExperienceValue = jsonObject.optLong("desireCostExperienceValue", 0);
        */
    }

    @Override
    public void build(JSONObject jsonObject){
        //uid = jsonObject.getLong("uid");
        /*
        desireID = jsonObject.optLong("desireID", 0);
        desireTitle = jsonObject.optString("desireTitle", UNDEF);
        desireDescription = jsonObject.optString("desireDescription", UNDEF);
        desireType = jsonObject.optString("desireType", UNDEF);
        desireAddTime = jsonObject.optLong("desireAddTime", 0);
        desireFulfillmentTime = jsonObject.optLong("desireFulfillmentTime", 0);
        desireStatus = jsonObject.optInt("desireStatus", ERRSTATUS);
        desireCostExperienceValue = jsonObject.optLong("desireCostExperienceValue", 0);
        */
    }

    @Override
    public String toString(){
        return toJSONObject().toString();
    }

    public JSONObject toJSONObject() {
        /*
        JSONObject jsonObject = new JSONObject();
        //jsonPut(jsonObject, "uid", ""+uid);
        jsonPut(jsonObject, "desireID", "" + desireID);
        jsonPut(jsonObject, "desireTitle", desireTitle);
        jsonPut(jsonObject, "desireDescription", desireDescription);
        jsonPut(jsonObject, "desireType", desireType);
        jsonPut(jsonObject, "desireAddTime", "" + desireAddTime);
        jsonPut(jsonObject, "desireFulfillmentTime", "" + desireFulfillmentTime);
        jsonPut(jsonObject, "desireStatus", "" + desireStatus);
        jsonPut(jsonObject, "desireCostExperienceValue", "" + desireCostExperienceValue);
        return jsonObject;
        */
        return data;
    }

    public void setJSONObject(JSONObject obj){
        if(obj == null)
            return;

        try{
            data = new JSONObject(obj.toString());
            hash = data.optString("hash");

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public String getHash(){
        return hash;
    }

    @Override
    public boolean isValid(){
        return true;
    }
}
