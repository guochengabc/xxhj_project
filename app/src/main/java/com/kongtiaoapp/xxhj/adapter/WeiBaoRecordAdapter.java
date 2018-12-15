package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.GetMTRecordList;
import com.kongtiaoapp.xxhj.ui.view.RoundImageView;
import com.kongtiaoapp.xxhj.utils.DrawableUtils;
import com.kongtiaoapp.xxhj.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ASUS-PC on 2016/6/16.
 */
public class WeiBaoRecordAdapter extends BaseAdapter {

    private Context context;
    private List mList;

    public WeiBaoRecordAdapter(Context context, List list) {
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
        ViewHolder holder=null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_weibao_record_listview, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        GetMTRecordList.MTRecord item = (GetMTRecordList.MTRecord) getItem(position);
        //值班人名称
        if (!TextUtils.isEmpty(item.getUserName())) {
            holder.nickName.setText(item.getUserName());
        }
        //创建时间
        if (!TextUtils.isEmpty(item.getCreateTime())) {
            holder.time.setText(TimeUtils.getDetailTime(item.getCreateTime()));
        }
        Log.i("ffffffffffffffff","shebei"+item.getDeviceType());
        holder.avatar.setImageResource(DrawableUtils.getLeveImage(item.getDeviceType()));

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_avatar)
        RoundImageView avatar;
        @BindView(R.id.tv_nickName)
        TextView nickName;
        @BindView(R.id.tv_loginTime)
        TextView time;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
