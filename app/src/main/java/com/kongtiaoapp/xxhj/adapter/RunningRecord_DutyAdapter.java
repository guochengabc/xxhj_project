package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.RunningRecord_DutyBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2017/3/10.
 */
public class RunningRecord_DutyAdapter extends BaseAdapter {
    private Context context;
    private List<RunningRecord_DutyBean.ResobjBean> list;

    public RunningRecord_DutyAdapter(Context context, List<RunningRecord_DutyBean.ResobjBean> list) {
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
                    R.layout.item_running_record_duty, null, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        holder.txt_record_person_zhi.setText("" + list.get(position).getUserName());
        holder.txt_record_time.setText( format.format(new Date(Long.parseLong(list.get(position).getTime() + "000"))));
        return convertView;
    }

    public void setList(List<RunningRecord_DutyBean.ResobjBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @BindView(R.id.txt_record_person_zhi)
        TextView txt_record_person_zhi;
        @BindView(R.id.txt_record_time)
        TextView txt_record_time;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
