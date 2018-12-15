package com.kongtiaoapp.xxhj.energymetering.adapter.status;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.EnergyDeviceListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2017/10/16.
 */

public class EnergyStatusThreeAdapter extends BaseAdapter {
    private List<EnergyDeviceListBean.ResobjBean.OneDataBean.TwoDataBean.ThreeDataBean> list;
    private Context context;

    public EnergyStatusThreeAdapter(List<EnergyDeviceListBean.ResobjBean.OneDataBean.TwoDataBean.ThreeDataBean> listChild, Context context) {
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
        EnergyDeviceListBean.ResobjBean.OneDataBean.TwoDataBean.ThreeDataBean threeDataBean = list.get(position);
        holders.dtv_oldPeople.setText(threeDataBean.getThreeName());
        holders.line_backGround.setBackgroundResource(R.color.ffffff);
        if (threeDataBean.getThreeStatus().equals("0")) {//未录入
            holders.dtv_oldPeople.setTextColor(context.getResources().getColor(R.color.no_status));
        } else {//已录入
            holders.dtv_oldPeople.setTextColor(context.getResources().getColor(R.color.yes_status));
        }
        return convertView;
    }

    public static class ViewHolders {
        @BindView(R.id.line_backGround)
        LinearLayout line_backGround;
        @BindView(R.id.dtv_oldPeople)
        TextView dtv_oldPeople;

        public ViewHolders(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
