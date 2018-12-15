package com.kongtiaoapp.xxhj.prefessionalknowledge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.GetFriendList;
import com.kongtiaoapp.xxhj.utils.GlideCircleTransform;
import com.kongtiaoapp.xxhj.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS-PC on 2016/6/16.
 */
public class MyFriendsAdapter extends BaseAdapter {

    private Context context;
    private List mList;

    public MyFriendsAdapter(Context context , List list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_my_friends_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GetFriendList.FriendList item = (GetFriendList.FriendList) getItem(position);
        holder.tvNickName.setText(item.getUserName());
        holder.tvLoginTime.setText("最近登录: "+TimeUtils.getStandardDate(item.getLastTime()));

        Glide.with(context).load(ConstantValue.URL+ item.getAvatarUrl()).placeholder(R.mipmap.default_head).transform(new GlideCircleTransform(context)).crossFade().into(holder.ivAvatar);

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.iv_avatar)
        ImageView ivAvatar;
        @BindView(R.id.tv_nickName)
        TextView tvNickName;
        @BindView(R.id.tv_loginTime)
        TextView tvLoginTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
