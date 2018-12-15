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
import com.kongtiaoapp.xxhj.bean.Moments;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Luoye on 2016-6-16.
 */
public class HotListAdapter extends BaseAdapter {
    private Context mContext;
    private List mList;
    public HotListAdapter(Context context, List list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
//            convertView =  inflater.inflate(R.layout.whatever, parent, false);
            convertView = LayoutInflater.from(App.application).inflate(
                    R.layout.item_hot_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Moments.MomentInfo item = (Moments.MomentInfo) getItem(position);
        if (item.getImageUrl()!=null&&item.getImageUrl().size()!=0) {
            Picasso.with(mContext).load(ConstantValue.URL + item.getImageUrl().get(0)).placeholder(R.mipmap.default_head).into(holder.ivPicture);
        }
        holder.tvContent.setText(item.getMessageInfo());

        return convertView;
    }

    public class ViewHolder {
        @BindView(R.id.iv_picture)
        public ImageView ivPicture;
        @BindView(R.id.tv_titleName)
        public TextView tvTitleName;
        @BindView(R.id.tv_content)
        public TextView tvContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
