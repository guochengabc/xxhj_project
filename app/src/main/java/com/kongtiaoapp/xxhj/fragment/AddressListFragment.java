package com.kongtiaoapp.xxhj.fragment;


import android.Manifest;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.SortModel;
import com.kongtiaoapp.xxhj.bean.UserInfoBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.UserInfoPresneter;
import com.kongtiaoapp.xxhj.mvp.view.UserInfoView;
import com.kongtiaoapp.xxhj.ui.address.ScreenUtils;
import com.kongtiaoapp.xxhj.ui.dialog.MyVersionDialog;
import com.kongtiaoapp.xxhj.ui.view.DialogPrompt;
import com.kongtiaoapp.xxhj.utils.HintDialogFragment;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;
import com.kongtiaoapp.xxhj.utils.user_contact.CharacterParser;
import com.kongtiaoapp.xxhj.utils.user_contact.PinyinComparator;
import com.kongtiaoapp.xxhj.utils.user_contact.SideBar;
import com.kongtiaoapp.xxhj.utils.user_contact.SortAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.  通讯录
 */
public class AddressListFragment extends BaseFragment<UserInfoPresneter, UserInfoView> implements UserInfoView
        , HintDialogFragment.DialogFragmentCallback {
    @BindView(R.id.country_lvcountry)
    ListView sortListView;
    @BindView(R.id.top_layout)//顶部悬浮的layout
            LinearLayout xuanfuLayout;
    @BindView(R.id.top_char)//英文字母文字  // 悬浮的文字， 和右上角的群发
            TextView xuanfaText;
    @BindView(R.id.sidrbar)//右侧显示姓氏
            SideBar sideBar;
    @BindView(R.id.dialog)//类似于吐司效果
            TextView dialog;
    @BindView(R.id.qunfa)//群发项
            TextView qunfa;
    private CharacterParser characterParser;
    private PinyinComparator pinyinComparator;
    private SortAdapter adapter; // 排序的适配器
    private int lastFirstVisibleItem = -1;
    private boolean isNeedChecked; // 是否需要出现选择的按钮
    private List<SortModel> SourceDateList = new ArrayList<>(); // 数据
    private List<UserInfoBean.ResobjBean> resobj = new ArrayList<>();
    private final int CAMERAS = 1000;
    private int positions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public UserInfoPresneter getPresenter() {
        return new UserInfoPresneter();
    }

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.activity_user_info, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        if (!ScreenUtils.isScreenOriatationPortrait(mActivity)) {
            sideBar.setVisibility(View.GONE);
        }
        characterParser = CharacterParser.getInstance();//拼音检索表
        pinyinComparator = new PinyinComparator();
        sideBar.setTextView(dialog);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            presenter.onResume(mActivity);
        }

    }

    /**
     * 只有切换的时候才会出现该方法
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "横竖屏切换" + newConfig.orientation);
        if (newConfig.orientation == 1) {//竖屏
            sideBar.setVisibility(View.VISIBLE);
        } else {//横屏
            sideBar.setVisibility(View.GONE);
        }
    }

    /**
     * 填充数据
     *
     * @param date
     * @return
     */
    private List<SortModel> filledData(List<UserInfoBean.ResobjBean> date) {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.size(); i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date.get(i).getUserName());
            sortModel.setPhone(date.get(i).getPhone());
            sortModel.setIconUrl(date.get(i).getAvatarUrl());
            sortModel.setLevelName(date.get(i).getLevelName());
            sortModel.setLevel(date.get(i).getLevel());
            sortModel.setUserId(date.get(i).getUserId());
            sortModel.setSex(i % 2);
            String pinyin = characterParser.getSelling(date.get(i).getUserName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     * 过滤数据
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (SortModel sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1
                        || characterParser.getSelling(name).startsWith(
                        filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }


    private void setdata(int position) {
        if (!isNeedChecked) {
            ToastUtils.showToast(mActivity, ((SortModel) adapter.getItem(position)).getName());
        } else {
            SourceDateList.get(position).setChecked(
                    !SourceDateList.get(position).isChecked());
            adapter.notifyDataSetChanged(); // 这样写效率很低， 以后可以改成
            // RecycleView 直接notify
            // item的状态
        }

    }


    @OnClick({R.id.qunfa})//可以进行多选按钮
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.qunfa:
                if (isNeedChecked) {
                    adapter.setNeedCheck(false);
                    isNeedChecked = false;
                } else {

                    adapter.setNeedCheck(true);
                    isNeedChecked = true;
                }
                adapter.notifyDataSetChanged();
                break;
            default:

                break;
        }
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void setList(UserInfoBean response) {
        resobj = response.getResobj();
        /**
         * 为右边添加触摸事件
         */
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }

            }
        });
        SourceDateList.clear();
        SourceDateList = filledData(resobj);// 填充数据
        Collections.sort(SourceDateList, pinyinComparator);
        if (adapter == null) {
            adapter = new SortAdapter(mActivity, SourceDateList);
            sortListView.setAdapter(adapter);
        } else {
            adapter.setList(SourceDateList);
        }


        /**
         * 设置滚动监听， 实时跟新悬浮的字母的值
         */
        sortListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                int section = adapter.getSectionForPosition(firstVisibleItem);
                int nextSecPosition = adapter
                        .getPositionForSection(section + 1);
                if (firstVisibleItem != lastFirstVisibleItem) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) xuanfuLayout.getLayoutParams();
                    params.topMargin = 0;
                    xuanfuLayout.setLayoutParams(params);
                    xuanfaText.setText(String.valueOf((char) section));
                }
                if (nextSecPosition == firstVisibleItem + 1) {
                    View childView = view.getChildAt(0);
                    if (childView != null) {
                        int titleHeight = xuanfuLayout.getHeight();
                        int bottom = childView.getBottom();
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) xuanfuLayout
                                .getLayoutParams();
                        if (bottom < titleHeight) {
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            xuanfuLayout.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                xuanfuLayout.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });
        sortListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                MyVersionDialog.Builder builder = new MyVersionDialog.Builder(mActivity);
                SortModel models = (SortModel) adapter.getItem(position);
                String phone = models.getPhone();
                if (phone.equals("13621111653")) {
                    String levels = models.getLevel();
                    Log.i(TAG, "等级水平？" + levels);
                    List<String> list = new ArrayList<String>();
                    list.add(models.getUserId());
                    if (levels != null && levels.equals("B")) {
                        builder.setTitle("确定设为操作员？");
                        list.add("C");
                    } else if (levels != null && levels.equals("C")) {
                        builder.setTitle("确定设为管理员？");
                        list.add("B");
                    }
                    builder.setTitle("确定注销该账号？");
                    builder.setPositiveButton("确定", (dialog, which) -> {

                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("phone", phone);
                        List<Map<String, String>> listPhone = new ArrayList<>();
                        listPhone.add(map);
                        presenter.deletePhone(mActivity, listPhone);
                        dialog.dismiss();
                    });
                    builder.setNegativeButton("取消",
                            (dialog, which) -> {
                                dialog.dismiss();
                            });
                    builder.create().show();
                }
                return true;//true   onitemclick无响应  要是false就会响应
            }

        });
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positions = position;
                manager_permission();
            }

            private void manager_permission() {
                if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CALL_PHONE)) {
                    setOnItemClick();
                } else {
                    Log.i(TAG, "申请权限");
                    if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity,
                            Manifest.permission.CALL_PHONE)) {
                        DialogFragment newFragment = HintDialogFragment.newInstance(R.string.hint_description_title,
                                R.string.hint_description_why_we_need_the_permission,
                                CAMERAS);
                        newFragment.show(mActivity.getFragmentManager(), HintDialogFragment.class.getSimpleName());
                    } else {
                        ActivityCompat.requestPermissions(mActivity,
                                new String[]{Manifest.permission.CALL_PHONE},
                                CAMERAS);
                    }
                }
            }


            public void onRequestPermissionsResult(int requestCode,
                                                   String permissions[], int[] grantResults) {
                switch (requestCode) {
                    case CAMERAS: {
                        if (grantResults.length > 0
                                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            setOnItemClick();
                        } else {
                            //拒绝进行相关的提醒
                            manager_permission();
                        }
                        return;
                    }
                }
            }

            private void setOnItemClick() {
                DialogPrompt.Builder dialogs = new DialogPrompt.Builder(mActivity);
                dialogs.setTitle("给" + SourceDateList.get(positions).getName() + "打电话？");
                dialogs.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 拨号：激活系统的拨号组件
                        Intent intent = new Intent(); // 意图对象：动作 + 数据
                        intent.setAction(Intent.ACTION_CALL); // 设置动作
                        Uri data = Uri.parse("tel:" + SourceDateList.get(positions).getPhone()); // 设置数据
                        intent.setData(data);
                        startActivity(intent); // 激活Activity组件
                        dialog.dismiss();
                    }
                }).create().show();
            }
        });
    }

    @Override
    public void RefreshAdapter() {
        presenter.onResume(mActivity);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void doPositiveClick(int requestCode) {
        if (CAMERAS == requestCode) {
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.CALL_PHONE},
                    CAMERAS);
        }
    }

    @Override
    public void doNegativeClick(int requestCode) {

    }
}
