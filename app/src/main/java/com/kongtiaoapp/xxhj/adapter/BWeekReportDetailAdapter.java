package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.BWeekReportDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by G_XXHJ on 2018/10/11.
 */

public class BWeekReportDetailAdapter extends BaseAdapter {
    private List<BWeekReportDetailBean.ResobjBean.DataBean> list;
    private Context context;

    public BWeekReportDetailAdapter(Context context, List<BWeekReportDetailBean.ResobjBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_b_week_report_detail, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        BWeekReportDetailBean.ResobjBean.DataBean bean = list.get(position);
        List<BWeekReportDetailBean.ResobjBean.DataBean.WeekReportBean> weekReport = bean.getWeekReport();
        String reportContent = "";
        int size = weekReport.size();
        if (size == 0) {
            holder.line_report.setVisibility(View.GONE);
            return convertView;
        }
        for (int i = 0; i < size; i++) {
            String remark = weekReport.get(i).getRemark();
            if (i == weekReport.size() - 1) {
                reportContent = reportContent + remark;
            } else {
                reportContent = reportContent + remark + "\n\r\n\r";
            }
        }
        holder.txt_time.setText(bean.getTime());
        holder.txt_bWeekReport.setText(reportContent);

        return convertView;
    }

    public void setList(List<BWeekReportDetailBean.ResobjBean.DataBean> list_data) {
        list = list_data;
        notifyDataSetChanged();
    }


    public class ViewHolder {
        @BindView(R.id.line_report)
        LinearLayout line_report;
        @BindView(R.id.txt_time)//时间
                TextView txt_time;
        @BindView(R.id.txt_bWeekReport)//内容
                TextView txt_bWeekReport;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}