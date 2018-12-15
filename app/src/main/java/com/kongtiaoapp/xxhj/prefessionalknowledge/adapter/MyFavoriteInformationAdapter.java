package com.kongtiaoapp.xxhj.prefessionalknowledge.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.ImageScaleActivity;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.MyZiXun;
import com.kongtiaoapp.xxhj.net.TestData;
import com.kongtiaoapp.xxhj.ui.NineGridView.NetworkImageAdapter;
import com.kongtiaoapp.xxhj.ui.NineGridView.NineGridView;
import com.kongtiaoapp.xxhj.utils.GlideCircleTransform;
import com.kongtiaoapp.xxhj.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Luoye on 2016-6-14.
 */
public class MyFavoriteInformationAdapter extends BaseAdapter {
    private Context mContext;
    private List<MyZiXun.MyZiXunBean> mList;


    public MyFavoriteInformationAdapter(Context context, List<MyZiXun.MyZiXunBean> list) {
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
                    R.layout.item_favorite_information, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final MyZiXun.MyZiXunBean momentInfo = (MyZiXun.MyZiXunBean) getItem(position);
        if (!TextUtils.isEmpty(momentInfo.getAvatarUrl())) {
            Glide.with(mContext).load(ConstantValue.URL + momentInfo.getAvatarUrl()).placeholder(R.mipmap.default_head).transform(new GlideCircleTransform(mContext)).crossFade().into(holder.ivHead);
        }
        holder.tvName.setText(momentInfo.getUserName());
        holder.tvTime.setText(TimeUtils.getStandardDate(momentInfo.getCreateTime()));
        holder.tvContent.setText(momentInfo.getTitle());
        NetworkImageAdapter adapter = new NetworkImageAdapter(App.application, momentInfo.getImageUrl());
        holder.gallery.setAdapter(adapter);
        final String[] arr = (String[]) momentInfo.getImageUrl().toArray(new String[momentInfo.getImageUrl().size()]);
        holder.gallery.setOnImageClickListener(new NineGridView.OnImageClickListener() {
            @Override
            public void onImageCilcked(int position, View view) {
                mContext.startActivity(new Intent(App.application, ImageScaleActivity.class).putExtra("urls", arr).putExtra("currentItem", position));
//                ImageScaleActivity.instance.overridePendingTransition(R.anim.activity_translate_in,
//                        0);
            }
        });


        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.gallery)
        NineGridView gallery;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
