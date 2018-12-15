package com.kongtiaoapp.xxhj.workorder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.WorkOrderStasficBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2017/11/22.
 */

public class WorkOrderSatisficAdapter extends BaseAdapter {
    List<WorkOrderStasficBean.ResobjBean.WorkDataBean.DataBeanX> list;
    private Context context;

    public WorkOrderSatisficAdapter(List<WorkOrderStasficBean.ResobjBean.WorkDataBean.DataBeanX> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_worksatisfic, parent, false);
            holders = new ViewHolders(convertView);
            convertView.setTag(holders);
        } else {
            holders = (ViewHolders) convertView.getTag();
        }
        WorkOrderStasficBean.ResobjBean.WorkDataBean.DataBeanX bean = list.get(position);
        holders.txt_repairName.setText(bean.getDispStateName());
        holders.txt_repairCount.setText(bean.getCount() + "");
        return convertView;
    }

    public class ViewHolders {
        @BindView(R.id.txt_repairName)
        TextView txt_repairName;
        @BindView(R.id.txt_repairCount)
        TextView txt_repairCount;

        public ViewHolders(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
