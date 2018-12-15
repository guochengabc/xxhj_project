package com.kongtiaoapp.xxhj.e_code.adapter;

import android.app.Activity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.RunningParam;
import com.kongtiaoapp.xxhj.ui.timeselector.activity.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kongtiaoapp.xxhj.App.sp;

/**
 * Created by G_XXHJ on 2018/5/29.
 */

public class NameplateInfoAdapter extends BaseAdapter {
    private List<RunningParam> mList;
    private Activity mContext;
    private boolean isAuto = false;
    private String types = "00";
    private int TYPE_1 = 1, TYPE_2 = 2;
    private int index = -1;

    public NameplateInfoAdapter(List mList, Activity mContext, boolean bool) {
        this.mList = mList;
        this.mContext = mContext;
        this.isAuto = bool;
    }

    public NameplateInfoAdapter(List mList, Activity mContext) {
        this.mList = mList;
        this.mContext = mContext;
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
        convertView = null;
        if (sp.getFontSize().equals("2")) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_nameplate_big, parent, false);
        } else {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_nameplate, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tvContent = (TextView) convertView.findViewById(R.id.tv_content);
        if (App.sp.getFontSize().equals("0")) {
            tvName.setTextSize(17);
            tvContent.setTextSize(17);
        } else if (sp.getFontSize().equals("1")) {
            tvName.setTextSize(20);
            tvContent.setTextSize(20);
        } else if (sp.getFontSize().equals("2")) {
            tvName.setTextSize(28);
            tvContent.setTextSize(28);
        }
        final RunningParam item = (RunningParam) getItem(position);
        String fieldType = item.getFieldType();
        if (fieldType.equals("time")) {
            tvContent.setFocusable(false);
        } else {
            tvContent.setFocusable(true);
        }
        if (!TextUtils.isEmpty(item.getUnit())) {
            tvName.setText(item.getValue() + "(" + item.getUnit() + "):");
        } else {
            tvName.setText(item.getValue() + ":");
        }

        tvContent.setText(item.getName());
        tvContent.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent event) {

                index = position;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (mList.get(position).getFieldType().equals("time")) {
                            setdatePicker(item, tvContent);
                        }


                        break;
                    case MotionEvent.ACTION_DOWN:


                        break;
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
                NameplateInfoAdapter.this.types = "11";
                // 在这个地方添加你的保存文本内容的代码，如果不保存，你就等着重新输入吧
                // 而且不管你输入多少次，也不会有用的，因为getView全清了～～
//                finalHolder.tvContent.setText(text);
                item.setMyContent(text.toString());
                if (item.getFieldType().equals("time")) {
                    Log.i("fffffffffffff", "onTextChanged==========");

                }

            }

        });


        // 这个地方可以添加将保存的文本内容设置到EditText上的代码，会有用的～～


        tvContent.clearFocus();

        if (index != -1 && index == position) {

            // 如果当前的行下标和点击事件中保存的index一致，手动为EditText设置焦点。

            tvContent.requestFocus();

        }
        if (!TextUtils.isEmpty(mList.get(position).getCode())) {
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


    private void setdatePicker(RunningParam item, TextView tvContent) {

        TimePickerView pvTime = new TimePickerView(mContext, TimePickerView.Type.HOURS_MINS);
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                String date_time = format.format(date);
                item.setMyContent(date_time);
                tvContent.setText(item.getMyContent());
            }
        });
        pvTime.show();

    }


}
