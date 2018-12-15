package com.kongtiaoapp.xxhj.prefessionalknowledge.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.ImageScaleActivity;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.Moments;
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
public class EnergyListAdapter extends BaseAdapter {
    private Context mContext;
    private List<Moments.MomentInfo> mList;

    private OperationListener operationListener;

    public void setOperationListener(OperationListener operationListener) {
        this.operationListener = operationListener;
    }

    public EnergyListAdapter(Context context, List<Moments.MomentInfo> list) {
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
                    R.layout.item_moments, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Moments.MomentInfo momentInfo = (Moments.MomentInfo) getItem(position);
        if (!TextUtils.isEmpty(momentInfo.getAvatarUrl())) {
            Glide.with(mContext).load(ConstantValue.URL + momentInfo.getAvatarUrl()).placeholder(R.mipmap.default_head).transform(new GlideCircleTransform(mContext)).crossFade().into(holder.ivHead);
        }
        holder.tvName.setText(momentInfo.getUserName());
        holder.tvTime.setText(TimeUtils.getStandardDate(momentInfo.getCreateTime()));
        holder.tvContent.setText(momentInfo.getMessageInfo());
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

        holder.tvPraise.setText(momentInfo.getWelldoneNumber());


        holder.tvComment.setText(momentInfo.getCommentNumber());

        if ("1".equals(momentInfo.getIsWelldone())) {
            //已赞
            holder.tvPraise.setSelected(true);
        } else {
            holder.tvPraise.setSelected(false);
        }
        final boolean praise = "1".equals(momentInfo.getIsWelldone());
        holder.tvPraise.setSelected(praise);
        //点赞的点击事件
        holder.rl_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operationListener != null) {
                    operationListener.praise(position, praise);
                }
            }
        });
        if ("1".equals(momentInfo.getIsCollection())) {
            //已经收藏
            holder.tvFavorite.setSelected(true);
        } else {
            holder.tvFavorite.setSelected(false);
        }
        final boolean favorite = "1".equals(momentInfo.getIsCollection());
        holder.tvFavorite.setSelected(favorite);
        //收藏的点击事件
        holder.rl_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operationListener != null) {
                    operationListener.favorite(position, favorite);
                }
            }
        });
        //评论的点击事件
        holder.rl_pinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operationListener != null) {
                    operationListener.comment(momentInfo);
                }
            }
        });


//        String s = momentInfo.getMessageInfo();
//        try {
//            String info = new String(s.getBytes("iso-8859-1"),"UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        return convertView;
    }


    static

    public class ViewHolder {
        @BindView(R.id.iv_head)
        public ImageView ivHead;
        @BindView(R.id.tv_time)
        public TextView tvTime;
        @BindView(R.id.tv_name)
        public TextView tvName;
        @BindView(R.id.tv_content)
        public TextView tvContent;
        @BindView(R.id.tv_praise)
        public TextView tvPraise;
        @BindView(R.id.tv_favorite)
        public TextView tvFavorite;
        @BindView(R.id.tv_comment)
        public TextView tvComment;
        @BindView(R.id.gallery)
        NineGridView gallery;

        @BindView(R.id.rl_zan)
        public RelativeLayout rl_zan;
        @BindView(R.id.rl_shoucang)
        public RelativeLayout rl_shoucang;
        @BindView(R.id.rl_pinglun)
        public RelativeLayout rl_pinglun;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OperationListener {
        /**
         * 点赞
         *
         * @param isPraise
         */
        void praise(int position, boolean isPraise);

        /**
         * 收藏
         */
        void favorite(int position, boolean isFavorite);

        /**
         * 评论
         */
        void comment(Moments.MomentInfo momentInfo);

    }

}
