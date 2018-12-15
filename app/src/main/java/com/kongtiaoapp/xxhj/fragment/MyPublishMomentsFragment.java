package com.kongtiaoapp.xxhj.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.MomentsDetailActivity;
import com.kongtiaoapp.xxhj.prefessionalknowledge.adapter.EnergyListAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.ReceiverAction;
import com.kongtiaoapp.xxhj.bean.Moments;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.MyPublishMomentsPresenter;
import com.kongtiaoapp.xxhj.mvp.view.MyPublishMomentsView;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我发布的动态Framgent
 * Created by Luoye on 2016-6-8.
 */
public class MyPublishMomentsFragment extends BaseFragment<MyPublishMomentsPresenter, MyPublishMomentsView> implements MyPublishMomentsView, EnergyListAdapter.OperationListener {


    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.sv_energy_main)
    SpringView svEnergyMain;
    private List<Moments.MomentInfo> mList = new ArrayList<>();
    private EnergyListAdapter energyListAdapter;

    private int current;
    private boolean isWelldone;
    private boolean IsCollection;

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

    public MyPublishMomentsFragment() {
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
                Moments.MomentInfo momentInfo = (Moments.MomentInfo) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), MomentsDetailActivity.class);
                intent.putExtra("MomentInfo", momentInfo);
                startActivity(intent);
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

        energyListAdapter = new EnergyListAdapter(mActivity, mList);
        energyListAdapter.setOperationListener(this);
        listview.setAdapter(energyListAdapter);
        getDataForServers();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void getDataForServers() {
        presenter.onResume(mActivity, String.valueOf(currentPage));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public MyPublishMomentsPresenter getPresenter() {
        return new MyPublishMomentsPresenter();
    }

    @Override
    public void praise(int position, boolean isPraise) {
        isWelldone = isPraise;
        current = position;
        String ispraise = isPraise ? "0" : "1";
        List<String> list = new ArrayList<>();
        list.add(mList.get(position).getMessageId());
        list.add(ispraise);
        presenter.praise(mActivity,list);
    }

    @Override
    public void favorite(int position, boolean isFavorite) {
        IsCollection = isFavorite;
        current = position;
        String isfavorite = isFavorite ? "0" : "1";
        List<String> list = new ArrayList<>();
        list.add(mList.get(position).getMessageId());
        list.add(isfavorite);
        presenter.favorite(mActivity,list);
    }

    @Override
    public void comment(Moments.MomentInfo momentInfo) {
        Intent intent = new Intent(getContext(), MomentsDetailActivity.class);
        intent.putExtra("MomentInfo", momentInfo);
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
        intentFilter.addAction(ReceiverAction.MOMENTS_FRESH);

        // 实例化广播监听器；
        receiver = new myReceiver();

        // 将广播监听器和过滤器注册在一起；
        getActivity().registerReceiver(receiver, intentFilter);

    }

    @Override
    public void setList(Object response) {
        Moments moments = (Moments) response;
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
    public void praise_View(Object data) {
        int number = Integer.valueOf(TextUtils.isEmpty(mList.get(current).getWelldoneNumber()) ? "0" : mList.get(current).getWelldoneNumber()).intValue();
        if (isWelldone) {
            mList.get(current).setIsWelldone("0");
            mList.get(current).setWelldoneNumber(String.valueOf(number - 1));
        } else {
            mList.get(current).setIsWelldone("1");
            mList.get(current).setWelldoneNumber(String.valueOf(number + 1));
        }
        energyListAdapter.notifyDataSetChanged();
    }

    @Override
    public void favorite_View(Object data) {
        int number = Integer.valueOf(TextUtils.isEmpty(mList.get(current).getWelldoneNumber()) ? "0" : mList.get(current).getWelldoneNumber()).intValue();
        if (IsCollection) {
            mList.get(current).setIsCollection("0");
//                        mList.get(current).setWelldoneNumber(String.valueOf(number - 1));
        } else {
            mList.get(current).setIsCollection("1");
//                        mList.get(current).setWelldoneNumber(String.valueOf(number + 1));
        }
        energyListAdapter.notifyDataSetChanged();
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
