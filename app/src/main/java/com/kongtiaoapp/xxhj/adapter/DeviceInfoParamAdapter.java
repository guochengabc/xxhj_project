package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.DeviceParam;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Luoye on 2016-8-8.
 * 说明:设备信息详情
 */
public class DeviceInfoParamAdapter extends BaseAdapter {
    private List<DeviceParam.DeviceParamList> mList;
    private Context mContext;

    public DeviceInfoParamAdapter(List<DeviceParam.DeviceParamList> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
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
                    R.layout.item_device_info_param, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DeviceParam.DeviceParamList item = (DeviceParam.DeviceParamList) getItem(position);
        holder.tvName.setText(item.getValue() + ":");
        if (!TextUtils.isEmpty(item.getUnit())) {
            holder.tvContent.setText(item.getData() + "(" + item.getUnit() + ")");
        } else {
            holder.tvContent.setText(item.getData());

        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_content)
        TextView tvContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
