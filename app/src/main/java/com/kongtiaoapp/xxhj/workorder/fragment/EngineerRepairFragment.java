package com.kongtiaoapp.xxhj.workorder.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.activites.ImageScaleActivity;
import com.kongtiaoapp.xxhj.adapter.ImageAdapter;
import com.kongtiaoapp.xxhj.afinal.UserManegerList;
import com.kongtiaoapp.xxhj.bean.DeviceParam;
import com.kongtiaoapp.xxhj.bean.MaterialBean;
import com.kongtiaoapp.xxhj.bean.WorkOrderGet;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.EngineerPresenter;
import com.kongtiaoapp.xxhj.mvp.view.EngineerView;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImageGridActivity;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImageItem;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImagePicker;
import com.kongtiaoapp.xxhj.ui.ImagePicker.ImagePreviewDelActivity;
import com.kongtiaoapp.xxhj.ui.NineGridView.NetworkImageAdapter;
import com.kongtiaoapp.xxhj.ui.NineGridView.NineGridView;
import com.kongtiaoapp.xxhj.ui.timeselector.activity.TimePickerView;
import com.kongtiaoapp.xxhj.ui.view.MyLinearlayout;
import com.kongtiaoapp.xxhj.ui.view.MyPopupWindow;
import com.kongtiaoapp.xxhj.ui.view.MyScrollView;
import com.kongtiaoapp.xxhj.ui.view.NoScrollGridView;
import com.kongtiaoapp.xxhj.utils.DateUtils;
import com.kongtiaoapp.xxhj.utils.GrabDialog;
import com.kongtiaoapp.xxhj.utils.StringUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;
import com.kongtiaoapp.xxhj.workorder.activity.WorkOrderActivity;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.kongtiaoapp.xxhj.R.id.txt_prospect;


/**
 * 工程师进行报修
 */
public class EngineerRepairFragment extends BaseFragment<EngineerPresenter, EngineerView> implements EngineerView, AdapterView.OnItemClickListener {
    @BindView(R.id.txt_workNumber)//工单编号
            TextView txt_workNumber;
    @BindView(R.id.txt_modifyForm)//是否显示修改报修单
            TextView txt_modifyForm;
    @BindView(R.id.edt_firm)
    TextView edt_firm;
    @BindView(R.id.task_describe)//保修内容
            TextView task_describe;
    @BindView(R.id.floor_roomNumber)//报修地点
            TextView floor_roomNumber;
    @BindView(R.id.edt_contactPhone)//联系电话
            TextView edt_contactPhone;
    @BindView(R.id.edt_repairPerson)//报修人
            TextView edt_repairPerson;
    @BindView(R.id.txt_worker_mode)//工单模式
            TextView txt_worker_mode;
    @BindView(R.id.line_formPeople)//接单人
            LinearLayout line_formPeople;
    @BindView(R.id.txt_formPerson)//接单人
            TextView txt_formPerson;
    @BindView(R.id.txt_finish_byself)//是否是工程师自己完成
            TextView txt_finish_byself;
    @BindView(R.id.txt_checkPerson)//质检员
            TextView txt_checkPerson;
    @BindView(R.id.txt_finish_timea)//完成时间
            TextView txt_finish_timea;
    @BindView(R.id.txt_professional)//专业
            TextView txt_professional;

    @BindView(R.id.line_line)
    LinearLayout line_line;
    @BindView(R.id.line_line1)
    LinearLayout line_line1;
    @BindView(R.id.line_line2)
    LinearLayout line_line2;
    @BindView(R.id.line_line3)
    LinearLayout line_line3;
    @BindView(R.id.line_line4)
    LinearLayout line_line4;
    @BindView(R.id.line_line5)
    LinearLayout line_line5;
    @BindView(R.id.edt_name_count)//名称
            EditText edt_name_count;
    @BindView(R.id.edt_name_count1)//名称
            EditText edt_name_count1;
    @BindView(R.id.edt_name_count2)//名称
            EditText edt_name_count2;
    @BindView(R.id.edt_name_count3)//名称
            EditText edt_name_count3;
    @BindView(R.id.edt_name_count4)//名称
            EditText edt_name_count4;
    @BindView(R.id.edt_name_count5)//名称
            EditText edt_name_count5;

    @BindView(R.id.edt_spec_count)//规格
            EditText edt_spec_count;
    @BindView(R.id.edt_spec_count1)//规格
            EditText edt_spec_count1;
    @BindView(R.id.edt_spec_count2)//规格
            EditText edt_spec_count2;
    @BindView(R.id.edt_spec_count3)//规格
            EditText edt_spec_count3;
    @BindView(R.id.edt_spec_count4)//规格
            EditText edt_spec_count4;
    @BindView(R.id.edt_spec_count5)//规格
            EditText edt_spec_count5;

