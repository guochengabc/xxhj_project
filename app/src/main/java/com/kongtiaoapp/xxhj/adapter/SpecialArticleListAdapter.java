package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.GetSpecialArticleList;
import com.kongtiaoapp.xxhj.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Luoye on 2016-6-14.
 */
public class SpecialArticleListAdapter extends BaseAdapter {
    private Context mContext;
    private List mList;


    public SpecialArticleListAdapter(Context context, List list) {
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(App.application).inflate(
                    R.layout.item_article_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GetSpecialArticleList.SpecialArticle item = (GetSpecialArticleList.SpecialArticle) mList.get(position);
        //Picasso.with(mContext).load(ConstantValue.URL + item.getFaceUrl()).placeholder(R.mipmap.default_head).into(holder.ivPicture);
        holder.tvContent.setText(item.getAbstract());
        holder.tvTitle.setText(item.getTitle());
        holder.tvTime.setText(TimeUtils.getStandardDate(item.getCreateTime()));

        return convertView;
    }


    static class ViewHolder {
        //@BindView(R.id.iv_picture)
        //ImageView ivPicture;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
