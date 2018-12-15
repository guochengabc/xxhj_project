package com.kongtiaoapp.xxhj.prefessionalknowledge.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.SpecialDetailActivity;
import com.kongtiaoapp.xxhj.adapter.SpecialArticleListAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.GetSpecialArticleList;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.Energy8ZhuanLanPresenter;
import com.kongtiaoapp.xxhj.mvp.view.Energy8ZhuanLanView;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Luoye on 2016-10-17.
 * 说明:专栏－＞文章列表
 */
public class Energy8ZhuanLanActivity extends BaseActivity<Energy8ZhuanLanPresenter, Energy8ZhuanLanView> implements Energy8ZhuanLanView {

    @BindView(R.id.tv_title)//文章标题
            TextView tvTitle;
    @BindView(R.id.iv_back)//返回上一界面
            ImageView ivBack;
    @BindView(R.id.tv_my_show)//展示
            TextView tvMyShow;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.iv_special)
    ImageView ivSpecial;
    @BindView(R.id.tv_special_title)
    TextView tvSTitle;
    @BindView(R.id.tv_special_content)
    TextView tvSContent;

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
    private SpecialArticleListAdapter adapter;
    private String sid;

    @Override
    protected int initContentView() {
        sid = getIntent().getStringExtra("sid");
        return R.layout.activity_energy_show;
    }

    @Override
    protected void initView() {
        tvTitle.setText("文章列表");
        tvMyShow.setVisibility(View.GONE);
        Picasso.with(mContext)
                .load(ConstantValue.URL + getIntent().getStringExtra("simgae"))
                .placeholder(R.mipmap.default_head)
                .into(ivSpecial);
        tvSTitle.setText(getIntent().getStringExtra("stitle"));
        tvSContent.setText(getIntent().getStringExtra("scontent"));

        springView.setHeader(new RotationHeader(this));
        springView.setFooter(new RotationFooter(this));
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

    /*设置监听事件*/
    @Override
    protected void initListener() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            GetSpecialArticleList.SpecialArticle item = (GetSpecialArticleList.SpecialArticle) parent.getAdapter().getItem(position);
            startActivity(new Intent(mContext, SpecialDetailActivity.class).putExtra("sid", item.getArticleId()));
        });
    }

    @Override
    protected void initData() {
        adapter = new SpecialArticleListAdapter(mContext, mList);
        listView.setAdapter(adapter);
        getDataForServies();
    }

    @Override
    protected Energy8ZhuanLanPresenter getPresenter() {
        return new Energy8ZhuanLanPresenter();
    }

    private void getDataForServies() {
        List<String> list = new ArrayList<>();
        list.add(sid);
        list.add(currentPage + "");
        presenter.onResume(this, list);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_back)
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
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
    public void setList(Object data) {
        GetSpecialArticleList dict = (GetSpecialArticleList) data;
        if (isRefresh) {
            mList.clear();
        }
        mList.addAll(dict.getResobj());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setText(Object text) {

    }
}