    @BindView(R.id.edt_count_count)//数量
            EditText edt_count_count;
    @BindView(R.id.edt_count_count1)//数量
            EditText edt_count_count1;
    @BindView(R.id.edt_count_count2)//数量
            EditText edt_count_count2;
    @BindView(R.id.edt_count_count3)//数量
            EditText edt_count_count3;
    @BindView(R.id.edt_count_count4)//数量
            EditText edt_count_count4;
    @BindView(R.id.edt_count_count5)//数量
            EditText edt_count_count5;

    @BindView(R.id.edt_unit_count)//单价
            EditText edt_unit_count;
    @BindView(R.id.edt_unit_count1)//单价
            EditText edt_unit_count1;
    @BindView(R.id.edt_unit_count2)//单价
            EditText edt_unit_count2;
    @BindView(R.id.edt_unit_count3)//单价
            EditText edt_unit_count3;
    @BindView(R.id.edt_unit_count4)//单价
            EditText edt_unit_count4;
    @BindView(R.id.edt_unit_count5)//单价
            EditText edt_unit_count5;

    @BindView(R.id.txt1)
    TextView txt1;
    @BindView(R.id.txt2)
    TextView txt2;
    @BindView(R.id.txt3)
    TextView txt3;
    @BindView(R.id.txt4)
    TextView txt4;
    @BindView(R.id.txt5)
    TextView txt5;
    @BindView(R.id.txt_add_enegry_metarial)//添加耗用材料
            TextView txt_add_enegry_metarial;

    @BindView(R.id.sv_work_Order)
    MyScrollView sv_work_Order;

    //勘查区

    @BindView(R.id.line_pros)
    MyLinearlayout line_pros;
    @BindView(R.id.txt_order_date)//接单时间
            TextView txt_order_date;
    @BindView(R.id.txt_profession_date)//预计完成时间
            TextView txt_profession_date;
    @BindView(R.id.line_spros)
    LinearLayout line_spros;//勘查上报按钮
    @BindView(R.id.edt_prospect)//点击勘查上报
            EditText edt_prospect;

    //提交上报区间
    @BindView(R.id.line_finish)
    MyLinearlayout line_finish;
    @BindView(R.id.txt_finish_time)//完成时间
            TextView txt_finish_time;
    @BindView(R.id.txt_isFinish)//是否完成
            TextView txt_isFinish;
    @BindView(R.id.line_repairDepartment)//维修部门
            LinearLayout line_repairDepartment;
    @BindView(R.id.edt_erpairDepartment)//请填写维修部门
            EditText edt_repairDepartment;
    @BindView(R.id.line_finish_Time)
    LinearLayout line_finish_Time;//显示或者隐藏
    @BindView(R.id.edt_finish_situation)//完成情况
            EditText edt_finish_situation;
    @BindView(R.id.line_commit)
    LinearLayout line_commit;//提交上报按钮
    //评价区
    @BindView(R.id.line_evaluate)
    LinearLayout line_evaluate;
    @BindView(R.id.line_evaluate_commit)
    MyLinearlayout line_evaluate_commit;

    @BindView(R.id.txt_isPass)//是否合格
            TextView txt_isPass;
    @BindView(R.id.edt_evaluate)
    EditText edt_evaluate;
    @BindView(R.id.gridview)
    NoScrollGridView gridview;
    @BindView(R.id.line_workorder)
    MyLinearlayout line_workorder;
    @BindView(R.id.gridview_finish)//完成时 进行的图片上传
            NoScrollGridView gridview_finish;
    @BindView(R.id.iv_picture_finish)//获取图片勘察
            NineGridView iv_picture_finish;
    @BindView(R.id.iv_picture)
    NineGridView iv_picture;

    @BindView(R.id.line_acceptWork)
    MyLinearlayout line_acceptWork;//接单显示和隐藏
   /* //报修图片
    @BindView(R.id.iv_repair)
    NineGridView iv_repair;*/

