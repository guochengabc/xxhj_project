package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
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
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.ChatMsg;
import com.kongtiaoapp.xxhj.utils.GlideCircleTransform;
import com.kongtiaoapp.xxhj.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS-PC on 2016/6/16.
 */
public class ChatAdapter extends BaseAdapter {

    private Context context;
    private List mList;

    private final int ONESELF = 0;
    private final int FRIENDS = 1;

    public ChatAdapter(Context context, List list) {
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
    public int getItemViewType(int position) {
        ChatMsg.ChatMessage item = (ChatMsg.ChatMessage) mList.get(position);
        if (App.sp.getUid().equals(item.getUserId())) {
            return ONESELF;
        } else {
            return FRIENDS;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            if (getItemViewType(position) == ONESELF) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_chat_oneself, null);
            } else {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_chat_friends, null);
            }
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ChatMsg.ChatMessage item = (ChatMsg.ChatMessage) getItem(position);

        if (!TextUtils.isEmpty(item.getCreateTime())) {
            holder.tvTime.setText(TimeUtils.getStandardDate(item.getCreateTime()));
        }
        Glide.with(context).load(ConstantValue.URL + item.getAvatarUrl()).placeholder(R.mipmap.default_head).transform(new GlideCircleTransform(context)).crossFade().into(holder.ivAvatar);
        holder.tvInfo.setText(item.getMessage());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_avatar)
        ImageView ivAvatar;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_info)
        TextView tvInfo;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
