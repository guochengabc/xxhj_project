package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.ProjectListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2017/8/9.
 */

public class ProjectManagerAdapter extends BaseAdapter {
    List<ProjectListBean.ResobjBean> list;
    private Context context;

    public ProjectManagerAdapter(List<ProjectListBean.ResobjBean> list, Context context) {
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
        ViewHolder holder = null;
        if (convertView == null) {
//            convertView =  inflater.inflate(R.layout.whatever, parent, false);
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_projectmanager, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txt_project_manager.setText(list.get(position).getProjectName());
        if (position % 2 == 0) {
            holder.txt_project_manager.setBackgroundColor(context.getResources().getColor(R.color.ffffff));
        } else if (position % 2 == 1) {
            holder.txt_project_manager.setBackgroundColor(context.getResources().getColor(R.color.second_module_background));
        }
        return convertView;
    }

    static class ViewHolder {

        @BindView(R.id.txt_project_manager)
        TextView txt_project_manager;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
