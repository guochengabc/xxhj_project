package com.kongtiaoapp.xxhj.activites;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
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
import com.kongtiaoapp.xxhj.adapter.Message_AreaAdapter;
import com.kongtiaoapp.xxhj.adapter.MyFragmentAdapter;
import com.kongtiaoapp.xxhj.adapter.MySplashAdapter;
import com.kongtiaoapp.xxhj.bean.CommentsGroupBean;
import com.kongtiaoapp.xxhj.bean.EnterRunning;
import com.kongtiaoapp.xxhj.bean.EventDutyBean;
import com.kongtiaoapp.xxhj.bean.EventbusBean;
import com.kongtiaoapp.xxhj.bean.Project_Detail_FirstBean;
import com.kongtiaoapp.xxhj.bean.RBResponse;
import com.kongtiaoapp.xxhj.bean.Running_SpredBean;
import com.kongtiaoapp.xxhj.duty.fragment.Duty_ProjectFragment;
import com.kongtiaoapp.xxhj.fragment.Protect_PaintFragment;
import com.kongtiaoapp.xxhj.fragment.RunningGuideFragment;
import com.kongtiaoapp.xxhj.fragment.Running_ProjectFragment;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.Group_Project_DetailPresenter;
import com.kongtiaoapp.xxhj.mvp.view.Group_Project_DetailView;
import com.kongtiaoapp.xxhj.ui.dialog.MyVersionDialog;
import com.kongtiaoapp.xxhj.ui.timeselector.activity.TimePickerView;
import com.kongtiaoapp.xxhj.ui.view.CustomViewPager;
import com.kongtiaoapp.xxhj.ui.view.DoubleButtonDialog;
import com.kongtiaoapp.xxhj.ui.view.MyExpandableListView;
import com.kongtiaoapp.xxhj.ui.view.MyScrollView;
import com.kongtiaoapp.xxhj.ui.view.ToastDialog;
import com.kongtiaoapp.xxhj.utils.BaseTools;
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

import static android.graphics.Typeface.createFromAsset;

/**
 * 2017.11.02    Para：项目详情  Create By 郭成
 */
public class Group_Project_DetailsActivity extends BaseActivity<Group_Project_DetailPresenter, Group_Project_DetailView> implements RadioGroup.OnCheckedChangeListener, Group_Project_DetailView {
    List list = new ArrayList();
    @BindView(R.id.verticalSrollView)
    MyScrollView myScrollView;
    @BindView(R.id.line_projectType)
    LinearLayout line_projectType;//项目类型
    @BindView(R.id.txt_running_evaluate_content)//整体运行评价内容
            TextView txt_running_evaluate_content;
    @BindView(R.id.txt_running_evaluate)//整体运行评价
            TextView txt_running_evaluate;
    @BindView(R.id.txt_all_enegry_content)//总能耗内容
            TextView txt_all_enegry_content;
    @BindView(R.id.txt_all_enegry)//总能耗
            TextView txt_all_enegry;
    @BindView(R.id.txt_save_enegry_content)//节约能耗内容
            TextView txt_save_enegry_content;
    @BindView(R.id.txt_save_enegry)//节约能耗
            TextView txt_save_enegry;
    @BindView(R.id.txt_unit_enegry_content)//单位功率内容
            TextView txt_unit_enegry_content;
    @BindView(R.id.txt_unit_enegry)//单位功率
            TextView txt_unit_enegry;
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
    @BindView(R.id.tab_projectinfo)
    TabLayout tab_projectinfo;
    @BindView(R.id.vpg_project_detail)
    CustomViewPager vpg_fragment;
    @BindView(R.id.radio_day)
    RadioButton radio_day;
    @BindView(R.id.radio_month)
    RadioButton radio_month;
    /*@BindView(R.id.radio_year)
    RadioButton radio_year;*/
    @BindView(R.id.group_time)
    RadioGroup group_time;
    @BindView(R.id.tab_paint)
    TabLayout tab_paint;
    @BindView(R.id.frame__paint)
    FrameLayout frame__paint;//绘图界面的图例
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
    private int tabPosition = 0;//tablayout滑动的位置
    private Duty_ProjectFragment duty_projectFragment;
    private int time = 0;
    private EventbusBean eventbusBean;
    private String day_rememrber = "";
    private String tabPostion_position = "0";//诊断图tab滑动的位置
    private String Running_Spread = "1";//0代表折叠，1代表展开
    private List<CommentsGroupBean.ResobjBean> list_comment = new ArrayList<>();
    private String toUid;
    private Message_AreaAdapter adapter;
    //更新分析图表
    private MySplashAdapter adapterAnylize;
    List<String> listAnylize = new ArrayList<>();
    private int group_position;
    private int child_position;
    private boolean ischild;
    private boolean isFirst_join = true;
    private boolean isproject_visible = false, isFist = true;
    private List list_param = new ArrayList<>();
    private List<Project_Detail_FirstBean.ResobjBean.DiagCategoryBean> list_diag = new ArrayList<>();
    private Running_SpredBean running_spredBean;
    private int tab_running_position = 0;
    private String category = "";
    private ArrayAdapter projectTypeAdapter;
    private String[] projectType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected Group_Project_DetailPresenter getPresenter() {
        return new Group_Project_DetailPresenter();
    }

