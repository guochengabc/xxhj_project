package com.kongtiaoapp.xxhj.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.BPDAdapter;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class BPDActivity extends BaseActivity<BasePresenterLpl, BaseView> implements BaseView {
    @BindView(R.id.gridview)
    GridView gridView;
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_bpd;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        List<String> list = new ArrayList<>();
        list.add("201开关柜");
        list.add("202开关柜");
        list.add("直流屏");
        list.add("1#变压器");
        list.add("2#变压器");
        list.add("3#变压器");
        list.add("发电机组");
        list.add("其他");
        BPDAdapter adapter = new BPDAdapter(list, this);
        gridView.setAdapter(adapter);
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

    @OnItemClick(R.id.gridview)
    public void onItemClick(int position) {
        if (position == 0) {
            intent = new Intent(this, KGGActivity.class);
            intent.putExtra("type", "0");
            startActivity(intent);
        } else if (position == 1) {
            intent = new Intent(this, KGGActivity.class);
            intent.putExtra("type", "1");
            startActivity(intent);
        } else if (position == 2) {
            intent = new Intent(this, ZLPActivity.class);
            startActivity(intent);
        }
    }

    @OnClick({R.id.txt_left, R.id.iv_back})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.txt_left:
                intent = new Intent(this, YXJCActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void setText(Object data) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
