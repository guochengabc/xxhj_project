package com.kongtiaoapp.xxhj.workorder.activity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.Report_Running_RecordBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.ReportWorkPresenter;
import com.kongtiaoapp.xxhj.mvp.view.ReportWorkView;
import com.kongtiaoapp.xxhj.ui.timeselector.activity.TimePickerView;
import com.kongtiaoapp.xxhj.ui.view.ProgressBar_Report_Data;
import com.kongtiaoapp.xxhj.utils.Byte_To_mUtils;
import com.kongtiaoapp.xxhj.utils.DateUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 导出工单记录
 */
public class ReportWorkActivity extends BaseActivity<ReportWorkPresenter, ReportWorkView> implements ReportWorkView {
    @BindView(R.id.txt_starttime)//开始时间
            TextView txt_starttime;
    @BindView(R.id.txt_endtime)//结束时间
            TextView txt_endtime;
    @BindView(R.id.progress)
    ProgressBar_Report_Data progress;
    @BindView(R.id.rela_loading)
    RelativeLayout rela_loading;
    @BindView(R.id.txt_loading)
    TextView txt_loading;
    @BindView(R.id.txt_loading_shu)
    TextView txt_loading_shu;
    private String start_time = "", end_time = "";
    private String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "/" + "00a_工单记录XiaoXiDownloads" + "/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_report_work;
    }

    @Override
    protected void initView() {
        progress.setMax(100);
        Date yDate = new Date(new Date().getTime() - 24 * 60 * 60 * 1000);//昨天的时间
        SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
        txt_starttime.setText(formats.format(yDate) + " " + "17时");
        txt_endtime.setText(DateUtils.getNowTime("yyyy-MM-dd") + " " + "17时");
        txt_starttime.setTag(formats.format(yDate) + " " + "17:00:00");
        txt_endtime.setTag(DateUtils.getNowTime("yyyy-MM-dd") + " " + "17:00:00");
        start_time = txt_starttime.getTag().toString();
        end_time = txt_endtime.getTag().toString();

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_report, R.id.txt_starttime, R.id.txt_endtime, R.id.iv_back})
    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_report:
                List<String> list = new ArrayList<>();
                list.add(start_time);
                list.add(end_time);
                presenter.onResume(this, list);
                break;
            case R.id.txt_starttime:
                setdatePicker(1);//1  代表开始时间  0代表结束时间
                break;
            case R.id.txt_endtime:
                setdatePicker(0);
                break;
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }

    private void setdatePicker(final int timeType) {
        // 时间选择器
        TimePickerView pvTime = null;
        pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY_HOURS_MIN);
        pvTime.setTime(new Date());
        pvTime.setCyclic(true);
        pvTime.setCancelable(true);
        pvTime.setOnTimeSelectListener(date -> {
            String time = getTime(date);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (timeType == 1) {
                txt_starttime.setText(time + "时");//开始时间
                start_time = format.format(date);
            } else if (timeType == 0) {//结束时间
                txt_endtime.setText(time + "时");
                end_time = format.format(date);
            }
        });
        pvTime.show();
    }

    public String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
        return format.format(date);
    }

    @Override
    protected ReportWorkPresenter getPresenter() {
        return new ReportWorkPresenter();
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void getDataSuccedd(Report_Running_RecordBean response) {
        Report_Running_RecordBean bean = (Report_Running_RecordBean) response;
        rela_loading.setVisibility(View.VISIBLE);
        progress.setVisibility(View.VISIBLE);
        if (bean.getResobj().getFileUrl() != null && !bean.getResobj().getFileUrl().equals("")) {
            downDocument(bean.getResobj().getFileUrl());//下载文件  z这个到底能不能请求下来呢   谁能告诉我下哈
        }
    }

    @Override
    public void succeed() {
        ToastUtils.showToast(this, "亲,导出数据成功了");
    }

    @Override
    public void onfinish(Object data) {
        txt_loading_shu.setVisibility(View.INVISIBLE);
        txt_loading.setText("下载完成");
        txt_loading.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.INVISIBLE);
        rela_loading.setVisibility(View.GONE);
        progress.setVisibility(View.GONE);
    }

    @Override
    public void onCancelled() {
        ToastUtils.showToast(this, "取消下载");
        txt_loading_shu.setVisibility(View.INVISIBLE);
        txt_loading.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.INVISIBLE);
        rela_loading.setVisibility(View.GONE);
        progress.setVisibility(View.GONE);
    }

    @Override
    public void onError(String type) {
        if (type.equals("4")) {
            txt_loading_shu.setVisibility(View.INVISIBLE);
            txt_loading.setVisibility(View.INVISIBLE);
            progress.setVisibility(View.INVISIBLE);
            rela_loading.setVisibility(View.GONE);
            progress.setVisibility(View.GONE);
            ToastUtils.showToast(this, "下载失败");
        }
    }

    @Override
    public void onLoading(long total, long current, boolean isDownloading) {
        int down_percent = (int) ((current * 100 / total));
        txt_loading.setText("正在下载:");
        progress.setVisibility(View.VISIBLE);
        txt_loading.setVisibility(View.VISIBLE);
        txt_loading_shu.setVisibility(View.VISIBLE);
        txt_loading_shu.setText(Byte_To_mUtils.Byte2M(current));
        progress.setProgress(down_percent);
    }

    private void downDocument(String url) {
        if (url.isEmpty() || url.length() == 0) {
            return;
        }
        final String fileurl = url.substring(url.lastIndexOf("/") + 1, url.length());
        try {
            List<String> list = new ArrayList<>();
            list.add(ConstantValue.URLS + url.substring(0, url.lastIndexOf("/") + 1) + URLEncoder.encode(fileurl, "UTF-8"));
            Log.i("ffffffffff", "下载url=" + ConstantValue.URLS + url.substring(0, url.lastIndexOf("/") + 1) + URLEncoder.encode(fileurl, "UTF-8"));
            Log.i(TAG,"Url==="+url+"======BaseUrl后面的url======"+url.substring(0, url.lastIndexOf("/") + 1) + URLEncoder.encode(fileurl, "UTF-8"));
            list.add(path + fileurl);
            presenter.downDocument(list);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
