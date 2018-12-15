package com.kongtiaoapp.xxhj.prefessionalknowledge.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.UserListActivity;
import com.kongtiaoapp.xxhj.duty.adapter.ZhiBanRecordAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.GetDutyRecordList;
import com.kongtiaoapp.xxhj.duty.activity.ZhiBanDetailActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.HistoryPresenter;
import com.kongtiaoapp.xxhj.mvp.view.HistoryView;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;
import com.kongtiaoapp.xxhj.ui.timeselector.activity.TimePickerView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Luoye on 2016-9-5.
 * 说明:历史记录
 */
public class HistoryActivity extends BaseActivity<HistoryPresenter, HistoryView> implements HistoryView, View.OnClickListener {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.et_time_start)
    TextView etTimeStart;
    @BindView(R.id.et_time_end)
    TextView etTimeEnd;
    @BindView(R.id.query)
    TextView query;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.txt_select_result)
    TextView txt_select_result;
    List<GetDutyRecordList.RecordList> mList = new ArrayList<>();
    @BindView(R.id.et_name)
    TextView etName;
    @BindView(R.id.ll_name)
    LinearLayout llName;

    private String startTime = "";//起始时间
    private String endTime = "";//结束时间
    private String name;//值班人员姓名

    private int currentPage = 1;
    private boolean isRefresh = true;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS://刷新数据
                    getDataForServers();
                    springView.onFinishFreshAndLoad();
                    break;
                case ConstantValue.JIAZAI_SUCESS://加载数据
                    getDataForServers();
                    springView.onFinishFreshAndLoad();
                    break;

                default:
                    break;
            }
        }
    };
    private ZhiBanRecordAdapter adapter;

    @Override
    protected int initContentView() {
        return R.layout.activity_history;
    }

    @Override
    protected void initView() {
        if (App.sp.isLeader().equals("BB")) {
            llName.setVisibility(View.VISIBLE);
        } else {
            llName.setVisibility(View.GONE);
        }

        etTimeStart.setOnClickListener(this);
        etTimeEnd.setOnClickListener(this);
        springView.setHeader(new RotationHeader(mContext));
        springView.setFooter(new RotationFooter(mContext));
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GetDutyRecordList.RecordList item = (GetDutyRecordList.RecordList) parent.getAdapter().getItem(position);
                startActivity(new Intent(HistoryActivity.this, ZhiBanDetailActivity.class).putExtra("dutyId", item.getDutyId()));
            }
        });
    }

    @Override
    protected void initData() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//上传
        SimpleDateFormat txt_format=new SimpleDateFormat("yyyy-MM-dd HH");//展现
        Date date1 = new Date();
        String date_end = format.format(date1);
        String txt_dat_end_show= txt_format.format(date1);//结束时间
        date1.setHours(0);
        String txtdate_start_show=txt_format.format(date1);
        date1.setMinutes(0);
        date1.setSeconds(0);
        String date_start = format.format(date1);
        etTimeStart.setText(txtdate_start_show + "时");
        etTimeEnd.setText(txt_dat_end_show + "时");
        startTime = date_start;
        endTime = date_end;
        adapter = new ZhiBanRecordAdapter(this, mList);
        listView.setAdapter(adapter);
        currentPage = 1;
        getDataForServers();
    }

    @Override
    protected HistoryPresenter getPresenter() {
        return new HistoryPresenter();
    }

    /*
    * 提交查询时间    获取交接班的状态
    * */
    private void getDataForServers() {
        name = etName.getText().toString();
        List<String> list = new ArrayList<>();
        list.add(currentPage + "");
        list.add(startTime);
        list.add(endTime);
        list.add(name);
        presenter.onResume(this, list);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.query, R.id.et_name, R.id.iv_back, R.id.et_time_start, R.id.et_time_end})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.query:
                isRefresh = true;
                currentPage = 1;
                getDataForServers();
                break;
            case R.id.et_name:
                Intent intent = new Intent(HistoryActivity.this, UserListActivity.class);
                intent.putExtra("type", 3);
                startActivityForResult(intent, 1000);
                break;
            case R.id.iv_back:
                close_key();
                finish();
                break;
            case R.id.et_time_start://开始时间
                // 时间选择器
                TimePickerView pvTime = new TimePickerView(HistoryActivity.this, TimePickerView.Type.ALL);
                // 控制时间范围
                // Calendar calendar = Calendar.getInstance();
                // pvTime.setRange(calendar.get(Calendar.YEAR) - 20,
                // calendar.get(Calendar.YEAR));
                pvTime.setTime(new Date());
                pvTime.setCyclic(false);
                pvTime.setCancelable(true);
                pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");//显示的是小时
                        startTime = getTime(date);//实际提交的是小时、分、秒
                        etTimeStart.setText(format.format(date) + "时");
                    }
                });
                pvTime.show();
                break;
            case R.id.et_time_end://结束时间
                // 时间选择器
                TimePickerView pvTime1 = new TimePickerView(HistoryActivity.this, TimePickerView.Type.ALL);
                // 控制时间范围
                // Calendar calendar = Calendar.getInstance();
                // pvTime.setRange(calendar.get(Calendar.YEAR) - 20,
                // calendar.get(Calendar.YEAR));
                pvTime1.setTime(new Date());
                pvTime1.setCyclic(false);
                pvTime1.setCancelable(true);
                pvTime1.setOnTimeSelectListener(date -> {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");//记录的是TextView里面显示
                    endTime = getTime(date);//记录的是提交服务器的时间
                    etTimeEnd.setText(format.format(date) + "时");
                });
                pvTime1.show();
                break;

            default:
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 10002) {
            name = data.getStringExtra("user");
            etName.setText(name);
        }
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
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
        txt_select_result.setText(getResources().getString(R.string.select_result));
        GetDutyRecordList info = (GetDutyRecordList) response;
        if (isRefresh) {
            mList.clear();
            mList.addAll(info.getResobj());
            adapter.notifyDataSetChanged();
        } else {
            if (info.getResobj() == null || info.getResobj().size() == 0) {
                ToastUtils.showToast(this,getString(R.string.no_data));
            }
            mList.addAll(info.getResobj());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setListClear() {
        if (currentPage == 1) {
            mList.clear();
            txt_select_result.setText(getResources().getString(R.string.no_data));
        } else {
            txt_select_result.setText(getResources().getString(R.string.all_load));
        }

        adapter.notifyDataSetChanged();

    }

    @Override
    public void setText(Object text) {

    }
}
