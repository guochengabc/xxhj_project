package com.kongtiaoapp.xxhj.mvp.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.AllActivityManager;
import com.kongtiaoapp.xxhj.net.CPNException;
import com.kongtiaoapp.xxhj.ui.progress.KProgressHUD;
import com.kongtiaoapp.xxhj.ui.view.DoubleButtonDialog;
import com.kongtiaoapp.xxhj.ui.view.ToastDialog;
import com.kongtiaoapp.xxhj.ui.widget.SystemBarTintManager;
import com.kongtiaoapp.xxhj.utils.KeyBoardUtils;
import com.kongtiaoapp.xxhj.utils.NetworkUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.xutils.ex.HttpException;

import java.util.Map;

import butterknife.ButterKnife;

/**
 *
 */
public abstract class BaseActivity<P extends BasePresenterLpl, V extends BaseView> extends AppCompatActivity {
    protected static final int REQUEST_CODE = 1000;
    protected static final int REQUEST_SECOND = 1100;
    protected static final int RESULT_CODE = 1000;
    protected FragmentManager FM;
    private SharedPreferences.Editor EDIT;
    protected Context mContext;
    private KProgressHUD mProgress;
    protected String TAG = "xiaoxihuiju";
    protected static Gson gson = gson = new Gson();
    private DoubleButtonDialog mDoubleButtonDialog;
    private ToastDialog toastDialog;
    protected P presenter;
    public Handler baseHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 自定义异常
                case 100:
                    showMyException((CPNException) msg.obj);
                    break;
                // 网络异常
                case 101:
                    showHttpException((HttpException) msg.obj);
                    break;
                // json 异常
                case 102:
                    showJSONException();
                    break;
                default:
                    break;
            }
        }
    };
    private AllActivityManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        manager = AllActivityManager.getInstance();
        manager.pushOneActivity(this);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题栏
        //   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 竖屏
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);// 进入界面后不让软键盘弹出
        //初始化通用的SP&EDIT
        mContext = getApplicationContext();
        //Fragment相关
        FM = getSupportFragmentManager();
        TAG = getClass().getSimpleName();// 得到类名
        setContentView(initContentView());
        ButterKnife.bind(this);//初始化ButterKnife
        presenter = getPresenter();
        presenter.attach((V) this);
        initView();

        /**
         * API＞19即安卓4.4以上才生效
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.a000000);// 通知栏颜色，这里设置为绿色
        }

        initListener();
        initData();
        // 打印当前活动名
        Log.d("BaseActivity", getClass().getSimpleName());
    }


    /**
     * 自定义状态栏
     *
     * @param on
     */
    @SuppressLint("InlinedApi")
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 禁止项目文字大小跟随系统变化，预防布局错乱
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();

        // config.setToDefaults();
        config.fontScale = 1.0f;
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;

    }

    protected boolean checkNetworked() {
        if (!NetworkUtils.checkNetwork(this)) {
            ToastUtils.showToast(this, getString(R.string.no_net));
            return false;
        }

        return true;
    }

    /**
     * 初始化contentView
     *
     * @return 返回contentView的layout id
     */
    protected abstract int initContentView();

    /**
     * 初始化View，执行findViewById操作
     */
    protected abstract void initView();


    /**
     * 初始化监听器
     */
    protected abstract void initListener();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    protected abstract P getPresenter();

    /**
     * 弹出一个进度对话框
     */
    protected void showProgressDialog() {
        if (mProgress == null) {
            mProgress = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("加载中...");
        }
        if (mProgress.isShowing()) {
            return;
        }

        mProgress.show();
    }

    /**
     * 返回键的点击事件
     *
     * @param v
     */
    public void back(View v) {
        if (KeyBoardUtils.isShowSoftInput(this)) {
            KeyBoardUtils.hideSoftInput(this);
        }
        finish();
    }

    /**
     * 关闭弹出的进度对话框
     */
    protected void dismissProgressDialog() {
        if (mProgress != null) {
            mProgress.dismiss();

        }


    }

    private Toast mToast;

    /**
     * 土司方法
     *
     * @param from
     * @param message
     */
 /*   public void showToast(String from, final String message) {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                if (mToast != null) {
                    mToast.setText(message);
                } else {
                    mToast = Toast.makeText(BaseActivity.this, message,
                            Toast.LENGTH_SHORT);
                    ToastUtils.showToast(BaseActivity.this, message);
                    // View view = mToast.getView();
                    // view.setBackgroundResource(R.drawable.shape_donate_money_background);
                }
                mToast.show();
            }
        });
        if (from != null && !from.equals("")) {
            Log.e(from, message);
        }
    }*/

    /**
     * 将请求参数转为Json格式
     *
     * @param method 网络请求方法名
     * @param map    参数集合
     * @return
     */
    public String map2Json(String method, Map<String, String> map) {

        String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + method + "\"" + "," + "\"" + "reqobj" + "\"" + ":" + gson.toJson(map).toString() + "}";

//        Map<String, String> maps = new HashMap<String, String>();
//        maps.put("method", method);
//        maps.put("reqobj", gson.toJson(map).toString());
        return string;
    }

    public String map2Json(String method) {

        String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + method + "\"" + "}";

//        Map<String, String> maps = new HashMap<String, String>();
//        maps.put("method", method);
//        maps.put("reqobj", gson.toJson(map).toString());
        return string;
    }

    public String map2Json(String method, String deviceId, String data) {

        String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + method + "\"" + "," + "\"" + "reqobj" + "\"" + ":" + "{" + "\"" + "DeviceId" + "\"" + ":" + deviceId + "," + "\"" + "Data" + "\"" + ":" + data + "}" + "}";

//        Map<String, String> maps = new HashMap<String, String>();
//        maps.put("method", method);
//        maps.put("reqobj", gson.toJson(map).toString());
        return string;
    }

    public String map2Jsonp(String method, String projectId, String data) {

        String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + method + "\"" + "," + "\"" + "reqobj" + "\"" + ":" + "{" + "\"" + "ProjectId" + "\"" + ":" + projectId + "," + "\"" + "Data" + "\"" + ":" + data + "}" + "}";

//        Map<String, String> maps = new HashMap<String, String>();
//        maps.put("method", method);
//        maps.put("reqobj", gson.toJson(map).toString());
        return string;
    }

    /**
     * 显示有取消的提示框
     *
     * @param message
     * @param code
     * @description 默认传 0 点击确定消失 如果点击其他请重写 DialogCallBack
     */

    public void ShowDoubleButtonDialog(String message, int code) {

        if (mDoubleButtonDialog == null) {
            mDoubleButtonDialog = new DoubleButtonDialog(BaseActivity.this,
                    R.style.MyLoadDialog);
        }
        mDoubleButtonDialog.setTextInfo(message);
        mDoubleButtonDialog.setCode(code);
        mDoubleButtonDialog.getButtonCannel().setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        cancel();
                        mDoubleButtonDialog.dismiss();
                    }
                });
        mDoubleButtonDialog.getButtonOk().setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        if (mDoubleButtonDialog.getCode() == 0) {
                            mDoubleButtonDialog.dismiss();
                        } else {
                            doubleButtonDialogCallBack(mDoubleButtonDialog);
                        }
                    }
                });
        mDoubleButtonDialog.show();
    }

    /**
     * 显示带取消的提示框
     *
     * @param message
     * @param code
     * @description 默认传 0 点击确定消失 如果点击其他请重写 ToastDialogCallBack
     */
    public void ShowToastDialog(final String message, final int code) {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                if (toastDialog == null) {
                    toastDialog = new ToastDialog(BaseActivity.this,
                            R.style.MyLoadDialog);
                }
                toastDialog.setTextInfo(message);
                toastDialog.setCode(code);
                toastDialog.getButtonOk().setOnClickListener(
                        new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                if (toastDialog.getCode() == 0) {
                                    toastDialog.dismiss();
                                } else {
                                    toastDialogCallBack(toastDialog);
                                }
                            }
                        });
                if (!isFinishing())
                    toastDialog.show();
            }
        });
    }

    /**
     * 单button 点击监听
     *
     * @param dialog
     */
    protected void toastDialogCallBack(ToastDialog dialog) {
    }

    /**
     * 双button 缺点点击 后执行方法
     *
     * @param dialog
     */
    protected void doubleButtonDialogCallBack(DoubleButtonDialog dialog) {
    }

    protected void cancel() {
        // TODO Auto-generated method stub

    }

    /**
     * 自定义异常显示
     *
     * @param exception
     */
    public void showMyException(CPNException exception) {
        if (exception.getCode().equals("40001")) {
            ShowToastDialog("参数错误", 0);
        } else if (exception.getCode().equals("40002")) {
            ShowToastDialog("系统异常", 0);
        } else if (exception.getCode().equals("40003")) {
            ShowToastDialog("证书错误", 0);
        } else if (exception.getCode().equals("40004")) {
            ShowToastDialog("执行失败", 0);
        } else if (exception.getCode().equals("40005")) {
//            ShowToastDialog("地址信息错误", 0);
            ToastUtils.showToast(BaseActivity.this, getString(R.string.no_data));
        } else if (exception.getCode().equals("40006")) {
//            ShowToastDialog("商品不存在", 0);
        } else if (exception.getCode().equals("40007")) {
            ShowToastDialog("用户不存在", 0);
        } else if (exception.getCode().equals("40008")) {
            ShowToastDialog("参数为空", 0);
        } else if (exception.getCode().equals("40009")) {
            ShowToastDialog("已经存在", 0);
        }
    }

    // 网络异常实体
    public void showHttpException(HttpException exception) {

        switch (Integer.valueOf(exception.getErrorCode())) {
            case 500:
                ToastUtils.showToast(BaseActivity.this, getString(R.string.network_anomaly));
                break;
            case 404:
                ToastUtils.showToast(BaseActivity.this, getString(R.string.network_anomaly));
                break;
            case 0:
                ToastUtils.showToast(BaseActivity.this, getString(R.string.network_timeout));
                break;
            default:
                ToastUtils.showToast(BaseActivity.this, getString(R.string.network_anomaly));
                break;
        }
    }

    /**
     * json异常
     */
    public void showJSONException() {
        ToastUtils.showToast(BaseActivity.this, getString(R.string.network_anomaly));
    }

    protected void sendMessage(int what, Object obj) {
        Message message = new Message();
        message.what = what;
        message.obj = obj;
        this.baseHandler.sendMessage(message);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.deAttach();
        }
        manager.removeOneActivity(this);
        if (toastDialog != null && toastDialog.isShowing()) {
            toastDialog.dismiss();
        }
        if (mDoubleButtonDialog != null && mDoubleButtonDialog.isShowing()) {
            mDoubleButtonDialog.dismiss();
        }
        if (KeyBoardUtils.isShowSoftInput(this)) {
            KeyBoardUtils.hideSoftInput(this);
        }
        if (mProgress != null && mProgress.isShowing()) {
            mProgress.dismiss();
        }

    }

    //此方法只是关闭软键盘
    public void close_key() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    protected void hiddenInput() {
        //没错，下面这一坨就是隐藏软键盘的代码
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i("fffffffff", "不保存状态信息======");
    }
}
