package com.example.mylib.net;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by setsuna on 2016/9/1.
 */
public class JsonGet implements Callback.CommonCallback<String> {
    private String mUrl;
    private Type mClass;
    private NetCallback mNetCallBack;
    private boolean ispost;
    private RequestParams mRequestParams;
    private Callback.Cancelable mCancelable;
    private Map<String,Object> mCookies;
    public JsonGet(String url,  NetCallback netCallBack, boolean ispost) {
        mUrl = url;
        mNetCallBack = netCallBack;
        this.ispost = ispost;
        mRequestParams=new RequestParams(url);
        if (netCallBack!=null){
            ParameterizedType parameterizedType = (ParameterizedType) netCallBack.getClass().getGenericSuperclass();
            mClass=parameterizedType.getActualTypeArguments()[0];
        }

    }

    public JsonGet(String url,  NetCallback netCallBack) {
        this(url,netCallBack,false);
    }

    /**
     * 执行网络访问
     */
    public void excute(){
        if (mCookies!=null && mCookies.size()!=0){
            StringBuilder stringBuilder=new StringBuilder();
            Set<String> keys=mCookies.keySet();
            for (String key:keys){
                stringBuilder.append(key+"="+mCookies.get(key)+"; ");
            }
            mRequestParams.setHeader("Cookie",stringBuilder.substring(0,stringBuilder.length()-2));
        }
        if (ispost){
            doPost();
        }else {
            doGet();
        }
    }

    /**
     * 添加参数
     * @param key
     * @param value
     */
    public void addParams(String key,Object value){
        if (ispost){
            mRequestParams.addBodyParameter(key,value+"");
        }else {
            mRequestParams.addParameter(key,value);
        }
    }

    /**
     * 替换参数
     * @param key  要替换的key
     * @param value  要替换的值
     */
    public void replaceParams(String key,Object value){
        mRequestParams.removeParameter(key);
        addParams(key,value);
    }

    /**
     * 添加参数
     * @param key  要删除的key
     *
     */
    public void removeParams(String key){
        mRequestParams.removeParameter(key);
    }



    /**
     * 添加cookie
     * @param key
     * @param value
     */
    public void addCookie(String key,Object value){
        if (mCookies==null){
            mCookies=new HashMap<>();
        }
        mCookies.put(key,value);
    }


    /**
     * 取消网络访问
     */
    public void cancle(){
        if (mCancelable!=null){
            mCancelable.cancel();
        }
    }


    /**
     * get方式访问
     */
    private void doGet() {
        mCancelable= x.http().get(mRequestParams,this);
    }
    /**
     * post方式访问
     */
    private void doPost() {
        mCancelable=x.http().post(mRequestParams,this);
    }

    @Override
    public void onSuccess(String result) {
        Gson gson=new Gson();
        mNetCallBack.success(gson.fromJson(result,mClass));
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        ex.printStackTrace();
        mNetCallBack.fail();
    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {
        mNetCallBack.finish();
    }
}
