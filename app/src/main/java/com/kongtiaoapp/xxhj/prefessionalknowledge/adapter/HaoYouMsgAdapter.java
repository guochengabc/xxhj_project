package com.kongtiaoapp.xxhj.prefessionalknowledge.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.FriendMesgList;
import com.kongtiaoapp.xxhj.utils.GlideCircleTransform;
import com.kongtiaoapp.xxhj.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS-PC on 2016/6/16.
 */
public class HaoYouMsgAdapter extends BaseAdapter {

    private Context context;
    private List mList;

    public HaoYouMsgAdapter(Context context, List list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_friends_msg_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        FriendMesgList.FriendMesg item = (FriendMesgList.FriendMesg) getItem(position);


        Glide.with(context).load(ConstantValue.URL + item.getAvatarUrl()).placeholder(R.mipmap.default_head).transform(new GlideCircleTransform(context)).crossFade().into(holder.avatar);
        holder.nickName.setText(item.getUserName());
        if (!TextUtils.isEmpty(item.getCreateTime())) {
            holder.time.setText(TimeUtils.getStandardDate(item.getCreateTime()));
        }
        holder.info.setText(item.getMessage());

//        if (position == 1 || position == 5 || position == 13) {
//            holder.info.setText("棒棒糖申请加您为好友");
//        } else if (position == 3 || position == 7 || position == 15) {
//            holder.info.setText("小怪兽发来了一条消息");
//        } else if (position == 6 || position == 10 || position == 19) {
//            holder.info.setText("奥特曼发来一条评论消息");
//        } else {
//            holder.info.setText("新消息");
//        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_avatar)
        ImageView avatar;
        @BindView(R.id.tv_nickName)
        TextView nickName;
        @BindView(R.id.tv_time)
        TextView time;
        @BindView(R.id.tv_loginTime)
        TextView info;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
