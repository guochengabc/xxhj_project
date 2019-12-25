package com.kongtiaoapp.xxhj.hvac;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.More_CommentsActivity;
import com.kongtiaoapp.xxhj.adapter.EfficParaInfoAdapter;
import com.kongtiaoapp.xxhj.adapter.EnergyParaInfoAdapter;
import com.kongtiaoapp.xxhj.adapter.Message_AreaAdapter;
import com.kongtiaoapp.xxhj.adapter.MyFragmentAdapter;
import com.kongtiaoapp.xxhj.adapter.SysParamInfoAdapter;
import com.kongtiaoapp.xxhj.alarms.AlarmFragment;
import com.kongtiaoapp.xxhj.bean.CommentsGroupBean;
import com.kongtiaoapp.xxhj.bean.EventBusDownBean;
import com.kongtiaoapp.xxhj.bean.EventbusBean;
import com.kongtiaoapp.xxhj.bean.HVAC_NewProjectDetailBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.duty.fragment.Duty_ProjectFragment;
import com.kongtiaoapp.xxhj.fragment.Export_RecorderFragment;
import com.kongtiaoapp.xxhj.fragment.Protect_PaintFragment;
import com.kongtiaoapp.xxhj.fragment.RunningGuideFragment;
import com.kongtiaoapp.xxhj.fragment.Running_ProjectFragment;
import com.kongtiaoapp.xxhj.hvac.fragment.ParamSettingFragment;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.Group_Project_DetailPresenter;
import com.kongtiaoapp.xxhj.mvp.view.Group_Project_DetailView;
import com.kongtiaoapp.xxhj.ui.view.ClickTextView;
import com.kongtiaoapp.xxhj.ui.view.CustomViewPager;
import com.kongtiaoapp.xxhj.ui.view.DoubleButtonDialog;
import com.kongtiaoapp.xxhj.ui.view.HorizontalGridView;
import com.kongtiaoapp.xxhj.ui.view.MyExpandableListView;
import com.kongtiaoapp.xxhj.ui.view.ToastDialog;
import com.kongtiaoapp.xxhj.utils.BaseTools;
import com.kongtiaoapp.xxhj.utils.DateUtils;
import com.kongtiaoapp.xxhj.utils.MyTablayout;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HVAC_NewActivity extends BaseActivity<Group_Project_DetailPresenter, Group_Project_DetailView>
        implements Group_Project_DetailView, RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.img_weather)
    ImageView img_weather;
    @BindView(R.id.glv_top)
    HorizontalGridView glv_top;
    @BindView(R.id.line_topOne)
    LinearLayout line_topOne;
    @BindView(R.id.line_two_gone)
    LinearLayout line_two_gone;
    @BindView(R.id.line_topTwo)
    LinearLayout line_topTwo;
    @BindView(R.id.glv_topTwo)
    HorizontalGridView glv_topTwo;
    @BindView(R.id.glv_topThree)
    HorizontalGridView glv_topThree;

    @BindView(R.id.img_left)
    ImageView img_left;
    @BindView(R.id.img_right)
    ImageView img_right;
    @BindView(R.id.group_time)
    RadioGroup group_time;
    @BindView(R.id.radio0)
    RadioButton radio0;//时
    @BindView(R.id.radio1)
    RadioButton radio1;//日

    @BindView(R.id.btn_comments)
    Button btn_comments;
    @BindView(R.id.edt_comments)
    EditText edt_comments;
    @BindView(R.id.elv_project_detail)
    MyExpandableListView elv_project_detail;

    //第一个分类
    @BindView(R.id.tab_projectinfo)
    TabLayout tab_projectinfo;
    @BindView(R.id.vpg_project_detail)
    CustomViewPager vpg_fragment;

    //第二个分类
    @BindView(R.id.tab_xunjian)
    TabLayout tab_xunjian;
    @BindView(R.id.vpg_project_xunjian)
    CustomViewPager vpg_project_xunjian;

    //第一张图表
    @BindView(R.id.tab_paint)
    MyTablayout tab_paint;
    @BindView(R.id.frame__paint)
    FrameLayout frame__paint;
    @BindView(R.id.txt_notata)
    TextView txt_notata;//显示时间


    //项目信息
    @BindView(R.id.txt_project_name_value)
    TextView txt_project_name_value;
    @BindView(R.id.txt_project_type_value)
    TextView txt_project_type_value;
    @BindView(R.id.txt_project_city_value)
    TextView txt_project_city_value;
    @BindView(R.id.txt_project_architecture_value)
    TextView txt_project_architecture_value;
    @BindView(R.id.txt_project_area_value)
    TextView txt_project_area_value;
    @BindView(R.id.txt_project_area)
    TextView txt_project_area;
    @BindView(R.id.txt_paintOneName)
    ClickTextView txt_paintOneName;
    private Duty_ProjectFragment duty_projectFragment;
    private String repeatTime = "0";//0代表没有重复  会走    1代表已经走过一次   不能连续再走了
    private String repeatTimeTwo = "0";//0代表没有重复  会走    1代表已经走过一次   不能连续再走了
    private int tabPosition = 0;//tablayout滑动的位置
    private int tabPositionTwo = 0;
    private int time = 0;//0 代表时  1 日  2月
    private int tab_running_position = 0;
    private String day_rememrber = "";
    private String category = "";
    private String categoryTwo = "";
    private String tabPostion_position = "0";//诊断图tab滑动的位置
    private String tabPosition_positionTwo = "0";
    private List<CommentsGroupBean.ResobjBean> list_comment = new ArrayList<>();
    private Message_AreaAdapter adapter;
    //评论
    private String toUid;
    private int group_position;
    private int child_position;
    private boolean ischild;
    private boolean isFirst_join = true;
    private boolean isFist = true, isFirstTwo = true;
    private List list_param = new ArrayList<>();
    private EventbusBean eventbusBean;
    private List<HVAC_NewProjectDetailBean.ResobjBean.DiagCategoryBean.ChartBean> list_diagOne;
    private EventBusDownBean eventBusDownBean;
    private List<HVAC_NewProjectDetailBean.ResobjBean.DiagCategoryBean.ChartBean> list_diagTwo;

    //切换制冷或者供暖
    private ArrayAdapter projectTypeAdapter;
    private String[] projectType;

    //设置保存图表 时 日的颜色状态
    private int dateStatus = 1;//1 时  2 日   3月
    private EnergyParaInfoAdapter adapterOne;
    private EfficParaInfoAdapter adapterTwo;
    private SysParamInfoAdapter adapterThree;
    private List<HVAC_NewProjectDetailBean.ResobjBean.EnergyParaInfoBean> runningInfoList = new ArrayList<>();
    private List<HVAC_NewProjectDetailBean.ResobjBean.EfficParaInfoBean> efficParaInfoList = new ArrayList<>();
    private List<HVAC_NewProjectDetailBean.ResobjBean.SysParamInfoBean> sysParamInfo = new ArrayList<>();
    private HVAC_NewProjectDetailBean.ResobjBean.ProjectInfoBean projectInfo;
    private boolean isFirstPaint = true;
    private int whichPaint = 0;//0  代表的时   1  代表的日
    private boolean isMonth = false;
    private String month;
    private String day;
    private String type;
    private String projectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_hvac__new;
    }

    @Override
    protected void initView() {
        month = DateUtils.getYM();
        day = DateUtils.getYMD();
    }

    @Override
    protected void initListener() {
        group_time.setOnCheckedChangeListener(this);

    }

    @Override
    protected void initData() {
        list_param.clear();
        list_param.add(isFirst_join);
        Intent intent = getIntent();
        if (intent != null) {
            projectId = intent.getStringExtra("projectId");
            App.sp.setProjectId(projectId);
        }
        if (!App.sp.getProjectTypeFinish().equals("")) {
            list_param.add(App.sp.getProjectTypeFinish());
        }
        //presenter.onResumeAll(this, list_param);//第一次进入界面会调取该接口  老版本
        presenter.onResumeAll_New(this, list_param);
      /*  String json = AssetsUtils.readText(this, "test.json");
        HVAC_NewProjectDetailBean hvac_newProjectDetailBean = gson.fromJson(json, HVAC_NewProjectDetailBean.class);
        setText(hvac_newProjectDetailBean);*/
        adapter = new Message_AreaAdapter(list_comment, this);
        elv_project_detail.setAdapter(adapter);
        setElvListerner();
        elv_project_detail.setFocusable(false);
        initFragment();//初始化fragment
        initSetInfo();//设置值班信息  设备信息
        initSetXunjian();//设置巡检项和其他 第二个分类
    }

    private void initSetInfo() {
        tab_projectinfo.setTabMode(TabLayout.MODE_FIXED);
        final String title[] = {"运行信息", "故障报警", "运行指导", "值班管理"};
        final List<Fragment> list_fragemnt = new ArrayList<>();
        //  list_fragemnt.add(new Device_ProjectFragment(vpg_fragment));
        Running_ProjectFragment running_projectFragment = new Running_ProjectFragment(vpg_fragment);
        AlarmFragment alarmFragment = new AlarmFragment(vpg_fragment);
        RunningGuideFragment runningGuideFragment = new RunningGuideFragment(vpg_fragment);
        duty_projectFragment = new Duty_ProjectFragment(vpg_fragment);
        Bundle bundle = new Bundle();
        bundle.putString("hvac", "true");
        bundle.putString("projectId", projectId);
        running_projectFragment.setArguments(bundle);
        list_fragemnt.add(running_projectFragment);//运行信息
        list_fragemnt.add(alarmFragment);//报警管理
        list_fragemnt.add(runningGuideFragment);//运行指导
        list_fragemnt.add(duty_projectFragment);//值班信息
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list_fragemnt);
        vpg_fragment.setAdapter(adapter);
        vpg_fragment.setOffscreenPageLimit(4);
        tab_projectinfo.setupWithViewPager(vpg_fragment);
        tab_projectinfo.setSelectedTabIndicatorColor(getResources().getColor(R.color.theme_color));
        setTabColor(title, tab_projectinfo);//设置初始值的textview的状态
        tab_projectinfo.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView textView = ((TextView) tab.getCustomView().findViewById(R.id.title_tv));
                textView.setTextColor(getResources().getColor(R.color.theme_color));
                textView.setSelected(true);
                tab_running_position = tab.getPosition();
                vpg_fragment.setCurrentItem(tab_running_position, false);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView textView = ((TextView) tab.getCustomView().findViewById(R.id.title_tv));
                textView.setTextColor(getResources().getColor(R.color.a666666));
                textView.setSelected(false);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vpg_fragment.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                vpg_fragment.resetHeight(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vpg_fragment.resetHeight(0);

    }

    private void initSetXunjian() {
        tab_xunjian.setTabMode(TabLayout.MODE_FIXED);
        //final String title[] = {"巡检记录", "记录导出", "参数设置", "设备管理"};
        // final String title[] = {"报警阈值", "记录导出", "巡检记录"};
        final String title[] = {"报警阈值", "记录导出"};
        final List<Fragment> list_fragemnt = new ArrayList<>();
        // RunningRecord_DutyFragment running_projectFragment = new RunningRecord_DutyFragment(vpg_project_xunjian);
        Export_RecorderFragment exportFragment = new Export_RecorderFragment(vpg_project_xunjian);
        ParamSettingFragment paramSettingFragment = new ParamSettingFragment(vpg_project_xunjian);
        duty_projectFragment = new Duty_ProjectFragment(vpg_project_xunjian);
        Bundle bundle = new Bundle();
        bundle.putString("hvac", "true");
        bundle.putString("projectId", projectId);
        // running_projectFragment.setArguments(bundle);
        exportFragment.setArguments(bundle);
        list_fragemnt.add(paramSettingFragment);//报警阈值
        list_fragemnt.add(exportFragment);//记录导出
        //list_fragemnt.add(running_projectFragment);//巡检记录
        // list_fragemnt.add(duty_projectFragment);//设备管理
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list_fragemnt);
        vpg_project_xunjian.setAdapter(adapter);
        vpg_project_xunjian.setOffscreenPageLimit(4);
        tab_xunjian.setupWithViewPager(vpg_project_xunjian);
        tab_xunjian.setSelectedTabIndicatorColor(getResources().getColor(R.color.theme_color));
        setTabColor(title, tab_xunjian);//设置初始值的textview的状态
        tab_xunjian.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView textView = ((TextView) tab.getCustomView().findViewById(R.id.title_tv));
                textView.setTextColor(getResources().getColor(R.color.theme_color));
                textView.setSelected(true);
                tab_running_position = tab.getPosition();
                vpg_project_xunjian.setCurrentItem(tab_running_position, false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView textView = ((TextView) tab.getCustomView().findViewById(R.id.title_tv));
                textView.setTextColor(getResources().getColor(R.color.a666666));
                textView.setSelected(false);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vpg_project_xunjian.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                vpg_project_xunjian.resetHeight(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vpg_project_xunjian.resetHeight(0);
    }

    private void setElvListerner() {
        elv_project_detail.setGroupIndicator(null);
        elv_project_detail.setChildIndicator(null);
        elv_project_detail.setOnGroupClickListener((expandableListView, view, i, l) -> {
            elv_project_detail.expandGroup(i);
            toUid = "";
            if (!App.sp.getUid().equals(list_comment.get(i).getUserId())) {
                edt_comments.setHint("回复" + (list_comment.get(i).getUserName() + ":"));
                edt_comments.requestFocus();
                toUid = list_comment.get(i).getCommentId();
            } else {
                edt_comments.setHint("评论");
                edt_comments.requestFocus();
            }
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

            return true;
        });
        //子菜单栏进行评论
     /*   elv_project_detail.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                ToastUtils.showToast(Group_Project_DetailsActivity.this,"点击了"+childPosition+"===="+"回复"+list_comment.get(groupPosition).getComments().get(childPosition).getUserName());
                elv_project_detail.expandGroup(groupPosition);
                toUid = "";
                if (!App.sp.getUid().equals(list_comment.get(groupPosition).getComments().get(childPosition).getUserId())) {
                    edt_comments.setHint("回复" + (list_comment.get(groupPosition).getComments().get(childPosition).getUserName() + ":"));
                    edt_comments.requestFocus();
                    toUid = list_comment.get(groupPosition).getComments().get(childPosition).getCommentId();
                } else {
                    edt_comments.setHint("评论");
                    edt_comments.requestFocus();
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                return true;
            }
        });*/
        elv_project_detail.setOnItemLongClickListener((adapterView, view, i, id) -> {

            if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                long position = ((ExpandableListView) adapterView).getExpandableListPosition(i);
                group_position = ExpandableListView.getPackedPositionGroup(position);
                child_position = ExpandableListView.getPackedPositionChild(position);
                if (App.sp.getUid().equals(list_comment.get(group_position).getComments().get(child_position).getUserId())) {
                    ShowDoubleButtonDialog("是否删除该评论?", 100);
                    ischild = true;
                }
            } else {
                long position = elv_project_detail.getExpandableListPosition(i);
                if (App.sp.getUid().equals(list_comment.get(ExpandableListView.getPackedPositionGroup(position)).getUserId())) {
                    group_position = ExpandableListView.getPackedPositionGroup(position);
                    ShowDoubleButtonDialog("是否删除该评论?", 100);
                    ischild = false;
                }

            }

            return false;
        });
        edt_comments.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                // 此处为得到焦点时的处理内容
            } else {
                // 此处为失去焦点时的处理内容
                toUid = "";
                edt_comments.setHint("评论");
            }
        });
        glv_top.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HVAC_NewProjectDetailBean.ResobjBean.EnergyParaInfoBean infoBean = runningInfoList.get(position);
                if (infoBean.getChartSign().equals("0")) {
                   /* startActivity(new Intent(HVAC_NewActivity.this, ParamPaintActivity.class).
                            putExtra("which", "1").
                            putExtra("paintOne", (Serializable) runningInfoList.get(position)));*/
                    tv_title.setText(infoBean.getName() == null ? "暖通空调" : infoBean.getName());
                    type = infoBean.getType();
                    if (whichPaint == 0) {//时
                        getPaintData(day);
                    } else if (whichPaint == 1) {//日
                        getPaintData(month);
                    }
                }

            }
        });
        glv_topTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HVAC_NewProjectDetailBean.ResobjBean.EfficParaInfoBean infoBean = efficParaInfoList.get(position);
                if (infoBean.getChartSign().equals("0")) {
                   /* if (infoBean.getDisplayType().equals("M")) {
                        startActivity(new Intent(HVAC_NewActivity.this, EnviromentMonitoringActivity.class));
                        // ToastUtils.showToast(HVAC_NewActivity.this,"正在研发。。。");
                    } else if (infoBean.getDisplayType().equals("F")) {//图表拉长
                        startActivity(new Intent(HVAC_NewActivity.this, ParamPaintWaterActivity.class).putExtra("which", "2").
                                putExtra("paintTwo", (Serializable) efficParaInfoList.get(position)));
                        // ToastUtils.showToast(HVAC_NewActivity.this,"正在研发。。。");
                    } else {
                        startActivity(new Intent(HVAC_NewActivity.this, ParamPaintActivity.class).putExtra("which", "2").
                                putExtra("paintTwo", (Serializable) efficParaInfoList.get(position)));
                    }*/
                    if (infoBean.getDisplayType().equals("M")) {
                        startActivity(new Intent(HVAC_NewActivity.this, EnviromentMonitoringActivity.class));
                    } else {
                        tv_title.setText(infoBean.getName() == null ? "暖通空调" : infoBean.getName());
                        type = infoBean.getType();
                        if (whichPaint == 0) {//时
                            getPaintData(day);
                        } else if (whichPaint == 1) {//日
                            getPaintData(month);

                        }
                    }

                }

            }
        });
        glv_topThree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HVAC_NewProjectDetailBean.ResobjBean.SysParamInfoBean infoBean = sysParamInfo.get(position);
                if (infoBean.getChartSign().equals("0")) {
                 /*   if (infoBean.getDisplayType().equals("M")) {
                        startActivity(new Intent(HVAC_NewActivity.this, EnviromentMonitoringActivity.class));
                        // ToastUtils.showToast(HVAC_NewActivity.this,"正在研发。。。");
                    } else if (infoBean.getDisplayType().equals("F")) {
                        startActivity(new Intent(HVAC_NewActivity.this, ParamPaintActivity.class).putExtra("which", "3").
                                putExtra("paintThree", (Serializable) sysParamInfo.get(position)));
                    } else {
                        startActivity(new Intent(HVAC_NewActivity.this, ParamPaintActivity.class).putExtra("which", "3").
                                putExtra("paintThree", (Serializable) sysParamInfo.get(position)));
                    }*/
                    if (infoBean.getDisplayType().equals("M")) {
                        startActivity(new Intent(HVAC_NewActivity.this, EnviromentMonitoringActivity.class));
                    } else {
                        tv_title.setText(infoBean.getName() == null ? "暖通空调" : infoBean.getName());
                        type = infoBean.getType();
                        if (whichPaint == 0) {//时
                            getPaintData(day);
                        } else if (whichPaint == 1) {//日
                            getPaintData(month);
                        }
                    }

                }

            }
        });
    }


    private void initFragment() {
        //显示图表信息
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame__paint, Protect_PaintFragment.getInstance()).commit();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setTabColor(String[] title, TabLayout tabs) {
        for (int i = 0; i < title.length; i++) {
            TabLayout.Tab tab = tabs.getTabAt(i);
            tab.setCustomView(getTabView(i, title[i]));
        }
    }


    public View getTabView(int position, String title) {
        LayoutInflater mInflater = LayoutInflater.from(this);
        View view = mInflater.inflate(R.layout.icon_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.title_tv);
        textView.setText(title);
        if (position == tabPosition) {
            textView.setTextColor(getResources().getColor(R.color.theme_color));
        } else {
            textView.setTextColor(getResources().getColor(R.color.a666666));
        }
        return view;
    }

    @Override
    protected Group_Project_DetailPresenter getPresenter() {
        return new Group_Project_DetailPresenter();
    }


    private void setPaintParams(String tabPostion_position) {
        if (list_diagOne.get(Integer.parseInt(tabPostion_position)).getCode() != null) {
            category = list_diagOne.get(Integer.parseInt(tabPostion_position)).getCode();
        }
        eventbusBean.setPosition(tabPostion_position);
        eventbusBean.setPaintCount(1);
        eventbusBean.setCode(category);
    }

    @Override
    protected void toastDialogCallBack(ToastDialog dialog) {
        super.toastDialogCallBack(dialog);
        if (ischild == true) {
            presenter.onDeleteCommitList(this, list_comment.get(group_position).getComments().get(child_position).getCommentId());
        } else {
            presenter.onDeleteCommitList(this, list_comment.get(group_position).getCommentId());

        }
        dialog.dismiss();
    }

    @Override
    protected void doubleButtonDialogCallBack(final DoubleButtonDialog dialog) {
        if (dialog.getCode() == 100) {
            // Toast.makeText(mContext,"确定删除此足迹",Toast.LENGTH_SHORT).show();
            if (ischild == true) {
                presenter.onDeleteCommitList(this, list_comment.get(group_position).getComments().get(child_position).getCommentId());
            } else {
                presenter.onDeleteCommitList(this, list_comment.get(group_position).getCommentId());
            }
            dialog.dismiss();
        }
    }

    @Override
    public void getCommitList(Object data) {
        CommentsGroupBean response = (CommentsGroupBean) data;
        if (response.getCode() == 40000) {
            isFirst_join = false;
            list_comment.clear();
            list_comment = response.getResobj();
            adapter.setList(list_comment);
            elv_project_detail.setAdapter(adapter);
            initCommit();
        } else if (response.getCode() == 40005) {
            list_comment.clear();
            adapter.notifyDataSetChanged();
        } else {
            ToastUtils.showToast(this, response.getErrormsg());
        }
    }

    //提交留言
    @Override
    public void postCommit(Object data) {
        RBResponse response = (RBResponse) data;
        if (response.getCode() == 40000) {
            ToastUtils.showToast(this, getString(R.string.comments_succeed));
            toUid = "";
            edt_comments.clearFocus();
            presenter.onCommentList(this);//获取留言列表
            edt_comments.setText("");
        } else {
            ToastUtils.showToast(this, getString(R.string.comments_error));
        }
    }

    @Override
    public void deleteCommitList(Object data) {
        RBResponse bean = (RBResponse) data;
        if (bean.getCode() == 40000) {
            ToastUtils.showToast(this, "删除成功");
            presenter.onCommentList(this);//获取留言列表
        } else if (bean.getCode() == 40005) {
            presenter.onCommentList(this);//获取留言列表
        } else {
            ToastUtils.showToast(this, bean.getErrormsg());
        }
    }

    private void initCommit() {
        if (list_comment != null) {

            for (int i = 0; i < adapter.getGroupCount(); i++) {
                elv_project_detail.expandGroup(i, false);
            }

        }
    }

    @Override
    public void getPaintAnylize(Object data) {

    }

    @OnClick({R.id.iv_back, R.id.img_add_time, R.id.btn_comments, R.id.txt_gp_moretalk, R.id.img_left, R.id.img_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_comments:
                String content = edt_comments.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    ToastUtils.showToast(this, getString(R.string.comments_content_no_null));
                    return;
                }
                BaseTools.closeKeybord(edt_comments, this);
                presenter.onSubmit(this, content, toUid);//提交留言
                break;
            case R.id.txt_gp_moretalk://查看更多留言详情
                startActivity(new Intent(this, More_CommentsActivity.class));
                break;
            case R.id.img_add_time:
                //setdatePicker(time);  //项目信息
                startActivity(new Intent(this, ProjectInfoHVACActivity.class).putExtra("info", (Serializable) projectInfo));
                break;
            case R.id.img_left:
                if (whichPaint == 0) {//时
                    day = DateUtils.getSpecifiedDayBefore(day);
                    getPaintData(day);
                } else if (whichPaint == 1) {//日
                    month = DateUtils.getLastMonth(month);
                    getPaintData(month);
                }
                break;
            case R.id.img_right:
                if (whichPaint == 0) {//时
                    day = DateUtils.getSpecifiedDayAfter(day);

                    getPaintData(day);
                } else if (whichPaint == 1) {//日
                    month = DateUtils.getNextMonth(month);
                    getPaintData(month);
                }
                break;

            default:
                break;
        }
    }

    /**
     * 请求图表数据
     *
     * @param dateStr
     */
    private void getPaintData(String dateStr) {
        EventbusBean eventbusBean = new EventbusBean();
        eventbusBean.setCode(type);
        eventbusBean.setDateStr(dateStr);
        eventbusBean.setPaintCount(1);
        if (isMonth) {
            eventbusBean.setIsMonth("1");
        } else {
            eventbusBean.setIsMonth("0");
        }
        EventBus.getDefault().post(eventbusBean);//进行订阅
    }


    public String getTime(Date date, boolean isMonth) {
        SimpleDateFormat format = null;
        if (isMonth) {
            format = new SimpleDateFormat("yyyy-MM");
        } else {
            format = new SimpleDateFormat("yyyy-MM-dd");
        }

        return format.format(date);
    }

    public String getTime(Date date, String isYear) {
        SimpleDateFormat format = null;
        if (isYear.equals("true")) {
            format = new SimpleDateFormat("yyyy");
        }

        return format.format(date);
    }


    @Override
    public void setText(Object data) {
        presenter.onCommentList(this);//获取留言列表
        group_time.setVisibility(View.VISIBLE);
        HVAC_NewProjectDetailBean bean = (HVAC_NewProjectDetailBean) data;
        HVAC_NewProjectDetailBean.ResobjBean resobj = bean.getResobj();

        //第一部分图表信息
        List<HVAC_NewProjectDetailBean.ResobjBean.DiagCategoryBean> listCategory = resobj.getDiagCategory();
        if (listCategory == null || listCategory.isEmpty()) {
            return;
        }
        radio0.setBackgroundResource(R.mipmap.green_background_circle);
        txt_paintOneName.setText(listCategory.get(0).getCategoryName());
        list_diagOne = listCategory.get(0).getChart();
        isFirstPaint = false;//防止图表请求多次   radioGroup先进行请求
        if (list_diagOne != null && !list_diagOne.isEmpty()) {
            if (isFist) {
                isFist = !isFist;
                List<HVAC_NewProjectDetailBean.ResobjBean.SysParamInfoBean> sysParamInfo = resobj.getSysParamInfo();
                if (!sysParamInfo.isEmpty() && sysParamInfo.size() > 2) {
                    type = sysParamInfo.get(2).getType();
                }

                getPaintData(day);
            }

        }

      /*  //加载天气图片
        Glide.with(this).load(ConstantValue.URL + resobj.getWeather()).placeholder(R.mipmap.default_head).transform(new GlideCircleTransform(this)).crossFade().into(img_weather);
        if (resobj.getTime() != null) {
            txt_notata.setText(resobj.getTime());
            App.sp.setDate(resobj.getTime());
            App.sp.setDateEnergy(resobj.getTime());
        }*/
        //第二个顶部
        runningInfoList = resobj.getEnergyParaInfo();
        if (runningInfoList == null) {
            line_two_gone.setVisibility(View.GONE);


        } else {
            glv_top.setGridViewParam(this, runningInfoList);
            if (adapterOne == null) {
                adapterOne = new EnergyParaInfoAdapter(runningInfoList, this);
                glv_top.setAdapter(adapterOne);
            } else {
                adapterOne.setList(runningInfoList);
            }
        }
        //第一个顶部
        efficParaInfoList = resobj.getEfficParaInfo();
        if (efficParaInfoList != null) {
            line_topOne.setVisibility(View.VISIBLE);
            glv_topTwo.setGridViewParam(this, efficParaInfoList);
            if (adapterTwo == null) {
                adapterTwo = new EfficParaInfoAdapter(efficParaInfoList, this);
                glv_topTwo.setAdapter(adapterTwo);
            } else {
                adapterTwo.setList(efficParaInfoList);
            }
        }
        //第三个顶部
        sysParamInfo = resobj.getSysParamInfo();
        if (sysParamInfo != null) {
            line_topTwo.setVisibility(View.VISIBLE);
            glv_topThree.setGridViewParam(this, sysParamInfo);
            if (adapterThree == null) {
                adapterThree = new SysParamInfoAdapter(sysParamInfo, this);
                glv_topThree.setAdapter(adapterThree);
            } else {
                adapterThree.setList(sysParamInfo);
            }

        }

        //项目信息
        projectInfo = resobj.getProjectInfo();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (isFirstPaint) {
            return;
        }
        switch (checkedId) {
            case R.id.radio0:
                whichPaint = 0;//代表我点击时
                img_left.setImageResource(R.mipmap.leftarrow);
                img_right.setImageResource(R.mipmap.rightarrow);
                isMonth = false;
                radio0.setBackgroundResource(R.mipmap.green_background_circle);
                radio1.setBackgroundResource(R.mipmap.white_background_circle);
                getPaintData(day);
                break;
            case R.id.radio1:
                whichPaint = 1;//代表我点击日
                img_left.setImageResource(R.mipmap.arrow_double_left);
                img_right.setImageResource(R.mipmap.arrow_double_right);
                isMonth = true;
                radio0.setBackgroundResource(R.mipmap.white_background_circle);
                radio1.setBackgroundResource(R.mipmap.green_background_circle);
                getPaintData(month);
                break;
        }
    }
}
