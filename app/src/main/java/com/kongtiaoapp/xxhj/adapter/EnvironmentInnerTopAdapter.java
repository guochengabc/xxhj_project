package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.EnvironmentInnerBan;
import com.kongtiaoapp.xxhj.ui.view.NoScrollGridView;
import com.kongtiaoapp.xxhj.ui.view.horizontallistview.HorizontalListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnvironmentInnerTopAdapter extends BaseAdapter {

    private Context context;
    private List<EnvironmentInnerBan.ResobjBean.GroupDataBean> list;

    public EnvironmentInnerTopAdapter(Context context, List<EnvironmentInnerBan.ResobjBean.GroupDataBean> groupData) {
        this.context = context;
        this.list = groupData;
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
                    R.layout.item_environmenttop, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        List<EnvironmentInnerBan.ResobjBean.GroupDataBean.EnerParamBean> enerParam = list.get(position).getEnerParam();

        if (enerParam == null || enerParam.isEmpty()) {
            holder.hlv_energyTop.setVisibility(View.VISIBLE);
            holder.lv_statisticTec.setVisibility(View.GONE);
            return convertView;
        }
        int numColumns = enerParam.size();
        if (numColumns <= 4) {
            holder.lv_statisticTec.setNumColumns(numColumns);
            holder.hlv_energyTop.setVisibility(View.GONE);
            holder.lv_statisticTec.setVisibility(View.VISIBLE);
            EnvironmentInnerItemAdapter adapter = new EnvironmentInnerItemAdapter(enerParam, context, 1);
            holder.lv_statisticTec.setAdapter(adapter);
        } else {
            holder.hlv_energyTop.setVisibility(View.VISIBLE);
            holder.lv_statisticTec.setVisibility(View.GONE);
            EnvironmentInnerItemAdapter adapter = new EnvironmentInnerItemAdapter(enerParam, context, 2);
            holder.hlv_energyTop.setAdapter(adapter);
        }


        return convertView;
    }

    public void setList(List<EnvironmentInnerBan.ResobjBean.GroupDataBean> groupData) {
        list = groupData;
        notifyDataSetChanged();
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
