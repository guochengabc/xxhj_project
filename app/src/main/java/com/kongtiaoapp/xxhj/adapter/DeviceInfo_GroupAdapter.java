package com.kongtiaoapp.xxhj.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.DeviceInfoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2017/3/17.
 */
public class DeviceInfo_GroupAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<DeviceInfoBean.ResobjBean> list_reso;
    int[] group_state_array = new int[]{R.mipmap.move_down,
            R.mipmap.move_up};
    private int[] group_checked = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    // 用来标识是否设置二級item背景色为绿色,初始值为-1既为选中状态
    private int child_groupId = -1;
    private int child_childId = -1;
    private int checkPosition=0;
    private int groupPosition=0;
    public DeviceInfo_GroupAdapter(Context context, List<DeviceInfoBean.ResobjBean> list_reso) {
        this.context = context;
        this.list_reso = list_reso;
    }

    @Override
    public int getGroupCount() {
        return list_reso.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list_reso.get(groupPosition).getData().size();
    }

    @Override
    public Object getGroup(int i) {
        return list_reso.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int i1) {
        return list_reso.get(groupPosition).getData().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * 指定位置相应的组视图
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup viewGroup) {
        // 为视图对象指定布局
      /*  convertView = (RelativeLayout) RelativeLayout.inflate(context,
                R.layout.item_deviceinfo, null);*/
        Viewholder_Group viewholder_group = null;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.item_deviceinfo, null);
            viewholder_group = new Viewholder_Group(convertView);
            convertView.setTag(viewholder_group);
        } else {
            viewholder_group = (Viewholder_Group) convertView.getTag();
        }
        viewholder_group.group_title.setText(list_reso.get(groupPosition).getTypeName());
        viewholder_group.txt_deviceCount.setText(list_reso.get(groupPosition).getCount()+"台");
        // 设置整体描述上的文本信息
        if (this.groupPosition==groupPosition){
            group_checked[this.groupPosition]=checkPosition;
        }
        if (group_checked[groupPosition] % 2 == 1) {
            // 设置默认的图片是选中状态
            viewholder_group.group_state.setBackgroundResource(group_state_array[0]);
            viewholder_group.myLayout.setBackgroundResource(R.drawable.text_item_top_bg);
        } else {
            for (int test : group_checked) {
                if (test == 0 || test % 2 == 0) {
                    viewholder_group.myLayout.setBackgroundResource(R.drawable.text_item_bg);
                    // 设置默认的图片是未选中状态
                    viewholder_group.group_state.setBackgroundResource(group_state_array[1]);
                }
            }
        }
        // 返回一个布局对象
        return convertView;
    }
        //记录被点击的状态
    public void setCheck(int position, int groupPosition) {
        checkPosition=position;
        this.groupPosition=groupPosition;
    }

    public class Viewholder_Group {
        @BindView(R.id.group_layout)
        RelativeLayout myLayout;
        @BindView(R.id.txt_deviceName)
        TextView group_title;
        @BindView(R.id.txt_deviceCount)
        TextView txt_deviceCount;
        @BindView(R.id.group_state)
        ImageView group_state;

        Viewholder_Group(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        Viewholder_Child viewholder_child = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_child_deviceinfo, null);
            viewholder_child = new Viewholder_Child(convertView);
            convertView.setTag(viewholder_child);
        } else {
            viewholder_child = (Viewholder_Child) convertView.getTag();
        }
        /**
         * 声明视图上要显示的控件
         */

        /**
         * 设置相应控件的内容
         */
        // 设置要显示的文本信息
        viewholder_child.txt_devicemodel.setText(list_reso.get(groupPosition).getData().get(childPosition).getModel());
        viewholder_child.txt_device_p.setText(list_reso.get(groupPosition).getData().get(childPosition).getRatedPower());
        // 判断item的位置是否相同，如相同，则表示为选中状态，更改其背景颜色，如不相同，则设置背景色为白色

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public void setList(List<DeviceInfoBean.ResobjBean> list_reso) {
        this.list_reso = list_reso;
        notifyDataSetChanged();
    }

    public class Viewholder_Child {
        @BindView(R.id.txt_devicemodel)
        TextView txt_devicemodel;
        @BindView(R.id.txt_device_p)
        TextView txt_device_p;

        Viewholder_Child(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
