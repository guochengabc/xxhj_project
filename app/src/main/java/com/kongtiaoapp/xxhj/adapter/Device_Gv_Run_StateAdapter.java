package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.RunDevice_StateBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2017/1/6.
 */
public class Device_Gv_Run_StateAdapter extends BaseAdapter {
    private List<RunDevice_StateBean.ResobjBean.ColDataBean> list;
    private Context context;
    private int TYPE_1 = 1, TYPE_2 = 2, TYPE_3 = 3, TYPE_4 = 4, type_size = 1, TYPE_5 = 5;

    public Device_Gv_Run_StateAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        int type = type_size;
        if (type == 1) {
            return TYPE_1;
        } else if (type == 2) {
            return TYPE_2;
        } else if (type == 3) {
            return TYPE_3;
        } else if (type == 4) {
            return TYPE_4;
        } else if (type == 5) {
            return TYPE_5;
        }
        return TYPE_1;
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2 = null;
        ViewHolder3 holder3 = null;
        ViewHolder4 holder4 = null;
        ViewHolder5 holder5 = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case 1:
                    convertView = LayoutInflater.from(context).inflate(
                            R.layout.item_device_control1, parent, false);
                    holder1 = new ViewHolder1(convertView);
                    convertView.setTag(holder1);
                    break;
                case 2:
                    convertView = LayoutInflater.from(context).inflate(
                            R.layout.item_device_control2, parent, false);
                    holder2 = new ViewHolder2(convertView);
                    convertView.setTag(holder2);
                    break;
                case 3:
                    convertView = LayoutInflater.from(context).inflate(
                            R.layout.item_device_control3, parent, false);
                    holder3 = new ViewHolder3(convertView);
                    convertView.setTag(holder3);
                    break;
                case 4:
                    convertView = LayoutInflater.from(context).inflate(
                            R.layout.item_device_control4, parent, false);
                    holder4 = new ViewHolder4(convertView);
                    convertView.setTag(holder4);
                    break;
                case 5:
                    convertView = LayoutInflater.from(context).inflate(
                            R.layout.item_device_control5, parent, false);
                    holder5 = new ViewHolder5(convertView);
                    convertView.setTag(holder5);
                    break;
            }

        } else {
            switch (type) {
                case 1:
                    holder1 = (ViewHolder1) convertView.getTag();
                    break;
                case 2:
                    holder2 = (ViewHolder2) convertView.getTag();
                    break;
                case 3:
                    holder3 = (ViewHolder3) convertView.getTag();
                    break;
                case 4:
                    holder4 = (ViewHolder4) convertView.getTag();
                    break;
                case 5:
                    holder5 = (ViewHolder5) convertView.getTag();
                    break;
            }

        }
        switch (type) {
            case 1:
                holder1.txt_device_item1.setText(list.get(position).getK0());
                break;
            case 2:
                String k12 = list.get(position).getK1();
                holder2.txt_device_item1.setText(list.get(position).getK0());
                holder2.txt_device_item2.setText(k12);

                if (k12.equals("ON")) {
                    holder2.txt_device_item2.setTextColor(context.getResources().getColor(R.color.red));
                } else {
                    holder2.txt_device_item2.setTextColor(context.getResources().getColor(R.color.theme_color));
                    if (position == 0) {
                        holder2.txt_device_item2.setTextColor(context.getResources().getColor(R.color.device_state_color));
                    }
                }


                break;
            case 3:
                String k13 = list.get(position).getK1();
                holder3.txt_device_item1.setText(list.get(position).getK0());
                holder3.txt_device_item2.setText(k13);
                holder3.txt_device_item3.setText(list.get(position).getK2());

                if (k13.equals("ON")) {
                    holder3.txt_device_item2.setTextColor(context.getResources().getColor(R.color.red));
                } else {
                    holder3.txt_device_item2.setTextColor(context.getResources().getColor(R.color.theme_color));
                    if (position == 0) {
                        holder3.txt_device_item2.setTextColor(context.getResources().getColor(R.color.device_state_color));
                    }
                }
                break;
            case 4:
                String k14 = list.get(position).getK1();
                holder4.txt_device_item1.setText(list.get(position).getK0());
                holder4.txt_device_item2.setText(k14);
                holder4.txt_device_item3.setText(list.get(position).getK2());
                holder4.txt_device_item4.setText(list.get(position).getK3());

                if (k14.equals("ON")) {
                    holder4.txt_device_item2.setTextColor(context.getResources().getColor(R.color.red));
                } else {
                    holder4.txt_device_item2.setTextColor(context.getResources().getColor(R.color.theme_color));
                    if (position == 0) {
                        holder4.txt_device_item2.setTextColor(context.getResources().getColor(R.color.device_state_color));
                    }
                }

                break;
            case 5:
                String k15 = list.get(position).getK1();
                holder5.txt_device_item1.setText(list.get(position).getK0());
                holder5.txt_device_item2.setText(k15);
                holder5.txt_device_item3.setText(list.get(position).getK2());
                holder5.txt_device_item4.setText(list.get(position).getK3());
                holder5.txt_device_item5.setText(list.get(position).getK4());

                if (k15.equals("ON")) {
                    holder5.txt_device_item2.setTextColor(context.getResources().getColor(R.color.red));
                } else {
                    holder5.txt_device_item2.setTextColor(context.getResources().getColor(R.color.theme_color));
                    if (position == 0) {
                        holder5.txt_device_item2.setTextColor(context.getResources().getColor(R.color.device_state_color));
                    }
                }

                break;
        }
        return convertView;
    }

    public void setList(List<RunDevice_StateBean.ResobjBean.ColDataBean> mList, int paramSize) {
        list = mList;
        type_size = paramSize;
        notifyDataSetChanged();
    }

    public class ViewHolder1 {
        @BindView(R.id.txt_device_item1)
        TextView txt_device_item1;

        ViewHolder1(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public class ViewHolder2 {
        @BindView(R.id.txt_device_item1)
        TextView txt_device_item1;
        @BindView(R.id.txt_device_item2)
        TextView txt_device_item2;

        ViewHolder2(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public class ViewHolder3 {
        @BindView(R.id.txt_device_item1)
        TextView txt_device_item1;
        @BindView(R.id.txt_device_item2)
        TextView txt_device_item2;
        @BindView(R.id.txt_device_item3)
        TextView txt_device_item3;

        ViewHolder3(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public class ViewHolder4 {
        @BindView(R.id.txt_device_item1)
        TextView txt_device_item1;
        @BindView(R.id.txt_device_item2)
        TextView txt_device_item2;
        @BindView(R.id.txt_device_item3)
        TextView txt_device_item3;
        @BindView(R.id.txt_device_item4)
        TextView txt_device_item4;

        ViewHolder4(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public class ViewHolder5 {
        @BindView(R.id.txt_device_item1)
        TextView txt_device_item1;
        @BindView(R.id.txt_device_item2)
        TextView txt_device_item2;
        @BindView(R.id.txt_device_item3)
        TextView txt_device_item3;
        @BindView(R.id.txt_device_item4)
        TextView txt_device_item4;
        @BindView(R.id.txt_device_item5)
        TextView txt_device_item5;

        ViewHolder5(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
