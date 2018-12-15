package com.kongtiaoapp.xxhj.ui.draws;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;

import com.kongtiaoapp.xxhj.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxhj_g on 2017/9/13.  横轴坐标可以自定义  而且同类的挨着 有对比
 */
public class BarChartCompare extends AbstractOneChart {

    private SimpleDateFormat format_month = null, format_day = null;

    /**
     * Execute graphical view.
     *
     * @param context the context
     * @param titles  the titles
     * @param x       the x
     * @param yvalues the yvalues
     * @param xtitile the xtitile
     * @param xMax    the x max
     * @param yMax    the y max
     * @param minY    the min y
     * @param isMonth the is month
     * @param nowTime the now time
     * @return the graphical view
     */
/*title.length()   代表的是图例的个数*/
    @TargetApi(Build.VERSION_CODES.M)
    public GraphicalView execute(Context context, String[] titles, List<String[]> x, List<double[]> yvalues, String xtitile, int xMax, double yMax, double minY, boolean isMonth, long nowTime, int sign) {
        int[] colors = null;
        if (titles.length == 0) {
            colors = new int[]{};
        }
        if (titles.length == 1) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one)};

        } else if (titles.length == 2) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)};

        } else if (titles.length == 3) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two), context.getResources().getColor(R.color.loading_three)};

        } else if (titles.length == 4) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                    , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four)};

        }
        List<double[]> yValue = new ArrayList<>();
        XYMultipleSeriesRenderer renderer = buildRenderer(colors);
        renderer.setDisplayChartValues(true);//是否显示y轴的值
        renderer.setXLabelsAlign(Paint.Align.LEFT);
        double[] dy1 = new double[22];
        double[] dy2 = new double[22];
        int xSizeSpace = 3;
        int xPaintSpace = 3;

        for (int i = 0; i < yvalues.size(); i++) {
            dy1[3 + (xPaintSpace * i)] = yvalues.get(0)[i];
            renderer.addXTextLabel(3.6 + (xSizeSpace * i), x.get(0)[i]);
        }
        yValue.add(dy1);

        for (int i = 0; i < yvalues.size(); i++) {
            dy2[xPaintSpace * i + 16] = yvalues.get(1)[i];
            renderer.addXTextLabel(xSizeSpace * i + 17.4, x.get(1)[i]);
        }
        yValue.add(dy2);
        yvalues.clear();
        yvalues.addAll(yValue);


        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer xySeriesRenderer = ((XYSeriesRenderer) renderer.getSeriesRendererAt(i));
            xySeriesRenderer.setDisplayChartValues(false);
        }
        renderer.setPanLimits(new double[]{0, xMax, minY, yMax});//设置拉动的范围  前2个参数是x轴的  后两个参数是轴
        //  renderer.setZoomLimits(new double[]{0, xMax/2, 0, yMax});//缩放的范围 参数同上
       /* setChartSettings(context, renderer, "主标题", "x轴标题", "百分比%", 0, xMax, 0, yMax,
                Color.LTGRAY, Color.LTGRAY);*/
        float xMin = 0.002f;
        if (format_month == null) {
            format_month = new SimpleDateFormat("MM-dd");
        }
            /*不是2月份进行分析图表的显示x图表的个数*/
        if (format_day == null) {
            format_day = new SimpleDateFormat("HH");
        }

        if (yMax < 8) {
            if (minY >= 0) {

                setChartSettings(context, renderer, "", xtitile, "", xMin, xMax, 0, 8,
                        Color.LTGRAY, Color.LTGRAY);
            } else {
                setChartSettings(context, renderer, "", xtitile, "", xMin, xMax, minY - 2, 8,
                        Color.LTGRAY, Color.LTGRAY);
            }
        } else {
            if (minY >= 0) {
                setChartSettings(context, renderer, "", xtitile, "", xMin, xMax, minY * (0.8), yMax * (1.2f),
                        Color.LTGRAY, Color.LTGRAY);

            } else {
                setChartSettings(context, renderer, "", xtitile, "", xMin, xMax, minY - 2, yMax * (1.2f),
                        Color.LTGRAY, Color.LTGRAY);
            }
        }
        renderer.setXLabels(1);
        if (yMax > 0) {
            renderer.setChartValuesTextSize(24);
        }
        renderer.setYLabels(5);
        renderer.setBarSpacing(0);
        renderer.setBarWidth(35);
        GraphicalView barChartView = ChartFactory.getBarChartView(context, buildBarDataset(titles, yvalues), renderer,
                BarChart.Type.DEFAULT);
        return barChartView;
    }
}
