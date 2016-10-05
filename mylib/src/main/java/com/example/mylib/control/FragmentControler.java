package com.example.mylib.control;

import com.example.mylib.base.BaseFragment;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by setsuna on 2016/9/4.
 */
public class FragmentControler {
    public static List<BaseFragment> mFragments=new LinkedList<>();

    public static void add(BaseFragment baseFragment){
        mFragments.add(baseFragment);
    }

    public static  void remove(Class aClass){
        for (int i=0;i<mFragments.size();i++){
            if (mFragments.get(i).getClass()==aClass){
                mFragments.remove(i);
            }
        }
    }

    public static <T> T getFragment(Class aClass){
        for (int i=0;i<mFragments.size();i++){
            if (mFragments.get(i).getClass()==aClass){
                return (T) mFragments.get(i);
            }
        }
        return null;
    }

    public static void killAll(){
        for (int i=mFragments.size()-1;i>0;i--){
            mFragments.get(i).killSelf();
        }
    }

}
