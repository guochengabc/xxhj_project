package com.kongtiaoapp.xxhj.ui.draws.piechart;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;

import com.kongtiaoapp.xxhj.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

/**
 * Created by G_XXHJ on 2018/6/6.  饼状图
 */

public class PieChart extends AbstractDemoChart {


    /**
     * Returns the chart name.
     *
     * @return the chart name
     */
    public String getName() {
        return "Budget chart";
    }

    /**
     * Returns the chart description.
     *
     * @return the chart description
     */
    public String getDesc() {
        return "The budget per project for this year (pie chart)";
    }


    /**
     * Executes the chart demo.
     *
     * @param context the context
     * @return the built intent
     */
    public GraphicalView execute(Context context, String titles, double[] values, String[] name, ViewGroup group) {
        int[] colors = null;
        if (values.length == 0) {
            colors = new int[]{};
        }
        if (values.length == 1) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one)};

        } else if (values.length == 2) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)};

        } else if (values.length == 3) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two), context.getResources().getColor(R.color.loading_three)};

        } else if (values.length == 4) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                    , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four)};

        } else if (values.length == 5) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                    , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four), context.getResources().getColor(R.color.loading_five)};

        } else if (values.length == 6) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                    , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four), context.getResources().getColor(R.color.loading_five)
                    , context.getResources().getColor(R.color.loading_six)};
        } else if (values.length == 7) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                    , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four), context.getResources().getColor(R.color.loading_five)
                    , context.getResources().getColor(R.color.loading_six), context.getResources().getColor(R.color.loading_seven)};
        } else if (values.length == 8) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                    , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four), context.getResources().getColor(R.color.loading_five)
                    , context.getResources().getColor(R.color.loading_six), context.getResources().getColor(R.color.loading_seven), context.getResources().getColor(R.color.loading_eight)};
        } else if (values.length == 9) {
            colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                    , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four), context.getResources().getColor(R.color.loading_five)
                    , context.getResources().getColor(R.color.loading_six), context.getResources().getColor(R.color.loading_seven), context.getResources().getColor(R.color.loading_eight), context.getResources().getColor(R.color.loading_nine)};
        }
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, Color.BLUE);
        r.setGradientStop(0, Color.GREEN);
        r.setHighlighted(true);
        GraphicalView chartView = ChartFactory.getPieChartView(context,
                buildCategoryDataset(titles, values,name), renderer);
        group.addView(chartView);
        return chartView;
    }

}
