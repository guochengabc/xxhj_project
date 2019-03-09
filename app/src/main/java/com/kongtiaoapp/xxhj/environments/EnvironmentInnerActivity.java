package com.kongtiaoapp.xxhj.environments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
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
import com.kongtiaoapp.xxhj.adapter.EnvironmentInnerTopAdapter;
import com.kongtiaoapp.xxhj.bean.EnvironmentCPaintBean;
import com.kongtiaoapp.xxhj.bean.EnvironmentInnerBan;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.EnvironmentInnerPresenter;
import com.kongtiaoapp.xxhj.mvp.view.EnvironmentInnerView;
import com.kongtiaoapp.xxhj.ui.address.ScreenUtils;
import com.kongtiaoapp.xxhj.ui.view.Mf_Tools;
import com.kongtiaoapp.xxhj.ui.view.horizontallistview.ListOutView;
import com.kongtiaoapp.xxhj.utils.DateUtils;
import com.kongtiaoapp.xxhj.utils.MyTablayout;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.kongtiaoapp.xxhj.R.id.rela_paint;

public class EnvironmentInnerActivity extends BaseActivity<EnvironmentInnerPresenter, EnvironmentInnerView> implements EnvironmentInnerView, RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.tv_tem)
    TextView tv_tem;//室外温度
    @BindView(R.id.tv_temContent)
    TextView tv_temContent;//室外温度值
    @BindView(R.id.tv_hum)
    TextView tv_hum;//室外湿度
    @BindView(R.id.tv_humContent)
    TextView tv_humContent;//室外湿度内容
    @BindView(R.id.lv_environment)
    ListOutView lv_environment;
    @BindView(R.id.tab_paint)
    MyTablayout tab_paint;//滑动项
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
    private String type = "";
    private int whichPaint = 0;//0  代表的时   1  代表的日
    private String month;
    private String day;
    private int tabPosition = 0;//tab滑动的位置
    private String intentPosition = "0";
    private int whichLeader = 1;
    private boolean isFirstPaint = true;
    private EnvironmentInnerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initContentView() {
        return whichLeader > 2 ? R.layout.activity_environmentinner2 : R.layout.activity_environmentinner;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        group_time.setOnCheckedChangeListener(this);
        group_time.check(R.id.radio0);
        month = DateUtils.getYM();
        day = DateUtils.getYMD();
        setPaint();//设置图表
    }

    @Override
    protected void initData() {
        //   getEnvironmentInnerInfo("");
        presenter.getEnvironmentInfo(this);
    }

    @Override
    protected EnvironmentInnerPresenter getPresenter() {
        return new EnvironmentInnerPresenter();
    }

    private void setTabPaint(List<EnvironmentInnerBan.ResobjBean.ChartArrayBean> runData) {
        tab_paint.setTabMode(TabLayout.MODE_FIXED);
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
                setTabDatePaint(runData.get(tabPosition).getDateType());//设置图表  更新日期
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

        EnvironmentInnerBan bean = (EnvironmentInnerBan) data;
        EnvironmentInnerBan.ResobjBean resobj = bean.getResobj();
        EnvironmentInnerTopAdapter adapter = new EnvironmentInnerTopAdapter(this, resobj.getGroupData());
        lv_environment.setAdapter(adapter);
        List<EnvironmentInnerBan.ResobjBean.ChartArrayBean> chartArray = resobj.getChartArray();
        if (chartArray != null && !chartArray.isEmpty()) {
            type = chartArray.get(0).getType();
            isFirstPaint = false;//防止图表请求多次   radioGroup先进行请求
            setTabDatePaint(chartArray.get(0).getDateType());//设置图表  更新日期
            setTabPaint(resobj.getChartArray());
        }
        tv_temContent.setText(resobj.getOuterTem() + "");
        tv_humContent.setText(resobj.getOutHum() + "");

        if (resobj.getLeader() == 3) {//操作员
        } else {
            setLeftDrawerLayout(resobj.getJobCate());//获取环控左侧抽屉布局
        }

    }

    /**
     * 抽屉布局左侧界面  各鸡场和鸡舍界面
     */
    private void setLeftDrawerLayout(List<EnvironmentInnerBan.ResobjBean.JobCateBean> list) {
        if (adapter==null){
            adapter = new EnvironmentInnerAdapter(list, this);
            melv_Job.setAdapter(adapter);
            melv_Job.setGroupIndicator(null);
            for (int i = 0; i < adapter.getGroupCount(); i++) {
                melv_Job.expandGroup(i, true);
            }
        }

    }

    private void setTabDatePaint(int dateType) {

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

        EnvironmentCPaintBean bean = (EnvironmentCPaintBean) data;
        EnvironmentCPaintBean.ResobjBean resobj = bean.getResobj();
        Mf_Tools.hintAllView(list);
        rela_loading.removeAllViews();
        txt_notata.setVisibility(View.VISIBLE);
        txt_notata.setText(whichPaint == 0 ? day : month);
        if (resobj == null) {

            return;
        }
        List<EnvironmentCPaintBean.ResobjBean.DataBean> listData = resobj.getData();
        if (listData == null) {
            return;
        }

        List<double[]> listX = new ArrayList<>();
        List<double[]> listY = new ArrayList<>();
        String[] titles = new String[listData.size()];
        for (int i = 0; i < listData.size(); i++) {
            titles[i] = listData.get(i).getText();
            listX.add(resobj.getTime());
            listY.add(listData.get(i).getValue());
        }
        setGraph(titles);//设置图列的个数
        try {
            if (resobj.getFlag().equals("Z")) {

                if (whichPaint == 0) {
                    Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), this, rela_loading, false, resobj.getNowTime());
                } else {
                    Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), this, rela_loading, true, resobj.getNowTime());
                }
            } else if (resobj.getFlag().equals("S")) {
                if (whichPaint == 0) {
                    Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), this, rela_loading, false, "month", resobj.getNowTime());
                } else {
                    Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), this, rela_loading, true, "month", resobj.getNowTime());
                }

            } else {
                if (whichPaint == 0) {
                    Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), this, rela_loading, false, resobj.getNowTime());
                } else {
                    Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), this, rela_loading, true, resobj.getNowTime());
                }
            }
        } catch (Exception e) {
            ToastUtils.showToast(this, "图表数据有异常,请您稍后再尝试!");
            return;
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
    }

    private void setGraph(String[] titles) {
        Mf_Tools.setGraph(list, titles);
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
}
