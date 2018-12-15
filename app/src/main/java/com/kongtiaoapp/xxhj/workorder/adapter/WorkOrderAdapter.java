package com.kongtiaoapp.xxhj.workorder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.UserManegerList;
import com.kongtiaoapp.xxhj.bean.WorkOrderListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2017/7/19.   status  0 已读   1   未读
 */

public class WorkOrderAdapter extends BaseAdapter {
    List<WorkOrderListBean.ResobjBean> list;
    private Context context;

    public WorkOrderAdapter(List<WorkOrderListBean.ResobjBean> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_work_order, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        WorkOrderListBean.ResobjBean bean = list.get(position);
        int type = bean.getDispType();
        //报修单
        if (type == 0) {
            holder.txt_type.setText("报");
            holder.txt_type.setBackgroundResource(R.drawable.text_bluecircle);
            holder.txt_time.setText("报修时间：");
            holder.txt_repair_formTime.setText(bean.getReportTime());
            holder.txt_repairPerson.setText("派工程师：");
        } else if (type == 1) {
            holder.txt_type.setText("返");
            holder.txt_type.setBackgroundResource(R.drawable.text_redcircle);
            holder.txt_time.setText("返单原因：");
            holder.txt_repair_formTime.setText(bean.getEvaluateDesc());
            holder.txt_repairPerson.setText("返工程师：");
        }
        holder.work_number.setText(bean.getDispatchNum() + "");
        holder.txt_status.setText(bean.getDispStateName() + "");
        holder.txt_repair_content.setText(bean.getContent());
        holder.txt_repair_place.setText(bean.getLocation());
        holder.txt_repair_people_content.setText(bean.getRepairUserName());
        holder.txt_repair_formLimitTime.setText(bean.getReportTime());
        int dispState = bean.getDispState();
        if (((dispState + "").equals("0") ||(dispState + "").equals("1") ) && (UserManegerList.WORKORDER_ENGI() || UserManegerList.WORKORDER_MANAGER() || UserManegerList.WORKORDER_EDITOR())) {
            holder.txt_readStatus.setVisibility(View.VISIBLE);
        } else if ((dispState + "").equals("5") && UserManegerList.WORKORDER_INSP()) {
            holder.txt_readStatus.setVisibility(View.VISIBLE);
        } else if ((dispState + "").equals("4") && (UserManegerList.WORKORDER_DISP() || UserManegerList.WORKORDER_MANAGER() || UserManegerList.WORKORDER_EDITOR())) {
            holder.txt_readStatus.setVisibility(View.VISIBLE);
        } else {
            holder.txt_readStatus.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    public void setList(List<WorkOrderListBean.ResobjBean> beanList) {
        list = beanList;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @BindView(R.id.txt_type)//工单类型
                TextView txt_type;
        @BindView(R.id.txt_repair_content)//报修内容
                TextView txt_repair_content;
        @BindView(R.id.txt_repair_place)//报修地点
                TextView txt_repair_place;
        @BindView(R.id.txt_time)//报修内容
                TextView txt_time;
        @BindView(R.id.txt_repair_formTime)//发单时间或者报修时间
                TextView txt_repair_formTime;
        @BindView(R.id.txt_repairPerson)//报修人或者指派工程师
                TextView txt_repairPerson;
        @BindView(R.id.txt_repair_people_content)//报修人
                TextView txt_repair_people_content;
        @BindView(R.id.txt_status)//派单后工程师的状态
                TextView txt_status;
        @BindView(R.id.txt_repair_formLimitTime)
        TextView txt_repair_formLimitTime;
        @BindView(R.id.txt_workNumber)
        TextView txt_workNumber;
        @BindView(R.id.work_number)
        TextView work_number;
        @BindView(R.id.txt_readStatus)
        TextView txt_readStatus;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
