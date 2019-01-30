package com.kongtiaoapp.xxhj.environments;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.EnvironmentInnerTopAdapter;
import com.kongtiaoapp.xxhj.bean.EnvironmentInnerBan;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.EnvironmentInnerPresenter;
import com.kongtiaoapp.xxhj.mvp.view.EnvironmentInnerView;
import com.kongtiaoapp.xxhj.ui.address.AssetsUtils;
import com.kongtiaoapp.xxhj.ui.address.ScreenUtils;
import com.kongtiaoapp.xxhj.ui.view.Mf_Tools;
import com.kongtiaoapp.xxhj.ui.view.horizontallistview.ListOutView;
import com.kongtiaoapp.xxhj.utils.DateUtils;
import com.kongtiaoapp.xxhj.utils.MyTablayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.kongtiaoapp.xxhj.R.id.rela_paint;

public class EnvironmentInnerActivity extends BaseActivity<EnvironmentInnerPresenter, EnvironmentInnerView> implements EnvironmentInnerView, RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.lv_environment)
    ListOutView lv_environment;
    @BindView(R.id.tab_paint)
    MyTablayout tab_paint;//滑动项
    @BindView(R.id.frame_up)
    FrameLayout frame_up;
    @BindView(rela_paint)
    RelativeLayout rela_loading;
    @BindView(R.id.graph1)
    TextView graph1;
    @BindView(R.id.graph2)
    TextView graph2;
    @BindView(R.id.graph3)
    TextView graph3;
    @BindView(R.id.graph4)
    TextView graph4;
    @BindView(R.id.graph5)
    TextView graph5;
    @BindView(R.id.graph6)
    TextView graph6;
    @BindView(R.id.graph7)
    TextView graph7;
    @BindView(R.id.graph8)
    TextView graph8;
    @BindView(R.id.graph9)
    TextView graph9;
    @BindView(R.id.txt_notata)
    TextView txt_notata;
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

    private boolean isMonth = false;
    private List<TextView> list = new ArrayList<>();
    private String type = "";
    private int whichPaint = 0;//0  代表的时   1  代表的日
    private String month;
    private String day;
    private int tabPosition;
    private String intentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_environmentinner;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        group_time.setOnCheckedChangeListener(this);
        group_time.check(R.id.radio0);
        month = DateUtils.getYM();
        day = DateUtils.getYMD();
    }

    @Override
    protected void initData() {
        getEnvironmentInnerInfo("");
    }

    @Override
    protected EnvironmentInnerPresenter getPresenter() {
        return new EnvironmentInnerPresenter();
    }

    @Override
    public void getEnvironmentInnerInfo(Object data) {
        String environmentInner = AssetsUtils.readText(this, "environmentinner.json");
        EnvironmentInnerBan bean = gson.fromJson(environmentInner, EnvironmentInnerBan.class);
        EnvironmentInnerTopAdapter adapter = new EnvironmentInnerTopAdapter(this, bean.getResobj().getGroupData());
        lv_environment.setAdapter(adapter);
    }

    @Override
    public void getEnvironmentInnerPaint(Object data) {

    }

    private void setPaint() {
        if (ScreenUtils.isScreenOriatationPortrait(this)) {
            Mf_Tools.setLayoutHeight(this, frame_up, rela_loading, 1);//根据横竖屏切换控制视图展示   竖屏
        } else {
            Mf_Tools.setLayoutHeight(this, frame_up, rela_loading, 2);//根据横竖屏切换控制视图展示  横屏

        }
        list.add(graph1);
        list.add(graph2);
        list.add(graph3);
        list.add(graph4);
        list.add(graph5);
        list.add(graph6);
        list.add(graph7);
        list.add(graph8);
        list.add(graph9);
    }

    private void setGraph(String[] titles) {
        Mf_Tools.setGraph(list, titles);
    }

    @OnClick({R.id.iv_back, R.id.img_left, R.id.img_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.img_left:
                if (whichPaint == 0) {//时

                    day = DateUtils.getSpecifiedDayBefore(day);
                    //getDataForService(day);
                } else if (whichPaint == 1) {//日
                    month = DateUtils.getLastMonth(month);
                    //getDataForService(month);
                }
                break;
            case R.id.img_right:
                if (whichPaint == 0) {//时
                    day = DateUtils.getSpecifiedDayAfter(day);
                    // getDataForService(day);
                } else if (whichPaint == 1) {//日
                    month = DateUtils.getNextMonth(month);
                    //getDataForService(month);
                }
                break;
        }
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio0:
                whichPaint = 0;//代表我点击时
                img_left.setImageResource(R.mipmap.leftarrow);
                img_right.setImageResource(R.mipmap.rightarrow);
                isMonth = false;
                radio0.setBackgroundResource(R.mipmap.green_background_circle);
                radio1.setBackgroundResource(R.mipmap.white_background_circle);
                //  getDataForService(day);

                break;
            case R.id.radio1:
                whichPaint = 1;//代表我点击日
                img_left.setImageResource(R.mipmap.arrow_double_left);
                img_right.setImageResource(R.mipmap.arrow_double_right);
                isMonth = true;
                radio0.setBackgroundResource(R.mipmap.white_background_circle);
                radio1.setBackgroundResource(R.mipmap.green_background_circle);
                //  getDataForService(month);
                break;

        }
    }
}
