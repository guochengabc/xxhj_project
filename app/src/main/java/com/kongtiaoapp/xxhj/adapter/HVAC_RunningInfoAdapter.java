package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.HVAC_ProjectDetailBean;

import org.xutils.common.util.DensityUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2017/11/29.
 */

public class HVAC_RunningInfoAdapter extends BaseAdapter {
    private List<HVAC_ProjectDetailBean.ResobjBean.RunningInfoBean> list;
    private Context context;

    public HVAC_RunningInfoAdapter(List<HVAC_ProjectDetailBean.ResobjBean.RunningInfoBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolders holders = null;
        if (convertView == null) {
            if (DensityUtil.getScreenHeight() < 1700) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_hvacrunninginfosmall, parent, false);
            } else {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_hvacrunninginfo, parent, false);
            }
            holders = new ViewHolders(convertView);
            convertView.setTag(holders);
        } else {
            holders = (ViewHolders) convertView.getTag();
        }
        HVAC_ProjectDetailBean.ResobjBean.RunningInfoBean bean = list.get(position);
        holders.txt_name.setText(bean.getName() + "  ");
        holders.txt_param.setText(bean.getParam());
        String y_param = bean.getY_param().substring(0, 1);
        String realValue = "";
        if (y_param.equals("-")) {
            realValue = bean.getY_param().substring(1);
            holders.img_arrow.setVisibility(View.VISIBLE);
            holders.img_arrow.setImageResource(R.mipmap.red_down_enegry);
        } else if (y_param.equals("0")) {
            realValue = bean.getY_param();
            holders.img_arrow.setVisibility(View.INVISIBLE);
        } else if (y_param.equals("+")) {
            realValue = bean.getY_param().substring(1);
            holders.img_arrow.setVisibility(View.VISIBLE);
            holders.img_arrow.setImageResource(R.mipmap.green_up_cop);
        }
        holders.txt_yParam.setText(realValue);
        return convertView;
    }

    public class ViewHolders {
        @BindView(R.id.txt_name)
        TextView txt_name;
        @BindView(R.id.txt_param)
        TextView txt_param;
        @BindView(R.id.img_arrow)
        ImageView img_arrow;
        @BindView(R.id.txt_yParam)
        TextView txt_yParam;

        public ViewHolders(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
