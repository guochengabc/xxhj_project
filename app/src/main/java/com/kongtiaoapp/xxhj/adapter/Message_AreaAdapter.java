package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.CommentsGroupBean;
import com.kongtiaoapp.xxhj.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2017/3/23.
 */
public class Message_AreaAdapter extends BaseExpandableListAdapter {
    private List<CommentsGroupBean.ResobjBean> list_group;
    private Context context;

    public Message_AreaAdapter(List<CommentsGroupBean.ResobjBean> list_comment, Context context) {
        this.context = context;
        this.list_group = list_comment;
    }

    @Override
    public int getGroupCount() {
        return list_group.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list_group.get(i).getComments().size();
    }

    @Override
    public Object getGroup(int i) {
        return list_group.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return list_group.get(i).getComments().get(i1);
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
    public View getGroupView(int position, boolean b, View groupView, ViewGroup viewGroup) {
        ViewHolder_Group_Detail group_holder = null;
        if (groupView == null) {
            groupView = LayoutInflater.from(context).inflate(R.layout.item_group_comments, null);
            group_holder = new ViewHolder_Group_Detail(groupView);
            groupView.setTag(group_holder);
        } else {
            group_holder = (ViewHolder_Group_Detail) groupView.getTag();
        }
        group_holder.txt_gp_name.setText(list_group.get(position).getUserName() + "：");
        group_holder.txt_gp_content.setText("\u3000\u3000" + list_group.get(position).getCommentInfo());
        group_holder.content_time.setText(TimeUtils.getStandardDate(list_group.get(position).getCreateTime()));
        group_holder.content_time.setText(TimeUtils.getStandardDate(list_group.get(position).getCreateTime()));
        return groupView;
    }

    public void setList(List<CommentsGroupBean.ResobjBean> list_comment) {
        list_group = list_comment;
        notifyDataSetChanged();
    }

    public static class ViewHolder_Group_Detail {
        @BindView(R.id.txt_gp_name)
        TextView txt_gp_name;
        @BindView(R.id.txt_gp_content)
        TextView txt_gp_content;
        @BindView(R.id.content_time)
        TextView content_time;

        ViewHolder_Group_Detail(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View childView, ViewGroup viewGroup) {
        ViewHolder_Child_Detail child_Holder = null;
        if (childView == null) {
            childView = LayoutInflater.from(context).inflate(R.layout.item_child_comments, null);
            child_Holder = new ViewHolder_Child_Detail(childView);
            childView.setTag(child_Holder);
        } else {
            child_Holder = (ViewHolder_Child_Detail) childView.getTag();
        }
        child_Holder.txt_cl_name.setText(list_group.get(groupPosition).getComments().get(childPosition).getUserName() + "回复" + "：");
        child_Holder.txt_cl_content.setText("\u3000\u3000" + list_group.get(groupPosition).getComments().get(childPosition).getCommentInfo());
        child_Holder.content_child_time.setText(TimeUtils.getStandardDate(list_group.get(groupPosition).getComments().get(childPosition).getCreateTime()));
        child_Holder.content_child_time.setText(TimeUtils.getStandardDate(list_group.get(groupPosition).getComments().get(childPosition).getCreateTime()));
        return childView;
    }

    public static class ViewHolder_Child_Detail {
        @BindView(R.id.txt_cl_name)
        TextView txt_cl_name;
        @BindView(R.id.txt_cl_content)
        TextView txt_cl_content;
        @BindView(R.id.content_child_time)
        TextView content_child_time;

        ViewHolder_Child_Detail(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}
