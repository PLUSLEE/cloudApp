package com.example.mylib.control;

import com.example.mylib.base.BaseActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by setsuna on 2016/9/4.
 */
public class ActivityControler {
    public static List<BaseActivity> mActivities=new LinkedList<>();

    public static void add(BaseActivity baseActivity){
        mActivities.add(baseActivity);
    }

    public static  void remove(Class aClass){
        for (int i=0;i<mActivities.size();i++){
            if (mActivities.get(i).getClass()==aClass){
                mActivities.remove(i);
            }
        }
    }

    public static <T> T getActivity(Class aClass){
        for (int i=0;i<mActivities.size();i++){
            if (mActivities.get(i).getClass()==aClass){
                return (T) mActivities.get(i);
            }
        }
        return null;
    }

    public static void killAll(){
        for (int i=mActivities.size()-1;i>0;i--){
            mActivities.get(i).killSelf();
        }
    }

}
