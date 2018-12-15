package com.kongtiaoapp.xxhj.energymetering.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.EnergyMeterBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by G_XXHJ on 2018/7/11.
 */

public class EnergyTopInAdapter extends BaseAdapter {
    private Context context;
    private List<EnergyMeterBean.ResobjBean.GroupDataBean.EnerParamBean> list;

    public EnergyTopInAdapter(List<EnergyMeterBean.ResobjBean.GroupDataBean.EnerParamBean> list, Context context) {
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_energytop_in, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        EnergyMeterBean.ResobjBean.GroupDataBean.EnerParamBean bean = list.get(position);
        holder.txt_name.setText(bean.getName() + "");
        holder.txt_param.setText(bean.getParam());
        String y_param = bean.getY_param().trim();
        holder.txt_yParam.setText(y_param);
        if (y_param.equals("")) {
            holder.txt_leftArrow.setVisibility(View.GONE);
            holder.txt_righyArrow.setVisibility(View.GONE);
        }
        return convertView;
    }

    public class ViewHolder {
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
