package com.kongtiaoapp.xxhj.workorder.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.workorder.activity.EngineerRepairActivity;
import com.kongtiaoapp.xxhj.activites.MainActivity;
import com.kongtiaoapp.xxhj.workorder.activity.ReportWorkActivity;
import com.kongtiaoapp.xxhj.workorder.activity.WorkOrderActivity;
import com.kongtiaoapp.xxhj.workorder.activity.WorkOrderSatisficActivity;
import com.kongtiaoapp.xxhj.workorder.adapter.WorkOrderAdapter;
import com.kongtiaoapp.xxhj.afinal.ConstantValue;
import com.kongtiaoapp.xxhj.afinal.UserManegerList;
import com.kongtiaoapp.xxhj.bean.WorkOrderListBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.WorkOrderPresenter;
import com.kongtiaoapp.xxhj.mvp.view.WorkOrderView;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationFooter;
import com.kongtiaoapp.xxhj.ui.refreshui.RotationHeader;
import com.kongtiaoapp.xxhj.ui.refreshui.SpringView;
import com.kongtiaoapp.xxhj.ui.view.PopWindowView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;
import scut.carson_ho.searchview.bCallBack;

/**
 * A simple {@link Fragment} subclass.   工单管理模块
 */
public class WorkOrderFragment extends BaseFragment<WorkOrderPresenter, WorkOrderView> implements WorkOrderView,
        PopWindowView.IntentActivity {
    @BindView(R.id.search_view)
    SearchView search_view;
    @BindView(R.id.txt_workorder_all)//全部
            TextView txt_workorder_all;
    @BindView(R.id.txt_workorder_recept)//报修单
            TextView txt_workorder_recept;
    @BindView(R.id.txt_workorder_no_recept)//未完成
            TextView txt_workorder_no_recept;
    @BindView(R.id.txt_workorder_give)//返工单
            TextView txt_workorder_give;
    @BindView(R.id.txt_workorder_my_form)
    TextView txt_workorder_my_form;
    @BindView(R.id.sv_work_Order)
    SpringView sv_work_Order;
    @BindView(R.id.lv_workOrder)
    ListView lv_workOrder;
    private MainActivity activity;
    private PopWindowView popWindowView;
    private boolean isRefresh = true;
    private int currentPage = 1;
    private int type = 4;//单子的类型
    private int allCurrentPage = 1;//全部界面
    private int repairPage = 1;//报修界面
    private int noRepairPage = 1;//未完成界面   type=3时代表的是未完成界面
    private int givePae = 1;//返工界面
    private int myPage = 1;//我的界面

    //统计总数
    private int pageALlSize = 10;
    private int pageRepairSize = 10;
    private int pageNoRepairSize = 10;
    private int pageGiveSize = 10;
    private int pageMySize = 10;

    //防止重复点击
    private int allClick = 1;//1 代表已经点击一次
    private int giveClick = 0;//1
    private int noRepairClick = 0;//1
    private int repairClick = 0;//1
    private int myClick = 0;//1

    //设置定位到指定item行
    private int allRow = 0;
    private int giveRow = 0;
    private int noRepairRow = 0;
    private int repairRow = 0;
    private int myRow = 0;
    private int isRow = 0;//1代表跳到指定位置行数  0或者其他代表不跳
    private String isFirstEnter = "0";//1代表可以访问，其他代表不能访问
    //搜索栏设置
    private String key_Word = "";//搜索栏输入的关键字
    private String key_Visible_IsFirst = "0";//1代表不是第一次   0代表是第一次
    private WorkOrderAdapter adapter;
    List<WorkOrderListBean.ResobjBean> listAll = new ArrayList<>();
    List<WorkOrderListBean.ResobjBean> listAll_Repair = new ArrayList<>();
    List<WorkOrderListBean.ResobjBean> listAll_No_Repair = new ArrayList<>();
    List<WorkOrderListBean.ResobjBean> listAll_Back = new ArrayList<>();
    List<WorkOrderListBean.ResobjBean> listAll_myform = new ArrayList<>();
    List<WorkOrderListBean.ResobjBean> beanList = new ArrayList<>();
    private String json;//接收到的json字符串
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SHUAXIN_SUCESS:
                    if (popWindowView.isShowing()) {
                        popWindowView.disMiss();
                    }
                    setTypeRefresh();//设置根据类型刷新数据
                    getDataForServers();
                    sv_work_Order.onFinishFreshAndLoad();

                    break;
                case ConstantValue.JIAZAI_SUCESS:
                    if (popWindowView.isShowing()) {
                        popWindowView.disMiss();
                    }
                    if (type == 0) {//报修单
                        if (pageRepairSize != 10) {
                            repairPage = pageRepairSize / 10;
                            pageRepairSize = 10;
                        }
                        ++repairPage;
                        currentPage = repairPage;
                    } else if (type == 1) {
                        if (pageGiveSize != 10) {
                            givePae = pageGiveSize / 10;
                            pageGiveSize = 10;
                        }
                        ++givePae;
                        currentPage = givePae;
                    } else if (type == 3) {
                        if (pageNoRepairSize != 10) {
                            noRepairPage = pageNoRepairSize / 10;
                            pageNoRepairSize = 10;
                        }
                        ++noRepairPage;
                        currentPage = noRepairPage;
                    } else if (type == 2) {
                        if (pageMySize != 10) {
                            myPage = pageMySize / 10;
                            pageMySize = 10;
                        }
                        ++myPage;
                        currentPage = myPage;
                    } else if (type == 4) {
                        if (pageALlSize != 10) {
                            allCurrentPage = pageALlSize / 10;
                            pageALlSize = 10;
                        }
                        ++allCurrentPage;
                        currentPage = allCurrentPage;
                    }
                    getDataForServers();
                    sv_work_Order.onFinishFreshAndLoad();
                    break;
                case R.id.iv_back:

                    break;
                default:
                    break;
            }
        }
    };

    private void setTypeRefresh() {
        if (type == 0) {//报修单
            pageRepairSize = 10 * repairPage;

        } else if (type == 1) {//返工
            pageGiveSize = 10 * givePae;
        } else if (type == 3) {//未完成
            pageNoRepairSize = 10 * noRepairPage;
        } else if (type == 2) {//我的
            pageMySize = 10 * myPage;
        } else {
            if (type == 4) {//全部
                pageALlSize = 10 * allCurrentPage;
            }

        }
        currentPage = 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isFirstEnter = "1";
            if (isFirstEnter.equals("1")) {
                setTypeRefresh();
                getDataForServers();
            }

        } else {
            isFirstEnter = "2";
            if (popWindowView != null) {
                if (popWindowView.isShowing()) {
                    popWindowView.disMiss();
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstEnter.equals("1")) {
            setUserVisibleHint(true);
        }

    }

    private void getDataForServers() {
        List<String> list = new ArrayList<>();
        list.add(key_Word);
        list.add(currentPage + "");
        list.add(type + "");
        switch (type) {
            case 0:

                list.add(pageRepairSize + "");
                break;
            case 1:

                list.add(pageGiveSize + "");
                break;
            case 2:

                list.add(pageMySize + "");
                break;
            case 3:

                list.add(pageNoRepairSize + "");
                break;
            case 4:

                list.add(pageALlSize + "");
                break;
        }

        presenter.onResume(mActivity, list);

    }

    @Override
    public WorkOrderPresenter getPresenter() {
        return new WorkOrderPresenter();
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_word_order, null);
    }

    @Override
    public void initData() {
        super.initData();
        initPopWindow();//初始化popwindow
        initListView();//初始化listview
        adapter = new WorkOrderAdapter(listAll, mActivity);
        lv_workOrder.setAdapter(adapter);
        initListener();//搜索栏监听  目前只有这一个
    }


    private void initListener() {
        search_view.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String searchContent) {
                if (searchContent != null) {
                    key_Word = searchContent;
                    currentPage = 1;
                    getDataForServers();
                }
            }
        });
        search_view.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton(String searchContent) {
                if (searchContent != null) {
                    key_Word = searchContent;
                    currentPage = 1;
                    getDataForServers();
                }
            }
        });

        lv_workOrder.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                switch (type) {
                    case 0://报修单
                        repairRow = firstVisibleItem;
                        break;
                    case 1://返回
                        giveRow = firstVisibleItem;
                        break;
                    case 2://我的
                        myRow = firstVisibleItem;
                        break;
                    case 3://未完成
                        noRepairRow = firstVisibleItem;
                        break;
                    case 4://全部
                        allRow = firstVisibleItem;
                        break;
                }
            }
        });


    }


    private void initListView() {
        sv_work_Order.setHeader(new RotationHeader(getContext()));
        sv_work_Order.setFooter(new RotationFooter(getContext()));
        sv_work_Order.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;
                        currentPage = 1;
                        handler.sendEmptyMessage(ConstantValue.SHUAXIN_SUCESS);
                    }
                }).start();
            }

            @Override
            public void onLoadmore() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = false;
                        currentPage++;
                        handler.sendEmptyMessage(ConstantValue.JIAZAI_SUCESS);
                    }
                }).start();
            }
        });
    }

    private void initPopWindow() {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.popwindow_top, null);
        // popWindowView = new PopWindowView(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, activity);
        popWindowView = new PopWindowView(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, activity);
        popWindowView.setIntent(this);//接口回调注册监听
    }


    @OnClick({R.id.img_menu, R.id.txt_workorder_all, R.id.txt_workorder_no_recept
            , R.id.txt_workorder_recept, R.id.txt_workorder_my_form,
            R.id.txt_workorder_give, R.id.txt_reportWork
    })
    public void onClick(View view) {
        isRow = 1;
        switch (view.getId()) {
            case R.id.txt_workorder_all://全部

                if (allClick == 0) {
                    setbackgroundno();
                    //防止重复点击
                    noRepairClick = 0;
                    repairClick = 0;
                    allClick = 1;
                    giveClick = 0;
                    myClick = 0;
                    txt_workorder_all.setBackgroundResource(R.drawable.workorderbackground);
                    type = 4;
                    currentPage = allCurrentPage;
                    getDataForServers();
                }
                break;
            case R.id.txt_workorder_recept://报修单
                Log.i("ffffffffff", "保修单====" + repairClick);
                if (repairClick == 0) {
                    noRepairClick = 0;
                    repairClick = 1;
                    allClick = 0;
                    giveClick = 0;
                    myClick = 0;
                    setbackgroundno();
                    txt_workorder_recept.setBackgroundResource(R.drawable.workorderbackground);
                    type = 0;
                    currentPage = repairPage;
                    getDataForServers();
                }
                break;
            case R.id.txt_workorder_no_recept://未完成
                if (noRepairClick == 0) {
                    noRepairClick = 1;
                    repairClick = 0;
                    allClick = 0;
                    giveClick = 0;
                    myClick = 0;//防止重复点击
                    setbackgroundno();
                    txt_workorder_no_recept.setBackgroundResource(R.drawable.workorderbackground);
                    type = 3;
                    currentPage = noRepairPage;
                    getDataForServers();
                }
                break;
            case R.id.txt_workorder_give://返工单
                if (giveClick == 0) {
                    //防止重复点击
                    noRepairClick = 0;
                    repairClick = 0;
                    allClick = 0;
                    giveClick = 1;
                    myClick = 0;
                    setbackgroundno();
                    txt_workorder_give.setBackgroundResource(R.drawable.workorderbackground);
                    type = 1;
                    currentPage = givePae;
                    getDataForServers();
                }

                break;
            case R.id.txt_workorder_my_form:
                if (myClick == 0) {
                    //防止重复点击
                    noRepairClick = 0;
                    repairClick = 0;
                    allClick = 0;
                    giveClick = 0;
                    myClick = 1;
                    setbackgroundno();
                    txt_workorder_my_form.setBackgroundResource(R.drawable.workorderbackground);
                    type = 2;
                    currentPage = myPage;
                    getDataForServers();
                }

                break;
            case R.id.img_menu://新建
                //打开popwindow
                if (popWindowView.isShowing()) {
                    popWindowView.disMiss();
                } else if (!popWindowView.isShowing()) {
                    popWindowView.onShow(view);
                }

                break;
         /*   case R.id.txt_weibao://维保
                //startActivity(new Intent(mActivity, WorkOrderRepairActivity.class));
                ToastUtils.showToast(mActivity, getString(R.string.not_open));
                break;*/
            case R.id.txt_reportWork://导出记录
                startActivity(new Intent(mActivity, ReportWorkActivity.class));
                break;
           /* case R.id.txt_search_commit:
                getDataForServers();
                break;*/

            default://上面都不走的时候走他

                break;
        }
    }


    @OnItemClick({R.id.lv_workOrder})
    public void onItemClick(int position) {
        if (popWindowView.isShowing()) {
            popWindowView.disMiss();
        }
        WorkOrderListBean.ResobjBean bean;
        if (type == 0) {
            bean = listAll_Repair.get(position);
        } else if (type == 3) {
            bean = listAll_No_Repair.get(position);
        } else if (type == 1) {
            bean = listAll_Back.get(position);
        } else if (type == 2) {
            bean = listAll_myform.get(position);
        } else {
            bean = listAll.get(position);
        }
        //往下是判断状态
        String dispatchId = bean.getDispatchId();
        if (UserManegerList.WORKORDER_INSP()) {//质检员
            if (bean.getDispState() < 2)//根据状态进行跳转不同的界面    未派单
            {
                startActivity(new Intent(mActivity, WorkOrderActivity.class).
                        putExtra("dispatchId", dispatchId).putExtra("ismodify", true));
            } else {
                startActivity(new Intent(mActivity, EngineerRepairActivity.class).
                        putExtra("dispatchId", dispatchId));
            }
        } else if (UserManegerList.WORKORDER_DISP() || UserManegerList.WORKORDER_MANAGER()) {//调度员/管理员
            if (bean.getDispState() == 0 || bean.getDispState() == 1)//0抢单模式工程师未接单  1   派单模式工程师为接单
            {
                startActivity(new Intent(mActivity, WorkOrderActivity.class).
                        putExtra("dispatchId", dispatchId).putExtra("ismodify", true));
            } else {
                startActivity(new Intent(mActivity, EngineerRepairActivity.class).
                        putExtra("dispatchId", dispatchId));
            }
        } else if (UserManegerList.WORKORDER_ENGI()) {//工程师
            startActivity(new Intent(mActivity, EngineerRepairActivity.class).
                    putExtra("dispatchId", dispatchId));

        } else if (UserManegerList.WORKORDER_EDITOR()) {
            if (bean.getDispState() == 0 || bean.getDispState() == 1)//0抢单模式工程师未接单  1   派单模式工程师为接单
            {
                startActivity(new Intent(mActivity, WorkOrderActivity.class).
                        putExtra("dispatchId", dispatchId).putExtra("ismodify", true));
            } else {
                startActivity(new Intent(mActivity, EngineerRepairActivity.class).
                        putExtra("dispatchId", dispatchId));
            }
        }
    }

    private void setbackgroundno() {
        txt_workorder_all.setBackgroundResource(R.drawable.workordernobackground);
        txt_workorder_recept.setBackgroundResource(R.drawable.workordernobackground);
        txt_workorder_no_recept.setBackgroundResource(R.drawable.workordernobackground);
        txt_workorder_give.setBackgroundResource(R.drawable.workordernobackground);
        txt_workorder_my_form.setBackgroundResource(R.drawable.workordernobackground);
    }

    @Override
    public void setText(Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        popWindowView.removeIntent();
    }

    @Override
    public void setList(Object beans) {
        if (!key_Word.equals("")) {
            setSearchView();
        }
        WorkOrderListBean bean = (WorkOrderListBean) beans;
        beanList = bean.getResobj();
        Log.i(TAG, "type===" + type);
        if (type == 0) {//报修
            if (currentPage == 1) {
                listAll_Repair.clear();
                listAll_Repair.addAll(beanList);
                adapter.setList(listAll_Repair);
            } else {
                listAll_Repair.addAll(beanList);
                adapter.setList(listAll_Repair);
            }
            if (isRow == 1) {
                isRow = 0;
                lv_workOrder.setSelection(repairRow);
            }
        } else if (type == 3) {//未完成
            if (currentPage == 1) {
                listAll_No_Repair.clear();
                listAll_No_Repair.addAll(beanList);
                adapter.setList(listAll_No_Repair);
            } else {
                listAll_No_Repair.addAll(beanList);
                adapter.setList(listAll_No_Repair);
            }
            if (isRow == 1) {
                isRow = 0;
                lv_workOrder.setSelection(noRepairRow);
            }

        } else if (type == 1) {//返工
            if (currentPage == 1) {
                listAll_Back.clear();
                listAll_Back.addAll(beanList);
                adapter.setList(listAll_Back);
            } else {
                listAll_Back.addAll(beanList);
                adapter.setList(listAll_Back);
            }
            if (isRow == 1) {
                isRow = 0;
                lv_workOrder.setSelection(giveRow);
            }

        } else if (type == 2) {//我的
            if (currentPage == 1) {
                listAll_myform.clear();
                listAll_myform.addAll(beanList);
                adapter.setList(listAll_myform);
            } else {
                listAll_myform.addAll(beanList);
                adapter.setList(listAll_myform);
            }
            if (isRow == 1) {
                isRow = 0;
                lv_workOrder.setSelection(myRow);
            }

        } else {//全部
            if (currentPage == 1) {
                listAll.clear();
                listAll.addAll(beanList);
                adapter.setList(listAll);
            } else {
                listAll.addAll(beanList);
                adapter.setList(listAll);
            }
            if (isRow == 1) {
                isRow = 0;
                lv_workOrder.setSelection(allRow);
            }
        }

    }

    /**
     * 搜索栏自动隐藏
     */
    private void setSearchView() {
        hiddenInput();
        //隐藏软键盘
        key_Visible_IsFirst = "0";
    }

    @Override
    public void setEmpty() {
        if (!key_Word.equals("")) {
            setSearchView();
        }
        if (currentPage == 1) {
            if (type == 0) {
                listAll_Repair.clear();
                adapter.setList(listAll_Repair);
            } else if (type == 3) {

                listAll_No_Repair.clear();
                adapter.setList(listAll_No_Repair);
            } else if (type == 1) {

                listAll_Back.clear();
                adapter.setList(listAll_Back);
            } else if (type == 2) {
                listAll_myform.clear();
                adapter.setList(listAll_myform);
            } else {

                listAll.clear();
                adapter.setList(listAll);
            }
        } else {
            if (type == 0) {
                --repairPage;
            } else if (type == 3) {
                --noRepairPage;
            } else if (type == 1) {
                --givePae;
            } else if (type == 2) {
                --myPage;
            } else {
                --allCurrentPage;
            }
        }
        ToastUtils.showToast(mActivity, getString(R.string.no_data));


    }

    @Override
    public void getIntents(String whichActivity) {
        if (whichActivity.equals("0")) {//新建工单
            if (UserManegerList.WORKORDER_DISP() || UserManegerList.WORKORDER_MANAGER() || UserManegerList.WORKORDER_EDITOR()) {
                startActivity(new Intent(mActivity, WorkOrderActivity.class));//工单
            } else {
                ToastUtils.showToast(mActivity, "您没有权限建工单哦!");
                return;
            }
        } else if (whichActivity.equals("1")) {
            startActivity(new Intent(mActivity, WorkOrderSatisficActivity.class));
        }
        // ToastUtils.showToast(mActivity, "工单统计研发中。。。");
    }


}
