package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.DeviceInfo;
import com.kongtiaoapp.xxhj.utils.DrawableUtils;
import com.kongtiaoapp.xxhj.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Shinelon on 2016/6/17.
 */
public class DeviceInfoListAdapter extends BaseAdapter {

    private List<DeviceInfo.DeviceInfoList> mList;
    private Context context;
    private String type;

    public DeviceInfoListAdapter(Context context, List<DeviceInfo.DeviceInfoList> list, String type) {
        super();
        this.context = context;
        this.mList = list;
        this.type = type;
    }

    public DeviceInfoListAdapter(Context context, List<DeviceInfo.DeviceInfoList> list) {
        super();
        this.context = context;
        this.mList = list;
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
    public void remove(int delete_position){
        mList.remove(delete_position);
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(App.application).inflate(
                    R.layout.item_device, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DeviceInfo.DeviceInfoList data = (DeviceInfo.DeviceInfoList) getItem(position);
        int imageRes = DrawableUtils.getLeveImage(StringUtils.isEmpty(type) ? data.getDeviceType() : type);
        holder.ivImage.setImageResource(imageRes);
        holder.tvNmae.setText(data.getName());

        return convertView;
    }

    public void setList(List<DeviceInfo.DeviceInfoList> list) {
        this.mList=list;
        notifyDataSetChanged();
    }

    public class ViewHolder {
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_name)
        TextView tvNmae;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
