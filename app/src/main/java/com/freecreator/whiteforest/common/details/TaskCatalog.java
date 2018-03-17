package com.freecreator.whiteforest.common.details;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.freecreator.whiteforest.common.details.AbstractDetails.jsonPut;

/**
 * Created by JackYanx on 2018/3/15.
 * 任务目录列表类
 */

public class TaskCatalog {
    private long uid;
    private long itemNum;
    private long itemEarliestCreateTime;
    private long itemLatestCreateTime;
    private List<TaskDetails> taskDetailsList = new ArrayList<>();
    public TaskCatalog(){}

    /*
    * 使用JSON初始化任务目录列表
    * */
    public TaskCatalog(JSONObject jsonObject) throws JSONException{
        if(null == jsonObject) return;
        JSONArray jsonArray = jsonObject.getJSONArray("taskDetailsList");
        itemNum = jsonArray.length();
        long tempTime = 0;
        for(int i = 0;i < itemNum;i++){
            TaskDetails taskDetails = new TaskDetails(jsonArray.getJSONObject(i));
            tempTime = taskDetails.getTaskCreateTime();
            taskDetailsList.add(taskDetails);
            if(i == 0){
                itemEarliestCreateTime = tempTime;
            }
            itemEarliestCreateTime = itemEarliestCreateTime > tempTime ? tempTime : itemEarliestCreateTime;
            itemLatestCreateTime = itemLatestCreateTime < tempTime ? tempTime : itemLatestCreateTime;
        }

    }

    public void setUID(long uid){
        this.uid = uid;
    }

    public long getItemNum(){
        return itemNum;
    }
    public long getItemEarliestCreateTime(){
        return itemEarliestCreateTime;
    }
    public long getItemLatestCreateTime(){
        return itemLatestCreateTime;
    }
    public List<TaskDetails> getTaskDetailsList(){
        return taskDetailsList;
    }

    /*向本列表中添加任务项目*/
    public boolean addTaskDetails(TaskDetails taskDetails){
        if(null == taskDetails || taskDetails.isInvalid()){
            return false;
        }
        taskDetailsList.add(taskDetails);
        return true;
    }

    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonPut(jsonObject, "uid", ""+uid);
        jsonPut(jsonObject, "itemNum", ""+itemNum);
        jsonPut(jsonObject, "itemEarliestCreateTime", ""+itemEarliestCreateTime);
        jsonPut(jsonObject, "itemLatestCreateTime", ""+itemLatestCreateTime);
        for(int i = 0;i < taskDetailsList.size();i++){
            jsonArray.put(taskDetailsList.get(i).toString());
        }
        jsonPut(jsonObject, "taskDetailsList",jsonArray.toString());
        return jsonObject.toString();
    }

}
