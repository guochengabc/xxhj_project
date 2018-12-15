package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sks on 2016/6/1.
 */
public class MySplashAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public MySplashAdapter(Context context, List listData) {
        this.context = context;
        this.list = listData;
    }

    public void setList(List list) {
        this.list = list;
        notifyDataSetChanged();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_splash, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvNickName.setText(list.get(position));
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.txt_message)
        TextView tvNickName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}