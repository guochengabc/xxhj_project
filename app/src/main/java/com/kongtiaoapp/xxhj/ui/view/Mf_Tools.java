package com.kongtiaoapp.xxhj.ui.view;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.ui.draws.AverageTemperatureChart;
import com.kongtiaoapp.xxhj.ui.draws.BarChart;
import com.kongtiaoapp.xxhj.ui.draws.BarChartCompare;
import com.kongtiaoapp.xxhj.ui.draws.BarChartH_String;
import com.kongtiaoapp.xxhj.ui.draws.CubicChart;
import com.kongtiaoapp.xxhj.ui.draws.piechart.PieChart;
import com.kongtiaoapp.xxhj.utils.DensityUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.achartengine.GraphicalView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.   设置图表显示风格的一些相关操作
 */
public class Mf_Tools {
    public static void setGraph(List<TextView> list, String[] titles) {
        if (titles.length == 1) {
            list.get(0).setVisibility(View.VISIBLE);
            list.get(0).setText(titles[0]);
        } else if (titles.length == 2) {
            list.get(0).setVisibility(View.VISIBLE);
            list.get(1).setVisibility(View.VISIBLE);
            list.get(0).setText(titles[0]);
            list.get(1).setText(titles[1]);
        } else if (titles.length == 3) {
            list.get(0).setVisibility(View.VISIBLE);
            list.get(1).setVisibility(View.VISIBLE);
            list.get(2).setVisibility(View.VISIBLE);
            list.get(0).setText(titles[0]);
            list.get(1).setText(titles[1]);
            list.get(2).setText(titles[2]);
        } else if (titles.length == 4) {
            list.get(0).setVisibility(View.VISIBLE);
            list.get(1).setVisibility(View.VISIBLE);
            list.get(2).setVisibility(View.VISIBLE);
            list.get(3).setVisibility(View.VISIBLE);
            list.get(0).setText(titles[0]);
            list.get(1).setText(titles[1]);
            list.get(2).setText(titles[2]);
            list.get(3).setText(titles[3]);
        } else if (titles.length == 5) {
            list.get(0).setVisibility(View.VISIBLE);
            list.get(1).setVisibility(View.VISIBLE);
            list.get(2).setVisibility(View.VISIBLE);
            list.get(3).setVisibility(View.VISIBLE);
            list.get(4).setVisibility(View.VISIBLE);
            list.get(0).setText(titles[0]);
            list.get(1).setText(titles[1]);
            list.get(2).setText(titles[2]);
            list.get(3).setText(titles[3]);
            list.get(4).setText(titles[4]);
        } else if (titles.length == 6) {
            list.get(0).setVisibility(View.VISIBLE);
            list.get(1).setVisibility(View.VISIBLE);
            list.get(2).setVisibility(View.VISIBLE);
            list.get(3).setVisibility(View.VISIBLE);
            list.get(4).setVisibility(View.VISIBLE);
            list.get(5).setVisibility(View.VISIBLE);
            list.get(0).setText(titles[0]);
            list.get(1).setText(titles[1]);
            list.get(2).setText(titles[2]);
            list.get(3).setText(titles[3]);
            list.get(4).setText(titles[4]);
            list.get(5).setText(titles[5]);
        } else if (titles.length == 7) {
            list.get(0).setVisibility(View.VISIBLE);
            list.get(1).setVisibility(View.VISIBLE);
            list.get(2).setVisibility(View.VISIBLE);
            list.get(3).setVisibility(View.VISIBLE);
            list.get(4).setVisibility(View.VISIBLE);
            list.get(5).setVisibility(View.VISIBLE);
            list.get(6).setVisibility(View.VISIBLE);
            list.get(0).setText(titles[0]);
            list.get(1).setText(titles[1]);
            list.get(2).setText(titles[2]);
            list.get(3).setText(titles[3]);
            list.get(4).setText(titles[4]);
            list.get(5).setText(titles[5]);
            list.get(6).setText(titles[6]);
        } else if (titles.length == 8) {
            list.get(0).setVisibility(View.VISIBLE);
            list.get(1).setVisibility(View.VISIBLE);
            list.get(2).setVisibility(View.VISIBLE);
            list.get(3).setVisibility(View.VISIBLE);
            list.get(4).setVisibility(View.VISIBLE);
            list.get(5).setVisibility(View.VISIBLE);
            list.get(6).setVisibility(View.VISIBLE);
            list.get(7).setVisibility(View.VISIBLE);
            list.get(0).setText(titles[0]);
            list.get(1).setText(titles[1]);
            list.get(2).setText(titles[2]);
            list.get(3).setText(titles[3]);
            list.get(4).setText(titles[4]);
            list.get(5).setText(titles[5]);
            list.get(6).setText(titles[6]);
            list.get(7).setText(titles[7]);
        } else if (titles.length == 9) {
            list.get(0).setVisibility(View.VISIBLE);
            list.get(1).setVisibility(View.VISIBLE);
            list.get(2).setVisibility(View.VISIBLE);
            list.get(3).setVisibility(View.VISIBLE);
            list.get(4).setVisibility(View.VISIBLE);
            list.get(5).setVisibility(View.VISIBLE);
            list.get(6).setVisibility(View.VISIBLE);
            list.get(7).setVisibility(View.VISIBLE);
            list.get(8).setVisibility(View.VISIBLE);
            list.get(0).setText(titles[0]);
            list.get(1).setText(titles[1]);
            list.get(2).setText(titles[2]);
            list.get(3).setText(titles[3]);
            list.get(4).setText(titles[4]);
            list.get(5).setText(titles[5]);
            list.get(6).setText(titles[6]);
            list.get(7).setText(titles[7]);
            list.get(8).setText(titles[8]);
        }
    }

