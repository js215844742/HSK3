package hsk3.jane.cn.hsk3.base;

import android.app.Activity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Activity管理控制
 * Created by Jane on 2018/3/28.
 */

public class MyActivityManager {
    private static HashMap<String, Activity> manager = new HashMap<>();
    public static void addActivity(Activity activity){
        manager.put(activity.getClass().getName(), activity);
    }
    public static void finishActivity(Activity activity){
        removeActivity(activity);
        activity.finish();
    }
    public static void finishActivity(String activityName){
        if (manager.containsKey(activityName)) {
            manager.remove(activityName);
            manager.get(activityName).finish();
        }
    }
    public static Activity getActivity(String activityName){
        return manager.get(activityName);
    }
    public static void clearActivities(){
        Collection<Activity> cs = manager.values();
        Iterator<Activity> it =  cs.iterator();
        while (it.hasNext()){
            Activity activity = it.next();
            activity.finish();
        }
        manager.clear();
    }

    private static void removeActivity(Activity activity){
        if (manager.containsKey(activity.getClass().getName())){
            manager.remove(activity.getClass().getName());
        }
    }
}
