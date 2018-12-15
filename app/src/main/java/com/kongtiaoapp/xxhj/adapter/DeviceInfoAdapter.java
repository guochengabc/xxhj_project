package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.DictNew;
import com.kongtiaoapp.xxhj.utils.BigToSmallUtils;
import com.kongtiaoapp.xxhj.utils.DrawableUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Shinelon on 2016/6/17.
 */
public class DeviceInfoAdapter extends BaseAdapter {

    private List<DictNew.ResobjBean> mList;
    private Context context;

    public DeviceInfoAdapter(Context context, List<DictNew.ResobjBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_device, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DictNew.ResobjBean data = (DictNew.ResobjBean) getItem(position);
        holder.ivImage.setImageResource(DrawableUtils.getLeveImage(BigToSmallUtils.pascalToUnderline(data.getDeviceType())));
        Log.i("DeviceInfoAdapter", "项目position======" + position + "======" + data.getDeviceType());
        holder.tvNmae.setText(data.getDeviceName());
        return convertView;
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
