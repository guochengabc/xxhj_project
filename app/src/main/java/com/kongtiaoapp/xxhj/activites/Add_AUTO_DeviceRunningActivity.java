package com.kongtiaoapp.xxhj.activites;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.Add_AUTO_DeviceAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.Add_AUDO_DEVICE_PaintBean;
import com.kongtiaoapp.xxhj.bean.Add_AUTO_DeviceBean;
import com.kongtiaoapp.xxhj.bean.RunningParam;
import com.kongtiaoapp.xxhj.db.ProjectTable;
import com.kongtiaoapp.xxhj.interfaces.Add_AUTO_DeviceRunning;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.Add_AUTO_DeviceRunningPresenter;
import com.kongtiaoapp.xxhj.mvp.view.Add_AUTO_DeviceRunningView;
import com.kongtiaoapp.xxhj.ui.address.ScreenUtils;
import com.kongtiaoapp.xxhj.ui.view.Mf_Tools;
import com.kongtiaoapp.xxhj.ui.view.MyLinearlayout;
import com.kongtiaoapp.xxhj.ui.view.MyScrollView;
import com.kongtiaoapp.xxhj.ui.view.draglistview.DragListView;
import com.kongtiaoapp.xxhj.utils.DateUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 查看实时设备运行状态和参数
 */
