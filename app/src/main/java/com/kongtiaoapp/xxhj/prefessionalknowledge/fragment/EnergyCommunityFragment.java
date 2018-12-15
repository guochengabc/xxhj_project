package com.kongtiaoapp.xxhj.prefessionalknowledge.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.prefessionalknowledge.activity.HotListActivity;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.GetCommunityESMessage;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.EnergyCommunityPresenter;
import com.kongtiaoapp.xxhj.mvp.view.EnergyCommunityView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 节能圈中的社区界面的Framgent
 * Created by Louye 2016-6-8.
 */
public class EnergyCommunityFragment extends BaseFragment<EnergyCommunityPresenter, EnergyCommunityView> implements  EnergyCommunityView {

    @BindView(R.id.tv_hot)
    TextView tvHot;
    @BindView(R.id.iv_hot1)
    ImageView ivHot1;
    @BindView(R.id.iv_hot2)
    ImageView ivHot2;
    @BindView(R.id.iv_hot3)
    ImageView ivHot3;
    @BindView(R.id.tv_same_city)
    TextView tvSameCity;
    @BindView(R.id.tv_same_hang)
    TextView tvSameHang;
    @BindView(R.id.iv_city1)
    ImageView ivCity1;
    @BindView(R.id.iv_city2)
    ImageView ivCity2;
    @BindView(R.id.iv_city3)
    ImageView ivCity3;
    @BindView(R.id.iv_work1)
    ImageView ivWork1;
    @BindView(R.id.iv_work2)
    ImageView ivWork2;
    @BindView(R.id.iv_work3)
    ImageView ivWork3;
    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_energy_community, null);
        return view;
    }

    @Override
    public void initData() {
        presenter.onResume(mActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public EnergyCommunityPresenter getPresenter() {
        return new EnergyCommunityPresenter();
    }

    @OnClick({R.id.tv_hot, R.id.tv_same_city, R.id.tv_same_hang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_hot:
                startActivity(new Intent(App.application, HotListActivity.class).putExtra("from", ConstantValue.COMMUNITY_HOT));
                break;
            case R.id.tv_same_city:
                startActivity(new Intent(App.application, HotListActivity.class).putExtra("from", ConstantValue.COMMUNITY_SAME_CITY));
                break;
            case R.id.tv_same_hang:
                startActivity(new Intent(App.application, HotListActivity.class).putExtra("from", ConstantValue.COMMUNITY_SAME_HANG));
                break;
        }
    }
    @Override
    public void setList(Object response) {
        GetCommunityESMessage data= (GetCommunityESMessage) response;
        if (data !=null && data.getResobj() !=null) {
            List<String> A = data.getResobj().getA();
            List<String> B = data.getResobj().getB();
            List<String> C = data.getResobj().getC();

            //A 热门
            if (A!=null&&A.size() ==1) {
                Picasso.with(mActivity).load(ConstantValue.URL +A.get(0)).placeholder(R.mipmap.picture).into(ivHot1);
            }
            if (A!=null&&A.size() ==2) {
                Picasso.with(mActivity).load(ConstantValue.URL +A.get(0)).placeholder(R.mipmap.picture).into(ivHot1);
                Picasso.with(mActivity).load(ConstantValue.URL +A.get(1)).placeholder(R.mipmap.picture).into(ivHot2);
            }
            if (A!=null&&A.size() >=3) {
                Picasso.with(mActivity).load(ConstantValue.URL +A.get(0)).placeholder(R.mipmap.picture).into(ivHot1);
                Picasso.with(mActivity).load(ConstantValue.URL +A.get(1)).placeholder(R.mipmap.picture).into(ivHot2);
                Picasso.with(mActivity).load(ConstantValue.URL +A.get(2)).placeholder(R.mipmap.picture).into(ivHot3);
            }

            //B 同城
            if (B!=null&&B.size() ==1) {
                Picasso.with(mActivity).load(ConstantValue.URL +B.get(0)).placeholder(R.mipmap.picture).into(ivCity1);
            }
            if (B!=null&&B.size() ==2) {
                Picasso.with(mActivity).load(ConstantValue.URL +B.get(0)).placeholder(R.mipmap.picture).into(ivCity1);
                Picasso.with(mActivity).load(ConstantValue.URL +B.get(1)).placeholder(R.mipmap.picture).into(ivCity2);
            }
            if (B!=null&&B.size() >=3) {
                Picasso.with(mActivity).load(ConstantValue.URL +B.get(0)).placeholder(R.mipmap.picture).into(ivCity1);
                Picasso.with(mActivity).load(ConstantValue.URL +B.get(1)).placeholder(R.mipmap.picture).into(ivCity2);
                Picasso.with(mActivity).load(ConstantValue.URL +B.get(2)).placeholder(R.mipmap.picture).into(ivCity3);
            }

            //C 同行
            if (C!=null&&C.size() ==1) {
                Picasso.with(mActivity).load(ConstantValue.URL +C.get(0)).placeholder(R.mipmap.picture).into(ivWork1);
            }
            if (C!=null&&C.size() ==2) {
                Picasso.with(mActivity).load(ConstantValue.URL +C.get(0)).placeholder(R.mipmap.picture).into(ivWork1);
                Picasso.with(mActivity).load(ConstantValue.URL +C.get(1)).placeholder(R.mipmap.picture).into(ivWork2);
            }
            if (C!=null&&C.size() >=3) {
                Picasso.with(mActivity).load(ConstantValue.URL +C.get(0)).placeholder(R.mipmap.picture).into(ivWork1);
                Picasso.with(mActivity).load(ConstantValue.URL +C.get(1)).placeholder(R.mipmap.picture).into(ivWork2);
                Picasso.with(mActivity).load(ConstantValue.URL +C.get(2)).placeholder(R.mipmap.picture).into(ivWork3);
            }
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
