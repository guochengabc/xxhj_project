package com.kongtiaoapp.xxhj.bpd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.StatisticEtcAdapter;
import com.kongtiaoapp.xxhj.bean.BTabViewBean;
import com.kongtiaoapp.xxhj.bean.EtcStatisticBean;
import com.kongtiaoapp.xxhj.bean.ChartDataBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.BPaintP;
import com.kongtiaoapp.xxhj.mvp.view.BPaintView;
import com.kongtiaoapp.xxhj.ui.address.ScreenUtils;
import com.kongtiaoapp.xxhj.ui.view.Mf_Tools;
import com.kongtiaoapp.xxhj.utils.DateUtils;
import com.kongtiaoapp.xxhj.utils.MyTablayout;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kongtiaoapp.xxhj.R.id.rela_paint;

/**
 * 变配电头部
 */
public class BPaintActivity extends BaseActivity<BPaintP, BPaintView> implements BPaintView, RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.tv_title)
    TextView tv_title;
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

    @BindView(R.id.lv_etc)
    ListView lv_etc;//峰谷电量值
    private boolean isMonth = false;
    private List<TextView> list = new ArrayList<>();
    private String type = "";
    private int whichPaint = 0;//0  代表的时   1  代表的日
    private String month;
    private String day;
    private int tabPosition;
    private String intentPosition;
    private StatisticEtcAdapter adapter;
    private String projectId;
    private String deviceId;
    private String dateSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_bpaint;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        group_time.setOnCheckedChangeListener(this);
        group_time.check(R.id.radio0);
    }

    private void setTabPaint(List<BTabViewBean.ResobjBean.RunDataBean> runData) {
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
                if (whichPaint == 0) {//时
                    getDataForService(day);

                } else if (whichPaint == 1) {//日
                    getDataForService(month);
                }
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
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            type = intent.getStringExtra("type");
            dateSign = intent.getStringExtra("dateSign");
            intentPosition = intent.getStringExtra("position");
            projectId = intent.getStringExtra("projectId");
            deviceId = intent.getStringExtra("deviceId");
            if (type != null) {
                tv_title.setText(name);
            }
        }

        lv_etc.setFocusable(false);//让listview失去焦点
        month = DateUtils.getYM();
        day = DateUtils.getYMD();
        setPaint();//设置图表
        if (dateSign != null) {
            setPaintHourOrDay(type);
        }


    }

    /**
     * 获取cop值
     */
    private void getDataExplain() {

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

    private void setPaintHourOrDay(String type) {
        radio0.setBackgroundResource(R.mipmap.green_background_circle);
        day = DateUtils.getYMD();
        txt_notata.setText(day);
        // getDataForService(day);
        List listTab = new ArrayList();
        listTab.add(type);
        listTab.add(projectId);
        presenter.onResume(this, listTab);


    }


    private void getDataForService(String date) {
        if (date == null) {
            date = DateUtils.getCurrentDate("yyyy-MM-dd");
        }
        txt_notata.setText(date);
        List<String> list = new ArrayList<>();
        list.add(date);
        list.add(type);
        list.add(projectId);
        list.add(deviceId);
        presenter.getPaint(this, list);
        presenter.getFGDL(this, list);
    }

    @Override
    protected BPaintP getPresenter() {
        return new BPaintP();
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
                    getDataForService(day);
                } else if (whichPaint == 1) {//日
                    month = DateUtils.getLastMonth(month);
                    getDataForService(month);
                }
                break;
            case R.id.img_right:
                if (whichPaint == 0) {//时
                    day = DateUtils.getSpecifiedDayAfter(day);
                    getDataForService(day);
                } else if (whichPaint == 1) {//日
                    month = DateUtils.getNextMonth(month);
                    getDataForService(month);
                }
                break;
        }
    }

    @Override
    public void setText(Object data) {
        Mf_Tools.hintAllView(list);
        rela_loading.removeAllViews();
        txt_notata.setVisibility(View.VISIBLE);
        ChartDataBean lr_bean = (ChartDataBean) data;
        ChartDataBean.ResobjBean resobj = lr_bean.getResobj();
        if (resobj == null) {
            return;
        }
        List<ChartDataBean.ResobjBean.DataBean> listData = resobj.getData();
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
    public void getTabView(Object data) {
        BTabViewBean bean = (BTabViewBean) data;
        setTabPaint(bean.getResobj().getRunData());
        getDataForService(day);
    }

    @Override
    public void list_null() {
        Mf_Tools.hintAllView(list);
        rela_loading.removeAllViews();
        txt_notata.setText(getString(R.string.no_data));
        txt_notata.setVisibility(View.VISIBLE);
    }

    @Override
    public void list_error(Object data) {
        txt_notata.setText(data.toString());
    }

    @Override
    public void getFGDL(Object beans) {

        EtcStatisticBean bean = (EtcStatisticBean) beans;
        EtcStatisticBean.ResobjBean resobj = bean.getResobj();
        List<EtcStatisticBean.ResobjBean.DataBean> data = resobj.getData();
        if (lv_etc.getVisibility() == View.GONE) {
            lv_etc.setVisibility(View.VISIBLE);
        }
        if (adapter == null) {
            adapter = new StatisticEtcAdapter(data, this);
            lv_etc.setAdapter(adapter);
        } else if (adapter != null) {
            adapter.setList(data);
        }

    }

    @Override
    public void getFGDL_null() {
        lv_etc.setVisibility(View.GONE);
    }

    @Override
    public void getFGDL_error(Object data) {
        lv_etc.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        switch (checkedId) {
            case R.id.radio0:
                whichPaint = 0;//代表我点击时
                isMonth = false;
                radio0.setBackgroundResource(R.mipmap.green_background_circle);
                radio1.setBackgroundResource(R.mipmap.white_background_circle);
                getDataForService(day);

                break;
            case R.id.radio1:
                whichPaint = 1;//代表我点击日
                isMonth = true;
                radio0.setBackgroundResource(R.mipmap.white_background_circle);
                radio1.setBackgroundResource(R.mipmap.green_background_circle);
                getDataForService(month);
                break;

        }

    }
}
