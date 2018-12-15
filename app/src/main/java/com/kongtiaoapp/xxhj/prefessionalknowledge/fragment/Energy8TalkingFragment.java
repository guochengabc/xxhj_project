package com.kongtiaoapp.xxhj.prefessionalknowledge.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.prefessionalknowledge.activity.PostDetailActivity;
import com.kongtiaoapp.xxhj.prefessionalknowledge.adapter.Energy8TalkingAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.ReceiverAction;
import com.kongtiaoapp.xxhj.bean.GetPostList;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.Energy8Talkingpresenter;
import com.kongtiaoapp.xxhj.mvp.view.Energy8TalkingView;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 节能吧_讨论
 * Created by Luoye on 2016-6-17.
 */
public class Energy8TalkingFragment extends BaseFragment<Energy8Talkingpresenter,Energy8TalkingView> implements Energy8TalkingView, AdapterView.OnItemClickListener {
    @BindView(R.id.listview)
    ListView listview;//分页加载
    @BindView(R.id.sv_energy_main)
    SpringView springView;
    private int currentPage = 1;
    private boolean isRefresh = true;//是否刷新
    List<GetPostList.Post> mList = new ArrayList<>();
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
    private Energy8TalkingAdapter adapter;
    private boolean isFirst = true;

    @Override
    protected View initView() {
        RegisterReceiver();
        return View.inflate(mActivity, R.layout.fragment_energy_main, null);
    }

    @Override
    public void initData() {
        setView();
        adapter = new Energy8TalkingAdapter(getContext(), mList);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            getDataForServers();
        }else{

        }
    }

    public void setView() {
        springView.setHeader(new RotationHeader(getContext()));
        springView.setFooter(new RotationFooter(getContext()));
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

    private void getDataForServers() {
        List list=new ArrayList<>();
        list.add(String.valueOf(currentPage));
        list.add(isFirst);
        if (isFirst) {
            isFirst = false;
        }
        presenter.onResume(mActivity,list);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public Energy8Talkingpresenter getPresenter() {
        return new Energy8Talkingpresenter();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GetPostList.Post post = (GetPostList.Post) parent.getItemAtPosition(position);
        Intent intent = new Intent(getContext(), PostDetailActivity.class);
        intent.putExtra("post", post);
        startActivity(intent);
    }

    private myReceiver receiver;

    /**
     * 注册广播接受者
     */
    private void RegisterReceiver() {
        // 实例化过滤器；
        IntentFilter intentFilter = new IntentFilter();
        // 添加过滤的Action值；
        intentFilter.addAction(ReceiverAction.POST_FRESH);

        // 实例化广播监听器；
        receiver = new myReceiver();

        // 将广播监听器和过滤器注册在一起；
        getActivity().registerReceiver(receiver, intentFilter);

    }

    @Override
    public void setList(Object data) {
        GetPostList info = (GetPostList) data;
        if (isRefresh) {
            mList.clear();
            mList.addAll(info.getResobj());
            adapter.notifyDataSetChanged();
        } else {
            if (info.getResobj() == null || info.getResobj().size() == 0) {
                ToastUtils.showToast(mActivity, getResources().getString(R.string.no_data));
                return;
            }
            mList.addAll(info.getResobj());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void list_null() {
        if (!isRefresh) {
            currentPage = 1;
            if (mList.size() == 0) {
                ToastUtils.showToast(mActivity,getString(R.string.no_data));
            } else {
                ToastUtils.showToast(mActivity,getString(R.string.all_load));
            }
            //加载更多
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
            if (action.equals(ReceiverAction.POST_FRESH)) {
                isRefresh = true;
                getDataForServers();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }
}
