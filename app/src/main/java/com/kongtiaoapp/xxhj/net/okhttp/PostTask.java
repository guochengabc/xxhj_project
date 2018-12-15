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
import com.kongtiaoapp.xxhj.login_register.activity.LoginActivity;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.ui.progress.KProgressHUD;
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
public class PostTask<T> extends AsyncTask<Void, Void, T> {

    private final Class<T> mClass;
    private WeakReference<Activity> act;
    private ResponseXXHJListener<T> listener;
    private String url;
    private RequestBody fb;
    private boolean isShowDialog, canUploadPicture = false;//canUpLoadPicture   代表可以上传图片
    private KProgressHUD mProgress;
    public static final String TAG = "xiaoxihuiju";
    private int totalTime = 3, currentTime = 0;
    private Activity activity;

    public PostTask(Activity activity, Class<T> clazz, String url, FormBody.Builder builder, boolean isShowDialog, ResponseXXHJListener<T> lis) {
        this.act = new WeakReference<>(activity);
        this.mClass = clazz;
        this.url = url;
        this.activity = activity;
        if (!App.sp.getToken().equals("")) {
            builder.add("token", App.sp.getToken());
        }
        this.fb = builder.build();
        this.isShowDialog = isShowDialog;
        this.listener = lis;
    }

    //带二进制上传图片
    public PostTask(Activity activity, Class<T> clazz, String url, MultipartBody.Builder builder, boolean isShowDialog, boolean canUploadPicture, ResponseXXHJListener<T> lis) {
        this.act = new WeakReference<>(activity);
        this.mClass = clazz;
        this.url = url;
        this.activity = activity;
        this.canUploadPicture = canUploadPicture;
        if (!App.sp.getToken().equals("")) {
            builder.addFormDataPart("token", App.sp.getToken());
        }
        this.fb = builder.build();
        this.isShowDialog = isShowDialog;
        this.listener = lis;

    }

    @SuppressLint("NewApi")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Activity a = act.get();
        if (a != null && !a.isFinishing() && !a.isDestroyed()) {
            if (mProgress == null) {
                mProgress = KProgressHUD.create(a)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("加载中...");
                Log.i(TAG,"mProgress为空==");
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
        }

        return null;
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPostExecute(T t) {
        try {
            if (mProgress != null) {
                mProgress.dismiss();
                mProgress=null;

            }
        } catch (Exception e) {
            Log.e(TAG, "PostTask Dialog Exception:" + e.toString());
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
