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

public class EnvironmentStatisticAdapter extends BaseAdapter {
    private List<EnvironmentInnerBan.ResobjBean.StatisticsNumberBean> list;
    private Context context;

    public EnvironmentStatisticAdapter(List<EnvironmentInnerBan.ResobjBean.StatisticsNumberBean> list, Context context) {
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
        ViewHolders holder = null;
        if (convertView == null) {
//            convertView =  inflater.inflate(R.layout.whatever, parent, false);
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_environment_statistic, parent, false);
            holder = new ViewHolders(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolders) convertView.getTag();
        }
        EnvironmentInnerBan.ResobjBean.StatisticsNumberBean bean = list.get(position);
        holder.tv_environmentEtc.setText(bean.getName());
        holder.tv_environmentEtcValue.setText(bean.getValue() + "");
/*//此处相当于布局文件中的Android:layout_gravity属性
        if (position % 2 == 0) {
            holder.line_statistic.setGravity(Gravity.LEFT);
        } else {
            holder.line_statistic.setGravity(Gravity.RIGHT);
        }*/
        return convertView;
    }

    public void setList(List<EnvironmentInnerBan.ResobjBean.StatisticsNumberBean> stasticArray) {
        list = stasticArray;
        notifyDataSetChanged();
    }


    public static class ViewHolders {
        @BindView(R.id.line_statistic)
        LinearLayout line_statistic;
        @BindView(R.id.tv_environmentEtc)
        TextView tv_environmentEtc;
        @BindView(R.id.tv_environmentEtcValue)
        TextView tv_environmentEtcValue;

        public ViewHolders(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
