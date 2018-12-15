package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.SystemParam;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2017/5/6.
 */
public class DeviceSystemParamAdapter extends BaseAdapter {
    private List<SystemParam.ResobjBean.SystemBean> mList;
    private Context mContext;
    private boolean isAuto = false;
    private String types = "00";
    private int TYPE_1 = 1, TYPE_2 = 2;
    private int index = -1;

    public DeviceSystemParamAdapter(List mList, Context mContext, boolean bool) {
        this.mList = mList;
        this.mContext = mContext;
        this.isAuto = bool;
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

        convertView = LayoutInflater.from(mContext).inflate(
                R.layout.item_running_device_get_info_param, parent, false);
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        EditText tvContent = (EditText) convertView.findViewById(R.id.tv_content);
        final SystemParam.ResobjBean.SystemBean item = (SystemParam.ResobjBean.SystemBean) getItem(position);
        if (!TextUtils.isEmpty(item.getUnit())) {
            tvName.setText(item.getValue() + "(" + item.getUnit() + "):");
        } else {
            tvName.setText(item.getValue() + ":");
        }
        if (!TextUtils.isEmpty(item.getMax())) {
            tvContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(item.getMax().length() + 3)});
            tvContent.setHint("范围: " + item.getMin() + "~" + item.getMax());
        }

        /*if (isAuto) {
            tvContent.setText(item.getData());
            tvContent.setFocusable(false);
            return convertView;
        }*/
     /*   if (type.equals("00")) {
            tvContent.setText(item.getData());
        } else if (type.equals("11")) {
            tvContent.setText(item.getMyContent());
        }*/
        tvContent.setText(item.getMyContent());
//        tvContent.setInputType(InputType.TYPE_CLASS_NUMBER);
//        if (item.getFieldType().equals("int")) {
//            tvContent.setInputType(InputType.TYPE_CLASS_NUMBER);
//        } else if (item.getFieldType().equals("float")) {
//            tvContent.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
//        } else {
//            tvContent.setInputType(InputType.TYPE_CLASS_TEXT);
//        }
//        holder.tvContent.clearFocus();

        // 你可以试试把addView放到这个函数的return之前，我保证你会后悔的～～

        // 因为前面说过，addView的先后对画面的结果是有影响的。


        tvContent.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent event) {

                // 在TOUCH的UP事件中，要保存当前的行下标，因为弹出软键盘后，整个画面会被重画

                // 在getView方法的最后，要根据index和当前的行下标手动为EditText设置焦点

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    index = position;

                }

                return false;

            }

        });

//        final ViewHolder finalHolder = holder;
        tvContent.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable) {

            }

            public void beforeTextChanged(CharSequence text, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence text, int start, int before, int count) {
                DeviceSystemParamAdapter.this.types = "11";
                // 在这个地方添加你的保存文本内容的代码，如果不保存，你就等着重新输入吧
                // 而且不管你输入多少次，也不会有用的，因为getView全清了～～
//                finalHolder.tvContent.setText(text);
                item.setMyContent(text.toString());

            }

        });


        // 这个地方可以添加将保存的文本内容设置到EditText上的代码，会有用的～～


        tvContent.clearFocus();

        if (index != -1 && index == position) {

            // 如果当前的行下标和点击事件中保存的index一致，手动为EditText设置焦点。

            tvContent.requestFocus();

        }
        if(!TextUtils.isEmpty(mList.get(position).getCode())){
            if (mList.get(position).getCode().equals("SplitLine")) {
                tvContent.setVisibility(View.GONE);
                tvName.setText("");
                // tvName.setPadding(0, 0, 15, 0);
                tvName.setBackgroundResource(R.mipmap.splitline);
                tvName.setGravity(Gravity.CENTER);
            }
        }

        return convertView;

    }

    public void remove(int from) {
        mList.remove(from);
        this.notifyDataSetChanged();
    }

    public void insert(SystemParam.ResobjBean.SystemBean item, int to) {
        mList.add(to, item);
        this.notifyDataSetChanged();
    }


    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_content)
        EditText tvContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
