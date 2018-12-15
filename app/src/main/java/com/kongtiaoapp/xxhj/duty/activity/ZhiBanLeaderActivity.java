package com.kongtiaoapp.xxhj.duty.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.prefessionalknowledge.activity.HistoryActivity;
import com.kongtiaoapp.xxhj.duty.adapter.ZhiBanRecordAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.GetDutyRecordList;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.ZhiBanLeaderPresenter;
import com.kongtiaoapp.xxhj.mvp.view.ZhiBanLeaderView;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhiBanLeaderActivity extends BaseActivity<ZhiBanLeaderPresenter, ZhiBanLeaderView> implements ZhiBanLeaderView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.listView)
    ListView listView;
    List<GetDutyRecordList.RecordList> mList = new ArrayList<>();

    @BindView(R.id.springView)
    SpringView springView;
    private int currentPage = 1;
    private boolean isRefresh = true;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS:
                    getDataForServers();
                    springView.onFinishFreshAndLoad();
                    break;
                case ConstantValue.JIAZAI_SUCESS:
                    getDataForServers();
                    springView.onFinishFreshAndLoad();
                    break;

                default:
                    break;
            }
        }
    };
    private ZhiBanRecordAdapter adapter;

    private void getDataForServers() {
        presenter.onResuem(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_zhiban_leader;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        setView();
        adapter = new ZhiBanRecordAdapter(mContext, mList);
        listView.setAdapter(adapter);
        getDataForServers();
    }

    @Override
    protected ZhiBanLeaderPresenter getPresenter() {
        return new ZhiBanLeaderPresenter();
    }

    private void setView() {
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Thread(() -> {
                    isRefresh = true;
                    currentPage = 1;
                    handler.sendEmptyMessage(ConstantValue.SHUAXIN_SUCESS);
                }).start();
            }

            @Override
            public void onLoadmore() {
                new Thread(() -> {
                    isRefresh = false;
                    currentPage++;
                    handler.sendEmptyMessage(ConstantValue.JIAZAI_SUCESS);
                }).start();
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            GetDutyRecordList.RecordList item = (GetDutyRecordList.RecordList) parent.getAdapter().getItem(position);
            startActivity(new Intent(mContext, ZhiBanDetailActivity.class).putExtra("dutyId", item.getDutyId()));
        });
    }

    @OnClick({R.id.iv_search, R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.iv_search:
                startActivity(new Intent(mContext, HistoryActivity.class));
                break;
            default:
                break;
        }

    }

    @Override
    public void setList(Object response) {
        GetDutyRecordList info = (GetDutyRecordList) response;
        if (isRefresh) {
            mList.clear();
            mList.addAll(info.getResobj());
            adapter.notifyDataSetChanged();
        } else {
            if (info.getResobj() == null || info.getResobj().size() == 0) {
                ToastUtils.showToast(this,getString(R.string.no_data));
                return;
            }
            mList.addAll(info.getResobj());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mList != null) {
            mList.clear();
            mList = null;
        }
    }
}
