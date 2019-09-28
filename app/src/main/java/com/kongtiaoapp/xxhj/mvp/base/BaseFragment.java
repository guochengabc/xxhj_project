package com.kongtiaoapp.xxhj.mvp.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.Gson;

import java.util.Map;

import butterknife.ButterKnife;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * BaseFragment
 * Created by Administrator on 2016-6-8.
 */
public abstract class BaseFragment<P extends BasePresenterLpl, V extends BaseView> extends Fragment {
    public View view;
    protected String TAG = getClass().getSimpleName();// 得到类名
    protected Activity mActivity;// 抽取Activity变量，让子类创建布局时使用，指MainUI
    protected P presenter;
    public static final int REQUEST_CODE = 1000;//
    public static final int REQUEST_CODE_TWO=10000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity = getActivity();
        presenter = getPresenter();
        presenter.attach((V) this);
        View view = initView();
        ButterKnife.bind(view);
        return view;
    }

    // Activity创建完成后，回调
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 更新布局
        initData();
    }

    public abstract P getPresenter();

    /**
     * 子类更新界面，不必须实现
     */
    public void initData() {

    }

    public void setTime(int time) {

    }

    public void intentBundle(int type) {

    }

    /**
     * 子类返回具体的控件、布局，必须实现
     *
     * @return
     */
    protected abstract View initView();

    /**
     * 弹出一个进度对话框
     */


    protected static Gson gson = new Gson();

    public static String map2Json(String method, Map<String, String> map) {

        String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + method + "\"" + "," + "\"" + "reqobj" + "\"" + ":" + gson.toJson(map).toString() + "}";
//        Map<String, String> maps = new HashMap<String, String>();
//        maps.put("method", method);
//        maps.put("reqobj", gson.toJson(map).toString());
        return string;
    }
    /**
     *
     * @param activity      跳转到指定activity界面
     */
    public void goActivity(Class activity) {
        startActivity(new Intent(mActivity, activity));
    }
    protected void hiddenInput() {
        //没错，下面这一坨就是隐藏软键盘的代码
        ((InputMethodManager) mActivity.getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.deAttach();
            presenter = null;
        }
    }
}