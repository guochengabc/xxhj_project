package com.kongtiaoapp.xxhj.activites;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pw.h57.popupbuttonlibrary.PopupButton;
import pw.h57.popupbuttonlibrary.bean.PopButtonBean;
import pw.h57.popupbuttonlibrary.inter.PopItemValue;

public class TestPopActivity extends BaseActivity<BasePresenterLpl,BaseView> implements BaseView, PopItemValue {
    @BindView(R.id.rl_test)
    RelativeLayout rl_test;
    @BindView(R.id.btn)
    PopupButton btn;
    @BindView(R.id.btn2)
    PopupButton btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_pop_workorder;
    }

    @Override
    protected void initView() {
        List<PopButtonBean.PopInnerBean> listOne = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            PopButtonBean.PopInnerBean bean = new PopButtonBean.PopInnerBean();
            bean.setId((i * i) + "");
            bean.setName("点击item" + i);
            listOne.add(bean);
        }
        List<PopButtonBean.PopInnerBean> pList = new ArrayList<>();
        final List<List<PopButtonBean.PopInnerBean>> cList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PopButtonBean.PopInnerBean bean = new PopButtonBean.PopInnerBean();
            bean.setName("绥化" + i);
            bean.setId((i * i) + "");
            pList.add(bean);
            List<PopButtonBean.PopInnerBean> t = new ArrayList<>();
            for (int j = 0; j < 25; j++) {
                PopButtonBean.PopInnerBean beans = new PopButtonBean.PopInnerBean();
                beans.setName(pList.get(i).getName() + "-红旗满族乡" + j);
                beans.setId((i * j) + "123");
                t.add(beans);
            }
            cList.add(t);
        }
        btn2.setDoubleClickData(this, pList, cList);
        btn.setOneClickData(this, listOne);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenterLpl getPresenter() {
        return new BasePresenterLpl() {
            @Override
            protected BaseModule getModel() {
                return new BaseModule() {
                    @Override
                    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {

                    }
                };
            }
        };
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void getBackBean(List<PopButtonBean.PopInnerBean> list) {
        StringBuffer name = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isCheck()) {
                if (i < list.size() - 1)
                    name.append(list.get(i).getName() + "-");
                else
                    name.append(list.get(i).getName());
            }
            btn.setText(name);
        }


    }

    @Override
    public void getDouleBackBean(PopButtonBean.PopInnerBean bean) {
        btn2.setText(bean.getName());
    }
}
