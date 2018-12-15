package com.kongtiaoapp.xxhj.activites;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.ChangeShiftBean;
import com.kongtiaoapp.xxhj.bean.DeviceParam;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.moduleipl.ChangeShiftsPresenter;
import com.kongtiaoapp.xxhj.mvp.view.ChangeShiftsView;
import com.kongtiaoapp.xxhj.ui.view.LinePathView;
import com.kongtiaoapp.xxhj.ui.view.MyPopupWindow;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 交接班
 */
public class ChangeShiftsActivity extends BaseActivity<ChangeShiftsPresenter, ChangeShiftsView> implements ChangeShiftsView {
    @BindView(R.id.lpv_esign)
    LinePathView lpv_esign;
    @BindView(R.id.txt_cstContent)
    TextView txt_cstContent;//交接班类别
    @BindView(R.id.txt_csTime)
    TextView txt_csTime;//交接班时间
    @BindView(R.id.img_eSign)
    ImageView img_eSign;
    @BindView(R.id.txt_sign)
    TextView txt_sign;//签名区
    List<DeviceParam.EnumValue> enumValue = new ArrayList<>();
    List<DeviceParam.EnumValue> enumDuty = new ArrayList<>();
    public static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "qm.png";
    private DeviceParam data;
    private String esigns;
    private String isFirstDown;//1时代表要隐藏
    private List<ChangeShiftBean.ResobjBean> list = new ArrayList<>();
    private int csPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_e__signatrue;
    }

    @Override
    protected void initView() {
        //1代表显示签名去，其他代表隐藏
        esigns = "1";
        lpv_esign.setPaintWidth(15);
    }

    @Override
    protected void initListener() {
        isFirstDown = "1";
        lpv_esign.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (isFirstDown.equals("1")) {
                            txt_sign.setVisibility(View.GONE);
                            isFirstDown = "2";
                        }
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        data = new DeviceParam();
    }

    @Override
    protected ChangeShiftsPresenter getPresenter() {
        return new ChangeShiftsPresenter();
    }

    @OnClick({R.id.iv_back, R.id.txt_save, R.id.txt_cstContent,
            R.id.txt_saveSign, R.id.img_eSign, R.id.fl_eSign,
            R.id.txt_cst, R.id.txt_csTime, R.id.txt_clearSign})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_save:
                //提交签名
                commitSign();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.txt_cstContent:
                if (!enumValue.isEmpty()) {
                    showSortPopup(txt_cstContent, enumValue);
                } else {
                    presenter.CST(this);
                }
                break;
            case R.id.txt_saveSign://保存签名
                if (lpv_esign.getTouched()) {
                    try {
                        img_eSign.setTag("1");
                        lpv_esign.setBackColor(getResources().getColor(R.color.f0f));
                        lpv_esign.save(path, true, 10);
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 2;
                        Bitmap bm = BitmapFactory.decodeFile(path, options);
                        img_eSign.setImageBitmap(bm);
                        img_eSign.setVisibility(View.VISIBLE);
                        lpv_esign.clear();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    ToastUtils.showToast(this, getString(R.string.e_nosigntrue));
                    return;
                }
                break;
            case R.id.img_eSign:
                if (img_eSign.getTag() == null) {
                    ToastUtils.showToast(this, getString(R.string.p_e_sign));
                }
                break;
            case R.id.fl_eSign:
                if (esigns.equals("1")) {
                    txt_sign.setVisibility(View.GONE);
                }
                esigns = "2";
                break;
            case R.id.txt_cst:
                //  startActivity(new Intent(this, RecordFormDetailActivity.class));
                break;
            case R.id.txt_csTime:
                if (list.isEmpty()) {
                    ToastUtils.showToast(this, "请先选择交接班类别");
                    return;
                }
                ChangeShiftBean.ResobjBean.InfoBean infoBean = list.get(csPosition).getInfo();
                if (infoBean != null) {
                    List<ChangeShiftBean.ResobjBean.InfoBean.PowerDutyBean> listDuty = infoBean.getPowerDuty();
                    enumDuty.clear();

                    for (int i = 0; i < listDuty.size(); i++) {
                        DeviceParam.EnumValue item = this.data.new EnumValue();
                        item.setCode(listDuty.get(i).getMark());
                        item.setValue(listDuty.get(i).getCShifts());
                        enumDuty.add(item);
                    }
                    showSortPopupDuty(txt_csTime, enumDuty);
                }
                break;
            case R.id.txt_clearSign:
                lpv_esign.clear();
                esigns = "1";
                isFirstDown="1";
                txt_sign.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    private void commitSign() {
        List list = new ArrayList();
        if (txt_cstContent.getTag() != null) {
            list.add(txt_cstContent.getTag());
        } else {
            ToastUtils.showToast(this, getString(R.string.p_cst));
            return;
        }
        if (txt_csTime.getTag() != null) {
            list.add(txt_csTime.getTag());
        } else {
            ToastUtils.showToast(this, getString(R.string.p_cstime));
            return;
        }
        if (img_eSign.getTag() != null && !img_eSign.getTag().equals("")) {
            list.add(path);
        } else {
            ToastUtils.showToast(this, getString(R.string.p_e_sign));
            return;
        }
        presenter.onResume(this, list);

    }

    private MyPopupWindow popupWindow;

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
            tv.setText(list.get(position).getValue());
            tv.setTag(list.get(position).getCode());
            csPosition = position;
            popupWindow.dismiss();
        });
        //初始化页面，必须写在后面
        popupWindow.initView();
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    private void showSortPopupDuty(final TextView tv, final List<DeviceParam.EnumValue> list) {
        if (popupWindow == null) {
            popupWindow = new MyPopupWindow(mContext, getWindow());
        }
        //设置宽高
        popupWindow.setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置数据源
        popupWindow.setList(list);
        //设置监听
        popupWindow.setListener((adapterView, view, position, id) -> {
            tv.setText(list.get(position).getValue());
            tv.setTag(list.get(position).getCode());
            popupWindow.dismiss();
        });
        //初始化页面，必须写在后面
        popupWindow.initView();
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void getCST(Object data) {
        ChangeShiftBean bean = (ChangeShiftBean) data;
        list = bean.getResobj();
        for (int i = 0; i < list.size(); i++) {
            DeviceParam.EnumValue item = this.data.new EnumValue();
            item.setCode(list.get(i).getDeviceId());
            item.setValue(list.get(i).getName());
            enumValue.add(item);
        }
        showSortPopup(txt_cstContent, enumValue);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lpv_esign.clear();
    }
}
