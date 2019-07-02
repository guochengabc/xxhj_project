package com.kongtiaoapp.xxhj.workorder.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.UserListActivity;
import com.kongtiaoapp.xxhj.workorder.adapter.WorkOrderSatisficAdapter;
import com.kongtiaoapp.xxhj.bean.EngineerRecordFormBean;
import com.kongtiaoapp.xxhj.bean.RepairFormSecondBean;
import com.kongtiaoapp.xxhj.bean.WorkOrderStasficBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.WorkOrderSatisficPresenter;
import com.kongtiaoapp.xxhj.mvp.view.WorkOrderSatisficView;
import com.kongtiaoapp.xxhj.ui.address.ScreenUtils;
import com.kongtiaoapp.xxhj.ui.view.ClickTextView;
import com.kongtiaoapp.xxhj.ui.view.FullGridView;
import com.kongtiaoapp.xxhj.ui.view.Mf_Tools;
import com.kongtiaoapp.xxhj.utils.DateUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kongtiaoapp.xxhj.R.id.rela_paint;

/**
 * 工单统计
 */
public class WorkOrderSatisficActivity extends BaseActivity<WorkOrderSatisficPresenter, WorkOrderSatisficView> implements WorkOrderSatisficView {
    @BindView(R.id.fgl_repairCount)
    FullGridView fgl_repairCount;//显示今日报单的数量
    @BindView(R.id.txt_engineer)
    ClickTextView txt_engineer;
    //工程师工单维修报修单
    @BindView(R.id.frame_up)
    FrameLayout frame_up;
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

    //    报修模块
    @BindView(R.id.txt_repair)
    TextView txt_repair;//报修员

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
    private List<TextView> list, list1;
    private long nowTime = 0;
    private String userId = "";

