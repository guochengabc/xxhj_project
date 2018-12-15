package com.kongtiaoapp.xxhj.e_code.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.MaintBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by G_XXHJ on 2018/5/30.
 */

public class WeiBaoAdapter extends BaseAdapter {
    private List<MaintBean.ResobjBean> list;
    private Context context;

    public WeiBaoAdapter(List<MaintBean.ResobjBean> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_weibao, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MaintBean.ResobjBean bean = list.get(position);
        holder.txt_weibao_title.setText(bean.getTitle());
        holder.txt_weibao_people.setText(bean.getUserName());
        holder.txt_weibao_time.setText(bean.getMaintTime() + "");
        return convertView;
    }

    public void setList(List<MaintBean.ResobjBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder {
        @BindView(R.id.txt_weibao_title)//维保标题  简述
                TextView txt_weibao_title;
        @BindView(R.id.txt_weibao_people)//维保人员
                TextView txt_weibao_people;
        @BindView(R.id.txt_weibao_time)//维保时间
                TextView txt_weibao_time;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
