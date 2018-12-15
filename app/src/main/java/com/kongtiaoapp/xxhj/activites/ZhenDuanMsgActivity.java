package com.kongtiaoapp.xxhj.activites;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.ZhenDuanAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.SysMesgList;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.ZhenDuanMsgPresenter;
import com.kongtiaoapp.xxhj.mvp.view.ZhenDuanMsgView;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS-PC on 2016/6/16.
 * 诊断消息
 */
public class ZhenDuanMsgActivity extends BaseActivity<ZhenDuanMsgPresenter, ZhenDuanMsgView> implements ZhenDuanMsgView {
    public static String SERVICE_RECEIVER = "com.art.zok.receiver";
    @ViewInject(R.id.zdMsg_listView)
    private ListView mListView;
    @ViewInject(R.id.zdMsg_tv_titleName)
    private TextView mTitleName;//标题名称
    @ViewInject(R.id.springView)
    private SpringView springView;//刷新
    private ZhenDuanAdapter adapter;
    private int type;//上个页面带过来的页面类型 1诊断 2故障 3维保
    private String category;
    private List mList = new ArrayList();
    private int currentPage = 1;
    private boolean isRefresh = true;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS:
                    getDataForServers(category);
                    springView.onFinishFreshAndLoad();
                    break;
                case ConstantValue.JIAZAI_SUCESS:
                    getDataForServers(category);
                    springView.onFinishFreshAndLoad();
                    break;

                default:
                    break;
            }
        }
    };

    @Event(value = {R.id.iv_back})
    private void getEvent(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }


    @Override
    protected int initContentView() {
        return R.layout.activity_zhenduan_msg;
    }

    @Override
    protected void initView() {
        x.view().inject(this);
        springView.setHeader(new RotationHeader(mContext));
        springView.setFooter(new RotationFooter(mContext));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;
                        currentPage = 1;
                        handler.sendEmptyMessage(ConstantValue.SHUAXIN_SUCESS);
                    }
                }).start();
            }

            @Override
            public void onLoadmore() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = false;
                        currentPage++;
                        handler.sendEmptyMessage(ConstantValue.JIAZAI_SUCESS);
                    }
                }).start();
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", 0);
        if (type == 1) {
            mTitleName.setText(getString(R.string.running_suggestion));
            category = "A";
        } else if (type == 2) {
            mTitleName.setText("故障信息");
            category = "B";
        } else if (type == 3) {
            mTitleName.setText("工单信息");
            category = "G";
        } else if (type == 5) {
            mTitleName.setText("值班信息");
            category = "E";
        }
        String categorys = getIntent().getStringExtra("category");
        if (categorys != null) {
            category = categorys;
            if (category.equals("A")) {
                type = 1;
                mTitleName.setText(getString(R.string.running_suggestion));
            } else if (category.equals("B")) {
                type = 2;
                mTitleName.setText("故障信息");
            } else if (category.equals("G")) {
                type = 3;
                mTitleName.setText("工单信息");
            } else if (category.equals("E")) {
                type = 5;
                mTitleName.setText("值班信息");
            }
        }
        adapter = new ZhenDuanAdapter(this, mList, type);
        mListView.setAdapter(adapter);
        getDataForServers(this.category);
    }

    @Override
    protected ZhenDuanMsgPresenter getPresenter() {
        return new ZhenDuanMsgPresenter();
    }

    private void getDataForServers(String category) {
        List<String> list = new ArrayList<>();
        list.add(category);
        list.add(String.valueOf(currentPage));
        presenter.onResume(this, list);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mList != null) {
            mList.clear();
            mList = null;
        }
    }

    @Override
    public void setList(Object response) {
        SysMesgList data = (SysMesgList) response;
        if (isRefresh) {
            mList.clear();
        }
        mList.addAll(data.getResobj());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setText(Object data) {

    }
}
