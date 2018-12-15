package com.kongtiaoapp.xxhj.energymetering.adapter;

import android.app.Activity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.EnergyRecordBean;
import com.kongtiaoapp.xxhj.interfaces.RecordStatus;
import com.kongtiaoapp.xxhj.ui.timeselector.activity.TimePickerView;
import com.kongtiaoapp.xxhj.utils.NetworkUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by G_XXHJ on 2018/7/18.
 */

public class EnergyRecordAdapter extends BaseAdapter {
    private Activity context;
    private List<EnergyRecordBean.ResobjBean.ElectricityBean> list;
    private String sensorId;
    private int swichStatus = 0;
    private int isStart = -1;
    private boolean isWrite = false;
    private String recordValue = "";
    private String recordStatus = "0";//0正常  1异常
    RecordStatus rStatus;
    private String dateShowType = "yyyy-MM-dd HH:mm:ss";

    public EnergyRecordAdapter(Activity context, List<EnergyRecordBean.ResobjBean.ElectricityBean> list, String sensorId) {
        this.context = context;
        this.list = list;
        this.sensorId = sensorId;
        rStatus = (RecordStatus) context;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_energy_record, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        EnergyRecordBean.ResobjBean.ElectricityBean bean = list.get(position);
        String inputType = bean.getInputType();//0可以输入    1不可以输入
        if (inputType.equals("0")) {
            if (!NetworkUtils.checkNetwork(context)) {
                holder.edt_CurrentContent.setText(bean.getName());
            }
            holder.edt_CurrentContent.setText(bean.getName());
            if (bean.getCode().equals("Status")) {
                holder.line0.setVisibility(View.GONE);
                holder.line1.setVisibility(View.GONE);
                holder.line2.setVisibility(View.VISIBLE);
                holder.txt_runStatus.setText(bean.getValue());
                setSwipth(holder);//设置运行状态开关
            } else {
              /*  holder.edt_CurrentContent.setFocusable(true);
                holder.edt_CurrentContent.setFocusableInTouchMode(true);*/
 /*
                context.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);*/
                if (bean.getFieldType().equals("float")) {
                    holder.edt_CurrentContent.setInputType(InputType.TYPE_CLASS_PHONE);//切换数字键盘
                } else if (bean.getFieldType().equals("string")) {
                    holder.edt_CurrentContent.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_CLASS_TEXT);//切换文本
                } else {
                    holder.edt_CurrentContent.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_CLASS_TEXT);//切换文本
                }
                if (isWrite == false) {
                    holder.line0.setVisibility(View.VISIBLE);
                    holder.line1.setVisibility(View.GONE);
                    holder.line2.setVisibility(View.GONE);
                }
                holder.txt_currentValue.setText(bean.getValue());
                getOtherValue(holder, position);//获取其他值
            }
        } else {
            holder.line0.setVisibility(View.GONE);
            holder.line1.setVisibility(View.VISIBLE);
            holder.line2.setVisibility(View.GONE);
            holder.txt_lastValue.setText(bean.getValue());
            holder.txt_lastContent.setText(bean.getName());
            switch (bean.getFieldType()) {
                case ConstantValue.DATEMONTH:
                    dateShowType = "yyyy-MM";
                    setRecordTime(position, holder, TimePickerView.Type.YEAR_MONTH);
                    break;
                case ConstantValue.DATEDAY:
                    dateShowType = "yyyy-MM-dd";
                    setRecordTime(position, holder, TimePickerView.Type.YEAR_MONTH_DAY);
                    break;
                case ConstantValue.DATEHOUR:
                    dateShowType = "yyyy-MM-dd HH";
                    setRecordTime(position, holder, TimePickerView.Type.YEAR_MONTH_DAY_HOURS);
                    break;
                case ConstantValue.DATEMINUTE:
                    dateShowType = "yyyy-MM-dd HH:mm";
                    setRecordTime(position, holder, TimePickerView.Type.YEAR_MONTH_DAY_HOURS_MIN);
                    break;
                case ConstantValue.DATESECOND:
                    dateShowType = "yyyy-MM-dd HH:mm:ss";
                    setRecordTime(position, holder, TimePickerView.Type.ALL);
                    break;
                default:

                    break;
            }

        }

        return convertView;
    }

    private void setRecordTime(int position, ViewHolder holder, TimePickerView.Type type) {
        holder.txt_lastContent.setText(ConstantValue.PLEASE_TIME);
        holder.txt_lastContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerView pvTime = new TimePickerView(context, type);
                pvTime.setTime(new Date());
                pvTime.setCyclic(false);
                pvTime.setCancelable(true);
                pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date) {
                        holder.txt_lastContent.setText(getTime(date));
                        EnergyRecordBean.ResobjBean.ElectricityBean bean = list.get(position);
                        bean.setName(getTime(date));
                        list.set(position, bean);
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

    private void getOtherValue(ViewHolder holder, int position) {

        holder.edt_CurrentContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isWrite = true;
                EnergyRecordBean.ResobjBean.ElectricityBean bean = list.get(position);
                bean.setName(s.toString());
                list.set(position, bean);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    private void setSwipth(ViewHolder holder) {
        //对2进行求余   正常   1异常
        holder.line_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++swichStatus;
                if (swichStatus % 2 == 0) {//正常
                    holder.line_switch.setBackgroundResource(R.drawable.shape_circle_open);
                    holder.img_switch_on.setVisibility(View.VISIBLE);
                    holder.img_switch_off.setVisibility(View.GONE);
                    holder.txt_switch.setText("正常");
                    recordStatus = "0";
                    rStatus.getRecordStatus(recordStatus);
                } else if (swichStatus % 2 == 1) {//异常
                    holder.line_switch.setBackgroundResource(R.drawable.shape_circle_off);
                    holder.img_switch_on.setVisibility(View.GONE);
                    holder.img_switch_off.setVisibility(View.VISIBLE);
                    holder.txt_switch.setText("异常");
                    recordStatus = "1";
                    rStatus.getRecordStatus(recordStatus);
                }
            }
        });
    }

    public String getRecordStatus() {

        return recordStatus;
    }

    public List<EnergyRecordBean.ResobjBean.ElectricityBean> getList() {
        if (list == null) {
            return list = new ArrayList<>();
        }
        notifyDataSetChanged();
        return this.list;
    }

    public void setList(List<EnergyRecordBean.ResobjBean.ElectricityBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder {
        @BindView(R.id.line0)
        LinearLayout line0;
        @BindView(R.id.line1)
        LinearLayout line1;
        @BindView(R.id.line2)
        LinearLayout line2;
        @BindView(R.id.txt_currentValue)
        TextView txt_currentValue;
        @BindView(R.id.edt_CurrentContent)//当前值  暂定
                EditText edt_CurrentContent;
        @BindView(R.id.txt_lastValue)
        TextView txt_lastValue;
        @BindView(R.id.txt_lastContent)//上月值
                TextView txt_lastContent;
        //开关
        @BindView(R.id.line_switch)
        LinearLayout line_switch;
        @BindView(R.id.txt_runStatus)
        TextView txt_runStatus;
        @BindView(R.id.img_switch_off)
        ImageView img_switch_off;
        @BindView(R.id.txt_switch)
        TextView txt_switch;
        @BindView(R.id.img_switch_on)
        ImageView img_switch_on;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
