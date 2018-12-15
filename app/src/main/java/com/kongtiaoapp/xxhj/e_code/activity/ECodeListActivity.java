package com.kongtiaoapp.xxhj.e_code.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.ECodeListBean;
import com.kongtiaoapp.xxhj.e_code.adapter.ECodeListAdapter;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.LongPressP;
import com.kongtiaoapp.xxhj.mvp.view.LongPressV;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * 长按识别图中二维码
 */
public class ECodeListActivity extends BaseActivity<LongPressP, LongPressV> implements LongPressV {
    @BindView(R.id.sv_longPress)
    SpringView sv_longPress;
    @BindView(R.id.lv_longPress)
    ListView lv_longPress;
    private int whichModule = 0;//0代表暖通  1代表能源计量
    private int currentPage = 1;
    private boolean isRefresh;
    private List<ECodeListBean.ResobjBean> listAll = new ArrayList<>();
    private ECodeListAdapter adapter;
    /*刷新数据*/
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS:
                    getDataForServies();
                    sv_longPress.onFinishFreshAndLoad();
                    break;
                case ConstantValue.JIAZAI_SUCESS:
                    getDataForServies();
                    sv_longPress.onFinishFreshAndLoad();
                    break;

                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_long_press;
    }

    @Override
    protected void initView() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.include_ecode_list, null);
        lv_longPress.addHeaderView(inflate);
        TextView txt_nt = ((TextView) inflate.findViewById(R.id.txt_nt));//暖通列表
        TextView txt_ny = ((TextView) inflate.findViewById(R.id.txt_ny));//能源列表
        txt_nt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_nt.setTextColor(getResources().getColor(R.color.theme_color));
                txt_ny.setTextColor(getResources().getColor(R.color.a666666));
                isRefresh = true;
                currentPage = 1;
                whichModule = 0;
                getDataForServies();
            }
        });
        txt_ny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_nt.setTextColor(getResources().getColor(R.color.a666666));
                txt_ny.setTextColor(getResources().getColor(R.color.theme_color));
                isRefresh = true;
                currentPage = 1;
                whichModule = 1;
                getDataForServies();
            }
        });
    }

    @Override
    protected void initListener() {
        adapter = new ECodeListAdapter(this, listAll);
        lv_longPress.setAdapter(adapter);
        sv_longPress.setHeader(new RotationHeader(mContext));
        sv_longPress.setFooter(new RotationFooter(mContext));
        sv_longPress.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {//刷新
                new Thread(() -> {
                    isRefresh = true;
                    currentPage = 1;
                    handler.sendEmptyMessage(ConstantValue.SHUAXIN_SUCESS);
                }).start();
            }

            @Override
            public void onLoadmore() {//加载更多
                new Thread(() -> {
                    isRefresh = false;
                    currentPage++;
                    handler.sendEmptyMessage(ConstantValue.JIAZAI_SUCESS);
                }).start();
            }
        });
    }

    @Override
    protected void initData() {
        getDataForServies();
    }

    private void getDataForServies() {
        List<String> list = new ArrayList<>();
        list.add(currentPage + "");
        list.add(whichModule + "");
        presenter.onResume(this, list);
    }

    @Override
    protected LongPressP getPresenter() {
        return new LongPressP();
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @OnItemClick({R.id.lv_longPress})
    public void onIemClick(int position) {
        try {
            ECodeListBean.ResobjBean resobjBean = listAll.get(position-1);
            Intent intent = new Intent(ECodeListActivity.this, EcodeCreateActivity.class);
            intent.putExtra("device", (Serializable) resobjBean);
            intent.putExtra("whichModule",whichModule+"");
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            currentPage=1;
            isRefresh=true;
            getDataForServies();
        }
    }

    @Override
    public void setText(Object data) {


    }

    @Override
    public void setList(Object data) {
        List<ECodeListBean.ResobjBean> list = (List<ECodeListBean.ResobjBean>) data;
        if (isRefresh) {
            listAll.clear();
            listAll.addAll(list);
        } else {
            listAll.addAll(list);
        }
        adapter.adapterList(listAll);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
