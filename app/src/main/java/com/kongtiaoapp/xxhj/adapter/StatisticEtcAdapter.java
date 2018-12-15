package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.EtcStatisticBean;
import com.kongtiaoapp.xxhj.ui.view.MyListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticEtcAdapter extends BaseAdapter {
    private List<EtcStatisticBean.ResobjBean.DataBean> list;
    private Context context;

    public StatisticEtcAdapter(List<EtcStatisticBean.ResobjBean.DataBean> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_statistic_etc_list, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        EtcStatisticBean.ResobjBean.DataBean dataBean = list.get(position);
            viewHolder.tv_etc.setText(dataBean.getStatisticType());
            List<EtcStatisticBean.ResobjBean.DataBean.EtcDataBean> listData = dataBean.getEtcData();
        if (listData == null) {

        }else{
            StatisticEtcItemAdapter adapter = new StatisticEtcItemAdapter(listData, context);
            viewHolder.lv_statisticTec.setAdapter(adapter);
        }


        return convertView;
    }

    public class ViewHolder {
        @BindView(R.id.tv_etc)
        TextView tv_etc;
        @BindView(R.id.lv_statisticTec)
        MyListView lv_statisticTec;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
