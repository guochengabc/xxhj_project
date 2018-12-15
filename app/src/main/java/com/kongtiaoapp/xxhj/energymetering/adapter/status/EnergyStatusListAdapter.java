package com.kongtiaoapp.xxhj.energymetering.adapter.status;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.DeviceNameE_CodeBean;
import com.kongtiaoapp.xxhj.bean.EnergyDeviceListBean;
import com.kongtiaoapp.xxhj.energymetering.activity.EnergyRecordActivity;
import com.kongtiaoapp.xxhj.ui.view.MyExpandableListView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by G_XXHJ on 2018/7/30.
 */

public class EnergyStatusListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<EnergyDeviceListBean.ResobjBean.OneDataBean> list;

    public EnergyStatusListAdapter(Context context, List<EnergyDeviceListBean.ResobjBean.OneDataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
        //return list.get(groupPosition).getTwoData().size();
    }

    @Override
    public Object getGroup(int i) {
        return list.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getTwoData().get(childPosition);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup holderGroup = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group_energylist, parent, false);
            holderGroup = new ViewHolderGroup(convertView);
            convertView.setTag(holderGroup);
        } else {
            holderGroup = (ViewHolderGroup) convertView.getTag();
        }
        EnergyDeviceListBean.ResobjBean.OneDataBean oneDataBean = list.get(groupPosition);
        holderGroup.txt_moduleOne.setText(oneDataBean.getOneName());
        holderGroup.txt_deviceCount.setText(oneDataBean.getOneCount());
        if (oneDataBean.getOneStatus().equals("0")) {//未录入
            holderGroup.txt_moduleOne.setTextColor(context.getResources().getColor(R.color.no_status));
            holderGroup.txt_deviceCount.setTextColor(context.getResources().getColor(R.color.no_status));
        } else {//已录入
            holderGroup.txt_moduleOne.setTextColor(context.getResources().getColor(R.color.yes_status));
            holderGroup.txt_deviceCount.setTextColor(context.getResources().getColor(R.color.yes_status));
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderChild holderChild = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_child_energylist, parent, false);
            holderChild = new ViewHolderChild(convertView);
            convertView.setTag(holderChild);
        } else {
            holderChild = (ViewHolderChild) convertView.getTag();
        }
        List<EnergyDeviceListBean.ResobjBean.OneDataBean.TwoDataBean> twoData = list.get(groupPosition).getTwoData();
        if (twoData.isEmpty()) {
            holderChild.mlv_work.setVisibility(View.GONE);
            return convertView;
        } else {
            holderChild.mlv_work.setVisibility(View.VISIBLE);
            holderChild.mlv_work.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    List<EnergyDeviceListBean.ResobjBean.OneDataBean.TwoDataBean.ThreeDataBean> threeData = twoData.get(groupPosition).getThreeData();
                    if (threeData == null || threeData.isEmpty()) {//不可点击
                        DeviceNameE_CodeBean bean = new DeviceNameE_CodeBean();
                        EnergyDeviceListBean.ResobjBean.OneDataBean.TwoDataBean twoDataBean = twoData.get(groupPosition);
                        bean.setSensorId(twoDataBean.getTwoSid());
                        bean.setName(twoDataBean.getTwoName());
                        context.startActivity(new Intent(context, EnergyRecordActivity.class).putExtra("device", (Serializable) (bean)));
                        return true;
                    } else {
                        return false;//可点击
                    }

                }
            });
            if (twoData == null || twoData.isEmpty()) {

            } else {
                EnergyStatusTwoListAdapter adapter = new EnergyStatusTwoListAdapter(context, list.get(groupPosition).getTwoData());
                holderChild.mlv_work.setAdapter(adapter);
                holderChild.mlv_work.setGroupIndicator(null);
            }

        }


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void setList(List<EnergyDeviceListBean.ResobjBean.OneDataBean> listOne) {
        this.list = listOne;
        notifyDataSetChanged();
    }

    public static class ViewHolderGroup {
        @BindView(R.id.txt_moduleOne)
        TextView txt_moduleOne;
        @BindView(R.id.txt_deviceCount)
        TextView txt_deviceCount;

        public ViewHolderGroup(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public static class ViewHolderChild {

        @BindView(R.id.mlv_work)
        MyExpandableListView mlv_work;

        public ViewHolderChild(View view) {
            ButterKnife.bind(this, view);
        }
    }
}