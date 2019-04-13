package com.kongtiaoapp.xxhj.environments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.EnvironmentInnerAdapter;
import com.kongtiaoapp.xxhj.adapter.EnvironmentInnerListAdapter;
import com.kongtiaoapp.xxhj.adapter.EnvironmentInnerTopAdapter;
import com.kongtiaoapp.xxhj.adapter.EnvironmentStatisticAdapter;
import com.kongtiaoapp.xxhj.afinal.UserManegerList;
import com.kongtiaoapp.xxhj.bean.EnvironmentCPaintBean;
import com.kongtiaoapp.xxhj.bean.EnvironmentInnerBan;
import com.kongtiaoapp.xxhj.interfaces.EnvironmentHenHouse;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.EnvironmentInnerPresenter;
import com.kongtiaoapp.xxhj.mvp.view.EnvironmentInnerView;
import com.kongtiaoapp.xxhj.ui.address.ScreenUtils;
import com.kongtiaoapp.xxhj.ui.view.Mf_Tools;
import com.kongtiaoapp.xxhj.ui.view.NoScrollGridView;
import com.kongtiaoapp.xxhj.ui.view.horizontallistview.ListOutView;
import com.kongtiaoapp.xxhj.utils.DateUtils;
import com.kongtiaoapp.xxhj.utils.MyTablayout;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.kongtiaoapp.xxhj.R.id.rela_paint;

