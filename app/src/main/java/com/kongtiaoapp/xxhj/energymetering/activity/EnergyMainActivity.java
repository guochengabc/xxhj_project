package com.kongtiaoapp.xxhj.energymetering.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.EnergyTopAdapter;
import com.kongtiaoapp.xxhj.bean.EnergyTopBean;
import com.kongtiaoapp.xxhj.bean.EnvironmentCPaintBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.EnergyMainP;
import com.kongtiaoapp.xxhj.mvp.view.EnergyMainV;
import com.kongtiaoapp.xxhj.ui.address.ScreenUtils;
import com.kongtiaoapp.xxhj.ui.view.Mf_Tools;
import com.kongtiaoapp.xxhj.ui.view.NoScrollGridView;
import com.kongtiaoapp.xxhj.utils.DateUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kongtiaoapp.xxhj.R.id.rela_paint;

public class EnergyMainActivity extends BaseActivity<EnergyMainP, EnergyMainV> implements EnergyMainV, AdapterView.OnItemClickListener, RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.tv_title)
    TextView tv_title;//顶部标题
    @BindView(R.id.glv_statistic)
    NoScrollGridView glv_statistic;//顶部列表

    //第一张图表
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

    //日、月、向左向右
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

    private boolean isMonth = false;
    private List<TextView> list = new ArrayList<>();
    private int whichPaint = 0;//0  代表的月   1  代表的年
    private String month;
    private String day;
    private boolean isFirstPaint = true;
    private EnergyTopAdapter adapter;
    private String projectId;
    private List<EnergyTopBean.ResobjBean.TopListBean> topList = new ArrayList<>();
    private String deviceId;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_energy_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        glv_statistic.setOnItemClickListener(this);
        group_time.setOnCheckedChangeListener(this);
        group_time.check(R.id.radio0);
        month = DateUtils.getYY();
        day = DateUtils.getYM();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        projectId = intent.getStringExtra("projectId");
        presenter.getMain(this, projectId);
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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Mf_Tools.setLayoutHeight(this, frame_up, rela_loading, newConfig.orientation);//根据横竖屏切换控制视图展示
    }

    @Override
    protected EnergyMainP getPresenter() {
        return new EnergyMainP();
    }

    @Override
    public void setText(Object data) {
        EnergyTopBean bean = (EnergyTopBean) data;
        EnergyTopBean.ResobjBean resobj = bean.getResobj();
        topList = resobj.getTopList();
        if (adapter == null) {
            adapter = new EnergyTopAdapter(topList, this);
            glv_statistic.setAdapter(adapter);
        } else if (adapter != null) {
            adapter.setList(topList);
        }
        if (!topList.isEmpty()) {
            deviceId = topList.get(0).getDeviceId();
            name = topList.get(0).getName()==null?"":topList.get(0).getName();
            tv_title.setText(name);
            isFirstPaint = false;
            getPaintData();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void getChart(Object data) {
        rela_loading.removeAllViews();
        tv_title.setText(name);
        EnvironmentCPaintBean bean = (EnvironmentCPaintBean) data;
        EnvironmentCPaintBean.ResobjBean resobj = bean.getResobj();
        Mf_Tools.hintAllView(list);
        txt_notata.setVisibility(View.VISIBLE);
        txt_notata.setText(whichPaint == 0 ? day : month);
        if (resobj == null) {
            return;
        }
        //第一张图表
        List<EnvironmentCPaintBean.ResobjBean.DataBean> listChat = resobj.getData();
        int chartSize = listChat.size();//图表个数
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
                    Mf_Tools.setData(titles, listY, listX, dataBean.getMaxX(), dataBean.getMaxY(), dataBean.getMinY(), this, rela_loading, 1, resobj.getNowTime());
                } else {
                    Mf_Tools.setData(titles, listY, listX, dataBean.getMaxX(), dataBean.getMaxY(), dataBean.getMinY(), this, rela_loading, 2, resobj.getNowTime());
                }
            } else if (resobj.getFlag().equals("S")) {
                if (whichPaint == 0) {
                    Mf_Tools.setData(titles, listY, listX, dataBean.getMaxX(), dataBean.getMaxY(), dataBean.getMinY(), this, rela_loading, "1", resobj.getNowTime());
                } else {
                    Mf_Tools.setData(titles, listY, listX, dataBean.getMaxX(), dataBean.getMaxY(), dataBean.getMinY(), this, rela_loading, "2", resobj.getNowTime());
                }
            } else {
                if (whichPaint == 0) {
                    Mf_Tools.setData(titles, listY, listX, dataBean.getMaxX(), dataBean.getMaxY(), dataBean.getMinY(), this, rela_loading, 1, resobj.getNowTime());
                } else {
                    Mf_Tools.setData(titles, listY, listX, dataBean.getMaxX(), dataBean.getMaxY(), dataBean.getMinY(), this, rela_loading, 2, resobj.getNowTime());
                }
            }
            setGraph(titles);//设置图列的个数
        } catch (Exception e) {
            ToastUtils.showToast(this, "图表数据有异常,请您稍后再尝试!");
            return;
        }


    }

    private void setGraph(String[] titles) {
        Mf_Tools.setGraph(list, titles);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        deviceId = topList.get(position).getDeviceId();
        name = topList.get(position).getName();
        getPaintData();//获取图表数据
    }

    @OnClick({R.id.iv_back, R.id.img_left, R.id.img_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.img_left:
                if (whichPaint == 0) {//日

                    day = DateUtils.getLastMonth(day);
                    getPaintData();
                } else if (whichPaint == 1) {//月
                    month = DateUtils.getLastYear(month);
                    getPaintData();
                }
                break;
            case R.id.img_right:
                if (whichPaint == 0) {//日
                    day = DateUtils.getNextMonth(day);
                    getPaintData();
                } else if (whichPaint == 1) {//月
                    month = DateUtils.getNextYear(month);
                    getPaintData();
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (isFirstPaint) {
            return;
        }
        switch (checkedId) {
            case R.id.radio0:
                whichPaint = 0;//代表我点击月
                img_left.setImageResource(R.mipmap.leftarrow);
                img_right.setImageResource(R.mipmap.rightarrow);
                isMonth = false;
                radio0.setBackgroundResource(R.mipmap.green_background_circle);
                radio1.setBackgroundResource(R.mipmap.white_background_circle);
                getPaintData();
                break;
            case R.id.radio1:
                whichPaint = 1;//代表我点击年
                img_left.setImageResource(R.mipmap.arrow_double_left);
                img_right.setImageResource(R.mipmap.arrow_double_right);
                isMonth = true;
                radio0.setBackgroundResource(R.mipmap.white_background_circle);
                radio1.setBackgroundResource(R.mipmap.green_background_circle);
                getPaintData();
                break;
        }
    }

    /**
     * 获取图表数据
     */
    private void getPaintData() {
        List list = new ArrayList();
        list.add(projectId);
        list.add(deviceId);
        list.add(name);
        if (whichPaint == 0) {
            list.add(day);
        } else {
            list.add(month);
        }
        presenter.getChart(this, list);
    }
}
