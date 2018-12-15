package com.kongtiaoapp.xxhj.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.DeviceParam;
import com.kongtiaoapp.xxhj.ui.timeselector.activity.TimePickerView;
import com.kongtiaoapp.xxhj.ui.view.MyPopupWindow;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Luoye on 2016-8-8.
 * 说明:设备信息详情
 */
public class DeviceInfoGetParamAdapter extends BaseAdapter {
    private List<DeviceParam.DeviceParamList> mList;
    private Context mContext;
    private Activity activity;
    private MyPopupWindow popupWindow;


    public DeviceInfoGetParamAdapter(List<DeviceParam.DeviceParamList> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
        this.activity = (Activity) mContext;

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

    private int index = -1;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //        ViewHolder holder = null;
        //        if (convertView == null) {
        //            convertView =  inflater.inflate(R.layout.whatever, parent, false);
        convertView = LayoutInflater.from(App.application).inflate(R.layout.item_device_get_info_param, parent, false);
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        final EditText tvContent = (EditText) convertView.findViewById(R.id.tv_content);
        TextView tvRequired = (TextView) convertView.findViewById(R.id.tv_required);
        //            holder = new ViewHolder(convertView);
        //            convertView.setTag(holder);
        //        } else {
        //            holder = (ViewHolder) convertView.getTag();
        //        }
        final DeviceParam.DeviceParamList item = (DeviceParam.DeviceParamList) getItem(position);
        if (!TextUtils.isEmpty(item.getUnit())) {
            tvName.setText(item.getValue() + "(" + item.getUnit() + "):");
        } else {
            tvName.setText(item.getValue());
        }
        tvContent.setText(item.getData());
        tvContent.setHint(item.getUnit());

        if (item.getIsRequire() != null && item.getIsRequire().equals("1")) {
            tvRequired.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(item.getData()) && !TextUtils.isEmpty(item.getFieldType()) && ConstantValue.ENUM.equals(item.getFieldType())) {
            List<DeviceParam.EnumValue> enumValue = item.getEnumValue();
            for (DeviceParam.EnumValue v : enumValue) {
                if (item.getData().equals(v.getCode())) {
                    tvContent.setText(v.getValue());
                    item.setData(v.getCode());
                }
            }
        }

        if (item.getFieldType().equals("int")) {
            tvContent.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else if (item.getFieldType().equals("float")) {
            tvContent.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        } else {
            tvContent.setInputType(InputType.TYPE_CLASS_TEXT);

        }

        //时间选择后回调
        //holder.tvContent.setFocusable(true);
        if (!TextUtils.isEmpty(item.getFieldType()) && ConstantValue.DATETIME.equals(item.getFieldType())) {
            tvContent.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    // 时间选择器
                    TimePickerView pvTime = new TimePickerView(mContext, TimePickerView.Type.ALL);
                    // 控制时间范围
                    // Calendar calendar = Calendar.getInstance();
                    // pvTime.setRange(calendar.get(Calendar.YEAR) - 20,
                    // calendar.get(Calendar.YEAR));
                    pvTime.setTime(new Date());
                    pvTime.setCyclic(false);
                    pvTime.setCancelable(true);
                    pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date) {
                            tvContent.setText(getTime(date));
                            item.setData(getTime(date));
                        }
                    });
                    pvTime.show();
                    return true;
                }
            });
        } else if (!TextUtils.isEmpty(item.getFieldType()) && ConstantValue.ENUM.equals(item.getFieldType())) {
            tvContent.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    List<DeviceParam.EnumValue> enumValue = item.getEnumValue();
                    showSortPopup(tvContent, item, enumValue);
                    return true;
                }
            });

        } else {
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
        }


        if ("ProjectId".equals(item.getCode())) {
            tvContent.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }

        tvContent.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable) {

            }

            public void beforeTextChanged(CharSequence text, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence text, int start, int before, int count) {
                // 在这个地方添加你的保存文本内容的代码，如果不保存，你就等着重新输入吧
                // 而且不管你输入多少次，也不会有用的，因为getView全清了～～
                // finalHolder.tvContent.setText(text);
                item.setData(text.toString());
            }

        });
        tvContent.clearFocus();

        if (index != -1 && index == position) {
            // 如果当前的行下标和点击事件中保存的index一致，手动为EditText设置焦点。
            tvContent.requestFocus();
        }

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_content)
        EditText tvContent;
        @BindView(R.id.tv_required)
        TextView tvRequired;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    /**
     * 显示智能排序popup
     */
    private void showSortPopup(final EditText editText, final DeviceParam.DeviceParamList item, final List<DeviceParam.EnumValue> list) {
        if (popupWindow == null) {
            popupWindow = new MyPopupWindow(mContext, activity.getWindow());
        }

        //设置宽高
        popupWindow.setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置数据源
        popupWindow.setList(list);
        //设置监听
        popupWindow.setListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                editText.setText(list.get(position).getValue());
                item.setData(list.get(position).getCode());
                popupWindow.dismiss();
            }
        });
        //初始化页面，必须写在后面
        popupWindow.initView();
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

    }
}
