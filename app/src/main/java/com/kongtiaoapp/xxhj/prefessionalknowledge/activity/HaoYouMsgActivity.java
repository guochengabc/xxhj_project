package com.kongtiaoapp.xxhj.prefessionalknowledge.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.FriendsChatMsgActivity;
import com.kongtiaoapp.xxhj.activites.MsgFriendDetailsActivity;
import com.kongtiaoapp.xxhj.prefessionalknowledge.adapter.HaoYouMsgAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.FriendMesgList;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.HaoYouMsgPresenter;
import com.kongtiaoapp.xxhj.mvp.view.HaoYouMsgView;
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
 * 好友消息面
 */
public class HaoYouMsgActivity extends BaseActivity<HaoYouMsgPresenter, HaoYouMsgView> implements HaoYouMsgView {

    @ViewInject(R.id.friendsMsg_listView)
    private ListView mListView;

    @ViewInject(R.id.springView)
    private SpringView springView;

    private HaoYouMsgAdapter adapter;
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

    private void getDataForServers() {
        presenter.onResume(this,currentPage+"");
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_haoyou_msg;
    }

    @Event(value = {R.id.iv_back})
    private void getEvent(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
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
    }

    @Override
    protected void initListener() {
        mListView.setOnItemClickListener((parent, view, position, id) -> {
            FriendMesgList.FriendMesg item = (FriendMesgList.FriendMesg) parent.getAdapter().getItem(position);
            if ("A".equals(item.getType())) {
                //A 记录
                startActivity(new Intent(mContext, FriendsChatMsgActivity.class).putExtra("friendId", item.getUserId()));
            } else if ("B".equals(item.getType())) {
                //B 好友请求
                startActivity(new Intent(mContext, MsgFriendDetailsActivity.class).putExtra("friendId", item.getUserId()).putExtra("isApply", true));
            } else if ("C".equals(item.getType())) {
                //C 好友分享
            } else if ("D".equals(item.getType())) {
                //D 话题回复
            }
        });
    }

    @Override
    protected void initData() {
        adapter = new HaoYouMsgAdapter(this, mList);
        mListView.setAdapter(adapter);
        getDataForServers();
    }

    @Override
    protected HaoYouMsgPresenter getPresenter() {
        return new HaoYouMsgPresenter();
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
        FriendMesgList data = (FriendMesgList) response;
        if (isRefresh) {
            mList.clear();
        }
        mList.addAll(data.getResobj());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setText(Object text) {

    }
}
