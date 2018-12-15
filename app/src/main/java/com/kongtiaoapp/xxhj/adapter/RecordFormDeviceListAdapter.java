package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.RecordFormDeviceBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2017/11/10.
 */

public class RecordFormDeviceListAdapter extends BaseExpandableListAdapter {
    private List<RecordFormDeviceBean.ResobjBean> list;
    private Context context;

    public RecordFormDeviceListAdapter(List<RecordFormDeviceBean.ResobjBean> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getRecordForm().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getRecordForm().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group_recordform, null);
            holder = new GroupViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        String name = list.get(groupPosition).getName();
        if (name != null) {
            holder.txt_group_deviceList.setText(name);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_rf_devicelist, null);
            holder = new ChildViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        holder.txt_deviceList.setText(list.get(groupPosition).getRecordForm().get(childPosition).getDeviceName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public class GroupViewHolder {
        @BindView(R.id.txt_group_deviceList)
        TextView txt_group_deviceList;

        public GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public  class ChildViewHolder {
        @BindView(R.id.txt_deviceList)
        TextView txt_deviceList;

        public ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
