package com.kongtiaoapp.xxhj.net.okhttp;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import com.kongtiaoapp.xxhj.ui.progress.KProgressHUD;
import com.kongtiaoapp.xxhj.utils.DBUtil;
import com.kongtiaoapp.xxhj.utils.NetworkUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.lang.ref.WeakReference;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guoc on 2016/12/24.
 */
public class GetTask<T> extends AsyncTask<Void, Void, T> {
    private Class<T> classOfT;
    private WeakReference<Activity> act;
    private ResponseXXHJListener<T> listener;
    private String url;
    private boolean isShowDialog;
    private KProgressHUD mProgress;
    public static final String TAG = "xiaoxihuiju";
    private Activity activity;
    private int totalTime = 3, currentTime = 0;
    public GetTask(Activity activity, Class<T> clazz, String url, boolean isShowDialog, ResponseXXHJListener<T> lis) {
        this.act = new WeakReference<>(activity);
        this.classOfT = clazz;
        this.url = url;
        this.isShowDialog = isShowDialog;
        this.listener = lis;
        this.activity = activity;
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Activity a = act.get();
        if (isShowDialog) {
            if (mProgress == null) {
                mProgress = KProgressHUD.create(a)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("加载中...");
            }
            if (mProgress.isShowing()) {
                mProgress.dismiss();
                mProgress=null;
                return;
            }
            if (!a.isDestroyed()) {
                mProgress.show();
            }
        }
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
                if (mProgress != null) {
                    mProgress.dismiss();
                    mProgress=null;
                }
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
            DBUtil.StartThread();
        }
        return null;
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPostExecute(T t) {
        try {
            if (isShowDialog) {
                if (mProgress != null) {
                    mProgress.dismiss();
                    mProgress=null;
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "request task dialog exception:" + e.toString());
        }
        Activity a = act.get();
        if (a != null && !a.isFinishing() && !a.isDestroyed()) {
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
        } else {
            listener = null;
            Log.e(TAG, "activity isdestroyed!!!");
        }
    }
}
