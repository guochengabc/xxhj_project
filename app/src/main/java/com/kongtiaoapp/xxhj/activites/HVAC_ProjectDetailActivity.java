package com.kongtiaoapp.xxhj.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
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

import com.bumptech.glide.Glide;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.HVAC_RunningInfoAdapter;
import com.kongtiaoapp.xxhj.adapter.Message_AreaAdapter;
import com.kongtiaoapp.xxhj.adapter.MyFragmentAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.CommentsGroupBean;
import com.kongtiaoapp.xxhj.bean.EventBusDownBean;
import com.kongtiaoapp.xxhj.bean.EventbusBean;
import com.kongtiaoapp.xxhj.bean.HVAC_ProjectDetailBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.duty.fragment.Duty_ProjectFragment;
import com.kongtiaoapp.xxhj.fragment.Project_PaintTwoFragment;
import com.kongtiaoapp.xxhj.fragment.Protect_PaintFragment;
import com.kongtiaoapp.xxhj.fragment.RunningGuideFragment;
import com.kongtiaoapp.xxhj.fragment.Running_ProjectFragment;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.Group_Project_DetailPresenter;
import com.kongtiaoapp.xxhj.mvp.view.Group_Project_DetailView;
import com.kongtiaoapp.xxhj.ui.timeselector.activity.TimePickerView;
import com.kongtiaoapp.xxhj.ui.view.ClickTextView;
import com.kongtiaoapp.xxhj.ui.view.CustomViewPager;
import com.kongtiaoapp.xxhj.ui.view.DoubleButtonDialog;
import com.kongtiaoapp.xxhj.ui.view.FullGridView;
import com.kongtiaoapp.xxhj.ui.view.MyExpandableListView;
import com.kongtiaoapp.xxhj.ui.view.ToastDialog;
import com.kongtiaoapp.xxhj.utils.BaseTools;
import com.kongtiaoapp.xxhj.utils.DateUtils;
import com.kongtiaoapp.xxhj.utils.GlideCircleTransform;
import com.kongtiaoapp.xxhj.utils.MyTablayout;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;
import com.kongtiaoapp.xxhj.utils.popup.CommonPopupWindow;
import com.kongtiaoapp.xxhj.utils.popup.PopupViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 暖通项目详情  2017年11月29日  GC
 */
