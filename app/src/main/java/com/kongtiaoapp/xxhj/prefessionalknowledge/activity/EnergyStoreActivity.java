package com.kongtiaoapp.xxhj.prefessionalknowledge.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.prefessionalknowledge.adapter.EnergyStoreAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.Dict;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.net.HttpLoader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 节能商城页面
 * Created by Luoye on 2016-6-16.
 */
public class EnergyStoreActivity extends BaseActivity implements HttpLoader.ResponseListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_my_show)
    TextView tvMyShow;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.springView)
    SpringView springView;

    private List mList = new ArrayList();

    private int currentPage = 1;
    private boolean isRefresh = true;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS:
                    getDataForServies();
                    springView.onFinishFreshAndLoad();
                    break;
                case ConstantValue.JIAZAI_SUCESS:
                    getDataForServies();
                    springView.onFinishFreshAndLoad();
                    break;

                default:
                    break;
            }
        }
    };
    private EnergyStoreAdapter adapter;

    @Override
    protected int initContentView() {
        return R.layout.activity_energy_show;
    }

    @Override
    protected void initView() {
        tvTitle.setText("节能商城");
        tvMyShow.setVisibility(View.GONE);

//        springView.setHeader(new RotationHeader(this));
//        springView.setFooter(new RotationFooter(this));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Thread(() -> {
                    isRefresh = true;
                    currentPage = 1;
                    handler.sendEmptyMessage(ConstantValue.SHUAXIN_SUCESS);
                }).start();
            }

            @Override
            public void onLoadmore() {
                new Thread(() -> {
                    isRefresh = false;
                    currentPage++;
                    handler.sendEmptyMessage(ConstantValue.JIAZAI_SUCESS);
                }).start();
            }
        });
    }

    @Override
    protected void initListener() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Dict.DictData item = (Dict.DictData) parent.getAdapter().getItem(position);
            startActivity(new Intent(mContext, EnergyStoreListActivity.class).putExtra("type", item.getCode()));
        });
    }

    @Override
    protected void initData() {
        adapter = new EnergyStoreAdapter(mContext, mList);
        listView.setAdapter(adapter);
        getDataForServies();
    }

    @Override
    protected BasePresenterLpl getPresenter() {
        return null;
    }

    private void getDataForServies() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("uid", App.sp.getUid());
        map.put("dictCode", "ProductType");
//        map.put("currentPage", String.valueOf(currentPage));
        Map<String, String> params = new HashMap<String, String>();
        params.put(HttpMethod.KEY, map2Json(HttpMethod.GETDICTDATA, map));

        Log.e(TAG, "GetDictData:  " + params);
        showProgressDialog();
        HttpLoader.post(ConstantValue.HTTP_URL, params, Dict.class, ConstantValue.GETDICTDATA, this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, RBResponse response) {
        dismissProgressDialog();
        switch (requestCode) {
            case ConstantValue.GETDICTDATA:
                if (response.getCode() == 40000) {
                    Dict dict = (Dict) response;
                    if (isRefresh) {
                        mList.clear();
                    }
                    mList.addAll(dict.getResobj());
                    adapter.notifyDataSetChanged();
                } else if (response.getCode() == 40005) {
                    ToastUtils.showToast(this,getString(R.string.all_load));
                } else {
                    ToastUtils.showToast(this,getString(R.string.loading_fail));
                }
                break;
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        dismissProgressDialog();
    }

    @Override
    public void onGetResponseFailed(int requestCode, int code) {
        dismissProgressDialog();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mList != null) {
            mList.clear();
            mList = null;
        }
    }
}
