package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.EnvironmentStateBean;
import com.kongtiaoapp.xxhj.ui.view.DF_HScrollView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by G_XXHJ on 2018/7/6.
 */

public class EnvironmentStateAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<EnvironmentStateBean.ResobjBean.DataBean> list_group;
    private List<DF_HScrollView> mHScrollViews;
    private ExpandableListView mListView;

    public EnvironmentStateAdapter(Context context, List<EnvironmentStateBean.ResobjBean.DataBean> list, List<DF_HScrollView> mHScrollViews, ExpandableListView mListView) {
        this.context = context;
        this.list_group = list;
        this.mHScrollViews = mHScrollViews;
        this.mListView = mListView;
    }

    @Override
    public int getGroupCount() {
        return list_group.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list_group.get(i).getReginMData().size();
    }

    @Override
    public Object getGroup(int i) {
        return list_group.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return list_group.get(i).getReginMData().get(i1);
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

    public static class ViewHolder_Group_Detail {
        @BindView(R.id.txt_environment)
        TextView txt_environment;
        @BindView(R.id.txt_value)
        TextView txt_value;

        ViewHolder_Group_Detail(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View groupView, ViewGroup parent) {
        ViewHolder_Group_Detail group_holder = null;
        if (groupView == null) {
            groupView = LayoutInflater.from(context).inflate(R.layout.item_group_environment, null);
            group_holder = new ViewHolder_Group_Detail(groupView);
            groupView.setTag(group_holder);
        } else {
            group_holder = (ViewHolder_Group_Detail) groupView.getTag();
        }
        EnvironmentStateBean.ResobjBean.DataBean dataBean = list_group.get(groupPosition);
        group_holder.txt_environment.setText(dataBean.getTypeName());
        group_holder.txt_value.setText(dataBean.getRCount());
        return groupView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder_Child_Detail child_Holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.group_item, null);
            //要记住关键点:第一次初始化的时候装进来
            addHViews((DF_HScrollView) convertView.findViewById(R.id.item_scroll));
            child_Holder = new ViewHolder_Child_Detail(convertView);
            convertView.setTag(child_Holder);
        } else {
            child_Holder = (ViewHolder_Child_Detail) convertView.getTag();
        }
        EnvironmentStateBean.ResobjBean.DataBean.ReginMDataBean bean = list_group.get(groupPosition).getReginMData().get(childPosition);
        child_Holder.item_title.setText(bean.getRName());
        child_Holder.item_move1.setText(bean.getSta());
        child_Holder.item_move2.setText(bean.getCPatt());
        child_Holder.item_move3.setText(bean.getWSS());
        child_Holder.item_move4.setText(bean.getRTemp());
        child_Holder.item_move5.setText(bean.getSTemp());
        return convertView;
    }

    public static class ViewHolder_Child_Detail {
        @BindView(R.id.item_title)
        TextView item_title;
        @BindView(R.id.item_move1)
        TextView item_move1;
        @BindView(R.id.item_move2)
        TextView item_move2;
        @BindView(R.id.item_move3)
        TextView item_move3;
        @BindView(R.id.item_move4)
        TextView item_move4;
        @BindView(R.id.item_move5)
        TextView item_move5;

        ViewHolder_Child_Detail(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void addHViews(final DF_HScrollView hScrollView) {
        if (!mHScrollViews.isEmpty()) {
            int size = mHScrollViews.size();
            DF_HScrollView scrollView = mHScrollViews.get(size - 1);
            final int scrollX = scrollView.getScrollX();
            //第一次满屏后，向下滑动，有一条数据在开始时未加入
            if (scrollX != 0) {
                mListView.post(new Runnable() {
                    @Override
                    public void run() {
                        //当listView刷新完成之后，把该条移动到最终位置
                        hScrollView.scrollTo(scrollX, 0);
                    }
                });
            }
        }
        mHScrollViews.add(hScrollView);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
