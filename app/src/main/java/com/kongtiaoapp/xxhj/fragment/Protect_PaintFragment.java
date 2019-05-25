package com.kongtiaoapp.xxhj.fragment;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.EventbusBean;
import com.kongtiaoapp.xxhj.bean.ChartDataBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.Protect_PaintPresenter;
import com.kongtiaoapp.xxhj.mvp.view.Protect_PaintView;
import com.kongtiaoapp.xxhj.ui.address.ScreenUtils;
import com.kongtiaoapp.xxhj.ui.view.Mf_Tools;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kongtiaoapp.xxhj.R.id.rela_paint;

/**
 * A simple {@link Fragment} subclass.
 */
public class Protect_PaintFragment extends BaseFragment<Protect_PaintPresenter, Protect_PaintView> implements Protect_PaintView {
    @BindView(R.id.frame_up)
    FrameLayout frame_up;
    @BindView(R.id.frame_down)
    FrameLayout frame_down;
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
    private static Protect_PaintFragment instance = null;
    private double paintCount = 1;//图例个数

    public Protect_PaintFragment() {
    }

    public synchronized static Protect_PaintFragment getInstance() {
        if (instance == null) {
            instance = new Protect_PaintFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public Protect_PaintPresenter getPresenter() {
        return new Protect_PaintPresenter();
    }

    @Override
    protected View initView() {
        Log.i(TAG, "横竖屏切换的时候，走该方法么");
        return View.inflate(mActivity, R.layout.paint_wanneng, null);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Mf_Tools.setLayoutHeight(mActivity, frame_up, rela_loading, newConfig.orientation);//根据横竖屏切换控制视图展示
        Mf_Tools.setLayoutHeight(mActivity, frame_down, rela_loading1, newConfig.orientation);//根据横竖屏切换控制视图展示
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEvent(EventbusBean eventbusBean) {
        String isMonth = eventbusBean.getIsMonth();
        if (isMonth != null) {
            if (isMonth.equals("0")) {
                this.isMonth = false;

            } else if (isMonth.equals("1")) {
                this.isMonth = true;
            }
        }
        Log.i(TAG, "月==========" + isMonth + "====code===" + eventbusBean.getCode() + "====time=====" + App.sp.getDate());
        setPaint(eventbusBean);//设置图裂显示个数
    }

    private void setPaint(EventbusBean eventbusBean) {
        paintCount = eventbusBean.getPaintCount();
        date = eventbusBean.getDateStr();
        if (paintCount == 1) {
            type = eventbusBean.getCode();
            frame_down.setVisibility(View.GONE);
        } else if (paintCount == 2) {
            String[] split = eventbusBean.getCode().split("--");
            if (split != null) {
                type = split[0];
                type1 = split[1];
            }
            frame_down.setVisibility(View.VISIBLE);
        }
       /* if (!App.sp.getDate().equals("")) {
            if (isMonth == true) {
                String[] timeSplit = App.sp.getDate().split("-");
                date = timeSplit[0] + "-" + timeSplit[1];
            } else {
                date = App.sp.getDate();
            }

        }*/
        getDataForServers();
    }

    //更新数据
    @Override
    public void initData() {
        super.initData();
        if (ScreenUtils.isScreenOriatationPortrait(mActivity)) {
            Mf_Tools.setLayoutHeight(mActivity, frame_up, rela_loading, 1);//根据横竖屏切换控制视图展示   竖屏
            Mf_Tools.setLayoutHeight(mActivity, frame_down, rela_loading1, 1);//根据横竖屏切换控制视图展示
        } else {
            Mf_Tools.setLayoutHeight(mActivity, frame_up, rela_loading, 2);//根据横竖屏切换控制视图展示  横屏
            Mf_Tools.setLayoutHeight(mActivity, frame_down, rela_loading1, 2);//根据横竖屏切换控制视图展示
        }
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
    }

    private void getDataForServers() {
        List<String> list = new ArrayList<>();
        list.add(type);
        list.add(date);
        presenter.paint_one(mActivity, list);
    }

    private void getDataForServers1() {
        List<String> list = new ArrayList<>();
        list.add(type1);
        list.add(date);
        presenter.paint_two(mActivity, list);
    }

    private void setGraph(String[] titles) {
        Mf_Tools.setGraph(list, titles);
    }

    private void setGraph1(String[] titles) {
        Mf_Tools.setGraph(list1, titles);
    }

    @Override
    public void paint_succeed_one() {
        isFirst = false;
        if (paintCount == 2) {
            getDataForServers1();
        }
        Mf_Tools.hintAllView(list);
        rela_loading.removeAllViews();
    }

    @Override
    public void paint_succeed_two() {
        Mf_Tools.hintAllView(list1);
        rela_loading1.removeAllViews();
    }

    @Override
    public void paint_one(Object response) {
       /* if (!date.isEmpty() && !date.equals("")) {
            txt_notata.setText(date);
            txt_notata.setTextColor(getResources().getColor(R.color.theme_color));
            txt_notata.setVisibility(View.VISIBLE);
        }*/
        txt_notata.setText(date);
        txt_notata.setVisibility(View.VISIBLE);
        ChartDataBean lr_bean = (ChartDataBean) response;
        ChartDataBean.ResobjBean resobj = lr_bean.getResobj();
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
            if (resobj.getFlag().equals("Z") && isMonth == true) {
                Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), mActivity, rela_loading, true, resobj.getNowTime());

            } else if (resobj.getFlag().equals("S")) {
                Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), mActivity, rela_loading, false, "month", resobj.getNowTime());
            } else {
                Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), mActivity, rela_loading, false, resobj.getNowTime());

            }
        } catch (Exception e) {
            ToastUtils.showToast(mActivity, "图表数据有异常,请您稍后再尝试!");
            return;
        }
    }

    @Override
    public void paint_two(Object response) {
        String dates = "";
        dates = App.sp.getDate();
      /*  if (!dates.isEmpty() && !dates.equals("")) {
            txt_notata1.setText(dates);
            txt_notata1.setTextColor(getResources().getColor(R.color.theme_color));
        }*/
        txt_notata1.setText("");
        ChartDataBean lr_bean = (ChartDataBean) response;
        ChartDataBean.ResobjBean resobj = lr_bean.getResobj();
        List<ChartDataBean.ResobjBean.DataBean> listData = resobj.getData();
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
            if (resobj.getFlag().equals("Z") && isMonth == true) {
                Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), mActivity, rela_loading1, true, resobj.getNowTime());

            } else if (resobj.getFlag().equals("S") && isMonth == true) {
                Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), mActivity, rela_loading1, true, "month", resobj.getNowTime());

            } else {
                Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), mActivity, rela_loading1, false, resobj.getNowTime());
            }

        } catch (Exception e) {
            ToastUtils.showToast(mActivity, "图表数据有异常,请您稍后再尝试!");
            return;
        }
    }

    @Override
    public void paint_error_one() {
        Mf_Tools.showToast(mActivity, 1);
        Mf_Tools.hintAllView(list);
        txt_notata.setVisibility(View.INVISIBLE);
    }

    @Override
    public void paint_error_two() {
        Mf_Tools.showToast(mActivity, 1);
        Mf_Tools.hintAllView(list1);
        frame_down.setVisibility(View.GONE);
    }

    @Override
    public void paint_null_one() {
        txt_notata.setTextColor(getResources().getColor(R.color.red));
        txt_notata.setText(getResources().getString(R.string.no_data));
        Mf_Tools.hintAllView(list);
        txt_notata.setVisibility(View.VISIBLE);
    }

    @Override
    public void paint_null_two() {
        txt_notata1.setTextColor(getResources().getColor(R.color.red));
        txt_notata1.setText(getResources().getString(R.string.no_data));
        Mf_Tools.hintAllView(list1);
        frame_down.setVisibility(View.GONE);
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
