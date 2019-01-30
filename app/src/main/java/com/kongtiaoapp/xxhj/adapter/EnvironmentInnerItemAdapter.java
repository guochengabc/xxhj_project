package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.EnvironmentInnerBan;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnvironmentInnerItemAdapter extends BaseAdapter {
    private Context context;
    private int type;//1  gridView  2水平滚动
    private List<EnvironmentInnerBan.ResobjBean.GroupDataBean.EnerParamBean> list;
    private final int[] colors;


    public EnvironmentInnerItemAdapter(List<EnvironmentInnerBan.ResobjBean.GroupDataBean.EnerParamBean> list, Context context, int type) {
        this.list = list;
        this.context = context;
        this.type = type;
        colors = new int[]{context.getResources().getColor(R.color.loading_one), context.getResources().getColor(R.color.loading_two)
                , context.getResources().getColor(R.color.loading_three), context.getResources().getColor(R.color.loading_four), context.getResources().getColor(R.color.loading_five)
                , context.getResources().getColor(R.color.loading_six)};
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
            convertView = LayoutInflater.from(context).inflate(R.layout.iten_environmentinner, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        EnvironmentInnerBan.ResobjBean.GroupDataBean.EnerParamBean bean = list.get(position);
        holder.tv_name.setText(bean.getName());
        if (position>0){
            holder.tv_name.setTextColor(colors[position-1]);
        }

        if (type == 1) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.tv_name.getLayoutParams();
            layoutParams.setMargins(0, 10, 0, 10);
            holder.tv_name.setLayoutParams(layoutParams);
        }
        return convertView;
    }

    public class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tv_name;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

