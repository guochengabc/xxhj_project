package com.kongtiaoapp.xxhj.activites;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.MySplashAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.base.MainFragment;
import com.kongtiaoapp.xxhj.bean.ModuleBean;
import com.kongtiaoapp.xxhj.bean.VersionBean;
import com.kongtiaoapp.xxhj.fragment.AddressListFragment;
import com.kongtiaoapp.xxhj.fragment.Group_RunningFragment;
import com.kongtiaoapp.xxhj.fragment.MeFragment;
import com.kongtiaoapp.xxhj.login_register.activity.LoginActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.MainPresenter;
import com.kongtiaoapp.xxhj.mvp.view.MainView;
import com.kongtiaoapp.xxhj.service.MyUpdateService;
import com.kongtiaoapp.xxhj.ui.dialog.MyVersionDialog;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;
import com.kongtiaoapp.xxhj.workorder.fragment.WorkOrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Shinelon on 2016/6/7.
 */
public class MainActivity extends BaseActivity<MainPresenter, MainView> implements RadioGroup.OnCheckedChangeListener, Group_RunningFragment.GetFragment, MainView {
    @BindView(R.id.F1)
    FrameLayout frameLayout;
    @BindView(R.id.radio0)
    RadioButton radio0;
    @BindView(R.id.radio1)
    RadioButton radio1;
    @BindView(R.id.radio3)
    RadioButton radio3;
    @BindView(R.id.radio4)
    RadioButton radio4;
    @BindView(R.id.radioGroup1)
    RadioGroup radioGroup1;
    @BindView(R.id.r1)
    RelativeLayout r1;
    @BindView(R.id.rela_main)
    RelativeLayout rela_main;
    private List<String> list = new ArrayList<String>();
    private long exitTime;//退出键时间记录
    private String projectId, localVersion, serverVersion, url;
    private boolean isNoRepeatCheck = true;
    private boolean isFirst = true;//是否第一次点击主页
    private Runnable runnable3 = new Runnable() {
        @Override
        public void run() {
            MyVersionDialog.Builder builder = new MyVersionDialog.Builder(MainActivity.this);
            builder.setTitle("更新信息");
            builder.setMessage(adapter);
            builder.setPositiveButton("立即更新", (dialog, which) -> {
                downLoadApk();
                dialog.dismiss();
            });
            builder.setNegativeButton("以后再说",
                    (dialog, which) -> {
                        dialog.dismiss();
                    });
            builder.create().show();
        }
    };
    private MySplashAdapter adapter;
    private String main = "", leader = "";
    private boolean isFirstRunning = true;
    private MainFragment runningFragment;
    private WorkOrderFragment workOrderFragment;
    private AddressListFragment messageFragment;
    private MeFragment meFragment;
    private Fragment[] list_fragemnt;
    private int preIndex, mCurrentPostion;//preIndex  上一个位置   mCurrentPosition当前位置
    private Group_RunningFragment group_runningFragment;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                runningFragment.onResume();
            }
        }
    };
    private String intentName = "";

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    private void checkVersion() {
        //获取服务器版本并检查更新
        presenter.onResume(this);
    }

    private void local2serverVersionCheck(VersionBean.ResobjBean versionBean) {
        //获取版本数据
        serverVersion = versionBean.getVersion();
        url = versionBean.getAppUrl();
        App.sp.setCheckVersion(serverVersion);
        App.sp.setUrl(url);
        list = versionBean.getDescription();
        if (list == null) {
            return;
        }
        if (serverVersion != null) {
            //检测版本更新
            try {
                PackageInfo packageInfo = null;
                packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                localVersion = packageInfo.versionName;
                if (!localVersion.equals(serverVersion)) {
                    dialog();
                } else {
                    App.sp.setCheckCount("当前为最新版本");
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            return;
        }
    }

    //类似一个dialog作用
    private void dialog() {
        adapter = new MySplashAdapter(this, list);
        handler.postDelayed(runnable3, 500);
    }

    /*
   * 从服务器中下载APK
   */
    protected void downLoadApk() {
        Intent intent = new Intent(MainActivity.this, MyUpdateService.class);
        intent.putExtra("Key_App_Name", "小溪  ");
        intent.putExtra("Key_Down_Url", ConstantValue.APP_URL + url);
        startService(intent);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio0:
                if (isNoRepeatCheck) {//防止第一个重复点击2次
                    App.sp.setIsMainSecond("false");
                    switchFragments(0);
                    mCurrentPostion = 0;
                    isNoRepeatCheck = false;
                }
                break;
            case R.id.radio1:
                App.sp.setIsMainSecond("true");
                isNoRepeatCheck = true;
                mCurrentPostion = 1;
                switchFragments(1);
                break;
            case R.id.radio3:
                App.sp.setIsMainSecond("false");
                isNoRepeatCheck = true;
                mCurrentPostion = 2;
                switchFragments(2);
                break;
            case R.id.radio4:
                App.sp.setIsMainSecond("false");
                isNoRepeatCheck = true;
                mCurrentPostion = 3;
                switchFragments(3);
                break;
        }
    }

    /**
     * 选择Fragments
     */
    protected void switchFragments(int i) {
        switch (i) {
            case 0:
                list_fragemnt[i] = runningFragment;
                workOrderFragment.setUserVisibleHint(false);
                if (isFirst == false) {
                    App.sp.setIsMainPause("false");//主页在运行
                    runningFragment.onResume();
                }
                isFirst = false;
              /*  String leader_type = App.sp.isLeader();
                if (leader_type.equals("BA") || leader_type.equals("BB") || leader_type.equals("BC")) {
                    list_fragemnt[i] = runningFragment;
                    runningFragment.onResume();
                } else if (leader_type.equals("AA") || leader_type.equals("AB") || leader_type.equals("AC")) {
                    list_fragemnt[i] = group_runningFragment;
                }*/
                break;
            case 1:
                isMainPause();
                list_fragemnt[i] = workOrderFragment;
                workOrderFragment.setUserVisibleHint(true);
                break;
            case 2:
                isMainPause();
                list_fragemnt[i] = messageFragment;
                workOrderFragment.setUserVisibleHint(false);

                messageFragment.setUserVisibleHint(true);
                break;
            case 3:
                isMainPause();
                list_fragemnt[i] = meFragment;
                workOrderFragment.setUserVisibleHint(false);
                meFragment.onResume();
                break;
        }
        getSupportFragmentManager().beginTransaction()
                .hide(list_fragemnt[preIndex]).show(list_fragemnt[i])
                .commitAllowingStateLoss();
        preIndex = i;
    }

    private void isMainPause() {
        if (App.sp.getIsMainPause().equals("false")) {//如果主页走暂停的方法了，那么就不让他继续暂停
            App.sp.setIsMainPause("true");
            runningFragment.onPause();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation  startFragment
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String mains = intent.getStringExtra("main");
        String whichName = intent.getStringExtra("intentName");
        if (whichName != null) {
            intentName = whichName;
        }
        if (mains != null && !mains.isEmpty()) {
            if (mains.equals("resume")) {
                main = "resume";
                if (savedInstanceState != null) {
                    //当activity被回收的时候，根据fragment的tag进行找回
                    runningFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag("startFragment");
                }
            }

        } else {
            rela_main.setVisibility(View.GONE);
            if (App.sp.isFirstLogoin()) {
                enter_Guide();
            } else {
                toLoginAcitivity();
            }
        }
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                        | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    protected void toLoginAcitivity() {

        if (App.sp.isLogin()) {
            main = "resume";
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (main.equals("resume")) {
            isFirst = true;
            App.sp.setIsMain("true");
            leader = App.sp.isLeader();
            presenter.getModule(this);


        }

    }


    private void enter_Guide() {//进入向导页
        Intent intent = new Intent(this, MyGuideActivity.class);
        startActivity(intent);
        finish();
    }
/*

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
*/


    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtils.showToast(this, getString(R.string.quit_again), 0);
            exitTime = System.currentTimeMillis();
        } else {
            AllActivityManager instance = AllActivityManager.getInstance();
            instance.finishAllActivity();
            overridePendingTransition(R.anim.enter, R.anim.exit);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    @Override
    public void getIndex(int index) {
        switch (index) {
            case 1:
                radioGroup1.check(R.id.radio1);
                break;
            case 2:
                radioGroup1.check(R.id.radio3);
                break;
            case 3:
                radioGroup1.check(R.id.radio4);
                break;
            default:
                radioGroup1.check(R.id.radio0);
                break;
        }

    }

    @Override
    public void VersionCheck(Object check) {
        VersionBean bean = (VersionBean) check;
        if (bean.getCode() == 40000) {
            local2serverVersionCheck(bean.getResobj());
        }
    }

    @Override
    public void getModule(Object data) {
        if (isFirstRunning) {
            isFirstRunning = false;
            rela_main.setVisibility(View.VISIBLE);
            ModuleBean bean = (ModuleBean) data;
            ModuleBean.ResobjBean resobj = bean.getResobj();
            List<ModuleBean.ResobjBean.MainArrayBean> mainArray = resobj.getMainArray();
            list_fragemnt = new Fragment[4];
            runningFragment = new MainFragment();
            if (intentName != null || !intentName.equals("")) {
                Bundle bundle = new Bundle();
                bundle.putString("intentName", intentName);
                bundle.putSerializable("mainFragment",mainArray.get(0));
                runningFragment.setArguments(bundle);
            }
            list_fragemnt[0] = runningFragment;
            workOrderFragment = new WorkOrderFragment();
            list_fragemnt[1] = workOrderFragment;
            messageFragment = new AddressListFragment();
            list_fragemnt[2] = messageFragment;
            meFragment = new MeFragment();
            list_fragemnt[3] = meFragment;
            for (int i = 0; i < mainArray.size(); i++) {
                String mainFragment = mainArray.get(i).getMainFragment();
                Drawable drawable = null;
                switch (mainFragment) {
                    case "00":
                        radio0.setText(mainArray.get(i).getMainName());
                        radio0.setVisibility(View.VISIBLE);
                        break;
                    case "01":
                        radio1.setText(mainArray.get(i).getMainName());
                        radio1.setVisibility(View.VISIBLE);
                        break;
                    case "02":
                        radio3.setText(mainArray.get(i).getMainName());
                        radio3.setVisibility(View.VISIBLE);
                        break;
                    case "03":
                        radio4.setText(mainArray.get(i).getMainName());
                        radio4.setVisibility(View.VISIBLE);
                        break;
                }
            }
              /*  if (leader.equals("BA") || leader.equals("BB") || leader.equals("BC")) {//代表的是分公司管理员或者普通用户*/


            // messageFragment = new MessageFragment();


            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            //startFragment做个标记防止activity被回收时，onreate(saveinstates==null)
            transaction.add(R.id.F1, list_fragemnt[0], "startFragment").hide(list_fragemnt[1]).hide(list_fragemnt[2]).hide(list_fragemnt[3]).
                    add(R.id.F1, list_fragemnt[1]).hide(list_fragemnt[0]).hide(list_fragemnt[2]).hide(list_fragemnt[3])
                    .add(R.id.F1, list_fragemnt[2]).hide(list_fragemnt[0]).hide(list_fragemnt[1]).hide(list_fragemnt[3])
                    .add(R.id.F1, list_fragemnt[3]).hide(list_fragemnt[0]).hide(list_fragemnt[1]).hide(list_fragemnt[2]).commit();
            radioGroup1.setOnCheckedChangeListener(this);
            radioGroup1.check(R.id.radio0);

            checkVersion();//检查版本更新
        }
    }



    @Override
    public void setText(Object text) {

    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {

    }


    @Override
    protected void onPause() {
        super.onPause();
        App.sp.setIsMainSecond("false");
        App.sp.setIsMain("false");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacks(runnable3);
            handler = null;
        }
        if (list != null) {
            list.clear();
            list = null;
        }
        App.sp.removeSp("IsFirstToken");
    }
}
