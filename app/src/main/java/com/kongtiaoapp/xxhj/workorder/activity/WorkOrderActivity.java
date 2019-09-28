package com.kongtiaoapp.xxhj.workorder.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.AllActivityManager;
import com.kongtiaoapp.xxhj.activites.UserListActivity;
import com.kongtiaoapp.xxhj.adapter.ImageAdapter;
import com.kongtiaoapp.xxhj.afinal.UserManegerList;
import com.kongtiaoapp.xxhj.bean.DeviceParam;
import com.kongtiaoapp.xxhj.bean.WorkOrderGet;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.WorkOrderActivityPresenter;
import com.kongtiaoapp.xxhj.mvp.view.WorkOrderActivityView;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImageGridActivity;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImageItem;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImagePicker;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImagePreviewDelActivity;
import com.kongtiaoapp.xxhj.ui.NineGridView.NineGridView;
import com.kongtiaoapp.xxhj.ui.timeselector.activity.TimePickerView;
import com.kongtiaoapp.xxhj.ui.view.MyLinearlayout;
import com.kongtiaoapp.xxhj.ui.view.MyPopupWindow;
import com.kongtiaoapp.xxhj.ui.view.MyScrollView;
import com.kongtiaoapp.xxhj.ui.view.NoScrollGridView;
import com.kongtiaoapp.xxhj.utils.DateUtils;
import com.kongtiaoapp.xxhj.utils.GrabDialog;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 调度人员收到客户电话报修  手动填写工单
 */
