package com.kongtiaoapp.xxhj.activites;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.AdapterView;

import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.WeiBaoRecordAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.ReceiverAction;
import com.kongtiaoapp.xxhj.bean.GetMTRecordList;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.WeibaoRecordPresenter;
import com.kongtiaoapp.xxhj.mvp.view.WeiBaoRecordView;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;
import com.kongtiaoapp.xxhj.utils.BaseTools;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS-PC on 2016/6/16.
 * 我的->维保记录页面
 */
public class WeiBaoRecordActivity extends BaseActivity<WeibaoRecordPresenter,WeiBaoRecordView> implements WeiBaoRecordView {
    @ViewInject(R.id.weiBaoRecord_listView)
    private SwipeMenuListView mListView;
    @ViewInject(R.id.springView)
    private SpringView springView;
    private WeiBaoRecordAdapter adapter;
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

    @Event(value = {R.id.iv_back, R.id.weiBaoRecord_iv_add})
    private void getEvent(View v) {
        switch (v.getId()) {
            case R.id.iv_back://返回按钮
                finish();
                break;
            case R.id.weiBaoRecord_iv_add://右上角添加按钮
                startActivity(new Intent(this, AddWeiBaoRecordActivity.class));
                break;
        }
    }

    @Override
    protected int initContentView() {
        RegisterReceiver();
        return R.layout.activity_weibao_record;
    }

    @Override
    protected void initView() {
        x.view().inject(this);
        springView.setHeader(new RotationHeader(mContext));
        springView.setFooter(new RotationFooter(mContext));
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

        SwipeMenuCreator creator = menu -> {
            // create "open" item
            SwipeMenuItem openItem = new SwipeMenuItem(
                    getApplicationContext());
            // set item background
            openItem.setBackground(new ColorDrawable(Color.rgb(0xFF, 0x00,
                    0x00)));
            // set item width
            openItem.setWidth(BaseTools.dip2px(mContext, 90));
            // set item title
            openItem.setTitle("删除");
            // set item title fontsize
            openItem.setTitleSize(18);
            // set item title font color
            openItem.setTitleColor(Color.WHITE);
            // add to menu
            menu.addMenuItem(openItem);

        };
        mListView.setMenuCreator(creator);

        mListView
                .setOnMenuItemClickListener((position, menu, index) -> {
                    //showToast(TAG,"删除第"+ position + "条");
                    GetMTRecordList.MTRecord item = (GetMTRecordList.MTRecord) adapter.getItem(position);
                    if (App.sp.getUid().equals(item.getUserId())) {
                        delete(item.getMaintenanceRecordId());
                    } else {
                        ToastUtils.showToast(WeiBaoRecordActivity.this, "只能删除自己的维保记录");
                    }
                    return false;
                });
        adapter = new WeiBaoRecordAdapter(this, mList);
        mListView.setAdapter(adapter);
    }

    //监听listview里面的数据，并进行相关的操作
    @Override
    protected void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GetMTRecordList.MTRecord item = (GetMTRecordList.MTRecord) adapter.getItem(position);
                startActivity(new Intent(mContext, AddWeiBaoRecordActivity.class).putExtra("MaintenanceRecordId", item.getMaintenanceRecordId()));
            }
        });
    }

    @Override
    protected void initData() {
        getDataForServers();
    }

    @Override
    protected WeibaoRecordPresenter getPresenter() {
        return new WeibaoRecordPresenter();
    }

    private void getDataForServers() {
        presenter.onResume(this,String.valueOf(currentPage));
    }

    /**
     * 删除记录
     */
    private void delete(String recordId) {
        presenter.delete(this,recordId);
    }
    private myReceiver receiver;

    /**
     * 注册广播接受者
     */
    private void RegisterReceiver() {
        // 实例化过滤器；
        IntentFilter intentFilter = new IntentFilter();
        // 添加过滤的Action值；
        intentFilter.addAction(ReceiverAction.WEIBAO_FRESH);

        // 实例化广播监听器；
        receiver = new myReceiver();

        // 将广播监听器和过滤器注册在一起；
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter);

    }

    @Override
    public void setList(Object response) {
        GetMTRecordList info = (GetMTRecordList) response;
        if (isRefresh) {
            mList.clear();
            mList.addAll(info.getResobj());
            adapter.notifyDataSetChanged();
        } else {
            if (info.getResobj() == null || info.getResobj().size() == 0) {
                ToastUtils.showToast(mContext, getString(R.string.no_data));
                return;
            }
            mList.addAll(info.getResobj());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void delete_View() {
        ToastUtils.showToast(mContext, getString(R.string.delete_succeed));
        isRefresh = true;
        adapter.notifyDataSetChanged();
        mListView.setAdapter(adapter);
        getDataForServers();
    }

    @Override
    public void list_Null() {
        if (!isRefresh) {
            if (mList.size() == 0) {
                ToastUtils.showToast(mContext, getString(R.string.no_data));
            } else {
                ToastUtils.showToast(mContext, getString(R.string.all_load));
            }
            return;
        }
        mList.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setText(Object data) {

    }


    /**
     * 广播接收者
     */
    public class myReceiver extends BroadcastReceiver {


        // 重写onReceive方法，该方法的实体为，接收到广播后的执行代码；
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ReceiverAction.WEIBAO_FRESH)) {
                isRefresh = true;
                getDataForServers();
            }
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        if (mList != null) {
            mList.clear();
            mList = null;
        }
    }
}