public class HVAC_ProjectDetailActivity extends BaseActivity<Group_Project_DetailPresenter, Group_Project_DetailView>
        implements Group_Project_DetailView, RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.img_weather)
    ImageView img_weather;
    @BindView(R.id.glv_top)
    FullGridView glv_top;
    @BindView(R.id.group_time)
    RadioGroup group_time;
    @BindView(R.id.radio1)
    RadioButton radio1;
    @BindView(R.id.radio2)
    RadioButton radio2;
    @BindView(R.id.radio3)
    RadioButton radio3;
    @BindView(R.id.radio4)
    RadioButton radio4;
    @BindView(R.id.btn_comments)
    Button btn_comments;
    @BindView(R.id.edt_comments)
    EditText edt_comments;
    @BindView(R.id.elv_project_detail)
    MyExpandableListView elv_project_detail;
    @BindView(R.id.img_move_down)
    ImageView img_move_down;//值班信息的显示与隐藏的图标
    @BindView(R.id.line_other)
    LinearLayout line_other;
    @BindView(R.id.img_isspread)
    ImageView img_isspread;//运行信息展开合并处理
    @BindView(R.id.tab_projectinfo)
    TabLayout tab_projectinfo;
    @BindView(R.id.vpg_project_detail)
    CustomViewPager vpg_fragment;

    //第一张图表
    @BindView(R.id.tab_paint)
    MyTablayout tab_paint;
    @BindView(R.id.frame__paint)
    FrameLayout frame__paint;
    @BindView(R.id.txt_notata)
    TextView txt_notata;//显示时间


    //第二张图表
    @BindView(R.id.tab_paintTwo)
    MyTablayout tab_paintTwo;
    @BindView(R.id.group_timeTwo)
    RadioGroup group_timeTwo;
    @BindView(R.id.radioTwo1)
    RadioButton radioTwo1;
    @BindView(R.id.radioTwo2)
    RadioButton radioTwo2;
    @BindView(R.id.radioTwo3)
    RadioButton radioTwo3;
    @BindView(R.id.frame__paintTwo)
    FrameLayout frame__paintTwo;
    @BindView(R.id.txt_notataDown)
    TextView txt_notataDown;

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
    @BindView(R.id.txt_paintTwoName)
    ClickTextView txt_paintTwoName;
    private Duty_ProjectFragment duty_projectFragment;
    private String repeatTime = "0";//0代表没有重复  会走    1代表已经走过一次   不能连续再走了
    private String repeatTimeTwo = "0";//0代表没有重复  会走    1代表已经走过一次   不能连续再走了
    private int tabPosition = 0;//tablayout滑动的位置
    private int tabPositionTwo = 0;
    private int time;//0 代表时  1 日  2月
    private int timeTwo = 0;
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
    private boolean isproject_visible = false, isFist = true, isFirstTwo = true;
    private List list_param = new ArrayList<>();
    private EventbusBean eventbusBean;
    private List<HVAC_ProjectDetailBean.ResobjBean.DiagCategoryBean.ChartBean> list_diagOne;
    private EventBusDownBean eventBusDownBean;
    private List<HVAC_ProjectDetailBean.ResobjBean.DiagCategoryBean.ChartBean> list_diagTwo;

    //切换制冷或者供暖
    private ArrayAdapter projectTypeAdapter;
    private String[] projectType;

    //设置保存图表 时 日的颜色状态
    private int dateStatus=1;//1 时  2 日   3月
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_hvac__project_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        group_time.setOnCheckedChangeListener(this);
        group_timeTwo.setOnCheckedChangeListener(this);
        group_time.check(R.id.radio1);
        group_timeTwo.check(R.id.radioTwo1);
    }

    @Override
    protected void initData() {
        adapter = new Message_AreaAdapter(list_comment, this);
        elv_project_detail.setAdapter(adapter);
        setElvListerner();
        elv_project_detail.setFocusable(false);
        initFragment();//初始化fragment
        initFragmentTwo();//初始化第二个模块图表fragment
        initSetInfo();//设置值班信息  设备信息
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
    }

    private void initFragmentTwo() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame__paintTwo, Project_PaintTwoFragment.getInstance()).commit();
    }


    private void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame__paint, Protect_PaintFragment.getInstance()).commit();
    }

    private void initSetInfo() {
        tab_projectinfo.setTabMode(TabLayout.MODE_FIXED);
        final String title[] = {"值班信息", "运行指导", "运行信息"};
        final List<Fragment> list_fragemnt = new ArrayList<>();
        duty_projectFragment = new Duty_ProjectFragment(vpg_fragment);
        list_fragemnt.add(duty_projectFragment);
        //  list_fragemnt.add(new Device_ProjectFragment(vpg_fragment));
        list_fragemnt.add(new RunningGuideFragment(vpg_fragment));//运行指导
        list_fragemnt.add(new Running_ProjectFragment(vpg_fragment));//运行信息
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list_fragemnt);
        vpg_fragment.setAdapter(adapter);
        vpg_fragment.setOffscreenPageLimit(3);
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
                if (tab_running_position == 2) {
                    img_isspread.setVisibility(View.VISIBLE);
                } else {
                    img_isspread.setVisibility(View.GONE);
                }
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
                if (position == 2) {
                    img_isspread.setVisibility(View.VISIBLE);
                } else {
                    img_isspread.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vpg_fragment.resetHeight(0);

    }

    @Override
    protected void onResume() {
        super.onResume();
        list_param.clear();
        list_param.add(isFirst_join);
        if (!App.sp.getProjectTypeFinish().equals("")) {
            list_param.add(App.sp.getProjectTypeFinish());
        }
        presenter.onResumeAll(this, list_param);//第一次进入界面会调取该接口
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

    @Override
    public void setText(Object data) {
        presenter.onCommentList(this);//获取留言列表
        group_time.setVisibility(View.VISIBLE);
        group_timeTwo.setVisibility(View.VISIBLE);
        Log.i("fffffffffff","R.id.setText");
        HVAC_ProjectDetailBean bean = (HVAC_ProjectDetailBean) data;
        HVAC_ProjectDetailBean.ResobjBean resobj = bean.getResobj();
        //加载天气图片
        Glide.with(this).load(ConstantValue.URL + resobj.getWeather()).placeholder(R.mipmap.default_head).transform(new GlideCircleTransform(this)).crossFade().into(img_weather);
        Log.i(TAG, "图片地址==" + ConstantValue.URL + resobj.getWeather());
        if (resobj.getTime() != null) {
            txt_notata.setText(resobj.getTime());
            txt_notataDown.setText(resobj.getTime());
            App.sp.setDate(resobj.getTime());
            App.sp.setDateEnergy(resobj.getTime());
        }
        List<HVAC_ProjectDetailBean.ResobjBean.RunningInfoBean> runningInfoList = resobj.getRunningInfo();
        if (runningInfoList != null) {
            HVAC_RunningInfoAdapter adapter = new HVAC_RunningInfoAdapter(runningInfoList, this);
            glv_top.setAdapter(adapter);
        }

        //项目信息
        HVAC_ProjectDetailBean.ResobjBean.ProjectInfoBean projectInfo = resobj.getProjectInfo();
        txt_project_name_value.setText(projectInfo.getProjectName());
        if (projectInfo.getProjectType().equals("A")) {
            txt_project_type_value.setText("制冷");
        } else if (projectInfo.getProjectType().equals("B")) {
            txt_project_type_value.setText("供暖");
        }
        txt_project_architecture_value.setText(projectInfo.getBuildingType());
        txt_project_city_value.setText(projectInfo.getCity());
        String projectType = projectInfo.getProjectType();
        if (projectType.equals("B")) {
            txt_project_area.setText(getString(R.string.project_heatingArea));
            txt_project_area_value.setText(projectInfo.getHeatingArea());

        } else if (projectType.equals("A")) {
            txt_project_area.setText(getString(R.string.project_coolingArea));
            txt_project_area_value.setText(projectInfo.getCoolingArea());
        }
        //第一部分图表信息
        List<HVAC_ProjectDetailBean.ResobjBean.DiagCategoryBean> listCategory = resobj.getDiagCategory();
        int categorySize = listCategory.size();//图表模块
        if (listCategory == null || listCategory.isEmpty()) {
            return;
        }
        txt_paintOneName.setText(listCategory.get(0).getCategoryName());
        list_diagOne = listCategory.get(0).getChart();
        if (list_diagOne != null && !list_diagOne.isEmpty()) {
            if (isFist) {
                isFist = !isFist;
                initSetPaint();//设置图表信息
                setTimeVisible(0);
            }

        }

        //第二部分图表
        if (categorySize == 2) {
            txt_paintTwoName.setText(listCategory.get(1).getCategoryName());
            list_diagTwo = listCategory.get(1).getChart();
            if (list_diagTwo != null && !list_diagTwo.isEmpty()) {
                if (isFirstTwo) {
                    isFirstTwo = !isFirstTwo;
                    initSetPaintTwo();//设置第二张图表信息
                    setTimeVisibleTwo(0);
                }

            }

        }


    }

    private void setTimeVisibleTwo(int tabPosition) {
        List<HVAC_ProjectDetailBean.ResobjBean.DiagCategoryBean.ChartBean.TimeNameBean> listTime = list_diagTwo.get(tabPosition).getTimeName();
        radioTwo1.setVisibility(View.GONE);
        radioTwo2.setVisibility(View.GONE);
        radioTwo3.setVisibility(View.GONE);
        radioTwo1.setBackgroundResource(R.mipmap.white_background_circle);
        radioTwo2.setBackgroundResource(R.mipmap.white_background_circle);
        radioTwo3.setBackgroundResource(R.mipmap.white_background_circle);
        for (int i = 0; i < listTime.size(); i++) {
            switch (listTime.get(i).getTimeCode()) {

                case "A":
                    radioTwo1.setVisibility(View.VISIBLE);
                    if (timeTwo == 0) {
                        radioTwo1.setBackgroundResource(R.mipmap.green_background_circle);
                        eventBusDownBean.setIsMonth("0");
                    }
                    break;
                case "B":

                    if (radioTwo1.getVisibility() == View.GONE) {
                        radioTwo2.setBackgroundResource(R.mipmap.green_background_circle);
                        eventBusDownBean.setIsMonth("1");
                    } else {
                        if (timeTwo == 1) {
                            radioTwo2.setBackgroundResource(R.mipmap.green_background_circle);
                            eventBusDownBean.setIsMonth("1");
                        }
                    }
                    radioTwo2.setVisibility(View.VISIBLE);
                    break;
                case "C":
                    if (radioTwo1.getVisibility() == View.GONE && radioTwo2.getVisibility() == View.GONE) {
                        eventBusDownBean.setIsMonth("1");
                        radioTwo3.setBackgroundResource(R.mipmap.green_background_circle);
                    }
                    radioTwo3.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }
    }

    private void initSetPaintTwo() {
        eventBusDownBean = new EventBusDownBean();
        setPaintParamsTwo("0");
        List<HVAC_ProjectDetailBean.ResobjBean.DiagCategoryBean.ChartBean.TimeNameBean> timeName = list_diagTwo.get(0).getTimeName();
        if (timeName.get(0).getTimeCode().equals("A")) {
            eventBusDownBean.setIsMonth("0");
        } else {
            eventBusDownBean.setIsMonth("1");
        }


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        App.sp.setDateEnergy(format.format(new Date(System.currentTimeMillis())));
        EventBus.getDefault().post(eventBusDownBean);
        tab_paintTwo.setTabMode(TabLayout.MODE_FIXED);
        final String title[] = new String[list_diagTwo.size()];
        for (int i = 0; i < list_diagTwo.size(); i++) {
            title[i] = list_diagTwo.get(i).getText();
            tab_paintTwo.addTab(tab_paintTwo.newTab().setText(title[i]));
        }
        tab_paintTwo.setSelectedTabIndicatorColor(getResources().getColor(R.color.theme_color));
        setTabColor(title, tab_paintTwo);//设置初始值的textview的状态
        tab_paintTwo.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView textView = ((TextView) tab.getCustomView().findViewById(R.id.title_tv));
                textView.setTextColor(getResources().getColor(R.color.theme_color));
                textView.setSelected(true);
                tabPositionTwo = tab.getPosition();
                switch (tabPositionTwo) {
                    case 0:
                        tabPosition_positionTwo = "0";
                        setPaintParamsTwo(tabPosition_positionTwo);
                        setTimeVisibleTwo(tabPositionTwo);//设置选择时日月的显示
                        EventBus.getDefault().post(eventBusDownBean);

                        break;
                    case 1:
                        tabPosition_positionTwo = "1";
                        setPaintParamsTwo(tabPosition_positionTwo);
                        setTimeVisibleTwo(tabPositionTwo);//设置选择时日月的显示
                        EventBus.getDefault().post(eventBusDownBean);
                        break;
                    case 2:
                        tabPosition_positionTwo = "2";
                        setPaintParamsTwo(tabPosition_positionTwo);
                        setTimeVisibleTwo(tabPositionTwo);//设置选择时日月的显示
                        EventBus.getDefault().post(eventBusDownBean);
                        break;
                    case 3:
                        tabPosition_positionTwo = "3";
                        setPaintParamsTwo(tabPosition_positionTwo);
                        setTimeVisibleTwo(tabPositionTwo);//设置选择时日月的显示
                        EventBus.getDefault().post(eventBusDownBean);
                        break;
                    default:
                        break;
                }
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
    }

    /**
     * 设置图表信息
     *
     * @param
     */
    private void initSetPaint() {
        eventbusBean = new EventbusBean();
        setPaintParams("0");
        List<HVAC_ProjectDetailBean.ResobjBean.DiagCategoryBean.ChartBean.TimeNameBean> timeName = list_diagOne.get(0).getTimeName();
        if (timeName.get(0).getTimeCode().equals("A")) {
            eventbusBean.setIsMonth("0");
        } else {
            eventbusBean.setIsMonth("1");
        }

        eventbusBean.setChartSign(list_diagOne.get(0).getTimeName().get(0).getChartSign());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        App.sp.setDate(format.format(new Date(System.currentTimeMillis())));
        EventBus.getDefault().post(eventbusBean);
        tab_paint.setTabMode(TabLayout.MODE_FIXED);
        final String title[] = new String[list_diagOne.size()];
        for (int i = 0; i < list_diagOne.size(); i++) {
            title[i] = list_diagOne.get(i).getText();
            tab_paint.addTab(tab_paint.newTab().setText(title[i]));
        }
        tab_paint.setSelectedTabIndicatorColor(getResources().getColor(R.color.theme_color));
        setTabColor(title, tab_paint);//设置初始值的textview的状态
        tab_paint.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView textView = ((TextView) tab.getCustomView().findViewById(R.id.title_tv));
                textView.setTextColor(getResources().getColor(R.color.theme_color));
                textView.setSelected(true);
                tabPosition = tab.getPosition();
                switch (tabPosition) {
                    case 0:
                        tabPostion_position = "0";
                        setPaintParams(tabPostion_position);
                        setTimeVisible(tabPosition);//设置选择时日月的显示
                        EventBus.getDefault().post(eventbusBean);

                        break;
                    case 1:
                        tabPostion_position = "1";
                        setPaintParams(tabPostion_position);
                        setTimeVisible(tabPosition);//设置选择时日月的显示
                        EventBus.getDefault().post(eventbusBean);
                        break;
                    case 2:
                        tabPostion_position = "2";
                        setPaintParams(tabPostion_position);
                        setTimeVisible(tabPosition);//设置选择时日月的显示
                        EventBus.getDefault().post(eventbusBean);
                        break;
                    case 3:
                        tabPostion_position = "3";
                        setPaintParams(tabPostion_position);
                        setTimeVisible(tabPosition);//设置选择时日月的显示
                        EventBus.getDefault().post(eventbusBean);
                        break;
                    default:
                        break;
                }
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
    }

    private void setPaintParamsTwo(String tabPostion_position) {
        if (list_diagTwo.get(Integer.parseInt(tabPostion_position)).getCode() != null) {
            categoryTwo = list_diagTwo.get(Integer.parseInt(tabPostion_position)).getCode();
        }
        eventBusDownBean.setPosition(tabPostion_position);
        eventBusDownBean.setPaintCount(list_diagTwo.get(Integer.parseInt(tabPostion_position)).getChartNum());
        eventBusDownBean.setCode(categoryTwo);
    }

    /**
     * 显示时日月
     */
    private void setTimeVisible(int tabPosition) {
        List<HVAC_ProjectDetailBean.ResobjBean.DiagCategoryBean.ChartBean.TimeNameBean> listTime = list_diagOne.get(tabPosition).getTimeName();
        radio1.setVisibility(View.GONE);
        radio2.setVisibility(View.GONE);
        radio3.setVisibility(View.GONE);
        radio1.setBackgroundResource(R.mipmap.white_background_circle);
        radio2.setBackgroundResource(R.mipmap.white_background_circle);
        radio3.setBackgroundResource(R.mipmap.white_background_circle);

        for (int i = 0; i < listTime.size(); i++) {
            Log.i("setTimeVisible","返回的TimeCode==="+listTime.get(i).getTimeCode());
            switch (listTime.get(i).getTimeCode()) {
                case "A":
                    radio1.setVisibility(View.VISIBLE);
                    if (time == 0) {
                        eventbusBean.setIsMonth("0");
                        radio1.setBackgroundResource(R.mipmap.green_background_circle);
                    }
                    break;
                case "B":
                    if (radio1.getVisibility() == View.GONE) {
                        eventbusBean.setIsMonth("1");
                        radio2.setBackgroundResource(R.mipmap.green_background_circle);
                    } else {
                        if (time == 1) {//当点击日的时候，并且有时跟日   会获取日的数据
                            radio2.setBackgroundResource(R.mipmap.green_background_circle);
                            eventbusBean.setIsMonth("1");
                        }
                    }
                    radio2.setVisibility(View.VISIBLE);
                    break;
                case "C":
                    radio3.setVisibility(View.VISIBLE);
                    if (radio1.getVisibility() == View.GONE && radio2.getVisibility() == View.GONE) {
                        eventbusBean.setIsMonth("1");
                        radio3.setBackgroundResource(R.mipmap.green_background_circle);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void setPaintParams(String tabPostion_position) {
        if (list_diagOne.get(Integer.parseInt(tabPostion_position)).getCode() != null) {
            category = list_diagOne.get(Integer.parseInt(tabPostion_position)).getCode();
        }
        eventbusBean.setPosition(tabPostion_position);
        eventbusBean.setPaintCount(list_diagOne.get(Integer.parseInt(tabPostion_position)).getChartNum());
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

    @OnClick({R.id.iv_back, R.id.line_project, R.id.img_add_time, R.id.btn_comments, R.id.txt_gp_moretalk, R.id.img_lastMonth,
            R.id.img_nextMonth, R.id.img_lastMonthDown, R.id.img_nextMonthDown, R.id.line_projectType})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.line_project:
                isproject_visible = !isproject_visible;
                if (isproject_visible) {
                    line_other.setVisibility(View.VISIBLE);
                    img_move_down.setImageResource(R.mipmap.move_down);
                } else {
                    line_other.setVisibility(View.GONE);
                    img_move_down.setImageResource(R.mipmap.move_up);
                }
                break;
            case R.id.line_projectType:
                setProjectType();//设置制冷或者供暖项目类型
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
                setdatePicker(time);
                break;
            case R.id.img_lastMonth://上一天或者上一个月
                setdateLastTime(time, "up", "last");//   第二个参数代表的是哪一个模块  第三个参数last 代表上一个  next代表下一个
                break;
            case R.id.img_nextMonth:
                setdateLastTime(time, "up", "next");
                break;
            case R.id.img_lastMonthDown:
                setdateLastTime(timeTwo, "down", "last");
                break;
            case R.id.img_nextMonthDown:
                setdateLastTime(timeTwo, "down", "next");
                break;
            default:
                break;
        }
    }

    private void setProjectType() {
        if (projectTypeAdapter == null) {
            projectType = new String[]{"制冷", "供暖"};
            projectTypeAdapter = new ArrayAdapter<String>(this,
                    R.layout.popuwindow_lv_item, projectType);
        }

        CommonPopupWindow popupWindow = new CommonPopupWindow(this, R.layout.posting_listview) {
            @Override
            protected void showWindow(PopupViewHolder view) {
                view.setArrayAdapter(R.id.posting_list, projectTypeAdapter);
                view.setOnItemClickListener(R.id.posting_list, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        dismiss();
                        if (position == 0) {
                            App.sp.setProjectTypeFinish("A");
                            //  startActivity(new Intent(Group_Project_DetailsActivity.this,Group_Project_DetailsActivity.class));
                            ToastUtils.showToast(HVAC_ProjectDetailActivity.this, "已经切换到夏季制冷，重新进入");
                            finish();
                        } else if (position == 1) {
                            App.sp.setProjectTypeFinish("B");
                            //此处最好不要进行修改，之前试过好多方法都是不行，暂时先这么处理
                            ToastUtils.showToast(HVAC_ProjectDetailActivity.this, "已经切换到冬季供暖，重新进入");
                            //  startActivity(new Intent(Group_Project_DetailsActivity.this,Group_Project_DetailsActivity.class));
                            finish();
                        }
                    }
                });
            }
        };
        popupWindow.listPopup(txt_project_type_value, projectType.length);

    }

    /**
     * 显示上一个月或者下一个月  上一天或者下一天
     */
    private void setdateLastTime(int item, String whichModule, String whith) {

        switch (item) {
            case 0:

                //更新第一个模块图表时间
                if (whichModule.equals("up")) {
                    if (whith.equals("last")) {
                        App.sp.setDate(DateUtils.getSpecifiedDayBefore(App.sp.getDate()));
                    } else if (whith.equals("next")) {
                        App.sp.setDate(DateUtils.getSpecifiedDayAfter(App.sp.getDate()));
                    }
                    setPaintParams(tabPostion_position);//设置图的个数和类型
                    EventBus.getDefault().post(eventbusBean);
                    txt_notata.setText(App.sp.getDate());
                } else {
                    //更新第二张图表
                    if (whith.equals("last")) {
                        App.sp.setDateEnergy(DateUtils.getSpecifiedDayBefore(App.sp.getDateEnergy()));
                    } else if (whith.equals("next")) {
                        App.sp.setDateEnergy(DateUtils.getSpecifiedDayAfter(App.sp.getDateEnergy()));
                    }
                    setPaintParamsTwo(tabPosition_positionTwo);//设置图的个数和类型
                    EventBus.getDefault().post(eventBusDownBean);
                    txt_notataDown.setText(App.sp.getDateEnergy());
                }


                break;
            case 1:
                //更新第一个模块图表时间
                if (whichModule.equals("up")) {
                    if (whith.equals("last")) {
                        App.sp.setDate(DateUtils.getLastMonth(App.sp.getDate()));
                    } else if (whith.equals("next")) {
                        App.sp.setDate(DateUtils.getNextMonth(App.sp.getDate()));
                    }
                    setPaintParams(tabPostion_position);//设置图的个数和类型
                    EventBus.getDefault().post(eventbusBean);
                    txt_notata.setText(App.sp.getDate());
                } else {
                    //更新第二张图表
                    if (whith.equals("last")) {
                        App.sp.setDateEnergy(DateUtils.getLastMonth(App.sp.getDateEnergy()));
                    } else if (whith.equals("next")) {
                        App.sp.setDateEnergy(DateUtils.getNextMonth(App.sp.getDateEnergy()));
                    }
                    setPaintParamsTwo(tabPosition_positionTwo);//设置图的个数和类型
                    EventBus.getDefault().post(eventBusDownBean);
                    txt_notataDown.setText(App.sp.getDateEnergy());
                }
                break;
            case 2:
                //这个有问题  后台没有做 我们这也没有精细处理
                if (whichModule.equals("up")) {
                    if (whith.equals("last")) {
                        App.sp.setDate(DateUtils.getSpecifiedDayBefore(App.sp.getDate()));
                    } else if (whith.equals("next")) {
                        App.sp.setDate(DateUtils.getSpecifiedDayAfter(App.sp.getDate()));
                    }
                    setPaintParams(tabPostion_position);//设置图的个数和类型
                    EventBus.getDefault().post(eventbusBean);
                    txt_notata.setText(App.sp.getDate());
                } else {
                    //更新第二张图表
                    if (whith.equals("last")) {
                        App.sp.setDateEnergy(DateUtils.getSpecifiedDayBefore(App.sp.getDateEnergy()));
                    } else if (whith.equals("next")) {
                        App.sp.setDateEnergy(DateUtils.getSpecifiedDayAfter(App.sp.getDateEnergy()));
                    }
                    setPaintParamsTwo(tabPosition_positionTwo);//设置图的个数和类型
                    EventBus.getDefault().post(eventBusDownBean);
                    txt_notataDown.setText(App.sp.getDateEnergy());
                }
                break;
            default:
                break;
        }
    }

    private void setdatePicker(final int item) {
        if (list_diagOne != null && list_diagOne.size() > 0) {
            // 时间选择器
            TimePickerView pvTime = null;
            if (item == 0) {
                pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
            } else if (item == 1) {
                pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH);
            } else if (item == 2) {
                pvTime = new TimePickerView(this, TimePickerView.Type.YEAR);
            }
            pvTime.setTime(new Date());
            pvTime.setCyclic(false);
            pvTime.setCancelable(true);
            pvTime.setOnTimeSelectListener(date -> {
                switch (item) {
                    case 0:
                        if (getTime(date, false).length() == 10) {
                            day_rememrber = getTime(date, false);
                        }
                        App.sp.setDate(getTime(date, false));
                        App.sp.setDateEnergy(getTime(date, false));
                        //更新第一个模块图表时间
                        setPaintParams(tabPostion_position);//设置图的个数和类型
                        EventBus.getDefault().post(eventbusBean);
                        txt_notata.setText(App.sp.getDate());
                        //更新第二个模块图表时间
                        setPaintParamsTwo(tabPosition_positionTwo);
                        EventBus.getDefault().post(eventBusDownBean);
                        txt_notataDown.setText(App.sp.getDateEnergy());
                        break;
                    case 1:
                        setPaintParams(tabPostion_position);//设置图的个数和类型
                        App.sp.setDate(getTime(date, true));
                        App.sp.setDateEnergy(getTime(date, true));
                        EventBus.getDefault().post(eventbusBean);
                        txt_notata.setText(App.sp.getDate());
                        //更新第二个模块图表时间
                        setPaintParamsTwo(tabPosition_positionTwo);
                        EventBus.getDefault().post(eventBusDownBean);
                        txt_notataDown.setText(App.sp.getDateEnergy());
                        break;
                    case 2:
                        setPaintParams(tabPostion_position);//设置图的个数和类型
                        App.sp.setDate(getTime(date, true));
                        App.sp.setDateEnergy(getTime(date, true));
                        EventBus.getDefault().post(eventbusBean);
                        txt_notata.setText(App.sp.getDate());
                        //更新第二个模块图表时间
                        setPaintParamsTwo(tabPosition_positionTwo);
                        EventBus.getDefault().post(eventBusDownBean);
                        txt_notataDown.setText(App.sp.getDateEnergy());
                        break;
                    default:
                        break;
                }

            });
            pvTime.show();
        }
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
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        Intent intent = null;
        switch (group.getId()) {
            case R.id.group_time:
                if (eventbusBean != null) {

                    switch (checkedId) {

                        case R.id.radio1:
                            if (repeatTime.equals("0")) {
                                repeatTime = "1";
                                radio1.setBackgroundResource(R.mipmap.green_background_circle);
                                radio2.setBackgroundResource(R.mipmap.white_background_circle);
                                radio3.setBackgroundResource(R.mipmap.white_background_circle);
                                time = 0;//记录选择时间  弹出时间框
                                if (App.sp.getDate().equals("") || TextUtils.isEmpty(App.sp.getDate())) {
                                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                    App.sp.setDate(format.format(new Date(System.currentTimeMillis())));
                                } else {
                                    if (day_rememrber.equals("")) {
                                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                        App.sp.setDate(format.format(new Date(System.currentTimeMillis())));
                                    } else {
                                        App.sp.setDate(day_rememrber);
                                    }
                                }
                                eventbusBean.setIsMonth("0");
                                setPaintParams(tabPostion_position);//设置图的个数和类型
                                EventBus.getDefault().post(eventbusBean);
                            }
                            break;
                        case R.id.radio2:
                            repeatTime = "0";
                            radio2.setBackgroundResource(R.mipmap.green_background_circle);
                            radio1.setBackgroundResource(R.mipmap.white_background_circle);
                            radio3.setBackgroundResource(R.mipmap.white_background_circle);
                            time = 1;
                            if (App.sp.getDate().equals("") || TextUtils.isEmpty(App.sp.getDate())) {
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
                                App.sp.setDate(format.format(new Date(System.currentTimeMillis())));
                            } else {
                                String date = App.sp.getDate();
                                if (date.length() == 10) {
                                    App.sp.setDate(date.substring(0, 7));
                                }
                            }
                            eventbusBean.setIsMonth("1");
                            setPaintParams(tabPostion_position);//设置图的个数和类型
                            EventBus.getDefault().post(eventbusBean);
                            break;
                        case R.id.radio3:
                            repeatTime = "0";
                            radio3.setBackgroundResource(R.mipmap.green_background_circle);
                            radio1.setBackgroundResource(R.mipmap.white_background_circle);
                            radio2.setBackgroundResource(R.mipmap.white_background_circle);
                            time = 2;
                            if (App.sp.getDate().equals("") || TextUtils.isEmpty(App.sp.getDate())) {
                                SimpleDateFormat format = new SimpleDateFormat("yyyy");
                                App.sp.setDate(format.format(new Date(System.currentTimeMillis())));
                            } else {
                                String date = App.sp.getDate();
                                if (date.length() == 10) {
                                    App.sp.setDate(date.substring(0, 4));
                                }
                            }
                            eventbusBean.setIsMonth("2");
                            setPaintParams(tabPostion_position);//设置图的个数和类型
                            EventBus.getDefault().post(eventbusBean);
                            break;

                    }
                }
                break;
            /**能效分析*/
            case R.id.group_timeTwo:
                if (eventBusDownBean != null) {

                    switch (checkedId) {

                        case R.id.radioTwo1:
                            if (repeatTime.equals("0")) {
                                repeatTimeTwo = "1";
                                radioTwo1.setBackgroundResource(R.mipmap.green_background_circle);
                                radioTwo2.setBackgroundResource(R.mipmap.white_background_circle);
                                radioTwo3.setBackgroundResource(R.mipmap.white_background_circle);
                                timeTwo = 0;//记录选择时间  弹出时间框
                                if (App.sp.getDateEnergy().equals("") || TextUtils.isEmpty(App.sp.getDateEnergy())) {
                                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                    App.sp.setDateEnergy(format.format(new Date(System.currentTimeMillis())));
                                } else {
                                    if (day_rememrber.equals("")) {
                                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                        App.sp.setDateEnergy(format.format(new Date(System.currentTimeMillis())));
                                    } else {
                                        App.sp.setDateEnergy(day_rememrber);
                                    }
                                }
                                eventBusDownBean.setIsMonth("0");//曲线图
                                setPaintParamsTwo(tabPosition_positionTwo);//设置图的个数和类型
                                EventBus.getDefault().post(eventBusDownBean);
                            }
                            break;
                        case R.id.radioTwo2:
                            repeatTimeTwo = "0";
                            radioTwo2.setBackgroundResource(R.mipmap.green_background_circle);
                            radioTwo1.setBackgroundResource(R.mipmap.white_background_circle);
                            radioTwo3.setBackgroundResource(R.mipmap.white_background_circle);
                            timeTwo = 1;
                            if (App.sp.getDateEnergy().equals("") || TextUtils.isEmpty(App.sp.getDateEnergy())) {
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
                                App.sp.setDateEnergy(format.format(new Date(System.currentTimeMillis())));
                            } else {
                                String date = App.sp.getDateEnergy();
                                if (date.length() == 10) {
                                    App.sp.setDateEnergy(date.substring(0, 7));
                                }
                            }
                            eventBusDownBean.setIsMonth("1");//柱状图
                            setPaintParamsTwo(tabPosition_positionTwo);//设置图的个数和类型
                            EventBus.getDefault().post(eventBusDownBean);
                            break;
                        case R.id.radioTwo3:
                            repeatTimeTwo = "0";
                            radioTwo3.setBackgroundResource(R.mipmap.green_background_circle);
                            radioTwo1.setBackgroundResource(R.mipmap.white_background_circle);
                            radioTwo2.setBackgroundResource(R.mipmap.white_background_circle);
                            timeTwo = 2;
                            if (App.sp.getDateEnergy().equals("") || TextUtils.isEmpty(App.sp.getDateEnergy())) {
                                SimpleDateFormat format = new SimpleDateFormat("yyyy");
                                App.sp.setDateEnergy(format.format(new Date(System.currentTimeMillis())));
                            } else {
                                String date = App.sp.getDateEnergy();
                                if (date.length() == 10) {
                                    App.sp.setDateEnergy(date.substring(0, 4));
                                }
                            }
                            eventBusDownBean.setIsMonth("2");
                            setPaintParamsTwo(tabPosition_positionTwo);//设置图的个数和类型
                            EventBus.getDefault().post(eventBusDownBean);
                            break;

                    }
                }
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
