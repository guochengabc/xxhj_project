package com.kongtiaoapp.xxhj.bpd.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.DeviceParam;
import com.kongtiaoapp.xxhj.bean.GetRDateParam_RecorderBean;
import com.kongtiaoapp.xxhj.bean.ProjectList;
import com.kongtiaoapp.xxhj.bean.Project_Module_Name;
import com.kongtiaoapp.xxhj.bean.Report_Running_RecordBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.BReportP;
import com.kongtiaoapp.xxhj.mvp.view.BReportV;
import com.kongtiaoapp.xxhj.ui.timeselector.activity.TimePickerView;
import com.kongtiaoapp.xxhj.ui.view.CustomViewPager;
import com.kongtiaoapp.xxhj.ui.view.MyPopupWindow;
import com.kongtiaoapp.xxhj.ui.view.ProgressBar_Report_Data;
import com.kongtiaoapp.xxhj.utils.Byte_To_mUtils;
import com.kongtiaoapp.xxhj.utils.DateUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.   变配电  报表导出
 */
public class BReportFragment extends BaseFragment<BReportP, BReportV> implements BReportV {

    private String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "/" + "00a变配电_XiaoXiDownloads" + "/";
    @BindView(R.id.txt_starttime)
    TextView txt_starttime;
    @BindView(R.id.txt_endtime)
    TextView txt_endtime;
    @BindView(R.id.txt_project_select)
    TextView txt_project_select;
    @BindView(R.id.txt_loading)
    TextView txt_loading;
    @BindView(R.id.txt_loading_shu)
    TextView txt_loading_shu;
    @BindView(R.id.progress)
    ProgressBar_Report_Data progress;
    @BindView(R.id.rela_loading)
    RelativeLayout rela_loading;
    @BindView(R.id.txt_project_module)
    TextView txt_project_module;
    @BindView(R.id.txt_down_3)
    TextView txt_down_3;
    private boolean isLoad = true, isRefresh = true;
    private int currentPage = 1;
    DeviceParam data = new DeviceParam();
    List<DeviceParam.EnumValue> enumValue = new ArrayList<>();
    Map<String, String> buildingTypeMap = new LinkedHashMap<>();
    Map<String, String> building_Module = new LinkedHashMap<String, String>();
    private MyPopupWindow popupWindow;
    private int projectPostion = 100;
    private String start_time = "", end_time = "";
    private List<ProjectList.ResobjBean> list_bean;
    private int percent = 0;
    private String projectModule = "100", isSuceedd = "100";//100代表选择的项目列表
    private List<Project_Module_Name.ResobjBean> list_module = new ArrayList<>();
    private CustomViewPager vp;
    private boolean isFirst = true;
    private String projectId;
    public BReportFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public BReportFragment(CustomViewPager vp) {
        this.vp = vp;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        if (vp != null) {
            vp.setObjectForPosition(view, 2);
        }
        return view;
    }

    @Override
    public BReportP getPresenter() {
        return new BReportP();
    }

    @Override
    protected View initView() {
        View inflate = View.inflate(mActivity, R.layout.fragment_running_reorder, null);
        return inflate;
    }

