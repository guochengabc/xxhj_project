package com.kongtiaoapp.xxhj.prefessionalknowledge.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.prefessionalknowledge.adapter.EnergyShowAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.EnergyShow;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.EnergyShowPresenter;
import com.kongtiaoapp.xxhj.mvp.view.EnergyShowView;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 节能展会页面
 * Created by Luoye on 2016-6-16.
 */
public class EnergyShowActivity extends BaseActivity<EnergyShowPresenter, EnergyShowView> implements EnergyShowView {
    @BindView(R.id.tv_my_show)//右上角的按钮
            TextView tvMyShow;
    @BindView(R.id.listView)//节能展会列表
            ListView listView;
    @BindView(R.id.springView)
    SpringView springView;

    private List mList = new ArrayList();

    private int currentPage = 1;
    private boolean isRefresh = true;
    /*更新数据源*/
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS:
                    getDataForService();
                    springView.onFinishFreshAndLoad();
                    break;
                case ConstantValue.JIAZAI_SUCESS:
                    getDataForService();
                    springView.onFinishFreshAndLoad();
                    break;

                default:
                    break;
            }
        }
    };
    private EnergyShowAdapter adapter;


    @Override
    protected int initContentView() {
        return R.layout.activity_energy_show;
    }

    //初始化布局
    @Override
    protected void initView() {
        springView.setHeader(new RotationHeader(this));
        springView.setFooter(new RotationFooter(this));
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
    }

    @Override
    protected void initListener() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            EnergyShow.EnergyShowList item = (EnergyShow.EnergyShowList) parent.getAdapter().getItem(position);
            if (item != null && !TextUtils.isEmpty(item.getExhibitionId())) {
                startActivity(new Intent(mContext, EnergyShowDetailActivity.class).putExtra("eid", item.getExhibitionId()));
            }
        });
    }

    @Override
    protected void initData() {
        adapter = new EnergyShowAdapter(mContext, mList);
        listView.setAdapter(adapter);
        getDataForService();
    }

    @Override
    protected EnergyShowPresenter getPresenter() {
        return new EnergyShowPresenter();
    }

    private void getDataForService() {
        presenter.onResume(this, currentPage + "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_my_show)
    public void onClick() {
        startActivity(new Intent(mContext, EnergyShowFormeActivity.class));
    }

    @Override
    public void setList(Object data) {
        EnergyShow response = (EnergyShow) data;
        if (isRefresh) {
            mList.clear();
        }
        mList.addAll(response.getResobj());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setText(Object text) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
