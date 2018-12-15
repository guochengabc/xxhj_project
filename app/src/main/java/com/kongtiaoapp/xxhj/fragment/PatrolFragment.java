package com.kongtiaoapp.xxhj.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.PatrolPresenter;
import com.kongtiaoapp.xxhj.mvp.view.PatrolView;

/**
 * A simple {@link Fragment} subclass.  巡视
 */
public class PatrolFragment extends BaseFragment<PatrolPresenter,PatrolView> implements PatrolView {


    public PatrolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patrol, container, false);
    }

    @Override
    public PatrolPresenter getPresenter() {
        return new PatrolPresenter();
    }

    @Override
    protected View initView() {
        return null;
    }

    @Override
    public void setText(Object data) {

    }
}
