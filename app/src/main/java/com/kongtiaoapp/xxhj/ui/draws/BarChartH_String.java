package com.kongtiaoapp.xxhj.ui.draws;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import com.kongtiaoapp.xxhj.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by xxhj_g on 2017/9/13.  横轴坐标可以自定义
 */
public class BarChartH_String extends AbstractOneChart {

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
    public GraphicalView execute(Context context, String[] titles, List<String> x, List<double[]> yvalues, String xtitile, int xMax, double yMax, double minY, boolean isMonth, long nowTime) {
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
        } else if (titles.length == 5) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                    , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four), context.getResources().getColor(R.color.loading_five)};
        } else if (titles.length == 6) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                    , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four), context.getResources().getColor(R.color.loading_five)
                    , context.getResources().getColor(R.color.loading_six)};
        } else if (titles.length == 7) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                    , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four), context.getResources().getColor(R.color.loading_five)
                    , context.getResources().getColor(R.color.loading_six), context.getResources().getColor(R.color.loading_seven)};
        } else if (titles.length == 8) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                    , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four), context.getResources().getColor(R.color.loading_five)
                    , context.getResources().getColor(R.color.loading_six), context.getResources().getColor(R.color.loading_seven), context.getResources().getColor(R.color.loading_eight)};
        } else if (titles.length == 9) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                    , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four), context.getResources().getColor(R.color.loading_five)
                    , context.getResources().getColor(R.color.loading_six), context.getResources().getColor(R.color.loading_seven), context.getResources().getColor(R.color.loading_eight), context.getResources().getColor(R.color.loading_nine)};
        } else if (titles.length == 10) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                    , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four), context.getResources().getColor(R.color.loading_five)
                    , context.getResources().getColor(R.color.loading_six), context.getResources().getColor(R.color.loading_seven), context.getResources().getColor(R.color.loading_eight),
                    context.getResources().getColor(R.color.loading_nine), context.getResources().getColor(R.color.loading_ten)};
        }

        XYMultipleSeriesRenderer renderer = buildRenderer(colors);
        renderer.setDisplayChartValues(true);//是否显示y轴的值
        double[] dy = new double[11 * x.size() * yvalues.size()];
        int xSizeSpace = 11;
        int xPaintSpace = 11;
        for (int j = 0; j < x.size(); j++) {
            renderer.addXTextLabel(xSizeSpace * j + 6, x.get(j).toString());
            for (int i = 0; i < yvalues.size(); i++) {
                dy[xPaintSpace * j + 5] = yvalues.get(i)[j];
            }
        }

        yvalues.clear();
        yvalues.add(dy);
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
        String month_day = format_month.format(new Date(nowTime));//读取的是月和日
        /*不是2月份进行分析图表的显示x图表的个数*/
        if (format_day == null) {
            format_day = new SimpleDateFormat("HH");
        }

        if (yMax < 8) {
            if (minY >= 0) {
                setChartSettings(context, renderer, "", xtitile, "", xMin, xMax, 0, 8,
                        Color.LTGRAY, Color.LTGRAY);
            } else {
                setChartSettings(context, renderer, "", xtitile, "", xMin, xMax, minY, 8,
                        Color.LTGRAY, Color.LTGRAY);
            }
        } else {
            if (minY >= 0) {
                if (isMonth) {
                    setChartSettings(context, renderer, "", xtitile, "", xMin, xMax, minY * (0.8), yMax * (1.2f),
                            Color.LTGRAY, Color.LTGRAY);
                } else {

                    setChartSettings(context, renderer, "", xtitile, "", xMin, xMax, minY * (0.8), yMax * (1.2f),
                            Color.LTGRAY, Color.LTGRAY);
                }
            } else {
                setChartSettings(context, renderer, "", xtitile, "", xMin, xMax, minY - 2, yMax * (1.2f),
                        Color.LTGRAY, Color.LTGRAY);
            }
        }
        if (isMonth) {
            renderer.setXLabels(1);
        } else {
            renderer.setXLabels(1);
        }
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