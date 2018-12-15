package com.kongtiaoapp.xxhj.activites;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.DeviceSystemParamAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.ReceiverAction;
import com.kongtiaoapp.xxhj.bean.SystemParam;
import com.kongtiaoapp.xxhj.db.ProjectTable;
import com.kongtiaoapp.xxhj.db.XUtil;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.AddRunningSystemPresenter;
import com.kongtiaoapp.xxhj.mvp.view.AddRunningSystemView;
import com.kongtiaoapp.xxhj.ui.view.MyScrollView;
import com.kongtiaoapp.xxhj.ui.view.draglistview.DragListView;
import com.kongtiaoapp.xxhj.utils.PatternUtils;
import com.kongtiaoapp.xxhj.utils.StringUtils;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.BindView;

/**
 * Created by ASUS-PC on 2016/6/16.
 * 添加运行中系统信息页面
 */
public class AddRunningSystemActivity extends BaseActivity<AddRunningSystemPresenter, AddRunningSystemView> implements AddRunningSystemView, MyScrollView.OnScrollListener, AbsListView.OnScrollListener {

    @ViewInject(R.id.listview)
    private DragListView mListview;
    @ViewInject(R.id.addDeviceInfo_tv_save)
    private TextView save;
    @BindView(R.id.txt_setting_draw)
    TextView txt_setting_draw;//修改排序
    private String type;
    private DeviceSystemParamAdapter adapter;
    private String projectId;
    private List<SystemParam.ResobjBean.SystemBean> mList = new ArrayList<>();
    private List<String> list_code = new ArrayList();
    private String key;
    private DbManager db;
    private ProjectTable project;
    private boolean isAuto = false;
    private boolean draw_type = false;

    @Event(value = {R.id.iv_back, R.id.addDeviceInfo_tv_save, R.id.txt_setting_draw})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
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
        }
    }

    // 监听器在手机拖动停下的时候触发
    private DragListView.DropListener onDrop = new DragListView.DropListener() {
        @Override
        public void drop(int from, int to) {// from to 分别表示 被拖动控件原位置 和目标位置
            if (from != to) {
                SystemParam.ResobjBean.SystemBean item = (SystemParam.ResobjBean.SystemBean) adapter.getItem(from);// 得到listview的适配器
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

    private void saveDrag_CommitData() {
        mList = null;
        list_code.clear();
        if (adapter == null) {
            return;
        }
        mList = adapter.getmList();
        for (int i = 0; i < mList.size(); i++) {
            list_code.add(mList.get(i).getCode());
        }
        JsonArray asJsonArray = gson.toJsonTree(list_code, new TypeToken<List<String>>() {
        }.getType()).getAsJsonArray();
        presenter.saveDrag_CommitData(this, asJsonArray.toString());
    }

    private void save() {
        Map<String, String> map = new HashMap<String, String>();
        List<SystemParam.ResobjBean.SystemBean> adapterList = adapter.getmList();
        for (int i = 0; i < adapterList.size(); i++) {
            String content = adapterList.get(i).getMyContent();
            if (content != null && !content.equals("")) {
                if (Pattern.compile(PatternUtils.getDigital()).matcher(content).matches() || content.equals("")) {
                    float data = Float.parseFloat(content);
                    if (!StringUtils.isEmpty(adapterList.get(i).getMin())) {
                        int min = Integer.parseInt(adapterList.get(i).getMin());
                        if (data < min) {
                            ToastUtils.showToast(this, adapterList.get(i).getValue() + "取值不能小于:" + min);
                            return;
                        }
                    }
                    if (!StringUtils.isEmpty(adapterList.get(i).getMax())) {
                        int max = Integer.parseInt(adapterList.get(i).getMax());
                        if (data > max) {
                            ToastUtils.showToast(this, adapterList.get(i).getValue() + "取值不能大于:" + max);
                            return;
                        }

                    }
                    map.put(mList.get(i).getCode(), content);
                } else {
                    ToastUtils.showToast(AddRunningSystemActivity.this, "请正确录入参数");
                    return;
                }
            } else {

            }
        }
        JSONObject json = new JSONObject(map);
        try {
            if (project == null) {
                project = new ProjectTable();
            }
            project.setProjectId(App.sp.getProjectId());
            project.setInfo(json.toString());
            project.setName("System");
            project.setSave(true);
            db.saveOrUpdate(project);
            Intent mIneten = new Intent();
            mIneten.setAction(ReceiverAction.DEVICE_SAVE);
            sendBroadcast(mIneten);
            finish();
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int initContentView() {
        type = getIntent().getStringExtra("type");
        projectId = getIntent().getStringExtra("projectId");
        isAuto = getIntent().getBooleanExtra(ConstantValue.AUTO, false);
        return R.layout.activity_add_device_information;
    }

    @Override
    protected void initView() {
        x.view().inject(this);
        mListview.setOnScrollListener(this);
        DbManager.DaoConfig daoConfig = XUtil.getDaoConfig();
        db = x.getDb(daoConfig);
    }

    @Override

    protected void initListener() {

    }

    @Override
    protected void initData() {//初始化相关数据
        txt_setting_draw.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "fonts/huawencaibi.TTF"));
        mListview.setDropListener(onDrop);
        mListview.setRemoveListener(onRemove);
        mListview.setDragEnabled(false);
        getData();//非自动采集
    }

    @Override
    protected AddRunningSystemPresenter getPresenter() {
        return new AddRunningSystemPresenter();
    }

    private void getData() {
        presenter.onResume(this);
    }

    @Override
    public void setList(Object data) {
        SystemParam response = (SystemParam) data;
        mList = response.getResobj().getSystem();
        adapter = new DeviceSystemParamAdapter(mList, AddRunningSystemActivity.this, isAuto);
        mListview.setAdapter(adapter);
        refreshList();
    }

    @Override
    public void saveDrag_CommitData_View(Object data) {
        ToastUtils.showToast(AddRunningSystemActivity.this, getResources().getString(R.string.drag_success));
    }

    @Override
    public void setText(Object data) {

    }

    private void refreshList() {
        try {
            project = db.selector(ProjectTable.class).where("projectId", "=", App.sp.getProjectId()).and("name", "=", "System").findFirst();
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
//                    mList.get(i).setMyContent(jsonObject.getString(mList.get(i).getCode()));
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
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(AddRunningSystemActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {

            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:    //当停止滚动时

                break;

            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:    //滚动时

                //没错，下面这一坨就是隐藏软键盘的代码
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(AddRunningSystemActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                break;

            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:   //手指抬起，但是屏幕还在滚动状态

                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    //此方法只是关闭软键盘
    public void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hintKbTwo();
        if (mList != null) {
            mList.clear();
            mList = null;
        }
        if (list_code != null) {
            list_code.clear();
            list_code = null;
        }
    }
}
