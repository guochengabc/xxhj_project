package com.kongtiaoapp.xxhj.activites;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.GetEnergyInfo;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.RuningRecordDetailPresenter;
import com.kongtiaoapp.xxhj.mvp.view.RunningRecordDetailView;
import com.kongtiaoapp.xxhj.ui.view.ChartMarkerView;
import com.kongtiaoapp.xxhj.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Luoye on 2016-9-30.
 * 说明:运行记录详情页面
 */
public class RunningRecordDetailActivity extends BaseActivity<RuningRecordDetailPresenter,RunningRecordDetailView> implements  RunningRecordDetailView{
    @BindView(R.id.tv_comment)
    TextView tvComment;
    @BindView(R.id.chart2)
    LineChart chart;
    @BindView(R.id.tv_pic1)
    ImageView tvPic1;
    @BindView(R.id.tv_nenghao1)
    TextView tvNenghao1;
    @BindView(R.id.tv_pic2)
    ImageView tvPic2;
    @BindView(R.id.tv_nenghao2)
    TextView tvNenghao2;
    @BindView(R.id.tv_pic3)
    ImageView tvPic3;
    @BindView(R.id.tv_nenghao3)
    TextView tvNenghao3;
    @BindView(R.id.tv_pic4)
    ImageView tvPic4;
    @BindView(R.id.tv_nenghao4)
    TextView tvNenghao4;
    private String projectId;
    private String date;
    private ArrayList<Entry> values1, values2;

    @Override
    protected int initContentView() {
        projectId = getIntent().getStringExtra("projectId");
        String time = getIntent().getStringExtra("data");
        if (!TextUtils.isEmpty(time)) {
            date = TimeUtils.getYMR(time);
        }
        return R.layout.activity_running_detail;
    }

    @Override
    protected void initView() {
        showChart(chart, Color.WHITE, "");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        getDataForServers();
    }

    @Override
    protected RuningRecordDetailPresenter getPresenter() {
        return new RuningRecordDetailPresenter();
    }

