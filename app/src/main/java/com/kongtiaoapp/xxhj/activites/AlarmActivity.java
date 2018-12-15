package com.kongtiaoapp.xxhj.activites;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.AlarmListAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.AlarmListBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.AlarmPresenter;
import com.kongtiaoapp.xxhj.mvp.view.AlarmView;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * 报警管理
 */
public class AlarmActivity extends BaseActivity<AlarmPresenter, AlarmView> implements AlarmView {
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.listView)
    ListView listView;
    private int currentPage = 1;
    boolean isRefresh;
    private List<AlarmListBean.ResobjBean> listAll = new ArrayList<AlarmListBean.ResobjBean>();
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
    private AlarmListAdapter adapter;
    private String method = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_alarm;
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
        adapter = new AlarmListAdapter(this, listAll);
        listView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getStringExtra("method") == null) {
                return;
            }
            method = intent.getStringExtra("method");
        }
        getDataForServies();
    }

    private void getDataForServies() {
        List<String> list = new ArrayList<>();
        list.add(currentPage + "");
        list.add(method);
        presenter.onResume(this, list);
    }

    @Override
    protected AlarmPresenter getPresenter() {
        return new AlarmPresenter();
    }

    @Override
    public void setText(Object data) {

    }

    @OnItemClick({R.id.listView})
    public void onItemClick(int position) {
        AlarmListBean.ResobjBean bean = (AlarmListBean.ResobjBean) adapter.getItem(position);
        startActivity(new Intent(this, AlarmDetailActivity.class).putExtra("alarmId", bean.getAlarmId()));
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }

    }

    @Override
    public void setList(Object data) {
        AlarmListBean bean = (AlarmListBean) data;
        if (isRefresh) {
            listAll.clear();
        }
        List<AlarmListBean.ResobjBean> list = bean.getResobj();
        listAll.addAll(list);
        adapter.setList(listAll);
    }

    @Override
    public void listNull() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
