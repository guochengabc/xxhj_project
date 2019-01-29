package com.kongtiaoapp.xxhj.energymetering.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.EnergyMeterBean;
import com.kongtiaoapp.xxhj.ui.view.NoScrollGridView;
import com.kongtiaoapp.xxhj.ui.view.horizontallistview.HorizontalListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by G_XXHJ on 2018/7/11.
 */

public class EnergyTopAdapter extends BaseAdapter {

    private Context context;
    private List<EnergyMeterBean.ResobjBean.GroupDataBean> list;

    public EnergyTopAdapter(List<EnergyMeterBean.ResobjBean.GroupDataBean> list, Context context) {
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_energytop, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        List<EnergyMeterBean.ResobjBean.GroupDataBean.EnerParamBean> enerParam = list.get(position).getEnerParam();
        if (enerParam==null || enerParam.isEmpty()){
            return convertView;
        }
        if (enerParam.size()<=3){
            holder.hlv_energyTop.setVisibility(View.GONE);
            holder.lv_statisticTec.setVisibility(View.VISIBLE);
            EnergyTopInAdapter adapter=new EnergyTopInAdapter(enerParam,context);
            holder.hlv_energyTop.setAdapter(adapter);
        }else{
            holder.hlv_energyTop.setVisibility(View.VISIBLE);
            holder.lv_statisticTec.setVisibility(View.GONE);
            EnergyTopInAdapter adapter=new EnergyTopInAdapter(enerParam,context);
            holder.hlv_energyTop.setAdapter(adapter);
        }


        return convertView;
    }
    public class ViewHolder {
        @BindView(R.id.hlv_energyTop)
        HorizontalListView hlv_energyTop;
        @BindView(R.id.lv_statisticTec)
        NoScrollGridView lv_statisticTec;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
