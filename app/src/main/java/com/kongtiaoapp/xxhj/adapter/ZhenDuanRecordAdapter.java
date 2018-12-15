package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.ZhenDuanList;
import com.kongtiaoapp.xxhj.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS-PC on 2016/6/16.
 */
public class ZhenDuanRecordAdapter extends BaseAdapter {

    private Context context;
    private List mList;

    public ZhenDuanRecordAdapter(Context context,List list) {
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
        ViewHolder holder=null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_zhenduan_record_listview, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        ZhenDuanList.ZhenduanTime item = (ZhenDuanList.ZhenduanTime) mList.get(position);
        if (!TextUtils.isEmpty(item.getDiagTime())) {
            holder.tvTime.setText(TimeUtils.getDetailTime(item.getDiagTime()));
        } else {
            holder.tvTime.setText("");
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
