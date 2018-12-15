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
import com.kongtiaoapp.xxhj.activites.SelectionDetailActivity;
import com.kongtiaoapp.xxhj.prefessionalknowledge.adapter.Energy8ListAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.Energy8List;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.Energy8InformationPresenter;
import com.kongtiaoapp.xxhj.mvp.view.Energy8InformationView;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * 节能吧_精选
 * Created by Luoye on 2016-6-17.
 */
public class Energy8InformationFragment extends BaseFragment<Energy8InformationPresenter, Energy8InformationView> implements Energy8InformationView {

    @BindView(R.id.listView1)
    ListView listView1;
    @BindView(R.id.springView1)
    SpringView springView1;
    private int currentPage = 1;
    private boolean isRefresh = true, isFirst = true;
    List<Energy8List.Energy8> mList = new ArrayList<>();
    private boolean isLoad = true;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS:
                    getDataForServers();
                    springView1.onFinishFreshAndLoad();
                    break;
                case ConstantValue.JIAZAI_SUCESS:
                    getDataForServers();
                    springView1.onFinishFreshAndLoad();
                    break;

                default:
                    break;
            }
        }
    };
    private Energy8ListAdapter adapter;


    public Energy8InformationFragment() {
    }




    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_energy8information, null);
    }

    @Override
    public void initData() {
        setView();
        adapter = new Energy8ListAdapter(getContext(), mList);
        listView1.setAdapter(adapter);
        getDataForServers();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (!isFirst){
                getDataForServers();
                isFirst=false;
            }
        }
    }

    public void setView() {
        springView1.setHeader(new RotationHeader(getContext()));
        springView1.setFooter(new RotationFooter(getContext()));
        springView1.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;
                        isLoad = true;
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
        listView1.setOnItemClickListener((parent, view1, position, id) -> {
            Energy8List.Energy8 item = (Energy8List.Energy8) parent.getAdapter().getItem(position);
            startActivity(new Intent(mActivity , SelectionDetailActivity.class).putExtra("sid", item.getSelectedInfoId()));
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public Energy8InformationPresenter getPresenter() {
        return new Energy8InformationPresenter();
    }

    private void getDataForServers() {
        presenter.onResume(mActivity, String.valueOf(currentPage));

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
                ToastUtils.showToast(mActivity,getString(R.string.no_data));
                return;
            }
            mList.addAll(info.getResobj());
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void list_null() {
        isLoad = false;
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
