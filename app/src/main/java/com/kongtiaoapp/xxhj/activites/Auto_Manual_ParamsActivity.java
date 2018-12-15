package com.kongtiaoapp.xxhj.activites;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.Item_Auto_ParamsAdapter;
import com.kongtiaoapp.xxhj.bean.Auto_Manual_ParamsBean;
import com.kongtiaoapp.xxhj.mvp.presenter.Auto_Manual_ParamsPresenter;
import com.kongtiaoapp.xxhj.mvp.view.Auto_Manual_ParamsView;
import com.kongtiaoapp.xxhj.ui.view.MyScrollView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 自动采集的项目下,手工录入参数
 *
 * @param
 */
public class Auto_Manual_ParamsActivity extends AppCompatActivity implements AbsListView.OnScrollListener, MyScrollView.OnScrollListener, Auto_Manual_ParamsView {
    @BindView(R.id.lv_auto_manaul_param)
    ListView listView;
    @BindView(R.id.txt_last_step)//上一步
            Button txt_last_step;
    @BindView(R.id.txt_next_step)//下一步
            Button txt_next_step;
    @BindView(R.id.txt_auto_title)
    TextView txt_auto_title;
    private Item_Auto_ParamsAdapter adapter;
    protected static Gson gson = new Gson();
    private AllActivityManager manager;
    private int page = 1, total = 0;
    private boolean canClick = false, isFirst = true;
    List<Auto_Manual_ParamsBean.ResobjBean.ParamBean> list;
    private List<String> list_code;
    private Map<String, String> map_step;
    private String deviceId;
    private String deviceType;
    private String isEffecValue = "100";//0代表无效值，1代表有效值
    private String isParams = "100";//0代表参数有误  1代表有效
    private String isContent = "100";//0代表文本内容为空  1正常有效
    private Auto_Manual_ParamsPresenter presenter;
    private String isfinish="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = AllActivityManager.getInstance();
        manager.pushOneActivity(this);
        setContentView(R.layout.activity_auto__manual__params);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        list_code = new ArrayList<>();
        map_step = new HashMap<>();
        Typeface typeface = Typeface.createFromAsset(getResources().getAssets(), "fonts/huawencaibi.TTF");
        txt_last_step.setTypeface(typeface);
        txt_next_step.setTypeface(typeface);
        listView.setOnScrollListener(this);
        adapter = new Item_Auto_ParamsAdapter();
        presenter = new Auto_Manual_ParamsPresenter();
        presenter.attach(this);
        presenter.onResume(this, 1);
    }

    @OnClick({R.id.iv_back, R.id.txt_next_step, R.id.txt_last_step})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.txt_next_step:
                if (canClick) {//防止连续点击2下
                    isfinish = "0";
                    getAdapterData();
                    if (isEffecValue.equals("100") && isContent.equals("100") && isParams.equals("100")) {
                        if (total > 1) {
                            //获取下一页的参数录入,提交当前页的数据
                            if (total > page + 1) {
                                txt_next_step.setVisibility(View.VISIBLE);
                                txt_last_step.setVisibility(View.VISIBLE);
                                txt_next_step.setText(getResources().getString(R.string.txt_next_step));
                                isfinish = "0";
                            } else if (total == page + 1) {
                                txt_next_step.setVisibility(View.VISIBLE);
                                txt_last_step.setVisibility(View.VISIBLE);
                                txt_next_step.setText(getResources().getString(R.string.txt_commit_step));
                                isfinish = "0";//标记是否提交
                            } else if (total == page) {
                                isfinish = "1";
                            }
                            setdataNextpage(isfinish);
                        } else if (total == 1) {
                            isfinish = "1";//标记是否提交
                            setdataNextpage(isfinish);
                        }
                    } else if (isEffecValue.equals("0")) {
                        ToastUtils.showToast(this, "请输入有效范围值");
                        canClick = true;
                    } else if (isContent.equals("0")) {
                        ToastUtils.showToast(this, "输入的值不能为空");
                        canClick = true;
                    }
                }
                break;
            case R.id.txt_last_step:
                //获取上一页的参数录入
                if (canClick) {
                    --page;
                    if (page == 1) {
                        txt_next_step.setVisibility(View.VISIBLE);
                        txt_last_step.setVisibility(View.GONE);
                        txt_next_step.setText(getResources().getString(R.string.txt_next_step));
                    } else if (page > 1) {
                        txt_next_step.setVisibility(View.VISIBLE);
                        txt_last_step.setVisibility(View.VISIBLE);
                        txt_next_step.setText(getResources().getString(R.string.txt_next_step));
                    }
                    presenter.onResume(this, page);
                }
                break;
            default:
                break;
        }
    }
    //对adapter进行相关数据的=
    //得到数据放进adapter里面
    private void getAdapterData() {
        map_step.clear();
        canClick = false;
        isEffecValue = "100";
        isContent = "100";
        double x_max = 0;
        double x_min = 0;
        List<Auto_Manual_ParamsBean.ResobjBean.ParamBean> adapterList = adapter.getList();
        if (adapterList == null || adapterList.isEmpty()) {
            return;
        }
        for (int i = 0; i < adapterList.size(); i++) {
            Auto_Manual_ParamsBean.ResobjBean.ParamBean paramBean = adapterList.get(i);

            if (!paramBean.getMax().equals("") && !paramBean.getMin().equals("")) {
                x_max = Double.parseDouble(paramBean.getMax());
                x_min = Double.parseDouble(paramBean.getMin());
            }
            if (paramBean.getMyContent() == null || paramBean.getMyContent().isEmpty()) {
                isContent = "0";
                break;
            } else {
                double x_content = Double.parseDouble(paramBean.getMyContent());
                if (paramBean.getMin().equals("") || paramBean.getMax().equals("")) {
                    map_step.put(list_code.get(i), paramBean.getMyContent());
                } else if (!paramBean.getMin().equals("") || !paramBean.getMax().equals("")) {
                    if (x_content >= x_min && x_content <= x_max) {
                        map_step.put(list_code.get(i), paramBean.getMyContent());
                    } else if (x_content < x_min || x_content > x_max) {
                        isEffecValue = "0";
                        break;
                    }
                }

            }
        }
    }

    //下一页的数据放大adapter里面
    private void setdataNextpage(final String f) {
        List list = new ArrayList<>();
        list.add(map_step);
        list.add(deviceId);
        list.add(deviceType);
        list.add(f);
        presenter.sendPageNext(this,list);

    }


    //此方法只是关闭软键盘
    public void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hintKbTwo();//关闭软键盘
        manager.removeOneActivity(this);
        list.clear();
        list_code.clear();
        map_step.clear();
        adapter = null;
        list = null;
        list_code = null;
        map_step = null;
        presenter.deAttach();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {

            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:    //当停止滚动时

                break;

            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:    //滚动时

                //没错，下面这一坨就是隐藏软键盘的代码
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                break;

            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:   //手指抬起，但是屏幕还在滚动状态

                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onScroll(int scrollY) {
        if (scrollY > 0) {
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void setText(Object text) {
        Auto_Manual_ParamsBean bean = (Auto_Manual_ParamsBean) text;
        Auto_Manual_ParamsBean.ResobjBean resobj = bean.getResobj();
        txt_next_step.setVisibility(View.VISIBLE);
        deviceType = resobj.getDeviceType();
        deviceId = resobj.getDeviceId();
        total = resobj.getTotalCount();
        if (total == 1) {
            txt_next_step.setText("提交");
            txt_last_step.setVisibility(View.GONE);
            txt_next_step.setVisibility(View.VISIBLE);
        }
        list.clear();
        list_code.clear();
        list = resobj.getParam();

        if (list != null) {
            txt_auto_title.setText(resobj.getDeviceName());
            adapter.setList(list);
            listView.setAdapter(adapter);
            if (isFirst) {
                for (int i = 0; i < list.size(); i++) {
                    list_code.add(list.get(i).getCode());
                }
            }
        }
        canClick = true;
    }

    @Override
    public void canClick(boolean isClick) {
        if (isClick) {
            canClick = true;
        }
    }

    @Override
    public void sendPageNext() {
        if (isfinish.equals("1")) {
            ToastUtils.showToast(Auto_Manual_ParamsActivity.this, "录入成功");
            finish();
        }
        if (page >= 1 && page <= 2) {
            presenter.onResume(Auto_Manual_ParamsActivity.this, ++page);
        }
    }
}
