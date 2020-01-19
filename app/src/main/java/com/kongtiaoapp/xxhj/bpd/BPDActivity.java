package com.kongtiaoapp.xxhj.bpd;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.BPD_TopAdapter;
import com.kongtiaoapp.xxhj.adapter.MyFragmentAdapter;
import com.kongtiaoapp.xxhj.bean.BPD_MainInfoBean;
import com.kongtiaoapp.xxhj.bpd.fragment.BAlarmFragment;
import com.kongtiaoapp.xxhj.bpd.fragment.BDataEntryListFragment;
import com.kongtiaoapp.xxhj.bpd.fragment.BDutyFragment;
import com.kongtiaoapp.xxhj.bpd.fragment.BPD_PaintFragment;
import com.kongtiaoapp.xxhj.bpd.fragment.BReportFragment;
import com.kongtiaoapp.xxhj.bpd.fragment.BThresholdsFragment;
import com.kongtiaoapp.xxhj.bpd.fragment.BWeek_MonthReportFragment;
import com.kongtiaoapp.xxhj.duty.fragment.Duty_ProjectFragment;
import com.kongtiaoapp.xxhj.interfaces.BPD_PaintUpdate;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.BPDP;
import com.kongtiaoapp.xxhj.mvp.view.BPDV;
import com.kongtiaoapp.xxhj.ui.view.CustomViewPager;
import com.kongtiaoapp.xxhj.ui.view.horizontallistview.ListOutView;
import com.kongtiaoapp.xxhj.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * By GC 2018-9-7    变配电
 */
public class BPDActivity extends BaseActivity<BPDP, BPDV> implements BPDV, RadioGroup.OnCheckedChangeListener,BPD_PaintUpdate {
    @BindView(R.id.lv_bpd_top)
    ListOutView lv_energyP;//变配电顶部
    @BindView(R.id.frame__paint)
    FrameLayout frame__paint;
    //图表 日月
    @BindView(R.id.img_left)
    ImageView img_left;
    @BindView(R.id.img_right)
    ImageView img_right;
    @BindView(R.id.group_time)
    RadioGroup group_time;
    @BindView(R.id.radio0)
    RadioButton radio0;//日
    @BindView(R.id.radio1)
    RadioButton radio1;//月

    //第一个分类
    @BindView(R.id.tab_one)
    TabLayout tab_one;
    @BindView(R.id.vpg_one)
    CustomViewPager vpg_one;

    //第二个分类
    @BindView(R.id.tab_two)
    TabLayout tab_two;
    @BindView(R.id.vpg_two)
    CustomViewPager vpg_two;

