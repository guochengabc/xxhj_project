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

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.ImageAdapter;
import com.kongtiaoapp.xxhj.adapter.RecordDetailAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.bean.DeviceNameE_CodeBean;
import com.kongtiaoapp.xxhj.bean.RecordDetailsBean;
import com.kongtiaoapp.xxhj.db.ProjectTable;
import com.kongtiaoapp.xxhj.db.SensorIdListTable;
import com.kongtiaoapp.xxhj.interfaces.RecordStatus;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.RecordSearchDetailsP;
import com.kongtiaoapp.xxhj.mvp.view.RecordSearchDetailsV;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImageGridActivity;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImageItem;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImagePicker;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImagePreviewDelActivity;
import com.kongtiaoapp.xxhj.ui.NineGridView.NineGridView;
import com.kongtiaoapp.xxhj.ui.view.NoScrollGridView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.xutils.DbManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kongtiaoapp.xxhj.R.id.gridview;

public class RecordSearchDetailsActivity extends BaseActivity<RecordSearchDetailsP, RecordSearchDetailsV> implements RecordSearchDetailsV, RecordStatus {
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.tv_title)
    TextView tv_title;
    private NoScrollGridView gridView;
    private NineGridView nineG;
    private ArrayList<ImageItem> selImageList = new ArrayList<>(); // 当前选择的所有图片  勘察
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    public static final int REQUEST_CODE_SELECT_FINISH = 1000;
    public static final int REQUEST_CODE_PREVIEW_FINISH = 1001;

    private ImageAdapter adapterImage;
    private RecordDetailAdapter adapter;
    private String sensorId = "";
    //以下是数据库管理内容
    private DbManager db;
    private ProjectTable project;
    private SensorIdListTable sensorTable;
    private String recordValue = "";
    private String readModel = "";
    private List<RecordDetailsBean.ResobjBean> electricity;
    private DeviceNameE_CodeBean deviceNameE_codeBean;
    private String recordType = "normal";
    private List<String> listParam;
    private String recordTime = "";
    private String time = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_record_search_details;
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            time = intent.getStringExtra("time");
            recordTime = intent.getStringExtra("recordTime");
            sensorId = intent.getStringExtra("sensorId");
            tv_title.setText(intent.getStringExtra("title"));
            listParam = new ArrayList<>();
            listParam.add(sensorId);
            listParam.add(time);
            listParam.add(recordTime);
            presenter.onResume(this, listParam);
        }

    }

    @Override
    protected RecordSearchDetailsP getPresenter() {
        return new RecordSearchDetailsP();
    }

    @OnClick({R.id.iv_back, R.id.tv_modify,R.id.ctv_recall})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                close_key();
                finish();
                break;
            case R.id.ctv_recall://撤回数据

                presenter.recallData(this,listParam);
                break;
            case R.id.tv_modify:
                commitData();//提交数据
                break;
        }
    }

    private void commitData() {
        List<RecordDetailsBean.ResobjBean> list = adapter.getList();
        for (int i = 0; i < list.size(); i++) {
            RecordDetailsBean.ResobjBean electricityBean = list.get(i);
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
                ToastUtils.showToast(RecordSearchDetailsActivity.this, "请上传异常照片");
                return;
            }
        }
        presenter.modifyData(RecordSearchDetailsActivity.this, list, selImageList,listParam , adapter.getRecordStatus());
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


    private void setFooter(View inflate) {
        listview.addFooterView(inflate);
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


    @Override
    public void getList(Object data) {
        if (sensorId == null) {
            return;
        }
        RecordDetailsBean bean = (RecordDetailsBean) data;
        electricity = bean.getResobj();

        if (adapter == null) {
            adapter = new RecordDetailAdapter(this, electricity);
            listview.setAdapter(adapter);
        } else {
            adapter.setList(electricity);
        }
    }

    @Override
    public void list_null() {

    }

    @Override
    public void list_error() {

    }

    @Override
    public void modifyData() {

    }

    @Override
    public void recallData() {

    }

    @Override
    public void getRecordStatus(String status) {

    }
}