public class WorkOrderActivity extends BaseActivity<WorkOrderActivityPresenter, WorkOrderActivityView> implements WorkOrderActivityView
        , AdapterView.OnItemClickListener {
    @BindView(R.id.line_worker_mode)
    MyLinearlayout line_worker_mode;

    @BindView(R.id.line_workNumber)//工单编号
            LinearLayout line_workNumber;
    @BindView(R.id.txt_workNumber)
    TextView txt_workNumber;
    @BindView(R.id.line_custom)
    LinearLayout line_custom;
    @BindView(R.id.edt_customName)//客户单位
            EditText edt_customName;
    @BindView(R.id.edt_task_describe)//任务描述
            EditText edt_task_describe;
    @BindView(R.id.edt_floor_roomNumber)//楼层位置
            EditText edt_floor_roomNumber;
    @BindView(R.id.edt_contactPhone)//联系电话
            EditText edt_contactPhone;
    @BindView(R.id.edt_repairPerson)//填写报修人
            EditText edt_repairPerson;
    @BindView(R.id.txt_worker_mode)//接单模式
            TextView txt_worker_mode;
    @BindView(R.id.line_receiver)//接单人
            MyLinearlayout line_receiver;
    @BindView(R.id.txt_reciever)//接单人
            TextView txt_reciever;
    @BindView(R.id.line_evaluate)
    LinearLayout line_evaluate;
    @BindView(R.id.txt_evaluate)
    TextView txt_evaluate;
    @BindView(R.id.line_finish_time)//完成时间
            LinearLayout line_finish_time;
    @BindView(R.id.txt_finish_time)//完成时间
            TextView txt_finish_time;
    @BindView(R.id.line_byself)//能否本人完成
            LinearLayout line_byself;
    @BindView(R.id.txt_finish_byself)
    TextView txt_finish_byself;
    @BindView(R.id.sv_work_Order)
    MyScrollView sv_work_Order;
    @BindView(R.id.line_workorder)
    MyLinearlayout line_workorder;
    @BindView(R.id.txt_visible)
    TextView txt_visivble;
    //报修时上传图片
    @BindView(R.id.gridview_repair)
    NoScrollGridView gridview_repair;
    @BindView(R.id.iv_picture_repair)
    NineGridView iv_picture_repair;
    @BindView(R.id.txt_repairWork)
    TextView txt_repairWork;
    private ImageAdapter adapterFinish;
    private ArrayList<ImageItem> selFinishList = new ArrayList<>(); // 当前选择的所有图片 完成
    private int maxImgCount_finish = 9; // 允许选择图片最大数

    List<DeviceParam.EnumValue> enumBySelf = new ArrayList<>();
    List<DeviceParam.EnumValue> enum_work_mode = new ArrayList<>();
    private MyPopupWindow popupWindow;
    private MyPopupWindow popupWindows;
    private boolean ismodify = true;
    private String dispatchId = "";
    private String disStatus;
    private String isVisible = "";//判断是否弹出对话框
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    public static final int REQUEST_CODE_SELECT_FINISH = 1000;
    public static final int REQUEST_CODE_PREVIEW_FINISH = 1001;
    private String dispatchIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_work_order;
    }

    @Override
    protected void initView() {

        txt_finish_byself.setTag("");
        txt_reciever.setTag("");
        txt_evaluate.setTag("");
        txt_worker_mode.setTag("0");
        edt_repairPerson.setText(App.sp.getName());
        edt_contactPhone.setText(App.sp.getPhone());
        txt_finish_time.setText(DateUtils.getMonth_Day_Hour_Min(new Date()));
        if (UserManegerList.WORKORDER_DISP()) {//调度员
            line_byself.setVisibility(View.GONE);
        }
        /* else if (UserManegerList.WORKORDER_ENGI()) {//工程师
            line_custom.setVisibility(View.GONE);
            line_receiver.setVisibility(View.GONE);
            line_finish_time.setVisibility(View.GONE);
            line_evaluate.setVisibility(View.GONE);
            line_byself.setVisibility(View.VISIBLE);
            DeviceParam data = new DeviceParam();
            DeviceParam.EnumValue itemByself = data.new EnumValue();
            DeviceParam.EnumValue itemByOther = data.new EnumValue();
            itemByself.setCode("1");
            itemByself.setValue("自己");
            itemByOther.setCode("0");
            itemByOther.setValue("其他人");
            enumBySelf.add(itemByself);
            enumBySelf.add(itemByOther);

        } else {//质检员和其他职位
            line_byself.setVisibility(View.GONE);
        }*/
        //工单模式
        DeviceParam data = new DeviceParam();
        DeviceParam.EnumValue item_mode_qiang = data.new EnumValue();
        DeviceParam.EnumValue item_mode_pai = data.new EnumValue();
        item_mode_qiang.setCode("0");
        item_mode_qiang.setValue("抢单");
        item_mode_pai.setCode("1");
        item_mode_pai.setValue("派单");

        enum_work_mode.add(item_mode_qiang);
        enum_work_mode.add(item_mode_pai);
        Intent intent = getIntent();
        if (intent != null) {
            dispatchIds = intent.getStringExtra("dispatchId");//工单号
            String isVisibles = intent.getStringExtra("isVisible");
            String contentName = intent.getStringExtra("contentName");
            if (contentName != null) {
                edt_task_describe.setText(contentName);
            }
            if (isVisibles != null) {
                isVisible = isVisibles;
            }
            if (dispatchIds != null) {
                dispatchId = dispatchIds;
                line_workNumber.setVisibility(View.VISIBLE);
                if (UserManegerList.WORKORDER_ENGI()) {
                    txt_repairWork.setVisibility(View.VISIBLE);
                }
            }
            ismodify = intent.getBooleanExtra("ismodify", true);
            if (ismodify == false) {//不可进行修改
                line_workorder.getFocus(true);
               /* line_byself.setVisibility(View.GONE);
                line_custom.setVisibility(View.VISIBLE);
                line_receiver.setVisibility(View.VISIBLE);
                line_finish_time.setVisibility(View.VISIBLE);
                line_evaluate.setVisibility(View.VISIBLE);*/
            }
            if (dispatchId != null && !dispatchId.equals("")) {
                presenter.onResume(this, dispatchId);//派工单
            }
        }
    }


    @Override
    protected void initListener() {
        gridview_repair.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapterFinish = new ImageAdapter(this, 0, selFinishList);
        gridview_repair.setAdapter(adapterFinish);
        gridview_repair.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected WorkOrderActivityPresenter getPresenter() {
        return new WorkOrderActivityPresenter();
    }

    @OnClick({R.id.iv_back, R.id.txt_sure, R.id.line_workorder, R.id.txt_reciever, R.id.txt_evaluate,
            R.id.txt_finish_time, R.id.txt_repairWork
            , R.id.txt_finish_byself, R.id.line_worker_mode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                close_key();
                finish();
                break;
            case R.id.txt_sure:
                setDataCommit();//提交工单
                break;
            case R.id.line_workorder:
                hiddenInput();//隐藏输入法
                break;
            case R.id.txt_reciever:
                if (isVisible.equals("")) {
                    Intent intent = new Intent(this, UserListActivity.class);
                    intent.putExtra("type", 4);
                    intent.putExtra("which_type", "CCB");
                    startActivityForResult(intent, REQUEST_CODE);
                }
                break;
            case R.id.txt_evaluate://评价人员
                Intent intents = new Intent(this, UserListActivity.class);
                intents.putExtra("type", 4);
                if (UserManegerList.WORKORDER_DISP()) {
                    intents.putExtra("which_type", "CCD");
                }
                startActivityForResult(intents, REQUEST_SECOND);
                break;
            case R.id.txt_finish_byself:
                showSortPopup(txt_finish_byself, enumBySelf);
                break;
            case R.id.txt_finish_time:
                setdatePicker();
                break;
            case R.id.line_worker_mode://工单模式
                if (isVisible.equals(""))
                    showSortPopup_work(txt_worker_mode, enum_work_mode);
                break;
            case R.id.txt_repairWork:
                startActivity(new Intent(this, EngineerRepairActivity.class).
                        putExtra("dispatchId", dispatchId));
                AllActivityManager.getInstance().removeOneActivity(this);
                break;
            default:
                break;
        }
    }

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
            popupWindow.dismiss();
        });
        //初始化页面，必须写在后面
        popupWindow.initView();
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

    }

    /**
     * 显示智能排序popup
     */
    private void showSortPopup_work(final TextView tv, final List<DeviceParam.EnumValue> list) {
        if (popupWindows == null) {
            popupWindows = new MyPopupWindow(this, getWindow());
        }
        //设置宽高
        popupWindows.setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置数据源
        popupWindows.setList(list);
        //设置监听
        popupWindows.setListener((adapterView, view, position, id) -> {
            String key = list.get(position).getCode();
            tv.setText(list.get(position).getValue());
            tv.setTag(key);
            if (list.get(position).getCode().equals("0")) {
                line_receiver.setVisibility(View.GONE);
                line_evaluate.setVisibility(View.GONE);
                txt_visivble.setVisibility(View.GONE);
                txt_worker_mode.setText("抢单");
                txt_worker_mode.setTag("0");
                txt_reciever.setText("");
                txt_reciever.setTag("");
            } else {
                line_receiver.setVisibility(View.VISIBLE);
                txt_visivble.setVisibility(View.VISIBLE);
            }
            popupWindows.dismiss();
        });
        //初始化页面，必须写在后面
        popupWindows.initView();
        popupWindows.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

    }

    private void setdatePicker() {

        // 时间选择器
        TimePickerView pvTime = new TimePickerView(this, TimePickerView.Type.ALL);
        pvTime.setTime(new Date());
        pvTime.setCyclic(true);
        pvTime.setCancelable(true);
        pvTime.setOnTimeSelectListener(date -> {
            txt_finish_time.setText(getTime(date));
        });
        pvTime.show();

    }


    public String getTime(Date date) {
        SimpleDateFormat format = null;
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return format.format(date);
    }


    //提交工单

    private void setDataCommit() {
        String custom_company = edt_customName.getText().toString();//客户单位
        String taskDescribes = edt_task_describe.getText().toString();//保修欸容
        String floorRoom = edt_floor_roomNumber.getText().toString();//楼房位置
        String repairPeople = edt_repairPerson.getText().toString();//报修人
        String phone = edt_contactPhone.getText().toString();//联系电话
        String work_mode = txt_worker_mode.getTag().toString();//工单模式
        String recieverPeople = null;//接单人
        if (txt_reciever.getTag() == null) {
            recieverPeople = "";
        } else {
            recieverPeople = txt_reciever.getTag().toString();
        }
        //   String evaluate = txt_evaluate.getTag().toString();//质检员
        String finishTime = txt_finish_time.getText().toString();//完成时间
        List<String> list_word = new ArrayList<>();
        list_word.add(custom_company);
        list_word.add(taskDescribes);
        list_word.add(floorRoom);
        list_word.add(repairPeople);
        list_word.add(phone);
        list_word.add(work_mode);
        if (work_mode.equals("0")) {
            list_word.add("");
        } else {
            list_word.add(recieverPeople);
        }
        list_word.add(finishTime);
        if (dispatchId != null && !dispatchId.equals("")) {
            list_word.add(dispatchId);
        }
        for (int i = 0; i < selFinishList.size(); i++) {
            list_word.add(selFinishList.get(i).path);
        }
        presenter.onCimmit(this, list_word, selFinishList.size());
    }


    @Override
    public void setText(Object data) {

    }


    @Override
    public void setCommit() {
        if (isVisible.equals("")) {
            ToastUtils.showToast(this, this.getString(R.string.commit_workorder));
        } else {
            ToastUtils.showToast(this, this.getString(R.string.modify_workorder));
        }


        finish();
    }

    @Override
    public void setDetailInfo(WorkOrderGet beans) {
        WorkOrderGet.ResobjBean bean = beans.getResobj();
        String status = bean.getDispState();
        int rStatus = Integer.parseInt(status);
        if (isVisible.equals("")) {
            if (rStatus >= 2) {
                GrabDialog.Builder builder = new GrabDialog.Builder(this);
                builder.setTitle("是否跳转");
                builder.setMessage("工程师已接单，是否跳转接单界面");
                builder.setPositiveButton("是", (dialog, which) -> {
                    dialog.dismiss();
                    startActivity(new Intent(WorkOrderActivity.this, EngineerRepairActivity.class).
                            putExtra("dispatchId", dispatchId));

                });
                builder.setNegativeButton("否",
                        (dialog, which) -> {
                            dialog.dismiss();
                        });
                builder.create().show();
            }
        }
        txt_workNumber.setText(bean.getDispatchNum() + "");
        edt_customName.setText(bean.getCompany());
        edt_task_describe.setText(bean.getContent());
        edt_floor_roomNumber.setText(bean.getLocation() + "");
        edt_repairPerson.setText(bean.getReportUser());
        edt_contactPhone.setText(bean.getPhone() + "");
        txt_reciever.setText(bean.getRepairUserName());
        txt_reciever.setTag(bean.getRepairUserId() + "");
        txt_finish_time.setText(bean.getReportTime());
        if (bean.getDispPattern().equals("0")) {
            txt_worker_mode.setText("抢单");
            line_receiver.setVisibility(View.GONE);
        } else {
            txt_worker_mode.setText("派单");
            line_receiver.setVisibility(View.VISIBLE);
        }
        txt_worker_mode.setTag(bean.getDispPattern());
    }

    @Override
    public void getStatus(String status) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.gridview_repair:
                IntentToImage(selFinishList, position, parent, REQUEST_CODE_SELECT_FINISH, REQUEST_CODE_PREVIEW_FINISH, maxImgCount_finish);//跳转到图库
                break;
        }
    }

    private void IntentToImage(ArrayList<ImageItem> selImageList, int position, AdapterView<?> parent, int one, int two, int maxImgCount) {
        if (position == parent.getAdapter().getCount() - 1 && position != 8) {
            // 打开选择,本次允许选择的数量
            ImagePicker.getInstance().setSelectLimit(
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (data != null) {
                txt_reciever.setText(data.getStringExtra("user"));
                txt_reciever.setTag(data.getStringExtra("uid"));
            }

        } else if (requestCode == REQUEST_SECOND) {
            txt_evaluate.setText(data.getStringExtra("user"));
            txt_evaluate.setTag(data.getStringExtra("uid"));
        }
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            // 添加图片返回
            if (data != null) {
                //工程师完成时上传照片
                if (requestCode == REQUEST_CODE_SELECT_FINISH) {
                    ArrayList<ImageItem> images = (ArrayList<ImageItem>) data
                            .getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                    if (images != null) {
                        Log.i(TAG, "图片的个数====" + images.size());
                    }
                    selFinishList.addAll(images);//回传过来的
                    adapterFinish.notifyDataSetChanged();
                }
            }
        }
        if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            // 预览图片返回
            if (data != null) {
                if (requestCode == REQUEST_CODE_PREVIEW_FINISH) {
                    ArrayList<ImageItem> images = (ArrayList<ImageItem>) data
                            .getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                    selFinishList.clear();
                    selFinishList.addAll(images);
                    adapterFinish.notifyDataSetChanged();
                }
            }
        }
    }

}
