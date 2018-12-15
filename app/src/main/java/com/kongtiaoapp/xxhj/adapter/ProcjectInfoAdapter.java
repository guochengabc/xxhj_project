package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.getProjectInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Luoye on 2016-8-8.
 * 说明:设备信息详情
 */
public class ProcjectInfoAdapter extends BaseAdapter {
    private Context mContext;
    private getProjectInfo.ProcjectInfo mObj;

    public ProcjectInfoAdapter(getProjectInfo.ProcjectInfo obj, Context mContext) {
        this.mObj = obj;
        this.mContext = mContext;


    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
//            convertView =  inflater.inflate(R.layout.whatever, parent, false);
            convertView = LayoutInflater.from(App.application).inflate(
                    R.layout.item_device_get_info_param, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        switch (position) {
            case 0://建筑物
                holder.tvName.setText("建筑物");
//                holder.tvContent.setText(mObj.getBuildingName());
                holder.tvContent.setHint(mObj.getBuildingName());
                break;
            case 1://建筑物类型
                holder.tvName.setText("建筑物类型");
                holder.tvContent.setHint(mObj.getBuildingType());
                break;
            case 2://城市
                holder.tvName.setText("城市");
                holder.tvContent.setHint(mObj.getCity());
                break;
            case 3://制冷区域面积
                holder.tvName.setText("制冷区域面积");
                holder.tvContent.setHint(mObj.getCoolingArea());
                break;
            case 4://供回水温差
                holder.tvName.setText("供回水温差");
                holder.tvContent.setHint(mObj.getTempDiff());
                break;
            case 5://值班人员
                holder.tvName.setText("值班人员");
                //TODO 参数不确定
                holder.tvContent.setHint(mObj.getUserId());
                break;
            case 6://设备信息
                holder.tvName.setText("设备信息");
                //TODO 参数不确定
                holder.tvContent.setHint("设备信息");
                break;

            default:
                break;
        }
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_content)
        EditText tvContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
