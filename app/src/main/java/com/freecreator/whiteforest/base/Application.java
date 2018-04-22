package com.freecreator.whiteforest.base;

import android.content.Context;

import com.freecreator.whiteforest.R;
import com.freecreator.whiteforest.common.cache.LocalCache;
import com.freecreator.whiteforest.common.details.DesireCatalog;
import com.freecreator.whiteforest.common.details.TaskCatalog;
import com.freecreator.whiteforest.common.details.UserDetails;
import com.freecreator.whiteforest.common.details.UserList;

import static com.freecreator.whiteforest.common.Debug.fakeLogin;

/**
 * Created by niko on 2018/4/18.
 */

public class Application {

    private static Context m_context = null;
    private static UserDetails m_user =  null;
    private static TaskCatalog task_list = null;
    private static DesireCatalog desire_list = null;

    public static void init(Context context){

        m_context = context;

        if(fakeLogin){
            String username = getDefaultUser(context);
            UserDetails user = getUser(context, username);

            if(username.equals("") || null == user || !user.isValid()){
                user = new UserDetails();
                user.setUserName("default");
                user.setNickName("unknown");
                setDefaultUser(context, user.getUserName());
                setCurrentUser(context , user);
            }
            else{
                m_user = user;
            }
        }
    }

    // 设置当前的用户
    public static boolean setCurrentUser(Context context, UserDetails user){

        m_context = context;

        LocalCache cache = new LocalCache(new Application(), context);
        boolean result = cache.setUserDetails(user);

        if(false == result)
            return false;

        m_user = user;
        return true;
    }

    // 设置当前的用户
    public static boolean setCurrentUser(Context context, String username){

        m_context = context;

        LocalCache cache = new LocalCache(new Application(), context);
        UserDetails user = cache.getUserDetails(username);

        if(null == user)
            return false;

        m_user = user;
        return true;
    }

    public static UserDetails getCurrentUser(Context context){

        m_context = context;

        if(null == m_user){
            init(context);
        }
        return m_user;
    }

    public static UserDetails getUser(Context context, String username){

        m_context = context;
        LocalCache cache = new LocalCache(new Application(), context);
        UserDetails user = cache.getUserDetails(username);
        return user;
    }

    /**
     *
     * @return 获得默认用户的用户名 username
     */
    public static String getDefaultUser(Context context){

        m_context = context;
        LocalCache cache = new LocalCache(new Application(), context);
        UserList userList = cache.getUserList();
        return userList.getDefaultUser();
    }

    public static void setDefaultUser(Context context, String username){

        m_context = context;
        LocalCache cache = new LocalCache(new Application(), context);
        UserList userList = cache.getUserList();
        userList.setDefaultUser(username);
        return ;
    }

    public static void saveData(Object data){
        if(null == m_context)
            return;

        LocalCache cache = new LocalCache(new Application(), m_context);

        if(data instanceof UserDetails){
            cache.setUserDetails( (UserDetails)data );
        }
        else if(data instanceof TaskCatalog){
            cache.setTaskCatalog((TaskCatalog)data);
        }
        else if(data instanceof DesireCatalog){
            cache.setDesireCatalog((DesireCatalog)data);
        }
        else if(data instanceof UserList){
            cache.setUserList((UserList)data);
        }
    }

    public static TaskCatalog getTaskCatalog(Context context){
        m_context = context;

        if(null == task_list){
            LocalCache cache = new LocalCache(new Application(), context);
            task_list = cache.getTaskCatalog();
        }

        return task_list;
    }

    public static DesireCatalog getDesireCatalog(Context context){
        m_context = context;

        if(null == desire_list){
            LocalCache cache = new LocalCache(new Application(), context);
            desire_list = cache.getDesireCatalog();
        }

        return desire_list;
    }


    // 构造器设置为 protected , 以保证有且仅有本类的静态函数可以构造,
    // Application 类的对象是一种高权限对象, 避免在他处被构造以获得权限, 故设置为 protected
    protected Application(){}
}
