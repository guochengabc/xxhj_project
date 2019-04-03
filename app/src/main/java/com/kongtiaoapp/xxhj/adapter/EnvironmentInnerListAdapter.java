package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.EnvironmentInnerBan;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2017/10/16.  环控各鸡舍
 */

public class EnvironmentInnerListAdapter extends BaseAdapter {
    private List<EnvironmentInnerBan.ResobjBean.JobCateBean.HenHouseArrayBean> list;
    private Context context;
    private int clickPosition = -1;//点击position
    private boolean isOperate = false;

    public EnvironmentInnerListAdapter(List<EnvironmentInnerBan.ResobjBean.JobCateBean.HenHouseArrayBean> listChild, Context context) {
        this.list = listChild;
        this.context = context;
        this.isOperate = false;
        clickPosition = -1;
    }

    /**
     * 该界面的构造函数来自于环境监控首页
     */
    public EnvironmentInnerListAdapter(List<EnvironmentInnerBan.ResobjBean.JobCateBean.HenHouseArrayBean> listChild, Context context, int positions) {
        this.list = listChild;
        this.context = context;
        clickPosition = positions;
        this.isOperate = true;
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
        ViewHolders holders = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_three_energy, parent, false);
            holders = new ViewHolders(convertView);
            convertView.setTag(holders);
        } else {
            holders = (ViewHolders) convertView.getTag();
        }
        holders.dtv_oldPeople.setText(list.get(position).getName());
        if (clickPosition != -1) {
            holders.line_backGround.setBackgroundResource(R.color.white);
        } else {
            holders.line_backGround.setBackgroundResource(R.color.eeeeff);
        }
        if (position == 0) {
            holders.dtv_oldPeople.setTextColor(context.getResources().getColor(R.color.theme_color));
        }
        if (clickPosition == position) {
            if (isOperate) {
                holders.dtv_oldPeople.setTextColor(context.getResources().getColor(R.color.theme_color));
                isOperate = false;
            }
        } else if (clickPosition != position) {
            holders.dtv_oldPeople.setTextColor(context.getResources().getColor(R.color.a999999));
        }
        return convertView;
    }

    public void setList(List<EnvironmentInnerBan.ResobjBean.JobCateBean.HenHouseArrayBean> listHenHouse) {
        list = listHenHouse;
        notifyDataSetChanged();
    }

    public void setList(List<EnvironmentInnerBan.ResobjBean.JobCateBean.HenHouseArrayBean> listHenHouse, int position) {
        list = listHenHouse;
        clickPosition = position;
        this.isOperate = true;
        notifyDataSetChanged();
    }

    public void setNotifyUI(int position, boolean isOperate) {
        clickPosition = position;
        this.isOperate = isOperate;
        notifyDataSetChanged();
    }


    public static class ViewHolders {
        @BindView(R.id.line_backGround)
        LinearLayout line_backGround;
        @BindView(R.id.dtv_oldPeople)
        TextView dtv_oldPeople;

        public ViewHolders(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
