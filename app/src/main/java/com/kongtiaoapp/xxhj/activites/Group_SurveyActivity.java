package com.kongtiaoapp.xxhj.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.DeviceParam;
import com.kongtiaoapp.xxhj.bean.GetGroupProjectListBean;
import com.kongtiaoapp.xxhj.bean.UserInfo;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.Group_SurveyPresenter;
import com.kongtiaoapp.xxhj.mvp.view.Group_SurveyView;
import com.kongtiaoapp.xxhj.ui.address.AddressInitTask1;
import com.kongtiaoapp.xxhj.ui.view.DF_HScrollView;
import com.kongtiaoapp.xxhj.ui.view.MyLinearlayout;
import com.kongtiaoapp.xxhj.ui.view.MyPopupWindow;
import com.kongtiaoapp.xxhj.utils.PatternUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 带滑动表头与固定列的ListView
 */
public class Group_SurveyActivity extends BaseActivity<Group_SurveyPresenter, Group_SurveyView> implements Group_SurveyView {
    @BindView(R.id.scroll_list)
    ListView mListView;
    @BindView(R.id.item_scroll_title)
    DF_HScrollView headerScroll;
    @BindView(R.id.img_up0)
    ImageView img_up0;
    @BindView(R.id.img_down0)
    ImageView img_down0;
    @BindView(R.id.img_up1)
    ImageView img_up1;
    @BindView(R.id.img_down1)
    ImageView img_down1;
    @BindView(R.id.img_up2)
    ImageView img_up2;
    @BindView(R.id.img_down2)
    ImageView img_down2;
    @BindView(R.id.img_up3)
    ImageView img_up3;
    @BindView(R.id.img_down3)
    ImageView img_down3;
    @BindView(R.id.img_up4)
    ImageView img_up4;
    @BindView(R.id.img_down4)
    ImageView img_down4;
    @BindView(R.id.img_up5)
    ImageView img_up5;
    @BindView(R.id.img_down5)
    ImageView img_down5;
    @BindView(R.id.img_up6)
    ImageView img_up6;
    @BindView(R.id.img_down6)
    ImageView img_down6;
    @BindView(R.id.img_up7)
    ImageView img_up7;
    @BindView(R.id.img_down7)
    ImageView img_down7;
    @BindView(R.id.img_up8)
    ImageView img_up8;
    @BindView(R.id.img_down8)
    ImageView img_down8;
    @BindView(R.id.img_up9)
    ImageView img_up9;
    @BindView(R.id.img_down9)
    ImageView img_down9;
    @BindView(R.id.img_up10)
    ImageView img_up10;
    @BindView(R.id.img_down10)
    ImageView img_down10;
    @BindView(R.id.txt_title1)
    TextView txt_title1;
    @BindView(R.id.txt_title2)
    TextView txt_title2;
    @BindView(R.id.txt_title3)
    TextView txt_title3;
    @BindView(R.id.txt_title4)
    TextView txt_title4;
    @BindView(R.id.txt_title5)
    TextView txt_title5;
    @BindView(R.id.txt_title6)
    TextView txt_title6;
    @BindView(R.id.txt_title7)
    TextView txt_title7;
    @BindView(R.id.txt_title8)
    TextView txt_title8;
    @BindView(R.id.txt_title9)
    TextView txt_title9;
    @BindView(R.id.txt_title10)
    TextView txt_title10;
    @BindView(R.id.line_grouplist_8)
    LinearLayout line_grouplist_8;
    @BindView(R.id.line_grouplist_9)
    LinearLayout line_grouplist_9;
    @BindView(R.id.line_grouplist_10)
    LinearLayout line_grouplist_10;

