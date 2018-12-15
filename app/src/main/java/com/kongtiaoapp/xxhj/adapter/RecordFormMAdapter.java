package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.RecordFormMBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by G_XXHJ on 2018/11/22.
 */

public class RecordFormMAdapter extends BaseAdapter {
    private Context context;
    private List<RecordFormMBean.ResobjBean.RecordModuleBean> list;
    public RecordFormMAdapter(Context context, List<RecordFormMBean.ResobjBean.RecordModuleBean> list) {
        this.context=context;
        this.list=list;
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
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_module_click, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txt_module.setText(list.get(position).getModuleName());
        return convertView;
    }
    public class ViewHolder {
        @BindView(R.id.img_module)
        ImageView img_module;//图片
        @BindView(R.id.txt_module)
        TextView txt_module;//文字


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
