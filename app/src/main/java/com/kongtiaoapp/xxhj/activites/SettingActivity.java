package com.kongtiaoapp.xxhj.activites;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.UserManegerList;
import com.kongtiaoapp.xxhj.login_register.activity.ModifyPWDActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;
import com.kongtiaoapp.xxhj.mvp.presenter.SettingPresenter;
import com.kongtiaoapp.xxhj.service.MyUpdateService;
import com.kongtiaoapp.xxhj.utils.FileUtil;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Luoye on 2016-6-15.
 * 设置页面
 */
public class SettingActivity extends BaseActivity<SettingPresenter, BaseView> implements BaseView {

    @ViewInject(R.id.tv_version_code)
    private TextView mVersionCode;//版本号
    @ViewInject(R.id.rl_invite)
    private RelativeLayout rl_invite;
    @ViewInject(R.id.tv_version_upDate)
    private TextView tv_version_upDate;//检查版本更新
    private String localVersion;
    private Handler handler;
    private Runnable runnable;

    @Event(value = {R.id.iv_back, R.id.rl_app_down, R.id.rl_invite, R.id.rl_about_our
            , R.id.rl_appIntroduct, R.id.rl_feedback, R.id.rl_version
            , R.id.rl_exit, R.id.rl_modify,R.id.rl_upDate})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回按钮
                finish();
                break;
            case R.id.rl_invite:
                startActivity(new Intent(this, InviteActivity.class));
                break;
            case R.id.rl_app_down://扫描下载APP
                startActivity(new Intent(this, AppDownActivity.class));
                break;
            case R.id.rl_about_our://关于我们
                startActivity(new Intent(this, AboutUsActivity.class));
                break;
            case R.id.rl_appIntroduct://app产品说明
                startActivity(new Intent(this, AppIntroductActivity.class));
                break;
            case R.id.rl_feedback://意见反馈
                startActivity(new Intent(this, FeedBackActivity.class));
                break;
            case R.id.rl_modify://修改密码
            startActivity(new Intent(this, ModifyPWDActivity.class));
            break;
            case R.id.rl_version://当前版本
                break;
            case R.id.rl_upDate://检查更新
                if (App.sp.getLoad()==false){
                    checkVersion();
                }
                break;
          /*  case R.id.rl_userinfo://用户管理
                startActivity(new Intent(this,UserInfoActivity.class));
                break;*/
            case R.id.rl_exit://退出登录
                presenter.Quit_Login(this);//调退出登录接口
                break;
        }
    }

    private void checkVersion() {
        if (App.sp.getCheckVersion().trim().equals(localVersion.trim())){
            tv_version_upDate.setText("当前为最新版本");
        }else{
            if (App.sp.getCheckCount().equals("点击安装")){
                try {
                    App.sp.setCheckCount("0");
                    Uri uri = Uri.fromFile(FileUtil.updateFile);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setDataAndType(uri,"application/vnd.android.package-archive");
                    this.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showToast(SettingActivity.this,"安装包已丢失，请点击重新下载");
                }
            }else{
                App.sp.isLoad(true);
                tv_version_upDate.setText("更新中");
                setCheckCount();
                Intent intent = new Intent(this, MyUpdateService.class);
                intent.putExtra("Key_App_Name", "小溪  ");
                intent.putExtra("Key_Down_Url", ConstantValue.APP_URL + App.sp.getUrl());
                startService(intent);
            }

        }
    }


    @Override
    protected int initContentView() {

        return R.layout.activity_setting;
    }


    @Override
    protected void initView() {
        x.view().inject(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        if (UserManegerList.HVAC_Manager()) {
            rl_invite.setVisibility(View.VISIBLE);
        }
        presenter.ShowVersion(this);//显示当前版本号信息
        if (App.sp.getCheckCount().equals("点击安装")){
            tv_version_upDate.setText("点击安装");
        }if (App.sp.getCheckCount().equals("当前为最新版本")){
            tv_version_upDate.setText(App.sp.getCheckCount());
        }
        else{
            setCheckCount();//显示更新数量
        }



    }

    private void setCheckCount() {
        if (App.sp.getLoad()==true){
            handler=new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        tv_version_upDate.setText(App.sp.getCheckCount());
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    handler.postDelayed(this,1300);
                }
            };
            handler.postDelayed(runnable,0);
        }
    }

    @Override
    protected SettingPresenter getPresenter() {
        return new SettingPresenter();
    }


    @Override
    public void setText(Object text) {
        mVersionCode.setText(text.toString());
        localVersion = text.toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (handler!=null){
                handler.removeCallbacksAndMessages(null);
                handler=null;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}