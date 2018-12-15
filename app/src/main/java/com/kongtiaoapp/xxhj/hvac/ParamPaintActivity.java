package com.kongtiaoapp.xxhj.hvac;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.HVACPieChartBean;
import com.kongtiaoapp.xxhj.bean.HVAC_NewProjectDetailBean;
import com.kongtiaoapp.xxhj.bean.Loading_RefrigeratorBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.ParamPaintP;
import com.kongtiaoapp.xxhj.mvp.view.ParamPaintV;
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
 * 这个是从暖通详情的上面头部点击进入该界面的   2018-5-25
 */

public class ParamPaintActivity extends BaseActivity<ParamPaintP, ParamPaintV> implements ParamPaintV, RadioGroup.OnCheckedChangeListener {
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

    @BindView(R.id.frame_pie)
    FrameLayout frame_pie;//饼状图
    @BindView(R.id.txt_notataPie)
    TextView txt_notataPie;
    private boolean isMonth = false;
    private List<TextView> list = new ArrayList<>();
    private String type = "";
    private int whichPaint = 0;//0  代表的时   1  代表的日
    private String month;
    private String day;
    private String whichType = "";//判断是否有
    private String displayType = "";
    private int tabPosition;
    private String pieType="";
    private boolean isFirstEnergy=true;
    private int pieVisivle=0;//0代表饼状图不显示  1显示

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_param_paint;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        group_time.setOnCheckedChangeListener(this);
        group_time.check(R.id.radio0);

    }

    private void setTabPaint(List<HVACPieChartBean.ResobjBean.RunDataBean> runData) {
        tab_paint.setTabMode(TabLayout.MODE_FIXED);
        final String title[] = new String[runData.size()];
        for (int i = 0; i < runData.size(); i++) {
            title[i] = runData.get(i).getName();
            tab_paint.addTab(tab_paint.newTab().setText(title[i]));
        }
        tab_paint.setSelectedTabIndicatorColor(getResources().getColor(R.color.theme_color));
        setTabColor(title, tab_paint);//设置初始值的textview的状态
        tab_paint.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView textView = ((TextView) tab.getCustomView().findViewById(R.id.title_tv));
                textView.setTextColor(getResources().getColor(R.color.theme_color));
                textView.setSelected(true);
                tabPosition = tab.getPosition();
                if (pieVisivle==1){
                    if (tabPosition==0){
                        frame_pie.setVisibility(View.VISIBLE);
                    }else{
                        frame_pie.setVisibility(View.GONE);
                    }
                }
                type = runData.get(tabPosition).getType();
                if (whichPaint == 0) {//时
                    getDataForService(day);
                    if (tabPosition==0){
                        setPieChartSetting(day);
                    }
                } else if (whichPaint == 1) {//日
                    getDataForService(month);
                    if (tabPosition==0){
                        setPieChartSetting(month);
                    }
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
        month = DateUtils.getYM();
        day = DateUtils.getYMD();
        setPaint();//设置图表
        Intent intent = getIntent();
        if (intent != null) {
            String which = intent.getStringExtra("which");
            if (which != null) {
                switch (which) {
                    case "1":
                        HVAC_NewProjectDetailBean.ResobjBean.EnergyParaInfoBean beanOne = (HVAC_NewProjectDetailBean.ResobjBean.EnergyParaInfoBean) intent.getSerializableExtra("paintOne");
                        if (beanOne.getType().equals("SECU")){
                            pieVisivle = 1;
                        }
                        type = beanOne.getType();
                        pieType = type;
                        displayType = beanOne.getDisplayType();
                        setPaintHourOrDay(beanOne.getDateSign());
                        tv_title.setText(beanOne.getName());//设置图表
                        break;
                    case "2":
                        HVAC_NewProjectDetailBean.ResobjBean.EfficParaInfoBean beanTwo = (HVAC_NewProjectDetailBean.ResobjBean.EfficParaInfoBean) intent.getSerializableExtra("paintTwo");
                        type = beanTwo.getType();
                        pieType = type;
                        displayType = beanTwo.getDisplayType();
                        setPaintHourOrDay(beanTwo.getDateSign());
                        tv_title.setText(beanTwo.getName());//设置图表
                        break;
                    case "3":
                        HVAC_NewProjectDetailBean.ResobjBean.SysParamInfoBean beanThree = (HVAC_NewProjectDetailBean.ResobjBean.SysParamInfoBean) intent.getSerializableExtra("paintThree");
                        type = beanThree.getType();
                        pieType = type;
                        displayType = beanThree.getDisplayType();
                        setPaintHourOrDay(beanThree.getDateSign());
                        tv_title.setText(beanThree.getName());//设置图表
                        break;
                    default:

                        break;
                }

            }

        }
    }

    /**
     * 获取cop值
     */
    private void getDataExplain() {

    }

    /**
     * 获取饼状图
     */
    private void getDataPieChart(String date) {
        List<String> list = new ArrayList<>();
        list.add(pieType);
        list.add(date);
        presenter.onPieChart(this, list);
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

    private void setPaintHourOrDay(String dateTpe) {
        String date = "";
        switch (dateTpe) {
            case "A":
                whichPaint = 0;
                date = DateUtils.getYMD();
                day = date;
                radio1.setVisibility(View.GONE);
                radio0.setBackgroundResource(R.mipmap.green_background_circle);
                txt_notata.setText(date);
                break;
            case "B":
                whichPaint = 1;
                date = DateUtils.getYM();
                month = date;
                radio0.setVisibility(View.GONE);
                isMonth = true;
                radio1.setBackgroundResource(R.mipmap.green_background_circle);
                txt_notata.setText(date);
                break;
            case "C":
                whichPaint = 0;
                radio0.setBackgroundResource(R.mipmap.green_background_circle);
                date = DateUtils.getYMD();
                day = date;
                txt_notata.setText(date);
                break;
            default:
                whichPaint = 0;
                radio0.setBackgroundResource(R.mipmap.green_background_circle);
                date = DateUtils.getYMD();
                day = date;
                txt_notata.setText(date);
                break;
        }
        getDataForService(date);
       setPieChartSetting(date);


    }

    private void setPieChartSetting(String date) {
        switch (displayType) {
            case "P"://请求饼状图  能耗图
                getDataPieChart(date);//获得图例
                break;
            case "T"://请求文本  SCOP和COP
                getDataExplain();//获得scop和cop解释
                break;
            case "O"://请求其他  暂不做处理  这个暂时还没有什么需求

                break;
            default:

                break;
        }
    }

    private void getDataForService(String date) {
        txt_notata.setText(date);
        List<String> list = new ArrayList<>();
        list.add(type);
        list.add(date);
        presenter.onResume(this, list);
    }

    @Override
    protected ParamPaintP getPresenter() {
        return new ParamPaintP();
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
                    setPieChartSetting(day);
                } else if (whichPaint == 1) {//日
                    month = DateUtils.getLastMonth(month);
                    getDataForService(month);
                    setPieChartSetting(month);
                }
                break;
            case R.id.img_right:
                if (whichPaint == 0) {//时
                    day = DateUtils.getSpecifiedDayAfter(day);
                    getDataForService(day);
                    setPieChartSetting(day);
                } else if (whichPaint == 1) {//日
                    month = DateUtils.getNextMonth(month);
                    getDataForService(month);
                    setPieChartSetting(month);
                }
                break;
        }
    }

    @Override
    public void setText(Object data) {
        Mf_Tools.hintAllView(list);
        rela_loading.removeAllViews();
        txt_notata.setVisibility(View.VISIBLE);
        Loading_RefrigeratorBean lr_bean = (Loading_RefrigeratorBean) data;
        Loading_RefrigeratorBean.ResobjBean resobj = lr_bean.getResobj();
        List<Loading_RefrigeratorBean.ResobjBean.DataBean> listData = resobj.getData();
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
            if (resobj.getFlag().equals("Z") ) {
                Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), this, rela_loading, true, resobj.getNowTime());

            } else if (resobj.getFlag().equals("S")) {
                Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), this, rela_loading, false, "month", resobj.getNowTime());
            } else {
                Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), this, rela_loading, false, resobj.getNowTime());

            }
        } catch (Exception e) {
            ToastUtils.showToast(this, "图表数据有异常,请您稍后再尝试!");
            return;
        }
    }

    @Override
    public void getPieChart(Object data) {
        HVACPieChartBean bean= (HVACPieChartBean) data;
        HVACPieChartBean.ResobjBean resobj = bean.getResobj();
        HVACPieChartBean.ResobjBean.DataSourcesBean dataSources = resobj.getDataSources();
        List<HVACPieChartBean.ResobjBean.DataSourcesBean.PieDataBean> pieData = dataSources.getPieData();
        int size = pieData.size();
        String[] name=new String[size];
        double[] values=new double[size];
        for (int i = 0; i < size; i++) {
            name[i]=pieData.get(i).getName();
            try {
                values[i]=Double.parseDouble(pieData.get(i).getValue());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                ToastUtils.showToast(this,"返回能耗值有问题！");
            }
        }
        txt_notataPie.setVisibility(View.GONE);
        frame_pie.setVisibility(View.VISIBLE);
        Mf_Tools.setDataPieChart(this,"饼状图==",values,name,frame_pie);

        List<HVACPieChartBean.ResobjBean.RunDataBean> runData = resobj.getRunData();
        if (runData != null&& !runData.isEmpty()) {
            if (isFirstEnergy){
                isFirstEnergy=!isFirstEnergy;
                tab_paint.setVisibility(View.VISIBLE);
                setTabPaint(runData);
            }

        }
    }

    @Override
    public void gtPieChartNull() {
        frame_pie.setVisibility(View.GONE);
        txt_notataPie.setVisibility(View.VISIBLE);
    }

    @Override
    public void getExplan(Object data) {

    }

    @Override
    public void list_null() {
        Mf_Tools.hintAllView(list);
        rela_loading.removeAllViews();
        txt_notata.setText(getString(R.string.no_data));
        txt_notata.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if (!type.equals("")) {
            switch (checkedId) {
                case R.id.radio0:
                    whichPaint = 0;//代表我点击时
                    isMonth = false;
                    radio0.setBackgroundResource(R.mipmap.green_background_circle);
                    radio1.setBackgroundResource(R.mipmap.white_background_circle);
                    getDataForService(day);
                    setPieChartSetting(day);
                    break;
                case R.id.radio1:
                    whichPaint = 1;//代表我点击日
                    isMonth = true;
                    radio0.setBackgroundResource(R.mipmap.white_background_circle);
                    radio1.setBackgroundResource(R.mipmap.green_background_circle);
                    getDataForService(month);
                    setPieChartSetting(month);
                    break;
            }
        }

    }
}
