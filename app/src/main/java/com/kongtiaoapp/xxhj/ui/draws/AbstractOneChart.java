/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kongtiaoapp.xxhj.ui.draws;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;

import com.kongtiaoapp.xxhj.R;

import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.MultipleCategorySeries;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.xutils.common.util.DensityUtil;

import java.util.Date;
import java.util.List;

/**
 * An abstract class for the demo charts to extend. It contains some methods for
 * building datasets and renderers.
 */
public abstract class AbstractOneChart {

    /**
     * Builds an XY multiple dataset using the provided values.
     *
     * @param titles  the series titles
     * @param xValues the values for the X axis
     * @param yValues the values for the Y axis
     * @return the XY multiple dataset
     */
    protected XYMultipleSeriesDataset buildDataset(String[] titles, List<double[]> xValues,
                                                   List<double[]> yValues) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        addXYSeries(dataset, titles, xValues, yValues, 0);
        return dataset;
    }

    public void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles, List<double[]> xValues,
                            List<double[]> yValues, int scale) {
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            XYSeries series = new XYSeries(titles[i], scale);
            double[] xV = xValues.get(i);
            double[] yV = yValues.get(i);
            int seriesLength = xV.length;
            for (int k = 0; k < seriesLength; k++) {
                series.add(xV[k], yV[k]);
            }
            dataset.addSeries(series);
        }
    }

    /**
     * Builds an XY multiple series renderer.
     *
     * @param colors the series rendering colors
     * @param styles the series point styles
     * @return the XY multiple series renderers
     */
    protected XYMultipleSeriesRenderer buildRenderer(int[] colors, PointStyle[] styles) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        setRenderer(renderer, colors, styles);
        return renderer;
    }

    protected void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors, PointStyle[] styles) {
        Log.i("fffffffffff","====DensityUtil.getScreenHeight()==="+DensityUtil.getScreenHeight());
        if (DensityUtil.getScreenHeight()<1700){
            renderer.setAxisTitleTextSize(16);
            renderer.setChartTitleTextSize(16);
            renderer.setLabelsTextSize(16);
            renderer.setLegendTextSize(16);
        }else{
            renderer.setAxisTitleTextSize(20);
            renderer.setChartTitleTextSize(20);
            renderer.setLabelsTextSize(20);
            renderer.setLegendTextSize(20);
        }

        renderer.setPointSize(2f);
        renderer.setMargins(new int[]{20, 30, 15, 20});
        int length = colors.length;
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(colors[i]);
            r.setPointStyle(styles[i]);
            renderer.addSeriesRenderer(r);
        }
    }

    protected XYMultipleSeriesRenderer buildRenderer(int[] colors) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        setRenderer(renderer, colors);
        return renderer;
    }

    protected void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors) {
        renderer.setAxisTitleTextSize(20);
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsTextSize(20);
        renderer.setLegendTextSize(20);
        renderer.setPointSize(2f);
        renderer.setMargins(new int[]{20, 30, 15, 20});
        int length = colors.length;
        if (length>0){
            for (int i = 0; i < length; i++) {
                XYSeriesRenderer r = new XYSeriesRenderer();
                r.setColor(colors[i]);
                renderer.addSeriesRenderer(r);
            }
        }

    }

    /**
     * Sets a few of the series renderer settings.
     *
     * @param renderer    the renderer to set the properties to
     * @param title       the chart title
     * @param xTitle      the title for the X axis
     * @param yTitle      the title for the Y axis
     * @param xMin        the minimum value on the X axis
     * @param xMax        the maximum value on the X axis
     * @param yMin        the minimum value on the Y axis
     * @param yMax        the maximum value on the Y axis
     * @param axesColor   the axes color
     * @param labelsColor the labels color
     */
    @TargetApi(Build.VERSION_CODES.M)
    protected void setChartSettings(Context context, XYMultipleSeriesRenderer renderer, String title, String xTitle,
                                    String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor,
                                    int labelsColor) {
        renderer.setChartTitle(title);
        renderer.setXTitle(xTitle);
        renderer.setYTitle(yTitle);
        renderer.setChartTitleTextSize(0);//设置图表参数标题的大小
        renderer.setLabelsTextSize(35);
        renderer.setTextTypeface(Typeface.SANS_SERIF);
        renderer.setAxisTitleTextSize(35);
        //  renderer.setLegendTextSize(35);  设置贞布局字体的大小
        renderer.setPointSize(5);
        renderer.setXAxisMin(xMin);
        renderer.setXAxisMax(xMax);
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
        renderer.setAxesColor(axesColor);
        renderer.setLabelsColor(labelsColor);
        renderer.setShowLegend(false);
        renderer.setApplyBackgroundColor(true);
        renderer.setFitLegend(true);//适应屏幕
        renderer.setShowGrid(true);
      //  renderer.setShowGridY(false);
        renderer.setGridColor(context.getResources().getColor(R.color.color_invest_view));
        //  renderer.setZoomButtonsVisible(false);//设置缩放按钮显示还是隐藏  如果写了这个话,下面的失效
        renderer.setZoomEnabled(false, false);
        renderer.setBackgroundColor(context.getResources().getColor(R.color.ffffff));
        renderer.setMarginsColor(context.getResources().getColor(R.color.ffffff));//设置空白区颜色
        renderer.setXLabelsColor(context.getResources().getColor(R.color.a80000000));
        renderer.setYLabelsColor(0, context.getResources().getColor(R.color.a80000000));
        renderer.setXLabelsAlign(Paint.Align.CENTER);
        renderer.setYLabelsAlign(Paint.Align.RIGHT);
        renderer.setMargins(new int[]{50, 100, 50, 60});//分别对应上左下右
    }

    /**
     * Builds an XY multiple time dataset using the provided values.
     *
     * @param titles  the series titles
     * @param xValues the values for the X axis
     * @param yValues the values for the Y axis
     * @return the XY multiple time dataset
     */
    protected XYMultipleSeriesDataset buildDateDataset(String[] titles, List<Date[]> xValues,
                                                       List<double[]> yValues) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            TimeSeries series = new TimeSeries(titles[i]);
            Date[] xV = xValues.get(i);
            double[] yV = yValues.get(i);
            int seriesLength = xV.length;
            for (int k = 0; k < seriesLength; k++) {
                series.add(xV[k], yV[k]);
            }
            dataset.addSeries(series);
        }
        return dataset;
    }

    /**
     * Builds a category series using the provided values.
     */
    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        for (double value : values) {
            series.add("Project " + ++k, value);
        }

        return series;
    }

    /**
     * Builds a multiple category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected MultipleCategorySeries buildMultipleCategoryDataset(String title,
                                                                  List<String[]> titles, List<double[]> values) {
        MultipleCategorySeries series = new MultipleCategorySeries(title);
        int k = 0;
        for (double[] value : values) {
            series.add(2007 + k + "", titles.get(k), value);
            k++;
        }
        return series;
    }

    /**
     * Builds a category renderer to use the provided colors.
     *
     * @param colors the colors
     * @return the category renderer
     */
    protected DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a bar multiple series dataset using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the XY multiple bar dataset
     */
    protected XYMultipleSeriesDataset buildBarDataset(String[] titles, List<double[]> values) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            CategorySeries series = new CategorySeries(titles[i]);
            double[] v = values.get(i);
            int seriesLength = v.length;
            for (int k = 0; k < seriesLength; k++) {
                series.add(v[k]);
            }
            dataset.addSeries(series.toXYSeries());
        }
        return dataset;
    }

    /**
     * Builds a bar multiple series renderer to use the provided colors.
     *
     * @param colors the series renderers colors
     * @return the bar multiple series renderer
     */
    protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setAxisTitleTextSize(16);
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        int length = colors.length;
        for (int i = 0; i < length; i++) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(colors[i]);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

}
