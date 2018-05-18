
package com.freecreator.whiteforest.common.details;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;

import static com.freecreator.whiteforest.utils.JsonUtils.jsonArrayPut;
import static com.freecreator.whiteforest.utils.JsonUtils.jsonPut;

/**
 * Created by JackYanx on 2018/3/15.
 * 任务详细信息类
 */

public class TaskDetails extends AbstractDetails{


    /*任务拥有者(用户)唯一标识*/
    //private long uid;
    /*任务唯一标识*/
    private long taskID;
    /*任务标题*/
    private String taskTitle;
    /*任务描述*/
    private String taskDescription;
    /*任务类型*/
    private int taskType;
    /*任务创建时间*/
    private long taskCreateTime;
    /*任务持续时间*/
    private long taskDurationTime;
    /*任务设定开始时间*/
    private long taskStartTime;
    /*任务被结束时间*/
    private long taskEndTime;
    /*任务状态*/
    private int taskStatus;
    /*完成任务所能获得的经验值*/
    private int taskObtainExperienceValue;
    /*完成任务所能获得的金币*/
    private int coin;
    /*时间投资任务的总时间*/
    private int total_use_time;
    private JSONArray use_record = new JSONArray();

    private String hash = null;

    /*
    public void setUID(long uid){
        this.uid = uid;
    }
    */
    public void setTotalUseTime(int min){
        total_use_time = min;
    }
    public void setTaskID(long taskID){
        this.taskID = taskID;
    }
    public void setTaskTitle(String taskTitle){
        this.taskTitle = taskTitle;
    }
    public void setTaskDescription(String taskDescription){
        this.taskDescription = taskDescription;
    }
    public void setTaskType(int taskType){
        this.taskType = taskType;
    }
    public void setTaskCreateTime(long taskCreateTime){
        this.taskCreateTime = taskCreateTime;
    }
    public void setTaskDurationTime(long taskDurationTime){
        this.taskDurationTime = taskDurationTime;
    }
    public void setTaskStartTime(long taskStartTime){
        this.taskStartTime = taskStartTime;
    }
    public void setTaskEndTime(long taskEndTime){
        this.taskEndTime = taskEndTime;
    }
    public void setTaskStatus(int taskStatus){
        this.taskStatus = taskStatus;
    }
    public void setTaskObtainExperienceValue(int taskObtainExperienceValue){
        this.taskObtainExperienceValue = taskObtainExperienceValue;
    }
    public void setCoins(int coins){
        this.coin = coins;
    }
    public void addTimerRecord(int minutes){

        int used_minutes = getTotalUseTime();
        used_minutes += minutes;
        setTotalUseTime(used_minutes);

        JSONObject new_record = new JSONObject();
        jsonPut(new_record, "timestamp", (new Timestamp(System.currentTimeMillis() )).toString());
        jsonPut(new_record, "add_minutes", minutes);

        if(null == use_record){
            use_record = new JSONArray();
            use_record.put(new_record);
        }
        else{
            use_record.put(new_record);
        }
    }


    /*
    public long getUID(){
        return uid;
    }
    */
    public int getTotalUseTime(){
        return this.total_use_time;
    }
    public long getTaskID(){
        return taskID;
    }
    public String getTaskTitle(){
        return isNull(taskTitle) ? UNDEF : taskTitle;
    }
    public String getTaskDescription(){
        return isNull(taskDescription) ? UNDEF : taskDescription;
    }
    public int getTaskType(){
        return taskType;
    }
    public long getTaskCreateTime(){
        return taskCreateTime;
    }
    public long getTaskDurationTime(){
        return taskDurationTime;
    }
    public long getTaskStartTime(){
        return taskStartTime;
    }
    public long getTaskEndTime(){
        return taskEndTime;
    }
    public int getTaskStatus(){
        return taskStatus;
    }
    public int getTaskObtainExperienceValue(){
        return taskObtainExperienceValue;
    }
    public int getCoin(){
        return this.coin;
    }

    public TaskDetails(){}

    public TaskDetails(JSONObject obj){
        if(obj == null)
            return;

        build(obj);
    }

    public String getHash(){
        return hash;
    }

    public void setHash(String a ){
        this.hash = a;
    }

    @Override
    public String toString(){
        return toJSONObject().toString();
    }


    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        //jsonPut(jsonObject, "uid", ""+uid);
        jsonPut(jsonObject, " taskID", ""+taskID);
        jsonPut(jsonObject, " taskTitle", taskTitle);
        jsonPut(jsonObject, " taskDescription", taskDescription);
        jsonPut(jsonObject, " taskType", taskType);
        jsonPut(jsonObject, " taskCreateTime", ""+taskCreateTime);
        jsonPut(jsonObject, " taskDurationTime", ""+taskDurationTime);
        jsonPut(jsonObject, " taskStartTime", ""+taskStartTime);
        jsonPut(jsonObject, " taskEndTime", ""+taskEndTime);
        jsonPut(jsonObject, " taskStatus", ""+taskStatus);
        jsonPut(jsonObject, " taskObtainExperienceValue", taskObtainExperienceValue);
        jsonPut(jsonObject, " coin", coin);
        jsonPut(jsonObject, " hash", hash);
        jsonPut(jsonObject, " totaltime", total_use_time);
        if(null != use_record)
            jsonArrayPut(jsonObject, " use_record", use_record);
        return jsonObject;
    }


    @Override
    public boolean isValid(){
        return true;
    }

    public void build(JSONObject jsonObject){
        if(jsonObject == null) return;
        //uid = jsonObject.optLong("uid");
        hash = jsonObject.optString(" hash",UNDEF);
        taskID = jsonObject.optLong(" taskID",0);
        taskTitle = jsonObject.optString(" taskTitle",UNDEF);
        taskDescription = jsonObject.optString(" taskDescription",UNDEF);
        taskType = jsonObject.optInt(" taskType",0);
        taskCreateTime = jsonObject.optLong( " taskCreateTime",0);
        taskDurationTime = jsonObject.optLong(" taskDurationTime",0);
        taskStartTime = jsonObject.optLong(" taskStartTime",0);
        taskEndTime = jsonObject.optLong(" taskEndTime",0);
        taskStatus = jsonObject.optInt(" taskStatus",ERRSTATUS);
        taskObtainExperienceValue = jsonObject.optInt(" taskObtainExperienceValue",0);
        coin  = jsonObject.optInt(" coin",0);
        hash = jsonObject.optString(" hash",UNDEF);
        total_use_time = jsonObject.optInt(" totaltime",0);
        use_record = jsonObject.optJSONArray(" use_record");
    }

}
