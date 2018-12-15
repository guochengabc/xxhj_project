package com.kongtiaoapp.xxhj.workorder.adapter;


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
 * Created by xxhj_g on 2017/7/8.
 */

public class WeiXiuReecordAdapter extends BaseAdapter {

    private Context context;
    private List mList;
    private int[] image = new int[]{R.mipmap.testa1, R.mipmap.testa7, R.mipmap.testa8,
            R.mipmap.testa4, R.mipmap.testa5, R.mipmap.testa6,
            R.mipmap.testa7, R.mipmap.testa3, R.mipmap.testa9,
            R.mipmap.testa10, R.mipmap.testa11, R.mipmap.testa12,
            R.mipmap.testa13, R.mipmap.testa14, R.mipmap.testa15};

    public WeiXiuReecordAdapter(Context context, List list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_repair_record_test, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txt_repair_content.setText(mList.get(position).toString());
        holder.img_repair_img.setImageResource(image[position]);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.txt_repair_content)
        TextView txt_repair_content;
        @BindView(R.id.img_repair_img)
        ImageView img_repair_img;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