    private String dispatchId = "";
    private String isFinish = "";
    private int finishStatus = 0;//完成状态
    private int visibleNumber = 1;
    private MyPopupWindow popupWindow;
    List<DeviceParam.EnumValue> enumValue = new ArrayList<>();
    List<DeviceParam.EnumValue> enumFinish = new ArrayList<>();
    List<DeviceParam.EnumValue> enumPass = new ArrayList<>();
    List<DeviceParam.EnumValue> enumBySelf = new ArrayList<>();
    private String isProspect = "0";
    private ArrayList<ImageItem> selRepairList = new ArrayList<>(); // 当前选择的所有图片  报修
    private ArrayList<ImageItem> selImageList = new ArrayList<>(); // 当前选择的所有图片  勘察
    private ArrayList<ImageItem> selFinishList = new ArrayList<>(); // 当前选择的所有图片 完成
    private ImageAdapter adapter;
    private ImageAdapter adapterFinish;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    public static final int REQUEST_CODE_SELECT_FINISH = 1000;
    public static final int REQUEST_CODE_PREVIEW_FINISH = 1001;
    private int maxImgCount = 9; // 允许选择图片最大数
    private int maxImgCount_finish = 9; // 允许选择图片最大数
    private static final Map<String, String> buildingTypeMap = new LinkedHashMap<String, String>() {{
        put("空调", "空调");
        put("强电", "强电");
        put("水暖", "水暖");
        put("水木", "木工");
        put("油漆", "油漆");
        put("泥水", "泥水");
        put("音响", "音响");
        put("清洁", "清洁");
        put("弱电", "弱电");
        put("其他", "其他");
    }};


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            dispatchId = arguments.getString("dispatchId");
        }

        ButterKnife.bind(this, view);
        return view;
    }


    private void getDataRepair() {
        txt_professional.setTag("");
        txt_isFinish.setTag("");
        edt_evaluate.setTag("");
        txt_isPass.setTag("");
        if (dispatchId != null && !dispatchId.equals("")) {
            presenter.onResume(mActivity, dispatchId);//派工单
        }

        enumValue.clear();
        DeviceParam data = new DeviceParam();
        for (String key : buildingTypeMap.keySet()) {
            DeviceParam.EnumValue item = data.new EnumValue();
            item.setCode(key);
            item.setValue(buildingTypeMap.get(key));
            enumValue.add(item);
        }
        DeviceParam.EnumValue itemFinish = data.new EnumValue();
        DeviceParam.EnumValue itemNoFinish = data.new EnumValue();
        DeviceParam.EnumValue itemPass = data.new EnumValue();
        DeviceParam.EnumValue itemNoPass = data.new EnumValue();
        DeviceParam.EnumValue itemPassOther = data.new EnumValue();
        DeviceParam.EnumValue itemByself = data.new EnumValue();
        DeviceParam.EnumValue itemByOther = data.new EnumValue();
        itemByself.setCode("byself");
        itemByself.setValue("自己");
        itemByOther.setCode("byother");
        itemByOther.setValue("其他人");
        itemFinish.setCode("1");
        itemFinish.setValue("已完成");
        itemNoFinish.setCode("0");
        itemNoFinish.setValue("未完成");
        itemPass.setCode("1");
        itemPass.setValue("审核通过");
        itemNoPass.setCode("0");
        itemNoPass.setValue("审核未通过");
        itemPassOther.setCode("2");
        itemPassOther.setValue("其他部门维修");

        enumFinish.add(itemFinish);
        enumFinish.add(itemNoFinish);
        enumPass.add(itemPass);
        enumPass.add(itemNoPass);
        enumPass.add(itemPassOther);
        enumBySelf.add(itemByself);
        enumBySelf.add(itemByOther);
        initListers();
    }

    @Override
    public EngineerPresenter getPresenter() {
        return new EngineerPresenter();
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.activity_engineer, null);
    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void onResume() {
        super.onResume();
        getDataRepair();
    }

    private void initListers() {
        //勘察上传图片
        gridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new ImageAdapter(mActivity, 0, selImageList);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(this);
        //完成上传图片
        gridview_finish.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapterFinish = new ImageAdapter(mActivity, 0, selFinishList);
        gridview_finish.setAdapter(adapterFinish);
        gridview_finish.setOnItemClickListener(this);
    }

    private void setComment() {
        line_spros.setVisibility(View.GONE);
        line_commit.setVisibility(View.GONE);
        line_evaluate_commit.setVisibility(View.GONE);
    }

    @OnClick({R.id.txt_finish_byself, R.id.line_workorder
            , R.id.txt_add_enegry_metarial, txt_prospect
            , R.id.txt_professional, R.id.txt_finish_time
            , R.id.txt_profession_date, R.id.txt_isFinish, R.id.txt_isPass, R.id.txt_sure
            , R.id.txt_evaluate, R.id.txt_modifyForm, R.id.txt_acceptWork})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.line_workorder:
                hiddenInputs();
                break;
            case R.id.txt_finish_byself:
                showSortPopup(txt_finish_byself, enumBySelf);
                break;
            case R.id.txt_add_enegry_metarial:
                ++visibleNumber;
                if (visibleNumber == 2) {
                    line_line1.setVisibility(View.VISIBLE);
                    edt_name_count1.setVisibility(View.VISIBLE);
                    edt_spec_count1.setVisibility(View.VISIBLE);
                    edt_count_count1.setVisibility(View.VISIBLE);
                    edt_unit_count1.setVisibility(View.VISIBLE);
                    txt1.setVisibility(View.VISIBLE);
                } else if (visibleNumber == 3) {
                    line_line2.setVisibility(View.VISIBLE);
                    edt_name_count2.setVisibility(View.VISIBLE);
                    edt_spec_count2.setVisibility(View.VISIBLE);
                    edt_count_count2.setVisibility(View.VISIBLE);
                    edt_unit_count2.setVisibility(View.VISIBLE);
                    txt2.setVisibility(View.VISIBLE);
                } else if (visibleNumber == 4) {
                    line_line3.setVisibility(View.VISIBLE);
                    edt_name_count3.setVisibility(View.VISIBLE);
                    edt_spec_count3.setVisibility(View.VISIBLE);
                    edt_count_count3.setVisibility(View.VISIBLE);
                    edt_unit_count3.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                } else if (visibleNumber == 5) {
                    line_line4.setVisibility(View.VISIBLE);
                    edt_name_count4.setVisibility(View.VISIBLE);
                    edt_spec_count4.setVisibility(View.VISIBLE);
                    edt_count_count4.setVisibility(View.VISIBLE);
                    edt_unit_count4.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                } else if (visibleNumber == 6) {
                    line_line5.setVisibility(View.VISIBLE);
                    edt_name_count5.setVisibility(View.VISIBLE);
                    edt_spec_count5.setVisibility(View.VISIBLE);
                    edt_count_count5.setVisibility(View.VISIBLE);
                    edt_unit_count5.setVisibility(View.VISIBLE);
                    txt5.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.txt_sure:
                commitSave();//报修完提交
                break;
            case txt_prospect://工程师勘查上报
                isProspect = "1";
                commitProspect();
                break;

            case R.id.txt_professional:
                showSortPopup(txt_professional, enumValue);//选择专业
                break;
            case R.id.txt_finish_time:
                setdatePicker(txt_finish_time);
                break;
            case R.id.txt_profession_date:
                setdatePicker(txt_profession_date);
                break;
            case R.id.txt_isFinish://工程师选择自己是否完成
                showSortPopup(txt_isFinish, enumFinish);//选择专业
                break;
            case R.id.txt_isPass://质检员选择工程师是否合格
                showSortPopup(txt_isPass, enumPass);//选择专业
                break;
            case R.id.txt_evaluate:
                commitEvaluate();//提交评价
                break;
            case R.id.txt_modifyForm://修改报修单
                if (!dispatchId.equals("")) {
                    startActivity(new Intent(mActivity, WorkOrderActivity.class).
                            putExtra("dispatchId", dispatchId).putExtra("ismodify", true).putExtra("isVisible", "false"));
                }
                break;
            case R.id.txt_acceptWork://接单
                presenter.setOrderTaking(mActivity, dispatchId);
                break;
            default:

                break;
        }
    }

    private void commitEvaluate() {
        if (txt_isPass.getText().toString().trim().equals("")) {
            ToastUtils.showToast(mActivity, "请选择是否通过");
            return;
        }
        String evaluates = edt_evaluate.getText().toString().trim();
        if (evaluates == null || evaluates.equals("")) {
            ToastUtils.showToast(mActivity, "请进行评价!");
            return;
        }
        List<String> list = new ArrayList<>();
        list.add(dispatchId);
        list.add(txt_isPass.getTag().toString());
        list.add(evaluates);
        presenter.commitEvaluate(mActivity, list);
    }

    protected void hiddenInputs() {
        //没错，下面这一坨就是隐藏软键盘的代码
        ((InputMethodManager) mActivity.getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(edt_finish_situation.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void commitSave() {
        List<String> list = new ArrayList<>();
        list.add(dispatchId);
        if (txt_isFinish.getText().toString().equals("已完成")) {
            isFinish = "1";
            list.add(txt_isFinish.getTag().toString());
            String finish_time = txt_finish_time.getText().toString().trim();
            if (finish_time.equals("")) {
                ToastUtils.showToast(mActivity, getString(R.string.finish_date));
                return;
            }
            list.add(finish_time);
        } else {
            list.add(txt_isFinish.getTag().toString());
            list.add(edt_repairDepartment.getText().toString().trim());//维修部门
            isFinish = "";
        }
        if (edt_finish_situation.getText().toString().equals("")) {
            ToastUtils.showToast(mActivity, "请填写任务进度");
            return;
        }
        list.add(edt_finish_situation.getText().toString().trim());
        for (int i = 0; i < selFinishList.size(); i++) {
            list.add(selFinishList.get(i).path);
        }
        presenter.CommitForm(mActivity, list, isFinish);
    }

    /**
     * 显示智能排序popup
     */
    private void showSortPopup(final TextView tv, final List<DeviceParam.EnumValue> list) {
        if (popupWindow == null) {
            popupWindow = new MyPopupWindow(mActivity, mActivity.getWindow());
        }
        //设置宽高
        popupWindow.setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置数据源
        popupWindow.setList(list);
        //设置监听
        popupWindow.setListener((adapterView, view, position, id) -> {
            String key = list.get(position).getCode();
            tv.setText(list.get(position).getValue());
            if (list.get(position).getValue().equals("未完成")) {
                line_finish_Time.setVisibility(View.GONE);
                line_repairDepartment.setVisibility(View.VISIBLE);
            } else if (list.get(position).getValue().equals("已完成")) {
                line_finish_Time.setVisibility(View.VISIBLE);
                line_repairDepartment.setVisibility(View.GONE);
            }
            tv.setTag(key);
            popupWindow.dismiss();
        });
        //初始化页面，必须写在后面
        popupWindow.initView();
        popupWindow.showAtLocation(mActivity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

    }


    private void setdatePicker(TextView txt_time) {
        TimePickerView pickerView = new TimePickerView(mActivity, TimePickerView.Type.ALL);
        pickerView.setCyclic(true);
        pickerView.setTime(new Date());
        pickerView.setCancelable(true);
        pickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                String month_day_hour_min = DateUtils.getMonth_Day_Hour_Min(date);
                txt_time.setText(month_day_hour_min);
            }
        });
        pickerView.show();
    }


    private void commitProspect() {
        String order_date = txt_order_date.getText().toString();//接单日期
        String professional = txt_professional.getTag().toString();//专业
        String prospect = edt_prospect.getText().toString().trim();//勘查情况
        String profession_date = txt_profession_date.getText().toString().trim();//预计完成时间
        if (order_date.equals("")) {
            ToastUtils.showToast(mActivity, getString(R.string.order_date));
            return;
        }
        if (professional.equals("")) {
            ToastUtils.showToast(mActivity, getString(R.string.professional));
            return;
        }
        if (prospect.equals("")) {
            ToastUtils.showToast(mActivity, getString(R.string.prospect));
            return;
        }
        if (profession_date.equals("")) {
            ToastUtils.showToast(mActivity, getString(R.string.pro_finish_time));
            return;
        }

        List<String> list = new ArrayList<>();
        list.add(dispatchId);
        list.add(order_date);
        list.add(professional);
        list.add(prospect);
        list.add(profession_date);
        Map<String, String> map = new LinkedHashMap<>();
        MaterialBean bean = new MaterialBean();
        MaterialBean bean1 = new MaterialBean();
        MaterialBean bean2 = new MaterialBean();
        MaterialBean bean3 = new MaterialBean();
        MaterialBean bean4 = new MaterialBean();
        MaterialBean bean5 = new MaterialBean();
        List<MaterialBean> listBean = new ArrayList<>();
        bean.setName(edt_name_count.getText().toString().trim());
        bean.setSpec(edt_spec_count.getText().toString().trim());
        bean.setAmount(edt_count_count.getText().toString().trim());
        bean.setPrice(edt_unit_count.getText().toString().trim());

        bean1.setName(edt_name_count1.getText().toString().trim());
        bean1.setSpec(edt_spec_count1.getText().toString().trim());
        bean1.setAmount(edt_count_count1.getText().toString().trim());
        bean1.setPrice(edt_unit_count1.getText().toString().trim());

        bean2.setName(edt_name_count2.getText().toString().trim());
        bean2.setSpec(edt_spec_count2.getText().toString().trim());
        bean2.setAmount(edt_count_count2.getText().toString().trim());
        bean2.setPrice(edt_unit_count2.getText().toString().trim());

        bean3.setName(edt_name_count3.getText().toString().trim());
        bean3.setSpec(edt_spec_count3.getText().toString().trim());
        bean3.setAmount(edt_count_count3.getText().toString().trim());
        bean3.setPrice(edt_unit_count3.getText().toString().trim());

        bean4.setName(edt_name_count4.getText().toString().trim());
        bean4.setSpec(edt_spec_count4.getText().toString().trim());
        bean4.setAmount(edt_count_count4.getText().toString().trim());
        bean4.setPrice(edt_unit_count4.getText().toString().trim());

        bean5.setName(edt_name_count5.getText().toString().trim());
        bean5.setSpec(edt_spec_count5.getText().toString().trim());
        bean5.setAmount(edt_count_count5.getText().toString().trim());
        bean5.setPrice(edt_unit_count5.getText().toString().trim());
        listBean.add(bean);
        listBean.add(bean1);
        listBean.add(bean2);
        listBean.add(bean3);
        listBean.add(bean4);
        listBean.add(bean5);
        for (int i = 0; i < selImageList.size(); i++) {
            list.add(selImageList.get(i).path);
        }
        presenter.onPros(mActivity, list, listBean);

    }

    @Override
    public void setDetailInfo(WorkOrderGet beans) {
        WorkOrderGet.ResobjBean bean = beans.getResobj();
        finishStatus = Integer.parseInt(bean.getDispState());
        if (UserManegerList.WORKORDER_ENGI()) {//工程师

            line_evaluate_commit.setVisibility(View.GONE);
            line_evaluate.setVisibility(View.GONE);
            if (finishStatus == 4 || finishStatus == 5) {
                setComment();
                line_workorder.getFocus(true);
            } else if (finishStatus == 6 || finishStatus == 7) {
                line_spros.setVisibility(View.GONE);
                line_commit.setVisibility(View.GONE);
                line_evaluate_commit.setVisibility(View.GONE);
                line_evaluate.setVisibility(View.VISIBLE);
                line_workorder.getFocus(true);
            }
            if (UserManegerList.WORKORDER_INSP()) {//质检员
                if (finishStatus == 4 || finishStatus == 5) {//异常
                    line_evaluate.setVisibility(View.VISIBLE);
                    line_evaluate_commit.setVisibility(View.VISIBLE);
                } else if (finishStatus == 6 || finishStatus == 7) {
                    line_finish.setVisibility(View.VISIBLE);
                    line_evaluate.setVisibility(View.VISIBLE);
                }
            }
        } else {//不带工程师得
            //不同角色界面显示风格不一样
            if (UserManegerList.WORKORDER_INSP()) {//质检员

                setComment();
                if (finishStatus == 2) {
                    line_pros.setVisibility(View.GONE);
                    line_finish.setVisibility(View.GONE);
                    line_evaluate.setVisibility(View.GONE);
                }
                if (finishStatus == 3) {//勘查
                    line_workorder.getFocus(true);
                    line_finish.setVisibility(View.GONE);
                    line_evaluate.setVisibility(View.GONE);
                } else if (finishStatus == 4) {//异常
                    line_pros.getFocus(true);
                    line_finish.getFocus(true);
                    line_evaluate.setVisibility(View.VISIBLE);
                    line_evaluate_commit.setVisibility(View.VISIBLE);
                } else if (finishStatus == 5) {
                    line_pros.getFocus(true);
                    line_finish.getFocus(true);
                    line_evaluate_commit.setVisibility(View.VISIBLE);
                } else if (finishStatus == 6 || finishStatus == 7) {
                    line_workorder.getFocus(true);
                    line_finish.setVisibility(View.VISIBLE);
                    line_evaluate.setVisibility(View.VISIBLE);
                }
            } else if (UserManegerList.WORKORDER_DISP() || UserManegerList.WORKORDER_MANAGER()) {//调度员/管理员
                line_workorder.getFocus(true);
                setComment();//共同隐藏的
                if (finishStatus == 2) {
                    line_pros.setVisibility(View.GONE);
                    line_finish.setVisibility(View.GONE);
                    line_evaluate.setVisibility(View.GONE);
                } else if (finishStatus == 3) {
                    line_finish.setVisibility(View.GONE);
                    line_evaluate.setVisibility(View.GONE);
                } else if (finishStatus == 4 || finishStatus == 5) {
                    line_finish.setVisibility(View.VISIBLE);
                    line_evaluate.setVisibility(View.GONE);
                } else if (finishStatus == 6 || finishStatus == 7) {
                    line_finish.setVisibility(View.VISIBLE);
                    line_evaluate.setVisibility(View.VISIBLE);
                }

            } else if (UserManegerList.WORKORDER_EDITOR()) {
                txt_modifyForm.setVisibility(View.VISIBLE);
                if (finishStatus == 2) {
                    line_evaluate_commit.setVisibility(View.GONE);
                    line_pros.setVisibility(View.GONE);
                    line_finish.setVisibility(View.GONE);
                    line_evaluate.setVisibility(View.GONE);
                } else if (finishStatus == 3) {
                    line_finish.setVisibility(View.GONE);
                    line_evaluate.setVisibility(View.GONE);
                    line_commit.setVisibility(View.GONE);
                    line_evaluate_commit.setVisibility(View.GONE);
                } else if (finishStatus == 4 || finishStatus == 5) {
                    line_finish.setVisibility(View.VISIBLE);
                    line_evaluate.setVisibility(View.GONE);
                    line_evaluate_commit.setVisibility(View.GONE);
                } else if (finishStatus == 6 || finishStatus == 7) {
                    line_finish.setVisibility(View.VISIBLE);
                    line_evaluate.setVisibility(View.VISIBLE);
                }

            }
        }
        if (UserManegerList.WORKORDER_ENGI() && (finishStatus == 0 || finishStatus == 1)) {//是工程师，并且是以提交状态
            GrabDialog.Builder builder = new GrabDialog.Builder(mActivity);
            builder.setTitle("是否接单");
            builder.setMessage("报修描述:" + bean.getLocation() + bean.getContent());
            builder.setPositiveButton("是", (dialog, which) -> {
                presenter.setOrderTaking(mActivity, dispatchId);
                dialog.dismiss();
            });
            builder.setNegativeButton("否",
                    (dialog, which) -> {
                        dialog.dismiss();
                        line_pros.setVisibility(View.GONE);
                        line_finish.setVisibility(View.GONE);
                        line_evaluate.setVisibility(View.GONE);
                        line_acceptWork.setVisibility(View.VISIBLE);
                    });
            builder.create().show();
        }
        if (bean.getDispatchNum() != null) {
            txt_workNumber.setText(bean.getDispatchNum() + "");
        }
        if (bean.getCompany() != null) {
            edt_firm.setText(bean.getCompany());
        }
        if (bean.getContent() != null) {
            task_describe.setText(bean.getContent());
        }
        if (bean.getLocation() != null) {
            floor_roomNumber.setText(bean.getLocation());
        }
        if (bean.getReportUser() != null) {
            edt_repairPerson.setText(bean.getReportUser());
        }
        if (bean.getPhone() != null) {
            edt_contactPhone.setText(bean.getPhone());
        }
        String dispPattern = bean.getDispPattern();
        if (bean.getDispPattern() == null) {
            dispPattern = "0";
        }
        if (bean.getDispPattern().equals("0")) {
            txt_worker_mode.setText("抢单");
            line_formPeople.setVisibility(View.GONE);
        } else {
            txt_worker_mode.setText("派单");
            if (bean.getRepairUserName() != null) {
                txt_formPerson.setText(bean.getRepairUserName());
            }
        }


        if (bean.getEvalUserName() != null) {
            txt_checkPerson.setText(bean.getEvalUserName());
        }
        if (bean.getReportTime() != null) {
            txt_finish_timea.setText(bean.getReportTime());
        }
        if (bean.getOrderTime() != null) {
            txt_order_date.setText(bean.getOrderTime());
        }
        if (!bean.getDomain().equals("")) {
            txt_professional.setText(bean.getDomain());
            txt_professional.setTag("1");
        }

        edt_prospect.setText(bean.getPrspDesc());
        if (bean.getEstimateTime() != null) {
            txt_profession_date.setText(bean.getEstimateTime());

        }
        if (bean.getPrspPhotoArr() != null && !bean.getPrspPhotoArr().isEmpty()) {
            gridview.setVisibility(View.GONE);
            iv_picture.setVisibility(View.VISIBLE);
            NetworkImageAdapter adapter = new NetworkImageAdapter(mActivity, bean.getPrspPhotoArr());
            iv_picture.setAdapter(adapter);
            final String[] arr = (String[]) bean.getPrspPhotoArr().toArray(new String[bean.getPrspPhotoArr().size()]);
            iv_picture.setOnImageClickListener(new NineGridView.OnImageClickListener() {
                @Override
                public void onImageCilcked(int position, View view) {
                    startActivity(new Intent(mActivity, ImageScaleActivity.class).putExtra("urls", arr).putExtra("currentItem", position));
//                ImageScaleActivity.instance.overridePendingTransition(R.anim.activity_translate_in,0);
                }
            });
        }
        List<WorkOrderGet.ResobjBean.MaterialArrBean> materialArr = bean.getMaterialArr();
        if (materialArr != null && !materialArr.isEmpty()) {
            edt_name_count.setText(materialArr.get(0).getName());
            edt_name_count1.setText(materialArr.get(1).getName());
            edt_name_count2.setText(materialArr.get(2).getName());
            edt_name_count3.setText(materialArr.get(3).getName());
            edt_name_count4.setText(materialArr.get(4).getName());
            edt_name_count5.setText(materialArr.get(5).getName());

            edt_spec_count.setText(materialArr.get(0).getSpec());
            edt_spec_count1.setText(materialArr.get(1).getSpec());
            edt_spec_count2.setText(materialArr.get(2).getSpec());
            edt_spec_count3.setText(materialArr.get(3).getSpec());
            edt_spec_count4.setText(materialArr.get(4).getSpec());
            edt_spec_count5.setText(materialArr.get(5).getSpec());

            edt_count_count.setText(materialArr.get(0).getAmount());
            edt_count_count1.setText(materialArr.get(1).getAmount());
            edt_count_count2.setText(materialArr.get(2).getAmount());
            edt_count_count3.setText(materialArr.get(3).getAmount());
            edt_count_count4.setText(materialArr.get(4).getAmount());
            edt_count_count5.setText(materialArr.get(5).getAmount());

            edt_unit_count.setText(materialArr.get(0).getPrice());
            edt_unit_count1.setText(materialArr.get(1).getPrice());
            edt_unit_count2.setText(materialArr.get(2).getPrice());
            edt_unit_count3.setText(materialArr.get(3).getPrice());
            edt_unit_count4.setText(materialArr.get(4).getPrice());
            edt_unit_count5.setText(materialArr.get(5).getPrice());
        }
        if (bean.getIsFinish().equals("0")) {
            line_finish_Time.setVisibility(View.GONE);
            line_repairDepartment.setVisibility(View.VISIBLE);
            edt_repairDepartment.setText("" + bean.getDepartment());
            txt_isFinish.setTag("0");
            txt_isFinish.setText("未完成");
        } else if (bean.getIsFinish().equals("1")) {
            line_finish_Time.setVisibility(View.VISIBLE);
            line_repairDepartment.setVisibility(View.GONE);
            txt_isFinish.setTag("1");
            if (bean.getFinishTime() != null) {
                txt_finish_time.setText(bean.getFinishTime());
            }
            txt_isFinish.setText("已完成");
        }
        if (bean.getFinishPhotoArr() != null && !bean.getFinishPhotoArr().isEmpty()) {
            gridview_finish.setVisibility(View.GONE);
            iv_picture_finish.setVisibility(View.VISIBLE);
            NetworkImageAdapter adapter = new NetworkImageAdapter(mActivity, bean.getFinishPhotoArr());
            iv_picture_finish.setAdapter(adapter);
            final String[] arr = (String[]) bean.getFinishPhotoArr().toArray(new String[bean.getFinishPhotoArr().size()]);
            iv_picture_finish.setOnImageClickListener(new NineGridView.OnImageClickListener() {
                @Override
                public void onImageCilcked(int position, View view) {
                    startActivity(new Intent(mActivity, ImageScaleActivity.class).putExtra("urls", arr).putExtra("currentItem", position));
//                ImageScaleActivity.instance.overridePendingTransition(R.anim.activity_translate_in,0);
                }
            });
        }
        if (StringUtils.isNotBlank(bean.getFinishDesc())) {
            edt_finish_situation.setText(bean.getFinishDesc());
        }
        if (bean.getIsQualified() != null) {
            if (bean.getIsQualified().equals("0")) {
                txt_isPass.setText("审核未通过");
                txt_isPass.setTag("0");
            } else if (bean.getIsQualified().equals("1")) {
                txt_isPass.setText("审核通过");
                txt_isPass.setTag("1");
            } else if (bean.getIsQualified().equals("2")) {
                txt_isPass.setText("其他部门维修");
                txt_isPass.setTag("2");
            }
        }
        if (!bean.getEvaluateDesc().equals("")) {
            edt_evaluate.setText(bean.getEvaluateDesc());
            if (finishStatus == 4) {//质检员并且为异常状态
                line_evaluate.setVisibility(View.VISIBLE);
                if (UserManegerList.WORKORDER_ENGI()) {
                    setComment();
                    line_evaluate.setVisibility(View.VISIBLE);
                    line_workorder.getFocus(true);
                } else if (UserManegerList.WORKORDER_DISP() || UserManegerList.WORKORDER_MANAGER()) {
                    line_finish.setVisibility(View.GONE);
                }

            }
        }
    }

    @Override
    public void setOrderTaking(String orderTime) {//接单
        ToastUtils.showToast(mActivity, this.getString(R.string.order_succeed));
        txt_order_date.setText(orderTime);
        line_pros.setVisibility(View.VISIBLE);
        line_finish.setVisibility(View.VISIBLE);
        line_evaluate.setVisibility(View.VISIBLE);
        line_acceptWork.setVisibility(View.GONE);
    }

    @Override
    public void setCommit() {
        ToastUtils.showToast(mActivity, this.getString(R.string.commit_succeed));
        mActivity.finish();
    }

    @Override
    public void setCommitEvaluate() {
        ToastUtils.showToast(mActivity, "评价成功");
        mActivity.finish();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.gridview:
                IntentToImage(selImageList, position, parent, REQUEST_CODE_SELECT, REQUEST_CODE_PREVIEW, maxImgCount);//跳转到图库
                break;
            case R.id.gridview_finish:
                IntentToImage(selFinishList, position, parent, REQUEST_CODE_SELECT_FINISH, REQUEST_CODE_PREVIEW_FINISH, maxImgCount_finish);//跳转到图库
                break;
        }

    }

    private void IntentToImage(ArrayList<ImageItem> selImageList, int position, AdapterView<?> parent, int one, int two, int maxImgCount) {
        if (position == parent.getAdapter().getCount() - 1 && position != 8) {
            // 打开选择,本次允许选择的数量
            ImagePicker.getInstance(true).setSelectLimit(
                    maxImgCount - selImageList.size());
            Intent intent = new Intent(mActivity, ImageGridActivity.class);
            startActivityForResult(intent, one);
        } else {
            Intent intentPreview = new Intent(mActivity,
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
                if (requestCode == REQUEST_CODE_SELECT) {//工程师勘查上传的照片
                    ArrayList<ImageItem> images = (ArrayList<ImageItem>) data
                            .getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                    selImageList.addAll(images);//回传过来的
                    adapter.notifyDataSetChanged();
                } else if (requestCode == REQUEST_CODE_SELECT_FINISH) {//工程师完成时上传照片
                    ArrayList<ImageItem> images = (ArrayList<ImageItem>) data
                            .getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                    selFinishList.addAll(images);//回传过来的
                    adapterFinish.notifyDataSetChanged();
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
                    adapter.notifyDataSetChanged();
                } else if (requestCode == REQUEST_CODE_PREVIEW_FINISH) {
                    ArrayList<ImageItem> images = (ArrayList<ImageItem>) data
                            .getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                    selFinishList.clear();
                    selFinishList.addAll(images);
                    adapterFinish.notifyDataSetChanged();
                }

            }

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
