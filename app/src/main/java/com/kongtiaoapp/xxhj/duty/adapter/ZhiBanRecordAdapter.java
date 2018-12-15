package com.kongtiaoapp.xxhj.duty.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.GetDutyRecordList;
import com.kongtiaoapp.xxhj.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS-PC on 2016/6/16.
 */
public class ZhiBanRecordAdapter extends BaseAdapter {

    private Context context;
    private List mList;

    public ZhiBanRecordAdapter(Context context, List list) {
        this.context = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_zhiban_record_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        GetDutyRecordList.RecordList item = (GetDutyRecordList.RecordList) getItem(position);
        //值班人名称
        if (!TextUtils.isEmpty(item.getUserName())) {
            holder.tvName.setText(item.getUserName());
        } else {
            holder.tvName.setText("");

        }
        //创建时间
        if (!TextUtils.isEmpty(item.getBeginTime())) {
            holder.tvTime.setText(TimeUtils.getDateToString(item.getBeginTime()));
        } else {
            holder.tvTime.setText("");

        }
        if (!TextUtils.isEmpty(item.getDutyStatus())){
            holder.tv_status.setText(item.getDutyStatus());
        }else{
            holder.tv_status.setText("");
        }


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_time)//值班开始时间
        TextView tvTime;
        @BindView(R.id.tv_name)//值班人姓名
        TextView tvName;
        @BindView(R.id.tv_status)
        TextView tv_status;//交接班状态

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
