package com.kongtiaoapp.xxhj.recordform.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.ImageAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.DeviceNameE_CodeBean;
import com.kongtiaoapp.xxhj.bean.EnergyRecordBean;
import com.kongtiaoapp.xxhj.db.ProjectTable;
import com.kongtiaoapp.xxhj.db.SensorIdListTable;
import com.kongtiaoapp.xxhj.energymetering.activity.EnergyRecordActivity;
import com.kongtiaoapp.xxhj.energymetering.activity.EnergyRecordStatusActivity;
import com.kongtiaoapp.xxhj.energymetering.adapter.EnergyRecordAdapter;
import com.kongtiaoapp.xxhj.interfaces.RecordStatus;
import com.kongtiaoapp.xxhj.mvp.base.BaseGuestorActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.EnergyRecordP;
import com.kongtiaoapp.xxhj.mvp.view.EnergyRecordV;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImageGridActivity;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImageItem;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImagePicker;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImagePreviewDelActivity;
import com.kongtiaoapp.xxhj.ui.NineGridView.NineGridView;
import com.kongtiaoapp.xxhj.ui.view.ClickTextView;
import com.kongtiaoapp.xxhj.ui.view.NoScrollGridView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kongtiaoapp.xxhj.R.id.ctv_commit;
import static com.kongtiaoapp.xxhj.R.id.gridview;

/**
 * 燃气充值
 */
