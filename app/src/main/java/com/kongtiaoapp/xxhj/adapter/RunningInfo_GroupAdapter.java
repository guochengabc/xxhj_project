package com.kongtiaoapp.xxhj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.RunDevice_StateBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xxhj_g on 2017/3/18.
 */
public class RunningInfo_GroupAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<RunDevice_StateBean.ResobjBean> list_group;
    int[] group_state_array = new int[]{R.mipmap.move_down,
            R.mipmap.move_up};
    private int[] group_checked = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    // 用来标识是否设置二級item背景色为绿色,初始值为-1既为选中状态
    private int child_groupId = -1;
    private int child_childId = -1;
    private int checkPosition = 0;
    private int groupPosition = 0;
    private int TYPE_1 = 1;//代表子view有2个控件
    private int TYPE_2 = 2;//代表子view有2个控件
    private int TYPE_3 = 3;//代表子view控件有3个
    private int TYPE_4 = 4;//代表子view有4个控件
    private int TYPE_5 = 5;//代表子view控件有5个
    private int record_group = 0;//记录groupposition的位置

    public RunningInfo_GroupAdapter(Context mActivity, List<RunDevice_StateBean.ResobjBean> list_reso) {
        context = mActivity;
        list_group = list_reso;

    }

    @Override
    public int getGroupCount() {
        return list_group.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list_group.get(i).getColData().size();
    }

    @Override
    public Object getGroup(int i) {
        return list_group.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return list_group.get(i).getColData().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup viewGroup) {
        Viewholder_Group viewholder_group = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_deviceinfo, null);
            viewholder_group = new Viewholder_Group(convertView);
            convertView.setTag(viewholder_group);
        } else {
            viewholder_group = (Viewholder_Group) convertView.getTag();
        }
        viewholder_group.group_title.setText(list_group.get(groupPosition).getTypeName());
        viewholder_group.txt_deviceCount.setText((list_group.get(groupPosition).getColData().size() - 1) + "台");
        // 设置整体描述上的文本信息
        if (this.groupPosition == groupPosition) {
            group_checked[this.groupPosition] = checkPosition;
        }
        if (group_checked[groupPosition] % 2 == 1) {
            // 设置默认的图片是选中状态
            viewholder_group.group_state.setBackgroundResource(group_state_array[0]);
            viewholder_group.myLayout.setBackgroundResource(R.drawable.text_item_top_bg);
        } else {
            for (int test : group_checked) {
                if (test == 0 || test % 2 == 0) {
                    viewholder_group.myLayout.setBackgroundResource(R.drawable.text_item_bg);
                    // 设置默认的图片是未选中状态
                    viewholder_group.group_state.setBackgroundResource(group_state_array[1]);
                }
            }
        }

        if (isExpanded) {
            viewholder_group.myLayout.setBackgroundResource(R.drawable.text_item_bg);
            // 设置默认的图片是未选中状态
            viewholder_group.group_state.setBackgroundResource(group_state_array[0]);
        } else {
            viewholder_group.myLayout.setBackgroundResource(R.drawable.text_item_bg);
            // 设置默认的图片是未选中状态
            viewholder_group.group_state.setBackgroundResource(group_state_array[1]);
        }
        // 返回一个布局对象
        return convertView;
    }

    public class Viewholder_Group {
        @BindView(R.id.group_layout)
        RelativeLayout myLayout;
        @BindView(R.id.txt_deviceName)
        TextView group_title;
        @BindView(R.id.txt_deviceCount)
        TextView txt_deviceCount;
        @BindView(R.id.group_state)
        ImageView group_state;

        Viewholder_Group(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        int type = list_group.get(groupPosition).getParamSize();
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
        } else {
            return TYPE_1;
        }

    }

    @Override
    public int getChildTypeCount() {
        return 5;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        int type = getChildType(groupPosition, childPosition);
        Viewholder_Child1 viewholder_child1 = null;
        Viewholder_Child2 viewholder_child2 = null;
        Viewholder_Child3 viewholder_child3 = null;
        Viewholder_Child4 viewholder_child4 = null;
        Viewholder_Child5 viewholder_child5 = null;

        if (convertView == null) {
            switch (type) {
                case 1:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_child_runninginfo2, null);
                    viewholder_child1 = new Viewholder_Child1(convertView);
                    convertView.setTag(viewholder_child1);
                    break;
                case 2:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_child_runninginfo2, null);
                    viewholder_child2 = new Viewholder_Child2(convertView);
                    convertView.setTag(viewholder_child2);
                    break;
                case 3:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_child_runninginfo2, null);
                    viewholder_child3 = new Viewholder_Child3(convertView);
                    convertView.setTag(viewholder_child3);
                    break;
                case 4:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_child_runninginfo2, null);
                    viewholder_child4 = new Viewholder_Child4(convertView);
                    convertView.setTag(viewholder_child4);
                    break;
                case 5:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_child_runninginfo2, null);
                    viewholder_child5 = new Viewholder_Child5(convertView);
                    convertView.setTag(viewholder_child5);
                    break;
            }
        } else {
            switch (type) {
                case 1:
                    viewholder_child1 = (Viewholder_Child1) convertView.getTag();
                    break;
                case 2:
                    viewholder_child2 = (Viewholder_Child2) convertView.getTag();
                    break;
                case 3:
                    viewholder_child3 = (Viewholder_Child3) convertView.getTag();
                    break;
                case 4:
                    viewholder_child4 = (Viewholder_Child4) convertView.getTag();
                    break;
                case 5:
                    viewholder_child5 = (Viewholder_Child5) convertView.getTag();
                    break;
            }
        }
        switch (type) {
            case 1:
                viewholder_child1.txt_running_1.setText(list_group.get(groupPosition).getColData().get(childPosition).getK0());
                break;
            case 2:
                RunDevice_StateBean.ResobjBean.ColDataBean bean2 = list_group.get(groupPosition).getColData().get(childPosition);
                List<Integer> alarmArr = list_group.get(groupPosition).getAlarmArr();
                if (list_group.get(groupPosition).getIsAlarm() == 1) {

                    if (alarmArr != null) {
                        for (int i = 0; i < alarmArr.size(); i++) {
                            if (alarmArr.get(i) != 0) {
                                if (childPosition == (i + 1)) {
                                    viewholder_child2.line_alarm.setBackgroundResource(R.color.lines_alarm);
                                }
                            }
                        }
                    }

                }
                if (childPosition == 0) {
                    viewholder_child2.line_alarm.setBackgroundResource(R.color.lines_unalarm);
                }
                viewholder_child2.txt_running_1.setText(bean2.getK0().toString());
                viewholder_child2.txt_running_5.setVisibility(View.VISIBLE);
                viewholder_child2.txt_running_5.setText(bean2.getK1().toString());
                if (bean2.getK1().equals("ON")) {
                    viewholder_child2.txt_running_5.setTextColor(context.getResources().getColor(R.color.red));
                } else {
                    viewholder_child2.txt_running_5.setTextColor(context.getResources().getColor(R.color.holo_blue_bright));
                    if (childPosition == 0) {
                        viewholder_child2.txt_running_5.setTextColor(context.getResources().getColor(R.color.device_state_color));
                    }
                }
                break;
            case 3:
                RunDevice_StateBean.ResobjBean.ColDataBean bean3 = list_group.get(groupPosition).getColData().get(childPosition);
                List<Integer> alarmArr3 = list_group.get(groupPosition).getAlarmArr();
                if (list_group.get(groupPosition).getIsAlarm() == 1) {
                    if (alarmArr3 != null) {
                        for (int i = 0; i < alarmArr3.size(); i++) {
                            if (alarmArr3.get(i) != 0) {
                                if (childPosition == (i + 1)) {
                                    viewholder_child3.line_alarm.setBackgroundResource(R.color.lines_alarm);
                                }
                            }
                        }

                    }
                }
                if (childPosition == 0) {
                    viewholder_child3.line_alarm.setBackgroundResource(R.color.lines_unalarm);
                }
                viewholder_child3.txt_running_1.setText(bean3.getK0().toString());
                viewholder_child3.txt_running_2.setVisibility(View.VISIBLE);
                viewholder_child3.txt_running_2.setText(bean3.getK1().toString());
                viewholder_child3.txt_running_5.setVisibility(View.VISIBLE);
                viewholder_child3.txt_running_5.setText(bean3.getK2().toString());
                if (bean3.getK1().equals("ON")) {
                    viewholder_child3.txt_running_2.setTextColor(context.getResources().getColor(R.color.red));
                } else {
                    viewholder_child3.txt_running_2.setTextColor(context.getResources().getColor(R.color.theme_color));
                    if (childPosition == 0) {
                        viewholder_child3.txt_running_2.setTextColor(context.getResources().getColor(R.color.device_state_color));
                    }
                }
                break;
            case 4:
                RunDevice_StateBean.ResobjBean.ColDataBean bean4 = list_group.get(groupPosition).getColData().get(childPosition);
                List<Integer> alarmArr4 = list_group.get(groupPosition).getAlarmArr();
                if (list_group.get(groupPosition).getIsAlarm() == 1) {
                    if (alarmArr4 != null) {
                        for (int i = 0; i < alarmArr4.size(); i++) {
                            if (alarmArr4.get(i) != 0) {
                                if (childPosition == (i + 1) && childPosition == 0) {
                                    viewholder_child4.line_alarm.setBackgroundResource(R.color.lines_alarm);
                                }
                            } else {
                                viewholder_child4.line_alarm.setBackgroundResource(R.color.lines_unalarm);
                            }
                        }
                    }
                }
                if (childPosition == 0) {
                    viewholder_child4.line_alarm.setBackgroundResource(R.color.lines_unalarm);
                }
                viewholder_child4.txt_running_1.setText(bean4.getK0().toString());
                viewholder_child4.txt_running_2.setVisibility(View.VISIBLE);
                viewholder_child4.txt_running_2.setText(bean4.getK1().toString());
                viewholder_child4.txt_running_3.setVisibility(View.VISIBLE);
                viewholder_child4.txt_running_3.setText(bean4.getK2().toString());
                viewholder_child4.txt_running_5.setVisibility(View.VISIBLE);
                viewholder_child4.txt_running_5.setText(bean4.getK3().toString());
                if (bean4.getK1().equals("ON")) {
                    viewholder_child4.txt_running_2.setTextColor(context.getResources().getColor(R.color.red));
                } else {
                    viewholder_child4.txt_running_2.setTextColor(context.getResources().getColor(R.color.theme_color));
                    if (childPosition == 0) {
                        viewholder_child4.txt_running_2.setTextColor(context.getResources().getColor(R.color.device_state_color));
                    }
                }
                break;
            case 5:
                RunDevice_StateBean.ResobjBean.ColDataBean bean5 = list_group.get(groupPosition).getColData().get(childPosition);
                List<Integer> alarmArr5 = list_group.get(groupPosition).getAlarmArr();
                if (list_group.get(groupPosition).getIsAlarm() == 1) {
                    if (alarmArr5 != null) {
                        for (int i = 0; i < alarmArr5.size(); i++) {
                            if (alarmArr5.get(i) != 0) {
                                if (childPosition == (i + 1)) {
                                    viewholder_child5.line_alarm.setBackgroundResource(R.color.lines_alarm);
                                } else {
                                    viewholder_child5.line_alarm.setBackgroundResource(R.color.whitesmoke);
                                }
                            } else {
                                viewholder_child5.line_alarm.setBackgroundResource(R.color.lines_unalarm);
                            }
                        }
                    }
                }
                if (childPosition == 0) {
                    viewholder_child5.line_alarm.setBackgroundResource(R.color.lines_unalarm);
                }
                viewholder_child5.txt_running_1.setText(bean5.getK0().toString());
                viewholder_child5.txt_running_2.setVisibility(View.VISIBLE);
                viewholder_child5.txt_running_2.setText(bean5.getK1().toString());
                viewholder_child5.txt_running_3.setVisibility(View.VISIBLE);
                viewholder_child5.txt_running_3.setText(bean5.getK2().toString());
                viewholder_child5.txt_running_4.setVisibility(View.VISIBLE);
                viewholder_child5.txt_running_4.setText(bean5.getK3().toString());
                viewholder_child5.txt_running_5.setVisibility(View.VISIBLE);
                viewholder_child5.txt_running_5.setText(bean5.getK4().toString());
                if (bean5.getK1().equals("ON")) {
                    viewholder_child5.txt_running_2.setTextColor(context.getResources().getColor(R.color.red));
                } else {
                    viewholder_child5.txt_running_2.setTextColor(context.getResources().getColor(R.color.theme_color));
                    if (childPosition == 0) {
                        viewholder_child5.txt_running_2.setTextColor(context.getResources().getColor(R.color.device_state_color));
                    }
                }
                break;
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public void setCheck(int i, int groupPosition) {
        checkPosition = i;
        this.groupPosition = groupPosition;
    }

    public void setList(List<RunDevice_StateBean.ResobjBean> list_reso) {
        list_group = list_reso;
        notifyDataSetChanged();
    }

    public class Viewholder_Child1 {
        @BindView(R.id.line_alarm)
        LinearLayout line_alarm;//报警背景色
        @BindView(R.id.txt_running_1)
        TextView txt_running_1;

        Viewholder_Child1(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public class Viewholder_Child2 {
        @BindView(R.id.line_alarm)
        LinearLayout line_alarm;//报警背景色
        @BindView(R.id.txt_running_1)
        TextView txt_running_1;
        @BindView(R.id.txt_running_5)
        TextView txt_running_5;

        Viewholder_Child2(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public class Viewholder_Child3 {
        @BindView(R.id.line_alarm)
        LinearLayout line_alarm;//报警背景色
        @BindView(R.id.txt_running_1)
        TextView txt_running_1;
        @BindView(R.id.txt_running_2)
        TextView txt_running_2;
        @BindView(R.id.txt_running_5)
        TextView txt_running_5;

        Viewholder_Child3(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public class Viewholder_Child4 {
        @BindView(R.id.line_alarm)
        LinearLayout line_alarm;//报警背景色
        @BindView(R.id.txt_running_1)
        TextView txt_running_1;
        @BindView(R.id.txt_running_2)
        TextView txt_running_2;
        @BindView(R.id.txt_running_3)
        TextView txt_running_3;
        @BindView(R.id.txt_running_5)
        TextView txt_running_5;


        Viewholder_Child4(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public class Viewholder_Child5 {
        @BindView(R.id.line_alarm)
        LinearLayout line_alarm;//报警背景色
        @BindView(R.id.txt_running_1)
        TextView txt_running_1;
        @BindView(R.id.txt_running_2)
        TextView txt_running_2;
        @BindView(R.id.txt_running_3)
        TextView txt_running_3;
        @BindView(R.id.txt_running_4)
        TextView txt_running_4;
        @BindView(R.id.txt_running_5)
        TextView txt_running_5;

        Viewholder_Child5(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
