package com.kongtiaoapp.xxhj.prefessionalknowledge.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.prefessionalknowledge.activity.Energy8ZhuanLanActivity;
import com.kongtiaoapp.xxhj.prefessionalknowledge.adapter.Energy8ListAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.Energy8List;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.Energy8SpecialPresenter;
import com.kongtiaoapp.xxhj.mvp.view.Energy8SpecialView;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 节能吧_专栏
 * Created by Luoye on 2016-6-17.
 */
public class Energy8SpecialFragment extends BaseFragment<Energy8SpecialPresenter, Energy8SpecialView> implements Energy8SpecialView {
    @BindView(R.id.listView2)
    ListView listView2;
    @BindView(R.id.springView2)
    SpringView springView2;
    private int currentPage = 1;
    private boolean isRefresh = true;
    List<Energy8List.Energy8> mList = new ArrayList<>();
    private Energy8ListAdapter adapter;
    private boolean isFirst = true;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS:
                    getDataForServers();
                    springView2.onFinishFreshAndLoad();
                    break;
                case ConstantValue.JIAZAI_SUCESS:
                    getDataForServers();
                    springView2.onFinishFreshAndLoad();
                    break;

                default:
                    break;
            }
        }
    };


    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_energy8special, null);
    }

    @Override
    public void initData() {
        setView();
        adapter = new Energy8ListAdapter(getContext(), mList);
        listView2.setAdapter(adapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getDataForServers();
        }
    }

    public void setView() {
        springView2.setHeader(new RotationHeader(getContext()));
        springView2.setFooter(new RotationFooter(getContext()));
        springView2.setListener(new SpringView.OnFreshListener() {
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
        listView2.setOnItemClickListener((parent, view1, position, id) -> {
            Energy8List.Energy8 item = (Energy8List.Energy8) parent.getAdapter().getItem(position);
            startActivity(new Intent(mActivity, Energy8ZhuanLanActivity.class)
                    .putExtra("sid", item.getSpecialInfoId())
                    .putExtra("simgae", item.getFaceUrl())
                    .putExtra("stitle", item.getTitle())
                    .putExtra("scontent", item.getAbstract()));
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public Energy8SpecialPresenter getPresenter() {
        return new Energy8SpecialPresenter();
    }

    private void getDataForServers() {

        List list = new ArrayList<>();
        list.add(String.valueOf(currentPage));
        list.add(isFirst);
        if (isFirst) {
            isFirst = false;
        }
        presenter.onResume(mActivity, list);
    }

    @Override
    public void setList(Object response) {
        Energy8List info = (Energy8List) response;
        if (isRefresh) {
            mList.clear();
            mList.addAll(info.getResobj());
            adapter.notifyDataSetChanged();
        } else {
            if (info.getResobj() == null || info.getResobj().size() == 0) {
                ToastUtils.showToast(mActivity, getString(R.string.no_data));
                return;
            }
            mList.addAll(info.getResobj());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
