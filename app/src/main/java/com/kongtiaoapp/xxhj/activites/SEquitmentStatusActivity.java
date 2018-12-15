package com.kongtiaoapp.xxhj.activites;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.Loading_RefrigeratorBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.SEquitmentStatusPresenter;
import com.kongtiaoapp.xxhj.mvp.view.SEquitmentStatusView;
import com.kongtiaoapp.xxhj.ui.address.AssetsUtils;
import com.kongtiaoapp.xxhj.ui.view.Mf_Tools;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 具体设备运行状况
 */
public class SEquitmentStatusActivity extends BaseActivity<SEquitmentStatusPresenter, SEquitmentStatusView> implements SEquitmentStatusView {
    @BindView(R.id.frame_down)
    FrameLayout frame_down;
    @BindView(R.id.rela_paint)
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
    private boolean isFirst = true;
    private List<TextView> list, list1;
    private String type, type1, method;
    private boolean isMonth;
    private String date;
    private int paintCount = 1;//图例个数


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_sequitmentstatus;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        list1.add(graph11);
        list1.add(graph21);
        list1.add(graph31);
        list1.add(graph41);
        list1.add(graph51);
        list1.add(graph61);
        list1.add(graph71);
        list1.add(graph81);
        list1.add(graph91);
        list.add(graph1);
        list.add(graph2);
        list.add(graph3);
        list.add(graph4);
        list.add(graph5);
        list.add(graph6);
        list.add(graph7);
        list.add(graph8);
        list.add(graph9);
        getPaintOne();//得到第一个图表
        getPaintTwo();//得到第二个图表
    }

    private void setGraph(String[] titles) {
        Mf_Tools.setGraph(list, titles);
    }

    private void setGraph1(String[] titles) {
        Mf_Tools.setGraph(list1, titles);

    }

    private void getPaintTwo() {
        String json = AssetsUtils.readText(this, "shuibengeffect.json");
        Loading_RefrigeratorBean lr_bean = JSON.parseObject(json, Loading_RefrigeratorBean.class);
        String dates = "";
        dates = App.sp.getDate();
        if (!dates.isEmpty() && !dates.equals("")) {
            txt_notata1.setText(dates);
            txt_notata1.setTextColor(getResources().getColor(R.color.theme_color));
        }
        Loading_RefrigeratorBean.ResobjBean resobj = lr_bean.getResobj();
        List<Loading_RefrigeratorBean.ResobjBean.DataBean> listData = resobj.getData();
        List<double[]> listX = new ArrayList<>();
        List<double[]> listY = new ArrayList<>();
        String[] titles = new String[listData.size()];
        for (int i = 0; i < listData.size(); i++) {
            listX.add(resobj.getTime());
            listY.add(listData.get(i).getValue());
            titles[i] = listData.get(i).getText();
        }
        setGraph1(titles);//设置图列的个数
        try {
            Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), this, rela_loading1, isMonth, resobj.getNowTime());
        } catch (Exception e) {
            ToastUtils.showToast(this, "图表数据有异常,请您稍后再尝试!");
            return;
        }
    }

    private void getPaintOne() {
        String json = AssetsUtils.readText(this, "shuibengenergy.json");
        Loading_RefrigeratorBean lr_bean = JSON.parseObject(json, Loading_RefrigeratorBean.class);
   /*     if (!date.isEmpty() && !date.equals("")) {
            txt_notata.setText(date);
            txt_notata.setTextColor(getResources().getColor(R.color.theme_color));
            txt_notata.setVisibility(View.VISIBLE);
        }*/
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
            Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), this, rela_loading, isMonth, resobj.getNowTime());
        } catch (Exception e) {
            ToastUtils.showToast(this, "图表数据有异常,请您稍后再尝试!");
            return;
        }
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected SEquitmentStatusPresenter getPresenter() {
        return new SEquitmentStatusPresenter();
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
