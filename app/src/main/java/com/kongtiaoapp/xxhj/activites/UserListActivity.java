package com.kongtiaoapp.xxhj.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.UserListAdapter;
import com.kongtiaoapp.xxhj.afinal.ReceiverAction;
import com.kongtiaoapp.xxhj.bean.getUserList;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.UserListPresenter;
import com.kongtiaoapp.xxhj.mvp.view.UserListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Luoye on 2016-9-12.
 * 说明:用户列表界面
 */
public class UserListActivity extends BaseActivity<UserListPresenter, UserListView> implements UserListView {
    @BindView(R.id.listView)
    ListView listView;
    private List<getUserList.UserList> mList = new ArrayList<>();
    private UserListAdapter adapter;
    private int type = -1;//0,值班记录  1,项目信息
    private getUserList.UserList userList1;
    private String type_which = "A";

    @Override
    protected int initContentView() {
        type = getIntent().getIntExtra("type", 0);
        String which_type = getIntent().getStringExtra("which_type");
        if (which_type != null) {
            type_which = which_type;
        }
        return R.layout.activity_user_list;
    }

    @Override
    protected void initView() {


    }

    @OnClick(R.id.iv_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;

        }
    }

    @Override
    protected void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            private Intent intent;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getUserList.UserList item = (getUserList.UserList) parent.getAdapter().getItem(position);
                switch (type) {
                    case 0://值班记录
                        intent = new Intent();
                        intent.setAction(ReceiverAction.SELECTUSER);
                        intent.putExtra("user", item);
                        setResult(RESULT_OK, intent);
                        finish();
                        break;
                    case 1://项目信息
                        intent = new Intent();
                        intent.putExtra("name", item.getUserName());
                        intent.putExtra("uid", item.getUserId());
                        // 设置结果，并进行传送
                        setResult(RESULT_OK, intent);
                        finish();
                        break;
                    case 2:
                        intent = new Intent();
                        intent.putExtra("name", item.getUserName());
                        intent.putExtra("uid", item.getUserId());
                        // 设置结果，并进行传送
                        setResult(10001, intent);
                        finish();
                        break;
                    case 3:
                        intent = new Intent();
                        intent.putExtra("user", item.getUserName());
                        // 设置结果，并进行传送
                        setResult(10002, intent);
                        finish();
                        break;
                    case 4://工程师
                        intent = new Intent();
                        intent.putExtra("user", item.getUserName());
                        intent.putExtra("uid", item.getUserId());
                        // 设置结果，并进行传送
                        setResult(10002, intent);
                        finish();
                        break;
                    case 5://调度员
                        intent = new Intent();
                        intent.putExtra("user", item.getUserName());
                        intent.putExtra("uid", item.getUserId());
                        // 设置结果，并进行传送
                        setResult(10002, intent);
                        finish();
                        break;
                    default:
                        break;
                }

            }
        });
    }

    @Override
    protected void initData() {
        adapter = new UserListAdapter(mList, mContext);
        listView.setAdapter(adapter);
        getDataForServers();
    }

    @Override
    protected UserListPresenter getPresenter() {
        return new UserListPresenter();
    }

    private void getDataForServers() {
        presenter.onResume(this, type_which);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mList != null) {
            mList.clear();
            mList = null;
        }
    }

    @Override
    public void setList(Object response) {
        getUserList info = (getUserList) response;
        mList.clear();
        List<getUserList.UserList> listUser = info.getResobj();
        if (type == 2) {
            if (userList1 == null) {
                userList1 = new getUserList.UserList();
            }
            userList1.setUserId("");
            userList1.setUserName("所有人");
            userList1.setPhone("暂无");
            listUser.add(0, userList1);
        }
        mList.addAll(listUser);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setText(Object data) {

    }
}
