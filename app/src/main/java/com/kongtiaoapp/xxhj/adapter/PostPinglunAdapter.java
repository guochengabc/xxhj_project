package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.Pinglun;
import com.kongtiaoapp.xxhj.bean.PostPinglun;
import com.kongtiaoapp.xxhj.ui.view.MyListView;
import com.kongtiaoapp.xxhj.utils.GlideCircleTransform;
import com.kongtiaoapp.xxhj.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Luoye on 2016-8-2.
 * 说明:
 */
public class PostPinglunAdapter extends BaseAdapter {
    private List mList;
    private Context mContext;
    private PinglunListener operationListener;

    public void setOperationListener(PinglunListener operationListener) {
        this.operationListener = operationListener;
    }

    public PostPinglunAdapter(List mList, Context mContext) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int current = position;
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_pinglun, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final PostPinglun.CommentsList pinglunInfo = (PostPinglun.CommentsList) getItem(position);
        Glide.with(mContext).load(ConstantValue.URL + pinglunInfo.getAvatarUrl()).placeholder(R.mipmap.default_head).transform(new GlideCircleTransform(mContext)).crossFade().into(holder.ivHead);
        holder.tvName.setText(pinglunInfo.getUserName());
        holder.tvTime.setText(TimeUtils.getStandardDate(pinglunInfo.getCreateTime()));
        holder.tvContent.setText(pinglunInfo.getContent());

        if (pinglunInfo.getComments() != null && pinglunInfo.getComments().size() > 0) {
            holder.listComment.setVisibility(View.VISIBLE);

            CommentPinglunAdapter adapter =  new CommentPinglunAdapter(pinglunInfo.getComments(), mContext);
            holder.listComment.setAdapter(adapter);
            holder.listComment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (operationListener != null) {
                        PostPinglun.CommentsList.CommentsBean item = (PostPinglun.CommentsList.CommentsBean) parent.getAdapter().getItem(position);
                        operationListener.comment(current, item);
                    }
                }
            });
            holder.listComment.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    if (operationListener != null) {
                        PostPinglun.CommentsList.CommentsBean item = (PostPinglun.CommentsList.CommentsBean) parent.getAdapter().getItem(position);
                        operationListener.longTimeclick(item.getUserId(),item.getAnswerId());
                    }
                    return true;
                }
            });
            setListViewHeightBasedOnChildren(holder.listComment);
            adapter.notifyDataSetChanged();
        } else {
            holder.listComment.setVisibility(View.GONE);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operationListener != null) {
                    operationListener.pingLun(position, pinglunInfo);
                }
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (operationListener != null) {
                    operationListener.longTimeclick(pinglunInfo.getUserId(),pinglunInfo.getAnswerId());
                }
                return true;
            }
        });


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
        @BindView(R.id.list_comment)
        MyListView listComment;

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

    public interface PinglunListener {
        /**
         * 整条点击
         *
         * @param
         */
        void pingLun(int position, PostPinglun.CommentsList item);


        /**
         * 评论
         */
        void comment(int position, PostPinglun.CommentsList.CommentsBean item);

        void longTimeclick(String uid, String answerId);

    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
