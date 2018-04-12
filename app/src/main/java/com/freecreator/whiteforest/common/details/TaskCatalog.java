package com.freecreator.whiteforest.common.details;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.freecreator.whiteforest.utils.JsonUtils.jsonArrayPut;
import static com.freecreator.whiteforest.utils.JsonUtils.jsonPut;

/**
 * Created by JackYanx on 2018/3/15.
 * 任务目录列表类
 */

public class TaskCatalog {
    private long uid = 0L;
    private long itemNum = 0L;
    private long itemEarliestCreateTime;
    private long itemLatestCreateTime;
    private List<TaskDetails> taskDetailsList = new ArrayList<>();
    public TaskCatalog(){}

    /*
    * 使用JSON初始化任务目录列表
    * */
    public TaskCatalog(JSONObject jsonObject){
        if(null == jsonObject) return;
        uid = jsonObject.optLong("uid",0);
        JSONArray jsonArray = jsonObject.optJSONArray("taskDetailsList");
        itemNum = jsonArray.length();
        long tempTime = 0;
        for(int i = 0;i < itemNum;i++){
            TaskDetails taskDetails = new TaskDetails(jsonArray.optJSONObject(i));
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
        return taskDetailsList.size();
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

    public void deleteTaskDetails(String hash){

        for(int i = 0; i< taskDetailsList.size();i++){
            TaskDetails item = taskDetailsList.get(i);
            if(item.getHash().equals(hash)){
                taskDetailsList.remove(i);
                return;
            }
        }
    }

    /*向本列表中添加任务项目*/
    public boolean addTaskDetails(TaskDetails taskDetails){
        if(null == taskDetails || !taskDetails.isValid()){
            return false;
        }

        for(int i = 0; i< taskDetailsList.size();i++){
            TaskDetails item = taskDetailsList.get(i);
            if(item.getHash().equals(taskDetails.getHash())){
                if(!taskDetails.equals(item))
                    taskDetailsList.set(i, taskDetails);
                return true;
            }
        }

        long tempTime = taskDetails.getTaskCreateTime();
        taskDetailsList.add(taskDetails);
        if(itemNum == 0){
            itemEarliestCreateTime = tempTime;
        }
        itemNum++;
        itemEarliestCreateTime = itemEarliestCreateTime > tempTime ? tempTime : itemEarliestCreateTime;
        itemLatestCreateTime = itemLatestCreateTime < tempTime ? tempTime : itemLatestCreateTime;
        return true;
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
        jsonPut(jsonObject, "itemEarliestCreateTime", ""+itemEarliestCreateTime);
        jsonPut(jsonObject, "itemLatestCreateTime", ""+itemLatestCreateTime);
        for(int i = 0;i < taskDetailsList.size();i++){
            jsonArray.put(taskDetailsList.get(i).toJSONObject());
        }
        jsonArrayPut(jsonObject,"taskDetailsList",jsonArray);
        //jsonPut(jsonObject, "taskDetailsList",jsonArray);
        return jsonObject;
    }

}
