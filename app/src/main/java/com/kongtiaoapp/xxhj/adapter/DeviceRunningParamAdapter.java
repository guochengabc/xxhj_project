package com.kongtiaoapp.xxhj.adapter;

import android.app.Activity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.RunningParam;
import com.kongtiaoapp.xxhj.ui.timeselector.activity.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kongtiaoapp.xxhj.App.sp;

/**
 * Created by Luoye on 2016-8-8.
 * 说明:设备信息详情
 */
public class DeviceRunningParamAdapter extends BaseAdapter {
    private List<RunningParam> mList;
    private Activity mContext;
    private boolean isAuto = false;
    private String types = "00";
    private int TYPE_1 = 1, TYPE_2 = 2;
    private int index = -1;
    private String dateShowType = "yyyy-MM-dd HH:mm:ss";

    public DeviceRunningParamAdapter(List mList, Activity mContext, boolean bool) {
        this.mList = mList;
        this.mContext = mContext;
        this.isAuto = bool;
    }

    public DeviceRunningParamAdapter(List mList, Activity mContext) {
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
        if (App.sp.getFontSize().equals("2")) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_running_device_get_info_param_big, parent, false);
        } else {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_running_device_get_info_param, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        EditText tvContent = (EditText) convertView.findViewById(R.id.tv_content);
        TextView tv_content_click = (TextView) convertView.findViewById(R.id.tv_content_click);
        if (sp.getFontSize().equals("0")) {
            tvName.setTextSize(17);
            tvContent.setTextSize(17);
        } else if (App.sp.getFontSize().equals("1")) {
            tvName.setTextSize(20);
            tvContent.setTextSize(20);
        } else if (App.sp.getFontSize().equals("2")) {
            tvName.setTextSize(28);
            tvContent.setTextSize(28);
        }
        final RunningParam item = (RunningParam) getItem(position);
        String fieldType = item.getFieldType();

        if (!TextUtils.isEmpty(item.getUnit())) {
            tvName.setText(item.getValue() + "(" + item.getUnit() + "):");
        } else {
            tvName.setText(item.getValue() + ":");
        }
        if (!TextUtils.isEmpty(item.getMax())) {
            tvContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(item.getMax().length() + 3)});
            tvContent.setHint("范围: " + item.getMin() + "~" + item.getMax());
        }
        if (fieldType.equals("string")) {
            tvContent.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_CLASS_TEXT);//切换文本
        } else if (fieldType.equals("float")) {
            tvContent.setInputType(InputType.TYPE_CLASS_PHONE);//切换数字键盘
        } else {
            tvContent.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_CLASS_TEXT);//切换文本
        }

        tvContent.setText(item.getMyContent());
        switch (fieldType) {
            case ConstantValue.DATEMONTH:
                dateShowType = "yyyy-MM";
                setRecordTime(position, tv_content_click, tvContent,TimePickerView.Type.YEAR_MONTH);
                break;
            case ConstantValue.DATEDAY:
                dateShowType = "yyyy-MM-dd";
                setRecordTime(position, tv_content_click,tvContent, TimePickerView.Type.YEAR_MONTH_DAY);
                break;
            case ConstantValue.DATEHOUR:
                dateShowType = "yyyy-MM-dd HH";
                setRecordTime(position, tv_content_click,tvContent, TimePickerView.Type.YEAR_MONTH_DAY_HOURS);
                break;
            case ConstantValue.DATEMINUTE:
                dateShowType = "yyyy-MM-dd HH:mm";
                setRecordTime(position, tv_content_click, tvContent,TimePickerView.Type.YEAR_MONTH_DAY_HOURS_MIN);
                break;
            case ConstantValue.DATESECOND:
                dateShowType = "yyyy-MM-dd HH:mm:ss";
                setRecordTime(position, tv_content_click, tvContent,TimePickerView.Type.ALL);
                break;
            case "time":
                tvContent.setFocusable(false);
                break;
            default:
                tvContent.setFocusable(true);
                break;
        }
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
                DeviceRunningParamAdapter.this.types = "11";
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

    private void setRecordTime(int position, TextView  textView, TextView tvContent,TimePickerView.Type type) {
        tvContent.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);
       textView.setText(ConstantValue.PLEASE_TIME);
       textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerView pvTime = new TimePickerView(mContext, type);
                pvTime.setTime(new Date());
                pvTime.setCyclic(false);
                pvTime.setCancelable(true);
                pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date) {
                       textView.setText(getTime(date));
                        RunningParam  item = (RunningParam) getItem(position);
                        item.setMyContent(getTime(date));

                        tvContent.setText(item.getMyContent());
                    }
                });
                pvTime.show();
            }
        });

    }

    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(dateShowType);
        return format.format(date);
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
        EditText tvContent;
        @BindView(R.id.tv_content_click)
        TextView tv_content_click;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    private void setdatePicker(RunningParam item, EditText tvContent) {

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
