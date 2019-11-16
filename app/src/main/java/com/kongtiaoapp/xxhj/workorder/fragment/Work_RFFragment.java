package com.kongtiaoapp.xxhj.workorder.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.GiveWaterSystemActivity;
import com.kongtiaoapp.xxhj.adapter.BPDAdapter;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.Work_RFPresenter;
import com.kongtiaoapp.xxhj.mvp.view.Work_RFView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Work_RFFragment extends BaseFragment<Work_RFPresenter, Work_RFView> implements Work_RFView {

    @BindView(R.id.gridview)
    GridView gridView;
    Intent intent = null;

    public Work_RFFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public Work_RFPresenter getPresenter() {
        return new Work_RFPresenter();
    }

    @Override
    public void initData() {
        super.initData();
        List<String> list = new ArrayList<>();
        list.add("给水系统");
        list.add("中水系统");
        list.add("热水系统");
        list.add("强电系统");
        list.add("弱电系统");
        list.add("新风机房");
        list.add("大楼巡视");
        list.add("故障维修");
        BPDAdapter adapter = new BPDAdapter(list, mActivity);
        gridView.setAdapter(adapter);
    }

    @OnItemClick(R.id.gridview)
    public void onItemClick(int position) {
        if (position == 0) {
            intent = new Intent(mActivity, GiveWaterSystemActivity.class);
            intent.putExtra("type", "0");
            startActivity(intent);
        } /*else if (position == 1) {
            intent = new Intent(mActivity, KGGActivity.class);
            intent.putExtra("type", "1");
            startActivity(intent);
        } else if (position == 2) {
            intent = new Intent(mActivity, ZLPActivity.class);
            startActivity(intent);
        }*/
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_bpd, null);
    }

    @Override
    public void setText(Object data) {

    }
}
