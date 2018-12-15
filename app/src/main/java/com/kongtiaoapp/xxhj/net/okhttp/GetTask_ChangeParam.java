package com.kongtiaoapp.xxhj.net.okhttp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.ui.progress.KProgressHUD;
import com.kongtiaoapp.xxhj.utils.NetworkUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guoc on 2016/12/24.
 */
public class GetTask_ChangeParam<T> extends AsyncTask<Void, Void, List<T>> {
    private WeakReference<Activity> act;
    private ResponseXXHJListener<List<T>> listener;
    private String url;
    private boolean isShowDialog;
    private KProgressHUD mProgress;
    public static final String TAG = "xiaoxihuiju";
    private Activity activity;
    private String key;
    Class<T[]> type;

    public GetTask_ChangeParam(Activity activity, Class<T[]> type, String url, boolean isShowDialog, ResponseXXHJListener<List<T>> lis) {
        this.act = new WeakReference<>(activity);
        this.url = url;
        this.isShowDialog = isShowDialog;
        this.listener = lis;
        this.type = type;
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
                return;
            }

            mProgress.show();
        }
    }

    @Override
    protected List<T> doInBackground(Void... params) {
        try {
            Log.e(TAG, "url:" + url);

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = OkHttpUtil
                    .getClient().newCall(request).execute();
            if (response == null) {
                return null;
            }

            if (!response.isSuccessful()) {
                return null;
            }

            String result = response.body().string();
            if (TextUtils.isEmpty(result)) {
                return null;
            }
            Log.e(TAG, result);
            JSONObject jsonObjs = new JSONObject(result);
            String s = jsonObjs.getString("resobj");
            JSONObject jsonObject = new JSONObject(s);
            Iterator iterator = jsonObject.keys();
            if (iterator.hasNext()) {
                key = iterator.next() + "";
                App.sp.setPaintType(key);
            }
            Gson gson = new Gson();
            String str = jsonObject.getString(key);
            T[] ts = gson.fromJson(str, type);
            List<T> t = Arrays.asList(ts);
            return t;
        } catch (Exception e) {
            Log.e(TAG, "GetTask Exception:" + e.toString());
        }

        return null;
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPostExecute(List<T> t) {
        try {
            if (isShowDialog) {
                if (mProgress != null) {
                    mProgress.dismiss();
                }
            }

        } catch (Exception e) {
            Log.e(TAG, "request task dialog exception:" + e.toString());
        }

        Activity a = act.get();
        if (a != null && !a.isFinishing() && !a.isDestroyed()) {
            if (listener != null) {
                if (t == null) {
                    if (NetworkUtils.checkNetwork(activity)==false){
                        ToastUtils.showToast(activity,activity.getString(R.string.please_net_work),activity.getResources().getColor(R.color.a666666),"");
                    }
                    listener.requuestError(0);//网络异常
                } else {
                    listener.requestSuccess(t);
                }
            }
        } else {
            listener = null;
            Log.e(TAG, "activity isdestroyed!!!");
        }
    }
}
