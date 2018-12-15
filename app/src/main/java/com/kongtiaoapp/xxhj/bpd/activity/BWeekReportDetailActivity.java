package com.kongtiaoapp.xxhj.bpd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.BWeekReportDetailAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.BWeekReportDetailBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.BWeekReportDetailP;
import com.kongtiaoapp.xxhj.mvp.view.BWeekReportDetailV;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 变配电   周报月报详情
 */
public class BWeekReportDetailActivity extends BaseActivity<BWeekReportDetailP, BWeekReportDetailV> implements BWeekReportDetailV {
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.myListView)
    ListView myListView;
    List<BWeekReportDetailBean.ResobjBean.DataBean> listAll = new ArrayList<>();
    private BWeekReportDetailAdapter adapter;
    private String reportId;
    private int currentPage = 1;
    private boolean isFirstLoading=true;
    private int intentPosition;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_bweek_report_detail;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        intentPosition = intent.getIntExtra("position", 0);
        reportId = intent.getStringExtra("reportId");

    }

    @Override
    protected void initListener() {
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
    protected void initData() {
        adapter = new BWeekReportDetailAdapter(this, listAll);
        myListView.setAdapter(adapter);
       getDataForServies();
    }
    private void getDataForServies() {
        List<String> list = new ArrayList<>();
        list.add(currentPage + "");
        list.add(reportId);
        presenter.onResume(this, list);
    }


    @Override
    protected BWeekReportDetailP getPresenter() {
        return new BWeekReportDetailP();
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:

                break;
        }
    }

    @Override
    public void setText(Object data) {
        BWeekReportDetailBean bean = (BWeekReportDetailBean) data;
        BWeekReportDetailBean.ResobjBean resobj = bean.getResobj();
        List<BWeekReportDetailBean.ResobjBean.DataBean> list = resobj.getData();
        if (isRefresh) {
            listAll.clear();
        }
        listAll.addAll(list);
        adapter.setList(listAll);
       if (isFirstLoading){
           isFirstLoading=!isFirstLoading;
           myListView.setSelection(intentPosition);
       }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