    @BindView(R.id.line_first)
    LinearLayout line_first;
    @BindView(R.id.txt_nodata)
    TextView txt_nodata;
    @BindView(R.id.dl_grouplist)
    DrawerLayout dl_grouplist;
    @BindView(R.id.txt_search_city)
    public TextView txt_search_city;//代表的是选择的城市
    @BindView(R.id.txt_search_buidldtype)
    TextView txt_search_buidldtype;//建筑物类型
    @BindView(R.id.txt_search_uec_min)
    EditText txt_search_uec_min;//单位能耗的最小值
    @BindView(R.id.txt_search_uec_max)
    EditText txt_search_uec_max;//最大值
    @BindView(R.id.txt_search_cop_min)
    EditText txt_search_cop_min;//系统COP最小值
    @BindView(R.id.txt_search_cop_max)
    EditText txt_search_cop_max;//最大值
    @BindView(R.id.txt_search_temp_min)
    EditText txt_search_temp_min;//供回水温差最小值
    @BindView(R.id.txt_search_temp_max)
    EditText txt_search_temp_max;//最大值
    @BindView(R.id.line_enable)
    MyLinearlayout line_enable;//当抽屉打开后失去焦点
    @BindView(R.id.img_city_down)
    public ImageView img_city_down;
    @BindView(R.id.img_buildtype_down)
    ImageView img_buildtype_down;
    private boolean click_0 = false, click_1 = true, click_2 = true, click_3 = true, click_4 = true, click_5 = true, click_6 = true, click_7 = true, click_8 = true, click_9 = true, click_10 = true, isopen = true, isFist = true;
    //方便测试，直接写的public
    public HorizontalScrollView mTouchView;
    private int click_position = 0;
    //装入所有的HScrollView
    protected List<DF_HScrollView> mHScrollViews = new ArrayList<DF_HScrollView>();
    private List<GetGroupProjectListBean.ResobjBean.ParamBean> list_params = new ArrayList<>();
    private List<GetGroupProjectListBean.ResobjBean.DataBean> list_data = new ArrayList<>();
    private List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
    private List<TextView> list_text = new ArrayList<>();
    private String sortParams = "", sortType = "", search_sure = "", isContinue = "";//代表第一次进入   1代表不是第一次了   continue判断检索是否还走下去
    private SimpleAdapter adapter;
    private UserInfo.ResobjBean entity;
    public String city;
    private Map map_filter;
    private List<ImageView> list_image = new ArrayList<>();
    List<DeviceParam.EnumValue> enumValue = new ArrayList<>();
    private Map<String, String> buildingTypeMap = new ArrayMap<String, String>() {{
        put("A", "行政办公建筑");
        put("B", "文教建筑");
        put("C", "托教建筑");
        put("D", "科研建筑");
        put("E", "医疗建筑");
        put("F", "商业建筑");
        put("G", "观览建筑");
        put("H", "体育建筑");
        put("I", "旅馆建筑");
        put("J", "交通建筑");
        put("K", "通信广播建筑");
        put("L", "公寓、住宅");
    }};
    private String projectType = "A";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    protected int initContentView() {
        return R.layout.activity_group_survey;
    }

    @Override
    protected void initView() {
        enumValue.clear();
        DeviceParam data = new DeviceParam();
        for (String key : buildingTypeMap.keySet()) {
            DeviceParam.EnumValue item = data.new EnumValue();
            item.setCode(key);
            item.setValue(buildingTypeMap.get(key));
            enumValue.add(item);
        }
    }

    @Override
    protected void initListener() {
        setDrawLiserner();//设置抽屉布局的开关情况
    }

