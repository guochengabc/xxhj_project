package com.kongtiaoapp.xxhj.activites;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by 成 on 2016/11/4.
 */

public class AllActivityManager {
    private static AllActivityManager instance;
    private Stack<Activity> activityStack;//activity栈
    private AllActivityManager() {
    }
    //单例模式
    public static AllActivityManager getInstance() {
        if (instance == null) {
            instance = new AllActivityManager();
        }
        return instance;
    }

    //把一个activity压入栈中
    public void pushOneActivity(Activity actvity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(actvity);
      //  Log.i("MyActivityManager ", "size = " + activityStack.size()+activityStack.get(0)+"==");

    }
    //获取栈顶的activity，先进后出原则
    public Activity getLastActivity() {
        return activityStack.lastElement();
    }
    //移除一个activity
    public void removeOneActivity(Activity activity) {
        if (activityStack != null && activityStack.size() > 0) {
            if (activity != null) {
                activity.finish();
                activityStack.remove(activity);
                activity = null;
            }
        }
    }
    //退出所有activity
    public void finishAllActivity() {
        if (activityStack != null) {
            while (activityStack.size() > 0) {
                Activity activity = getLastActivity();
                if (activity == null) break;
                removeOneActivity(activity);
            }
        }
    }
}
