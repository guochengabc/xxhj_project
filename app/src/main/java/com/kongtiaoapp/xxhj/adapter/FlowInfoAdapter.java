package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.FlowInfoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2017/8/10.
 */

public class FlowInfoAdapter extends BaseAdapter {
    private List<FlowInfoBean.ResobjBean> list;
    private Context context;

    public FlowInfoAdapter(List<FlowInfoBean.ResobjBean> list, Context context) {
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
        ViewHolders holder = null;
        if (convertView == null) {
//            convertView =  inflater.inflate(R.layout.whatever, parent, false);
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_flowinfo, parent, false);
            holder = new ViewHolders(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolders) convertView.getTag();
        }
        holder.txt_dowhat.setText(list.get(position).getFlowDesc());
        holder.txt_status.setText(list.get(position).getFlowName());
        String flowName = list.get(position).getFlowName();
        if (flowName.equals("结束") || flowName.equals("异常")) {
            holder.txt_status.setTextColor(context.getResources().getColor(R.color.main_module_count));
        }
        if (position == 0) {
            holder.img_flow.setImageResource(R.drawable.shape_redclircle);
        } else {
            holder.img_flow.setImageResource(R.drawable.shape_blackecircle);
        }
        holder.txt_time.setText(list.get(position).getCreateTime());
        return convertView;
    }

    public void setList(List<FlowInfoBean.ResobjBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class ViewHolders {
        @BindView(R.id.txt_dowhat)
        TextView txt_dowhat;
        @BindView(R.id.txt_time)
        TextView txt_time;
        @BindView(R.id.txt_status)
        TextView txt_status;
        @BindView(R.id.img_flow)
        ImageView img_flow;

        public ViewHolders(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