    @OnClick({R.id.iv_back})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void getDataForServers() {
        List<String> list=new ArrayList<>();
        list.add(projectId);
        list.add(date);
        presenter.onResume(this,list);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    // 设置显示的样式
    private void showChart(LineChart lineChart, int color, String str) {
        lineChart.setDrawBorders(false);  //是否在折线图上添加边框


        lineChart.setDescription(str);// 数据描述
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
//        lineChart.setNoDataTextDescription("暂时没有数据");
        lineChart.setNoDataText("暂时没有数据");


        lineChart.setDrawGridBackground(false); // 是否显示表格颜色
        lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度
        ChartMarkerView mv = new ChartMarkerView(mContext, R.layout.chart_marker_view);  //点击的时候显示的View

        lineChart.setMarkerView(mv);

        // enable touch gestures
        lineChart.setTouchEnabled(true); // 设置是否可以触摸

        // enable scaling and dragging
        lineChart.setDragEnabled(true);// 是否可以拖拽
        lineChart.setScaleEnabled(true);// 是否可以缩放

        // 如果禁用,扩展可以在x轴和y轴分别完成
        lineChart.setPinchZoom(false);//

        lineChart.setBackgroundColor(color);// 设置背景

        // add data
//        lineChart.setData(lineData); // 设置数据


        // get the legend (only possible after setting data)
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的


        // 修改传奇……
        mLegend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        mLegend.setForm(Legend.LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(6f);// 字体
        mLegend.setTextColor(Color.WHITE);// 颜色
//      mLegend.setTypeface(mTf);// 字体
        mLegend.setForm(Legend.LegendForm.LINE);//集的形式/形状传奇形式
//        mLegend.setTypeface(mTfLight);//设置一个特定的字体的标签

        XAxis xAxis = lineChart.getXAxis();
//        xAxis.setTypeface(mTfLight);
        xAxis.setTextSize(11f);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//X轴的位置
        xAxis.setTextColor(Color.RED);
        xAxis.setAxisLineColor(Color.CYAN); //线颜色
        xAxis.setAxisLineWidth(2);
        xAxis.setSpaceBetweenLabels(1);//设置x轴标签之间的空间字符数，默认是4个。
        xAxis.setAvoidFirstLastClipping(true);//如果设置为true，图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘。
        ; //线的 宽度
        xAxis.setEnabled(true);  //设置为true如果该组件应该启用(应该是),*如果不是假的。如果禁用,没有这个组件将它们分开。*默认:true
//        xAxis.enableGridDashedLine(10f, 10f, 10f); //这是设置虚线
//        xAxis.setEnabled(true); //是否显示X轴
//        xAxis.setDrawGridLines(false);//设置为true来启用这个轴的网格线。
//        xAxis.setDrawAxisLine(false); //设置为true是否应该画线和轴。
//        xAxis.setAxisMaxValue(1000);
//        xAxis.setAxisMinValue(-1000);
        xAxis.setLabelsToSkip(14);//默认是10


        //设置右边y轴的样式
        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);

//

        YAxis leftAxis = lineChart.getAxisLeft();
//        rightAxis.setTypeface(mTfLight);
        leftAxis.setTextColor(Color.RED);
        leftAxis.setAxisLineColor(Color.CYAN); //线颜色
        leftAxis.setAxisLineWidth(2); //线的 宽度

        leftAxis.enableGridDashedLine(10f, 10f, 10f); //这是设置虚线
//        leftAxis.setAxisMaxValue(900);
//        leftAxis.setAxisMinValue(-200);
//        leftAxis.setDrawGridLines(false); //设置为true来启用这个轴的网格线。
//        leftAxis.setDrawZeroLine(false); //设置为true的零位线不管天气*网格线是启用或不是。默认值:false
//        leftAxis.setGranularityEnabled(false);  //启用/禁用粒度控制轴值的间隔。如果启用,轴*间隔不得低于一定的粒度。默认值:false
        xAxis.setEnabled(true);  //设置为true如果该组件应该启用(应该是),*如果不是假的。如果禁用,没有这个组件将它们分开。*默认:true


        lineChart.animateX(2500); // 立即执行的动画,x轴
    }

    /**
     * @param //数据数量
     * @param //数据值
     * @return
     */
    private LineData setData(LineChart mLineChart, ArrayList<Entry> values, ArrayList<Entry> values2) {
//        //X轴数据
        ArrayList<String> x = new ArrayList<String>();
//        //第一条线的Y轴数据
//        ArrayList<Entry> values = new ArrayList<Entry>();
//        //第二条线的Y轴数据
//        ArrayList<Entry> values2 = new ArrayList<Entry>();
        //轴的假数据
        for (int i = 0; i < 24; i++) {
            // x轴显示的数据
            x.add(String.valueOf(i + 0.0));
            x.add(String.valueOf(i + 0.2));
            x.add(String.valueOf(i + 0.4));
            x.add(String.valueOf(i + 0.6));
            x.add(String.valueOf(i + 0.8));

        }
//        //造第一条线Y轴的假数据
//        for (int i = 0; i < count; i++) {
//
//            float val = (float) (Math.random() * range) + 0.2f;
//            values.add(new Entry(val, i));
//
//        }
//
//        //造第二条线Y轴的假数据
//        for (int i = 0; i < count; i++) {
//
//            float val = (float) (Math.random() * range) + 5;
//            values2.add(new Entry(val, i));
//        }
        //第一条线的 数据容器
        LineDataSet set1;
        //第二条线的数据容器
        LineDataSet set2;
//
        if (mLineChart.getData() != null &&
                mLineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
//            set1.setValues(values);
            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(values, "第一条线");

//            // set the include_material_consumtion to be drawn like this "- - - - - -"
//            set1.enableDashedLine(10f, 5f, 0f);
//            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(Color.BLACK);//设置曲线的颜色
//            set1.setCircleColor(Color.BLACK);//圆点颜色

            set1.setLineWidth(1f); //设置线的宽度
//            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(false);////填充颜色
//            set1.setFormLineWidth(1f);
//            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
//            set1.setFormSize(15.f);
            //设置先上的圆点是空心的
            set1.setDrawCircleHole(true);
            set1.setDrawCircles(false);
            // 改变折线样式，用曲线。
            set1.setDrawCubic(true);
            // 默认是直线
            // 曲线的平滑度，值越大越平滑。
            set1.setCubicIntensity(0.1f);

            set2 = new LineDataSet(values2, "第二条线 2");

//            set1.enableDashedLine(10f, 5f, 0f);
//            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set2.setColor(Color.RED);//设置曲线的颜色
//            set1.setCircleColor(Color.TRANSPARENT);//圆点颜色

            set2.setLineWidth(1f); //设置线的宽度
//            set2.setCircleRadius(3f);
            set2.setDrawCircleHole(false);
            set2.setValueTextSize(9f);
            set2.setDrawFilled(false);////填充颜色
            set2.setDrawCircles(true);

            //设置先上的圆点是空心的
            set2.setDrawCircleHole(true);
            //设置设置是否显示圆点
            set2.setDrawCircles(false);
// 改变折线样式，用曲线。
            set2.setDrawCubic(true);
            // 默认是直线
            // 曲线的平滑度，值越大越平滑。
            set2.setCubicIntensity(0.1f);

            // 填充曲线下方的区域，红色，半透明。
            // mLineDataSet.setDrawFilled(true);
            // mLineDataSet.setFillAlpha(128);
            // mLineDataSet.setFillColor(Color.RED);
            ArrayList<LineDataSet> mLineDataSets = new ArrayList<LineDataSet>();
            mLineDataSets.add(set1);
            mLineDataSets.add(set2);
//            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
//            dataSets.add(set1); // add the datasets
//            dataSets.add(set2); // add the datasets

            // create a data object with the datasets
            LineData data = new LineData(x, mLineDataSets);

            // set data
//            mLineChart.setData(data);
            return data;
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (values1 != null) {
            values1.clear();
            values1 = null;
        }
        if (values2 != null) {
            values2.clear();
            values2 = null;
        }
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void setList(Object response) {
        GetEnergyInfo info = (GetEnergyInfo) response;
        if (info.getResobj() != null) {
            if (info.getResobj().getSysEnergyConsumption() != null && info.getResobj().getSysEnergyConsumption().size() > 0) {
                List<GetEnergyInfo.ResobjBean.SysEnergyConsumptionBean> list = info.getResobj().getSysEnergyConsumption();
                values1 = new ArrayList<Entry>();
                values2 = new ArrayList<Entry>();
                for (int i = 0; i < list.size() && i < 120; i++) {
                    float val1;
                    float val2;
                    if (!TextUtils.isEmpty(list.get(i).getValue())) {
                        val1 = (float) (Float.parseFloat(list.get(i).getValue()));
                        val2 = (float) (Float.parseFloat(list.get(i).getSave()));
                    } else {
                        val1 = (float) (Float.parseFloat("0.0"));
                        val2 = (float) (Float.parseFloat("0.0"));
                    }
                    values1.add(new Entry(val1, i));
                    values2.add(new Entry(val2, i));
                }
                if (values1 != null && values2 != null) {
                    showChart(chart, Color.WHITE, "");
                    chart.setData(setData(chart, values1, values2));
                }
            }


            if (info.getResobj().getUnitEnergyConsumption() != null) {
                //机组能耗
                tvNenghao1.setText("机组能耗:" + info.getResobj().getUnitEnergyConsumption().getValue() + "Kw,优化后能耗:" + info.getResobj().getUnitEnergyConsumption().getSave() + "Kw");
            }
            if (info.getResobj().getCoPumpEnergyConsumption() != null) {
                //冷冻水泵能耗
                tvNenghao2.setText("冷冻水泵能耗:" + info.getResobj().getCoPumpEnergyConsumption().getValue() + "Kw,优化后能耗:" + info.getResobj().getCoPumpEnergyConsumption().getSave() + "Kw");
            }
            if (info.getResobj().getChPumpEnergyConsumption() != null) {
                //冷却水泵能耗
                tvNenghao3.setText("冷却水泵能耗:" + info.getResobj().getChPumpEnergyConsumption().getValue() + "Kw,优化后能耗:" + info.getResobj().getChPumpEnergyConsumption().getSave() + "Kw");
            }
            if (info.getResobj().getTowerEnergyConsumption() != null) {
                //冷却塔能耗
                tvNenghao4.setText("冷却塔能耗:" + info.getResobj().getTowerEnergyConsumption().getValue() + "Kw,优化后能耗:" + info.getResobj().getTowerEnergyConsumption().getSave() + "Kw");
            }


        }
    }
}
