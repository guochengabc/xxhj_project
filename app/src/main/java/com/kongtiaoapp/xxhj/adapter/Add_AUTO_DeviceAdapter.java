package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.RunningParam;
import com.kongtiaoapp.xxhj.interfaces.Add_AUTO_DeviceRunning;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kongtiaoapp.xxhj.App.sp;

/**
 * Created by xxhj_g on 2017/7/11.
 */

public class Add_AUTO_DeviceAdapter extends BaseAdapter {
    private List<RunningParam> mList;
    private Context mContext;
    private boolean isAuto = false;
    private String types = "00";
    private int TYPE_1 = 1, TYPE_2 = 2;
    private int index = -1;
    public Add_AUTO_DeviceRunning add_auto_deviceRunning;
    public Add_AUTO_DeviceAdapter(List mList, Context mContext, boolean bool) {
        this.mList = mList;
        this.mContext = mContext;
        this.isAuto = bool;
        add_auto_deviceRunning= (Add_AUTO_DeviceRunning) mContext;
    }

    public List getmList() {
        return this.mList;
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView=null;
        if (sp.getFontSize().equals("2")) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_add_auto_device_big, parent, false);
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_add_auto_device, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tvContent = (TextView) convertView.findViewById(R.id.tv_content);
        if (sp.getFontSize().equals("0")){
            tvName.setTextSize(17);
            tvContent.setTextSize(17);
        }else if (sp.getFontSize().equals("1")){
            tvName.setTextSize(20);
            tvContent.setTextSize(20);
        }else if (sp.getFontSize().equals("2")){
            tvName.setTextSize(28);
            tvContent.setTextSize(28);
        }
        final RunningParam item = (RunningParam) getItem(position);

        if (!TextUtils.isEmpty(item.getUnit())) {
            tvName.setText(item.getValue() + "(" + item.getUnit() + "):");
        } else {
            tvName.setText(item.getValue() + ":");
        }
        tvContent.setText(item.getData());
        if (!TextUtils.isEmpty(mList.get(position).getCode())) {
            if (mList.get(position).getCode().equals("SplitLine")) {
                tvContent.setVisibility(View.GONE);
                tvName.setText("");
                // tvName.setPadding(0, 0, 15, 0);
                tvName.setBackgroundResource(R.mipmap.splitline);
                tvName.setGravity(Gravity.CENTER);

            }
        }
        tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_auto_deviceRunning.getCodeing(mList.get(position));
            }
        });

        return convertView;

    }

    public void remove(int from) {
        mList.remove(from);
        this.notifyDataSetChanged();
    }

    public void insert(RunningParam item, int to) {
        mList.add(to, item);
        this.notifyDataSetChanged();
    }

    public void setList(List<RunningParam> mList, boolean isAuto) {
        this.mList = mList;
        this.isAuto = isAuto;
        notifyDataSetChanged();
    }


    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_content)
        TextView tvContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
