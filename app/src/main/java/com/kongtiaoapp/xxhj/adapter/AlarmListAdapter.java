package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.AlarmListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2017/9/25.
 */

public class AlarmListAdapter extends BaseAdapter {
    private List<AlarmListBean.ResobjBean> list;
    private Context context;

    public AlarmListAdapter(Context context, List<AlarmListBean.ResobjBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_alarm, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AlarmListBean.ResobjBean bean = list.get(position);
        String alarmType = bean.getAlarmType();
        if (alarmType != null) {
            if (alarmType.equals("告警")) {
                holder.img_alarm.setImageResource(R.mipmap.yellow_alarm);
            } else if (alarmType.equals("严重告警")) {
                holder.img_alarm.setImageResource(R.mipmap.red_alarm);
            } else {
                holder.img_alarm.setVisibility(View.GONE);
            }
        }
        //    holder.txt_alarm.setText(bean.getAlarmType());
        holder.txt_device.setText(bean.getDeviceName());
        holder.txt_time.setText(bean.getTime());
        return convertView;
    }

    public void setList(List<AlarmListBean.ResobjBean> list_data) {
        list = list_data;
        notifyDataSetChanged();
    }


    public class ViewHolder {
        @BindView(R.id.img_alarm)
        ImageView img_alarm;
        /*  @BindView(R.id.txt_alarm)//警告类型
                  TextView txt_alarm;*/
        @BindView(R.id.txt_device)//设备类型
                TextView txt_device;
        @BindView(R.id.txt_time)//时间
                TextView txt_time;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
