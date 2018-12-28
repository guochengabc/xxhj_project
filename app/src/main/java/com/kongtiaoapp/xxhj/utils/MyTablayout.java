package com.kongtiaoapp.xxhj.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.MyFragmentAdapter;
import com.kongtiaoapp.xxhj.ui.view.DefinationViewpager;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by xxhj_g on 2017/4/17.
 */
public class MyTablayout extends TabLayout implements TabLayout.OnTabSelectedListener {
    private int tabPosition = 0;
    private int length;
    private DefinationViewpager dv_recorder;
    private TextView tv_weiBao;//添加维保，要用到该textView
    private int TabViewNumber = 4;
    private String SCROLLABLE_TAB_MIN_WIDTH = "mScrollableTabMinWidth";

    public MyTablayout(Context context) {
        super(context);
        this.setTabMode(MODE_SCROLLABLE);
        //  setTabNumber();

    }

    public MyTablayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTabMode(MODE_SCROLLABLE);
        //  setTabNumber();

    }

    public MyTablayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setTabMode(MODE_SCROLLABLE);
        //  setTabNumber();
    }

    /**
     * 设置tablayout相关参数
     */
    public void addTablayoutSetting(Context mContext, FragmentManager fragmentManager, List<Fragment> list_fragment, String[] title, DefinationViewpager dv_recorder) {

        this.length = title.length;
        if (this.length <= 3) {
            this.setTabMode(MODE_FIXED);
        } else {
            this.setTabMode(MODE_SCROLLABLE);
        }
        this.dv_recorder = dv_recorder;
        MyFragmentAdapter adapter = new MyFragmentAdapter(fragmentManager, list_fragment);
        dv_recorder.setAdapter(adapter);
        dv_recorder.setOffscreenPageLimit(length);
        dv_recorder.setNoScroll(false);
        this.setupWithViewPager(dv_recorder);
        this.setSelectedTabIndicatorColor(mContext.getResources().getColor(R.color.theme_color));
        for (int i = 0; i < list_fragment.size(); i++) {
            Tab tab = this.getTabAt(i);
            tab.setCustomView(getTabView(mContext, i, title[i]));
        }
        this.setOnTabSelectedListener(this);
        this.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(MyTablayout.this, 50, 50);
            }
        });
    }

    public void addTablayoutChanges(Context mContext, List<Fragment> list_fragment, String[] title, DefinationViewpager dv_recorder, TextView tv_weiBao) {
        this.tv_weiBao = tv_weiBao;
        this.length = title.length;
        if (this.length <= 3) {
            this.setTabMode(MODE_FIXED);
        } else {
            this.setTabMode(MODE_SCROLLABLE);
        }
        this.dv_recorder = dv_recorder;
        this.setSelectedTabIndicatorColor(mContext.getResources().getColor(R.color.theme_color));
        for (int i = 0; i < list_fragment.size(); i++) {
            Tab tab = this.getTabAt(i);
            tab.setCustomView(getTabView(mContext, i, title[i]));
        }
        this.setOnTabSelectedListener(this);
        this.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(MyTablayout.this, 50, 50);
            }
        });
    }

    public void addTablayoutChanges(Context mContext, List<Fragment> list_fragment, String[] title, DefinationViewpager dv_recorder) {
        this.length = title.length;
        if (this.length <= 3) {
            this.setTabMode(MODE_FIXED);
        } else {
            this.setTabMode(MODE_SCROLLABLE);
        }
        this.dv_recorder = dv_recorder;
        this.setSelectedTabIndicatorColor(mContext.getResources().getColor(R.color.theme_color));
        for (int i = 0; i < list_fragment.size(); i++) {
            Tab tab = this.getTabAt(i);
            tab.setCustomView(getTabView(mContext, i, title[i]));
        }
        this.setOnTabSelectedListener(this);
        this.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(MyTablayout.this, 50, 50);
            }
        });
    }

    public int getPosition() {

        return tabPosition;
    }

    @Override
    public void onTabSelected(Tab tab) {
        if (tab.getCustomView() == null) {
            return;
        }
        TextView textView = ((TextView) tab.getCustomView().findViewById(R.id.title_tv));
        textView.setTextColor(getResources().getColor(R.color.theme_color));
        textView.setSelected(true);

        tabPosition = tab.getPosition();
        if (length == 2) {
            switch (tabPosition) {
                case 0:
                    dv_recorder.setCurrentItem(0, false);
                    break;
                case 1:
                    dv_recorder.setCurrentItem(1, false);
                    break;
                default:
                    break;
            }
        } else if (length == 3) {
            switch (tabPosition) {
                case 0:
                    dv_recorder.setCurrentItem(0, false);
                    break;
                case 1:
                    dv_recorder.setCurrentItem(1, false);
                    break;
                case 2:
                    dv_recorder.setCurrentItem(2, false);
                    break;
                default:
                    break;
            }
        } else if (length == 4) {
            if (tv_weiBao == null) {
                switch (tabPosition) {
                    case 0:
                        dv_recorder.setCurrentItem(0, false);
                        break;
                    case 1:
                        dv_recorder.setCurrentItem(1, false);
                        break;
                    case 2:
                        dv_recorder.setCurrentItem(2, false);
                        break;
                    case 3:
                        dv_recorder.setCurrentItem(3, false);
                        break;
                    default:
                        break;
                }
            } else {
                tv_weiBao.setVisibility(GONE);
                switch (tabPosition) {
                    case 0:

                        dv_recorder.setCurrentItem(0, false);
                        break;
                    case 1:
                        dv_recorder.setCurrentItem(1, false);
                        break;
                    case 2:
                        dv_recorder.setCurrentItem(2, false);
                        break;
                    case 3:
                        tv_weiBao.setVisibility(VISIBLE);
                        dv_recorder.setCurrentItem(3, false);
                        break;
                    default:
                        break;
                }
            }

        } else if (length == 5) {
            switch (tabPosition) {
                case 0:
                    dv_recorder.setCurrentItem(0, false);
                    break;
                case 1:
                    dv_recorder.setCurrentItem(1, false);
                    break;
                case 2:
                    dv_recorder.setCurrentItem(2, false);
                    break;
                case 3:
                    dv_recorder.setCurrentItem(3, false);
                    break;
                case 4:
                    dv_recorder.setCurrentItem(4, false);
                    break;
                default:
                    break;
            }
        } else if (length == 6) {
            switch (tabPosition) {
                case 0:
                    dv_recorder.setCurrentItem(0, false);
                    break;
                case 1:
                    dv_recorder.setCurrentItem(1, false);
                    break;
                case 2:
                    dv_recorder.setCurrentItem(2, false);
                    break;
                case 3:
                    dv_recorder.setCurrentItem(3, false);
                    break;
                case 4:
                    dv_recorder.setCurrentItem(4, false);
                    break;
                case 5:
                    dv_recorder.setCurrentItem(5, false);
                    break;
                default:
                    break;
            }
        } else if (length == 7) {
            switch (tabPosition) {
                case 0:
                    dv_recorder.setCurrentItem(0, false);
                    break;
                case 1:
                    dv_recorder.setCurrentItem(1, false);
                    break;
                case 2:
                    dv_recorder.setCurrentItem(2, false);
                    break;
                case 3:
                    dv_recorder.setCurrentItem(3, false);
                    break;
                case 4:
                    dv_recorder.setCurrentItem(4, false);
                    break;
                case 5:
                    dv_recorder.setCurrentItem(5, false);
                    break;
                case 6:
                    dv_recorder.setCurrentItem(6, false);
                    break;
                default:
                    break;
            }
        } else if (length == 8) {
            switch (tabPosition) {
                case 0:
                    dv_recorder.setCurrentItem(0, false);
                    break;
                case 1:
                    dv_recorder.setCurrentItem(1, false);
                    break;
                case 2:
                    dv_recorder.setCurrentItem(2, false);
                    break;
                case 3:
                    dv_recorder.setCurrentItem(3, false);
                    break;
                case 4:
                    dv_recorder.setCurrentItem(4, false);
                    break;
                case 5:
                    dv_recorder.setCurrentItem(5, false);
                    break;
                case 6:
                    dv_recorder.setCurrentItem(6, false);
                    break;
                case 7:
                    dv_recorder.setCurrentItem(7, false);
                    break;
                default:
                    break;
            }
        } else if (length == 9) {
            switch (tabPosition) {
                case 0:
                    dv_recorder.setCurrentItem(0, false);
                    break;
                case 1:
                    dv_recorder.setCurrentItem(1, false);
                    break;
                case 2:
                    dv_recorder.setCurrentItem(2, false);
                    break;
                case 3:
                    dv_recorder.setCurrentItem(3, false);
                    break;
                case 4:
                    dv_recorder.setCurrentItem(4, false);
                    break;
                case 5:
                    dv_recorder.setCurrentItem(5, false);
                    break;
                case 6:
                    dv_recorder.setCurrentItem(6, false);
                    break;
                case 7:
                    dv_recorder.setCurrentItem(7, false);
                    break;
                case 8:
                    dv_recorder.setCurrentItem(8, false);
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    public void onTabUnselected(Tab tab) {
        TextView textView = ((TextView) tab.getCustomView().findViewById(R.id.title_tv));
        textView.setTextColor(getResources().getColor(R.color.a666666));
        textView.setSelected(false);
    }

    @Override
    public void onTabReselected(Tab tab) {

    }

    public View getTabView(Context context, int position, String title) {

        LayoutInflater mInflater = LayoutInflater.from(context);
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

    /**
     * 设置一屏显示tab的个数
     */
    public void setTabNumber() {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int tabMinWidth = screenWidth / 2 * TabViewNumber;
        Field field;
        try {
            field = MyTablayout.class.getDeclaredField(SCROLLABLE_TAB_MIN_WIDTH);
            field.setAccessible(true);
            field.set(this, tabMinWidth);
            LinearLayout llTab = (LinearLayout) field.get(this);
            int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, Resources.getSystem().getDisplayMetrics());
            int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, Resources.getSystem().getDisplayMetrics());

            for (int i = 0; i < llTab.getChildCount(); i++) {
                View child = llTab.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                params.leftMargin = left;
                params.rightMargin = right;
                child.setLayoutParams(params);
                child.invalidate();
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void setIndicator(TabLayout tab, int left, int right) {
        try {
            Class<?> tablayout = tab.getClass();
            Field tabStrip = tablayout.getDeclaredField("mTabStrip");
            tabStrip.setAccessible(true);
            LinearLayout ll_tab = (LinearLayout) tabStrip.get(tablayout);
            for (int i = 0; i < ll_tab.getChildCount(); i++) {
                View child = ll_tab.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                //修改两个tab的间距
                params.setMarginStart(left);
                params.setMarginEnd(right);
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();

        } catch (IllegalAccessException e) {
            e.printStackTrace();

        }
    }

}