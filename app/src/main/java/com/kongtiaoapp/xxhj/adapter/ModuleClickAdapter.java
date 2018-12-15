package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.ModuleBean;
import com.kongtiaoapp.xxhj.utils.BigToSmallUtils;
import com.kongtiaoapp.xxhj.utils.DrawableUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2017/9/25.  首页各模块点击响应
 */

public class ModuleClickAdapter extends BaseAdapter {
    private List<ModuleBean.ResobjBean.MainArrayBean.ModuleArrayBean> list;
    private Context context;

    public ModuleClickAdapter(Context context, List<ModuleBean.ResobjBean.MainArrayBean.ModuleArrayBean> list) {
        this.context = context;
        this.list = list;
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
        ModuleBean.ResobjBean.MainArrayBean.ModuleArrayBean bean = list.get(position);
        try {
        if (!bean.getPackageName().equals("")) {
                holder.img_module.setImageResource(DrawableUtils.getLeveImage(BigToSmallUtils.pascalToUnderline(bean.getIconName())));
                holder.txt_module.setText(bean.getModuleName());

        }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        return convertView;
    }

    public void setList(List<ModuleBean.ResobjBean.MainArrayBean.ModuleArrayBean> list_data) {
        list = list_data;
        notifyDataSetChanged();
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
