package com.kongtiaoapp.xxhj.activites;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.ZhenDuan;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.MyZhenduanPresenter;
import com.kongtiaoapp.xxhj.mvp.view.MyZhenduanView;
import com.kongtiaoapp.xxhj.utils.BaseTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyZhenduanActivity extends BaseActivity<MyZhenduanPresenter,MyZhenduanView> implements MyZhenduanView{
    @BindView(R.id.tv_title)
    TextView tvTitle;
    /*@BindView(R.id.tv_right_btn)
    TextView tvRightBtn;*/
    @BindView(R.id.tv_project_name)
    TextView tvProjectName;
    @BindView(R.id.tv_jianzhu_name)
    TextView tvJianzhuName;
    @BindView(R.id.tv_jianzhu_mianji)
    TextView tvJianzhuMianji;
    @BindView(R.id.iv_zhizhen)
    ImageView ivZhizhen;
    /*@BindView(R.id.chart1)
    LineChart mLineChart;*/
    @BindView(R.id.tv_qujian_1)
    TextView tvQujian1;
    @BindView(R.id.tv_qujian_2)
    TextView tvQujian2;
    @BindView(R.id.tv_qujian_3)
    TextView tvQujian3;
    @BindView(R.id.iv_change5)
    ImageView ivChange5;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.ll_3)
    LinearLayout ll3;
    /*@BindView(R.id.chart2)
    LineChart chart2;*/
    @BindView(R.id.biao1)
    TextView biao1;
    @BindView(R.id.tv_cop1_nmb)
    TextView tvCop1Nmb;
    @BindView(R.id.tv_qujian_4)
    TextView tvQujian4;
    @BindView(R.id.ll_4)
    LinearLayout ll4;
    @BindView(R.id.tv_qujian_5)
    TextView tvQujian5;
    @BindView(R.id.ll_5)
    LinearLayout ll5;
    @BindView(R.id.tv_cop_content)
    TextView tvCopContent;
    @BindView(R.id.biao2)
    TextView biao2;
    @BindView(R.id.iv_biao2_zhizhen)
    ImageView ivBiao2Zhizhen;
    @BindView(R.id.tv_biao2_nmb)
    TextView tvBiao2Nmb;
    @BindView(R.id.tv_biao2_qu_1)
    TextView tvBiao2Qu1;
    @BindView(R.id.ll_biao2_1)
    LinearLayout llBiao21;
    @BindView(R.id.tv_biao2_qu_2)
    TextView tvBiao2Qu2;
    @BindView(R.id.ll_biao2_2)
    LinearLayout llBiao22;
    @BindView(R.id.tv_biao2_qu_3)
    TextView tvBiao2Qu3;
    @BindView(R.id.ll_biao2_3)
    LinearLayout llBiao23;
    @BindView(R.id.tv_biao2_qu_4)
    TextView tvBiao2Qu4;
    @BindView(R.id.ll_biao2_4)
    LinearLayout llBiao24;
    @BindView(R.id.tv_biao2_qu_5)
    TextView tvBiao2Qu5;
    @BindView(R.id.ll_biao2_5)
    LinearLayout llBiao25;
    @BindView(R.id.tv_biao2_content)
    TextView tvBiao2Content;
    @BindView(R.id.rl_shiji)
    RelativeLayout rlShiji;
    @BindView(R.id.tv_shiji)
    TextView tvShiji;
    @BindView(R.id.tv_heli)
    TextView tvHeli;
    @BindView(R.id.tv_jindu_content)
    TextView tvJDcontent;

    @BindView(R.id.iv_biao1)
    ImageView iv_biao1;
    @BindView(R.id.iv_biao2)
    ImageView iv_biao2;

    @BindView(R.id.diag_result)
    LinearLayout llDiagResult;

    private ArrayList<Entry> values2, values3, values4;
    private ArrayList<Entry> values1;

    private String diagId;
    private String projectId;
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        //  RegisterReceiver();
        return R.layout.activity_my_zhenduan_activity;
    }

    @Override
    protected void initView() {
        projectId = App.sp.getProjectId();

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        width = BaseTools.getWindowsWidth(this) / 2;
        getDataForServers();
        setView();
    }

    @Override
    protected MyZhenduanPresenter getPresenter() {
        return new MyZhenduanPresenter();
    }

    private void setView() {
        //        showChart(mLineChart, Color.WHITE, "");
        //        showChart(chart2, Color.WHITE, "");
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, width);
        //        layoutParams.setMargins(0,-BaseTools.dip2px(activity,10),0,0);
        //        layoutParams.setMargins(width/2-BaseTools.dip2px(activity,15),BaseTools.dip2px(activity,20),0,0);
        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(width, width);
        iv_biao1.setLayoutParams(layoutParams);
        iv_biao1.setScaleType(ImageView.ScaleType.CENTER);
        //        iv_biao1.setImageResource(R.mipmap.biaopan_bg);
        ivZhizhen.setLayoutParams(layoutParams1);
        iv_biao2.setLayoutParams(layoutParams);
        iv_biao2.setScaleType(ImageView.ScaleType.CENTER);
        ivBiao2Zhizhen.setLayoutParams(layoutParams1);
        //        ivZhizhen.setImageBitmap(zhuan(210));
        //        ivBiao2Zhizhen.setImageBitmap(zhuan(35));
    }

    private void getDataForServers() {
        /*showChart(mLineChart, Color.WHITE, "");
        showChart(chart2, Color.WHITE, "");*/

        if (TextUtils.isEmpty(projectId))
            return;

        setView();
        List<String> list=new ArrayList<>();
        list.add(projectId);
        list.add(diagId);
       presenter.onResume(this,list);
    }
    private Matrix matrix = new Matrix();

    private Bitmap zhuan(int progress) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.zhizhen);
        int w = bitmap.getWidth();
        // 设置旋转角度
        matrix.setRotate(progress, 0.5f, 0.5f);
        // 重新绘制Bitmap
        //        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, w, w, matrix, true);
        return bitmap;
    }

    /*// 设置显示的样式
    private void showChart(LineChart lineChart, int color, String str) {
        lineChart.setDrawBorders(false);  //是否在折线图上添加边框


        lineChart.setDescription(str);// 数据描述
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
//        lineChart.setNoDataTextDescription("暂时没有数据");
        lineChart.setNoDataText("暂时没有数据");


        lineChart.setDrawGridBackground(false); // 是否显示表格颜色
        lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度
        ChartMarkerView mv = new ChartMarkerView(this, R.layout.chart_marker_view);  //点击的时候显示的View

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
        YAxis yAxisRight = mLineChart.getAxisRight();
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
    }*/

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

    @OnClick({R.id.iv_change5})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_change5:
                startActivity(new Intent(this, ProjectInfoActivity.class).putExtra("type", 2));
                break;
            default:
                break;
        }
    }

   /* private myReceiver receiver;

    *//**
     * 注册广播接受者
     *//*
    private void RegisterReceiver() {
        // 实例化过滤器；
        IntentFilter intentFilter = new IntentFilter();
        // 添加过滤的Action值；
        intentFilter.addAction(ReceiverAction.ZHENDUAN);
        intentFilter.addAction(ReceiverAction.PROJECT_SELECT);

        // 实例化广播监听器；
        receiver = new myReceiver();

        // 将广播监听器和过滤器注册在一起；
        App.application.registerReceiver(receiver, intentFilter);

    }*/


   /* */

    /**
     * 广播接收者
     *//*
    public class myReceiver extends BroadcastReceiver {

        // 重写onReceive方法，该方法的实体为，接收到广播后的执行代码；
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ReceiverAction.ZHENDUAN)) {
                diagId = intent.getStringExtra("diagId");
                getDataForServers();
            }
            if (action.equals(ReceiverAction.PROJECT_SELECT)) {
                projectId = intent.getStringExtra(ConstantValue.PROJECTID);
                diagId = "";
                getDataForServers();
            }
        }
    }*/
    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
      /*  if (receiver.getAbortBroadcast()) {
            App.application.unregisterReceiver(receiver);
        }*/


        if (values1 != null) {
            values1.clear();
            values1 = null;
        }
        if (values2 != null) {
            values2.clear();
            values2 = null;
        }
        if (values3 != null) {
            values3.clear();
            values3 = null;
        }
        if (values4 != null) {
            values4.clear();
            values4 = null;
        }
    }

    @Override
    public void setList(Object response) {
        ZhenDuan info = (ZhenDuan) response;
        if (info.getResobj() != null) {
            llDiagResult.setVisibility(View.VISIBLE);
            tvProjectName.setText(info.getResobj().getProjectName());
            tvJianzhuName.setText(info.getResobj().getBuilding());
            tvJianzhuMianji.setText(info.getResobj().getCoolingArea());
            //系统cop
            if (info.getResobj().getSysCOP() != null) {
                String s = info.getResobj().getSysCOP().getValueInterval();
                String[] strings = s.split(",");
                tvQujian1.setText("<" + strings[0]);
                tvQujian2.setText(strings[0] + "~" + strings[1]);
                tvQujian3.setText(strings[1] + "~" + strings[2]);
                tvQujian4.setText(strings[2] + "~" + strings[3]);
                tvQujian5.setText(">" + strings[3]);
                tvCop1Nmb.setText("" + info.getResobj().getSysCOP().getResult());
                tvCopContent.setText(info.getResobj().getSysCOP().getDescription());
                float now_numb = Float.parseFloat(info.getResobj().getSysCOP().getResult());
                float min = Float.parseFloat(strings[0]);
                float max = Float.parseFloat(strings[3]);
                int delt = (int) ((now_numb - min) / (max - min));
                if (now_numb < min) {
                    ivZhizhen.setImageBitmap(zhuan(330));
                } else if (now_numb > max) {
                    ivZhizhen.setImageBitmap(zhuan(210));
                } else {
                    ivZhizhen.setImageBitmap(zhuan(delt));
                }
            } else {
                tvQujian1.setText("区间1");
                tvQujian2.setText("区间2");
                tvQujian3.setText("区间3");
                tvQujian4.setText("区间4");
                tvQujian5.setText("区间5");
                tvCop1Nmb.setText("无数据");
                tvCopContent.setText("没有相应数据");
                ivZhizhen.setImageBitmap(zhuan(1));
            }

            //系统单位制冷量
            if (info.getResobj().getSysCapacity() != null) {

                String s = info.getResobj().getSysCapacity().getValueInterval();
                String[] strings = s.split(",");
                tvBiao2Qu1.setText("<" + strings[0]);
                tvBiao2Qu2.setText(strings[0] + "~" + strings[1]);
                tvBiao2Qu3.setText(strings[1] + "~" + strings[2]);
                tvBiao2Qu4.setText(strings[2] + "~" + strings[3]);
                tvBiao2Qu5.setText(">" + strings[3]);
                tvBiao2Nmb.setText("" + info.getResobj().getSysCapacity().getResult());
                tvBiao2Content.setText(info.getResobj().getSysCapacity().getDescription());
                float now_numb = Float.parseFloat(info.getResobj().getSysCapacity().getResult());
                float min = Float.parseFloat(strings[0]);
                float max = Float.parseFloat(strings[3]);
                int delt = (int) ((now_numb - min) / (max - min));
                if (now_numb < min) {
                    ivBiao2Zhizhen.setImageBitmap(zhuan(330));
                } else if (now_numb > max) {
                    ivBiao2Zhizhen.setImageBitmap(zhuan(210));
                } else {
                    ivBiao2Zhizhen.setImageBitmap(zhuan(delt));
                }
            } else {
                tvBiao2Qu1.setText("区间1");
                tvBiao2Qu2.setText("区间2");
                tvBiao2Qu3.setText("区间3");
                tvBiao2Qu4.setText("区间4");

                tvBiao2Qu5.setText("区间5");
                tvBiao2Nmb.setText("无数据");
                tvBiao2Content.setText("没有相应数据");
                ivBiao2Zhizhen.setImageBitmap(zhuan(1));
            }
            //系统设计耗电输冷比
            if (info.getResobj().getSysEcr() != null) {
                ZhenDuan.ResobjBean.SysEcrBean sysEcrBean = info.getResobj().getSysEcr();
                //合理值
                tvHeli.setText(sysEcrBean.getValueInterval());
                //实际值
                tvShiji.setText(sysEcrBean.getResult());
                //描述
                tvJDcontent.setText(sysEcrBean.getDescription());
                try {
                    float percent = Float.parseFloat(sysEcrBean.getResult()) / (Float.parseFloat(sysEcrBean.getValueInterval()) * 2);
                    int winWidth = BaseTools.getWindowsWidth(this);
                    int margin = (int) ((winWidth - BaseTools.dip2px(this, 80)) * percent);
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(BaseTools.dip2px(this, 80), BaseTools.dip2px(this, 90));//定义一个LayoutParams
                    layoutParams.setMargins(margin, 0, 0, 0);
                    rlShiji.setLayoutParams(layoutParams);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //合理值
                tvHeli.setText("0");
                //实际值
                tvShiji.setText("0");
                //描述
                tvJDcontent.setText("没有相应数据");
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(BaseTools.dip2px(this, 80), BaseTools.dip2px(this, 90));//定义一个LayoutParams
                layoutParams.setMargins(0, 0, 0, 0);
                rlShiji.setLayoutParams(layoutParams);
            }
                        /*//系统总能耗
                        if (info.getResobj().getSysEnergyConsumption() != null) {
                            List<ZhenDuan.ResobjBean.SysEnergyConsumptionBean> list1 = info.getResobj().getSysEnergyConsumption();
                            values1 = new ArrayList<Entry>();
                            for (int i = 0; i < list1.size() && i < 120; i++) {
                                float val;
                                if (!TextUtils.isEmpty(list1.get(i).getValue())) {
                                    val = (float) (Float.parseFloat(list1.get(i).getValue()));
                                } else {
                                    val = (float) (Float.parseFloat("0.0"));
                                }
                                values1.add(new Entry(val, i));
                            }
                        }
                        //系统制冷量
                        if (info.getResobj().getSysCoolingCapacity() != null) {
                            List<ZhenDuan.ResobjBean.SysCoolingCapacityBean> list2 = info.getResobj().getSysCoolingCapacity();
                            values2 = new ArrayList<Entry>();
                            for (int i = 0; i < list2.size() && i < 120; i++) {
                                float val;
                                if (!TextUtils.isEmpty(list2.get(i).getValue())) {
                                    val = (float) (Float.parseFloat(list2.get(i).getValue()));
                                } else {
                                    val = (float) (Float.parseFloat("0.0"));
                                }
//                                float val = (float) (Float.parseFloat(list2.get(i).getValue()));
                                values2.add(new Entry(val, i));
                            }
                        }
                        if (values1 != null && values2 != null) {
                            showChart(mLineChart, Color.WHITE, "");//第一个曲线图
                            mLineChart.setData(setData(mLineChart, values1, values2));
                        }


                        //系统实时COP
                        if (info.getResobj().getSysRunningCop() != null) {
                            List<ZhenDuan.ResobjBean.SysRunningCopBean> list3 = info.getResobj().getSysRunningCop();
                            values3 = new ArrayList<Entry>();
                            for (int i = 0; i < list3.size() && i < 120; i++) {
                                float val = (float) (Float.parseFloat(list3.get(i).getValue()));
                                values3.add(new Entry(val, i));
                            }
                        }
                        //机组实时COP
                        if (info.getResobj().getUnitRunningCop() != null) {
                            List<ZhenDuan.ResobjBean.UnitRunningCopBean> list4 = info.getResobj().getUnitRunningCop();
                            values4 = new ArrayList<Entry>();
                            for (int i = 0; i < list4.size() && i < 120; i++) {
                                float val = (float) (Float.parseFloat(list4.get(i).getValue()));
                                values4.add(new Entry(val, i));
                            }
                        }
                        if (values3 != null && values4 != null) {
                            showChart(chart2, Color.WHITE, "");//第二个曲线图
                            chart2.setData(setData(chart2, values3, values4));
                        }*/


            setView();
        }

    }

    @Override
    public void setText(Object text) {

    }
}