    @Override
    public void initData() {
        super.initData();
        txt_down_3.setText("3.当历史输出数据提示下载成功后,进入文件管理,找到\"00a变配电_XiaoXiDownloads\"文件夹即可");
        progress.setMax(100);
        txt_starttime.setText(DateUtils.getNowTime("yyyy-MM-dd"));
        txt_endtime.setText(DateUtils.getNowTime("yyyy-MM-dd"));
        start_time = txt_starttime.getText().toString();
        end_time = txt_endtime.getText().toString();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirst) {
                isFirst = !isFirst;
                getDataForServices();
            }

        }
    }

    private void getDataForServices() {
        presenter.onResume(mActivity,projectId);
    }


    @OnClick({R.id.btn_report, R.id.txt_starttime, R.id.txt_endtime, R.id.txt_project_select, R.id.txt_project_module})
    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_report:
                if (!start_time.equals("") && !end_time.equals("") && (!TextUtils.isEmpty(txt_project_select.getTag() != null ? txt_project_select.getTag().toString() : null)) && projectPostion != 100 && (!TextUtils.isEmpty(txt_project_module.getTag() != null ? txt_project_module.getTag().toString() : null))) {
                    report_data_forservice();
                }
                break;
            case R.id.txt_starttime:
                setdatePicker(1);//1  代表开始时间  0代表结束时间
                break;
            case R.id.txt_endtime:
                setdatePicker(0);
                break;
            case R.id.txt_project_select:
                if (projectPostion != 100)
                    getProjectName();
                break;
            case R.id.txt_project_module:
                if (!isSuceedd.equals("100"))
                    projectModule = "1000";//100代表不出来popmenu  其他出来
                getProjectModule();//选择项目模板
                break;
            default:
                break;
        }
    }

    private void getProjectModule() {//获取项目模板
        presenter.getProjectModule(mActivity, txt_project_select.getTag().toString());
    }

    private void getProjectName() {//获取项目名字
        presenter.getProjectName(mActivity, String.valueOf(currentPage));
    }

    private void report_data_forservice() {
        List<String> list = new ArrayList<>();
        list.add(start_time);
        list.add(end_time);
        list.add(txt_project_module.getTag().toString());
        list.add(txt_project_select.getTag().toString());
        presenter.report_data_forservice(mActivity, list);
    }

    private void downDocument(String url) {
        if (url.isEmpty() || url.length() == 0) {
            return;
        }
        final String fileurl = url.substring(url.lastIndexOf("/") + 1, url.length());
        try {
            List<String> list = new ArrayList<>();
            list.add(ConstantValue.URLS + url.substring(0, url.lastIndexOf("/") + 1) + URLEncoder.encode(fileurl, "UTF-8"));
            Log.i("ffffffffff", "下载url=" + ConstantValue.URL + url.substring(0, url.lastIndexOf("/") + 1) + URLEncoder.encode(fileurl, "UTF-8"));
            list.add(path + fileurl);
            presenter.downDocument(list);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void setdatePicker(final int timeType) {
        // 时间选择器
        TimePickerView pvTime = null;
        pvTime = new TimePickerView(mActivity, TimePickerView.Type.YEAR_MONTH_DAY);
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        pvTime.setOnTimeSelectListener(date -> {
            String time = getTime(date);
            if (timeType == 1) {
                txt_starttime.setText(time);
                start_time = time;
            } else if (timeType == 0) {
                txt_endtime.setText(time);
                end_time = time;
            }
        });
        pvTime.show();
    }

    public String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private void setProjectModule() {
        enumValue.clear();
        for (String key : building_Module.keySet()) {
            DeviceParam.EnumValue item = data.new EnumValue();
            item.setCode(key);
            item.setValue(building_Module.get(key));
            enumValue.add(item);
            showSortPopup_module(txt_project_module, enumValue);//弹出框选择项目名称
        }
    }

    private void settingProjectName() {
        enumValue.clear();
        for (String key : buildingTypeMap.keySet()) {
            DeviceParam.EnumValue item = data.new EnumValue();
            item.setCode(key);
            item.setValue(buildingTypeMap.get(key));
            enumValue.add(item);
        }
        showSortPopup(txt_project_select, enumValue);//弹出框选择项目名称
    }

    private void showSortPopup_module(final TextView txt_project_modules, final List<DeviceParam.EnumValue> projectList) {
        if (popupWindow == null) {
            popupWindow = new MyPopupWindow(mActivity, mActivity.getWindow());
        }
        //设置宽高
        popupWindow.setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置数据源
        popupWindow.setList(projectList);
        //设置监听
        popupWindow.setListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String value = projectList.get(position).getValue();
                if (value.length() > 10) {
                    value = value.substring(0, 10) + "...";
                }
                txt_project_modules.setText(value);
                txt_project_modules.setTag(projectList.get(position).getCode());
                popupWindow.dismiss();
            }
        });
        //初始化页面，必须写在后面
        popupWindow.initView();
        popupWindow.showAtLocation(mActivity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    private void showSortPopup(final TextView txt_project_select, final List<DeviceParam.EnumValue> projectList) {
        if (popupWindow == null) {
            popupWindow = new MyPopupWindow(mActivity, mActivity.getWindow());
        }
        //设置宽高
        popupWindow.setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置数据源
        popupWindow.setList(projectList);
        //设置监听
        popupWindow.setListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String value = projectList.get(position).getValue();
                if (value.length() > 10) {
                    value = value.substring(0, 10) + "...";
                }
                txt_project_select.setText(value);
                txt_project_select.setTag(projectList.get(position).getCode());
                projectModule = "100";
                getProjectModule();//选择项目模板
                popupWindow.dismiss();
            }
        });
        //初始化页面，必须写在后面
        popupWindow.initView();
        popupWindow.showAtLocation(mActivity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void setList(Object data) {
        GetRDateParam_RecorderBean bean = (GetRDateParam_RecorderBean) data;
        projectPostion = 1000;
        projectModule = "1000";
        GetRDateParam_RecorderBean.ResobjBean resobj = bean.getResobj();

        if (resobj.getProjectId() == null) {

        }
        txt_project_select.setTag(resobj.getProjectId());
        txt_project_module.setTag(resobj.getTemplateId());
        if (!TextUtils.isEmpty(resobj.getProjectName())) {
            txt_project_select.setText(resobj.getProjectName());
        } else {
            txt_project_select.setText(getString(R.string.project_no_project));
        }
        if (!TextUtils.isEmpty(resobj.getTemplateName())) {
            txt_project_module.setText(resobj.getTemplateName());
        } else {
            txt_project_module.setText(R.string.project_no_module);
        }
    }

    @Override
    public void list_null(String type) {
        if (type.equals("0")) {
            projectModule = "100";
            txt_project_select.setText(getString(R.string.project_no_project));
            txt_project_module.setText(R.string.project_no_module);
        } else if (type.equals("1")) {
            projectModule = "100";
            isSuceedd = "100";
        } else if (type.equals("2")) {
            txt_project_module.setText(getString(R.string.project_moduleName));
        }
    }

    @Override
    public void getProjectName_View(Object response) {
        ProjectList info = (ProjectList) response;
        list_bean = info.getResobj();
        buildingTypeMap.clear();
        for (int i = 0; i < list_bean.size(); i++) {
            buildingTypeMap.put(list_bean.get(i).getProjectId(), list_bean.get(i).getName());
        }
        projectModule = "1000";
        isSuceedd = "1000";
        settingProjectName();//选择项目名称
    }

    @Override
    public void getProjectModule_View(Object response) {
        Project_Module_Name bean_module = (Project_Module_Name) response;
        list_module = bean_module.getResobj();
        building_Module.clear();
        for (int i = 0; i < list_module.size(); i++) {
            building_Module.put(list_module.get(i).getTemplateId(), list_module.get(i).getTemplateName());
        }
        if (!projectModule.equals("100")) {
            setProjectModule();
        } else if (projectModule.equals("100")) {
            txt_project_module.setText(list_module.get(0).getTemplateName());
            txt_project_module.setTag(list_module.get(0).getTemplateId());
        }
        projectModule = "1000";
    }

    @Override
    public void report_data_forservice_View(Object response) {
        Report_Running_RecordBean bean = (Report_Running_RecordBean) response;
        rela_loading.setVisibility(View.VISIBLE);
        progress.setVisibility(View.VISIBLE);
        if (bean.getResobj().getFileUrl() != null && !bean.getResobj().getFileUrl().equals("")) {
            downDocument(bean.getResobj().getFileUrl());//下载文件  z这个到底能不能请求下来呢   谁能告诉我下哈
        }
    }

    @Override
    public void downDocument_View(Object data) {

    }

    @Override
    public void succeed() {
        ToastUtils.showToast(mActivity, "亲,导出数据成功了");
    }

    @Override
    public void onfinish(Object data) {
        txt_loading_shu.setVisibility(View.INVISIBLE);
        txt_loading.setText("下载完成");
        txt_loading.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.INVISIBLE);
        rela_loading.setVisibility(View.GONE);
        progress.setVisibility(View.GONE);
    }

    @Override
    public void onCancelled() {
        ToastUtils.showToast(mActivity, "取消下载");
        txt_loading_shu.setVisibility(View.INVISIBLE);
        txt_loading.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.INVISIBLE);
        rela_loading.setVisibility(View.GONE);
        progress.setVisibility(View.GONE);
    }

    @Override
    public void onError(String type) {
        if (type.equals("0")) {
            projectModule = "100";
        } else if (type.equals("1")) {
            projectModule = "100";
        } else if (type.equals("4")) {
            txt_loading_shu.setVisibility(View.INVISIBLE);
            txt_loading.setVisibility(View.INVISIBLE);
            progress.setVisibility(View.INVISIBLE);
            rela_loading.setVisibility(View.GONE);
            progress.setVisibility(View.GONE);
            ToastUtils.showToast(mActivity, "下载失败");
        }
    }

    @Override
    public void onLoading(long total, long current, boolean isDownloading) {
        int down_percent = (int) ((current * 100 / total));
        txt_loading.setText("正在下载:");
        progress.setVisibility(View.VISIBLE);
        txt_loading.setVisibility(View.VISIBLE);
        txt_loading_shu.setVisibility(View.VISIBLE);
        txt_loading_shu.setText(Byte_To_mUtils.Byte2M(current));
        progress.setProgress(down_percent);
    }

    @Override
    public void setText(Object data) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (enumValue != null) {
            enumValue.clear();
            enumValue = null;
        }
        if (buildingTypeMap != null) {
            buildingTypeMap.clear();
            buildingTypeMap = null;
        }
        if (list_bean != null) {
            list_bean.clear();
            list_bean = null;
        }
        if (building_Module != null) {
            building_Module.clear();
            building_Module = null;
        }
    }

}
