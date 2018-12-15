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
import com.kongtiaoapp.xxhj.utils.NetworkUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.lang.ref.WeakReference;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by guoc on 2016/12/24.
 */
public class Post_NO_Task<T> extends AsyncTask<Void, Void, T> {

    private final Class<T> mClass;
    private WeakReference<Activity> act;
    private ResponseXXHJListener<T> listener;
    private String url;
    private RequestBody fb;
    private boolean canUploadPicture = false;//canUpLoadPicture   代表可以上传图片
    public static final String TAG = "xiaoxihuiju";
    private int totalTime = 3, currentTime = 0;
    private Activity activity;

    public Post_NO_Task(Activity activity, Class<T> clazz, String url, FormBody.Builder builder , ResponseXXHJListener<T> lis) {
        this.act = new WeakReference<>(activity);
        this.mClass = clazz;
        this.url = url;
        this.activity = activity;
        if (!App.sp.getToken().equals("")) {
            builder.add("token", App.sp.getToken());
        }
        this.fb = builder.build();
        this.listener = lis;
    }

    //带二进制上传图片
    public Post_NO_Task(Activity activity, Class<T> clazz, String url, MultipartBody.Builder builder, boolean canUploadPicture, ResponseXXHJListener<T> lis) {
        this.act = new WeakReference<>(activity);
        this.mClass = clazz;
        this.url = url;
        this.activity = activity;
        this.canUploadPicture = canUploadPicture;
        if (!App.sp.getToken().equals("")) {
            builder.addFormDataPart("token", App.sp.getToken());
        }
        this.fb = builder.build();

        this.listener = lis;

    }

    @SuppressLint("NewApi")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected T doInBackground(Void... params) {
        return setResult(canUploadPicture);
    }

    private T setResult(boolean canPicture) {
        try {
            Log.e(TAG, "url:" + url + fb.toString());
            Request request = new Request.Builder()
                    .url(url)
                    .post(fb)
                    .build();

            Response response = OkHttpUtil.getClient().newCall(request).execute();
            if (response == null || !response.isSuccessful()) {
                return null;
            }

            String result = response.body().string();

            if (TextUtils.isEmpty(result)) {
                return null;
            }

            Log.e(TAG, result);

            Gson g = new Gson();
            T t = g.fromJson(result, mClass);
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

        Activity a = act.get();
        if (a != null && !a.isFinishing() && !a.isDestroyed()) {
            if (listener != null) {
                if (t == null) {
                    if (NetworkUtils.checkNetwork(activity) == false) {
                        ToastUtils.showToast(activity, activity.getString(R.string.please_net_work), activity.getResources().getColor(R.color.a666666), "");
                    }
                    ++currentTime;
                    if (currentTime <= totalTime) {
                        setResult(canUploadPicture);//接着重新进行网络请求，重复连接
                    } else {
                        listener.requuestError(0);//超过请求次数后，停止请求
                    }
                } else {
                    currentTime = 0;
                    listener.requestSuccess(t);
                }
            }
        } else {
            listener = null;
            Log.e(TAG, "activity is recyled!!!");
        }
    }
}
