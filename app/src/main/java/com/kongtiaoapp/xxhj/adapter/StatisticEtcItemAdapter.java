package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.EtcStatisticBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticEtcItemAdapter extends BaseAdapter {
    private List<EtcStatisticBean.ResobjBean.DataBean.EtcDataBean> list;
    private Context context;

    public StatisticEtcItemAdapter(List<EtcStatisticBean.ResobjBean.DataBean.EtcDataBean> list, Context context) {
        this.list = list;
        this.context = context;
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_statistic_etc, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_etc.setText(list.get(position).getStatisticEtc());
        viewHolder.tv_etc1.setText(list.get(position).getEtcValueO());
        viewHolder.tv_etc2.setText(list.get(position).getEtcValueT());
        return convertView;
    }

    public class ViewHolder {
        @BindView(R.id.tv_etc)
        TextView tv_etc;
        @BindView(R.id.tv_etc1)
        TextView tv_etc1;
        @BindView(R.id.tv_etc2)
        TextView tv_etc2;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

