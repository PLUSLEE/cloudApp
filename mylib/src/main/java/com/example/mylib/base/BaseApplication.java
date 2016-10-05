package com.example.mylib.base;

import android.app.Application;

import org.xutils.x;


/**
 * Created by setsuna on 2016/9/3.
 */
public abstract class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        int titleresID=initTitle();
        if (titleresID!=0){
            TitleBarConfig.title_resID=titleresID;
        }
        initx();
        initLog();
        initOthers();
    }
    public abstract void initOthers();
    protected abstract int initTitle();

    private void initx() {
        x.Ext.init(this);
    }

    private void initLog() {
        if (isDebug()) {
            com.orhanobut.logger.Logger.init(this.getPackageName())
                    .logLevel(com.orhanobut.logger.LogLevel.FULL)
                    .methodCount(1)                 // default 2
                    .hideThreadInfo();            // default shown
        }else {
            com.orhanobut.logger.Logger.init(this.getPackageName())
                    .logLevel(com.orhanobut.logger.LogLevel.NONE)
                    .methodCount(1)                 // default 2
                    .hideThreadInfo();            // default shown
        }
    }

    protected abstract boolean isDebug();
}
