package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2016/11/19.
 */
public class My_Report_Second_Adapter extends BaseAdapter {
    private Context mContext;
    private List<String> list_name;
    private List<String> list_value;

    public My_Report_Second_Adapter(Context context, List<String> list_name, List<String> list_value) {
        super();
        this.mContext = context;
        this.list_name = list_name;
        this.list_value = list_value;
    }

    @Override
    public int getCount() {
        return list_name.size();
    }

    @Override
    public Object getItem(int position) {
        return list_name.get(position);
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
            convertView = LayoutInflater.from(App.application).inflate(
                    R.layout.item_report_second, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position==0){
            holder.txt_report7.setText("负载率");
        }else{
            holder.txt_report7.setText("频率");
        }
        holder.txt_report_name.setText(list_name.get(position));
        holder.txt_report8.setText(list_value.get(position));
        return convertView;
    }

    public class ViewHolder {


        @BindView(R.id.txt_report_name)
        TextView txt_report_name;
        @BindView(R.id.txt_report7)
        TextView txt_report7;
        @BindView(R.id.txt_report8)
        TextView txt_report8;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}