    @Override
    protected int initContentView() {

        return R.layout.activity_group__project__details;
    }

    @Override
    public void initView() {
        //设置字体样式
        Typeface typeFace = createFromAsset(getAssets(), "fonts/huawencaibi.TTF");
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Noh_bold.ttf");
        txt_running_evaluate_content.setTypeface(typeFace);
        txt_running_evaluate_content.setTextColor(getResources().getColor(R.color.theme_color));
        txt_running_evaluate.setTypeface(type);
        txt_all_enegry_content.setTypeface(type);
        txt_all_enegry.setTypeface(type);
        txt_save_enegry_content.setTypeface(type);
        txt_save_enegry.setTypeface(type);
        txt_unit_enegry_content.setTypeface(type);
        txt_unit_enegry.setTypeface(type);

        adapter = new Message_AreaAdapter(list_comment, this);
        elv_project_detail.setAdapter(adapter);
        setElvListerner();
        elv_project_detail.setFocusable(false);
        initFragment();//初始化fragment
        initSetInfo();//设置值班信息  设备信息

    }

    @Override
    protected void onResume() {
        super.onResume();
        list_param.add(isFirst_join);
        if (!App.sp.getProjectTypeFinish().equals("")) {
            list_param.add(App.sp.getProjectTypeFinish());
        }
        presenter.onResumeAll(this, list_param);//第一次进入界面会调取该接口
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


    private void initCommit() {
        if (list_comment != null) {
            for (int i = 0; i < adapter.getGroupCount(); i++) {
                elv_project_detail.expandGroup(i, false);
            }
        }
    }

    @OnClick({R.id.btn_comments, R.id.txt_gp_moretalk, R.id.line_project,
            R.id.img_add_time, R.id.img_isspread, R.id.iv_back,
            R.id.txt_paint_anylize, R.id.line_enegry, R.id.txt_running_evaluate_content, R.id.txt_running_evaluate,
            R.id.line_projectType})
    public void onClick(View view) {
        switch (view.getId()) {
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
                setdatePicker(time);//0代表日   1代表月  2代表年
                break;
            case R.id.img_isspread:
                if (tab_running_position == 1) {
                    if (Running_Spread.equals("1")) {//进行展开
                        running_spredBean.setRunningIsSpred(Running_Spread);
                        Running_Spread = "0";//
                        img_isspread.setImageResource(R.mipmap.enegrydown);
                    } else if (Running_Spread.equals("0")) {//进行合并
                        running_spredBean.setRunningIsSpred(Running_Spread);
                        Running_Spread = "1";
                        img_isspread.setImageResource(R.mipmap.enegryup);
                    }
                    EventBus.getDefault().post(running_spredBean);
                }
                break;
            case R.id.txt_paint_anylize:
                getDataForservice();//获取图表分析
                break;
            case R.id.iv_back:
                BackFirst();
                break;
            case R.id.line_enegry://点击进入能耗界面
                if (!App.sp.getProjectId().equals("")) {
                    startActivity(new Intent(this, RunningRecordActivity.class).putExtra("projectId", App.sp.getProjectId() + ""));
                }

                break;
            case R.id.txt_running_evaluate_content://整体运行评价
            case R.id.txt_running_evaluate:
                // startActivity(new Intent(this, OverAllEvaluateActivity.class));
                break;
            case R.id.line_projectType:
                setProjectType();//设置制冷或者供暖项目类型
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
                            ToastUtils.showToast(Group_Project_DetailsActivity.this, "已经切换到夏季制冷，重新进入");
                            finish();
                        } else if (position == 1) {
                            App.sp.setProjectTypeFinish("B");
                            //此处最好不要进行修改，之前试过好多方法都是不行，暂时先这么处理
                            ToastUtils.showToast(Group_Project_DetailsActivity.this, "已经切换到冬季供暖，重新进入");
                            //  startActivity(new Intent(Group_Project_DetailsActivity.this,Group_Project_DetailsActivity.class));
                            finish();
                        }
                    }
                });
            }
        };
        popupWindow.listPopup(txt_project_type_value, projectType.length);

    }

    //返回运行主页
    public void BackFirst() {
        EnterRunning running = new EnterRunning();
        running.setEnterRunning("ok");
        EventBus.getDefault().post(running);
        finish();
    }

    /**
     * 获取图表分析
     */
    private void getDataForservice() {
        presenter.getPaintAnyize(this, category);
    }


    private void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame__paint, Protect_PaintFragment.getInstance()).commit();
    }

    public void initSetPaint() {
        running_spredBean = new Running_SpredBean();
        eventbusBean = new EventbusBean();

        setPaintParams("0");
        eventbusBean.setIsMonth("0");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        App.sp.setDate(format.format(new Date(System.currentTimeMillis())));
        EventBus.getDefault().post(eventbusBean);
        tab_paint.setTabMode(TabLayout.MODE_FIXED);
        final String title[] = new String[list_diag.size()];
        for (int i = 0; i < list_diag.size(); i++) {
            title[i] = list_diag.get(i).getText();
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
                        EventBus.getDefault().post(eventbusBean);
                        break;
                    case 1:
                        tabPostion_position = "1";
                        setPaintParams(tabPostion_position);
                        EventBus.getDefault().post(eventbusBean);
                        break;
                    case 2:
                        tabPostion_position = "2";
                        setPaintParams(tabPostion_position);
                        EventBus.getDefault().post(eventbusBean);
                        break;
                    case 3:
                        tabPostion_position = "3";
                        setPaintParams(tabPostion_position);
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
    protected void initListener() {
        group_time.setOnCheckedChangeListener(this);
        group_time.check(R.id.radio_day);
    }

    @Override
    protected void initData() {

    }


    private void setdatePicker(final int item) {
        if (list_diag != null && list_diag.size() > 0) {
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
                        setPaintParams(tabPostion_position);//设置图的个数和类型
                        EventBus.getDefault().post(eventbusBean);
                        break;
                    case 1:
                        setPaintParams(tabPostion_position);//设置图的个数和类型
                        App.sp.setDate(getTime(date, true));
                        EventBus.getDefault().post(eventbusBean);
                        break;
                    case 2:
                        setPaintParams(tabPostion_position);//设置图的个数和类型
                        App.sp.setDate(getTime(date, true));
                        EventBus.getDefault().post(eventbusBean);
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
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Intent intent = null;
        switch (group.getId()) {
            case R.id.group_time:
                radio_day.setBackgroundResource(R.mipmap.green_background_circle);
                radio_month.setBackgroundResource(R.mipmap.white_background_circle);
                if (eventbusBean != null) {
                    radio_day.setBackgroundResource(R.mipmap.white_background_circle);
                    switch (checkedId) {
                        case R.id.radio_day:
                            radio_day.setBackgroundResource(R.mipmap.green_background_circle);
                            time = 0;
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
                            break;

                        case R.id.radio_month:
                            radio_month.setBackgroundResource(R.mipmap.green_background_circle);
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
                        default:
                            break;
                    }
                    break;

                }
            default:
                break;

        }
    }

    private void setPaintParams(String tabPostion_position) {
        if (list_diag.get(Integer.parseInt(tabPostion_position)).getCode() != null) {
            category = list_diag.get(Integer.parseInt(tabPostion_position)).getCode();
        }
        eventbusBean.setPosition(tabPostion_position);
        eventbusBean.setPaintCount(list_diag.get(Integer.parseInt(tabPostion_position)).getChartNum());
        eventbusBean.setCode(category);
    }


    @Override
    public void setText(Object text) {
        list_param.clear();
        Project_Detail_FirstBean bean = (Project_Detail_FirstBean) text;
        presenter.onCommentList(Group_Project_DetailsActivity.this);//获取留言列表
        if (bean.getCode() == 40000) {
            Project_Detail_FirstBean.ResobjBean resobj = bean.getResobj();
            Project_Detail_FirstBean.ResobjBean.EnergyInfoBean energyInfo = resobj.getEnergyInfo();
            txt_running_evaluate_content.setText(resobj.getRunStatus());
            txt_all_enegry_content.setText(energyInfo.getTotalEnergy() + "");//总能耗
            txt_unit_enegry_content.setText(energyInfo.getUnitEnergy() + "");//单位功率
            if (!TextUtils.isEmpty(energyInfo.getCYdayEnergy())) {
                String sign_day = energyInfo.getCYdayEnergy().substring(0, 1);//得到正负号
                if (sign_day.equals("+")) {
                    txt_save_enegry.setText("上升");
                    txt_save_enegry.setTextColor(getResources().getColor(R.color.theme_color));
                    txt_save_enegry_content.setTextColor(getResources().getColor(R.color.theme_color));
                } else if (sign_day.equals("-")) {
                    txt_save_enegry.setText("下降");
                    txt_save_enegry.setTextColor(getResources().getColor(R.color.a999999));
                    txt_save_enegry_content.setTextColor(getResources().getColor(R.color.a999999));
                } else {
                    txt_save_enegry.setText("上升");
                    txt_save_enegry.setTextColor(getResources().getColor(R.color.theme_color));
                    txt_save_enegry_content.setTextColor(getResources().getColor(R.color.theme_color));
                }
            }
            txt_save_enegry_content.setText(energyInfo.getSaveEnergy().substring(1));//对比昨日
            App.sp.setProjectId("" + resobj.getProjectId());
            list_diag = resobj.getDiagCategory();

            if (list_diag != null && list_diag.size() > 0) {
                if (isFist) {
                    isFist = !isFist;
                    initSetPaint();//设置图表信息
                }
            }
            Project_Detail_FirstBean.ResobjBean.ProjectInfoBean projectInfo = resobj.getProjectInfo();
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
            Project_Detail_FirstBean.ResobjBean.DutyInfoBean dutyInfo = resobj.getDutyInfo();
            EventDutyBean dutyBean = new EventDutyBean();
            dutyBean.setUserName(dutyInfo.getUserName());
            dutyBean.setUserPhone(dutyInfo.getPhone());
            EventBus.getDefault().post(dutyBean);
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
            ToastUtils.showToast(Group_Project_DetailsActivity.this, response.getErrormsg());
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
            presenter.onCommentList(Group_Project_DetailsActivity.this);//获取留言列表
            edt_comments.setText("");
        } else {
            ToastUtils.showToast(this, getString(R.string.comments_error));
        }
    }

    @Override
    public void deleteCommitList(Object data) {
        RBResponse bean = (RBResponse) data;
        if (bean.getCode() == 40000) {
            ToastUtils.showToast(Group_Project_DetailsActivity.this, "删除成功");
            presenter.onCommentList(Group_Project_DetailsActivity.this);//获取留言列表
        } else if (bean.getCode() == 40005) {
            presenter.onCommentList(Group_Project_DetailsActivity.this);//获取留言列表
        } else {
            ToastUtils.showToast(Group_Project_DetailsActivity.this, bean.getErrormsg());
        }
    }

    ///图表分析
    @Override
    public void getPaintAnylize(Object data) {
        listAnylize.clear();
        listAnylize.add((String) data);
        if (adapterAnylize == null) {
            adapterAnylize = new MySplashAdapter(this, listAnylize);
        } else if (adapterAnylize != null) {
            adapterAnylize.notifyDataSetChanged();
        }
        MyVersionDialog.Builder builder = new MyVersionDialog.Builder(this);
        builder.setTitle("图表分析");
        builder.setMessage(adapterAnylize);
        builder.setNegativeButton("关闭",
                (dialog, which) -> {
                    dialog.dismiss();
                });
        builder.createSure(this.getResources().getColor(R.color.updatecolor)).show();

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            BackFirst();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.sp.removeSp("diag_date");
        if (list_comment != null) {
            list_comment.clear();
            list_comment = null;
        }
        if (list_diag != null) {
            list_diag.clear();
            list_diag = null;
        }
    }

}
