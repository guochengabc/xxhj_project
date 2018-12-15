package com.kongtiaoapp.xxhj.energymetering.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.EnergyMeterBean;
import com.kongtiaoapp.xxhj.bean.EventBusDownBean;
import com.kongtiaoapp.xxhj.bean.EventbusBean;
import com.kongtiaoapp.xxhj.energymetering.adapter.EnergyTopAdapter;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.HeatMeteringPresenter;
import com.kongtiaoapp.xxhj.mvp.view.HeatMeteringView;
import com.kongtiaoapp.xxhj.ui.view.ClickTextView;
import com.kongtiaoapp.xxhj.ui.view.FullGridView;
import com.kongtiaoapp.xxhj.ui.view.horizontallistview.ListOutView;
import com.kongtiaoapp.xxhj.utils.MyTablayout;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 热源计量
 */
public class EnergytMeteringActivity extends BaseActivity<HeatMeteringPresenter, HeatMeteringView> implements HeatMeteringView {
    @BindView(R.id.lv_energyP)
    ListOutView lv_energyP;//能耗顶部

    @BindView(R.id.glv_top)
    FullGridView glv_top;
    @BindView(R.id.txt_moduleOne)
    ClickTextView txt_moduleOne;
    @BindView(R.id.txt_moduleTwo)
    ClickTextView txt_moduleTwo;

    @BindView(R.id.group_time)
    RadioGroup group_time;
    @BindView(R.id.radio1)
    RadioButton radio1;
    @BindView(R.id.radio2)
    RadioButton radio2;
    @BindView(R.id.radio3)
    RadioButton radio3;
    @BindView(R.id.radio4)
    RadioButton radio4;


    //第一张图表
    @BindView(R.id.tab_paint)
    MyTablayout tab_paint;
    @BindView(R.id.frame__paint)
    FrameLayout frame__paint;
    @BindView(R.id.txt_notata)
    TextView txt_notata;//显示时间

@BindView(R.id.txt_noDatas)
TextView txt_noDatas;//后续版本敬请期待
    private String repeatTime = "0";//0代表没有重复  会走    1代表已经走过一次   不能连续再走了
    private String repeatTimeTwo = "0";//0代表没有重复  会走    1代表已经走过一次   不能连续再走了
    private int tabPosition = 0;//tablayout滑动的位置
    private int tabPositionTwo = 0;
    private int time;//0 代表时  1 日  2月
    private int timeTwo = 0;
    private int tab_running_position = 0;
    private String day_rememrber = "";
    private String category = "";
    private String categoryTwo = "";
    private String tabPostion_position = "0";//诊断图tab滑动的位置
    private String tabPosition_positionTwo = "0";

    private EventbusBean eventbusBean;
    private List<EnergyMeterBean.ResobjBean.ChartCategBean.ChartBean> list_diagOne;
    private EventBusDownBean eventBusDownBean;
    private List<EnergyMeterBean.ResobjBean.ChartCategBean.ChartBean> list_diagTwo;

