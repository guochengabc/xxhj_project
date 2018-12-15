package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.HVAC_NewProjectDetailBean;

import org.xutils.common.util.DensityUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by G_XXHJ on 2018/5/11.
 */

public class SysParamInfoAdapter extends BaseAdapter {
    private List<HVAC_NewProjectDetailBean.ResobjBean.SysParamInfoBean> list;
    private Context context;

    public SysParamInfoAdapter(List<HVAC_NewProjectDetailBean.ResobjBean.SysParamInfoBean> list, Context context) {
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
        HVAC_NewProjectDetailBean.ResobjBean.SysParamInfoBean bean = list.get(position);
        holders.txt_name.setText(bean.getName() + "");
        holders.txt_param.setText(bean.getValue());
        holders.txt_yParam.setText(bean.getStandard());
        if (bean.getStandard().equals("")){
            holders.txt_leftArrow.setVisibility(View.GONE);
            holders.txt_righyArrow.setVisibility(View.GONE);
        }

        return convertView;
    }

    public void setList(List<HVAC_NewProjectDetailBean.ResobjBean.SysParamInfoBean> sysParamInfo) {
        list = sysParamInfo;
        notifyDataSetChanged();
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
        @BindView(R.id.txt_leftArrow)
        TextView txt_leftArrow;
        @BindView(R.id.txt_righyArrow)
        TextView txt_righyArrow;
        public ViewHolders(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
