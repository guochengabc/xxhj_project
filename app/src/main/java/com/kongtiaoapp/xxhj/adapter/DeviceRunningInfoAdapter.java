package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.RunningDevice;
import com.kongtiaoapp.xxhj.db.ProjectTable;
import com.kongtiaoapp.xxhj.db.XUtil;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Shinelon on 2016/6/17.
 */
public class DeviceRunningInfoAdapter extends BaseAdapter {

    private List<RunningDevice.RunningDeviceList> mList;
    private Context context;
    private ProjectTable project;
    private DbManager db;

    public DeviceRunningInfoAdapter(Context context, List<RunningDevice.RunningDeviceList> list) {
        super();
        this.context = context;
        this.mList = list;
        DbManager.DaoConfig daoConfig = XUtil.getDaoConfig();
        db = x.getDb(daoConfig);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
//            convertView =  inflater.inflate(R.layout.whatever, parent, false);
          /*  convertView = LayoutInflater.from(App.application).inflate(
                    R.layout.item_device, parent, false);*/
            convertView = LayoutInflater.from(App.application).inflate(
                    R.layout.item_bpd, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        RunningDevice.RunningDeviceList data = (RunningDevice.RunningDeviceList) getItem(position);
       /* if (data.getType().equals("bbcs")) {
            if (data.getName().equals("电制冷设备能源消耗")) {
                holder.ivImage.setImageResource(R.mipmap.unit);
            } else if (data.getName().equals("锅炉能源消耗")) {
                holder.ivImage.setImageResource(R.mipmap.boiler);
            } else if (data.getName().equals("蓝标直燃机点检")) {
                holder.ivImage.setImageResource(R.mipmap.d_f_machine);
            } else if (data.getName().equals("蓝标直燃机能源消耗")) {
                holder.ivImage.setImageResource(R.mipmap.d_f_machine);
            } else if (data.getName().equals("民生直燃机点检")) {
                holder.ivImage.setImageResource(R.mipmap.d_f_machine);
            } else if (data.getName().equals("民生直燃机能源消耗")) {
                holder.ivImage.setImageResource(R.mipmap.d_f_machine);
            } else if (data.getName().equals("约克冷机点检")) {
                holder.ivImage.setImageResource(R.mipmap.unit);
            }
        } else {
            holder.ivImage.setImageResource(DrawableUtils.getLeveImage(data.getType()));
        }holder.tvNmae.setText(data.getName());
        */
        try {
            project = db.selector(ProjectTable.class).where("projectId", "=", App.sp.getProjectId()).and("deviceId", "=", data.getDeviceId()).findFirst();
            if (project != null && project.isSave()) {
                holder.iv_gou.setVisibility(View.VISIBLE);
            } else {
                holder.iv_gou.setVisibility(View.INVISIBLE);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
       holder.tvContent.setText(data.getName());
        return convertView;
    }

    public static  class ViewHolder {
       /* @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.iv_gou)
        ImageView ivGou;
        @BindView(R.id.tv_name)
        TextView tvNmae;*/
       @BindView(R.id.iv_gou)
        ImageView iv_gou;
        @BindView(R.id.item1)
        TextView tvContent;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
