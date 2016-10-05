package com.example.mylib.net;

/**
 * Created by setsuna on 2016/9/1.
 */
public abstract class NetCallback<T> {
    public abstract void success(T entity);
    public abstract void fail();
    public abstract void finish();
}
