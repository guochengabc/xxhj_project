package com.kongtiaoapp.xxhj.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.ChangeShiftsActivity;
import com.kongtiaoapp.xxhj.activites.MyPublishActivity;
import com.kongtiaoapp.xxhj.activites.MyZhenduanActivity;
import com.kongtiaoapp.xxhj.activites.PersonalInfoActivity;
import com.kongtiaoapp.xxhj.activites.ProjectExchangeActivity;
import com.kongtiaoapp.xxhj.activites.Running_RecordActivity;
import com.kongtiaoapp.xxhj.activites.SettingActivity;
import com.kongtiaoapp.xxhj.activites.WeiBaoRecordActivity;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.UserInfo;
import com.kongtiaoapp.xxhj.energymetering.activity.EnergyRecordListActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.MePresenter;
import com.kongtiaoapp.xxhj.mvp.view.MeView;
import com.kongtiaoapp.xxhj.prefessionalknowledge.activity.MyFavoriteActivity;
import com.kongtiaoapp.xxhj.ui.view.DialogPrompt;
import com.kongtiaoapp.xxhj.utils.GlideCircleTransform;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * "我"的界面Framgent
 * Created by Administrator on 2016-6-8.
 */
public class MeFragment extends BaseFragment<MePresenter, MeView> implements MeView {
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.ll_head)
    LinearLayout llHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_hobby)
    TextView tvHobby;
    @BindView(R.id.tv_my_favorite)
    TextView tvMyFavorite;
    @BindView(R.id.tv_my_favorite_num)
    TextView tvMyFavoriteNum;
    @BindView(R.id.tv_my_moments)
    TextView tvMyMoments;
    @BindView(R.id.tv_my_moments_num)
    TextView tvMyMomentsNum;
    @BindView(R.id.rl_head_layout)
    RelativeLayout rlHeadLayout;
    @BindView(R.id.rl_service_record)
    RelativeLayout rlServiceRecord;
    @BindView(R.id.rl_diagnosis_record)
    RelativeLayout rlDiagnosisRecord;
    @BindView(R.id.rl_run_record)
    RelativeLayout rl_run_record;
    @BindView(R.id.rl_project_exchange)
    RelativeLayout rl_project_exchange;//查看项目
    @BindView(R.id.txt_visible_project)
    TextView txt_visible_project;
    @BindView(R.id.rl_projectTest)
    RelativeLayout rl_projectTest;
    @BindView(R.id.txt_projectTest)
    TextView txt_projectTest;
    private UserInfo.ResobjBean info;
    private String isFirst = "true";

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_me, null);
        return view;
    }

    @Override
    public void initData() {
        String phone = App.sp.getPhone();

        if (App.sp.isLeader().equals("AB") || App.sp.isLeader().equals("AC")) {
            rlServiceRecord.setVisibility(View.GONE);
            rlDiagnosisRecord.setVisibility(View.GONE);
            rl_run_record.setVisibility(View.GONE);
        }
        if (phone.equals("18001010101") || phone.equals("18001010102") || phone.equals("18001010103") || phone.equals("18001010109")
                ||phone.equals("15001010101") || phone.equals("15001010102") || phone.equals("15001010103") || phone.equals("15001010104")) {
            rl_projectTest.setVisibility(View.VISIBLE);
            txt_projectTest.setVisibility(View.VISIBLE);
        }
        getDataForServies();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (App.sp.getRefresh().equals("yes") && !isFirst.equals("true")) {//不是第一次进入并且提示刷新
            isFirst = "ok";
            getDataForServies();
        }
    }

    private void getDataForServies() {
        presenter.onResume(mActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public MePresenter getPresenter() {
        return new MePresenter();
    }

    @OnClick({R.id.rl_run_record, R.id.iv_setting, R.id.tv_my_favorite, R.id.tv_my_favorite_num,
            R.id.rl_myPublish, R.id.rl_eCode, R.id.rl_service_record,
            R.id.rl_diagnosis_record, R.id.iv_head,
            R.id.rl_project_exchange, R.id.rl_esign, R.id.txt_quit, R.id.rl_projectTest})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_setting://设置
                startActivity(new Intent(App.application, SettingActivity.class));
                break;
            case R.id.tv_my_favorite://我的收藏
            case R.id.tv_my_favorite_num://我的收藏
                startActivity(new Intent(App.application, MyFavoriteActivity.class));
                break;
            case R.id.rl_myPublish://我的发布
                startActivity(new Intent(App.application, MyPublishActivity.class));
                break;
            case R.id.rl_eCode://各设备二维码
               /* Intent intent = new Intent(getActivity(), MyStainActivity.class);
                startActivity(intent);*/
                //startActivity(new Intent(App.application, ECodeListActivity.class));
                startActivity(new Intent(App.application, EnergyRecordListActivity.class));
                break;
            case R.id.rl_service_record://维修记录
                startActivity(new Intent(getActivity(), WeiBaoRecordActivity.class));
                break;
            case R.id.rl_diagnosis_record://诊断记录
                //startActivity(new Intent(getActivity(), ZhenDuanRecordActivity.class));
                startActivity(new Intent(App.application, MyZhenduanActivity.class));
                break;
            case R.id.rl_esign://电子签名demo
                startActivity(new Intent(mActivity, ChangeShiftsActivity.class));
                break;
            case R.id.iv_head://头像点击事件
                if (info != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("user", info);
                    startActivity(new Intent(getActivity(), PersonalInfoActivity.class).putExtras(bundle));
                }
                break;
            case R.id.rl_run_record://运行记录 导出报告数据
                startActivity(new Intent(mActivity, Running_RecordActivity.class));
                break;

            case R.id.rl_project_exchange://切换项目
                startActivity(new Intent(mActivity, ProjectExchangeActivity.class));
                break;
            case R.id.rl_projectTest://修改环境 正式  测试  本地
                settingEnvironment();
                break;
            case R.id.txt_quit://退出登录接口
                quitLogin();//退出登录
                break;
            default:

                break;
        }
    }

    private void quitLogin() {
        DialogPrompt.Builder builder = new DialogPrompt.Builder(mActivity);
        builder.setTitle("确定退出吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                presenter.Quit_Login(mActivity);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();

    }

    private void settingEnvironment() {
        DialogPrompt.Builder builder = new DialogPrompt.Builder(mActivity);
        builder.setTitle("确定修改环境？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ToastUtils.showToast(mActivity, "退出重进即可");
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).createEnvironment().show();

    }


    @Override
    public void setText(Object data) {
        UserInfo userInfo = (UserInfo) data;
        if (isFirst.equals("ok")) {
            App.sp.setRefresh("no");
        }
        info = userInfo.getResobj();
        //设置头像
        if (info.getAvatarUrl() != null) {
            Glide.with(mActivity).load(ConstantValue.URL + info.getAvatarUrl()).placeholder(R.mipmap.default_head).transform(new GlideCircleTransform(mActivity)).crossFade().into(ivHead);
        }
        //设置昵称
        if (info.getUserName() != null) {
            tvName.setText(info.getUserName());
        }
        //设置爱好
        if (!TextUtils.isEmpty(info.getInterest())) {
            tvHobby.setText(info.getInterest());
        }
        //设置性别
        if (!TextUtils.isEmpty(info.getGender())) {
            tvSex.setText(info.getGender());
        }
        //设置我的收藏数
        if (!TextUtils.isEmpty(info.getMyCollection())) {
            tvMyFavoriteNum.setText(info.getMyCollection());
        } else {
            tvMyFavoriteNum.setText("0");
        }
        //设置我的发布数
        if (!TextUtils.isEmpty(info.getMyPublish())) {
            tvMyMomentsNum.setText(info.getMyPublish());
        } else {
            tvMyMomentsNum.setText("0");
        }
        isFirst = "false";
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}