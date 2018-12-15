package com.kongtiaoapp.xxhj.prefessionalknowledge.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.MomentsDetailActivity;
import com.kongtiaoapp.xxhj.prefessionalknowledge.adapter.EnergyListAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.Moments;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.HotListPresenter;
import com.kongtiaoapp.xxhj.mvp.view.HotListView;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 热门,同城,同行共用的更多页面
 * Created by Luoye on 2016-6-16.
 */
public class HotListActivity extends BaseActivity<HotListPresenter, HotListView> implements HotListView, EnergyListAdapter.OperationListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.springView)
    SpringView springView;

    private List<Moments.MomentInfo> mList = new ArrayList<>();
    private EnergyListAdapter hotListAdapter;
    private String from;
    private boolean isWelldone;
    private boolean IsCollection;
    private int current;

    private int currentPage = 1;
    private boolean isRefresh = true;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS:
                    getDataForService();
                    springView.onFinishFreshAndLoad();
                    break;
                case ConstantValue.JIAZAI_SUCESS:
                    getDataForService();
                    springView.onFinishFreshAndLoad();
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected int initContentView() {
        from = getIntent().getStringExtra("from");
        return R.layout.actiity_hotlist;
    }

    @Override
    protected void initView() {
        if (ConstantValue.COMMUNITY_HOT.equals(from)) {
            tvTitle.setText("热门");
        } else if (ConstantValue.COMMUNITY_SAME_CITY.equals(from)) {
            tvTitle.setText("同城");
        } else if (ConstantValue.COMMUNITY_SAME_HANG.equals(from)) {
            tvTitle.setText("同行");
        }

        springView.setHeader(new RotationHeader(this));
        springView.setFooter(new RotationFooter(this));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;
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


    }

    @Override
    protected void initListener() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Moments.MomentInfo momentInfo = (Moments.MomentInfo) parent.getItemAtPosition(position);
            Intent intent = new Intent(mContext, MomentsDetailActivity.class);
            intent.putExtra("MomentInfo", momentInfo);
            startActivity(intent);
        });
    }

    @Override
    protected void initData() {
        hotListAdapter = new EnergyListAdapter(this, mList);
        hotListAdapter.setOperationListener(this);
        listView.setAdapter(hotListAdapter);
        getDataForService();
    }

    @Override
    protected HotListPresenter getPresenter() {
        return new HotListPresenter();
    }

    private void getDataForService() {
        List<String> list = new ArrayList<>();
        list.add(currentPage + "");
        list.add(from);
        presenter.onResume(this, list);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void praise(int position, boolean isPraise) {
        isWelldone = isPraise;
        current = position;
        String ispraise = isPraise ? "0" : "1";
        List list = new ArrayList<>();
        list.add(mList);
        list.add(position);
        list.add(ispraise);
        presenter.praise_p(this, list);
    }

    @Override
    public void favorite(int position, boolean isFavorite) {
        IsCollection = isFavorite;
        current = position;
        String isfavorite = isFavorite ? "0" : "1";
        List list = new ArrayList<>();
        list.add(mList);
        list.add(position);
        list.add(isfavorite);
        presenter.favorite_p(this, list);
    }

    @Override
    public void comment(Moments.MomentInfo momentInfo) {
        Intent intent = new Intent(mContext, MomentsDetailActivity.class);
        intent.putExtra("MomentInfo", momentInfo);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mList != null) {
            mList.clear();
            mList = null;
        }
    }

    @Override
    public void setList(Object response) {
        Moments moments = (Moments) response;
        if (isRefresh) {
            mList.clear();
            mList.addAll(moments.getResobj());
            hotListAdapter.notifyDataSetChanged();
        } else {
            if (moments.getResobj() == null || moments.getResobj().size() == 0) {
                ToastUtils.showToast(this, getString(R.string.no_data));
                return;
            }
            mList.addAll(moments.getResobj());
            hotListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void praise_view(Object data) {
        int number = Integer.valueOf(TextUtils.isEmpty(mList.get(current).getWelldoneNumber()) ? "0" : mList.get(current).getWelldoneNumber()).intValue();
        if (isWelldone) {
            mList.get(current).setIsWelldone("0");
            mList.get(current).setWelldoneNumber(String.valueOf(number - 1));
        } else {
            mList.get(current).setIsWelldone("1");
            mList.get(current).setWelldoneNumber(String.valueOf(number + 1));
        }
        hotListAdapter.notifyDataSetChanged();
    }

    @Override
    public void favorite_view(Object data) {
        int number = Integer.valueOf(TextUtils.isEmpty(mList.get(current).getWelldoneNumber()) ? "0" : mList.get(current).getWelldoneNumber()).intValue();
        if (IsCollection) {
            mList.get(current).setIsCollection("0");
//                        mList.get(current).setWelldoneNumber(String.valueOf(number - 1));
        } else {
            mList.get(current).setIsCollection("1");
//                        mList.get(current).setWelldoneNumber(String.valueOf(number + 1));
        }
        hotListAdapter.notifyDataSetChanged();
    }

    @Override
    public void setText(Object text) {

    }
}
