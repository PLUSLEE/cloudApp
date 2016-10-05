package com.example.mylib.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by setsuna on 2016/9/5.
 */
public abstract class SimpleViewPagerAdapter extends PagerAdapter {


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=intiView(container,position);
        container.addView(view);
        return view;
    }

    protected abstract View intiView(ViewGroup container, int position);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
