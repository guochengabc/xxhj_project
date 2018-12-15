package com.kongtiaoapp.xxhj.hvac.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.ParamSettingAdapter;
import com.kongtiaoapp.xxhj.bean.ParamSettingBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.ParamSettingP;
import com.kongtiaoapp.xxhj.mvp.view.ParamSettingV;
import com.kongtiaoapp.xxhj.ui.view.CustomViewPager;
import com.kongtiaoapp.xxhj.ui.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.  报警阈值
 */
public class ParamSettingFragment extends BaseFragment<ParamSettingP, ParamSettingV> implements ParamSettingV {
    @BindView(R.id.lv_paramSetting)
    MyListView lv_paramSetting;
    @BindView(R.id.txt_nodata)
    TextView txt_nodata;
    private CustomViewPager vp;
    List<ParamSettingBean.ResobjBean.ColDataBean> listAll = new ArrayList<>();
    ParamSettingAdapter adapter;
    private int help = 0;

    public ParamSettingFragment() {
        // Required empty public constructor
    }

    public ParamSettingFragment(CustomViewPager vp) {
        this.vp = vp;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        if (vp != null) {
            vp.setObjectForPosition(rootView, 0);
        }
        return rootView;
    }

    @Override
    public ParamSettingP getPresenter() {
        return new ParamSettingP();
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_param_setting, null);
    }

    @Override
    public void initData() {
        super.initData();
        View inflate = LayoutInflater.from(mActivity).inflate(R.layout.footer_paramsetting, null);
        TextView txt_modify = ((TextView) inflate.findViewById(R.id.txt_modify));//修改
        TextView txt_save = ((TextView) inflate.findViewById(R.id.txt_save));//保存
        TextView txt_help = ((TextView) inflate.findViewById(R.id.txt_help));//文本帮助
        ImageView img_help = ((ImageView) inflate.findViewById(R.id.img_help));//帮助
        txt_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!listAll.isEmpty()) {
                    adapter.getNoModify(false);
                }
            }
        });
        txt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!listAll.isEmpty()) {
                    adapter.getNoModify(true);
                }
            }
        });
        img_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++help;
                if (help % 2 == 0) {
                    txt_help.setVisibility(View.GONE);
                } else {
                    txt_help.setVisibility(View.VISIBLE);
                }
            }
        });
        lv_paramSetting.addFooterView(inflate);
        adapter = new ParamSettingAdapter(mActivity, listAll);
        lv_paramSetting.setAdapter(adapter);
        presenter.onResume(mActivity);
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void setList(Object data) {
        ParamSettingBean bean = (ParamSettingBean) data;
        ParamSettingBean.ResobjBean resobj = bean.getResobj();
        listAll = resobj.getColData();
        adapter.setList(listAll);
    }

    @Override
    public void listNull() {
        txt_nodata.setText(View.VISIBLE);
        lv_paramSetting.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
