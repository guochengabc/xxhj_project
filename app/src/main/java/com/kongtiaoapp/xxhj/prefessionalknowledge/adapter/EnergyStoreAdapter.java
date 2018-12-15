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
import com.kongtiaoapp.xxhj.bean.Dict;
import com.kongtiaoapp.xxhj.net.TestData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Luoye on 2016-6-14.
 */
public class EnergyStoreAdapter extends BaseAdapter {
    private Context mContext;
    private List mList;


    public EnergyStoreAdapter(Context context, List list) {
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
        final TestData.Moment moment = TestData.getData().get(position);
        ViewHolder holder = null;
        if (convertView == null) {
//            convertView =  inflater.inflate(R.layout.whatever, parent, false);
            convertView = LayoutInflater.from(App.application).inflate(
                    R.layout.item_energy_show, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Dict.DictData item = ( Dict.DictData) mList.get(position);
//        Picasso.with(mContext).load(ConstantValue.URL +item.getFaceUrl()).placeholder(R.mipmap.default_head).into(holder.ivPicture);
        holder.tvContent.setText(item.getValue());

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.iv_picture)
        ImageView ivPicture;
        @BindView(R.id.tv_content)
        TextView tvContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
