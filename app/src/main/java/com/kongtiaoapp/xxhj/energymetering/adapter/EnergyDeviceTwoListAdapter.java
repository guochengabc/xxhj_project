package com.kongtiaoapp.xxhj.energymetering.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.DeviceNameE_CodeBean;
import com.kongtiaoapp.xxhj.bean.EnergyDeviceListBean;
import com.kongtiaoapp.xxhj.energymetering.activity.EnergyRecordActivity;
import com.kongtiaoapp.xxhj.ui.view.MyListView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by G_XXHJ on 2018/2/10.
 */

public class EnergyDeviceTwoListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<EnergyDeviceListBean.ResobjBean.OneDataBean.TwoDataBean> list;
    int[] group_state_array = new int[]{R.mipmap.move_down,
            R.mipmap.move_up};

    public EnergyDeviceTwoListAdapter(Context context, List<EnergyDeviceListBean.ResobjBean.OneDataBean.TwoDataBean> listChild) {
        this.context = context;
        list = listChild;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
        // return list.get(groupPosition).getThreeData().size();
    }

    @Override
    public Object getGroup(int i) {
        return list.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getThreeData().get(childPosition);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.group_two_energy, parent, false);
            holderGroup = new ViewHolderGroup(convertView);
            convertView.setTag(holderGroup);
        } else {
            holderGroup = (ViewHolderGroup) convertView.getTag();
        }
        holderGroup.txt_deviceName.setText(list.get(groupPosition).getTwoName());
        holderGroup.txt_deviceCount.setText(list.get(groupPosition).getTwoCount() + "");
        if (list.get(groupPosition).getThreeData() == null || list.get(groupPosition).getThreeData().isEmpty()) {
            holderGroup.group_state.setVisibility(View.INVISIBLE);
            return convertView;
        } else {
            holderGroup.group_state.setVisibility(View.VISIBLE);
            if (isExpanded) {
                // 设置默认的图片是未选中状态
                holderGroup.group_state.setBackgroundResource(group_state_array[0]);
            } else {
                // 设置默认的图片是未选中状态
                holderGroup.group_state.setBackgroundResource(group_state_array[1]);
            }
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderChild holderChild = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.child_two_energy, parent, false);
            holderChild = new ViewHolderChild(convertView);
            convertView.setTag(holderChild);
        } else {
            holderChild = (ViewHolderChild) convertView.getTag();
        }
        List<EnergyDeviceListBean.ResobjBean.OneDataBean.TwoDataBean.ThreeDataBean> listChild = list.get(groupPosition).getThreeData();
        if (listChild == null || listChild.isEmpty()) {
            holderChild.gv_people.setVisibility(View.GONE);
            return convertView;
        } else {
            holderChild.gv_people.setVisibility(View.VISIBLE);
            EnergyDeviceThreeAdapter adapter = new EnergyDeviceThreeAdapter(listChild, context);
            holderChild.gv_people.setAdapter(adapter);
            holderChild.gv_people.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    DeviceNameE_CodeBean bean = new DeviceNameE_CodeBean();
                    EnergyDeviceListBean.ResobjBean.OneDataBean.TwoDataBean.ThreeDataBean threeDataBean = listChild.get(position);
                    bean.setSensorId(threeDataBean.getThreeSid());
                    bean.setName(threeDataBean.getThreeName());
                    context.startActivity(new Intent(context, EnergyRecordActivity.class).putExtra("device", (Serializable) (bean)));
                }
            });


        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public static class ViewHolderGroup {
        @BindView(R.id.group_layout)
        RelativeLayout myLayout;
        @BindView(R.id.txt_deviceName)
        TextView txt_deviceName;
        @BindView(R.id.txt_deviceCount)
        TextView txt_deviceCount;
        @BindView(R.id.group_state)
        ImageView group_state;

        public ViewHolderGroup(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public static class ViewHolderChild {

        @BindView(R.id.gv_people)
        MyListView gv_people;

        public ViewHolderChild(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
