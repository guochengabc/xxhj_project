package com.kongtiaoapp.xxhj.activites;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.ProjectManagerAdapter;
import com.kongtiaoapp.xxhj.bean.LoginBean;
import com.kongtiaoapp.xxhj.bean.ProjectListBean;
import com.kongtiaoapp.xxhj.bean.ProjectManagerBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.LoginPresenter;
import com.kongtiaoapp.xxhj.mvp.view.LoginView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProjectExchangeActivity extends BaseActivity<LoginPresenter, LoginView> implements LoginView {
    @BindView(R.id.lv_project_manager)
    ListView lv_project_manager;
    private List<ProjectManagerBean> list = new ArrayList<>();
    private String uid;
    private List<ProjectListBean.ResobjBean> listProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    protected int initContentView() {
        return R.layout.activity_project_exchange;
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void initListener() {
        lv_project_manager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String status = listProject.get(position).getStatus();
                if (status != null && status.equals("0")) {
                    String userNmae = listProject.get(position).getPhone();
                    String passWord = listProject.get(position).getPwd();
                    login(userNmae, passWord);
                } else {
                    ToastUtils.showToast(ProjectExchangeActivity.this, "您暂时切换不了项目");
                }

            }
        });
    }

    private void login(String phone, String pass) {
        List<String> list = new ArrayList<>();
        list.add(phone);
        list.add(pass);
        presenter.onResume(this, list);
    }

    @Override
    protected void initData() {
        presenter.getProjectList(this);
    }

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    @OnClick({R.id.iv_back})
    public void onCLick(View view) {
        finish();
    }

    @Override
    public void setText(Object data) {
        LoginBean loginBean = (LoginBean) data;
        uid = loginBean.getResobj().getUserId();
        if (uid != null) {
            LoginBean.ResobjBean resobj = loginBean.getResobj();
            if (resobj != null) {
                String loginType = resobj.getLoginType().toString();
                if (loginType == null) {
                    loginType = "";
                }
                App.sp.setLeader(loginType);
            }

        }
        App.sp.setToken(loginBean.getResobj().getToken());
        List<String> list_roles = loginBean.getResobj().getRoles();//权限只能列表
        String jsonUserManagerGson = gson.toJson(list_roles).toString();
        App.sp.setRole(gson.toJson(list_roles).toString());
        getUserInfo();
    }

    private void getUserInfo() {
        presenter.getUserInfo(this, uid);//获取用户信息
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getProjectList(Object data) {
        ProjectListBean bean = (ProjectListBean) data;
        listProject = bean.getResobj();
        ProjectManagerAdapter adapter = new ProjectManagerAdapter(listProject, this);
        lv_project_manager.setAdapter(adapter);
    }
}
