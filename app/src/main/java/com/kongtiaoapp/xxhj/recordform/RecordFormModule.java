package com.kongtiaoapp.xxhj.recordform;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.RecordFormMAdapter;
import com.kongtiaoapp.xxhj.bean.RecordFormMBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.RecordFormMP;
import com.kongtiaoapp.xxhj.mvp.view.RecordFormMV;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 名称不可修改
 */
public class RecordFormModule extends BaseActivity<RecordFormMP, RecordFormMV> implements RecordFormMV {
    @BindView(R.id.gridView)
    GridView gridView;
    private RecordFormMAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_record_form_module;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        
    }

    @Override
    protected void initData() {
        /*String jlbd = AssetsUtils.readText(this, "jlbd.json");
        RecordFormMBean recordFormMBean = gson.fromJson(jlbd, RecordFormMBean.class);
        getList(recordFormMBean);*/
        presenter.onResume(this);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecordFormMBean.ResobjBean.RecordModuleBean moduleBean = (RecordFormMBean.ResobjBean.RecordModuleBean) adapter.getItem(position);
                if (!moduleBean.getIntentName().equals("")) {
                    try {
                        ComponentName com = new ComponentName(moduleBean.getPackageName(), moduleBean.getIntentName());
                        Intent intent = new Intent();
                        intent.setComponent(com);
                        intent.putExtra("title", moduleBean.getModuleName());
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                        ToastUtils.showToast(RecordFormModule.this, "缺少相应包文件");
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        ToastUtils.showToast(RecordFormModule.this, "请联系后台");
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                        ToastUtils.showToast(RecordFormModule.this, "请联系后台");
                    }

                }

            }
        });
    }

    @Override
    protected RecordFormMP getPresenter() {
        return new RecordFormMP();
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:

                break;
            default:

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

    @Override
    public void getList(Object data) {

        RecordFormMBean bean = (RecordFormMBean) data;
        List<RecordFormMBean.ResobjBean.RecordModuleBean> recordModule = bean.getResobj().getRecordModule();
        adapter = new RecordFormMAdapter(this, recordModule);
        gridView.setAdapter(adapter);
    }
}
