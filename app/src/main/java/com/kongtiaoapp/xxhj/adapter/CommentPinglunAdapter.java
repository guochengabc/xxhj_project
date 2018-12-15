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
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.PostPinglun;
import com.kongtiaoapp.xxhj.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Luoye on 2016-8-2.
 * 说明:帖子评论
 */
public class CommentPinglunAdapter extends BaseAdapter {
    private List mList;
    private Context mContext;

    public CommentPinglunAdapter(List mList, Context mContext) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_comment, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        PostPinglun.CommentsList.CommentsBean item = (PostPinglun.CommentsList.CommentsBean) getItem(position);
        if (!TextUtils.isEmpty(item.getToUserId()) && !item.getUserId().equals(item.getToUserId())) {
            String str = item.getUserName() + "回复" + item.getToUserName() + ":" + item.getContent();
            SpannableStringBuilder ssb = setColor(item.getUserName(),
                    item.getToUserName(), str);
            holder.tvComment.setText(ssb);

//            holder.tvComment.setText(item.getUserName() + ":" + item.getContent());

        } else {
            String str = item.getUserName() + ":" + item.getContent();
            SpannableStringBuilder ssb = setColor(item.getUserName(),
                    str);
            holder.tvComment.setText(ssb);
        }

        holder.tvTime.setText(TimeUtils.getStandardDate(item.getCreateTime()));
        return convertView;
    }

    private SpannableStringBuilder setColor(String name1, String str) {
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        ForegroundColorSpan blueSpan = new ForegroundColorSpan(Color.BLUE);
        int name1_start = str.indexOf(name1);
        int name1_end = name1_start + name1.length();


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
                        }, name1_start, name1_end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    private SpannableStringBuilder setColor(String name1, String name2, String str) {
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        ForegroundColorSpan blueSpan = new ForegroundColorSpan(Color.BLUE);
        int name1_start = str.indexOf(name1);
        int name1_end = name1_start + name1.length();

        int name2_start = str.indexOf(name2);
        int name2_end = name2_start + name2.length();

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
                        }, name1_start, name1_end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
                        }, name2_start, name2_end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    static class ViewHolder {
        @BindView(R.id.tv_comment)
        TextView tvComment;
        @BindView(R.id.tv_comment_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
