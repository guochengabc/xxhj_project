package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.RunDevice_StateBean;
import com.kongtiaoapp.xxhj.ui.view.NoScrollListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2017/1/6.
 */
public class Device_Run_StateAdapter extends BaseAdapter {
    List<RunDevice_StateBean.ResobjBean> mList;
    List<String> list_title;
    Context context;

    public Device_Run_StateAdapter(Context context) {
        this.context = context;


    }

    @Override
    public int getCount() {
        return list_title.size();
    }

    @Override
    public Object getItem(int position) {
        return list_title.get(position);
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
                    R.layout.item_device_state, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Device_Gv_Run_StateAdapter adapter = new Device_Gv_Run_StateAdapter(context);
        holder.txt_device_item.setText(list_title.get(position));
        List<RunDevice_StateBean.ResobjBean.ColDataBean> colData = mList.get(position).getColData();

        adapter.setList(colData, mList.get(position).getParamSize());
        holder.gv_device_item.setAdapter(adapter);
        return convertView;
    }

    public void setList(List<RunDevice_StateBean.ResobjBean> list_data, List<String> list_title) {
        mList = list_data;
        this.list_title = list_title;
        notifyDataSetChanged();
    }


    public class ViewHolder {
        @BindView(R.id.txt_device_item)
        TextView txt_device_item;
        @BindView(R.id.gv_device_item)
        NoScrollListView gv_device_item;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