public class EnvironmentInnerActivity extends BaseActivity<EnvironmentInnerPresenter, EnvironmentInnerView> implements EnvironmentInnerView,
        RadioGroup.OnCheckedChangeListener, EnvironmentHenHouse, AdapterView.OnItemClickListener {
    @BindView(R.id.line_environment)
    LinearLayout line_environment;
    @BindView(R.id.glv_statistic)
    NoScrollGridView glv_statistic;//环控统计
    @BindView(R.id.glv_henHouse)
    NoScrollGridView glv_henHouse;//所有鸡舍
    @BindView(R.id.line_backJiShe)
    LinearLayout line_backJiShe;//鸡舍背景图
    @BindView(R.id.lv_environment)
    ListOutView lv_environment;
    @BindView(R.id.tab_paint)
    MyTablayout tab_paint;//滑动项
    @BindView(R.id.tv_title)
    TextView tv_title;
    //第一张图表
    @BindView(R.id.line_onePaint)
    LinearLayout line_onePaint;//第一张图表显示和隐藏
    @BindView(R.id.frame_up)
    FrameLayout frame_up;
    @BindView(rela_paint)
    RelativeLayout rela_loading;
    @BindView(R.id.graph1)
    TextView graph1;
    @BindView(R.id.graph2)
    TextView graph2;
    @BindView(R.id.graph3)
    TextView graph3;
    @BindView(R.id.graph4)
    TextView graph4;
    @BindView(R.id.graph5)
    TextView graph5;
    @BindView(R.id.graph6)
    TextView graph6;
    @BindView(R.id.graph7)
    TextView graph7;
    @BindView(R.id.graph8)
    TextView graph8;
    @BindView(R.id.graph9)
    TextView graph9;
    @BindView(R.id.txt_notata)
    TextView txt_notata;
    //第二张图表
    @BindView(R.id.line_twoPaint)
    LinearLayout line_twoPaint;//第二张图表显示隐藏
    @BindView(R.id.frame_down)
    FrameLayout frame_down;
    @BindView(R.id.rela_paint1)
    RelativeLayout rela_loading1;
    @BindView(R.id.graph11)//graph1-9代表的是图例
            TextView graph11;
    @BindView(R.id.graph21)
    TextView graph21;
    @BindView(R.id.graph31)
    TextView graph31;
    @BindView(R.id.graph41)
    TextView graph41;
    @BindView(R.id.graph51)
    TextView graph51;
    @BindView(R.id.graph61)
    TextView graph61;
    @BindView(R.id.graph71)
    TextView graph71;
    @BindView(R.id.graph81)
    TextView graph81;
    @BindView(R.id.graph91)
    TextView graph91;
    @BindView(R.id.txt_notata1)
    TextView txt_notata1;

    @BindView(R.id.img_left)
    ImageView img_left;
    @BindView(R.id.img_right)
    ImageView img_right;
    @BindView(R.id.group_time)
    RadioGroup group_time;
    @BindView(R.id.radio0)
    RadioButton radio0;//时
    @BindView(R.id.radio1)
    RadioButton radio1;//日
    @BindView(R.id.dl_hk)
    DrawerLayout dl_hk;//抽屉布局
    @BindView(R.id.melv_Job)
    ExpandableListView melv_Job;//左侧菜单栏
    @BindView(R.id.line_left)
    LinearLayout line_left;//左侧抽屉
    private boolean isMonth = false;
    private List<TextView> list = new ArrayList<>();
    private List<TextView> list1 = new ArrayList<>();
    private boolean isFirstTab = true;
    private String type = "";
    private int whichPaint = 0;//0  代表的时   1  代表的日
    private String month;
    private String day;
    private int tabPosition = 0;//tab滑动的位置
    private String intentPosition = "0";
    private int whichLeader = 1;
    private boolean isFirstPaint = true;
    private EnvironmentInnerAdapter adapter;
    private String deviceId = "";
    private List<EnvironmentInnerBan.ResobjBean.JobCateBean> jobCate;
    private EnvironmentStatisticAdapter adapterStatistic;
    private EnvironmentInnerTopAdapter adapterTop;
    private EnvironmentInnerListAdapter adapterHenHouse;
    private int dateType;
    private List<EnvironmentInnerBan.ResobjBean.JobCateBean.HenHouseArrayBean> listHenHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initContentView() {
        return UserManegerList.OPERATEFACTORY() ? R.layout.activity_environmentinner2 : R.layout.activity_environmentinner;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        group_time.setOnCheckedChangeListener(this);
        glv_henHouse.setOnItemClickListener(this);
        group_time.check(R.id.radio0);
        month = DateUtils.getYM();
        day = DateUtils.getYMD();
        setPaint();//设置图表
    }

    @Override
    protected void initData() {
        //getEnvironmentInnerInfo("");
        if (ScreenUtils.isScreenOriatationPortrait(this)) {
            Mf_Tools.setLayoutHeight(this, frame_up, rela_loading, 1);//根据横竖屏切换控制视图展示   竖屏
            Mf_Tools.setLayoutHeight(this, frame_down, rela_loading1, 1);//根据横竖屏切换控制视图展示
        } else {
            Mf_Tools.setLayoutHeight(this, frame_up, rela_loading, 2);//根据横竖屏切换控制视图展示  横屏
            Mf_Tools.setLayoutHeight(this, frame_down, rela_loading1, 2);//根据横竖屏切换控制视图展示
        }
        getMainData();//获取首页信息
    }

    private void getMainData() {
        presenter.getEnvironmentInfo(this, deviceId);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Mf_Tools.setLayoutHeight(this, frame_up, rela_loading, newConfig.orientation);//根据横竖屏切换控制视图展示
        Mf_Tools.setLayoutHeight(this, frame_down, rela_loading1, newConfig.orientation);//根据横竖屏切换控制视图展示
    }

    @Override
    protected EnvironmentInnerPresenter getPresenter() {
        return new EnvironmentInnerPresenter();
    }

    private void setTabPaint(List<EnvironmentInnerBan.ResobjBean.ChartArrayBean> runData) {
        tab_paint.setTabMode(TabLayout.DRAWING_CACHE_QUALITY_AUTO);
        final String title[] = new String[runData.size()];
        int size = runData.size();
        if (size == 1) {
            tab_paint.setVisibility(View.GONE);
        }
        for (int i = 0; i < size; i++) {
            title[i] = runData.get(i).getName();
            tab_paint.addTab(tab_paint.newTab().setText(title[i]));
        }
        tab_paint.getTabAt(Integer.parseInt(intentPosition)).select();
        tabPosition = Integer.parseInt(intentPosition);
        tab_paint.setSelectedTabIndicatorColor(getResources().getColor(R.color.theme_color));
        setTabColor(title, tab_paint);//设置初始值的textview的状态
        tab_paint.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView textView = ((TextView) tab.getCustomView().findViewById(R.id.title_tv));
                textView.setTextColor(getResources().getColor(R.color.theme_color));
                textView.setSelected(true);
                tabPosition = tab.getPosition();
                type = runData.get(tabPosition).getType();
                setTabDatePaint(runData.get(tabPosition).getDateType(), true);//设置图表  更新日期
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
    }

    /**
     * 获取图表数据
     */
    private void getPaintData(String date) {
        List<String> list = new ArrayList<>();
        list.add(date);
        list.add(type);
        list.add(deviceId);
        presenter.getEnvironmentPaint(this, list);
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

    @Override
    public void getEnvironmentInnerInfo(Object data) {
      /*  String s = AssetsUtils.readText(this, "environmentinner.json");
        EnvironmentInnerBan bean = JSON.parseObject(s, EnvironmentInnerBan.class);*/
        line_environment.invalidate();
        EnvironmentInnerBan bean = (EnvironmentInnerBan) data;
        EnvironmentInnerBan.ResobjBean resobj = bean.getResobj();
        List<EnvironmentInnerBan.ResobjBean.StatisticsNumberBean> stasticArray = resobj.getStatisticsNumber();
        if (adapterStatistic == null) {
            adapterStatistic = new EnvironmentStatisticAdapter(stasticArray, this);
            glv_statistic.setAdapter(adapterStatistic);
        } else {
            adapterStatistic.setList(stasticArray);
        }
        if (adapterTop == null) {
            adapterTop = new EnvironmentInnerTopAdapter(this, resobj.getGroupData());
            lv_environment.setAdapter(adapterTop);
        } else {
            adapterTop.setList(resobj.getGroupData());
        }

        List<EnvironmentInnerBan.ResobjBean.ChartArrayBean> chartArray = resobj.getChartArray();
        if (chartArray != null && !chartArray.isEmpty()) {
            if (isFirstTab) {
                jobCate = resobj.getJobCate();//获取抽屉集合
                if (!jobCate.isEmpty() && jobCate != null) {
                    if (resobj.getLeader() == 3) {//操作员
                    } else {
                        setLeftDrawerLayout(jobCate);//获取环控左侧抽屉布局
                    }

                }
                type = chartArray.get(0).getType();
                isFirstPaint = false;//防止图表请求多次   radioGroup先进行请求
                setTabDatePaint(chartArray.get(0).getDateType(), true);//设置图表  更新日期
                isFirstTab = !isFirstTab;
                setTabPaint(resobj.getChartArray());
            } else if (isFirstTab == false) {
                setTabDatePaint(2, false);//设置图表  更新日期
            }
        }


    }

    /**
     * 抽屉布局左侧界面  各鸡场和鸡舍界面
     */
    private void setLeftDrawerLayout(List<EnvironmentInnerBan.ResobjBean.JobCateBean> list) {
        if (adapter == null) {
            adapter = new EnvironmentInnerAdapter(list, this);
            melv_Job.setAdapter(adapter);
            melv_Job.setGroupIndicator(null);
            listHenHouse = list.get(0).getHenHouseArray();
            if (listHenHouse == null || listHenHouse.isEmpty()) {
                return;
            } else if (listHenHouse.size() == 1) {
                glv_henHouse.setVisibility(View.GONE);
                line_backJiShe.setVisibility(View.GONE);
            }
            deviceId = listHenHouse.get(2).getId();
            tv_title.setText(listHenHouse.get(2).getName());
            adapterHenHouse = new EnvironmentInnerListAdapter(listHenHouse, this,2);
            glv_henHouse.setAdapter(adapterHenHouse);
            for (int i = 0; i < adapter.getGroupCount(); i++) {
                melv_Job.expandGroup(i, true);
            }
        }

    }

    private void setTabDatePaint(int dateTypes, boolean assignment) {
        if (assignment) {
            this.dateType = dateTypes;
        }

        switch (dateType) {
            case 1://显示日
                img_left.setImageResource(R.mipmap.leftarrow);
                img_right.setImageResource(R.mipmap.rightarrow);
                whichPaint = 0;
                radio0.setBackgroundResource(R.mipmap.green_background_circle);
                getPaintData(day);
                radio0.setVisibility(View.VISIBLE);
                radio1.setVisibility(View.GONE);
                break;
            case 2://显示月
                img_left.setImageResource(R.mipmap.arrow_double_left);
                img_right.setImageResource(R.mipmap.arrow_double_right);
                whichPaint = 1;
                radio1.setBackgroundResource(R.mipmap.green_background_circle);
                getPaintData(month);
                radio0.setVisibility(View.GONE);
                radio1.setVisibility(View.VISIBLE);
                break;
            case 3://显示日月
                if (isMonth == false) {
                    img_left.setImageResource(R.mipmap.leftarrow);
                    img_right.setImageResource(R.mipmap.rightarrow);
                    whichPaint = 0;
                    radio0.setBackgroundResource(R.mipmap.green_background_circle);
                    radio1.setBackgroundResource(R.mipmap.white_background_circle);
                    getPaintData(day);
                } else {
                    img_left.setImageResource(R.mipmap.arrow_double_left);
                    img_right.setImageResource(R.mipmap.arrow_double_right);
                    whichPaint = 1;
                    radio0.setBackgroundResource(R.mipmap.white_background_circle);
                    radio1.setBackgroundResource(R.mipmap.green_background_circle);
                    getPaintData(month);
                }
                radio0.setVisibility(View.VISIBLE);
                radio1.setVisibility(View.VISIBLE);
                break;
            default:
                if (isMonth == false) {
                    whichPaint = 0;
                    radio0.setBackgroundResource(R.mipmap.green_background_circle);
                    radio1.setBackgroundResource(R.mipmap.white_background_circle);
                    getPaintData(day);
                } else {
                    whichPaint = 1;
                    radio0.setBackgroundResource(R.mipmap.white_background_circle);
                    radio1.setBackgroundResource(R.mipmap.green_background_circle);
                    getPaintData(month);
                }
                radio0.setVisibility(View.VISIBLE);
                radio1.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    public void getEnvironmentInnerPaint(Object data) {
        rela_loading.removeAllViews();
        rela_loading1.removeAllViews();
        EnvironmentCPaintBean bean = (EnvironmentCPaintBean) data;
        EnvironmentCPaintBean.ResobjBean resobj = bean.getResobj();
        Mf_Tools.hintAllView(list);
        Mf_Tools.hintAllView(list1);
        txt_notata.setVisibility(View.VISIBLE);
        txt_notata.setText(whichPaint == 0 ? day : month);
        line_onePaint.setVisibility(View.GONE);
        line_twoPaint.setVisibility(View.GONE);
        if (resobj == null) {
            return;
        }
        //第一张图表
        List<EnvironmentCPaintBean.ResobjBean.DataBean> listChat = resobj.getData();
        int chartSize = listChat.size();//图表个数

        line_onePaint.setVisibility(View.VISIBLE);
        EnvironmentCPaintBean.ResobjBean.DataBean dataBean = listChat.get(0);
        List<EnvironmentCPaintBean.ResobjBean.DataBean.CharDataBean> listData = dataBean.getCharData();
        if (listData == null) {
            return;
        }
        try {
            List<double[]> listX = new ArrayList<>();
            List<double[]> listY = new ArrayList<>();
            String[] titles = new String[listData.size()];
            for (int i = 0; i < listData.size(); i++) {
                titles[i] = listData.get(i).getText();
                listX.add(resobj.getTime());
                listY.add(listData.get(i).getValue());
            }

            if (resobj.getFlag().equals("Z")) {
                if (whichPaint == 0) {
                    Mf_Tools.setData(titles, listY, listX, dataBean.getMaxX(), dataBean.getMaxY(), dataBean.getMinY(), this, rela_loading, false, resobj.getNowTime());
                } else {
                    Mf_Tools.setData(titles, listY, listX, dataBean.getMaxX(), dataBean.getMaxY(), dataBean.getMinY(), this, rela_loading, true, resobj.getNowTime());
                }
            } else if (resobj.getFlag().equals("S")) {
                if (whichPaint == 0) {
                    Mf_Tools.setData(titles, listY, listX, dataBean.getMaxX(), dataBean.getMaxY(), dataBean.getMinY(), this, rela_loading, false, "month", resobj.getNowTime());
                } else {
                    Mf_Tools.setData(titles, listY, listX, dataBean.getMaxX(), dataBean.getMaxY(), dataBean.getMinY(), this, rela_loading, true, "month", resobj.getNowTime());
                }
            } else {
                if (whichPaint == 0) {
                    Mf_Tools.setData(titles, listY, listX, dataBean.getMaxX(), dataBean.getMaxY(), dataBean.getMinY(), this, rela_loading, false, resobj.getNowTime());
                } else {
                    Mf_Tools.setData(titles, listY, listX, dataBean.getMaxX(), dataBean.getMaxY(), dataBean.getMinY(), this, rela_loading, true, resobj.getNowTime());
                }
            }
            setGraph(titles);//设置图列的个数
        } catch (Exception e) {
            ToastUtils.showToast(this, "图表数据有异常,请您稍后再尝试!");
            return;
        }

        //第二张图表
        if (chartSize == 2) {
            line_twoPaint.setVisibility(View.VISIBLE);
            EnvironmentCPaintBean.ResobjBean.DataBean dataBean1 = listChat.get(1);
            List<EnvironmentCPaintBean.ResobjBean.DataBean.CharDataBean> listData1 = dataBean1.getCharData();
            if (listData1 == null) {
                return;
            }
             try {
            List<double[]> listX1 = new ArrayList<>();
            List<double[]> listY1 = new ArrayList<>();
            String[] titles1 = new String[listData1.size()];
            for (int i = 0; i < listData1.size(); i++) {
                titles1[i] = listData1.get(i).getText();
                listX1.add(resobj.getTime());
                listY1.add(listData1.get(i).getValue());
            }
            if (resobj.getFlag().equals("Z")) {
                if (whichPaint == 0) {
                    Mf_Tools.setData(titles1, listY1, listX1, dataBean1.getMaxX(), dataBean1.getMaxY(), dataBean1.getMinY(), this, rela_loading1, false, resobj.getNowTime());
                } else {
                    Mf_Tools.setData(titles1, listY1, listX1, dataBean1.getMaxX(), dataBean1.getMaxY(), dataBean1.getMinY(), this, rela_loading1, true, resobj.getNowTime());
                }
            } else if (resobj.getFlag().equals("S")) {
                if (whichPaint == 0) {
                    Mf_Tools.setData(titles1, listY1, listX1, dataBean1.getMaxX(), dataBean1.getMaxY(), dataBean1.getMinY(), this, rela_loading1, false, "month", resobj.getNowTime());
                } else {
                    Mf_Tools.setData(titles1, listY1, listX1, dataBean1.getMaxX(), dataBean1.getMaxY(), dataBean1.getMinY(), this, rela_loading1, true, "month", resobj.getNowTime());
                }
            } else {
                if (whichPaint == 0) {
                    Mf_Tools.setData(titles1, listY1, listX1, dataBean1.getMaxX(), dataBean1.getMaxY(), dataBean1.getMinY(), this, rela_loading1, false, resobj.getNowTime());
                } else {
                    Mf_Tools.setData(titles1, listY1, listX1, dataBean1.getMaxX(), dataBean1.getMaxY(), dataBean1.getMinY(), this, rela_loading1, true, resobj.getNowTime());
                }
            }
            setGraph1(titles1);//设置图列的个数
            } catch (Exception e) {
                ToastUtils.showToast(this, "图表数据有异常,请您稍后再尝试!");
                return;
            }
        }
    }

    @Override
    public void paintError(Object data) {

    }

    private void setPaint() {
        if (ScreenUtils.isScreenOriatationPortrait(this)) {
            Mf_Tools.setLayoutHeight(this, frame_up, rela_loading, 1);//根据横竖屏切换控制视图展示   竖屏
        } else {
            Mf_Tools.setLayoutHeight(this, frame_up, rela_loading, 2);//根据横竖屏切换控制视图展示  横屏
        }
        list.add(graph1);
        list.add(graph2);
        list.add(graph3);
        list.add(graph4);
        list.add(graph5);
        list.add(graph6);
        list.add(graph7);
        list.add(graph8);
        list.add(graph9);
        list1.add(graph11);
        list1.add(graph21);
        list1.add(graph31);
        list1.add(graph41);
        list1.add(graph51);
        list1.add(graph61);
        list1.add(graph71);
        list1.add(graph81);
        list1.add(graph91);
    }

    private void setGraph(String[] titles) {
        Mf_Tools.setGraph(list, titles);
    }

    private void setGraph1(String[] titles) {
        Mf_Tools.setGraph(list1, titles);
    }

    @OnClick({R.id.iv_back, R.id.img_left, R.id.img_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
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
     * 得到该厂所有环控鸡舍
     */
    @Override
    public void getHenHouse(Object data, int position) {
        listHenHouse = (List<EnvironmentInnerBan.ResobjBean.JobCateBean.HenHouseArrayBean>) data;
        if (adapterHenHouse == null) {
            adapterHenHouse = new EnvironmentInnerListAdapter(listHenHouse, this, position);
            glv_henHouse.setAdapter(adapterHenHouse);
        } else {
            adapterHenHouse.setList(listHenHouse, position);
        }
        if (listHenHouse == null || listHenHouse.isEmpty() || listHenHouse.size() == 1) {
            glv_henHouse.setVisibility(View.GONE);
            line_backJiShe.setVisibility(View.GONE);
        } else {
            glv_henHouse.setVisibility(View.VISIBLE);
            line_backJiShe.setVisibility(View.VISIBLE);
        }
        EnvironmentInnerBan.ResobjBean.JobCateBean.HenHouseArrayBean henHouseArrayBean = listHenHouse.get(position);
        deviceId = henHouseArrayBean.getId();
        tv_title.setText(henHouseArrayBean.getName());
        dl_hk.closeDrawers();
        getMainData();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (listHenHouse == null || listHenHouse.isEmpty()) {
            return;
        }
        //点击环控鸡舍首页列表出现数据
        EnvironmentInnerBan.ResobjBean.JobCateBean.HenHouseArrayBean henHouseArrayBean = listHenHouse.get(position);
        adapterHenHouse.setNotifyUI(position, true);
        deviceId = henHouseArrayBean.getId();
        tv_title.setText(henHouseArrayBean.getName());
        dl_hk.closeDrawers();
        getMainData();
    }
}