    private void setDrawLiserner() {
        dl_grouplist.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                line_enable.getFocus(true);
                if (click_position % 2 == 0) {
                    list_image.get(click_position).setImageResource(R.mipmap.groups_up_red);
                } else if (click_position % 2 == 1) {
                    list_image.get(click_position).setImageResource(R.mipmap.groups_down_red);
                }
                isopen = false;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                line_enable.getFocus(false);
                if (click_position % 2 == 0) {
                    list_image.get(click_position).setImageResource(R.mipmap.groups_up_red);
                } else if (click_position % 2 == 1) {
                    list_image.get(click_position).setImageResource(R.mipmap.groups_down_red);
                }
                isopen = true;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    protected void initData() {
        //处理一些业务逻辑
        entity = (UserInfo.ResobjBean) getIntent().getSerializableExtra("user");
        //添加头滑动事件
        mHScrollViews.add(headerScroll);
        img_up0.setImageResource(R.mipmap.groups_up_red);
        getDataForService();
        //本地读取json文件
       /* String json = AssetsUtils.readText(this, "group_list.json");
        Log.i(TAG, "json字符串=====" + json);
        GetGroupProjectListBean beans = JSON.parseObject(json, GetGroupProjectListBean.class);
        list_params = beans.getResobj().getParam();
        list_data = beans.getResobj().getData();
        setViewLayout();*/
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (list_data != null && !list_data.isEmpty()) {

                    Intent intent = new Intent(Group_SurveyActivity.this, Group_Project_DetailsActivity.class);
                    intent.putExtra("projectId", list_data.get(i).getProjectId());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected Group_SurveyPresenter getPresenter() {
        return new Group_SurveyPresenter();
    }


    private void getDataForService() {
        List<String> list = new ArrayList<>();
        list.add(sortType);
        list.add(sortParams);
        list.add(map_filter + "");
        presenter.onResume(this, list);
    }

    private void setViewLayout() {

        if (!projectType.equals(App.sp.getpType())) {
            if (!App.sp.equals("C")) {//判断是不是第一次进行缓存
                isFist = true;
                line_grouplist_8.setVisibility(View.VISIBLE);
                line_grouplist_9.setVisibility(View.VISIBLE);
                line_grouplist_10.setVisibility(View.VISIBLE);
            }
        }
        if (isFist) {
            setViewTlist();//把参数view放到list里面
            isFist = false;
        }
        for (int i = 0; i < list_params.size(); i++) {//参数名的个数
            list_text.get(i).setText(list_params.get(i).getText());
        }
        Map<String, String> data = null;
        if (list_data.size() > 0 && list_params.size() > 0) {
            for (int i = 0; i < list_data.size(); i++) {
                data = new HashMap<String, String>();
                data.put("title", list_data.get(i).getOrgName().toString());
                for (int j = 0; j < list_params.size(); j++) {
                    data.put("move_" + (j + 1), list_data.get(i).getArr().get(j));
                }
                datas.add(data);
            }
            if (!projectType.equals(App.sp.getpType())) {//冬季和夏季交换的临界点
                if (!App.sp.getpType().equals("C")) {
                    adapter = null;
                }
            }
            if (adapter == null) {
                if (projectType.equals("A")) {
                    adapter = new ScrollAdapter(this, datas, R.layout.group_item_winter
                            , new String[]{"title", "move_1", "move_2", "move_3", "move_4", "move_5", "move_6", "move_7", "move_8", "move_9", "move_10"}
                            , new int[]{R.id.item_title
                            , R.id.item_move1
                            , R.id.item_move2
                            , R.id.item_move3
                            , R.id.item_move4
                            , R.id.item_move5
                            , R.id.item_move6
                            , R.id.item_move7
                            , R.id.item_move8
                            , R.id.item_move9
                            , R.id.item_move10});

                } else if (projectType.equals("B")) {
                    adapter = new ScrollAdapter(this, datas, R.layout.group_item
                            , new String[]{"title", "move_1", "move_2", "move_3", "move_4", "move_5", "move_6", "move_7"}
                            , new int[]{R.id.item_title
                            , R.id.item_move1
                            , R.id.item_move2
                            , R.id.item_move3
                            , R.id.item_move4
                            , R.id.item_move5
                            , R.id.item_move6
                            , R.id.item_move7});
                }
                mListView.setAdapter(adapter);

            }
            adapter.notifyDataSetChanged();


        }
        App.sp.setpType(projectType);


    }

    private void setViewTlist() {
        list_text.clear();
        list_image.clear();
        list_text.add(txt_title1);
        list_text.add(txt_title2);
        list_text.add(txt_title3);
        list_text.add(txt_title4);
        list_text.add(txt_title5);
        list_text.add(txt_title6);
        list_text.add(txt_title7);
        list_image.add(img_up0);
        list_image.add(img_down0);
        list_image.add(img_up1);
        list_image.add(img_down1);
        list_image.add(img_up2);
        list_image.add(img_down2);
        list_image.add(img_up3);
        list_image.add(img_down3);
        list_image.add(img_up4);
        list_image.add(img_down4);
        list_image.add(img_up5);
        list_image.add(img_down5);
        list_image.add(img_up6);
        list_image.add(img_down6);
        list_image.add(img_up7);
        list_image.add(img_down7);
        if (projectType.equals("A")) {
            line_grouplist_8.setVisibility(View.VISIBLE);
            line_grouplist_9.setVisibility(View.VISIBLE);
            line_grouplist_10.setVisibility(View.VISIBLE);
            list_image.add(img_up8);
            list_image.add(img_down8);
            list_image.add(img_up9);
            list_image.add(img_down9);
            list_image.add(img_up10);
            list_image.add(img_down10);
            list_text.add(txt_title8);
            list_text.add(txt_title9);
            list_text.add(txt_title10);
        }
    }

    public void addHViews(final DF_HScrollView hScrollView) {
        if (!mHScrollViews.isEmpty()) {
            int size = mHScrollViews.size();
            DF_HScrollView scrollView = mHScrollViews.get(size - 1);
            final int scrollX = scrollView.getScrollX();
            //第一次满屏后，向下滑动，有一条数据在开始时未加入
            if (scrollX != 0) {
                mListView.post(new Runnable() {
                    @Override
                    public void run() {
                        //当listView刷新完成之后，把该条移动到最终位置
                        hScrollView.scrollTo(scrollX, 0);
                    }
                });
            }
        }
        mHScrollViews.add(hScrollView);
    }

    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        for (DF_HScrollView scrollView : mHScrollViews) {
            //防止重复滑动
            if (mTouchView != scrollView)
                scrollView.smoothScrollTo(l, t);
        }
    }

    @Override
    public void setList(Object data) {
        GetGroupProjectListBean bean= (GetGroupProjectListBean) data;
        line_first.setVisibility(View.VISIBLE);
        txt_nodata.setVisibility(View.GONE);
        list_params.clear();
        list_data.clear();
        datas.clear();
        GetGroupProjectListBean.ResobjBean res = bean.getResobj();
        projectType = res.getProjectType();
        list_params = res.getParam();
        list_data = res.getData();
        if (list_data.isEmpty()) {
            adapter.notifyDataSetChanged();
            txt_nodata.setVisibility(View.VISIBLE);
            return;
        }
        setViewLayout();//对数据进行布局
    }

    @Override
    public void closeDrawer() {
        if (search_sure.equals("sure")) {
            dl_grouplist.closeDrawer(GravityCompat.END);//关闭右边的抽屉
        }
        search_sure = "no";
    }

    @Override
    public void setText(Object text) {
        txt_nodata.setVisibility(View.VISIBLE);
    }

    class ScrollAdapter extends SimpleAdapter {
        private List<? extends Map<String, ?>> datas;
        private int res;
        private String[] from;
        private int[] to;
        private Context context;

        public ScrollAdapter(Context context,
                             List<? extends Map<String, ?>> data, int resource,
                             String[] from, int[] to) {
            super(context, data, resource, from, to);
            this.context = context;
            this.datas = data;
            this.res = resource;
            this.from = from;
            this.to = to;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(res, null);
                //要记住关键点:第一次初始化的时候装进来
                addHViews((DF_HScrollView) convertView.findViewById(R.id.item_scroll));
                View[] views = new View[to.length];
                for (int i = 0; i < to.length; i++) {
                    View tv = convertView.findViewById(to[i]);
                    //  tv.setOnClickListener(clickListener);
                    views[i] = tv;
                }
                convertView.setTag(views);
            }
            View[] holders = (View[]) convertView.getTag();
            int len = holders.length;
            for (int i = 0; i < len; i++) {
                ((TextView) holders[i]).setText(this.datas.get(position).get(from[i]).toString());
            }
            return convertView;
        }
     /*   //测试点击的事件
        protected View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   ToastUtils.showToast(Group_SurveyActivity.this,"position==="+datas.get);

              *//*  Intent intent = new Intent(Group_SurveyActivity.this, Group_Project_DetailsActivity.class);
                startActivity(intent);*//*
            }
        };
*/
    }

    @OnClick({R.id.txt_search_sure, R.id.txt_search_reset, R.id.txt_search_buidldtype, R.id.txt_search_city, R.id.iv_back, R.id.iv_search, R.id.line_grouplist_0, R.id.line_grouplist_1, R.id.line_grouplist_2, R.id.line_grouplist_3, R.id.line_grouplist_4, R.id.line_grouplist_5, R.id.line_grouplist_6, R.id.line_grouplist_7, R.id.line_grouplist_8, R.id.line_grouplist_9, R.id.line_grouplist_10})
    public void onClick(View view) {
        settranglecolor();//恢复三角形本来颜色
        switch (view.getId()) {

            case R.id.txt_search_sure:
                search_sure = "sure";
                //拼接json串
                isContinue = "true";
                splitString();
                break;
            case R.id.txt_search_reset:
                setAll();
                break;
            case R.id.txt_search_city:
                img_city_down.setImageResource(R.mipmap.groups_up);
                new AddressInitTask1(this, true, "1").execute("北京", "北京");
                break;
            case R.id.txt_search_buidldtype:
                img_buildtype_down.setImageResource(R.mipmap.groups_up);
                showSortPopup(txt_search_buidldtype, enumValue);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_search:
                if (isopen) {
                    dl_grouplist.openDrawer(GravityCompat.END);
                } else {
                    dl_grouplist.closeDrawer(GravityCompat.END);
                }

                break;
            case R.id.line_grouplist_0:
                sortParams = "OrgName";
                if (click_0) {
                    click_position = 0;
                    setimg_src(img_down0, img_up0, click_0, 10, "ASC");//ASC代表升序   DESC代表降序
                    click_0 = false;
                    getDataForService();
                    return;
                } else {
                    click_position = 1;
                    setimg_src(img_down0, img_up0, click_0, 10, "DESC");
                    click_0 = true;

                }
                getDataForService();
                break;
            case R.id.line_grouplist_1:
                if (list_params != null) {
                    sortParams = list_params.get(0).getCode();

                    if (click_1) {
                        click_position = 2;
                        setimg_src(img_down1, img_up1, click_1, 0, "ASC");//ASC代表升序   DESC代表降序
                        click_1 = false;
                        getDataForService();
                        return;
                    } else {
                        click_position = 3;
                        setimg_src(img_down1, img_up1, click_1, 0, "DESC");
                        click_1 = true;

                    }
                    getDataForService();
                }
                break;
            case R.id.line_grouplist_2:

                if (click_2) {
                    click_position = 4;
                    setimg_src(img_down2, img_up2, click_2, 1, "ASC");
                    click_2 = false;
                    getDataForService();
                    return;
                } else {
                    click_position = 5;
                    setimg_src(img_down2, img_up2, click_2, 1, "DESC");
                    click_2 = true;
                }
                getDataForService();
                break;
            case R.id.line_grouplist_3:


                if (click_3) {
                    click_position = 6;
                    setimg_src(img_down3, img_up3, click_3, 2, "ASC");
                    click_3 = false;
                    getDataForService();
                    return;

                } else {
                    click_position = 7;
                    setimg_src(img_down3, img_up3, click_3, 2, "DESC");
                    click_3 = true;
                }
                getDataForService();
                break;
            case R.id.line_grouplist_4:

                if (click_4) {
                    click_position = 8;
                    setimg_src(img_down4, img_up4, click_4, 3, "ASC");
                    click_4 = false;
                    getDataForService();
                    return;
                } else {
                    click_position = 9;
                    setimg_src(img_down4, img_up4, click_4, 3, "DESC");
                    click_4 = true;
                }
                getDataForService();
                break;
            case R.id.line_grouplist_5:
                if (click_5) {
                    click_position = 10;
                    setimg_src(img_down5, img_up5, click_5, 4, "ASC");
                    click_5 = false;
                    getDataForService();
                    return;
                } else {
                    click_position = 11;
                    setimg_src(img_down5, img_up5, click_5, 4, "DESC");
                    click_5 = true;
                }
                getDataForService();
                break;
            case R.id.line_grouplist_6:

                if (click_6) {
                    click_position = 12;
                    setimg_src(img_down6, img_up6, click_6, 5, "ASC");
                    click_6 = false;
                    getDataForService();
                    return;
                } else {
                    click_position = 13;
                    setimg_src(img_down6, img_up6, click_6, 5, "DESC");
                    click_6 = true;
                }
                getDataForService();
                break;
            case R.id.line_grouplist_7:

                if (click_7) {
                    click_position = 14;
                    setimg_src(img_down7, img_up7, click_7, 6, "ASC");
                    click_7 = false;
                    getDataForService();
                    return;
                } else {
                    click_position = 15;
                    setimg_src(img_down7, img_up7, click_7, 6, "DESC");
                    click_7 = true;
                }
                getDataForService();
                break;
            case R.id.line_grouplist_8:
                if (click_8) {
                    click_position = 16;
                    setimg_src(img_down8, img_up8, click_8, 7, "ASC");
                    click_8 = false;
                    getDataForService();
                    return;
                } else {
                    click_position = 17;
                    setimg_src(img_down8, img_up8, click_8, 7, "DESC");
                    click_8 = true;
                }
                getDataForService();
                break;
            case R.id.line_grouplist_9:

                if (click_9) {
                    click_position = 18;
                    setimg_src(img_down9, img_up9, click_9, 8, "ASC");
                    click_9 = false;
                    getDataForService();
                    return;
                } else {
                    click_position = 19;
                    setimg_src(img_down9, img_up9, click_9, 8, "DESC");
                    click_9 = true;
                }
                getDataForService();
                break;
            case R.id.line_grouplist_10:

                if (click_10) {
                    click_position = 20;
                    setimg_src(img_down10, img_up10, click_10, 9, "ASC");
                    click_10 = false;
                    getDataForService();
                    return;
                } else {
                    click_position = 21;
                    setimg_src(img_down10, img_up10, click_10, 9, "DESC");
                    click_10 = true;
                }
                getDataForService();
                break;
            default:
                break;
        }

    }

    private void setAll() {
        txt_search_buidldtype.setText("建筑物类型");
        txt_search_buidldtype.setTag("");
        txt_search_city.setText("城市");
        img_buildtype_down.setImageResource(R.mipmap.groups_down);
        img_city_down.setImageResource(R.mipmap.groups_down);
        city = "";

        if (map_filter != null) {
            map_filter.clear();
            map_filter = null;
        }
        if (!TextUtils.isEmpty(txt_search_uec_min.getText().toString())) {
            txt_search_uec_min.getText().clear();
        }
        if (!TextUtils.isEmpty(txt_search_uec_max.getText().toString())) {
            txt_search_uec_max.getText().clear();
        }
        if (!TextUtils.isEmpty(txt_search_cop_min.getText().toString())) {
            txt_search_cop_min.getText().clear();
        }
        if (!TextUtils.isEmpty(txt_search_cop_max.getText().toString())) {
            txt_search_cop_max.getText().clear();
        }
        if (!TextUtils.isEmpty(txt_search_temp_min.getText().toString())) {
            txt_search_temp_min.getText().clear();
        }
        if (!TextUtils.isEmpty(txt_search_temp_max.getText().toString())) {
            txt_search_temp_max.getText().clear();
        }
    }

    private void splitString() {
        map_filter = new HashMap();
        if (!TextUtils.isEmpty(txt_search_uec_min.getText().toString()) || !TextUtils.isEmpty(txt_search_uec_max.getText().toString())) {
            Map map_u = new HashMap();
            if (!TextUtils.isEmpty(txt_search_uec_min.getText().toString())) {
                //判断正则
                judge_regular(map_u, txt_search_uec_min.getText().toString(), "Min");
            }
            if (!TextUtils.isEmpty(txt_search_uec_max.getText().toString())) {
                judge_regular(map_u, txt_search_uec_max.getText().toString(), "Max");
            }
            map_filter.put("UEC", map_u);

        }
        if (!TextUtils.isEmpty(txt_search_cop_min.getText().toString()) || !TextUtils.isEmpty(txt_search_cop_max.getText().toString())) {
            Map map_s = new HashMap();
            if (!TextUtils.isEmpty(txt_search_cop_min.getText().toString())) {
                judge_regular(map_s, txt_search_cop_min.getText().toString(), "Min");
            }
            if (!TextUtils.isEmpty(txt_search_cop_max.getText().toString())) {
                judge_regular(map_s, txt_search_cop_max.getText().toString(), "Max");
            }
            map_filter.put("SCOP", map_s);

        }
        if (!TextUtils.isEmpty(txt_search_temp_min.getText().toString()) || !TextUtils.isEmpty(txt_search_temp_max.getText().toString())) {
            Map map_t = new HashMap();
            if (!TextUtils.isEmpty(txt_search_temp_min.getText().toString())) {
                judge_regular(map_t, txt_search_temp_min.getText().toString(), "Min");
            }
            if (!TextUtils.isEmpty(txt_search_temp_max.getText().toString())) {
                judge_regular(map_t, txt_search_temp_max.getText().toString(), "Max");
            }
            map_filter.put("TempD", map_t);
        }
        if (!TextUtils.isEmpty(city)) {
            map_filter.put("City", city);
        }
        if (!TextUtils.isEmpty(txt_search_buidldtype.getTag() != null ? txt_search_buidldtype.getTag().toString() : null)) {
            map_filter.put("BuildingType", txt_search_buidldtype.getTag());
        }
        if (isContinue.equals("true")) {
            getDataForService();
        }
    }

    private void judge_regular(Map map, String content, String key) {
        if (Pattern.compile(PatternUtils.getDigital()).matcher(content).matches()) {
            map.put(key, content);
        } else {
            ToastUtils.showToast(this, "参数不合法");
            isContinue = "false";
        }
    }

    private MyPopupWindow popupWindow;

    private void showSortPopup(final TextView tv, final List<DeviceParam.EnumValue> list) {
        if (popupWindow == null) {
            popupWindow = new MyPopupWindow(mContext, getWindow());
        }
        //设置宽高
        popupWindow.setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置数据源
        popupWindow.setList(list);
        //设置监听
        popupWindow.setListener((adapterView, view, position, id) -> {
            tv.setText(list.get(position).getValue());
            tv.setTag(list.get(position).getCode());
            img_buildtype_down.setImageResource(R.mipmap.groups_down);
            popupWindow.dismiss();
        });
        //初始化页面，必须写在后面
        popupWindow.initView();
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(() -> img_buildtype_down.setImageResource(R.mipmap.groups_down));
    }

    private void settranglecolor() {
        img_down0.setImageResource(R.mipmap.groups_down);
        img_down1.setImageResource(R.mipmap.groups_down);
        img_down2.setImageResource(R.mipmap.groups_down);
        img_down3.setImageResource(R.mipmap.groups_down);
        img_down4.setImageResource(R.mipmap.groups_down);
        img_down5.setImageResource(R.mipmap.groups_down);
        img_down6.setImageResource(R.mipmap.groups_down);
        img_down7.setImageResource(R.mipmap.groups_down);
        img_up0.setImageResource(R.mipmap.groups_up);
        img_up1.setImageResource(R.mipmap.groups_up);
        img_up2.setImageResource(R.mipmap.groups_up);
        img_up3.setImageResource(R.mipmap.groups_up);
        img_up4.setImageResource(R.mipmap.groups_up);
        img_up5.setImageResource(R.mipmap.groups_up);
        img_up6.setImageResource(R.mipmap.groups_up);
        img_up7.setImageResource(R.mipmap.groups_up);
        if (projectType.equals("A")) {
            img_down8.setImageResource(R.mipmap.groups_down);
            img_down9.setImageResource(R.mipmap.groups_down);
            img_down10.setImageResource(R.mipmap.groups_down);
            img_up8.setImageResource(R.mipmap.groups_up);
            img_up9.setImageResource(R.mipmap.groups_up);
            img_up10.setImageResource(R.mipmap.groups_up);
        }
    }

    //第一个参数降序的图标状态，2，升序状态，3.是否在这个参数上点击了2次，4.是降序还是升序
    private void setimg_src(ImageView img_down, ImageView img_up, boolean aclicks, int index, String sort_type) {
        if (sort_type.equals("DESC")) {
            img_down.setImageResource(R.mipmap.groups_down_red);
            img_up.setImageResource(R.mipmap.groups_up);
            sortType = "DESC";
            click_0 = true;
            click_1 = true;
            click_2 = true;
            click_3 = true;
            click_4 = true;
            click_5 = true;
            click_6 = true;
            click_7 = true;
            click_8 = true;
            click_9 = true;
            click_10 = true;
        } else if (sort_type.equals("ASC")) {
            img_down.setImageResource(R.mipmap.groups_down);
            img_up.setImageResource(R.mipmap.groups_up_red);
            sortType = "ASC";
        }
        if (index <= 9) {//index=10时，代表的是最左侧项目列表
            sortParams = list_params.get(index).getCode();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (list_params != null) {
            list_params.clear();
            list_params = null;
        }
        if (list_data != null) {
            list_data.clear();
            list_data = null;
        }
        if (list_image != null) {
            list_image.clear();
            list_image = null;
        }
        if (enumValue != null) {
            enumValue.clear();
            enumValue = null;
        }
        if (buildingTypeMap != null) {
            buildingTypeMap.clear();
            buildingTypeMap = null;
        }
        if (dl_grouplist.isDrawerOpen(GravityCompat.END)) {
            dl_grouplist.closeDrawers();
            dl_grouplist = null;
        }
    }
}