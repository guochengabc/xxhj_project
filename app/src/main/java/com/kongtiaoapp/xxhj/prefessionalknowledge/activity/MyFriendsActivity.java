package com.kongtiaoapp.xxhj.prefessionalknowledge.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.MsgFriendDetailsActivity;
import com.kongtiaoapp.xxhj.prefessionalknowledge.adapter.MyFriendsAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.GetFriendList;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.MyFriendsPresenter;
import com.kongtiaoapp.xxhj.mvp.view.MyFriendsView;
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
 * 我的好友页面
 */
public class MyFriendsActivity extends BaseActivity<MyFriendsPresenter,MyFriendsView> implements MyFriendsView {

    @ViewInject(R.id.myFriends_listView)
    private ListView mListView;

    @ViewInject(R.id.springView)
    private SpringView springView;

    private MyFriendsAdapter adapter;

    private int currentPage = 1;
    private boolean isRefresh = true;
    private List<GetFriendList.FriendList> mList = new ArrayList<>();

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS:
                    presenter.onResume(MyFriendsActivity.this,currentPage+"");
                    springView.onFinishFreshAndLoad();
                    break;
                case ConstantValue.JIAZAI_SUCESS:
                    presenter.onResume(MyFriendsActivity.this,currentPage+"");
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
            case R.id.iv_back://返回按钮
                finish();
                break;
        }
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_my_friends;
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
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GetFriendList.FriendList item = (GetFriendList.FriendList) parent.getAdapter().getItem(position);
                Intent intent = new Intent(MyFriendsActivity.this, MsgFriendDetailsActivity.class).putExtra("friendId", item.getUserId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        adapter = new MyFriendsAdapter(this, mList);
        mListView.setAdapter(adapter);
        presenter.onResume(this,currentPage+"");

    }

    @Override
    protected MyFriendsPresenter getPresenter() {
        return new MyFriendsPresenter();
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
        GetFriendList data = (GetFriendList) response;
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

