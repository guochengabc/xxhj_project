package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.BPD_MainInfoBean;
import com.kongtiaoapp.xxhj.bpd.activity.BPaintActivity;
import com.kongtiaoapp.xxhj.ui.view.NoScrollGridView;
import com.kongtiaoapp.xxhj.ui.view.horizontallistview.HorizontalListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by G_XXHJ on 2018/9/29. 变配电  头部
 */

public class BPD_TopAdapter extends BaseAdapter {

    private Context context;
    private List<BPD_MainInfoBean.ResobjBean.GroupDataBean> list;

    public BPD_TopAdapter(List<BPD_MainInfoBean.ResobjBean.GroupDataBean> list, Context context) {
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
        List<BPD_MainInfoBean.ResobjBean.GroupDataBean.EnerParamBean> enerParam = list.get(position).getEnerParam();
        if(enerParam ==null||enerParam.isEmpty()){
            return convertView;
        }
        BPD_TopInAdapter adapter = new BPD_TopInAdapter(enerParam, context);
        if (enerParam.size()<=3){
            holder.lv_statisticTec.setNumColumns(enerParam.size());
            holder.hlv_energyTop.setVisibility(View.GONE);
            holder.lv_statisticTec.setVisibility(View.VISIBLE);
            holder.lv_statisticTec.setAdapter(adapter);
            holder.lv_statisticTec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    BPD_MainInfoBean.ResobjBean.GroupDataBean.EnerParamBean enerParamBean = enerParam.get(position);
                    context.startActivity(new Intent(context, BPaintActivity.class)
                            .putExtra("name", enerParamBean.getName())
                            .putExtra("type", enerParamBean.getType())
                            .putExtra("dateSign",enerParamBean.getDateSign())
                            .putExtra("position",position+""));
                }
            });
        }else{
            holder.hlv_energyTop.setVisibility(View.VISIBLE);
            holder.lv_statisticTec.setVisibility(View.GONE);
            holder.hlv_energyTop.setAdapter(adapter);
            holder.hlv_energyTop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    BPD_MainInfoBean.ResobjBean.GroupDataBean.EnerParamBean enerParamBean = enerParam.get(position);
                    context.startActivity(new Intent(context, BPaintActivity.class)
                            .putExtra("name", enerParamBean.getName())
                            .putExtra("type", enerParamBean.getType())
                            .putExtra("dateSign",enerParamBean.getDateSign())
                            .putExtra("position",position+""));
                }
            });
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
