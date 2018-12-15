package com.kongtiaoapp.xxhj.activites;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.UserManegerList;
import com.kongtiaoapp.xxhj.bean.EnterRunning;
import com.kongtiaoapp.xxhj.fragment.Group_RunningFragment;
import com.kongtiaoapp.xxhj.fragment.InformationFragment;
import com.kongtiaoapp.xxhj.fragment.Project_detail_mainFragment;
import com.kongtiaoapp.xxhj.fragment.RunningFragment3;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.HVAC_MainPresenter;
import com.kongtiaoapp.xxhj.mvp.view.HVAC_MainView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HVAC_MainActivity extends BaseActivity<HVAC_MainPresenter, HVAC_MainView> implements RadioGroup.OnCheckedChangeListener,
    Group_RunningFragment.GetFragment, HVAC_MainView{
    @BindView(R.id.radio0)
    RadioButton radio0;
    @BindView(R.id.radio1)//诊断
    RadioButton radio1;
    @BindView(R.id.radio2)
    RadioButton radio2;
    @BindView(R.id.radioGroup1)
    RadioGroup radioGroup1;
    @BindView(R.id.rela_main)
    RelativeLayout rela_main;
    private List<String> list = new ArrayList<String>();
    private boolean isNoRepeatCheck = true;
    private String leader = "";
    private RunningFragment3 runningFragment;
    private InformationFragment informationFragment;
    private Project_detail_mainFragment messageFragment;
    private Fragment[] list_fragemnt;
    private int preIndex, mCurrentPostion;//preIndex  上一个位置   mCurrentPosition当前位置
    private Group_RunningFragment group_runningFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEvent(EnterRunning eventbusBean) {
        if (eventbusBean.getEnterRunning() != null && eventbusBean.getEnterRunning().equals("ok")) {
            isNoRepeatCheck = true;
            preIndex = 1;
            onCheckedChanged(radioGroup1, R.id.radio0);
            radio0.setChecked(true);
            radio1.setChecked(false);
        }

    }

    @Override
    protected int initContentView() {
        return R.layout.activity_hvac__main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        setModule();//设置暖通空调显示的模块
    }

    @Override
    protected HVAC_MainPresenter getPresenter() {
        return new HVAC_MainPresenter();
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.radio0:
                if (isNoRepeatCheck) {//防止第一个重复点击2次
                    switchFragments(0);
                    mCurrentPostion = 0;
                    isNoRepeatCheck = false;
                }
                break;
            case R.id.radio1:
                isNoRepeatCheck = true;
                mCurrentPostion = 1;
                switchFragments(1);
                break;
            case R.id.radio2:
                isNoRepeatCheck = true;
                mCurrentPostion = 2;
                switchFragments(2);
                break;
        }
    }

    /**
     * 选择Fragments
     */
    protected void switchFragments(int i) {
        switch (i) {
            case 0:
                String leader_type = App.sp.isLeader();
                if (UserManegerList.HVAC()) {//A代表集团  B代表分公司 C代表普通员工
                    list_fragemnt[i] = runningFragment;
                    runningFragment.onResume();
                } else if (leader_type.equals("AB") || leader_type.equals("AC")) {
                    list_fragemnt[i] = group_runningFragment;
                }
                break;
            case 1:
                list_fragemnt[i] = messageFragment;
                messageFragment.setUserVisibleHint(true);
                break;
            case 2:
                list_fragemnt[i] = informationFragment;
                break;

        }
        getSupportFragmentManager().beginTransaction()
                .hide(list_fragemnt[preIndex]).show(list_fragemnt[i])
                .commitAllowingStateLoss();
        preIndex = i;
    }

    private void setModule() {
        leader = App.sp.isLeader();
        rela_main.setVisibility(View.VISIBLE);
        if (UserManegerList.HVAC()) {//代表的是分公司管理员或者普通用户
            runningFragment = new RunningFragment3();
            messageFragment = new Project_detail_mainFragment();
            informationFragment = new InformationFragment();
            list_fragemnt = new Fragment[]{runningFragment, messageFragment, informationFragment};
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.F1, list_fragemnt[0]).hide(list_fragemnt[1]).hide(list_fragemnt[2]).
                    add(R.id.F1, list_fragemnt[1]).hide(list_fragemnt[0]).hide(list_fragemnt[2]).add(R.id.F1, list_fragemnt[2]).hide(list_fragemnt[0]).hide(list_fragemnt[1])
                    .hide(list_fragemnt[0]).hide(list_fragemnt[1]).hide(list_fragemnt[2]).commit();
            radioGroup1.setOnCheckedChangeListener(this);
            radioGroup1.check(R.id.radio0);
        } else if (leader.equals("AB") || leader.equals("AC")) {//代表的是集团管理员或者普通用户
            radio0.setText("首页");
            group_runningFragment = new Group_RunningFragment();
            informationFragment = new InformationFragment();
            messageFragment = new Project_detail_mainFragment();
            list_fragemnt = new Fragment[]{group_runningFragment, messageFragment, informationFragment};
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.F1, list_fragemnt[0]).hide(list_fragemnt[1]).hide(list_fragemnt[2]).
                    add(R.id.F1, list_fragemnt[1]).hide(list_fragemnt[0]).hide(list_fragemnt[2])
                    .add(R.id.F1, list_fragemnt[2]).hide(list_fragemnt[0]).hide(list_fragemnt[1])
                    .hide(list_fragemnt[0]).hide(list_fragemnt[1]).hide(list_fragemnt[2]).commit();
            radioGroup1.setOnCheckedChangeListener(this);
            radioGroup1.check(R.id.radio0);
        }

    }

    @Override
    public void getIndex(int index) {
        switch (index) {
            case 0:
                radioGroup1.check(R.id.radio0);
                break;
            case 1:
                radioGroup1.check(R.id.radio1);
                break;
            case 2:

                radioGroup1.check(R.id.radio0);
                break;

            default:
                radioGroup1.check(R.id.radio0);
                break;
        }

    }

    @Override
    public void setText(Object text) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // super.onSaveInstanceState(outState);总是执行这句代码来调用父类去保存视图层的状态，会导致fragmen重影，不能正常恢复状态  有时会出现折叠现象
        outState.putInt("position", mCurrentPostion);
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        int position = bundle.getInt("position");
        getIndex(position);
        super.onRestoreInstanceState(bundle);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (list != null) {
            list.clear();
            list = null;
        }
    }


}
