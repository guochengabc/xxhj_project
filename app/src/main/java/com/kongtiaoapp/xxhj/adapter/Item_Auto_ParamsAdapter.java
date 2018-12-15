package com.kongtiaoapp.xxhj.adapter;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.Auto_Manual_ParamsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2016/12/29.
 */
public class Item_Auto_ParamsAdapter extends BaseAdapter {
    List<Auto_Manual_ParamsBean.ResobjBean.ParamBean> list;
    private int index = -1;
    private String deviceNam="";
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
    public View getView(final int position, View convertView, ViewGroup parent) {
      /*  ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(App.application).inflate(
                    R.layout.item_auto_params_lv, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }*/
        convertView = LayoutInflater.from(App.application).inflate(
                R.layout.item_auto_params_lv, parent, false);
        TextView tv_name = ((TextView) convertView.findViewById(R.id.tv_name));
        EditText tv_content = ((EditText) convertView.findViewById(R.id.tv_content));
        final Auto_Manual_ParamsBean.ResobjBean.ParamBean item = (Auto_Manual_ParamsBean.ResobjBean.ParamBean) getItem(position);
        tv_name.setText(item.getValue());
        if (!TextUtils.isEmpty(list.get(position).getMax())) {
            tv_content.setHint("范围: " + list.get(position).getMin() + "~" + list.get(position).getMax());
        }
        tv_content.setText(item.getMyContent());
        if (item.getMyContent() != null) {
            item.setMyContent(item.getMyContent());
        }

        tv_content.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    index = position;
                }
                return false;
            }

        });
        tv_content.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {

            }

            public void beforeTextChanged(CharSequence text, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence text, int start, int before, int count) {

                item.setMyContent(text.toString());

            }

        });

        tv_content.clearFocus();

        if (index != -1 && index == position) {

            // 如果当前的行下标和点击事件中保存的index一致，手动为EditText设置焦点。

            tv_content.requestFocus();

        }
        return convertView;
    }

    public void setList(List<Auto_Manual_ParamsBean.ResobjBean.ParamBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<Auto_Manual_ParamsBean.ResobjBean.ParamBean> getList() {
        return this.list;
    }

    public class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_content)
        EditText tv_content;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
