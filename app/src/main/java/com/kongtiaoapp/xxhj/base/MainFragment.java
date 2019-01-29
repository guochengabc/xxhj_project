package com.kongtiaoapp.xxhj.base;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.AllActivityManager;
import com.kongtiaoapp.xxhj.adapter.ModuleClickAdapter;
import com.kongtiaoapp.xxhj.adapter.MyPageFragmentAdapter;
import com.kongtiaoapp.xxhj.bean.ModuleBean;
import com.kongtiaoapp.xxhj.e_code.activity.E_CodeActivity;
import com.kongtiaoapp.xxhj.environments.EnvironmentInnerActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.MainFragmentPresenter;
import com.kongtiaoapp.xxhj.mvp.view.MainFragmentView;
import com.kongtiaoapp.xxhj.utils.emoji.CommonUtil;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xxhj_g on 2017/6/20.
 */
public class MainFragment extends BaseFragment<MainFragmentPresenter, MainFragmentView> implements MainFragmentView {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.top_main_viewpager)
    ViewPager viewPager;
    @BindView(R.id.txt_main_viewpagertitle)
    TextView tex_main_viewpagertitle;
    @BindView(R.id.dots_ll)
    LinearLayout dots_ll;
    @BindView(R.id.gridView)
    GridView gridView;
    private Thread thread = null;
    private List<View> dotViewlist = new ArrayList<View>();
    private List<Integer> imageInfolist = new ArrayList<>();
    private List<String> list_title = new ArrayList<>();
    private Runnable runnable;
    private boolean flag = true;
    private boolean isFirstEnter = true;
    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    int currentItem = viewPager.getCurrentItem();
                    viewPager.setCurrentItem(currentItem + 1);
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public MainFragmentPresenter getPresenter() {
        return new MainFragmentPresenter();
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.mainfragment, null);
    }

    @Override
    public void onResume() {
        super.onResume();
        //  commitData();//断网后提交能源记录数据
        presenter.onResume(mActivity);
        if (isFirstEnter == false) {
            if (flag == false) {
                flag = true;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new Thread(runnable).start();
                    }
                }, 3510);
            }
        }
        isFirstEnter = false;
    }

    @Override
    public void initData() {
        super.initData();
        Bundle bundle = getArguments();
        if (bundle != null) {
            String intentName = bundle.getString("intentName");
            ModuleBean.ResobjBean.MainArrayBean bean = (ModuleBean.ResobjBean.MainArrayBean) bundle.getSerializable("mainFragment");
            List<ModuleBean.ResobjBean.MainArrayBean.ModuleArrayBean> moduleArray = bean.getModuleArray();
            ModuleClickAdapter moduleAdapter = new ModuleClickAdapter(mActivity, moduleArray);
            gridView.setAdapter(moduleAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ModuleBean.ResobjBean.MainArrayBean.ModuleArrayBean moduleBean = (ModuleBean.ResobjBean.MainArrayBean.ModuleArrayBean) moduleAdapter.getItem(position);
                    if (!moduleBean.getIntentName().equals("")) {
                        try {
                            ComponentName com = new ComponentName(moduleBean.getPackageName(), moduleBean.getIntentName());
                            Intent intent = new Intent();
                            if (moduleBean.getPackageName().equals("com.zchg.woa")) {
                                intent.putExtra("intentName", "xxhj");
                            }
                            intent.putExtra("title", moduleBean.getModuleName());
                            intent.setComponent(com);
                            startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            e.printStackTrace();
                            ToastUtils.showToast(mActivity, "缺少相应包文件");
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                            ToastUtils.showToast(mActivity, "请联系后台");
                        } catch (IndexOutOfBoundsException e) {
                            e.printStackTrace();
                            ToastUtils.showToast(mActivity, "请联系后台");
                        }

                    }

                }
            });
            if (!intentName.equals("")) {
                iv_back.setVisibility(View.VISIBLE);
            } else {
                iv_back.setVisibility(View.GONE);
            }
        }
        imageInfolist.add(R.mipmap.wuyeone);
        imageInfolist.add(R.mipmap.wuyetwo);
        imageInfolist.add(R.mipmap.wuyethree);
        imageInfolist.add(R.mipmap.wuyefour);
        imageInfolist.add(R.mipmap.wuyefive);
        list_title.add("我们愿贡献力量让天空更蓝");
        list_title.add("我们专注信息化、智能化、绿色化");
        list_title.add("帮助一万家物业降低运营成本");
        list_title.add("精细管理、智能控制降低成本");
        list_title.add("能源计量、能效诊断、调适改造");
        initvipdate(imageInfolist);
        setVipOnpageListerner(viewPager);
        setThread();
    }


    private void setThread() {
        runnable = new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    try {
                        Thread.sleep(3500);
                        handler.sendEmptyMessage(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        flag = false;
                    }
                }

            }
        };
        thread = new Thread(runnable);
        thread.start();
    }


    @Override
    public void setText(Object data) {

    }

    public void initvipdate(List<Integer> list1) {
        dots_ll.removeAllViews();
        dotViewlist.clear();
        for (int i = 0; i < list1.size(); i++) {

            View view = new View(getActivity());
            if (i == 0) {
                view.setBackgroundResource(R.mipmap.circle_two);
                tex_main_viewpagertitle.setText(list_title.get(i));
            } else {
                view.setBackgroundResource(R.mipmap.circle_one);
            }
            //像素
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(CommonUtil.dip2px(getContext(), 10), CommonUtil.dip2px(getContext(), 10));
            layoutParams.setMargins(5, 0, 5, 5);
            dots_ll.addView(view, layoutParams);
            dotViewlist.add(view);
        }
        MyPageFragmentAdapter adapter_vip = new MyPageFragmentAdapter(list1, getActivity());
        viewPager.setAdapter(adapter_vip);
    }

    private void setVipOnpageListerner(ViewPager vip) {
        vip.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (imageInfolist != null) {
                    for (int i = 0; i < dotViewlist.size(); i++) {
                        if (i == position % imageInfolist.size()) {
                            dots_ll.getChildAt(i).setBackgroundResource(R.mipmap.circle_two);
                            tex_main_viewpagertitle.setText(list_title.get(i));
                        } else {
                            dots_ll.getChildAt(i).setBackgroundResource(R.mipmap.circle_one);
                        }
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @OnClick({R.id.iv_back, R.id.img_eCode, R.id.tv_environment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                AllActivityManager.getInstance().finishAllActivity();
                break;
            case R.id.img_eCode://二维码扫描记录表单
                startActivity(new Intent(mActivity, E_CodeActivity.class));
                break;
            case R.id.tv_environment:
                startActivity(new Intent(mActivity, EnvironmentInnerActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        flag = false;
        Log.i(TAG, "恭喜你成功了，fragment已经暂停了");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacks(null);
        }
        if (runnable != null) {
            runnable = null;
        }
        if (list_title != null) {
            list_title.clear();
            list_title = null;
        }
        if (imageInfolist != null) {
            imageInfolist.clear();
            imageInfolist = null;
        }
        viewPager.addOnPageChangeListener(null);
    }
}
