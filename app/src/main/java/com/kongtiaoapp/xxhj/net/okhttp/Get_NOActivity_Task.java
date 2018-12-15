package com.kongtiaoapp.xxhj.net.okhttp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.AllActivityManager;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.login_register.activity.LoginActivity;
import com.kongtiaoapp.xxhj.utils.NetworkUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.lang.ref.WeakReference;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guoc on 2016/12/24.
 */
public class Get_NOActivity_Task<T> extends AsyncTask<Void, Void, T> {
    private Class<T> classOfT;
    private WeakReference<Context> act;
    private ResponseXXHJListener<T> listener;
    private String url;
    public static final String TAG = "xiaoxihuiju";
    private Context activity;
    private int totalTime = 3, currentTime = 0;

    public Get_NOActivity_Task(Context activity, Class<T> clazz, String url, ResponseXXHJListener<T> lis) {
        this.act = new WeakReference<>(activity);
        this.classOfT = clazz;
        this.url = url;
        this.listener = lis;
        this.activity = activity;
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e("onPreExecute","======onPreExecute======");
    }

    @Override
    protected T doInBackground(Void... params) {

        return setResult();
    }

    private T setResult() {

        try {
            Log.e(TAG, "url:" + url);
            Request request = new Request.Builder()
                    .header("Authorization", "Client-ID " + "...")
                    .url(url)
                    .build();
            Response response = OkHttpUtil.getClient().newCall(request).execute();

            if (response == null || !response.isSuccessful()) {
                return null;
            }

            String result = response.body().string();

            if (TextUtils.isEmpty(result)) {
                return null;
            }

            Log.e(TAG, "JSON数据=====返回======" + result);

            Gson g = new Gson();
            T t = g.fromJson(result, classOfT);
            RBResponse rbResponse = (RBResponse) t;
            if (rbResponse.getCode() == 40006) {//当返回的状态码为40006时，进行强迫下线。
                App.sp.setLoginState(false);
                App.sp.resetSp();
                AllActivityManager.getInstance().finishAllActivity();
                Intent intent = new Intent(activity, LoginActivity.class);
                intent.putExtra("quit_login", "quit_login");
                activity.startActivity(intent);
            }
            return t;
        } catch (Exception e) {
            Log.e(TAG, "PostTask Exception:" + e.toString());
        }
        return null;
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPostExecute(T t) {

            if (listener != null) {
                if (t == null) {
                    if (NetworkUtils.checkNetwork(activity) == false) {
                        ToastUtils.showToast(activity, activity.getString(R.string.please_net_work), activity.getResources().getColor(R.color.a666666), "");
                    }
                    ++currentTime;
                    if (currentTime <= totalTime) {
                        setResult();//连接超时或者网不好重新请求
                    } else {
                        listener.requuestError(0);//网络异常
                        ToastUtils.showToast(activity, "服务器正在升级，稍后尝试!");
                    }
                } else {
                    currentTime = 0;
                    listener.requestSuccess(t);
                }
            }

    }
}