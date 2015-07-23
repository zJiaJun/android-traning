package com.zjiajun.firstapp.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhujiajun
 * 15/7/11 19:04
 */
public class ActivityManager {

    public static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
