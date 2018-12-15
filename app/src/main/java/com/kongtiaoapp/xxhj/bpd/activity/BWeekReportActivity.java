package com.kongtiaoapp.xxhj.bpd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.BWeekReportAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.BWeekReportBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.BWeek_MonthReportP;
import com.kongtiaoapp.xxhj.mvp.view.BWeek_MonthReportV;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class BWeekReportActivity extends BaseActivity<BWeek_MonthReportP, BWeek_MonthReportV> implements BWeek_MonthReportV {
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.txt_nodata)
    TextView txt_nodata;
    private List<BWeekReportBean.ResobjBean.MonthDataBean> listAll = new ArrayList<BWeekReportBean.ResobjBean.MonthDataBean>();
    private BWeekReportAdapter adapter;
    private boolean isClick = true, isFirst = true;
    private int currentPage = 1;
    boolean isRefresh;
    /*刷新数据*/
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS:
                    getDataForServies();
                    springView.onFinishFreshAndLoad();
                    break;
                case ConstantValue.JIAZAI_SUCESS:
                    getDataForServies();
                    springView.onFinishFreshAndLoad();
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ButterKnife.bind(this);
    }

    @Override
    public BWeek_MonthReportP getPresenter() {
        return new BWeek_MonthReportP();
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_bweek_report;
    }

    @Override
    protected void initView() {
        springView.setHeader(new RotationHeader(mContext));
        springView.setFooter(new RotationFooter(mContext));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {//刷新
                new Thread(() -> {
                    isRefresh = true;
                    currentPage = 1;
                    handler.sendEmptyMessage(ConstantValue.SHUAXIN_SUCESS);
                }).start();
            }

            @Override
            public void onLoadmore() {//加载更多
                new Thread(() -> {
                    isRefresh = false;
                    currentPage++;
                    handler.sendEmptyMessage(ConstantValue.JIAZAI_SUCESS);
                }).start();
            }
        });
    }


    @Override
    protected void initListener() {

    }

    @Override
    public void initData() {
        adapter = new BWeekReportAdapter(this, listAll);
        listView.setAdapter(adapter);
        getDataForServies();
    }

    private void getDataForServies() {
        presenter.onResume(this, currentPage + "");
    }

@OnClick({R.id.iv_back})
public void onClick(View view){
    switch (view.getId()){
        case R.id.iv_back:
            finish();
            break;
    }
}
    @OnItemClick({R.id.listView})
    public void onItemClick(int position) {
//        AlarmListBean.ResobjBean bean = (AlarmListBean.ResobjBean) adapter.getItem(position);
//        startActivity(new Intent(mActivity, AlarmDetailActivity.class).putExtra("alarmId", bean.getAlarmId()));
        BWeekReportBean.ResobjBean.MonthDataBean bean = (BWeekReportBean.ResobjBean.MonthDataBean) adapter.getItem(position);
        startActivity(new Intent(this, BWeekReportDetailActivity.class).putExtra("reportId", bean.getReportId()).putExtra("position", (position % 10)));
    }


    @Override
    public void setList(Object data) {
        txt_nodata.setVisibility(View.GONE);
        BWeekReportBean bean = (BWeekReportBean) data;
        BWeekReportBean.ResobjBean resobj = bean.getResobj();
        List<BWeekReportBean.ResobjBean.MonthDataBean> list = resobj.getMonthData();
        if (isRefresh) {
            listAll.clear();
        }
        listAll.addAll(list);
        adapter.setList(listAll);
    }

    @Override
    public void listNull() {
        isClick = false;
        txt_nodata.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setText(Object data) {

    }
}

