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
import com.kongtiaoapp.xxhj.utils.BigToSmallUtils;
import com.kongtiaoapp.xxhj.utils.DrawableUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Shinelon on 2016/6/17.
 */
public class UserDeviceListDuoAdapter extends BaseAdapter {

    public List<DeviceInfo.DeviceInfoList> mList;
    private Context context;

    public UserDeviceListDuoAdapter(Context context, List<DeviceInfo.DeviceInfoList> list) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            // convertView =  inflater.inflate(R.layout.whatever, parent, false);
            convertView = LayoutInflater.from(App.application).inflate(R.layout.item_device_duo, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DeviceInfo.DeviceInfoList data = (DeviceInfo.DeviceInfoList) getItem(position);
        holder.ivImage.setImageResource(DrawableUtils.getLeveImage(BigToSmallUtils.pascalToUnderline(data.getDeviceType())));
        holder.tvNmae.setText(data.getName());
        if (data.getIsUse().equals("1")) {
            holder.ivGou.setImageResource(R.drawable.selector_device_use);
        } else {
            holder.ivGou.setImageResource(R.drawable.selector_device);
        }

        if (data.isSelected()) {
            holder.ivGou.setSelected(true);
        } else {
            holder.ivGou.setSelected(false);
        }

        return convertView;
    }

    public class ViewHolder {
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.iv_gou)
        ImageView ivGou;
        @BindView(R.id.tv_name)
        TextView tvNmae;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