    public static void hintAllView(List<TextView> list) {
        for (TextView textView : list) {
            textView.setVisibility(View.GONE);
        }
    }

    public static void showToast(Context context, int type) {
        if (type == 0) {
            ToastUtils.showToast(context, "服务器请求失败");
        } else if (type == 1) {
            ToastUtils.showToast(context, "服务器请求错误");
        }

    }

    /**
     * 设置图例，分日和月份进行处理  曲线图
     */
    public static void setData(String[] titles, List<double[]> listsY, List<double[]> listX, int maxX, double maxY, double minY, Activity mActivity, RelativeLayout rela_loading, boolean isMonth, long nowTime) {
        GraphicalView executes = null;
        if (isMonth) {
            BarChart sensor = new BarChart();
            executes = sensor.execute(mActivity, titles, listX, listsY, mActivity.getResources().getString(R.string.ismonth), maxX, maxY, minY, isMonth, nowTime);
        } else if (isMonth == false) {
            CubicChart sensor = new CubicChart();
            executes = sensor.execute(mActivity, titles, listX, listsY, mActivity.getResources().getString(R.string.isday), maxX, maxY, minY, isMonth, nowTime);
        }
        rela_loading.removeView(executes);
        if (executes != null) {
            rela_loading.addView(executes);
        }

    }

    /**
     * 设置图例，月数据显示的是曲线图
     */
    public static void setData(String[] titles, List<double[]> listsY, List<double[]> listX, int maxX, double maxY, double minY, Activity mActivity, RelativeLayout rela_loading, boolean isMonth, String type, long nowTime) {
        GraphicalView executes = null;
        CubicChart sensor = new CubicChart();
        if (isMonth) {
            executes = sensor.execute(mActivity, titles, listX, listsY, mActivity.getResources().getString(R.string.ismonth), maxX, maxY, minY, isMonth, nowTime);
        } else if (isMonth == false) {
            executes = sensor.execute(mActivity, titles, listX, listsY, mActivity.getResources().getString(R.string.isday), maxX, maxY, minY, isMonth, nowTime);
        }
        rela_loading.removeView(executes);
        if (executes != null) {
            rela_loading.addView(executes);
        }

    }
    /*设置图例，分日和月份进行处理*/

    public static void setData(String[] titles, List<double[]> listsY, List<double[]> listX, int maxX, double maxY, double minY, Activity mActivity, RelativeLayout rela_loading, String type, long nowTime) {
        //type  0代表曲线图 1代表柱状图
        GraphicalView executes = null;
        if (type.equals("0")) {
            AverageTemperatureChart sensor = new AverageTemperatureChart();
            executes = sensor.execute(mActivity, titles, listX, listsY, mActivity.getResources().getString(R.string.ismonth), maxX, maxY, minY, true, nowTime);
        } else {
            BarChart sensor = new BarChart();
            executes = sensor.execute(mActivity, titles, listX, listsY, mActivity.getResources().getString(R.string.ismonth), maxX, maxY, minY, true, nowTime);
        }
        if (executes != null) {
            rela_loading.addView(executes);
        }


    }
    /*设置图例，柱状图 单个对比*/

