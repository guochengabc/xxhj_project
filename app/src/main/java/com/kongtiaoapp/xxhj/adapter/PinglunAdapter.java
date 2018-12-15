package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.Pinglun;
import com.kongtiaoapp.xxhj.utils.GlideCircleTransform;
import com.kongtiaoapp.xxhj.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Luoye on 2016-8-2.
 * 说明:
 */
public class PinglunAdapter extends BaseAdapter {
    private List mList;
    private Context mContext;

    public PinglunAdapter(List mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_pinglun, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Pinglun.PinglunInfo pinglunInfo = (Pinglun.PinglunInfo) getItem(position);
        Glide.with(mContext).load(ConstantValue.URL + pinglunInfo.getAvatarUrl()).placeholder(R.mipmap.default_head).transform(new GlideCircleTransform(mContext)).crossFade().into(holder.ivHead);
        holder.tvName.setText(pinglunInfo.getUserName());
        holder.tvTime.setText(TimeUtils.getStandardDate(pinglunInfo.getCreateTime()));
        //判断是不是自己的评论
        if (!TextUtils.isEmpty(pinglunInfo.getToUserId()) && !pinglunInfo.getUserId().equals(pinglunInfo.getToUserId())) {
            //二次评论
            String str = "回复" + pinglunInfo.getToUserName() + ":" + pinglunInfo.getCommentInfo();
            SpannableStringBuilder ssb = setColor(str,
                    pinglunInfo.getToUserName(), pinglunInfo);
            holder.tvContent.setText(ssb);

        } else {

            holder.tvContent.setText(pinglunInfo.getCommentInfo());
        }



        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.rl_head_layout)
        RelativeLayout rlHeadLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private SpannableStringBuilder setColor(String str, String str1, Pinglun.PinglunInfo item) {
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        ForegroundColorSpan blueSpan = new ForegroundColorSpan(Color.BLUE);
        int fstart = str.indexOf(str1);
        int fend = fstart + str1.length();
        builder.setSpan(new ClickableSpan() {
                            @Override
                            public void onClick(View widget) {
                                //TODO 跳转用户页面
                            }

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                super.updateDrawState(ds);
                                ds.setColor(Color.BLUE); // 设置文本颜色
                                // 去掉下划线
                                ds.setUnderlineText(false);
                            }
                        }, fstart, fend,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }
}
