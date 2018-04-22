package com.freecreator.whiteforest.common.details;

import com.freecreator.whiteforest.base.Application;

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
    private int level;
    /*当前段位(使用整数标识)(荒野求生者,从林探险家...)*/
    private int segmentLevel;
    /*累计获得经验值*/
    private int totalExperienceValue;
    /*累计消耗经验值*/
    private int costExperienceValue;
    /*当前剩余经验值*/
    private int remainExperienceValue;
    /*任务总计数*/
    private int totalTaskNum;
    /*任务成功完成数*/
    private int finishedTaskNum;
    /*所有任务详细列表(暂时存在)*/
    //private List<TaskDetails> taskDetailsList = new LinkedList();
    /*欲望总计数*/
    private int totalDesireNum;
    /*用户状态*/
    private int userStatus;
    /*所有欲望详细列表(暂时存在)*/
    //private List<DesireDetails> desireDetailsList = new LinkedList();
    /**/
    private int remainCoins;

    public void setUID(long uid){
        this.uid = uid;
        Application.saveData(this);
    }
    public void setUserName(String userName){
        this.userName = userName;
        Application.saveData(this);
    }
    public void setNickName(String nickNime){
        this.nickName = nickNime;
        Application.saveData(this);
    }
    public void setRealName(String realName){
        this.realName = realName;
        Application.saveData(this);
    }
    public void setPassword(String password){
        this.password = password;
        Application.saveData(this);
    }
    public void setSex(String sex){
        this.sex = sex;
        Application.saveData(this);
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
        Application.saveData(this);
    }
    public void setMotto(String motto){
        this.motto = motto;
        Application.saveData(this);
    }
    public void setBirthday(String birthday){
        this.birthday = birthday;
        Application.saveData(this);
    }
    public void setLevel(int level){
        this.level = level;
        Application.saveData(this);
    }
    public void setSegmentLevel(int segmentLevel){
        this.segmentLevel = segmentLevel;
        Application.saveData(this);
    }
    public void setTotalExperienceValue(int totalExperienceValue){
        this.totalExperienceValue = totalExperienceValue;
        Application.saveData(this);
    }
    public void setCostExperienceValue(int costExperienceValue){
        this.costExperienceValue = costExperienceValue;
        Application.saveData(this);
    }
    public void setRemainExperienceValue(int remainExperienceValue){
        this.remainExperienceValue = remainExperienceValue;
        Application.saveData(this);
    }
    public void setTotalTaskNum(int totalTaskNum){
        this.totalTaskNum = totalTaskNum;
        Application.saveData(this);
    }
    public void setFinishedTaskNum(int finishedTaskNum){
        this.finishedTaskNum = finishedTaskNum;
        Application.saveData(this);
    }
    public void setTotalDesireNum(int totalDesireNum){
        this.totalDesireNum = totalDesireNum;
        Application.saveData(this);
    }
    public void setUserStatus(int userStatus){
        this.userStatus = userStatus;
        Application.saveData(this);
    }
    public void setCoins(int val){
        this.remainCoins = val;
        Application.saveData(this);
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
    public int getLevel(){
        return this.level;
    }
    public int getSegmentLevel(){
        return this.segmentLevel;
    }
    public int getTotalExperienceValue(){
        return this.totalExperienceValue;
    }
    public int getCostExperienceValue(){
        return this.costExperienceValue;
    }
    public int getRemainExperienceValue(){
        return this.remainExperienceValue;
    }
    public int getTotalTaskNum(){
        return this.totalTaskNum;
    }
    public int getFinishedTaskNum(){
        return this.finishedTaskNum;
    }
    public int getTotalDesireNum(){
        return this.totalDesireNum;
    }
    public int getUserStatus(){
        return this.userStatus;
    }
    public int getCoins(){
        return this.remainCoins;
    }

    public UserDetails(){}
    public UserDetails(JSONObject jsonObject){
        build(jsonObject);
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
        jsonPut(jsonObject, "coins", ""+remainCoins);
        return jsonObject;
    }

    @Override
    public boolean isValid(){
        if(userName.equals(UNDEF))
            return false;
        return true;
    }

    public void build(JSONObject jsonObject){
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
        level = jsonObject.optInt("level",0);
        segmentLevel = jsonObject.optInt("segmentLevel",0);
        totalExperienceValue = jsonObject.optInt("totalExperienceValue",0);
        costExperienceValue = jsonObject.optInt("costExperienceValue",0);
        remainExperienceValue = jsonObject.optInt("remainExperienceValue",0);
        totalTaskNum = jsonObject.optInt("totalTaskNum",0);
        finishedTaskNum = jsonObject.optInt("finishedTaskNum",0);
        totalDesireNum = jsonObject.optInt("totalDesireNum",0);
        userStatus = jsonObject.optInt("userStatus",ERRSTATUS);
        remainCoins = jsonObject.optInt("coins",ERRSTATUS);
    }

    /**
     *
     * @param access 用于保证该方法能且仅能被 Application 类调用
     * @param json 数据
     */
    public void rebuild(Application access, JSONObject json){
        build(json);
    }

}