    private String nextOrLastEngineerMonth = "1";//0代表上一个月  1代表本月   2代表下一个月
    private String nextOrLastRepairMonth = "1";//0代表上一个月  1代表本月   2代表下一个月
    private String dateMonth = "";
    private String dateMonthDown = "";
    private String repairUserId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_work_order_satisfic;
    }

    @Override
    protected void initView() {
        userId = App.sp.getUid();
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        if (ScreenUtils.isScreenOriatationPortrait(this)) {
            Mf_Tools.setLayoutHeight(this, frame_up, rela_loading, 1);//根据横竖屏切换控制视图展示   竖屏
        } else {
            Mf_Tools.setLayoutHeight(this, frame_up, rela_loading, 2);//根据横竖屏切换控制视图展示  横屏
        }
        setListTextView();//把textview放到list里面
        getServiceData("", "", userId);
    }

    private void getServiceData(String nowTime, String dispat, String userId) {
        List<String> list = new ArrayList<>();
        if (!nowTime.equals("")) {
            list.add(nowTime);
        }
        list.add(dispat);
        list.add(userId);
        presenter.onResume(this, list);
    }

    /**
     * 获取上个月或者下个月报修单的统计
     */
    private void getRepairData(String nowTime, String repairUserIds) {
        List<String> list = new ArrayList<>();
        list.add(nowTime);
        if (!repairUserIds.equals("")) {
            list.add(repairUserIds);
        }
        presenter.getRepairRecordForm(this, list);
    }

    private void setListTextView() {
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

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected WorkOrderSatisficPresenter getPresenter() {
        return new WorkOrderSatisficPresenter();
    }

    @OnClick({R.id.iv_back, R.id.img_lastMonth, R.id.img_nextMonth, R.id.txt_engineer
            , R.id.img_lastMonth2, R.id.img_nextMonth2, R.id.txt_repair})
    public void onCLick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.img_lastMonth://查询上一个月
                if (!dateMonth.equals("")) {
                    dateMonth = DateUtils.getLastMonth(dateMonth);
                    getEngineerRecordForm(dateMonth, "", userId);
                }
                break;
            case R.id.img_nextMonth://查询下一个月
                if (!dateMonth.equals("")) {
                    dateMonth = DateUtils.getNextMonth(dateMonth);
                    getEngineerRecordForm(dateMonth, "", userId);
                }

                break;
            case R.id.txt_engineer://查看工程师
                intent = new Intent(this, UserListActivity.class);
                intent.putExtra("type", 4);
                intent.putExtra("which_type", "CCB");
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.img_lastMonth2://第二张图表上一个月
                if (!dateMonthDown.equals("")) {
                    dateMonthDown = DateUtils.getLastMonth(dateMonthDown);
                    getRepairData(dateMonthDown, repairUserId);
                }
                break;
            case R.id.img_nextMonth2://第二张图表下一个月
                if (!dateMonthDown.equals("")) {
                    dateMonthDown = DateUtils.getNextMonth(dateMonthDown);
                    getRepairData(dateMonthDown, repairUserId);
                }
                break;
            case R.id.txt_repair:
                intent = new Intent(this, UserListActivity.class);
                intent.putExtra("type", 5);
                intent.putExtra("which_type", "CCC");
                startActivityForResult(intent, REQUEST_SECOND);
                break;
            default:

                break;

        }


    }

    @Override
    public void setText(Object data) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == REQUEST_CODE) {
                txt_engineer.setText(data.getStringExtra("user"));
                userId = data.getStringExtra("uid");
                /**/
                getEngineerRecordForm(DateUtils.unixTimestampToDateMonth(nowTime), "", userId);
            } else if (requestCode == REQUEST_SECOND) {
                txt_repair.setText(data.getStringExtra("user"));
                repairUserId = data.getStringExtra("uid");
                getRepairData(dateMonthDown, repairUserId);
            }
        }
    }

    private void getEngineerRecordForm(String date, String disp, String userId) {
        List<String> list = new ArrayList<>();
        if (!date.equals("")) {
            list.add(date);
        }
        list.add("2");
        list.add(userId);
        presenter.getEngineerRecordForm(this, list);
    }

    @Override
    public void getStasticData(Object data) {
        //第一个图表
        WorkOrderStasficBean bean = (WorkOrderStasficBean) data;
        WorkOrderStasficBean.ResobjBean resobj = bean.getResobj();
        if (resobj.getNowTime() != 0) {
            nowTime = resobj.getNowTime();
            dateMonth = DateUtils.unixTimestampToDateMonth(nowTime);
            dateMonthDown = dateMonth;
            txt_notata.setText("" + DateUtils.unixTimestampToDateMonth(nowTime));
            txt_notata.setTextColor(getResources().getColor(R.color.theme_color));
            txt_notata.setVisibility(View.VISIBLE);
            txt_notata1.setText("" + DateUtils.unixTimestampToDateMonth(nowTime));
            txt_notata1.setTextColor(getResources().getColor(R.color.theme_color));
            txt_notata1.setVisibility(View.VISIBLE);
        }
        WorkOrderStasficBean.ResobjBean.WorkDataBean workData = resobj.getWorkData();
        List<WorkOrderStasficBean.ResobjBean.WorkDataBean.DataBeanX> listData = workData.getData();
        WorkOrderSatisficAdapter adapter = new WorkOrderSatisficAdapter(listData, this);
        fgl_repairCount.setAdapter(adapter);
        WorkOrderStasficBean.ResobjBean.EngineerBean engineer = resobj.getEngineer();
        List<WorkOrderStasficBean.ResobjBean.EngineerBean.DataBean> listEngineer = engineer.getData();
        if (listEngineer == null) {
            return;
        }
        List<double[]> listX = new ArrayList<>();
        List<double[]> listY = new ArrayList<>();
        String[] titles = new String[listEngineer.size()];
        for (int i = 0; i < listEngineer.size(); i++) {
            titles[i] = listEngineer.get(i).getDispStateName();
            listX.add(listEngineer.get(i).getTime());
            listY.add(listEngineer.get(i).getValues());
        }
        setGraph(titles);//设置图列的个数

        try {
            Mf_Tools.setData(titles, listY, listX, engineer.getMaxX(), engineer.getMaxY(), engineer.getMinY(), this, rela_loading, "1", resobj.getNowTime());
        } catch (Exception e) {
            ToastUtils.showToast(this, "图表数据有异常,请您稍后再尝试!");
            Log.i(TAG, "图表有问题：" + e.getMessage());
            return;
        }

        //第二个图表
        List<WorkOrderStasficBean.ResobjBean.RepairBean> listRepair = resobj.getRepair();

        if (listRepair == null) {
            return;
        }
        List<double[]> listX1 = new ArrayList<>();
        List<double[]> listY1 = new ArrayList<>();
        String[] titles1 = new String[listRepair.size()];
        for (int i = 0; i < listRepair.size(); i++) {
            titles1[i] = listRepair.get(i).getDispStateName();
            listX1.add(listRepair.get(i).getTime());
            listY1.add(listRepair.get(i).getValues());
        }
        setGraph(titles1);//设置图列的个数
        WorkOrderStasficBean.ResobjBean.RepairBean repairBean = listRepair.get(0);
        try {
            Mf_Tools.setData(titles1, listY1, listX1, repairBean.getMaxX(), repairBean.getMaxY(), repairBean.getMinY(), this, rela_loading1, "1", resobj.getNowTime());
        } catch (Exception e) {
            ToastUtils.showToast(this, "图表数据有异常,请您稍后再尝试!");
            Log.i(TAG, "图表有问题：" + e.getMessage());
            return;
        }
    }

    /*工单统计点击响应成功反馈*/
    @Override
    public void getSucceedPaint(Object data) {
        Mf_Tools.hintAllView(list);
        rela_loading.removeAllViews();
    }

    /*报修点击反馈*/
    @Override
    public void getSecondSucceedPaint(Object data) {
        Mf_Tools.hintAllView(list1);
        rela_loading1.removeAllViews();
    }

    private void setGraph(String[] titles) {
        Mf_Tools.setGraph(list, titles);
    }

    private void setGraph1(String[] titles) {
        Mf_Tools.setGraph(list1, titles);
    }

    @Override
    public void getListNull(Object noData) {

    }

    /*对工程师进行统计*/
    @Override
    public void engineerRecordForm(Object data) {
        EngineerRecordFormBean bean = (EngineerRecordFormBean) data;
        EngineerRecordFormBean.ResobjBean resobj = bean.getResobj();
        if (resobj.getNowTime() != 0) {
            nowTime = resobj.getNowTime();
            dateMonth = DateUtils.unixTimestampToDateMonth(nowTime);
            txt_notata.setText("" + DateUtils.unixTimestampToDateMonth(nowTime));
            txt_notata.setTextColor(getResources().getColor(R.color.theme_color));
            txt_notata.setVisibility(View.VISIBLE);
        }
        EngineerRecordFormBean.ResobjBean.EngineerBean engineer = resobj.getEngineer();
        List<EngineerRecordFormBean.ResobjBean.EngineerBean.DataBean> listEngineer = engineer.getData();
        if (listEngineer == null) {
            return;
        }
        List<double[]> listX = new ArrayList<>();
        List<double[]> listY = new ArrayList<>();
        String[] titles = new String[listEngineer.size()];
        for (int i = 0; i < listEngineer.size(); i++) {
            titles[i] = listEngineer.get(i).getDispStateName();
            listX.add(listEngineer.get(i).getTime());
            listY.add(listEngineer.get(i).getValues());
        }
        setGraph(titles);//设置图列的个数

        try {
            Mf_Tools.setData(titles, listY, listX, engineer.getMaxX(), engineer.getMaxY(), engineer.getMinY(), this, rela_loading, "1", resobj.getNowTime());
        } catch (Exception e) {
            ToastUtils.showToast(this, "图表数据有异常,请您稍后再尝试!");
            Log.i(TAG, "图表有问题：" + e.getMessage());
            return;
        }
    }

    /*工程师返回空40005*/
    @Override
    public void engineerRecordFormListNull() {
        txt_notata.setText(getString(R.string.no_data));
    }

    /*工程师返回错误数据*/
    @Override
    public void engineerRecordFormError() {
        txt_notata.setText(getString(R.string.paint_error));
    }

    /*对报修员*/
    @Override
    public void repairForm(Object data) {
        RepairFormSecondBean bean = (RepairFormSecondBean) data;
        RepairFormSecondBean.ResobjBean resobj = bean.getResobj();

        List<RepairFormSecondBean.ResobjBean.RepairBean> listRepair = resobj.getRepair();
        if (resobj.getNowTime() != 0) {
            nowTime = resobj.getNowTime();
            dateMonthDown = DateUtils.unixTimestampToDateMonth(nowTime);
            txt_notata1.setText("" + DateUtils.unixTimestampToDateMonth(nowTime));
            txt_notata1.setTextColor(getResources().getColor(R.color.theme_color));
            txt_notata1.setVisibility(View.VISIBLE);
        }
        if (listRepair == null) {
            return;
        }
        List<double[]> listX1 = new ArrayList<>();
        List<double[]> listY1 = new ArrayList<>();
        String[] titles1 = new String[listRepair.size()];
        for (int i = 0; i < listRepair.size(); i++) {
            titles1[i] = listRepair.get(i).getDispStateName();
            listX1.add(listRepair.get(i).getTime());
            listY1.add(listRepair.get(i).getValues());
        }
        setGraph(titles1);//设置图列的个数
        RepairFormSecondBean.ResobjBean.RepairBean repairBean = listRepair.get(0);
        try {
            Mf_Tools.setData(titles1, listY1, listX1, repairBean.getMaxX(), repairBean.getMaxY(), repairBean.getMinY(), this, rela_loading1, "1", resobj.getNowTime());
        } catch (Exception e) {
            ToastUtils.showToast(this, "图表数据有异常,请您稍后再尝试!");
            Log.i(TAG, "图表有问题：" + e.getMessage());
            return;
        }
    }

    @Override
    public void repairFormListNull() {
        txt_notata1.setText(getString(R.string.no_data));
    }

    @Override
    public void repairFormError() {
        txt_notata1.setText(getString(R.string.paint_error));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
