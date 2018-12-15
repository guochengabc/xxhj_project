package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.ProjectList;
import com.kongtiaoapp.xxhj.utils.TimeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Luoye on 2016-6-15.
 */
public class ProjectInfoAdapter extends BaseAdapter {
    private Context mContext;
    private List mList;
    public ProjectInfoAdapter(Context context, List list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void remove(int from) {
        mList.remove(from);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(App.application).inflate(
                    R.layout.item_project_info, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ProjectList.ResobjBean item = ( ProjectList.ResobjBean) getItem(position);
        //项目名称
        if (!TextUtils.isEmpty(item.getName())) {
            holder.tvProjectName.setText(item.getName());
        } else {
            holder.tvProjectName.setText("");
        }
        //项目时间
        if (!TextUtils.isEmpty(item.getTime())) {
            holder.tvProjectTime.setText(TimeUtils.getDateToString(item.getTime()));
        } else {
            holder.tvProjectTime.setText("");
        }
        //项目地址
        if (!TextUtils.isEmpty(item.getBuildingName())) {
            holder.tvProjectAddress.setText(item.getBuildingName());
        } else {
            holder.tvProjectAddress.setText("");
        }
        return convertView;
    }


    class ViewHolder {
        @BindView(R.id.tv_project_name)
        TextView tvProjectName;
        @BindView(R.id.tv_project_time)
        TextView tvProjectTime;
        @BindView(R.id.tv_project_address)
        TextView tvProjectAddress;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
