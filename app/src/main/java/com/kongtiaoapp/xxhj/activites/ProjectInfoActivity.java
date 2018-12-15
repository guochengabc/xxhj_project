package com.kongtiaoapp.xxhj.activites;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.ProjectInfoAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.UserManegerList;
import com.kongtiaoapp.xxhj.bean.ProjectList;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.ProjectInfoPresenter;
import com.kongtiaoapp.xxhj.mvp.view.ProjectInfoView;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;
import com.kongtiaoapp.xxhj.utils.BaseTools;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Luoye on 2016-6-15.
 * 项目信息页面
 */
public class ProjectInfoActivity extends BaseActivity<ProjectInfoPresenter, ProjectInfoView> implements AdapterView.OnItemClickListener, ProjectInfoView {
    @BindView(R.id.listview)
    SwipeMenuListView listview;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.springView)
    SpringView springView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    private List<ProjectList.ResobjBean> mList = new ArrayList();
    private ProjectInfoAdapter adapter;
    private int currentPage = 1;
    private boolean isRefresh = true;
    //1,项目信息查看   2,项目切换

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS:
                    getDataForServers();
                    springView.onFinishFreshAndLoad();
                    break;
                case ConstantValue.JIAZAI_SUCESS:
                    getDataForServers();
                    springView.onFinishFreshAndLoad();
                    break;

                default:
                    break;
            }
        }
    };
    private boolean isdelete;
    private int delete_position;
    private String project_diag = "0";

    @Override
    protected int initContentView() {

        return R.layout.activity_project_info;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataForServers();
    }

    @Override
    protected void initView() {
        if (UserManegerList.HVAC_Manager()) {
            ivSetting.setVisibility(View.VISIBLE);
        }

        springView.setHeader(new RotationHeader(mContext));
        springView.setFooter(new RotationFooter(mContext));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Thread(() -> {
                    isRefresh = true;
                    currentPage = 1;
                    handler.sendEmptyMessage(ConstantValue.SHUAXIN_SUCESS);
                }).start();
            }

            @Override
            public void onLoadmore() {
                new Thread(() -> {
                    isRefresh = false;
                    currentPage++;
                    handler.sendEmptyMessage(ConstantValue.JIAZAI_SUCESS);
                }).start();
            }
        });

        SwipeMenuCreator creator = menu -> {
            // create "open" item
            SwipeMenuItem openItem = new SwipeMenuItem(
                    getApplicationContext());
            // set item background
            openItem.setBackground(new ColorDrawable(Color.rgb(0xFF, 0x00,
                    0x00)));
            // set item width
            openItem.setWidth(BaseTools.dip2px(mContext, 90));
            // set item title
            openItem.setTitle("删除");
            // set item title fontsize
            openItem.setTitleSize(18);
            // set item title font color
            openItem.setTitleColor(Color.WHITE);
            // add to menu
            menu.addMenuItem(openItem);

        };

        listview.setMenuCreator(creator);
        listview
                .setOnMenuItemClickListener((position, menu, index) -> {
                    ProjectList.ResobjBean item = (ProjectList.ResobjBean) adapter.getItem(position);
                    if (UserManegerList.HVAC_Operate()) {
                        ToastUtils.showToast(this, getString(R.string.project_nopermission_delete));
                        return false;
                    }
                    delete_position = position;

                    delete(item.getProjectId());
                    return false;
                });


        adapter = new ProjectInfoAdapter(mContext, mList);
        listview.setAdapter(adapter);

    }

    @Override
    protected void initListener() {
        listview.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {


    }

    @Override
    protected ProjectInfoPresenter getPresenter() {
        return new ProjectInfoPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ProjectList.ResobjBean item = (ProjectList.ResobjBean) parent.getAdapter().getItem(position);
        startActivity(new Intent(mContext, ProcjectInfoDetailActivity.class).putExtra("projectId", item.getProjectId()));
    }


    private void getDataForServers() {
        presenter.onResume(this, String.valueOf(currentPage));

    }

    /**
     * 删除记录
     */
    private void delete(String projectId) {
        presenter.delete(this, projectId);
    }

    @OnClick({R.id.iv_setting, R.id.iv_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_setting:
                Intent intent = new Intent(mContext, ProcjectInfoDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void delete_View() {
        ToastUtils.showToast(this, getString(R.string.enegry_delete_succeed));
        if (App.sp.getProjectName().equals(mList.get(delete_position).getName())) {
            App.sp.removeSp("projectName");
        }
        adapter.remove(delete_position);
    }

    @Override
    public void list_null() {
        project_diag = "1";
        ToastUtils.showToast(this, getString(R.string.no_data));
    }

    @Override
    public void setText(Object response) {
        ProjectList info = (ProjectList) response;
        if (isRefresh) {
            mList.clear();

            mList.addAll(info.getResobj());
            if (project_diag.equals("1")) {
                App.sp.setProjectName(mList.get(0).getName());
                App.sp.setProjectType(mList.get(0).getProjectType());
                App.sp.setProjectId(mList.get(0).getProjectId());
                App.sp.setUid(mList.get(0).getUserId());

                project_diag = "0";
            }
            adapter.notifyDataSetChanged();
        } else {
            if (info.getResobj() == null || info.getResobj().size() == 0) {
                ToastUtils.showToast(this, getString(R.string.no_data));
                return;
            }
            mList.addAll(info.getResobj());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        if (mList != null) {
            mList.clear();
            mList = null;
        }
    }
}
