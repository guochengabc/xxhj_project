package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.SysMesgList;
import com.kongtiaoapp.xxhj.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS-PC on 2016/6/16.
 */
public class ZhenDuanAdapter extends BaseAdapter {

    private Context context;
    private List mList;
    private int type;//1诊断 2故障 3维保

    public ZhenDuanAdapter(Context context, List list, int type) {
        this.context = context;
        this.type = type;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_zd_msg_listview, null);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SysMesgList.SysMesg item = (SysMesgList.SysMesg) getItem(position);

        if (type == 1) {//1诊断 2故障 3维保
            holder.avatar.setImageResource(R.mipmap.fragment_msg_zhenduan);

        } else if (type == 2) {
            holder.avatar.setImageResource(R.mipmap.fragment_msg_guzhang);
//            holder.info.setText("故障故障故障故障故障故障故障故障故障故障故障故障故障故障故障故障故障故障故障");
        } else if (type == 3) {
            holder.avatar.setImageResource(R.mipmap.fragment_msg_weibao);
//            holder.info.setText("维保维保维保维保维保维\n保维保维保维保维保维保维保维\n保维保维保维保\n" +
//                    "保维保维保维保");
        }else if (type == 5) {
            holder.avatar.setImageResource(R.mipmap.duty_info);
        }
        holder.info.setText(item.getMessage());
        holder.time.setText(TimeUtils.getDateToString(item.getCreateTime()));
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.iv_avatar)
        ImageView avatar;
        @BindView(R.id.tv_time)
        TextView time;
        @BindView(R.id.tv_info)
        TextView info;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
