package com.kongtiaoapp.xxhj.adapter;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.ParamSettingBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.net.okhttp.Encode_params;
import com.kongtiaoapp.xxhj.net.okhttp.Get_NO_Task;
import com.kongtiaoapp.xxhj.net.okhttp.ParamJson;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.ui.timeselector.activity.TimePickerView;
import com.kongtiaoapp.xxhj.ui.view.MyLinearlayout;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by G_XXHJ on 2018/6/8.
 */

public class ParamSettingAdapter extends BaseAdapter {
    private Activity context;
    private List<ParamSettingBean.ResobjBean.ColDataBean> list;
    protected boolean NoModify = true;//不允许修改

    public ParamSettingAdapter(Activity context, List<ParamSettingBean.ResobjBean.ColDataBean> list) {
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
            convertView = LayoutInflater.from(App.application).inflate(
                    R.layout.item_paramsetting, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.myLine.getFocus(NoModify);
        if (NoModify) {
            holder.txt_k1.setBackgroundResource(R.color.ffffff);
            holder.txt_k2.setBackgroundResource(R.color.ffffff);
            holder.txt_time.setBackgroundResource(R.color.ffffff);
            holder.txt_time2.setBackgroundResource(R.color.ffffff);
        } else {
            if (position != 0) {
                holder.txt_k1.setBackgroundResource(R.drawable.shape_paramsetting);
                holder.txt_k2.setBackgroundResource(R.drawable.shape_paramsetting);
                holder.txt_time.setBackgroundResource(R.drawable.shape_paramsetting);
                holder.txt_time2.setBackgroundResource(R.drawable.shape_paramsetting);
            }

        }
        //shape_add_weibao_record_et_bg
        ParamSettingBean.ResobjBean.ColDataBean bean = list.get(position);
        holder.txt_k0.setText(bean.getName());

        if (bean.getFieidType().equals("time")) {
            holder.txt_time.setText(bean.getSetValue());
            holder.txt_time2.setText(bean.getAlarmValue());
            holder.txt_time.setVisibility(View.VISIBLE);
            holder.txt_time2.setVisibility(View.VISIBLE);
            holder.txt_k1.setVisibility(View.GONE);
            holder.txt_k2.setVisibility(View.GONE);
            ViewHolder finalHolder = holder;
            holder.txt_time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TimePickerView pvTime = new TimePickerView(context, TimePickerView.Type.HOURS_MINS);
                    pvTime.setTime(new Date());
                    pvTime.setCyclic(false);
                    pvTime.setCancelable(true);
                    pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date) {
                            finalHolder.txt_time.setText(getTime(date));
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("paraId", list.get(position).getParaId());
                            map.put("setValue", getTime(date));
                            Map<String, String> params = new HashMap<String, String>();
                            params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.UPDATEPARAMSETINFO, map));
                            new Get_NO_Task<RBResponse>(context, RBResponse.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), new ResponseXXHJListener<RBResponse>() {
                                @Override
                                public void requuestError(int code) {

                                }

                                @Override
                                public void requestSuccess(RBResponse rbResponse) {
                                    if (rbResponse.getCode() == 40000) {
                                        ParamSettingBean.ResobjBean.ColDataBean colDataBean = list.get(position);
                                        colDataBean.setSetValue(getTime(date));
                                        list.set(position, colDataBean);
                                    } else if (rbResponse.getCode() == 40002) {
                                        ToastUtils.showToast(context, rbResponse.getErrormsg());
                                    }
                                }
                            }).execute();
                        }
                    });
                    pvTime.show();
                }
            });
            holder.txt_time2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TimePickerView pvTime = new TimePickerView(context, TimePickerView.Type.HOURS_MINS);
                    pvTime.setTime(new Date());
                    pvTime.setCyclic(false);
                    pvTime.setCancelable(true);
                    pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date) {
                            finalHolder.txt_time2.setText(getTime(date));
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("paraId", list.get(position).getParaId());
                            map.put("alarmValue", getTime(date));
                            Map<String, String> params = new HashMap<String, String>();
                            params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.UPDATEPARAMSETINFO, map));
                            new Get_NO_Task<RBResponse>(context, RBResponse.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), new ResponseXXHJListener<RBResponse>() {
                                @Override
                                public void requuestError(int code) {

                                }

                                @Override
                                public void requestSuccess(RBResponse rbResponse) {
                                    if (rbResponse.getCode() == 40000) {
                                        ParamSettingBean.ResobjBean.ColDataBean colDataBean = list.get(position);
                                        colDataBean.setAlarmValue(getTime(date));
                                        list.set(position, colDataBean);
                                    } else if (rbResponse.getCode() == 40002) {
                                        ToastUtils.showToast(context, rbResponse.getErrormsg());
                                    }
                                }
                            }).execute();

                        }
                    });
                    pvTime.show();
                }
            });
        } else if (bean.getFieidType().equals("string-1")) {
            holder.txt_k1.setText(bean.getSetValue());
            holder.txt_time2.setText(bean.getAlarmValue());
            holder.txt_k1.setVisibility(View.VISIBLE);
            holder.txt_time2.setVisibility(View.VISIBLE);
            holder.txt_time.setVisibility(View.GONE);
            holder.txt_k2.setVisibility(View.GONE);
        } else if (bean.getFieidType().equals("string-2")) {
            holder.txt_time.setText(bean.getSetValue());
            holder.txt_k2.setText(bean.getAlarmValue());
            holder.txt_time.setVisibility(View.VISIBLE);
            holder.txt_k2.setVisibility(View.VISIBLE);
            holder.txt_k1.setVisibility(View.GONE);
            holder.txt_time2.setVisibility(View.GONE);
        } else {
            if (position == 0) {
                holder.txt_k1.setEnabled(false);
                holder.txt_k2.setEnabled(false);
            } else {
                holder.txt_k1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (start != 0) {
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("paraId", list.get(position).getParaId());
                            map.put("setValue", s.toString().trim());
                            Map<String, String> params = new HashMap<String, String>();
                            params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.UPDATEPARAMSETINFO, map));
                            new Get_NO_Task<RBResponse>(context, RBResponse.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), new ResponseXXHJListener<RBResponse>() {
                                @Override
                                public void requuestError(int code) {

                                }

                                @Override
                                public void requestSuccess(RBResponse rbResponse) {
                                    if (rbResponse.getCode() == 40000) {
                                        ParamSettingBean.ResobjBean.ColDataBean colDataBean = list.get(position);
                                        colDataBean.setSetValue(s.toString().trim());
                                        list.set(position, colDataBean);
                                    } else if (rbResponse.getCode() == 40002) {
                                        ToastUtils.showToast(context, rbResponse.getErrormsg());
                                    }
                                }
                            }).execute();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.txt_k2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (start != 0) {
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("paraId", list.get(position).getParaId());
                            map.put("alarmValue", s.toString().trim());
                            Map<String, String> params = new HashMap<String, String>();
                            params.put(HttpMethod.KEY, ParamJson.map2Json(HttpMethod.UPDATEPARAMSETINFO, map));
                            new Get_NO_Task<RBResponse>(context, RBResponse.class, ConstantValue.HTTP_URLS + Encode_params.YesToken_Encodeparms(params), new ResponseXXHJListener<RBResponse>() {
                                @Override
                                public void requuestError(int code) {

                                }

                                @Override
                                public void requestSuccess(RBResponse rbResponse) {
                                    if (rbResponse.getCode() == 40000) {
                                        ParamSettingBean.ResobjBean.ColDataBean colDataBean = list.get(position);
                                        colDataBean.setAlarmValue(s.toString().trim());
                                        list.set(position, colDataBean);
                                    } else if (rbResponse.getCode() == 40002) {
                                        ToastUtils.showToast(context, rbResponse.getErrormsg());
                                    }
                                }
                            }).execute();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            holder.txt_time.setVisibility(View.GONE);
            holder.txt_time2.setVisibility(View.GONE);
            holder.txt_k1.setVisibility(View.VISIBLE);
            holder.txt_k2.setVisibility(View.VISIBLE);
            holder.txt_k1.setText(bean.getSetValue());
            holder.txt_k2.setText(bean.getAlarmValue());
        }
        return convertView;
    }

    private static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    public void getNoModify(boolean NoModify) {
        this.NoModify = NoModify;
        notifyDataSetChanged();
    }

    public void setList(List<ParamSettingBean.ResobjBean.ColDataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    class ViewHolder {
        @BindView(R.id.myLine)
        MyLinearlayout myLine;
        @BindView(R.id.txt_k0)
        TextView txt_k0;
        @BindView(R.id.txt_k1)
        EditText txt_k1;
        @BindView(R.id.txt_k2)
        EditText txt_k2;
        @BindView(R.id.txt_time)
        TextView txt_time;
        @BindView(R.id.txt_time2)
        TextView txt_time2;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
