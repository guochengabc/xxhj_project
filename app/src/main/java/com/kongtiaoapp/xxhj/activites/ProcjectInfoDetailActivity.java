package com.kongtiaoapp.xxhj.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.DeviceInfoListAdapter;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.afinal.ReceiverAction;
import com.kongtiaoapp.xxhj.afinal.UserManegerList;
import com.kongtiaoapp.xxhj.bean.DeviceInfo;
import com.kongtiaoapp.xxhj.bean.DeviceParam;
import com.kongtiaoapp.xxhj.bean.getProjectInfo;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.ProjectInfoDetailPresenter;
import com.kongtiaoapp.xxhj.mvp.view.ProjectInfoDetailView;
import com.kongtiaoapp.xxhj.ui.address.AddressInitTask;
import com.kongtiaoapp.xxhj.ui.timeselector.activity.TimePickerView;
import com.kongtiaoapp.xxhj.ui.view.MyLinearlayout;
import com.kongtiaoapp.xxhj.ui.view.MyPopupWindow;
import com.kongtiaoapp.xxhj.utils.BigToSmallUtils;
import com.kongtiaoapp.xxhj.utils.StringUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ASUS-PC on 2016/6/16.
 * 项目信息详情页面
 */
public class ProcjectInfoDetailActivity extends BaseActivity<ProjectInfoDetailPresenter, ProjectInfoDetailView> implements ProjectInfoDetailView {

    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name0)
    TextView tvName0;
    @BindView(R.id.tv_content0)
    EditText tvContent0;
    @BindView(R.id.tv_name1)
    TextView tvName1;
    @BindView(R.id.tv_content1)
    TextView tvContent1;
    @BindView(R.id.tv_name2)
    TextView tvName2;
    @BindView(R.id.tv_content2)
    public TextView tvContent2;
    @BindView(R.id.tv_name3)
    TextView tvName3;
    @BindView(R.id.tv_content3)
    EditText tvContent3;
    @BindView(R.id.tv_name4)
    TextView tvName4;
    @BindView(R.id.tv_content4)
    EditText tvContent4;
    @BindView(R.id.tv_name5)
    TextView tvName5;
    @BindView(R.id.tv_content5)
    TextView tvContent5;
    @BindView(R.id.tv_name6)
    TextView tvName6;
    @BindView(R.id.tv_content6)
    TextView tvContent6;
    @BindView(R.id.tv_name7)
    TextView tvName7;
    @BindView(R.id.tv_content7)
    EditText tvContent7;
    @BindView(R.id.addProjectInfo_tv_save)
    TextView addProjectInfoTvSave;
    /* @BindView(R.id.et_heatingArea)
     EditText etHeatingArea;*/
    @BindView(R.id.tv_project_type)
    TextView tvProjectType;
    @BindView(R.id.tv_start_running_choose)
    TextView tv_start_running_choose;//选择开始时间
    @BindView(R.id.tv_end_running_choose)
    TextView tv_end_running_choose;
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.line_projectid)
    MyLinearlayout line_projectid;
    /*@BindView(R.id.line_hot)
    LinearLayout line_hot;*/
    @BindView(R.id.line_freeze)
    LinearLayout line_freeze;
    private String start_time = "1";
    private String end_time = "0";
    private static final Map<String, String> buildingTypeMap = new ArrayMap<String, String>() {{
        put("A", "行政办公建筑");
        put("B", "文教建筑");
        put("C", "托教建筑");
        put("D", "科研建筑");
        put("E", "医疗建筑");
        put("F", "商业建筑");
        put("G", "观览建筑");
        put("H", "体育建筑");
        put("I", "旅馆建筑");
        put("J", "交通建筑");
        put("K", "通信广播建筑");
        put("L", "公寓、住宅");
    }};

    private Map<String, String> projectTypeMap = new ArrayMap<String, String>() {{
        put("A", "制冷");
        put("B", "供暖");
    }};

    private String projectId;
    private DeviceInfoListAdapter adapter;

    private getProjectInfo.ProcjectInfo resobj;
    public String cityCode;
    List<DeviceParam.EnumValue> enumValue = new ArrayList<>();
    List<DeviceParam.EnumValue> typeEnumValue = new ArrayList<>();
    private String uid;
    private List<DeviceInfo.DeviceInfoList> mList = new ArrayList();
    private String userId;

    @Override
    protected int initContentView() {

        enumValue.clear();
        DeviceParam data = new DeviceParam();
        for (String key : buildingTypeMap.keySet()) {
            DeviceParam.EnumValue item = data.new EnumValue();
            item.setCode(key);
            item.setValue(buildingTypeMap.get(key));
            enumValue.add(item);
        }
        typeEnumValue.clear();
        for (String key : projectTypeMap.keySet()) {
            DeviceParam.EnumValue item = data.new EnumValue();
            item.setCode(key);
            item.setValue(projectTypeMap.get(key));
            typeEnumValue.add(item);
        }
        return R.layout.activity_add_project_info;
    }

    @Override
    protected void initView() {
        projectId = getIntent().getStringExtra("projectId");
        userId = getIntent().getStringExtra("UserId");
        if (UserManegerList.HVAC_Operate()) {
            tvContent7.setCursorVisible(false);
            line_projectid.getFocus(true);
            addProjectInfoTvSave.setVisibility(View.GONE);
        }
        adapter = new DeviceInfoListAdapter(mContext, mList);
        gridView.setAdapter(adapter);
        if (resobj != null) {
            tvContent0.setText(resobj.getBuildingName());
            tvContent1.setTag(resobj.getBuildingType());
            tvContent1.setText(buildingTypeMap.get(resobj.getBuildingType()));
            tvProjectType.setTag(resobj.getProjectType());
            setAreaVisible(resobj.getProjectType());//根据项目类型判断是制冷或者供暖面积显示或隐藏
            tvProjectType.setText(projectTypeMap.get(resobj.getProjectType()));
            tvContent2.setText(resobj.getCity());
            tvContent4.setText(resobj.getTempDiff());
            tvContent5.setText(resobj.getUserName());
            tvContent6.setText("选择设备");
            tvContent7.setText(resobj.getName());
            if (resobj.getProjectType().equals("A")) {//制冷面积
                tvContent3.setText(resobj.getCoolingArea());
            } else if (resobj.getProjectType().equals("B")) {//供暖面积
                tvContent3.setText(resobj.getHeatingArea());
            }
            if (!TextUtils.isEmpty(resobj.getStartDate())) {
                tv_start_running_choose.setText(resobj.getStartDate());
                start_time = resobj.getStartDate();
            }
            if (!TextUtils.isEmpty(resobj.getEndDate())) {
                tv_end_running_choose.setText(resobj.getEndDate());
                end_time = resobj.getEndDate();
            }

            mList.clear();
            if (resobj.getDevices() != null) {
                DeviceInfo dif = new DeviceInfo();
                for (getProjectInfo.ProcjectInfo.DevicesBean item : resobj.getDevices()
                        ) {
                    DeviceInfo.DeviceInfoList dd = dif.new DeviceInfoList();
                    dd.setDeviceId(item.getDeviceId());
                    dd.setName(item.getDeviceName());
                    dd.setSelected(true);
                    dd.setDeviceType(BigToSmallUtils.pascalToUnderline(item.getDeviceType()));
                    mList.add(dd);
                }
                adapter.setList(mList);
            }

        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        if (!TextUtils.isEmpty(projectId)) {
            getDataForService();
        }
    }

    @Override
    protected ProjectInfoDetailPresenter getPresenter() {
        return new ProjectInfoDetailPresenter();
    }

    private void getDataForService() {
        presenter.onResume(this, projectId);
    }

    private void submit() {
        String BuildingName = tvContent0.getText().toString().trim();
        String BuildingType = tvContent1.getTag() != null ? tvContent1.getTag().toString() : null;
        String City = tvContent2.getText().toString().trim();
        String CoolingArea = tvContent3.getText().toString().trim();
        String TempDiff = tvContent4.getText().toString().trim();
        String UserId = tvContent5.getText().toString().trim();
        String Name = tvContent7.getText().toString().trim();
        //   String heatingArea = etHeatingArea.getText().toString().trim();
        String projectType = tvProjectType.getTag() != null ? tvProjectType.getTag().toString() : null;
        if (TextUtils.isEmpty(Name)) {
            ToastUtils.showToast(this, getString(R.string.project_name_no_null));
            return;
        }
        if (TextUtils.isEmpty(projectType)) {
            ToastUtils.showToast(this, getString(R.string.project_type_no_null));
            return;
        }
        if (TextUtils.isEmpty(City)) {
            ToastUtils.showToast(this, getString(R.string.project_city_no_null));
            return;
        }
        if (TextUtils.isEmpty(BuildingType)) {
            ToastUtils.showToast(this, getString(R.string.project_buildtype_no_null));
            return;
        }
        if (start_time.equals("1")) {
            ToastUtils.showToast(this, getString(R.string.please_start_time));
            return;
        }
        if (end_time.equals("0")) {
            ToastUtils.showToast(this, getString(R.string.please_end_time));
            return;
        }
        if (TextUtils.isEmpty(CoolingArea)) {
            ToastUtils.showToast(this, getString(R.string.project_refrigenation_no_null));
            return;
        }
       /* if (TextUtils.isEmpty(heatingArea)) {
            ToastUtils.showToast(this, getString(R.string.project_hot_no_null));
            return;
        }*/
       /* if (TextUtils.isEmpty(UserId)) {
            ToastUtils.showToast(this, getString(R.string.project_duty_no_null));
            return;
        }*/

        try {
            JSONObject result = new JSONObject();
            // result.put("UserId", App.sp.getUid());
            if (!TextUtils.isEmpty(projectId)) {
                result.put("ProjectId", projectId);
            }
            result.put("Name", Name);
            result.put("BuildingName", BuildingName);
            result.put("BuildingType", BuildingType);
            result.put("City", City);
            if (tvProjectType.getTag().equals("A")) {
                result.put("CoolingArea", CoolingArea);
            } else if (tvProjectType.getTag().equals("B")) {
                result.put("HeatingArea", CoolingArea);
            }
            result.put("ProjectType", projectType);
            result.put("StartDate", start_time);
            result.put("EndDate", end_time);

            if (!TextUtils.isEmpty(BuildingName)) {
                result.put("BuildingName", BuildingName);
            }
            if (!TextUtils.isEmpty(TempDiff)) {
                result.put("TempDiff", TempDiff);
            }
            if (mList != null && mList.size() > 0) {
                List<String> list = new ArrayList<>();
                for (DeviceInfo.DeviceInfoList v : mList) {
                    list.add(v.getDeviceId());
                }
                JSONArray resObj = new JSONArray(list);
                result.put("DeviceIds", resObj);
            }

            String string = "{" + "\"" + "method" + "\"" + ":" + "\"" + HttpMethod.POSTPROJECTINFO + "\"" + "," + "\"" + "reqobj" + "\"" + ":" + result.toString() + "}";
            presenter.submit(this, string);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.addProjectInfo_tv_save, R.id.tv_content1, R.id.tv_content2, R.id.tv_content5, R.id.tv_content6, R.id.tv_project_type, R.id.tv_start_running_choose, R.id.tv_end_running_choose})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.addProjectInfo_tv_save:
                submit();
                break;
            case R.id.tv_content1://建筑物类型
                showSortPopup(tvContent1, enumValue);
                break;
            case R.id.tv_project_type://项目类型
                showSortPopup(tvProjectType, typeEnumValue);
                break;
            case R.id.tv_content2://城市选择
                new AddressInitTask(this, true).execute("北京", "北京");
                break;
            case R.id.tv_content5://值班人员
                startActivityForResult(new Intent(mContext, UserListActivity.class).putExtra("type", 1), 100);
                break;
            case R.id.tv_content6://设备信息
                if (StringUtils.isEmpty(tvProjectType.getText().toString().trim())) {
                    ToastUtils.showToast(this, getString(R.string.please_project_type));
                    return;
                }
                startActivityForResult(new Intent(mContext, UserDeviceListDuoActivity.class)
                                .putExtra("list", (Serializable) mList)
                                .putExtra("projectType", tvProjectType.getTag().toString())
                        , 200);
                break;
            case R.id.tv_start_running_choose:
                setdatePicker(1);
                break;
            case R.id.tv_end_running_choose:
                setdatePicker(0);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                // 值班人员
                if (data != null && resultCode == RESULT_OK) {
                    tvContent5.setText(data.getStringExtra("name"));
                    uid = data.getStringExtra("uid");
                }
                break;
            case 200:
                // 选择设备
                if (data != null && resultCode == RESULT_OK) {

                    mList.addAll((ArrayList<DeviceInfo.DeviceInfoList>) data.getSerializableExtra("list"));
                    adapter.setList(mList);
                }
                break;
            default:
        }
    }

    private void setdatePicker(final int timeType) {
        // 时间选择器
        TimePickerView pvTime = null;
        pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        pvTime.setOnTimeSelectListener(date -> {
            String time = getTime(date);
            if (timeType == 1) {
                tv_start_running_choose.setText(time);
                start_time = time;
            } else if (timeType == 0) {
                tv_end_running_choose.setText(time);
                end_time = time;
            }
        });
        pvTime.show();
    }

    public String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private MyPopupWindow popupWindow;

    /**
     * 显示智能排序popup
     */
    private void showSortPopup(final TextView tv, final List<DeviceParam.EnumValue> list) {
        if (popupWindow == null) {
            popupWindow = new MyPopupWindow(mContext, getWindow());
        }

        //设置宽高
        popupWindow.setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置数据源
        popupWindow.setList(list);
        //设置监听
        popupWindow.setListener((adapterView, view, position, id) -> {
            String key = list.get(position).getCode();
            tv.setText(list.get(position).getValue());
            tv.setTag(key);
            setAreaVisible(key);//设置是制冷还是供暖面积显示
            popupWindow.dismiss();
        });
        //初始化页面，必须写在后面
        popupWindow.initView();
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

    }

    private void setAreaVisible(String key) {
       /* if (key.equals("A")){
            line_freeze.setVisibility(View.VISIBLE);
           // line_hot.setVisibility(View.GONE);
        }
        else if (key.equals("B")){
            line_freeze.setVisibility(View.GONE);
            //line_hot.setVisibility(View.VISIBLE);
        }*/
    }

    @Override
    public void submit_View() {
        ToastUtils.showToast(this, getString(R.string.save_success));
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent().setAction(ReceiverAction.PROJECT_FRESH));
        finish();
    }

    @Override
    public void setText(Object response) {
        getProjectInfo item = (getProjectInfo) response;
        resobj = item.getResobj();
        initView();
    }

    class MyAdapter extends BaseAdapter {
        List list;

        public MyAdapter(List mlist) {
            this.list = mlist;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(
                        R.layout.item_device_choose, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            DeviceInfo.DeviceInfoList item = (DeviceInfo.DeviceInfoList) getItem(position);
            holder.tvName.setText(item.getName());
            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.tv_name)
            TextView tvName;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}
