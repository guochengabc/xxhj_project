package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.DeviceNameE_CodeBean;
import com.kongtiaoapp.xxhj.bean.EnvironmentInnerBan;
import com.kongtiaoapp.xxhj.interfaces.EnvironmentHenHouse;
import com.kongtiaoapp.xxhj.ui.view.MyListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2017/3/23. 获取环控监测的各场子数据
 */
public class EnvironmentInnerAdapter extends BaseExpandableListAdapter {
    private EnvironmentHenHouse henHouse;
    private List<EnvironmentInnerBan.ResobjBean.JobCateBean> list_group;
    private Context context;
    int[] group_state_array = new int[]{R.mipmap.move_down,
            R.mipmap.move_up};

    public  EnvironmentInnerAdapter(List<EnvironmentInnerBan.ResobjBean.JobCateBean> list_comment, Context context) {
        this.context = context;
        this.list_group = list_comment;
        henHouse= (EnvironmentHenHouse) context;
    }

    @Override
    public int getGroupCount() {
        return list_group.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list_group.get(i).getHenHouseArray().size() == 0 ? 0 : 1;
    }

    @Override
    public Object getGroup(int i) {
        return list_group.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list_group.get(groupPosition).getHenHouseArray().get(childPosition);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return getCombinedChildId(groupPosition, childPosition);
    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return super.getCombinedChildId(groupId, childId);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int position, boolean isExpanded, View groupView, ViewGroup viewGroup) {
        ViewHolder_Group_Detail group_holder = null;
        if (groupView == null) {
            groupView = LayoutInflater.from(context).inflate(R.layout.item_group_environment_inner, null);
            group_holder = new ViewHolder_Group_Detail(groupView);
            groupView.setTag(group_holder);
        } else {
            group_holder = (ViewHolder_Group_Detail) groupView.getTag();
        }
        if (list_group == null || list_group.isEmpty()) {
            group_holder.group_state.setVisibility(View.INVISIBLE);
            return groupView;
        } else {
            group_holder.group_state.setVisibility(View.VISIBLE);
        }
        EnvironmentInnerBan.ResobjBean.JobCateBean jobCateBean = list_group.get(position);
        group_holder.txt_moduleOne.setText(jobCateBean.getName());
        if (isExpanded) {
            // 设置默认的图片是未选中状态
            group_holder.group_state.setBackgroundResource(group_state_array[0]);
        } else {
            // 设置默认的图片是未选中状态
            group_holder.group_state.setBackgroundResource(group_state_array[1]);
        }
        return groupView;
    }

    public void setList(List<EnvironmentInnerBan.ResobjBean.JobCateBean> list_comment) {
        list_group = list_comment;
        notifyDataSetChanged();
    }

    public static class ViewHolder_Group_Detail {
        @BindView(R.id.txt_moduleOne)
        TextView txt_moduleOne;
        @BindView(R.id.group_state)
        ImageView group_state;

        ViewHolder_Group_Detail(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View childView, ViewGroup viewGroup) {
        ViewHolder_Child_Detail child_Holder = null;
        if (childView == null) {
            childView = LayoutInflater.from(context).inflate(R.layout.item_child_environment_inner, null);
            child_Holder = new ViewHolder_Child_Detail(childView);
            childView.setTag(child_Holder);
        } else {
            child_Holder = (ViewHolder_Child_Detail) childView.getTag();
        }
        List<EnvironmentInnerBan.ResobjBean.JobCateBean.HenHouseArrayBean> listChild = list_group.get(groupPosition).getHenHouseArray();
        if (listChild == null || listChild.isEmpty()) {
            return childView;
        } else {
            child_Holder.mlv_environmentInner.setVisibility(View.VISIBLE);
            EnvironmentInnerListAdapter adapter = new EnvironmentInnerListAdapter(listChild, context);
            child_Holder.mlv_environmentInner.setAdapter(adapter);
            child_Holder.mlv_environmentInner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    DeviceNameE_CodeBean bean = new DeviceNameE_CodeBean();
                    // context.startActivity(new Intent(context, EnergyRecordActivity.class).putExtra("device", (Serializable) (bean)));
                   henHouse.getHenHouse(listChild,position);
                }

            });
        }
        return childView;
    }

    public static class ViewHolder_Child_Detail {
        @BindView(R.id.mlv_environmentInner)
        MyListView mlv_environmentInner;

        ViewHolder_Child_Detail(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}
