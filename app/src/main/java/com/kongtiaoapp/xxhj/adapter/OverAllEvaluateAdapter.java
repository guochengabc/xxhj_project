package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.RunningGuigeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2017/7/17.
 */

public class OverAllEvaluateAdapter extends BaseAdapter {
    private Context context;
    private List<RunningGuigeBean.ResobjBean> list;

    public OverAllEvaluateAdapter(Context context, List<RunningGuigeBean.ResobjBean> list) {
        this.context = context;
        this.list = list;
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
//            convertView =  inflater.inflate(R.layout.whatever, parent, false);
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_overallevaluate, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txt_status.setText(list.get(position).getDesc());

        return convertView;
    }

    public void setList(List<RunningGuigeBean.ResobjBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @BindView(R.id.txt_status)
        TextView txt_status;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
