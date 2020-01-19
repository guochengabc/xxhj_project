package com.kongtiaoapp.xxhj.hvac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.EnvironmentStateAdapter;
import com.kongtiaoapp.xxhj.bean.EnvironmentStateBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.EnviromentMonitoringP;
import com.kongtiaoapp.xxhj.mvp.view.EnviromentMonitoringV;
import com.kongtiaoapp.xxhj.ui.view.DF_HScrollView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class EnviromentMonitoringActivity extends BaseActivity<EnviromentMonitoringP, EnviromentMonitoringV> implements EnviromentMonitoringV {
    @BindView(R.id.item_scroll_title)
    DF_HScrollView headerScroll;
    @BindView(R.id.txt_line)
    TextView txt_line;//小黑线
    @BindView(R.id.txt_environment)
    TextView txt_environment;//室内区域
    @BindView(R.id.txt_status)
    TextView txt_status;//风机状态
    @BindView(R.id.txt_control)
    TextView txt_control;//模式控制
    @BindView(R.id.txt_fSetting)
    TextView txt_fSetting;//风机设置
    @BindView(R.id.txt_hourseTemp)
    TextView txt_hourseTemp;//房间温度
    @BindView(R.id.txt_settingTemp)
    TextView txt_settingTemp;//设定温度
    @BindView(R.id.scroll_list)
    ExpandableListView mListView;
    @BindView(R.id.txt_help)
    TextView txt_help;
    private int help = 0;
    //装入所有的HScrollView
    protected List<DF_HScrollView> mHScrollViews = new ArrayList<DF_HScrollView>();
    private List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
    //方便测试，直接写的public
    public HorizontalScrollView mTouchView;

    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        for (DF_HScrollView scrollView : mHScrollViews) {
            //防止重复滑动
            if (mTouchView != scrollView)
                scrollView.smoothScrollTo(l, t);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int initContentView() {
        return R.layout.activity_enviroment_monitoring;
    }

    @Override
    protected void initView() {
        //添加头滑动事件
        mHScrollViews.add(headerScroll);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            String projectId = intent.getStringExtra("projectId");
            presenter.onResume(this,projectId);
        }

    }

    @Override
    protected EnviromentMonitoringP getPresenter() {
        return new EnviromentMonitoringP();
    }

    @OnClick({R.id.iv_back, R.id.img_help})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.img_help:
                ++help;
                if (help % 2 == 1) {
                    txt_help.setVisibility(View.VISIBLE);
                } else if (help % 2 == 0) {
                    txt_help.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setText(Object data) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void lidt_null() {

    }

    @Override
    public void setList(Object data) {
        txt_line.setVisibility(View.VISIBLE);
      /*  String text = AssetsUtils.readText(this, "environment_state.json");
        EnvironmentStateBean environmentStateBean = JSON.parseObject(text, EnvironmentStateBean.class);*/
        EnvironmentStateBean environmentStateBean = (EnvironmentStateBean) data;
        EnvironmentStateBean.ResobjBean resobj = environmentStateBean.getResobj();
        EnvironmentStateBean.ResobjBean.TitleBean titleBean = resobj.getTitle();
        txt_environment.setText(titleBean.getRName());

        String sta = titleBean.getSta();
        if (sta.length() <= 2) {
            txt_status.setText(sta);
        } else {
            txt_status.setText(sta.substring(0, 2) + "\n" + sta.substring(2));
        }

        String cPatt = titleBean.getCPatt();
        if (cPatt.length() <= 2) {
            txt_control.setText(cPatt);
        } else {
            txt_control.setText(cPatt.substring(0, 2) + "\n" + cPatt.substring(2));
        }

        String wss = titleBean.getWSS();
        if (wss.length() <= 2) {
            txt_fSetting.setText(wss);
        } else {
            txt_fSetting.setText(wss.substring(0, 2) + "\n" + wss.substring(2));
        }

        String rTemp = titleBean.getRTemp();
        if (rTemp.length() <= 2) {
            txt_hourseTemp.setText(rTemp);
        } else {
            txt_hourseTemp.setText(rTemp.substring(0, 2) + "\n" + rTemp.substring(2));
        }

        String sTemp = titleBean.getSTemp();
        if (sTemp.length() <= 2) {
            txt_settingTemp.setText(sTemp);
        } else {
            txt_settingTemp.setText(sTemp.substring(0, 2) + "\n" + sTemp.substring(2));
        }
        txt_help.setText(resobj.getRemark());
        EnvironmentStateAdapter adapter = new EnvironmentStateAdapter(this, resobj.getData(), mHScrollViews, mListView);
        mListView.setAdapter(adapter);
        for (int i = 0; i < resobj.getData().size(); i++) {
            mListView.expandGroup(i);
        }
    }
}
