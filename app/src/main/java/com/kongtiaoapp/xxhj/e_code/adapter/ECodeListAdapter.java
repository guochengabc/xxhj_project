package com.kongtiaoapp.xxhj.e_code.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.ECodeListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 郭成 on 2018/1/15.
 */

public class ECodeListAdapter extends BaseAdapter {
    private Context context;
    private List<ECodeListBean.ResobjBean> list;

    public ECodeListAdapter(Context mActivity, List<ECodeListBean.ResobjBean> list) {
        this.context = mActivity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.isEmpty() ? 0 : list.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_goodslist, parent, false);
            holders = new ViewHolders(convertView);
            convertView.setTag(holders);
        } else {
            holders = (ViewHolders) convertView.getTag();
        }
        ECodeListBean.ResobjBean bean = list.get(position);
        holders.txt_deviceName.setText(bean.getName());

        return convertView;
    }

    public void adapterList(List<ECodeListBean.ResobjBean> listAll) {
        list = listAll;
        notifyDataSetChanged();
    }

    public class ViewHolders {
        @BindView(R.id.txt_deviceName)
        TextView txt_deviceName;//设备名

        public ViewHolders(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
