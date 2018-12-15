package com.kongtiaoapp.xxhj.energymetering.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.EnergyDeviceListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2017/10/16.
 */

public class EnergyDeviceThreeAdapter extends BaseAdapter {
    private List<EnergyDeviceListBean.ResobjBean.OneDataBean.TwoDataBean.ThreeDataBean> list;
    private Context context;

    public EnergyDeviceThreeAdapter(List<EnergyDeviceListBean.ResobjBean.OneDataBean.TwoDataBean.ThreeDataBean> listChild, Context context) {
        this.list = listChild;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_three_energy, parent, false);
            holders = new ViewHolders(convertView);
            convertView.setTag(holders);
        } else {
            holders = (ViewHolders) convertView.getTag();
        }
        holders.dtv_oldPeople.setText(list.get(position).getThreeName());
        return convertView;
    }

    public static class ViewHolders {
        @BindView(R.id.dtv_oldPeople)
        TextView dtv_oldPeople;

        public ViewHolders(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
