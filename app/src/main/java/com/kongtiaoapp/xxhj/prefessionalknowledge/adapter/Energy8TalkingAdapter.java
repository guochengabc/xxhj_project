package com.kongtiaoapp.xxhj.prefessionalknowledge.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.GetPostList;
import com.kongtiaoapp.xxhj.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Luoye on 2016-6-17.
 */
public class Energy8TalkingAdapter extends BaseAdapter {
    private Context mContext;
    private List<GetPostList.Post> mList;

    public Energy8TalkingAdapter(Context context, List<GetPostList.Post> list) {
        super();
        this.mContext = context;
        this.mList = list;
        notifyDataSetChanged();
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
                    R.layout.item_energy8_talking, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        GetPostList.Post item = (GetPostList.Post) getItem(position);
        //标题
        if (!TextUtils.isEmpty(item.getTitle())) {
            String title = "";
            if (item.getTitle().length() > 10) {
                title = item.getTitle().substring(0, 10)+"...";
            } else {
                title = item.getTitle();
            }
            holder.tvType.setText(title);
        } else {
            holder.tvType.setText("");
        }
        //顶
        if (!TextUtils.isEmpty(item.getTopStatus())) {
            if ("1".equals(item.getTopStatus())) {
                holder.ivDing.setVisibility(View.VISIBLE);
            } else {
                holder.ivDing.setVisibility(View.GONE);
            }
        }
        //精
        if (!TextUtils.isEmpty(item.getEliteStatus())) {
            if ("1".equals(item.getEliteStatus())) {
                holder.ivDing.setVisibility(View.VISIBLE);
            } else {
                holder.ivDing.setVisibility(View.GONE);
            }
        }

        //图
        if (item.getImageUrl().size() > 0) {
            holder.ivTu.setVisibility(View.VISIBLE);
        } else {
            holder.ivTu.setVisibility(View.GONE);
        }

        //内容
        if (!TextUtils.isEmpty(item.getContent())) {
            holder.tvContent.setText(item.getContent());
        } else {
            holder.tvContent.setText("");
        }
        //时间
        if (!TextUtils.isEmpty(item.getCreateTime())) {
            holder.tvTime.setText(TimeUtils.getStandardDate(item.getCreateTime()));
        }
        return convertView;
    }

    public class ViewHolder {
        @BindView(R.id.iv_ding)
        ImageView ivDing;
        @BindView(R.id.iv_jing)
        ImageView ivJing;
        @BindView(R.id.iv_tu)
        ImageView ivTu;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_content)
        TextView tvContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
