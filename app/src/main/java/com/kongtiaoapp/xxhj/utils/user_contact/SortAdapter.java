package com.kongtiaoapp.xxhj.utils.user_contact;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.SortModel;
import com.kongtiaoapp.xxhj.utils.GlideCircleTransform;

import java.util.List;

/**
 * @author J 适配器
 */
public class SortAdapter extends BaseAdapter implements SectionIndexer {
    private List<SortModel> list = null;
    private Context mContext;
    private boolean isNeedCheck;

    public boolean isNeedCheck() {
        return isNeedCheck;
    }

    public void setNeedCheck(boolean isNeedCheck) {
        this.isNeedCheck = isNeedCheck;
    }

    public SortAdapter(Context mContext, List<SortModel> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public void updateListView(List<SortModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final SortModel mContent = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_user_contact, null);
            viewHolder.tvTitle = (TextView) view
                    .findViewById(R.id.tv_user_item_name);
            viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
            viewHolder.checked = (ImageView) view
                    .findViewById(R.id.iv_user_item_check);
            viewHolder.icon = (ImageView) view
                    .findViewById(R.id.iv_user_item_icon);
          /*  viewHolder.sex = (ImageView) view
                    .findViewById(R.id.iv_user_item_sex);*/
            viewHolder.tvPhone = (TextView) view
                    .findViewById(R.id.tv_user_item_phone);
            viewHolder.tv_user_item_levelName = (TextView) view
                    .findViewById(R.id.tv_user_item_levelName);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        int section = getSectionForPosition(position);

        if (position == getPositionForSection(section)) {
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            viewHolder.tvLetter.setText(mContent.getSortLetters());
        } else {
            viewHolder.tvLetter.setVisibility(View.GONE);
        }
        SortModel model = list.get(position);
        if (isNeedCheck) {
            viewHolder.checked.setVisibility(View.VISIBLE);
            if (model.isChecked())
                viewHolder.checked.setImageResource(R.mipmap.round_checked);
            else
                viewHolder.checked.setImageResource(R.mipmap.round_unchecked);
        } else {
            viewHolder.checked.setVisibility(View.GONE);
        }
        viewHolder.tvPhone.setText(model.getPhone());
        viewHolder.tvTitle.setText(model.getName());
        if (model.getLevel().equals("B")) {
            viewHolder.tv_user_item_levelName.setVisibility(View.VISIBLE);
            viewHolder.tv_user_item_levelName.setText(model.getLevelName());
        } else {
            viewHolder.tv_user_item_levelName.setVisibility(View.GONE);
        }
        // 头像采用imageloader加载
     /*   viewHolder.sex.setImageResource(model.getSex() == 0 ? R.mipmap.boy
                : R.mipmap.girl);*/
        Log.i("fffffffff", "===图像===" + ConstantValue.URL + model.getIconUrl());
        Glide.with(mContext).load(ConstantValue.URL + model.getIconUrl()).placeholder(R.mipmap.default_head).transform(new GlideCircleTransform(mContext)).crossFade().into(viewHolder.icon);
        return view;

    }

    public void setList(List<SortModel> sourceDateList) {
        list = sourceDateList;
        this.notifyDataSetChanged();
    }

    final static class ViewHolder {
        TextView tvLetter;
        TextView tvTitle;
        TextView tvPhone;
        TextView tv_user_item_levelName;
        ImageView checked, icon, sex;
    }

    /**
     * 得到首字母的ascii值
     */
    public int getSectionForPosition(int position) {
        return list.get(position).getSortLetters().charAt(0);
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    public String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }
}