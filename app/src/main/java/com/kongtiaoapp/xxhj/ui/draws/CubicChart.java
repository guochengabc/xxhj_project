package com.kongtiaoapp.xxhj.ui.draws;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import com.kongtiaoapp.xxhj.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by xxhj_g on 2016/11/15.  曲线图
 */
public class CubicChart extends AbstractOneChart {

    private SimpleDateFormat format_month = null, format_day = null;

    /*title.length()   代表的是图例的个数*/
    @TargetApi(Build.VERSION_CODES.M)
    public GraphicalView execute(Context context, String[] titles, List<double[]> x, List<double[]> yvalues, String xtitile, int xMax, double maxY, double min_Y, boolean isMonth, long nowTime) {
        int[] colors = null;
        PointStyle[] styles = null;
        if (titles.length == 0) {
            colors = new int[]{};
            styles = new PointStyle[]{};
        }
        if (titles.length == 1) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one)};
            styles = new PointStyle[]{PointStyle.CIRCLE};
        } else if (titles.length == 2) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)};
            styles = new PointStyle[]{PointStyle.CIRCLE, PointStyle.DIAMOND,
            };
        } else if (titles.length == 3) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two), context.getResources().getColor(R.color.loading_three)};
            styles = new PointStyle[]{PointStyle.CIRCLE, PointStyle.DIAMOND,
                    PointStyle.TRIANGLE};
        } else if (titles.length == 4) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                    , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four)};
            styles = new PointStyle[]{PointStyle.CIRCLE, PointStyle.DIAMOND,
                    PointStyle.TRIANGLE, PointStyle.SQUARE};
        } else if (titles.length == 5) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                    , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four), context.getResources().getColor(R.color.loading_five)};
            styles = new PointStyle[]{PointStyle.CIRCLE, PointStyle.DIAMOND,
                    PointStyle.TRIANGLE, PointStyle.SQUARE, PointStyle.POINT};
        } else if (titles.length == 6) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                    , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four), context.getResources().getColor(R.color.loading_five)
                    , context.getResources().getColor(R.color.loading_six)};
            styles = new PointStyle[]{PointStyle.CIRCLE, PointStyle.DIAMOND,
                    PointStyle.TRIANGLE, PointStyle.SQUARE, PointStyle.POINT,PointStyle.X};
        } else if (titles.length == 7) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                    , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four), context.getResources().getColor(R.color.loading_five)
                    , context.getResources().getColor(R.color.loading_six), context.getResources().getColor(R.color.loading_seven)};
            styles = new PointStyle[]{PointStyle.CIRCLE, PointStyle.DIAMOND,
                    PointStyle.TRIANGLE, PointStyle.SQUARE, PointStyle.POINT,PointStyle.X,PointStyle.CIRCLE};
        } else if (titles.length == 8) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                    , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four), context.getResources().getColor(R.color.loading_five)
                    , context.getResources().getColor(R.color.loading_six), context.getResources().getColor(R.color.loading_seven), context.getResources().getColor(R.color.loading_eight)};
            styles = new PointStyle[]{PointStyle.CIRCLE, PointStyle.DIAMOND,
                    PointStyle.TRIANGLE, PointStyle.SQUARE, PointStyle.POINT,PointStyle.X,PointStyle.CIRCLE, PointStyle.DIAMOND};
        } else if (titles.length == 9) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                    , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four), context.getResources().getColor(R.color.loading_five)
                    , context.getResources().getColor(R.color.loading_six), context.getResources().getColor(R.color.loading_seven), context.getResources().getColor(R.color.loading_eight), context.getResources().getColor(R.color.loading_nine)};
            styles = new PointStyle[]{PointStyle.CIRCLE, PointStyle.DIAMOND,
                    PointStyle.TRIANGLE, PointStyle.SQUARE, PointStyle.POINT,PointStyle.X,PointStyle.CIRCLE, PointStyle.DIAMOND, PointStyle.SQUARE};
        } else if (titles.length == 10) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                    , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four), context.getResources().getColor(R.color.loading_five)
                    , context.getResources().getColor(R.color.loading_six), context.getResources().getColor(R.color.loading_seven), context.getResources().getColor(R.color.loading_eight),
                    context.getResources().getColor(R.color.loading_nine), context.getResources().getColor(R.color.loading_ten)};
            styles = new PointStyle[]{PointStyle.CIRCLE, PointStyle.DIAMOND,
                    PointStyle.TRIANGLE, PointStyle.SQUARE, PointStyle.POINT,PointStyle.X,PointStyle.CIRCLE, PointStyle.DIAMOND, PointStyle.SQUARE, PointStyle.POINT};
        }

        XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer xySeriesRenderer = ((XYSeriesRenderer) renderer.getSeriesRendererAt(i));
            xySeriesRenderer.setFillPoints(false);
            xySeriesRenderer.setLineWidth(2);
            xySeriesRenderer.setPointStyle(PointStyle.POINT);// 设置点的样式
        }
        double yMax=maxY+1;
        double minY=min_Y/1.1;
        int Max_Min = (int) (yMax - minY);//最大值与最小值的差
        if (Max_Min <= 21) {
            if (yMax<8){
                renderer.setPanLimits(new double[]{0, xMax, (int) minY,8});//设置拉动的范围  前2个参数是x轴的  后两个参数是y轴
            }else{
                renderer.setPanLimits(new double[]{0, xMax, (int) minY, (int)yMax});//设置拉动的范围  前2个参数是x轴的  后两个参数是y轴
            }

        } else {
            renderer.setPanLimits(new double[]{0, xMax, (int) minY - 2, (int) (yMax*1.2)});//设置拉动的范围  前2个参数是x轴的  后两个参数是y轴
        }

        //  renderer.setZoomLimits(new double[]{0, xMax/2, 0, yMax});//缩放的范围 参数同上
       /* setChartSettings(context, renderer, "主标题", "x轴标题", "百分比%", 0, xMax, 0, yMax,
                Color.LTGRAY, Color.LTGRAY);*/
        float xMin = 0.002f;
        int xVisivle = 11;
        if (isMonth) {
            if (format_month == null) {
                format_month = new SimpleDateFormat("MM-dd");
            }
            String month_day = format_month.format(new Date(nowTime));//读取的是月和日
            String[] date_split = month_day.split("-");
            float month_split = Float.parseFloat(date_split[0]);
            /*不是2月份进行分析图表的显示x图表的个数*/
            if (month_split != 2) {
                float month_date = Float.parseFloat(date_split[1]);
                if (month_date <= xVisivle) {
                    xMax = xVisivle;
                } else if (month_date > xVisivle && month_date <= 28) {
                    xMax = (int) month_date + 2;
                    xMin = xMax - xVisivle;
                } else if (month_date > 28 && month_date <= 30) {
                    xMax = 30;
                    xMin = xMax - xVisivle;
                }
                // xMax=30;
            } else {//是2月份的
                float month_date = Float.parseFloat(date_split[1]);
                if (month_date <= xVisivle) {
                    xMax = xVisivle;
                } else if (month_date > xVisivle && month_date <= 26) {
                    xMax = (int) month_date + 2;
                    xMin = xMax - xVisivle;
                } else if (month_date >= 126) {
                    xMax = 28;
                    xMin = xMax - xVisivle;
                }
                xMax = 28;
            }
        } else {
            if (format_day == null) {
                format_day = new SimpleDateFormat("HH");
            }
            xMax = 24;
            xVisivle = 12;
          /*  if (day_date <= xVisivle) {
                xMax = xVisivle;
            } else if (day_date > xVisivle && day_date <= 21) {
                xMax = (int) day_date + 2;
                xMin = xMax - xVisivle;
            } else if (day_date >= 21) {
                xMax = 23;
                xMin = xMax - xVisivle;
            }*/
          /*  xMin = 9;
            xMax = 16;*/
        }
        if (yMax < 8){
            if (minY >= 0) {

                    setChartSettings(context, renderer, "", xtitile, "", xMin, xMax, (int) minY,8,
                            Color.LTGRAY, Color.LTGRAY);


            }
            else {
                if (isMonth) {
                    setChartSettings(context, renderer, "", xtitile, "", xMin, xMax, minY - 2, 5,
                            Color.LTGRAY, Color.LTGRAY);
                } else {
                    setChartSettings(context, renderer, "", xtitile, "", xMin, xMax, minY - 2, 5,
                            Color.LTGRAY, Color.LTGRAY);
                }
            }
        }

        else {
            if (minY > 0) {
                if (isMonth) {
                    setChartSettings(context, renderer, "", xtitile, "", xMin, xMax, (int) (minY * (0.8)), (int) (yMax * (1.2f)),
                            Color.LTGRAY, Color.LTGRAY);
                } else {
                    if (Max_Min <= 21) {
                        setChartSettings(context, renderer, "", xtitile, "", xMin, xMax, (int) minY, (int) (yMax * (1.2f)),
                                Color.LTGRAY, Color.LTGRAY);
                    } else {
                        setChartSettings(context, renderer, "", xtitile, "", xMin, xMax, minY * (0.8), yMax * (1.2f),
                                Color.LTGRAY, Color.LTGRAY);
                    }

                }
            } else {
                setChartSettings(context, renderer, "", xtitile, "", xMin, xMax, (int) (minY - 2), (int) (yMax * (1.2f)),
                        Color.LTGRAY, Color.LTGRAY);
            }
        }

        XYMultipleSeriesDataset dataset = buildDataset(titles, x, yvalues);
        if (isMonth) {
            renderer.setXLabels(xVisivle + 1);//滑动的时候可以保持数连续
        } else {
            renderer.setXLabels(xVisivle + 1);
        }
        if (Max_Min <= 21) {
            if (maxY<20){
                if(maxY<8){
                    renderer.setYLabels(8);
                }else{
                    renderer.setYLabels((int)(yMax)-(int)(minY));
                }
            }else{
                renderer.setYLabels(8);
            }
        } else {
            renderer.setYLabels(8);
        }
        renderer.setBarSpacing(0);
        GraphicalView timeChartView = ChartFactory.getCubeLineChartView(context, dataset, renderer, 0.3f);
        return timeChartView;
    }
}
