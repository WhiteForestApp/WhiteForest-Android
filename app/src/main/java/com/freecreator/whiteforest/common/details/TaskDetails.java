package com.freecreator.whiteforest.common.details;

import org.json.JSONException;
import org.json.JSONObject;

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
    private String taskType;
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
    private long taskObtainExperienceValue;

    /*
    public void setUID(long uid){
        this.uid = uid;
    }
    */
    public void setTaskID(long taskID){
        this.taskID = taskID;
    }
    public void setTaskTitle(String taskTitle){
        this.taskTitle = taskTitle;
    }
    public void setTaskDescription(String taskDescription){
        this.taskDescription = taskDescription;
    }
    public void setTaskType(String taskType){
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
    public void setTaskObtainExperienceValue(long taskObtainExperienceValue){
        this.taskObtainExperienceValue = taskObtainExperienceValue;
    }


    /*
    public long getUID(){
        return uid;
    }
    */
    public long getTaskID(){
        return taskID;
    }
    public String getTaskTitle(){
        return isNull(taskTitle) ? UNDEF : taskTitle;
    }
    public String getTaskDescription(){
        return isNull(taskDescription) ? UNDEF : taskDescription;
    }
    public String getTaskType(){
        return isNull(taskType) ? UNDEF : taskType;
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
    public long getTaskObtainExperienceValue(){
        return taskObtainExperienceValue;
    }

    public TaskDetails(){}

    public TaskDetails(JSONObject jsonObject)throws JSONException {
        if(jsonObject == null) return;
        //uid = jsonObject.getLong("uid");
        taskID = jsonObject.optLong("taskID",0);
        taskTitle = jsonObject.optString("taskTitle",UNDEF);
        taskDescription = jsonObject.optString("taskDescription",UNDEF);
        taskType = jsonObject.optString("taskType",UNDEF);
        taskCreateTime = jsonObject.optLong("taskCreateTime",0);
        taskDurationTime = jsonObject.optLong("taskDurationTime",0);
        taskStartTime = jsonObject.optLong("taskStartTime",0);
        taskEndTime = jsonObject.optLong("taskEndTime",0);
        taskStatus = jsonObject.optInt("taskStatus",ERRSTATUS);
        taskObtainExperienceValue = jsonObject.optLong("taskObtainExperienceValue",0);
    }

    @Override
    public String toString(){
        return toJSONObject().toString();
    }


    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        //jsonPut(jsonObject, "uid", ""+uid);
        jsonPut(jsonObject, "taskID", ""+taskID);
        jsonPut(jsonObject, "taskTitle", taskTitle);
        jsonPut(jsonObject, "taskDescription", taskDescription);
        jsonPut(jsonObject, "taskType", taskType);
        jsonPut(jsonObject, "taskCreateTime", ""+taskCreateTime);
        jsonPut(jsonObject, "taskDurationTime", ""+taskDurationTime);
        jsonPut(jsonObject, "taskStartTime", ""+taskStartTime);
        jsonPut(jsonObject, "taskEndTime", ""+taskEndTime);
        jsonPut(jsonObject, "taskStatus", ""+taskStatus);
        jsonPut(jsonObject, "taskObtainExperienceValue", ""+taskObtainExperienceValue);
        return jsonObject;
    }

    @Override
    public boolean isInvalid(){
        return false;
    }

}
