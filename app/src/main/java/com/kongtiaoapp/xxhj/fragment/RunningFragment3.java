package com.kongtiaoapp.xxhj.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.HVAC_MainActivity;
import com.kongtiaoapp.xxhj.prefessionalknowledge.activity.HistoryActivity;
import com.kongtiaoapp.xxhj.activites.OverAllEvaluateActivity;
import com.kongtiaoapp.xxhj.activites.RunningDeviceListActivity;
import com.kongtiaoapp.xxhj.activites.RunningRecordActivity;
import com.kongtiaoapp.xxhj.duty.activity.ZhiBanSubmitActivity;
import com.kongtiaoapp.xxhj.bean.HouseBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.RunningPresenter;
import com.kongtiaoapp.xxhj.mvp.view.RunningView;
import com.kongtiaoapp.xxhj.ui.view.HouseView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 运行界面的Framgent3 ->运行
 * Created by Administrator on 2016-6-8.
 */
public class RunningFragment3 extends BaseFragment<RunningPresenter, RunningView> implements RunningView {
    @BindView(R.id.txt_latent_enegry)
    TextView txt_latent_enegry;
    @BindView(R.id.txt_openegry)
    TextView txt_openegry;
    @BindView(R.id.txt_total_enegry)
    TextView txt_total_enegry;
    @BindView(R.id.txt_openegry_shu)//单位能耗
            TextView txt_openegry_shu;
    @BindView(R.id.txt_total_enegry_shu)//总能耗
            TextView txt_total_enegry_shu;
    @BindView(R.id.txt_latent_enegry_shu)//节约潜力
            TextView txt_latent_enegry_shu;
    @BindView(R.id.tv_project_name)
    TextView mProjectName;//项目名称
    @BindView(R.id.tv_runStatus)
    TextView mRunStatus;
    @BindView(R.id.tv_currentDuty)
    TextView mCurrentDuty;
    @BindView(R.id.hv_running3)
    HouseView hv_running3;
    @BindView(R.id.img_enegry)
    ImageView img_enegry;//对比昨日的小图标
    @BindView(R.id.txt_yesterday_total_enegry_shu)
    TextView txt_yesterday_total_enegry_shu;//对比昨日的总能耗
    private String projectId;
    private HVAC_MainActivity activity;
    private boolean isAuto;
    private boolean isFirst = true, isFirst_dialog = true;
    private HouseBean.ResobjBean item = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (HVAC_MainActivity) activity;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        projectId = App.sp.getProjectId();
        isAuto = App.sp.isAuto();
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_running3, null);
    }


    @Override
    public void initData() {
        //设置字体样式
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(),"fonts/huawencaibi.TTF");
        txt_openegry_shu.setTypeface(typeFace);
        txt_latent_enegry_shu.setTypeface(typeFace);
        txt_total_enegry_shu.setTypeface(typeFace);
        txt_openegry.setTypeface(typeFace);
        txt_total_enegry.setTypeface(typeFace);
        txt_latent_enegry.setTypeface(typeFace);
        mRunStatus.setTypeface(typeFace);
        mCurrentDuty.setTypeface(typeFace);
        GetRunningHomeData();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (isFirst == false) {
            GetRunningHomeData();
        }
        isFirst = false;
    }

    //5.1.1获取运行首页数据
    private void GetRunningHomeData() {
        presenter.onResume(mActivity, isFirst_dialog);
        if (isFirst_dialog) {
            isFirst_dialog = !isFirst_dialog;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public RunningPresenter getPresenter() {
        return new RunningPresenter();
    }

    @OnClick({R.id.iv_back, R.id.iv_lengqb, R.id.line_op_enegry, R.id.line_talent_enegry, R.id.line_total_enegry, R.id.line_duty, R.id.tv_runStatus})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                if (item == null) {
                    return;
                }
              /*  String projectId = item.getProjectId();
                if (projectId != null) {
                    Intent intent = new Intent(mActivity, Group_Project_DetailsActivity.class);
                    intent.putExtra("projectId", projectId);
                    startActivity(intent);
                }*/
              mActivity.finish();
                break;
            case R.id.iv_lengqb://右上角设备列表
                startActivity(new Intent(mActivity, RunningDeviceListActivity.class));
                break;
            case R.id.line_op_enegry://单位能耗
                setIntent();//跳转到运行记录里
                break;
            case R.id.line_talent_enegry://节能潜力
                setIntent();//跳转到运行记录里
                break;
            case R.id.line_total_enegry://总能耗
                setIntent();//跳转到运行记录里
                break;
            case R.id.line_duty:
                if (App.sp.isLeader().equals("BB")) {///ZhiBanLeaderActivity之前是跳转到值班列表    没有分页  现在用HistoryActivity替代
                    startActivity(new Intent(mActivity, HistoryActivity.class));
                } else {
                    startActivity(new Intent(mActivity, ZhiBanSubmitActivity.class));
                }
                break;
            case R.id.tv_runStatus:
                // startActivity(new Intent(mActivity, RunningEvaluateActivity.class));
                startActivity(new Intent(mActivity, OverAllEvaluateActivity.class));
                break;
        }
    }

    private void setIntent() {
        if (item == null)
            return;
        if (TextUtils.isEmpty(item.getProjectId())) {
            ToastUtils.showToast(mActivity, getString(R.string.no_prejext));
            return;
        }
        startActivity(new Intent(mActivity, RunningRecordActivity.class).putExtra("projectId", item.getProjectId()));
    }


    @Override
    public void setList(Object response) {
        HouseBean info = (HouseBean) response;
        hv_running3.setrenovate(info);
        item = info.getResobj();
        //总能耗
        if (!TextUtils.isEmpty(item.getTotalEnergy())) {
            txt_total_enegry_shu.setText(item.getTotalEnergy());
        } else {
            txt_total_enegry_shu.setText("0");
        }
        if (!TextUtils.isEmpty(item.getCYdayEnergy())) {
            String sign_day = item.getCYdayEnergy().substring(0, 1);//得到正负号
            if (sign_day.equals("+")) {
                img_enegry.setVisibility(View.VISIBLE);
                img_enegry.setImageResource(R.mipmap.enegry_up);
                txt_yesterday_total_enegry_shu.setText(item.getCYdayEnergy().substring(1));
                txt_yesterday_total_enegry_shu.setTextColor(getResources().getColor(R.color.a277dff));
            } else if (sign_day.equals("-")) {
                img_enegry.setVisibility(View.VISIBLE);
                img_enegry.setImageResource(R.mipmap.enegrydown);
                txt_yesterday_total_enegry_shu.setText(item.getCYdayEnergy().substring(1));
                txt_yesterday_total_enegry_shu.setTextColor(getResources().getColor(R.color.theme_color));
            } else {
                img_enegry.setVisibility(View.GONE);
                txt_yesterday_total_enegry_shu.setText(item.getCYdayEnergy());
                txt_yesterday_total_enegry_shu.setTextColor(getResources().getColor(R.color.theme_color));
            }
        }
        //单位能耗
        if (!TextUtils.isEmpty(item.getUnitEnergy())) {
            txt_openegry_shu.setText(item.getUnitEnergy());
        } else {
            txt_openegry_shu.setText("0");
        }
        //节能潜力
        if (!TextUtils.isEmpty(item.getSaveEnergy())) {
            txt_latent_enegry_shu.setText(item.getSaveEnergy());
        } else {
            txt_latent_enegry_shu.setText("0");
        }

        //项目昵称
        if (!TextUtils.isEmpty(item.getProjectName())) {
            mProjectName.setText(item.getProjectName());
            App.sp.setProjectName(item.getProjectName());
        } else {
            mProjectName.setText("暂无项目");
        }
        if (!TextUtils.isEmpty(item.getRunStatus())) {
            mRunStatus.setText(item.getRunStatus());
        } else {
            mRunStatus.setText("0");
        }
        if (!TextUtils.isEmpty(item.getCurrentDuty())) {//值班人这个字段暂时还没有了
            mCurrentDuty.setText(item.getCurrentDuty());
        } else {
            mCurrentDuty.setText("无");
        }


        if (!TextUtils.isEmpty(item.getProjectId())) {
            App.sp.setProjectId(item.getProjectId());
            Log.i("ffffffffffffff","projectId===="+App.sp.getProjectId());
        }
        if (!TextUtils.isEmpty(item.getProjectType())) {
            App.sp.setProjectType(item.getProjectType());
        }
        if (!TextUtils.isEmpty(item.getCollectMode())) {
            App.sp.setProjectAuto(item.getCollectMode());
        }
    }

    @Override
    public void list_null() {
        txt_total_enegry_shu.setText("0");
        txt_openegry_shu.setText("0");
        txt_latent_enegry_shu.setText("0");
        mRunStatus.setText("0");
        mProjectName.setText("暂无项目");
        mCurrentDuty.setText("无");
        App.sp.removeSp("projectId");
        App.sp.removeSp("projectAuto");
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}
