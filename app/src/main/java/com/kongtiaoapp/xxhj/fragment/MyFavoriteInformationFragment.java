package com.kongtiaoapp.xxhj.fragment;

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
import com.kongtiaoapp.xxhj.activites.SelectionDetailActivity;
import com.kongtiaoapp.xxhj.activites.SpecialDetailActivity;
import com.kongtiaoapp.xxhj.prefessionalknowledge.adapter.MyFavoriteInformationAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.ReceiverAction;
import com.kongtiaoapp.xxhj.bean.GetPostList;
import com.kongtiaoapp.xxhj.bean.MyZiXun;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.MyFavoriteInformationPresenter;
import com.kongtiaoapp.xxhj.mvp.view.MyFavoriteInformationView;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我收藏的资讯Framgent
 * Created by Luoye on 2016-6-8.
 */
public class MyFavoriteInformationFragment extends BaseFragment<MyFavoriteInformationPresenter,MyFavoriteInformationView> implements   MyFavoriteInformationView {
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.sv_energy_main)
    SpringView svEnergyMain;
    private List<MyZiXun.MyZiXunBean> mList = new ArrayList<>();
    private MyFavoriteInformationAdapter energyListAdapter;

    private int current;

    private int currentPage = 1;
    private boolean isRefresh = true;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS:
                    getDataForServers();
                    svEnergyMain.onFinishFreshAndLoad();
                    break;
                case ConstantValue.JIAZAI_SUCESS:
                    getDataForServers();
                    svEnergyMain.onFinishFreshAndLoad();
                    break;

                default:
                    break;
            }
        }
    };

    public MyFavoriteInformationFragment() {
    }

    @Override
    protected View initView() {
        RegisterReceiver();
        return View.inflate(mActivity, R.layout.fragment_energy_main, null);
    }

    @Override
    public void initData() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyZiXun.MyZiXunBean item = (MyZiXun.MyZiXunBean) parent.getItemAtPosition(position);
                if ("B".equals(item.getType())) {
                    //B.讨论帖
                    GetPostList data = new GetPostList();
                    GetPostList.Post post = data.new Post();
                    post.setUserId(item.getUserId());
                    post.setThemeId(item.getCollectionId());
                    post.setAvatarUrl(item.getAvatarUrl());
                    post.setUserName(item.getUserName());
                    post.setCreateTime(item.getCreateTime());
                    post.setContent(item.getTitle());
                    post.setImageUrl(item.getImageUrl());
                    post.setWelldoneNumber(item.getIsCollection());
                    startActivity(new Intent(mActivity, PostDetailActivity.class).putExtra("post",post));
                } else if ("C".equals(item.getType())) {
                    // C.精选
                    startActivity(new Intent(mActivity, SelectionDetailActivity.class).putExtra("sid", item.getMsgId()));
                } else if ("D".equals(item.getType())) {
                    // D.专栏
                    startActivity(new Intent(mActivity, SpecialDetailActivity.class).putExtra("sid", item.getMsgId()));
                }
//                Intent intent = new Intent(getContext(), MomentsDetailActivity.class);
//                intent.putExtra("MomentInfo", momentInfo);
//                startActivity(intent);
            }
        });
        svEnergyMain.setHeader(new RotationHeader(getContext()));
        svEnergyMain.setFooter(new RotationFooter(getContext()));
        svEnergyMain.setListener(new SpringView.OnFreshListener() {
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

        energyListAdapter = new MyFavoriteInformationAdapter(mActivity, mList);
        listview.setAdapter(energyListAdapter);
        getDataForServers();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void getDataForServers() {
        presenter.onResume(mActivity,String.valueOf(currentPage));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public MyFavoriteInformationPresenter getPresenter() {
        return new MyFavoriteInformationPresenter();
    }

    private myReceiver receiver;

    /**
     * 注册广播接受者
     */
    private void RegisterReceiver() {
        // 实例化过滤器；
        IntentFilter intentFilter = new IntentFilter();
        // 添加过滤的Action值；
        intentFilter.addAction(ReceiverAction.MOMENTS_FRESH);

        // 实例化广播监听器；
        receiver = new myReceiver();

        // 将广播监听器和过滤器注册在一起；
        getActivity().registerReceiver(receiver, intentFilter);

    }

    @Override
    public void setList(Object response) {
        MyZiXun moments = (MyZiXun) response;
        if (isRefresh) {
            mList.clear();
            mList.addAll(moments.getResobj());
            energyListAdapter.notifyDataSetChanged();
        } else {
            if (moments.getResobj() == null || moments.getResobj().size() == 0) {
                ToastUtils.showToast(mActivity,getString(R.string.no_data));
                return;
            }
            mList.addAll(moments.getResobj());
            energyListAdapter.notifyDataSetChanged();
        }
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
            if (action.equals(ReceiverAction.MOMENTS_FRESH)) {
//                isRefresh = true;
//                getDataForServers();
            }
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }
}
