package com.kongtiaoapp.xxhj.prefessionalknowledge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.EnergyShow;
import com.kongtiaoapp.xxhj.bean.Moments;
import com.kongtiaoapp.xxhj.net.TestData;
import com.kongtiaoapp.xxhj.utils.TimeUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Luoye on 2016-6-14.
 */
public class MyEnergyShowAdapter extends BaseAdapter {
    private Context mContext;
    private List mList;


    public MyEnergyShowAdapter(Context context, List<Moments.MomentInfo> list) {
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
                    R.layout.item_my_energy_show, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        EnergyShow.EnergyShowList item = (EnergyShow.EnergyShowList) mList.get(position);
        Picasso.with(mContext).load(ConstantValue.URL + item.getFaceUrl()).placeholder(R.mipmap.default_head).into(holder.ivTopBg);
        holder.tvZhuBanFang.setText("主办方:" + item.getHost());
        holder.tvTime.setText("时间:" + TimeUtils.getDateToString(item.getTime()));
        holder.tvAddress.setText("地址:" + item.getPlace());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_top_bg)
        ImageView ivTopBg;
        @BindView(R.id.tv_zhuBanFang)
        TextView tvZhuBanFang;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_address)
        TextView tvAddress;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
