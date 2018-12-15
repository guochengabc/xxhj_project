package com.kongtiaoapp.xxhj.activites;

import android.content.res.Configuration;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.JieYueList;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.RunningRecordPresenter;
import com.kongtiaoapp.xxhj.mvp.view.RunningRecordView;
import com.kongtiaoapp.xxhj.ui.timeselector.activity.TimePickerView;
import com.kongtiaoapp.xxhj.ui.view.Mf_Tools;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ASUS-PC on 2016/6/16.
 * 运行-->点击能耗跳转的历史记录
 */
public class RunningRecordActivity extends BaseActivity<RunningRecordPresenter, RunningRecordView> implements RunningRecordView {
    @BindView(R.id.frame_down)
    FrameLayout frame_down;
    @BindView(R.id.frame_up0)
    FrameLayout frame_up0;
    @BindView(R.id.frame_up)
    FrameLayout frame_up;
    @BindView(R.id.rela_paint1)
    RelativeLayout rela_loading1;
    @BindView(R.id.rela_paint)
    RelativeLayout rela_loading;
    @BindView(R.id.rela_paint0)
    RelativeLayout rela_loading0;
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
    @BindView(R.id.graph10)//graph1-9代表的是图例
            TextView graph10;
    @BindView(R.id.graph20)
    TextView graph20;
    @BindView(R.id.graph30)
    TextView graph30;
    @BindView(R.id.graph40)
    TextView graph40;
    @BindView(R.id.graph50)
    TextView graph50;
    @BindView(R.id.graph60)
    TextView graph60;
    @BindView(R.id.graph70)
    TextView graph70;
    @BindView(R.id.graph80)
    TextView graph80;
    @BindView(R.id.graph90)
    TextView graph90;
    @BindView(R.id.txt_notata0)
    TextView txt_notata0;
    @BindView(R.id.txt_notata1)
    TextView txt_notata1;
    private String projectId = "";
    List<TextView> list = new ArrayList<>();//把textview放置list集合里
    List<TextView> list1 = new ArrayList<>();
    List<TextView> list0 = new ArrayList<>();
    private List<JieYueList.ResobjBean> list_reso = new ArrayList<>();
    private String date;

