package com.kongtiaoapp.xxhj.ui.draws;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.util.MathHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 桂飞 on 2016/11/4.
 */

public class SensorValuesChart extends AbstractOneChart {
    private Context context;

    public SensorValuesChart(Context context) {
        this.context = context;
    }

    private static final long HOUR = 3600 * 1000;

    private static final long DAY = HOUR * 24;

    private static final int HOURS = 24;

    public GraphicalView execute() {
        String[] titles = new String[]{"Inside", "Outside"};
           long now = Math.round(new Date().getTime() / DAY) * DAY;

        //  Log.i("fffffffffff", "现在的时间为:" + now+"时间为："+new Date().getTime());
        //获取当前时间
        SimpleDateFormat format = new SimpleDateFormat("H");
        int date = Integer.parseInt(format.format(new Date()));
        Log.i("fffffffffffffff", new Date() + "时间");
        List<Date[]> x = new ArrayList();
        for (int i = 0; i < titles.length; i++) {
            Date[] dates = new Date[HOURS];
            for (int j = 0; j < HOURS; j++) {
                dates[j] = new Date(now - (HOURS - j) * HOUR);
                //Log.i("fffffffffffffffff","FOR循环时间:+"+(now-(HOURS-j)*HOUR)+"时间");


            }
            x.add(dates);


        }
        List<double[]> values = new ArrayList<double[]>();

        values.add(new double[]{21.2, 21.5, 21.7, 21.5, 21.4, 21.4, 21.3, 21.1, 20.6, 20.3, 20.2,
                19.9, 19.7, 19.6, 19.9, 20.3, 20.6, 20.9, 21.2, 21.6, 21.9, 22.1, 21.7, 21.5});
        values.add(new double[]{1.9, 1.2, 0.9, 0.5, 0.1, 0.5, 0.6, MathHelper.NULL_VALUE,
                MathHelper.NULL_VALUE, 1.8, 0.3, 1.4, 3.4, 4.9, 7.0, 6.4, 3.4, 2.0, 1.5, 0.9, 0.5,
                MathHelper.NULL_VALUE, 1.9, 2.5, 4.3});
        int[] colors = new int[]{Color.GREEN, Color.BLUE};
        PointStyle[] styles = new PointStyle[]{PointStyle.CIRCLE, PointStyle.DIAMOND};
        XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);//设置图上的点为实心点
        }

        //   Log.i("fffffffffffffffff","最小刻度"+x.get(0)[0].getTime()+":"+"最大刻度"+x.get(0)[23].getTime());

        setChartSettings(context, renderer, "负载率折线图", "时间", "百分比%",
                x.get(0)[0].getTime(), x.get(0)[23].getTime(), 0, 60, Color.LTGRAY, Color.LTGRAY);
        renderer.setXLabels(10);//X轴显示的数量X轴显示的数量 X轴显示的数量
        renderer.setYLabels(10);
        GraphicalView timeChartView = ChartFactory.getTimeChartView(context, buildDateDataset(titles, x, values),

                renderer, "H:mm");
        return timeChartView;
    }
}



