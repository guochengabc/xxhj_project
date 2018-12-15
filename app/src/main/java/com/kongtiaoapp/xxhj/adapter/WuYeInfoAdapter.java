package com.kongtiaoapp.xxhj.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Luoye on 2016-6-15.
 */
public class WuYeInfoAdapter extends BaseAdapter {
    public WuYeInfoAdapter() {
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(App.application).inflate(
                    R.layout.item_wuye_info, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvAddress.setText("地址:"+"哦哈哈哈");
        return convertView;
    }

   public static class ViewHolder {
        @BindView(R.id.tv_address)
        public TextView tvAddress;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