    private int tabPosition = 0;//tablayout滑动的位置
    private String tabPostion_position = "0";//诊断图tab滑动的位置
    private int tab_running_position = 0;
    private Duty_ProjectFragment duty_projectFragment;
    private String projectId;
    private BPD_MainInfoBean.ResobjBean resobj;
    //日月相关逻辑
    private boolean isFirstPaint = true;
    private int whichPaint = 0;//0  代表的时   1  代表的日
    private boolean isMonth = false;
    private String month;
    private String day;
    private String paramType;
    private String deviceId;
    private BPD_TopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_bpd2;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            projectId = intent.getStringExtra("projectId");
        }
        presenter.onResume(this, projectId);
    }

    @Override
    protected void initListener() {
        group_time.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initData() {
        month = DateUtils.getYM();
        day = DateUtils.getYMD();
        radio0.setBackgroundResource(R.mipmap.green_background_circle);
    }

    private void initSetTabOne() {
        tab_one.setTabMode(TabLayout.MODE_FIXED);
        final String title[] = {"故障报警", "值班信息", "数据录入"};
        final List<Fragment> list_fragemnt = new ArrayList<>();
        //  list_fragemnt.add(new Device_ProjectFragment(vpg_one));
        BAlarmFragment running_projectFragment = new BAlarmFragment(vpg_one);
        BDutyFragment alarmFragment = new BDutyFragment(vpg_one);
        BDataEntryListFragment dataEntryListFragment = new BDataEntryListFragment(vpg_one);
        Bundle bundle = new Bundle();
        bundle.putString("hvac", "true");
        bundle.putString("projectId", projectId);
        dataEntryListFragment.setArguments(bundle);
        list_fragemnt.add(running_projectFragment);//报警信息
        list_fragemnt.add(alarmFragment);//值班信息
        list_fragemnt.add(dataEntryListFragment);//数据录入
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list_fragemnt);
        vpg_one.setAdapter(adapter);
        vpg_one.setOffscreenPageLimit(4);
        tab_one.setupWithViewPager(vpg_one);
        tab_one.setSelectedTabIndicatorColor(getResources().getColor(R.color.theme_color));
        setTabColor(title, tab_one);//设置初始值的textview的状态
        tab_one.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView textView = ((TextView) tab.getCustomView().findViewById(R.id.title_tv));
                textView.setTextColor(getResources().getColor(R.color.theme_color));
                textView.setSelected(true);
                tab_running_position = tab.getPosition();
                vpg_one.setCurrentItem(tab_running_position, false);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView textView = ((TextView) tab.getCustomView().findViewById(R.id.title_tv));
                textView.setTextColor(getResources().getColor(R.color.a666666));
                textView.setSelected(false);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vpg_one.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                vpg_one.resetHeight(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vpg_one.resetHeight(0);

    }

    private void initSetTabTwo(BPD_MainInfoBean.ResobjBean bean) {
        tab_two.setTabMode(TabLayout.MODE_FIXED);
        //final String title[] = {"巡检记录", "记录导出", "参数设置", "设备管理"};
        final String title[] = {"报警阈值", "周报月报", "报表导出"};
        final List<Fragment> list_fragemnt = new ArrayList<>();
        BThresholdsFragment params = new BThresholdsFragment(vpg_two);
        BWeek_MonthReportFragment week_monthReportFragment = new BWeek_MonthReportFragment(vpg_two);
        BReportFragment bReportFragment = new BReportFragment(vpg_two);
        duty_projectFragment = new Duty_ProjectFragment(vpg_two);
        Bundle bundle = new Bundle();
        bundle.putString("projectId", bean.getProjectId());
        params.setArguments(bundle);
        list_fragemnt.add(params);//;//报警阈值
        list_fragemnt.add(week_monthReportFragment);//周报月报
        list_fragemnt.add(bReportFragment);
        // list_fragemnt.add(duty_projectFragment);//设备管理
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list_fragemnt);
        vpg_two.setAdapter(adapter);
        vpg_two.setOffscreenPageLimit(3);
        tab_two.setupWithViewPager(vpg_two);
        tab_two.setSelectedTabIndicatorColor(getResources().getColor(R.color.theme_color));
        setTabColor(title, tab_two);//设置初始值的textview的状态
        tab_two.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView textView = ((TextView) tab.getCustomView().findViewById(R.id.title_tv));
                textView.setTextColor(getResources().getColor(R.color.theme_color));
                textView.setSelected(true);
                tab_running_position = tab.getPosition();
                vpg_two.setCurrentItem(tab_running_position, false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView textView = ((TextView) tab.getCustomView().findViewById(R.id.title_tv));
                textView.setTextColor(getResources().getColor(R.color.a666666));
                textView.setSelected(false);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vpg_two.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                vpg_two.resetHeight(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vpg_two.resetHeight(0);
    }

    private void setTabColor(String[] title, TabLayout tabs) {
        for (int i = 0; i < title.length; i++) {
            TabLayout.Tab tab = tabs.getTabAt(i);
            tab.setCustomView(getTabView(i, title[i]));
        }
    }


    public View getTabView(int position, String title) {
        LayoutInflater mInflater = LayoutInflater.from(this);
        View view = mInflater.inflate(R.layout.icon_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.title_tv);
        textView.setText(title);
        if (position == tabPosition) {
            textView.setTextColor(getResources().getColor(R.color.theme_color));
        } else {
            textView.setTextColor(getResources().getColor(R.color.a666666));
        }
        return view;
    }

    private void initFragment(String time) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        BPD_PaintFragment fragment = BPD_PaintFragment.getInstance();
        Bundle bundle = new Bundle();
        bundle.putString("time", time);
        bundle.putString("projectId", projectId);
        bundle.putString("deviceId", deviceId);
        bundle.putString("type", paramType);
        fragment.setArguments(bundle);
        transaction.add(R.id.frame__paint, fragment).commit();
    }

    @Override
    protected BPDP getPresenter() {
        return new BPDP();
    }

    @OnClick({R.id.iv_back, R.id.txt_record,R.id.img_left, R.id.img_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.txt_record:
                //startActivity(new Intent(this,RecordFormModule.class));
                break;
            case R.id.img_left:
                if (whichPaint == 0) {//时
                    day = DateUtils.getSpecifiedDayBefore(day);
                    getPaintData(day);
                } else if (whichPaint == 1) {//日
                    month = DateUtils.getLastMonth(month);
                    getPaintData(month);
                }
                break;
            case R.id.img_right:
                if (whichPaint == 0) {//时
                    day = DateUtils.getSpecifiedDayAfter(day);

                    getPaintData(day);
                } else if (whichPaint == 1) {//日
                    month = DateUtils.getNextMonth(month);
                    getPaintData(month);
                }
                break;

        }
    }

    @Override
    public void setText(Object data) {
        isFirstPaint = false;//防止图表请求多次   radioGroup先进行请求
        BPD_MainInfoBean bean = (BPD_MainInfoBean) data;
        resobj = bean.getResobj();
        //能耗顶部
        List<BPD_MainInfoBean.ResobjBean.GroupDataBean> groupData = resobj.getGroupData();
        if (groupData != null) {
            String timeDate = "";
            if (whichPaint == 0) {
                timeDate = day;
            } else {
                timeDate = month;
            }
            adapter = new BPD_TopAdapter(groupData, this, projectId, resobj, timeDate);
            lv_energyP.setAdapter(adapter);
        }
        if (groupData != null && !groupData.isEmpty()) {
            List<BPD_MainInfoBean.ResobjBean.GroupDataBean.EnerParamBean> enerParam = groupData.get(0).getEnerParam();
            if (enerParam != null && !enerParam.isEmpty()) {
                BPD_MainInfoBean.ResobjBean.GroupDataBean.EnerParamBean enerParamBean = enerParam.get(0);
                deviceId = enerParamBean.getDeviceId();
                paramType = enerParamBean.getType();
                initFragment(resobj.getTime());//初始化fragment
            }
        }
      /*  initSetTabOne();//设置第一个tab
        initSetTabTwo(resobj);//设置第二个tab*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (isFirstPaint) {
            return;
        }
        switch (checkedId) {
            case R.id.radio0:
                whichPaint = 0;//代表我点击时
                img_left.setImageResource(R.mipmap.leftarrow);
                img_right.setImageResource(R.mipmap.rightarrow);
                isMonth = false;
                radio0.setBackgroundResource(R.mipmap.green_background_circle);
                radio1.setBackgroundResource(R.mipmap.white_background_circle);
                getPaintData(day);
                break;
            case R.id.radio1:
                whichPaint = 1;//代表我点击日
                img_left.setImageResource(R.mipmap.arrow_double_left);
                img_right.setImageResource(R.mipmap.arrow_double_right);
                isMonth = true;
                radio0.setBackgroundResource(R.mipmap.white_background_circle);
                radio1.setBackgroundResource(R.mipmap.green_background_circle);
                getPaintData(month);
                break;
        }
    }

    /**
     * @param time 日月
     *             传值获取图表数据
     */
    private void getPaintData(String time) {
        BPD_PaintFragment fragment = BPD_PaintFragment.getInstance();
        Bundle bundle = new Bundle();
        bundle.putString("time", time);
        bundle.putString("projectId", projectId);
        bundle.putString("deviceId", deviceId);
        bundle.putString("type", paramType);
        fragment.setBundleData(bundle);
        if (adapter!=null){
            adapter.setPaintTime(time);
        }
    }

    @Override
    public void updatePaint(String deviceIdNew, String paramTypeNew) {
        deviceId=deviceIdNew;
        paramType=paramTypeNew;
    }
}
