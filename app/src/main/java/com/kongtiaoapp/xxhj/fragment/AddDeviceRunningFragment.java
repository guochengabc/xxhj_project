package com.kongtiaoapp.xxhj.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.DeviceRunningParamAdapter;
import com.kongtiaoapp.xxhj.afinal.HttpMethod;
import com.kongtiaoapp.xxhj.bean.RecordFormDeviceBean;
import com.kongtiaoapp.xxhj.bean.RunningParam;
import com.kongtiaoapp.xxhj.db.ProjectTable;
import com.kongtiaoapp.xxhj.db.XUtil;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.AddDeviceRunningPresenter;
import com.kongtiaoapp.xxhj.mvp.view.AddDeviceRunningView;
import com.kongtiaoapp.xxhj.ui.view.MyScrollView;
import com.kongtiaoapp.xxhj.ui.view.draglistview.DragListView;
import com.kongtiaoapp.xxhj.utils.PatternUtils;
import com.kongtiaoapp.xxhj.utils.StringUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by xxhj_g on 2017/11/6.  添加设备运行信息
 */

public class AddDeviceRunningFragment extends BaseFragment<AddDeviceRunningPresenter, AddDeviceRunningView> implements AddDeviceRunningView,
        MyScrollView.OnScrollListener, AbsListView.OnScrollListener {
    @BindView(R.id.listview)
    DragListView mListview;
    @BindView(R.id.addDeviceInfo_tv_save)
    TextView save;
    @BindView(R.id.txt_setting_draw)
    TextView txt_setting_draw;//设置拖动
    @BindView(R.id.line_add_device_info)
    LinearLayout line_add_device_info;
    @BindView(R.id.txt_setting_font_small)//小号字体
            TextView txt_setting_font_small;
    @BindView(R.id.txt_setting_font_mid)//中号字体
            TextView txt_setting_font_mid;
    @BindView(R.id.txt_setting_font_big)//大号字体
            TextView txt_setting_font_big;
    private String type = "";
    private DeviceRunningParamAdapter adapter;
    private String deviceId = "";
    private String deviceName = "";
    private List<RunningParam> mList = new ArrayList<>();
    private String key = "";
    private DbManager db;
    private ProjectTable project;
    private boolean draw_type = false;
    private boolean isAuto = false, isFirstRefresh = true;
    private List<String> list_code = new ArrayList();
    // 监听器在手机拖动停下的时候触发
    private DragListView.DropListener onDrop = new DragListView.DropListener() {
        @Override
        public void drop(int from, int to) {// from to 分别表示 被拖动控件原位置 和目标位置
            if (from != to) {
                RunningParam item = (RunningParam) adapter.getItem(from);// 得到listview的适配器
                adapter.remove(from);// 在适配器中”原位置“的数据。
                adapter.insert(item, to);// 在目标位置中插入被拖动的控件。
            }
        }
    };
    // 删除监听器，点击左边差号就触发。删除item操作。
    private DragListView.RemoveListener onRemove = new DragListView.RemoveListener() {
        @Override
        public void remove(int which) {
            adapter.remove(which);
        }
    };

    @OnClick({R.id.addDeviceInfo_tv_save, R.id.txt_setting_draw, R.id.txt_setting_font_small,
            R.id.txt_setting_font_mid, R.id.txt_setting_font_big, R.id.ctv_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addDeviceInfo_tv_save:
                hintKbTwo();
                save();
                break;
            case R.id.txt_setting_draw:
                draw_type = !draw_type;
                if (!draw_type) {
                    mListview.setDragEnabled(false);
                    txt_setting_draw.setText(getResources().getString(R.string.txt_setting_draw));
                    //  if (!isAuto) {
                    save.setVisibility(View.VISIBLE);
                    //  }
                    txt_setting_draw.setTextColor(getResources().getColor(R.color.black));
                    saveDrag_CommitData();//提交尽心拖动顺序的数据
                } else if (draw_type) {
                    mListview.setDragEnabled(true);
                    txt_setting_draw.setText(getResources().getString(R.string.save_draw));
                    save.setVisibility(View.GONE);
                    txt_setting_draw.setTextColor(getResources().getColor(R.color.theme_color));
                }
                break;
            case R.id.txt_setting_font_small:
                setBackground(txt_setting_font_small, txt_setting_font_mid, txt_setting_font_big);//设置背景色
                App.sp.setFontSize("0");
                adapter.setList(mList, isAuto);
                break;
            case R.id.txt_setting_font_mid:

                App.sp.setFontSize("1");
                setBackground(txt_setting_font_mid, txt_setting_font_small, txt_setting_font_big);//设置背景色
                adapter.setList(mList, isAuto);
                break;
            case R.id.txt_setting_font_big:

                App.sp.setFontSize("2");
                setBackground(txt_setting_font_big, txt_setting_font_small, txt_setting_font_mid);//设置背景色
                adapter.setList(mList, isAuto);
                break;
            case R.id.ctv_clear://一键清空
                List<ProjectTable> projectTable = null;
                try {
                    projectTable = db.selector(ProjectTable.class).where("deviceId", "=", deviceId).findAll();
                    List<RunningParam> list = adapter.getmList();
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setMyContent("");
                    }
                    if (projectTable!=null){
                        db.delete(projectTable);
                        adapter.setList(list, isAuto);
                    }else{
                        ToastUtils.showToast(mActivity,"录入保存后才可清空呦！");
                    }

                } catch (DbException e) {
                    e.printStackTrace();
                }

                break;
            default:
                break;
        }
    }

    private void setBackground(TextView txt1, TextView txt2, TextView txt3) {
        if (isAdded()) {

            txt1.setTextColor(getResources().getColor(R.color.a000000));
            txt2.setTextColor(getResources().getColor(R.color.devicedetail_selectcolor));
            txt3.setTextColor(getResources().getColor(R.color.devicedetail_selectcolor));
        }
    }

    private void saveDrag_CommitData() {
        mList = null;
        list_code.clear();
        mList = adapter.getmList();
        for (int i = 0; i < mList.size(); i++) {
            list_code.add(mList.get(i).getCode());
        }
        JsonArray asJsonArray = gson.toJsonTree(list_code, new TypeToken<List<String>>() {
        }.getType()).getAsJsonArray();
        List list = new ArrayList<>();
        list.add(gson.toJson(deviceId));
        list.add(asJsonArray.toString());
        presenter.saveDrag_CommitData(mActivity, list);
    }

    //保存数据
    private void save() {
        Map<String, String> map = new HashMap<>();
        map.put("DeviceId", deviceId);
        List<RunningParam> adapterList = adapter.getmList();
        if (adapterList == null) {
            return;
        }
        for (int i = 0; i < mList.size(); i++) {
            String content = adapterList.get(i).getMyContent();
            String fieldType = adapterList.get(i).getFieldType();
            if (fieldType.equals("float")) {

                if (content != null && !content.equals("")) {
                    if (Pattern.compile(PatternUtils.getDigital()).matcher(content).matches() || content.equals("")) {
                        float data = Float.parseFloat(content);
                        if (!StringUtils.isEmpty(adapterList.get(i).getMin())) {
                            float min = Float.parseFloat(adapterList.get(i).getMin());
                            if (data < min) {
                                ToastUtils.showToast(mActivity, adapterList.get(i).getValue() + "取值不能小于:" + min);
                                return;
                            }
                        }
                        if (!StringUtils.isEmpty(adapterList.get(i).getMax())) {
                            float max = Float.parseFloat(adapterList.get(i).getMax());
                            if (data > max) {
                                ToastUtils.showToast(mActivity, adapterList.get(i).getValue() + "取值不能大于:" + max);
                                return;
                            }

                        }
                        map.put(mList.get(i).getCode(), content);
                    } else {
                        ToastUtils.showToast(mActivity, "请正确录入参数");
                        return;
                    }

                } else {
                }
            } else if (fieldType.equals("string")) {
                if (content != null && !content.equals(""))
                    map.put(mList.get(i).getCode(), content);
            } else if (fieldType.equals("time")) {
                if (content != null && !content.equals(""))
                    map.put(mList.get(i).getCode(), content);
            } else {

            }
        }
        if (map.size() == 1) {//之前已经有mup.pu("deviceId");
            ToastUtils.showToast(mActivity, getString(R.string.null_save));
            return;
        }
        JSONObject json = new JSONObject(map);
        try {
            if (project == null) {
                project = new ProjectTable();
            }
            project.setProjectId(App.sp.getProjectId());
            project.setDeviceId(deviceId);
            project.setInfo(json.toString());
            project.setName(App.sp.getPaintType());
            project.setSave(true);
            db.saveOrUpdate(project);
            JSONObject jsonObject = new JSONObject();
            List<JSONObject> vel = new ArrayList<>();
            vel.clear();
            try {
                vel.add(new JSONObject(json.toString()));
                JSONArray resObj = new JSONArray(vel);
                jsonObject.put("Unit", resObj);
                JSONObject result = new JSONObject();
                result.put("uid", App.sp.getUid());
                result.put("projectId", App.sp.getProjectId());
                result.put("data", jsonObject);
                String recordFormResult = "{" + "\"" + "method" + "\"" + ":" + "\"" + HttpMethod.POSTRUNNINGDATA + "\"" + "," + "\"" + "reqobj" + "\"" + ":" + result.toString() + "}";
                presenter.commitData(mActivity, recordFormResult);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        x.view().inject(rootView);
        return rootView;
    }

    @Override
    public AddDeviceRunningPresenter getPresenter() {
        return new AddDeviceRunningPresenter();
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.activity_add_device_info, null);
    }

    @Override
    public void initData() {
        super.initData();
        mListview.setOnScrollListener(this);
    }

    private void getData() {
        presenter.onResume(mActivity, deviceId);
    }

    private void refreshList() {
        try {
            project = db.selector(ProjectTable.class).where("projectId", "=", App.sp.getProjectId()).and("deviceId", "=", deviceId).findFirst();
            if (project == null) {
                return;
            }
            if (project != null) {
                String s = project.getInfo();
                JSONObject jsonObject = new JSONObject(s);

                for (int i = 0; i < mList.size(); i++) {
                    String str = null;
                    try {
                        str = jsonObject.getString(mList.get(i).getCode());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } finally {

                        mList.get(i).setMyContent(str);
                    }
                }

                adapter.notifyDataSetChanged();
            }
        } catch (DbException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onScroll(int scrollY) {
        if (scrollY > 0) {
            ((InputMethodManager) mActivity.getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {

            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:    //当停止滚动时

                break;

            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:    //滚动时

                //没错，下面这一坨就是隐藏软键盘的代码
                ((InputMethodManager) mActivity.getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                break;

            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:   //手指抬起，但是屏幕还在滚动状态

                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


    @Override
    public void setList(Object data) {
        isFirstRefresh = false;
        mList.clear();
        List list = (List<RunningParam>) data;
        mList.addAll(list);
        if (mList == null) {
            return;
        }
        if (mList.size() > 0) {
            key = mList.get(0).getUnit();
        }
        adapter = new DeviceRunningParamAdapter(mList, mActivity, isAuto);
        mListview.setAdapter(adapter);
        refreshList();
    }

    @Override
    public void saveDrag_CommitData_View(Object data) {
        ToastUtils.showToast(mActivity, getResources().getString(R.string.drag_success));
    }

    @Override
    public void commitData(Object data) {
        ToastUtils.showToast(mActivity, getString(R.string.commit_succeed));
        save.setText("已提交");
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hintKbTwo();
        EventBus.getDefault().unregister(this);
        line_add_device_info.setBackgroundResource(0);
    /*    if (thread != null && thread.isAlive()) {
            thread.interrupt();
        }*/
        if (mList != null) {
            mList.clear();
            mList = null;
        }
        if (list_code != null) {
            list_code.clear();
            list_code = null;
        }

    }

    //此方法只是关闭软键盘
    public void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(INPUT_METHOD_SERVICE);
        if (imm.isActive() && mActivity.getCurrentFocus() != null) {
            if (mActivity.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public void setData(RecordFormDeviceBean.ResobjBean.RecordFormBean.DataBean bean) {

        if (bean != null) {
            setInitData(bean);
        }

    }

    private void setInitData(RecordFormDeviceBean.ResobjBean.RecordFormBean.DataBean bean) {
        deviceName = bean.getName();
        type = bean.getType();
        deviceId = bean.getDeviceId();
        DbManager.DaoConfig daoConfig = XUtil.getDaoConfig();
        db = x.getDb(daoConfig);
        if (App.sp.getFontSize().equals("0")) {
            setBackground(txt_setting_font_small, txt_setting_font_mid, txt_setting_font_big);//设置背景色
        } else if (App.sp.getFontSize().equals("1")) {
            setBackground(txt_setting_font_mid, txt_setting_font_small, txt_setting_font_big);//设置背景色
        } else if (App.sp.getFontSize().equals("2")) {
            setBackground(txt_setting_font_big, txt_setting_font_small, txt_setting_font_mid);//设置背景色
        }
     /*   if (isAuto) {
            save.setVisibility(View.GONE);
        }*/

        if (type.equals("bbcs")) {
            txt_setting_draw.setVisibility(View.GONE);
        } else {
            txt_setting_draw.setVisibility(View.VISIBLE);
            txt_setting_draw.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/huawencaibi.TTF"));
            mListview.setDropListener(onDrop);
            mListview.setRemoveListener(onRemove);
        }
        mListview.setDragEnabled(false);
        //非自动
        getData();
    }


}
