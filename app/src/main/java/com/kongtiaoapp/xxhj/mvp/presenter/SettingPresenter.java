package com.kongtiaoapp.xxhj.mvp.presenter;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.activites.AllActivityManager;
import com.kongtiaoapp.xxhj.activites.SettingActivity;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.login_register.activity.LoginActivity;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;
import com.kongtiaoapp.xxhj.mvp.moduleipl.SettingModulelpl;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

/**
 * Created by xxhj_g on 2017/5/2.
 */
public class SettingPresenter extends BasePresenterLpl<BaseView, SettingModulelpl> {
    @Override
    protected SettingModulelpl getModel() {
        return new SettingModulelpl();
    }

    public void Quit_Login(SettingActivity activity) {
        getModel().getDataForservices(activity, "", new ResponseXXHJListener() {
            @Override
            public void requuestError(int code) {

            }

            @Override
            public void requestSuccess(Object o) {
                RBResponse data = (RBResponse) o;
                if (data.getCode() == 40000) {
                    App.sp.setLoginState(false);
                    App.sp.resetSp();
                    System.gc();
                    AllActivityManager.getInstance().finishAllActivity();
                    activity.startActivity(new Intent(activity, LoginActivity.class));
                    activity.finish();
                }else{
                    ToastUtils.showToast(activity,data.getErrormsg());
                }
            }
        });
    }
    public void ShowVersion(SettingActivity activity){//显示当前版本号
        // 包的管理者，获取应用程序中清单文件里的所有信息了
        PackageManager packageManager = activity.getPackageManager();
        // packageName : 当前应用程序的包名
        // flags:指定的信息
        try {
            // getPackageName() : 获取应用程序的包名
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    activity.getPackageName(), 0);
            // 获取当前应用程序的版本号
           getView().setText(packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            // 找不到包名的异常
            e.printStackTrace();
        }
    }
}