    private boolean isFirst_join = true;
    private boolean isproject_visible = false, isFist = true, isFirstTwo = true;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    @Override
    protected int initContentView() {
        return R.layout.activity_heat_metering;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        presenter.onResume(this);
/*

        //第一部分图表信息
        List<EnergyMeterBean.ResobjBean.DiagCategoryBean> diagCategory = resobj.getDiagCategory();
        int categorySize = diagCategory.size();//图表模块
        if (diagCategory == null || diagCategory.isEmpty()) {
            return;
        }
        txt_moduleTwo.setText(diagCategory.get(0).getCategoryName());
        list_diagOne = diagCategory.get(0).getChart();
        if (list_diagOne != null && !list_diagOne.isEmpty()) {
            if (isFist) {
                isFist = !isFist;
                initSetPaint();//设置图表信息
                setTimeVisible(0);
            }

        }

*/


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

    private void setPaintParamsTwo(String tabPostion_position) {
        if (list_diagTwo.get(Integer.parseInt(tabPostion_position)).getCode() != null) {
            categoryTwo = list_diagTwo.get(Integer.parseInt(tabPostion_position)).getCode();
        }
        eventBusDownBean.setPosition(tabPostion_position);
        eventBusDownBean.setPaintCount(list_diagTwo.get(Integer.parseInt(tabPostion_position)).getChartNum());
        eventBusDownBean.setCode(categoryTwo);
    }

    /**
     * 显示时日月
     */
    private void setTimeVisible(int tabPosition) {
        /* List<HVAC_ProjectDetailBean.ResobjBean.DiagCategoryBean.ChartBean.TimeNameBean> listTime = list_diagOne.get(tabPosition).getTimeName();
        radio1.setVisibility(View.GONE);
        radio2.setVisibility(View.GONE);
        radio3.setVisibility(View.GONE);
        radio1.setBackgroundResource(R.mipmap.white_background_circle);
        radio2.setBackgroundResource(R.mipmap.white_background_circle);
        radio3.setBackgroundResource(R.mipmap.white_background_circle);
        for (int i = 0; i < listTime.size(); i++) {
            switch (listTime.get(i).getTimeCode()) {
                case "A":
                    radio1.setVisibility(View.VISIBLE);
                    if (time == 0) {
                        eventbusBean.setIsMonth("0");
                        radio1.setBackgroundResource(R.mipmap.green_background_circle);
                    }
                    break;
                case "B":
                    if (radio1.getVisibility() == View.GONE) {
                        eventbusBean.setIsMonth("1");
                        radio2.setBackgroundResource(R.mipmap.green_background_circle);
                    } else {
                        if (time == 1) {//当点击日的时候，并且有时跟日   会获取日的数据
                            radio2.setBackgroundResource(R.mipmap.green_background_circle);
                            eventbusBean.setIsMonth("1");
                        }
                    }
                    radio2.setVisibility(View.VISIBLE);
                    break;
                case "C":
                    radio3.setVisibility(View.VISIBLE);
                    if (radio1.getVisibility() == View.GONE && radio2.getVisibility() == View.GONE) {
                        eventbusBean.setIsMonth("1");
                        radio3.setBackgroundResource(R.mipmap.green_background_circle);
                    }
                    break;
                default:
                    break;
            }
        }
        */
    }

    private void setPaintParams(String tabPostion_position) {
        if (list_diagOne.get(Integer.parseInt(tabPostion_position)).getCode() != null) {
            category = list_diagOne.get(Integer.parseInt(tabPostion_position)).getCode();
        }
        eventbusBean.setPosition(tabPostion_position);
        eventbusBean.setPaintCount(list_diagOne.get(Integer.parseInt(tabPostion_position)).getChartNum());
        eventbusBean.setCode(category);
    }


    /**
     * 设置图表信息
     *
     * @param
     */
    private void initSetPaint() {
        eventbusBean = new EventbusBean();
        setPaintParams("0");
        //  List<HVAC_ProjectDetailBean.ResobjBean.DiagCategoryBean.ChartBean.TimeNameBean> timeName = list_diagOne.get(0).getTimeName();
       /* if (timeName.equals("A")) {
            eventbusBean.setIsMonth("0");
        } else {
            eventbusBean.setIsMonth("1");
        }*/

        eventbusBean.setChartSign(list_diagOne.get(0).getTimeName().get(0).getChartSign());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        App.sp.setDate(format.format(new Date(System.currentTimeMillis())));
        EventBus.getDefault().post(eventbusBean);
        tab_paint.setTabMode(TabLayout.MODE_FIXED);
        final String title[] = new String[list_diagOne.size()];
        for (int i = 0; i < list_diagOne.size(); i++) {
            title[i] = list_diagOne.get(i).getText();
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
                switch (tabPosition) {
                    case 0:
                        tabPostion_position = "0";
                        setPaintParams(tabPostion_position);
                        setTimeVisible(tabPosition);//设置选择时日月的显示
                        EventBus.getDefault().post(eventbusBean);

                        break;
                    case 1:
                        tabPostion_position = "1";
                        setPaintParams(tabPostion_position);
                        setTimeVisible(tabPosition);//设置选择时日月的显示
                        EventBus.getDefault().post(eventbusBean);
                        break;
                    case 2:
                        tabPostion_position = "2";
                        setPaintParams(tabPostion_position);
                        setTimeVisible(tabPosition);//设置选择时日月的显示
                        EventBus.getDefault().post(eventbusBean);
                        break;
                    case 3:
                        tabPostion_position = "3";
                        setPaintParams(tabPostion_position);
                        setTimeVisible(tabPosition);//设置选择时日月的显示
                        EventBus.getDefault().post(eventbusBean);
                        break;
                    default:
                        break;
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

    @Override
    protected HeatMeteringPresenter getPresenter() {
        return new HeatMeteringPresenter();
    }

    @OnClick({R.id.iv_back, R.id.txt_record})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.txt_record:
                startActivity(new Intent(this, EnergyRecordListActivity.class));
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
    public void getTopList(Object data) {
        txt_noDatas.setVisibility(View.VISIBLE);
        EnergyMeterBean bean = (EnergyMeterBean) data;
        EnergyMeterBean.ResobjBean resobj = bean.getResobj();
        //能耗顶部
        List<EnergyMeterBean.ResobjBean.GroupDataBean> groupData = resobj.getGroupData();
        if (groupData != null) {
            EnergyTopAdapter adapter = new EnergyTopAdapter(groupData, this);
            lv_energyP.setAdapter(adapter);
        }
     /*   //能效分析
        EnergyMeterBean.ResobjBean.EnerAnalyBean enerAnaly = resobj.getEnerAnaly();
        List<EnergyMeterBean.ResobjBean.EnerAnalyBean.AnalyParamBean> listEnerAnaly = enerAnaly.getAnalyParam();
        if (!listEnerAnaly.isEmpty()){

        }*/
    }
}