public class Add_AUTO_DeviceRunningActivity extends BaseActivity<Add_AUTO_DeviceRunningPresenter, Add_AUTO_DeviceRunningView> implements Add_AUTO_DeviceRunningView
        , MyScrollView.OnScrollListener, AbsListView.OnScrollListener, Add_AUTO_DeviceRunning {

    @BindView(R.id.ml_add_device)
    MyLinearlayout ml_add_device;
    private TextView graph1;
    private TextView graph2;
    private TextView graph3;
    private TextView graph4;
    private TextView graph5;
    private TextView graph6;
    private TextView graph7;
    private TextView graph8;
    private TextView graph9;
    @BindView(R.id.listview)
    DragListView mListview;
    @BindView(R.id.txt_setting_draw)
    TextView txt_setting_draw;//设置拖动
    @BindView(R.id.addDeviceInfo_tv_save)
    TextView addDeviceInfo_tv_save;
    @BindView(R.id.line_add_device_info)
    LinearLayout line_add_device_info;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.txt_setting_font_small)//小号字体
            TextView txt_setting_font_small;
    @BindView(R.id.txt_setting_font_mid)//中号字体
            TextView txt_setting_font_mid;
    @BindView(R.id.txt_setting_font_big)//大号字体
            TextView txt_setting_font_big;
    private String type;
    private Add_AUTO_DeviceAdapter adapter;
    private String deviceId;
    private String deviceName;
    private List<RunningParam> mList = new ArrayList<>();
    private String key = "";
    private String exChangeSystem_Device = "0";//0代表系统  1设备
    private ProjectTable project;
    private int rawX = 0;//原始X坐标
    private int rawY = 0;//原始Y坐标
    private int offsetXX = 0;
    private int offsetYY = 0;
    private boolean draw_type = false;
    private boolean isAuto = false, isFirstRefresh = true;
    private List<String> list_code = new ArrayList();
    private List<TextView> list;
    private int whichPaint = 0;//0  代表的时   1  代表的日
    // 监听器在手机拖动停下的时候触发
    private DragListView.DropListener onDrop = new DragListView.DropListener() {
        @Override
        public void drop(int from, int to) {// from to 分别表示 被拖动控件原位置 和目标位置
            if (from != to) {
                RunningParam item = (RunningParam) adapter.getItem(from);// 得到listview的适配器
                adapter.remove(from);// 在适配器中”原位置“的数据。
                adapter.insert(item, to);// 在目标位置中插入被拖动的控件。
            }
        }
    };
    // 删除监听器，点击左边差号就触发。删除item操作。
    private DragListView.RemoveListener onRemove = new DragListView.RemoveListener() {
        @Override
        public void remove(int which) {
            adapter.remove(which);
        }
    };

    private TextView txt_notata;
    private String currentDate;
    private FrameLayout frame_up;
    private RelativeLayout rela_paint;
    private String day;
    private String month;
    private boolean isFirstLoading=true;

    @OnClick({R.id.iv_back, R.id.txt_setting_draw, R.id.txt_setting_font_small,
            R.id.txt_setting_font_mid, R.id.txt_setting_font_big, R.id.addDeviceInfo_tv_save
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                close_key();
                finish();
                break;
            case R.id.txt_setting_draw:
                if (mList != null && !mList.isEmpty()) {
                    draw_type = !draw_type;
                    if (!draw_type) {
                        mListview.setDragEnabled(false);
                        txt_setting_draw.setText(getResources().getString(R.string.txt_setting_draw));
                        //  if (!isAuto) {
                        addDeviceInfo_tv_save.setVisibility(View.VISIBLE);
                        //  }
                        txt_setting_draw.setTextColor(getResources().getColor(R.color.black));
                        saveDrag_CommitData();//提交尽心拖动顺序的数据
                    } else if (draw_type) {
                        mListview.setDragEnabled(true);
                        txt_setting_draw.setText(getResources().getString(R.string.save_draw));
                        addDeviceInfo_tv_save.setVisibility(View.GONE);
                        txt_setting_draw.setTextColor(getResources().getColor(R.color.theme_color));
                    }
                }
                break;
            case R.id.txt_setting_font_small:
                if (mList != null && !mList.isEmpty()) {
                    setBackground(txt_setting_font_small, txt_setting_font_mid, txt_setting_font_big);//设置背景色
                    App.sp.setFontSize("0");
                    adapter.setList(mList, isAuto);
                }
                break;
            case R.id.txt_setting_font_mid:


                if (mList != null && !mList.isEmpty()) {
                    setBackground(txt_setting_font_mid, txt_setting_font_small, txt_setting_font_big);//设置背景色
                    App.sp.setFontSize("1");
                    adapter.setList(mList, isAuto);
                }

                break;
            case R.id.txt_setting_font_big:
                if (mList != null && !mList.isEmpty()) {
                    setBackground(txt_setting_font_big, txt_setting_font_small, txt_setting_font_mid);//设置背景色
                    App.sp.setFontSize("2");
                    adapter.setList(mList, isAuto);
                }
                break;
            case R.id.addDeviceInfo_tv_save://切换系统运行记录
                if (exChangeSystem_Device.equals("0")) {//系统切换设备
                    presenter.onResume(this, deviceId);
                } else if (exChangeSystem_Device.equals("1")) {//设备切换系统
                    presenter.onResumeSystem(this, App.sp.getProjectId());
                }
                break;

            default:
                break;
        }
    }


    private void setBackground(TextView txt1, TextView txt2, TextView txt3) {

        txt1.setTextColor(getResources().getColor(R.color.a000000));
        txt2.setTextColor(getResources().getColor(R.color.devicedetail_selectcolor));
        txt3.setTextColor(getResources().getColor(R.color.devicedetail_selectcolor));
    }

    private void saveDrag_CommitData() {
        mList = null;
        list_code.clear();
        mList = adapter.getmList();
        for (int i = 0; i < mList.size(); i++) {
            list_code.add(mList.get(i).getCode());
        }
        JsonArray asJsonArray = gson.toJsonTree(list_code, new TypeToken<List<String>>() {
        }.getType()).getAsJsonArray();
        List list = new ArrayList<>();
        if (exChangeSystem_Device.equals("0")) {
            list.add(gson.toJson(App.sp.getProjectId()));
            list.add(asJsonArray.toString());
            presenter.saveDrag_CommitData_Sys(this, list);
        } else if (exChangeSystem_Device.equals("1")) {
            list.add(gson.toJson(deviceId));
            list.add(asJsonArray.toString());
            presenter.saveDrag_CommitData(this, list);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        type = getIntent().getStringExtra("type");
        deviceId = getIntent().getStringExtra("deviceId");
        deviceName = getIntent().getStringExtra("deviceName");
        isAuto = getIntent().getBooleanExtra(ConstantValue.AUTO, false);
        return R.layout.activity_auto_add_device_info;
    }

    @Override
    protected void initView() {
        month = DateUtils.getYM();
        day = DateUtils.getYMD();
        setHeadView();//设置头部信息及界面（图表）
        addDeviceInfo_tv_save.setText(getString(R.string.running_sys_record));
        addDeviceInfo_tv_save.setVisibility(View.GONE);//功能已经做好 暂时关掉
        mListview.setOnScrollListener(this);
        if (App.sp.getFontSize().equals("0")) {
            setBackground(txt_setting_font_small, txt_setting_font_mid, txt_setting_font_big);//设置背景色
        } else if (App.sp.getFontSize().equals("1")) {
            setBackground(txt_setting_font_mid, txt_setting_font_small, txt_setting_font_big);//设置背景色
        } else if (App.sp.getFontSize().equals("2")) {
            setBackground(txt_setting_font_big, txt_setting_font_small, txt_setting_font_mid);//设置背景色
        }
        if (type.equals("bbcs")) {
            txt_setting_draw.setVisibility(View.GONE);
        } else {
            txt_setting_draw.setVisibility(View.VISIBLE);
            txt_setting_draw.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/huawencaibi.TTF"));
            mListview.setDropListener(onDrop);
            mListview.setRemoveListener(onRemove);
        }
        tvTitle.setText(deviceName);
        mListview.setDragEnabled(false);
        getData(day);

    }

    /**
     * 设置图表信息
     */
    private void setHeadView() {
        View view = getLayoutInflater().inflate(R.layout.activity_sequitmentstatus, null);
        ((ImageView) view.findViewById(R.id.img_left)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (whichPaint == 0) {//时
                    day = DateUtils.getSpecifiedDayBefore(day);
                    getData(day);
                } else if (whichPaint == 1) {//日
                    month = DateUtils.getLastMonth(month);
                    getData(month);

                }
            }
        });
        ((ImageView) view.findViewById(R.id.img_right)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (whichPaint == 0) {//时
                    day = DateUtils.getSpecifiedDayAfter(day);
                    getData(day);
                } else if (whichPaint == 1) {//日
                    month = DateUtils.getNextMonth(month);
                    getData(month);
                }
            }
        });
        RadioGroup group_time = ((RadioGroup) view.findViewById(R.id.group_time));
        RadioButton radio_day = ((RadioButton) view.findViewById(R.id.radio_day));
        RadioButton radio_month = ((RadioButton) view.findViewById(R.id.radio_month));
        rela_paint = ((RelativeLayout) view.findViewById(R.id.rela_paint));
        frame_up = ((FrameLayout) view.findViewById(R.id.frame_up));
        FrameLayout frame_down = ((FrameLayout) view.findViewById(R.id.frame_down));
        frame_down.setVisibility(View.GONE);
        txt_notata = ((TextView) view.findViewById(R.id.txt_notata));
        graph1 = ((TextView) view.findViewById(R.id.graph1));
        graph2 = ((TextView) view.findViewById(R.id.graph2));
        graph3 = ((TextView) view.findViewById(R.id.graph3));
        graph4 = ((TextView) view.findViewById(R.id.graph4));
        graph5 = ((TextView) view.findViewById(R.id.graph5));
        graph6 = ((TextView) view.findViewById(R.id.graph6));
        graph7 = ((TextView) view.findViewById(R.id.graph7));
        graph8 = ((TextView) view.findViewById(R.id.graph8));
        graph9 = ((TextView) view.findViewById(R.id.graph9));


        mListview.addHeaderView(view);

        group_time.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_day:
                        whichPaint = 0;//代表我点击时
                        radio_day.setBackgroundResource(R.mipmap.green_background_circle);
                        radio_month.setBackgroundResource(R.mipmap.white_background_circle);
                        currentDate = DateUtils.getCurrentDate("yyyy-MM-dd");
                        getData(day);
                        break;

                    case R.id.radio_month:
                        whichPaint = 1;//代表我点击日
                        radio_day.setBackgroundResource(R.mipmap.white_background_circle);
                        radio_month.setBackgroundResource(R.mipmap.green_background_circle);
                        currentDate = DateUtils.getCurrentDate("yyyy-MM");
                        getData(month);
                        break;
                }
            }
        });
    }

    @Override
    protected void initListener() {

    }


    @Override
    protected void initData() {
        if (ScreenUtils.isScreenOriatationPortrait(this)) {
            Mf_Tools.setLayoutHeight(this, frame_up, rela_paint, 1);//根据横竖屏切换控制视图展示   竖屏
        } else {
            Mf_Tools.setLayoutHeight(this, frame_up, rela_paint, 2);//根据横竖屏切换控制视图展示  横屏

        }
        /*最上面的图表*/
        list = new ArrayList<>();
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


    @Override
    protected Add_AUTO_DeviceRunningPresenter getPresenter() {
        return new Add_AUTO_DeviceRunningPresenter();
    }

    private void getData(String dates) {
        List<String> list_pai = new ArrayList<>();
        list_pai.add(deviceId);
        list_pai.add(dates);//时间  可填可不填
        if (isFirstLoading){
            isFirstLoading = false;
            presenter.onResume(this, deviceId);
        }
        presenter.onResumeDevice(this, list_pai);//进入界面获取图表数据
    }

    @Override
    public void onScroll(int scrollY) {
        if (scrollY > 0) {
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {

            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:    //当停止滚动时

                break;

            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:    //滚动时

                //没错，下面这一坨就是隐藏软键盘的代码
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                break;

            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:   //手指抬起，但是屏幕还在滚动状态

                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


    @Override
    public void setText(Object data) {

    }

    /**
     * 进入界面获取图表的数据的情况
     */
    @Override
    public void setListPaint(Object data) {
        if (whichPaint==0){
            txt_notata.setText(day);
        }else{
            txt_notata.setText(month);
        }
        rela_paint.removeAllViews();
        Add_AUDO_DEVICE_PaintBean bean = (Add_AUDO_DEVICE_PaintBean) data;
        Add_AUDO_DEVICE_PaintBean.ResobjBean resobj = bean.getResobj();
        List<Add_AUDO_DEVICE_PaintBean.ResobjBean.DataBean> listData = resobj.getData();

        if (listData == null) {
            return;
        }


        txt_notata.setVisibility(View.VISIBLE);
        txt_notata.setTextColor(getResources().getColor(R.color.theme_color));

        List<double[]> listX = new ArrayList<>();
        List<double[]> listY = new ArrayList<>();
        String[] titles = new String[listData.size()];
        for (int i = 0; i < listData.size(); i++) {
            titles[i] = listData.get(i).getText();
            listX.add(resobj.getTime());
            listY.add(listData.get(i).getValue());
        }
        setGraphPaint(list, titles);//设置图列的个数
        try {
            if (resobj.getFlag().equals("Z")) {
                Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), this, rela_paint, true, resobj.getNowTime());

            } else if (resobj.getFlag().equals("S")) {
                Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), this, rela_paint, false, "month", resobj.getNowTime());
            } else {
                Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), this, rela_paint, false, resobj.getNowTime());

            }
        } catch (Exception e) {
            ToastUtils.showToast(this, "图表数据有异常,请您稍后再尝试!");
            return;
        }
    }

    /**
     * 进入界面获取图表时为空的情况
     */
    @Override
    public void setList_nullPaint() {
        txt_notata.setText(getString(R.string.no_data));
        rela_paint.removeAllViews();
    }

    /**
     * 进入界面获取图表时错误的情况
     */
    @Override
    public void setList_error() {
        txt_notata.setText(getString(R.string.paint_error));
        rela_paint.removeAllViews();
    }

    @Override
    public void setList(Object data) {
        isFirstRefresh = false;
        mList.clear();
        List list = (List<RunningParam>) data;
        mList.addAll(list);
        if (mList == null) {
            return;
        }
        if (mList.size() > 0) {
            key = mList.get(0).getUnit();
        }
        if (exChangeSystem_Device.equals("0")) {
            addDeviceInfo_tv_save.setText(getString(R.string.running_sys_record));
            exChangeSystem_Device = "1";
        } else if (exChangeSystem_Device.equals("1")) {
            addDeviceInfo_tv_save.setText(getString(R.string.running_device_record));
            exChangeSystem_Device = "0";
        }
        adapter = new Add_AUTO_DeviceAdapter(mList, this, true);
        mListview.setAdapter(adapter);

    }

    @Override
    public void saveDrag_CommitData_View(Object data) {
        ToastUtils.showToast(this, getResources().getString(R.string.drag_success));
    }

    /*下面几个都是图表的*/
    @Override
    public void showPaint(Object data) {
        month = DateUtils.getYM();
        day = DateUtils.getYMD();
        if (whichPaint==0){
            txt_notata.setText(day);
        }else{
            txt_notata.setText(month);
        }
        ml_add_device.getFocus(false);
        Add_AUTO_DeviceBean bean = (Add_AUTO_DeviceBean) data;
        Add_AUTO_DeviceBean.ResobjBean resobj = bean.getResobj();
        List<Add_AUTO_DeviceBean.ResobjBean.DataBean> listData = resobj.getData();
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
        setGraphPaint(list, titles);//设置图列的个数
        try {
            if (resobj.getFlag().equals("Z")) {
                Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), this, rela_paint, true, resobj.getNowTime());

            } else if (resobj.getFlag().equals("S")) {
                Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), this, rela_paint, false, "month", resobj.getNowTime());
            } else {
                Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), this, rela_paint, false, resobj.getNowTime());

            }
        } catch (Exception e) {
            ToastUtils.showToast(this, "图表数据有异常,请您稍后再尝试!");
            return;
        }
    }

    private void setGraphPaint(List<TextView> listPaints, String[] titles) {
        Mf_Tools.setGraph(listPaints, titles);

    }

    @Override
    public void list_null() {

    }

    @Override
    public void errorlist() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        line_add_device_info.setBackgroundResource(0);
    /*    if (thread != null && thread.isAlive()) {
            thread.interrupt();
        }*/
        if (mList != null) {
            mList.clear();
            mList = null;
        }
        if (list_code != null) {
            list_code.clear();
            list_code = null;
        }
    }

    /**
     * 点击设备时要显示图表
     */
    @Override
    public void getCodeing(RunningParam param) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<String> lists = new ArrayList<>();
        if (deviceId == null) {
            deviceId = "";
        }
        lists.add(deviceId);
        lists.add("" + param.getCode());
        lists.add(format.format(new Date()));
        presenter.showPaint(this, lists);
    }

}