    @Event(value = {R.id.iv_back, R.id.img_add_time})
//设置点击事件   R.id.iv_back  代表的是返回上一界面的点击事件  每隔activity或者fragment都有
    private void getEvent(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.img_add_time:
                setdatePicker();
                break;
            default:
                break;

        }
    }

    @Override
    protected int initContentView() {//加载布局
        projectId = getIntent().getStringExtra("projectId");
        return R.layout.activity_runningrecords;

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Mf_Tools.setLayoutHeight(this, frame_up0, rela_loading0, newConfig.orientation);
        Mf_Tools.setLayoutHeight(this, frame_up, rela_loading, newConfig.orientation);
        Mf_Tools.setLayoutHeight(this, frame_down, rela_loading1, newConfig.orientation);
    }

    @Override
    protected void initView() {//初始化布局及相关操作
        x.view().inject(this);
    }

    /*设置监听事件*/
    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {//初始化数据
        list0.add(graph10);
        list0.add(graph20);
        list0.add(graph30);
        list0.add(graph40);
        list0.add(graph50);
        list0.add(graph60);
        list0.add(graph70);
        list0.add(graph80);
        list0.add(graph90);
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
        date = new SimpleDateFormat("yyyy-MM").format(new Date());
        getDataForServers();//每次进入界面都会有该方法代表的是访问服务器的数据
    }

    //所有的带这个方法的都表示初始化Presenter  getPresenter()
    @Override
    protected RunningRecordPresenter getPresenter() {
        return new RunningRecordPresenter();
    }

    private void getDataForServers() {
        List<String> list = new ArrayList<>();
        if (projectId != null && !projectId.equals("")) {
            list.add(date);
            list.add(projectId);
            presenter.onResume(this, list);
        }
    }

    private void setdatePicker() {

        TimePickerView pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH);
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        pvTime.setOnTimeSelectListener(dates -> {
            date = getTime(dates);
            getDataForServers();
        });
        pvTime.show();
    }


    public String getTime(Date date) {
        SimpleDateFormat format = null;
        format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }


    private void setGraph0(String[] titles) {
        Mf_Tools.setGraph(list0, titles);
    }

    private void setGraph1(String[] titles) {
        Mf_Tools.setGraph(list1, titles);
    }

    private void setGraph(String[] titles) {
        Mf_Tools.setGraph(list, titles);
    }

    /*每个acvitity都有  settext方法   代表的是更新textview布局*/
    @Override
    public void setText(Object text) {

    }

    @Override
    public void success_one() {
        Mf_Tools.hintAllView(list);
        Mf_Tools.hintAllView(list1);
        Mf_Tools.hintAllView(list0);
        rela_loading0.removeAllViews();
        rela_loading1.removeAllViews();
        rela_loading.removeAllViews();

    }

    @Override
    public void paint_one(Object response) {
        JieYueList info = (JieYueList) response;
        list_reso = info.getResobj();
        int size = list_reso.size();
        setPaint(rela_loading0, txt_notata0, 0);//绘制第一个图表
        if (size == 2) {
            setPaint(rela_loading, txt_notata, 1);//绘制第二个图表
        } else if (size == 3) {
            setPaint(rela_loading, txt_notata, 1);//绘制第二个图表
            setPaint(rela_loading1, txt_notata1, 2);//绘制第三个图表
        }
    }

    private void setPaint(RelativeLayout relativeLayout, TextView textView, int positions) {
        if (list_reso.get(positions).getData() == null) {
            return;
        }
        if (!date.isEmpty() && !date.equals("")) {
            textView.setText(date);
            textView.setTextColor(getResources().getColor(R.color.theme_color));
            textView.setVisibility(View.VISIBLE);
        }
        List<JieYueList.ResobjBean.DataBean> listData1 = list_reso.get(positions).getData();
        if (listData1 == null) {
            return;
        }
        List<double[]> listX1 = new ArrayList<>();
        List<double[]> listY1 = new ArrayList<>();
        String[] titles1 = new String[listData1.size()];
        for (int i = 0; i < listData1.size(); i++) {
            titles1[i] = listData1.get(i).getText();
            listY1.add(listData1.get(i).getValue());
            listX1.add(list_reso.get(positions).getTime());
        }
        if (positions == 0) {
            setGraph0(titles1);//设置图列的个数
        }
        if (positions == 1) {
            setGraph(titles1);//设置图列的个数
        } else if (positions == 2) {
            setGraph1(titles1);
        }
        JieYueList.ResobjBean bean1 = list_reso.get(positions);
        try {
            Mf_Tools.setData(titles1, listY1, listX1, bean1.getMaxX(), bean1.getMaxY(), bean1.getMinY(), this, relativeLayout, positions + "", bean1.getNowTime());
        } catch (Exception e) {
            ToastUtils.showToast(this, "图表数据有异常,请您稍后再尝试!");
            return;
        }
    }

    @Override
    public void list_null_one() {
        txt_notata0.setTextColor(getResources().getColor(R.color.red));
        txt_notata0.setText(getResources().getString(R.string.no_data));
        Mf_Tools.hintAllView(list0);
        txt_notata0.setVisibility(View.VISIBLE);
        if (list != null) {
            txt_notata.setTextColor(getResources().getColor(R.color.red));
            txt_notata.setText(getResources().getString(R.string.no_data));
            Mf_Tools.hintAllView(list);
            txt_notata.setVisibility(View.VISIBLE);
        }
        if (list1 != null) {
            txt_notata1.setTextColor(getResources().getColor(R.color.red));
            txt_notata1.setText(getResources().getString(R.string.no_data));
            Mf_Tools.hintAllView(list1);
            txt_notata1.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void error() {
        Mf_Tools.showToast(this, 1);
        Mf_Tools.hintAllView(list0);
        txt_notata0.setVisibility(View.INVISIBLE);
        if (list != null) {
            Mf_Tools.showToast(this, 1);
            Mf_Tools.hintAllView(list);
            txt_notata.setVisibility(View.INVISIBLE);
        }
        if (list1 != null) {
            Mf_Tools.showToast(this, 1);
            Mf_Tools.hintAllView(list1);
            txt_notata1.setVisibility(View.INVISIBLE);
        }
    }

    /*这个ondestory方法必须写   */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