public class GasRechargeActivity extends BaseGuestorActivity<EnergyRecordP, EnergyRecordV> implements EnergyRecordV, RecordStatus {
    @BindView(R.id.lv_energyRecord)
    ListView lv_energyRecord;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ctv_commit)
    ClickTextView line_commit;//提交
    @BindView(R.id.ctv_additionalRecord)
    ClickTextView ctv_additionalRecord;//补录
    @BindView(R.id.tv_prompt)
    TextView tv_prompt;
    private NoScrollGridView gridView;
    private NineGridView nineG;
    private ArrayList<ImageItem> selImageList = new ArrayList<>(); // 当前选择的所有图片  勘察
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    public static final int REQUEST_CODE_SELECT_FINISH = 1000;
    public static final int REQUEST_CODE_PREVIEW_FINISH = 1001;

    private ImageAdapter adapterImage;
    private EnergyRecordAdapter adapter;
    private String sensorId = "";
    //以下是数据库管理内容
    private DbManager db;
    private ProjectTable project;
    private SensorIdListTable sensorTable;
    private String recordValue = "";
    private String readModel = "";
    private List<EnergyRecordBean.ResobjBean.ElectricityBean> electricity;
    private DeviceNameE_CodeBean deviceNameE_codeBean;
    private String recordType = "normal";
    private List<String> listParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_energy_record;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        tv_prompt.setVisibility(View.GONE);
        ctv_additionalRecord.setVisibility(View.GONE);
        db = App.getDb();//得到db
        if (sensorTable == null) {
            sensorTable = new SensorIdListTable();
        }
        if (project == null) {
            project = new ProjectTable();
        }
        Intent intent = getIntent();
        if (intent != null) {
            deviceNameE_codeBean = (DeviceNameE_CodeBean) intent.getSerializableExtra("device");
            sensorId = deviceNameE_codeBean.getSensorId();
            recordType = intent.getStringExtra("recordType");
            if (recordType == null || recordType.trim().equals("")) {//正常录入
                recordType = "normal";
            } else {
                ctv_additionalRecord.setVisibility(View.GONE);
            }
            if (deviceNameE_codeBean != null) {
                tv_title.setText(deviceNameE_codeBean.getName());
                listParam = new ArrayList<>();
                listParam.add(deviceNameE_codeBean.getSensorId());
                listParam.add(recordType);
                presenter.onResume(this, listParam);
            }

        }

    }

    @Override
    protected EnergyRecordP getPresenter() {
        return new EnergyRecordP();
    }

    @OnClick({R.id.iv_back, ctv_commit, R.id.txt_status, R.id.ctv_additionalRecord})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                close_key();
                finish();
                break;
            case R.id.ctv_additionalRecord://{"Code":"time","FieldType":"dateDay","InputType":"1","Name":"","Unit":"","Value":"补录时间"}
                startActivity(new Intent(this, EnergyRecordActivity.class).putExtra("device", (Serializable) deviceNameE_codeBean).putExtra("recordType", "reInput"));
                finish();

                break;
          /*  case R.id.ctv_caculator://录值计算   目前先不要，以后可能会用到
                if (adapter.getRecordValue().equals("")) {
                    ToastUtils.showToast(this, "录入数才能计算哦！");
                    return;
                }
                if (recordValue.equals(adapter.getRecordValue())) {//获取值和上次录入值一样  就不用获取后台录入数据接口
                    return;
                }
                recordValue = adapter.getRecordValue();
                List<String> list = new ArrayList<>();
                list.add(sensorId);
                list.add(adapter.getRecordValue());
                presenter.caculatorData(this, list);
                break;*/
            case R.id.txt_status:
                startActivity(new Intent(this, EnergyRecordStatusActivity.class));
                break;
            case ctv_commit:
                commitData();//提交数据
                break;
        }
    }

    private void commitData() {
        List<EnergyRecordBean.ResobjBean.ElectricityBean> list = adapter.getList();
        for (int i = 0; i < list.size(); i++) {
            EnergyRecordBean.ResobjBean.ElectricityBean electricityBean = list.get(i);
            if (electricityBean.getInputType().equals("0")) {
                if (!electricityBean.getCode().equals("Status")) {
                    if (electricityBean.getName().toString().trim().equals("")) {
                        ToastUtils.showToast(this, "请填写" + list.get(i).getValue());
                        return;
                    }
                }
            }
            switch (electricityBean.getFieldType()) {
                case ConstantValue.DATEMONTH:
                    if (electricityBean.getName().equals(ConstantValue.PLEASE_TIME)) {
                        ToastUtils.showToast(this, ConstantValue.PLEASE_TIME);
                        return;
                    }
                    break;
                case ConstantValue.DATEDAY:
                    if (electricityBean.getName().equals(ConstantValue.PLEASE_TIME)) {
                        ToastUtils.showToast(this, ConstantValue.PLEASE_TIME);
                        return;
                    }
                    break;
                case ConstantValue.DATEHOUR:
                    if (electricityBean.getName().equals(ConstantValue.PLEASE_TIME)) {
                        ToastUtils.showToast(this, ConstantValue.PLEASE_TIME);
                        return;
                    }
                    break;
                case ConstantValue.DATEMINUTE:
                    if (electricityBean.getName().equals(ConstantValue.PLEASE_TIME)) {
                        ToastUtils.showToast(this, ConstantValue.PLEASE_TIME);
                        return;
                    }
                    break;
                case ConstantValue.DATESECOND:
                    if (electricityBean.getName().equals(ConstantValue.PLEASE_TIME)) {
                        ToastUtils.showToast(this, ConstantValue.PLEASE_TIME);
                        return;
                    }
                    break;
                default:

                    break;
            }
        }
        if (adapter.getRecordStatus().equals("1")) {//如果事异常状态
            if (selImageList.isEmpty()) {
                ToastUtils.showToast(GasRechargeActivity.this, "请上传异常照片");
                return;
            }
        }

        presenter.commitData(GasRechargeActivity.this, list, selImageList, sensorId, adapter.getRecordStatus());
        if (list == null || list.isEmpty()) {
            return;
        }
        //  saveData(list);
    }

    @Override
    protected void initView() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.include_energy_record, null);
        gridView = ((NoScrollGridView) inflate.findViewById(gridview));
        nineG = ((NineGridView) inflate.findViewById(R.id.iv_picture));
        setFooter(inflate);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    private void saveData(List<EnergyRecordBean.ResobjBean.ElectricityBean> list) {

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Electricity", list);
        String json = gson.toJson(map);
        if (project == null) {
            project = new ProjectTable();
        }
        try {
            String sendorIdList = null;
            try {
                sensorTable = db.selector(SensorIdListTable.class).where("projectId", "=", App.sp.getProjectId()).findFirst();
                sendorIdList = sensorTable.getSensorId();
                if (!sendorIdList.contains(sensorId)) {//如果不包含该界面的sensorId添加sensorId
                    sendorIdList = sendorIdList + ConstantValue.SPLIT_ENERGY + sensorId;
                    sensorTable.setSensorId(sendorIdList);
                    sensorTable.setProjectId(App.sp.getProjectId());
                    sensorTable.setSave(true);
                    db.saveOrUpdate(sensorTable);
                }
                project.setProjectId(App.sp.getProjectId());
                project.setDeviceId(sensorId);
                project.setInfo(gson.toJson(json));
                project.setName(App.sp.getPaintType());
                project.setSave(true);
                db.saveOrUpdate(project);
            } catch (NullPointerException e) {
                sendorIdList = sensorId;
                if (sensorTable == null) {
                    sensorTable = new SensorIdListTable();
                }
                sensorTable.setSensorId(sendorIdList);
                sensorTable.setProjectId(App.sp.getProjectId());
                sensorTable.setSave(true);
                db.saveOrUpdate(sensorTable);
                project.setProjectId(App.sp.getProjectId());
                project.setDeviceId(sensorId);
                project.setInfo(json.toString());
                project.setName(App.sp.getPaintType());
                project.setSave(true);
                db.saveOrUpdate(project);
                e.printStackTrace();
            }

        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private void setFooter(View inflate) {
        lv_energyRecord.addFooterView(inflate);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapterImage = new ImageAdapter(this, 0, selImageList);
        gridView.setAdapter(adapterImage);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IntentToImage(selImageList, position, parent, REQUEST_CODE_SELECT, REQUEST_CODE_PREVIEW, 2);//跳转到图库
            }
        });
    }

    private void IntentToImage(ArrayList<ImageItem> selImageList, int position, AdapterView<?> parent, int one, int two, int maxImgCount) {
        if (position == parent.getAdapter().getCount() - 1 && position != 8) {
            // 打开选择,本次允许选择的数量
            ImagePicker.getInstance(true).setSelectLimit(
                    maxImgCount - selImageList.size());
            Intent intent = new Intent(this, ImageGridActivity.class);
            startActivityForResult(intent, one);
        } else {
            Intent intentPreview = new Intent(this,
                    ImagePreviewDelActivity.class);
            intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS,
                    (ArrayList<ImageItem>) selImageList);
            intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION,
                    position);
            startActivityForResult(intentPreview, two);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            // 添加图片返回
            if (data != null) {
                if (requestCode == REQUEST_CODE_SELECT) {
                    ArrayList<ImageItem> images = (ArrayList<ImageItem>) data
                            .getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                    selImageList.addAll(images);//回传过来的
                    adapterImage.notifyDataSetChanged();
                }

            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            // 预览图片返回
            if (data != null) {
                if (requestCode == REQUEST_CODE_PREVIEW) {
                    ArrayList<ImageItem> images = (ArrayList<ImageItem>) data
                            .getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapterImage.notifyDataSetChanged();
                }

            }

        }

    }


    @Override
    public void setText(Object data) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        close_key();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /**
     * 上一页
     */
    @Override
    public void pre(View view) {
        if (sensorId != null) {
            presenter.getPre(this, sensorId);
        }
    }

    /**
     * 下一页
     */
    @Override
    public void next(View view) {
        if (sensorId != null) {
            presenter.getNext(this, sensorId);
        }
    }

    @Override
    public void setList(Object data) {
        if (sensorId == null) {
            return;
        }
        EnergyRecordBean bean = (EnergyRecordBean) data;
        EnergyRecordBean.ResobjBean resobj = bean.getResobj();
        setReadModel(resobj);//设置自动检测和手动录入
        electricity = resobj.getElectricity();
        if (adapter == null) {
            adapter = new EnergyRecordAdapter(this, electricity, sensorId);
            lv_energyRecord.setAdapter(adapter);
        } else {
            adapter.setList(electricity);
        }


      /*  if (NetworkUtils.checkNetwork(this)) {
            adapter = new EnergyRecordAdapter(this, electricity, sensorId);
            lv_energyRecord.setAdapter(adapter);
        } else {
            try {
                project = db.selector(ProjectTable.class).where("projectId", "=", App.sp.getProjectId()).and("deviceId", "=", sensorId).findFirst();
                String info = project.getInfo();
                List<EnergyRecordBean.ResobjBean.ElectricityBean> list = stringToArray(info, EnergyRecordBean.ResobjBean.ElectricityBean[].class);
                adapter = new EnergyRecordAdapter(this, list, sensorId);
                lv_energyRecord.setAdapter(adapter);

            } catch (DbException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }*/
    }

    private void setReadModel(EnergyRecordBean.ResobjBean resobj) {
        readModel = resobj.getReadModel();//自动还是手动检测
        if (readModel == null) {

        } else {
            if (readModel.equals(ConstantValue.ENERGY_manu)) {//手动录入  显示提交按钮
                line_commit.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public void getRecordValue(Object data) {
        EnergyRecordBean bean = (EnergyRecordBean) data;
        EnergyRecordBean.ResobjBean resobj = bean.getResobj();
        List<EnergyRecordBean.ResobjBean.ElectricityBean> electricity = resobj.getElectricity();
        adapter.setList(electricity);
    }

    @Override
    public void getPre(Object data) {
        EnergyRecordBean bean = (EnergyRecordBean) data;
        EnergyRecordBean.ResobjBean resobj = bean.getResobj();
        if (resobj.getSensorId() == null) {

        } else {
            sensorId = resobj.getSensorId();
            tv_title.setText(resobj.getTitle());
            setReadModel(resobj);
        }
        List<EnergyRecordBean.ResobjBean.ElectricityBean> electricity = resobj.getElectricity();
        adapter.setList(electricity);
    }

    @Override
    public void getNext(Object data) {
        EnergyRecordBean bean = (EnergyRecordBean) data;
        EnergyRecordBean.ResobjBean resobj = bean.getResobj();
        if (resobj.getSensorId() == null) {

        } else {
            sensorId = resobj.getSensorId();
            tv_title.setText(resobj.getTitle());
            setReadModel(resobj);
        }
        List<EnergyRecordBean.ResobjBean.ElectricityBean> electricity = resobj.getElectricity();
        adapter.setList(electricity);
    }

    @Override
    public void getCommitData() {
        if (listParam != null) {
            presenter.onResume(this, listParam);
        }

    }

    public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {//这块有问题，需要去修改
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr);
    }


    @Override
    public void getRecordStatus(String status) {
        if (readModel == null) {

        } else {
            if (readModel.equals(ConstantValue.ENERGY_AUTO)) {
                if (status == null) {
                    return;
                } else if (status.equals("0")) {//正常隐藏
                    line_commit.setVisibility(View.GONE);
                } else if (status.equals("1")) {//异常显示
                    line_commit.setVisibility(View.VISIBLE);
                }
            }
        }


    }
}
