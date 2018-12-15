package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 桂飞 on 2016/11/4.
 */

public class MyZhenduanAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    private List<Integer> list_image;

    public MyZhenduanAdapter( Context context) {
        this.context = context;
        notifyDataSetChanged();
    }
    public void setList(List<String> list, List<Integer> list_image) {
        this.list = list;
        this.list_image = list_image;
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

        ViewHolers viewholers = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_zhenduan, parent, false);
            viewholers = new ViewHolers(convertView);
            convertView.setTag(viewholers);
        } else {
            viewholers = (ViewHolers) convertView.getTag();
        }
        viewholers.txt_zhenduan.setText(list.get(position).toString());
        viewholers.image_summer.setImageResource(list_image.get(position));
        return convertView;
    }


    class ViewHolers {
        @BindView(R.id.txt_zhenduan)
        TextView txt_zhenduan;
        @BindView(R.id.image_summer)
        ImageView image_summer;

        public ViewHolers(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
