package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.RecordSearchListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordSearchAdapter extends BaseAdapter {
    private Context context;
    private List<RecordSearchListBean.ResobjBean.DataBean> list;

    public RecordSearchAdapter(Context context, List<RecordSearchListBean.ResobjBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
//            convertView =  inflater.inflate(R.layout.whatever, parent, false);
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_record_search, null, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RecordSearchListBean.ResobjBean.DataBean dataBean = list.get(position);
        holder.txt_record_title.setText(dataBean.getName());
        holder.txt_record_person_zhi.setText("" + dataBean.getUserName());
        holder.txt_record_time.setText(dataBean.getTime());
        return convertView;
    }

    public void setList(List<RecordSearchListBean.ResobjBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @BindView(R.id.txt_record_title)
        TextView txt_record_title;
        @BindView(R.id.txt_record_person_zhi)
        TextView txt_record_person_zhi;
        @BindView(R.id.txt_record_time)
        TextView txt_record_time;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
