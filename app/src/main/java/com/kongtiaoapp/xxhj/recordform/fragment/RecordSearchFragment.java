package com.kongtiaoapp.xxhj.recordform.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.UserListActivity;
import com.kongtiaoapp.xxhj.adapter.RecordSearchAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.RecordSearchListBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.RecordSearchP;
import com.kongtiaoapp.xxhj.mvp.view.RecordSearchFragmentV;
import com.kongtiaoapp.xxhj.recordform.activity.RecordSearchDetailsActivity;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;
import com.kongtiaoapp.xxhj.ui.timeselector.activity.TimePickerView;
import com.kongtiaoapp.xxhj.ui.view.CustomViewPager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.  fragment_record_search
 */
public class RecordSearchFragment extends BaseFragment<RecordSearchP, RecordSearchFragmentV> implements RecordSearchFragmentV, View.OnClickListener {


    private int whichFragment = 0;

    public RecordSearchFragment() {
        // Required empty public constructor
    }


    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.springView)
    SpringView springView;
    TextView txt_starttime;//开始时间
    TextView txt_endtime;//结束时间
    Button btn_search;//查询功能按钮
    private String start_time = "", end_time = "";
    private boolean isRefresh = true;
    private boolean isSearch = false;
    private boolean isResume = true;//true  走onResume方法的加载数据   结合isFirst使用
    private boolean isFirst = true;
    private int currentPage = 1;
    List<RecordSearchListBean.ResobjBean.DataBean> list, listAll;
    private RecordSearchAdapter adapter = null;
    private CustomViewPager vp;
    private TextView txt_nodata;
    private TextView txt_please;
    private String userId = "";
    private String recordType = "";//记录的是能源管理  还是暖通空调  或者变配电
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
    public RecordSearchP getPresenter() {
        return new RecordSearchP();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;

    }

    @Override
    public void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            recordType = bundle.getString("recordType");
            whichFragment = bundle.getInt("position");
        }
        View view = LayoutInflater.from(mActivity).inflate(R.layout.record_running_head, null, false);
        RelativeLayout rl_title = ((RelativeLayout) view.findViewById(R.id.rl_title));
        LinearLayout line_recordPeople = (LinearLayout) view.findViewById(R.id.line_recordPeople);
        rl_title.setVisibility(View.GONE);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_back);
        txt_starttime = ((TextView) view.findViewById(R.id.txt_starttime));
        txt_endtime = ((TextView) view.findViewById(R.id.txt_endtime));
        btn_search = ((Button) view.findViewById(R.id.btn_search));
        txt_nodata = ((TextView) view.findViewById(R.id.txt_nodata));
        txt_please = ((TextView) view.findViewById(R.id.txt_please));
        line_recordPeople.setVisibility(View.GONE);//先隐藏了  各个录表模块获取人员列表没有做
        imageView.setOnClickListener(this);
        btn_search.setOnClickListener(this);
        txt_starttime.setOnClickListener(this);
        txt_endtime.setOnClickListener(this);
        txt_please.setOnClickListener(this);
        listView.addHeaderView(view);
        list = new ArrayList<>();
        listAll = new ArrayList<>();
        adapter = new RecordSearchAdapter(mActivity, listAll);
        listView.setAdapter(adapter);
     /*   springView.setHeader(new RotationHeader(mActivity));
        springView.setFooter(new RotationFooter(mActivity));*/
       /* springView.setListener(new SpringView.OnFreshListener() {
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
        });*/

    }

    @Override
    public void onResume() {
        super.onResume();

        if (whichFragment == 0) {
            if (isFirst) {
                isFirst = !isFirst;
                getDataForService();
            } else {
                if (isResume) {
                    getDataForService();
                }
            }
        } else {
            if (isResume) {
                getDataForService();
            }
        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (whichFragment != 0) {
                isResume = true;
                getDataForService();
            }
        } else {
            isResume = false;//不可见  不走onResume方法
        }

    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_record_search, null);
    }


    private void setdatePicker(final int timeType) {
        // 时间选择器
        TimePickerView pvTime = null;
        pvTime = new TimePickerView(mActivity, TimePickerView.Type.YEAR_MONTH_DAY);
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                String time = getTime(date);
                if (timeType == 1) {
                    txt_starttime.setText(time);
                    start_time = time;
                } else if (timeType == 0) {
                    txt_endtime.setText(time);
                    end_time = time;
                }
            }
        });
        pvTime.show();
    }

    public String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private void getDataForService() {
        List<String> list_param = new ArrayList<>();
        list_param.add(String.valueOf(currentPage));
        list_param.add(recordType);
        list_param.add(start_time);
        list_param.add(end_time);
        list_param.add(txt_please.getText().toString().trim());//人名
        presenter.getData(mActivity, list_param);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_starttime:
                setdatePicker(1);//1代表开始时间
                break;
            case R.id.txt_endtime:
                setdatePicker(0);//0代表结束时间
                break;
            case R.id.btn_search:
                isRefresh = true;
                isSearch = true;
                getDataForService();
                break;
            case R.id.txt_please:
                Intent intent = new Intent(mActivity, UserListActivity.class);
                intent.putExtra("type", 2);
                startActivityForResult(intent, 1000);
                break;
            default:

                break;
        }
    }

    @OnItemClick({R.id.listView})
    public void onItemClick(int position) {
        //listView.addHeadView  所以position-1
        RecordSearchListBean.ResobjBean.DataBean dataBean = listAll.get(position - 1);
        Intent intent = new Intent(mActivity, RecordSearchDetailsActivity.class);
        intent.putExtra("title", dataBean.getName());
        intent.putExtra("time", dataBean.getTime());
        intent.putExtra("recordTime", dataBean.getRecordTime());
        intent.putExtra("sensorId", dataBean.getSensorId());
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 10001) {
            if (data != null) {
                txt_please.setText(data.getStringExtra("name"));
                userId = data.getStringExtra("uid");
            }

        }
    }

    @Override
    public void setList(Object data) {
        RecordSearchListBean bean = (RecordSearchListBean) data;
        txt_nodata.setVisibility(View.GONE);
        list.clear();
        if (isRefresh) {
            listAll.clear();
        }
        list = bean.getResobj().getData();
        listAll.addAll(list);
        adapter.setList(listAll);
        adapter.notifyDataSetChanged();
        isSearch = false;
    }

    @Override
    public void list_null(Object data) {
        txt_nodata.setVisibility(View.VISIBLE);
        listAll.clear();
        isSearch = false;
        currentPage = 1;
        adapter.notifyDataSetChanged();
    }


    @Override
    public void error() {
        txt_nodata.setVisibility(View.GONE);
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}