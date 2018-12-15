package com.kongtiaoapp.xxhj.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.KGGActivity;
import com.kongtiaoapp.xxhj.activites.ZLPActivity;
import com.kongtiaoapp.xxhj.adapter.BPDAdapter;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.BPDPresenter;
import com.kongtiaoapp.xxhj.mvp.view.BPDView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class BPD_RFFragment extends BaseFragment<BPDPresenter, BPDView> implements BPDView {
    @BindView(R.id.gridview)
    GridView gridView;
    Intent intent = null;

    public BPD_RFFragment() {
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
    public BPDPresenter getPresenter() {
        return new BPDPresenter();
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_bpd, null);
    }

    @Override
    public void initData() {
        super.initData();
        List<String> list = new ArrayList<>();
        list.add("201开关柜");
        list.add("202开关柜");
        list.add("直流屏");
        list.add("1#变压器");
        list.add("2#变压器");
        list.add("3#变压器");
        list.add("发电机组");
        list.add("其他");
        BPDAdapter adapter = new BPDAdapter(list, mActivity);
        gridView.setAdapter(adapter);
    }

    @OnItemClick(R.id.gridview)
    public void onItemClick(int position) {
        if (position == 0) {
            intent = new Intent(mActivity, KGGActivity.class);
            intent.putExtra("type", "0");
            startActivity(intent);
        } else if (position == 1) {
            intent = new Intent(mActivity, KGGActivity.class);
            intent.putExtra("type", "1");
            startActivity(intent);
        } else if (position == 2) {
            intent = new Intent(mActivity, ZLPActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public void setText(Object data) {

    }
}
