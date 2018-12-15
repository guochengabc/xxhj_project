package com.kongtiaoapp.xxhj.activites;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.ZhenDuanRecordAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.ZhenDuanList;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.ZhenDuanRecordPresenter;
import com.kongtiaoapp.xxhj.mvp.view.ZhenDuanRecordView;
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
 * 我的->诊断记录页面
 */
public class ZhenDuanRecordActivity extends BaseActivity<ZhenDuanRecordPresenter,ZhenDuanRecordView> implements ZhenDuanRecordView {

    @ViewInject(R.id.zhenDuanRecord_listView)
    private ListView mListView;
    @ViewInject(R.id.springView)
    private SpringView springView;

    private ZhenDuanRecordAdapter adapter;
    private List mList = new ArrayList();

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
    private String projectId;

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

        projectId = getIntent().getStringExtra("projectId");
        return R.layout.activity_zhenduan_record;

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
      /*  mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ZhenDuanList.ZhenduanTime item = (ZhenDuanList.ZhenduanTime) parent.getAdapter().getItem(position);
                Intent mIneten = new Intent();
                mIneten.setAction(ReceiverAction.ZHENDUAN);
                mIneten.putExtra("diagId", item.getDiagID());
                sendBroadcast(mIneten);
                finish();
            }
        });*/
    }

    @Override
    protected void initData() {
        adapter = new ZhenDuanRecordAdapter(this, mList);
        mListView.setAdapter(adapter);
        getDataForServers();
    }

    @Override
    protected ZhenDuanRecordPresenter getPresenter() {
        return new ZhenDuanRecordPresenter();
    }

    private void getDataForServers() {
        List<String> list=new ArrayList<>();
        list.add(projectId);
        list.add(String.valueOf(currentPage));
        presenter.onResume(this,list);
    }

    @Override
    public void setList(Object response) {
        ZhenDuanList info = (ZhenDuanList) response;
        if (info.getResobj() != null) {
            if (isRefresh) {
                mList.clear();
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