    public static void setData(String[] titles, List<double[]> listsY, List<String> listX, int maxX, double maxY, double minY, Activity mActivity, RelativeLayout rela_loading, long nowTime) {
        BarChartH_String sensor = new BarChartH_String();
        GraphicalView executes = sensor.execute(mActivity, titles, listX, listsY, mActivity.getResources().getString(R.string.ismonth), maxX, maxY, minY, false, nowTime);

        if (executes != null) {
            rela_loading.addView(executes);
        }


    } /*设置图例，柱状图 多个对比*/

    public static void setData(String[] titles, List<double[]> listsY, List<String[]> listX, int maxX, double maxY, double minY, Activity mActivity, RelativeLayout rela_loading, long nowTime, int sign) {
        BarChartCompare sensor = new BarChartCompare();
        GraphicalView executes = sensor.execute(mActivity, titles, listX, listsY, mActivity.getResources().getString(R.string.ismonth), maxX, maxY, minY, false, nowTime, sign);
        if (executes != null) {
            rela_loading.addView(executes);
        }


    }

    /**
     * 更新展示层界面的高度  rela_loading:展示视图层 newConfig=1代表竖屏   2代表横屏
     */
    public static void setLayoutHeight(Activity mActivity, ViewGroup frame, ViewGroup rela_loading, int newConfig) {
        ViewGroup.LayoutParams params = rela_loading.getLayoutParams();
        ViewGroup.LayoutParams frameParam = frame.getLayoutParams();
        if (newConfig == 1) {
            frameParam.height = DensityUtils.dp2px(mActivity, 300);
            frame.setLayoutParams(frameParam);
            frame.invalidate();
            params.height = DensityUtils.dp2px(mActivity, 300);
            rela_loading.setLayoutParams(params);
            rela_loading.invalidate();
        } else {
            frameParam.height = DensityUtils.dp2px(mActivity, 330);
            frame.setLayoutParams(frameParam);
            frame.invalidate();
            params.height = DensityUtils.dp2px(mActivity, 330);
            rela_loading.setLayoutParams(params);
            rela_loading.invalidate();
        }
    }

    public static void setLayoutMatchHeight(Activity mActivity, ViewGroup frame, ViewGroup rela_loading, int newConfig) {
        ViewGroup.LayoutParams params = rela_loading.getLayoutParams();
        ViewGroup.LayoutParams frameParam = frame.getLayoutParams();
        if (newConfig == 1) {
            frameParam.height = DensityUtils.dp2px(mActivity, 330);
            frame.setLayoutParams(frameParam);
            frame.invalidate();
            params.height = DensityUtils.dp2px(mActivity, 330);
            rela_loading.setLayoutParams(params);
            rela_loading.invalidate();
        } else {
            frameParam.height = DensityUtils.dp2px(mActivity, 380);
            frame.setLayoutParams(frameParam);
            frame.invalidate();
            params.height = DensityUtils.dp2px(mActivity, 380);
            rela_loading.setLayoutParams(params);
            rela_loading.invalidate();
        }
    }

    public static void setEvalLayoutHeight(Activity mActivity, ViewGroup frame, ViewGroup rela_loading, int newConfig) {
        ViewGroup.LayoutParams params = rela_loading.getLayoutParams();
        ViewGroup.LayoutParams frameParam = frame.getLayoutParams();
        if (newConfig == 1) {
            frameParam.height = DensityUtils.dp2px(mActivity, 150);
            frame.setLayoutParams(frameParam);
            frame.invalidate();
            params.height = DensityUtils.dp2px(mActivity, 150);
            rela_loading.setLayoutParams(params);
            rela_loading.invalidate();
        } else {
            frameParam.height = DensityUtils.dp2px(mActivity, 210);
            frame.setLayoutParams(frameParam);
            frame.invalidate();
            params.height = DensityUtils.dp2px(mActivity, 210);
            rela_loading.setLayoutParams(params);
            rela_loading.invalidate();
        }
    }

    /**
     * 对饼状图进行相关设置饼状图，做出一个中间层，方便后期调试和修改 2018-6-6
     */
    public static void setDataPieChart(Context context, String title, double[] values, String[] name, ViewGroup rela_loading) {
        rela_loading.removeAllViews();
        PieChart chart = new PieChart();
        chart.execute(context, title, values, name, rela_loading);
    }
}
