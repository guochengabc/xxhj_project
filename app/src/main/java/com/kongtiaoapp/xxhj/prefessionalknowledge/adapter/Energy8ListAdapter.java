package com.kongtiaoapp.xxhj.prefessionalknowledge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.Energy8List;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Luoye on 2016-6-14.
 */
public class Energy8ListAdapter extends BaseAdapter {
    private Context mContext;
    private List mList;


    public Energy8ListAdapter(Context context, List<Energy8List.Energy8> list) {
        this.mContext = context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
//            convertView =  inflater.inflate(R.layout.whatever, parent, false);
            convertView = LayoutInflater.from(App.application).inflate(
                    R.layout.item_energy8_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Energy8List.Energy8 item = (Energy8List.Energy8) mList.get(position);
        Picasso.with(mContext).load(ConstantValue.URL + item.getFaceUrl()).placeholder(R.mipmap.default_head).into(holder.ivPicture);
        holder.tvContent.setText(item.getAbstract());
        holder.tvTitle.setText(item.getTitle());

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.iv_picture)
        ImageView ivPicture;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
