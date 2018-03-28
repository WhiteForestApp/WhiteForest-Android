package com.freecreator.whiteforest.common.details;

import org.json.JSONObject;

import static com.freecreator.whiteforest.utils.JsonUtils.jsonPut;

/**
 * Created by JackYanx on 2018/3/15.
 * 用户详细信息累
 */

public class UserDetails extends AbstractDetails{

    /*用户唯一标识*/
    private long uid;
    /*用户名*/
    private String userName;
    /*用户昵称*/
    private String nickName;
    /*真实姓名*/
    private String realName;
    /*密码*/
    private String password;
    /*性别*/
    private String sex;
    /*手机号码*/
    private String phoneNumber;
    /*座右铭*/
    private String motto;
    /*生日 e.g."1998-01-01"*/
    private String birthday;
    /*当前等级(Lv.)*/
    private long level;
    /*当前段位(使用整数标识)(荒野求生者,从林探险家...)*/
    private long segmentLevel;
    /*累计获得经验值*/
    private long totalExperienceValue;
    /*累计消耗经验值*/
    private long costExperienceValue;
    /*当前剩余经验值*/
    private long remainExperienceValue;
    /*任务总计数*/
    private long totalTaskNum;
    /*任务成功完成数*/
    private long finishedTaskNum;
    /*所有任务详细列表(暂时存在)*/
    //private List<TaskDetails> taskDetailsList = new LinkedList();
    /*欲望总计数*/
    private long totalDesireNum;
    /*用户状态*/
    private int userStatus;
    /*所有欲望详细列表(暂时存在)*/
    //private List<DesireDetails> desireDetailsList = new LinkedList();

    public void setUID(long uid){
        this.uid = uid;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setNickName(String nickNime){
        this.nickName = nickNime;
    }
    public void setRealName(String realName){
        this.realName = realName;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setSex(String sex){
        this.sex = sex;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setMotto(String motto){
        this.motto = motto;
    }
    public void setBirthday(String birthday){
        this.birthday = birthday;
    }
    public void setLevel(long level){
        this.level = level;
    }
    public void setSegmentLevel(long segmentLevel){
        this.segmentLevel = segmentLevel;
    }
    public void setTotalExperienceValue(long totalExperienceValue){
        this.totalExperienceValue = totalExperienceValue;
    }
    public void setCostExperienceValue(long costExperienceValue){
        this.costExperienceValue = costExperienceValue;
    }
    public void setRemainExperienceValue(long remainExperienceValue){
        this.remainExperienceValue = remainExperienceValue;
    }
    public void setTotalTaskNum(long totalTaskNum){
        this.totalTaskNum = totalTaskNum;
    }
    public void setFinishedTaskNum(long finishedTaskNum){
        this.finishedTaskNum = finishedTaskNum;
    }
    public void setTotalDesireNum(long totalDesireNum){
        this.totalDesireNum = totalDesireNum;
    }
    public void setUserStatus(int userStatus){
        this.userStatus = userStatus;
    }

    public long getUID(){
        return this.uid;
    }
    public String getUserName(){
        return isNull(userName) ? UNDEF : userName;
    }
    public String getNickName(){
        return isNull(nickName) ? UNDEF : nickName;
    }
    public String getRealName(){
        return isNull(realName) ? UNDEF : realName;
    }
    public String getPassword(){
        return isNull(password) ? UNDEF : password;
    }
    public String getSex(){
        return isNull(sex) ? UNDEF : sex;
    }
    public String getPhoneNumber(){
        return isNull(phoneNumber) ? UNDEF : phoneNumber;
    }
    public String getMotto(){
        return isNull(motto) ? UNDEF : motto;
    }
    public String getBirthday(){
        return isNull(birthday) ? UNDEF : birthday;
    }
    public long getLevel(){
        return this.level;
    }
    public long getSegmentLevel(){
        return this.segmentLevel;
    }
    public long getTotalExperienceValue(){
        return this.totalExperienceValue;
    }
    public long getCostExperienceValue(){
        return this.costExperienceValue;
    }
    public long getRemainExperienceValue(){
        return this.remainExperienceValue;
    }
    public long getTotalTaskNum(){
        return this.totalTaskNum;
    }
    public long getFinishedTaskNum(){
        return this.finishedTaskNum;
    }
    public long getTotalDesireNum(){
        return this.totalDesireNum;
    }
    public int getUserStatus(){
        return this.userStatus;
    }

    public UserDetails(){}
    public UserDetails(JSONObject jsonObject){
        if(jsonObject == null) return;
        uid = jsonObject.optLong("uid",0);
        userName = jsonObject.optString("userName",UNDEF);
        nickName = jsonObject.optString("nickName",UNDEF);
        realName = jsonObject.optString("realName",UNDEF);
        password = jsonObject.optString("password",UNDEF);
        sex = jsonObject.optString("sex",UNDEF);
        phoneNumber = jsonObject.optString("phoneNumber",UNDEF);
        motto = jsonObject.optString("motto",UNDEF);
        birthday = jsonObject.optString("birthday",UNDEF);
        level = jsonObject.optLong("level",0);
        segmentLevel = jsonObject.optLong("segmentLevel",0);
        totalExperienceValue = jsonObject.optLong("totalExperienceValue",0);
        costExperienceValue = jsonObject.optLong("costExperienceValue",0);
        remainExperienceValue = jsonObject.optLong("remainExperienceValue",0);
        totalTaskNum = jsonObject.optLong("totalTaskNum",0);
        finishedTaskNum = jsonObject.optLong("finishedTaskNum",0);
        totalDesireNum = jsonObject.optLong("totalDesireNum",0);
        userStatus = jsonObject.optInt("userStatus",ERRSTATUS);
    }

    @Override
    public String toString(){
        return toJSONObject().toString();
    }

    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        jsonPut(jsonObject, "uid", ""+uid);
        jsonPut(jsonObject, "userName", userName);
        jsonPut(jsonObject, "nickName", nickName);
        jsonPut(jsonObject, "realName", realName);
        jsonPut(jsonObject, "password", password);
        jsonPut(jsonObject, "sex", sex);
        jsonPut(jsonObject, "phoneNumber", phoneNumber);
        jsonPut(jsonObject, "motto", motto);
        jsonPut(jsonObject, "birthday", birthday);
        jsonPut(jsonObject, "level", ""+level);
        jsonPut(jsonObject, "segmentLevel", ""+segmentLevel);
        jsonPut(jsonObject, "totalExperienceValue", ""+totalExperienceValue);
        jsonPut(jsonObject, "costExperienceValue", ""+costExperienceValue);
        jsonPut(jsonObject, "remainExperienceValue", ""+remainExperienceValue);
        jsonPut(jsonObject, "totalTaskNum", ""+totalTaskNum);
        jsonPut(jsonObject, "finishedTaskNum", ""+finishedTaskNum);
        jsonPut(jsonObject, "totalDesireNum", ""+totalDesireNum);
        jsonPut(jsonObject, "userStatus", ""+userStatus);
        return jsonObject;
    }

    @Override
    public boolean isValid(){
        return true;
    }




}
