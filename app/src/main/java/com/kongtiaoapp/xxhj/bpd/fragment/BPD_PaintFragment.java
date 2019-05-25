package com.kongtiaoapp.xxhj.bpd.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.BPD_MainInfoBean;
import com.kongtiaoapp.xxhj.bean.ChartDataBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseFragment;
import com.kongtiaoapp.xxhj.mvp.presenter.BPD_PaintP;
import com.kongtiaoapp.xxhj.mvp.view.BPD_PaintV;
import com.kongtiaoapp.xxhj.ui.address.ScreenUtils;
import com.kongtiaoapp.xxhj.ui.view.Mf_Tools;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kongtiaoapp.xxhj.R.id.rela_paint;

/**
 * CreatBy G_XXHJ 2018年9月11日
 */
public class BPD_PaintFragment extends BaseFragment<BPD_PaintP, BPD_PaintV> implements BPD_PaintV {
    @BindView(R.id.frame_up)
    FrameLayout frame_up;
    @BindView(rela_paint)
    RelativeLayout rela_loading;
    @BindView(R.id.graph1)
    TextView graph1;
    @BindView(R.id.graph2)
    TextView graph2;
    @BindView(R.id.graph3)
    TextView graph3;
    @BindView(R.id.graph4)
    TextView graph4;
    @BindView(R.id.graph5)
    TextView graph5;
    @BindView(R.id.graph6)
    TextView graph6;
    @BindView(R.id.graph7)
    TextView graph7;
    @BindView(R.id.graph8)
    TextView graph8;
    @BindView(R.id.graph9)
    TextView graph9;
    @BindView(R.id.txt_notata)
    TextView txt_notata;
    private String type;
    private double paintCount = 1;//图例个数
    private boolean isMonth=false;
    private String date;
    private List<TextView> list;
    private static BPD_PaintFragment instance = null;

    public BPD_PaintFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public synchronized static BPD_PaintFragment getInstance() {
        if (instance == null) {
            instance = new BPD_PaintFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public BPD_PaintP getPresenter() {
        return new BPD_PaintP();
    }

    @Override
    protected View initView() {
        return View.inflate(mActivity, R.layout.fragment_bpd__paint, null);
    }


    @Override
    public void initData() {
        super.initData();
        Bundle bundle= getArguments();
        String time = bundle.getString("time");
        BPD_MainInfoBean.ResobjBean.ChartCategBean.ChartBean chartBean= (BPD_MainInfoBean.ResobjBean.ChartCategBean.ChartBean) bundle.getSerializable("chart");
        txt_notata.setText(time);
        getDataForServers(chartBean.getCode(),time);
        if (ScreenUtils.isScreenOriatationPortrait(mActivity)) {
            Mf_Tools.setLayoutHeight(mActivity, frame_up, rela_loading, 1);//根据横竖屏切换控制视图展示   竖屏
        } else {
            Mf_Tools.setLayoutHeight(mActivity, frame_up, rela_loading, 2);//根据横竖屏切换控制视图展示  横屏
        }
        list = new ArrayList<>();
        list.add(graph1);
        list.add(graph2);
        list.add(graph3);
        list.add(graph4);
        list.add(graph5);
        list.add(graph6);
        list.add(graph7);
        list.add(graph8);
        list.add(graph9);

    }



    private void getDataForServers(String code, String time) {
        List<String> list=new ArrayList<>();
        list.add(time);
        list.add(code);
       presenter.onResume(mActivity,list);
    }

    @Override
    public void setText(Object data) {

        txt_notata.setVisibility(View.VISIBLE);
        ChartDataBean lr_bean = (ChartDataBean) data;
        ChartDataBean.ResobjBean resobj = lr_bean.getResobj();
        List<ChartDataBean.ResobjBean.DataBean> listData = resobj.getData();
        if (listData == null) {
            return;
        }

        List<double[]> listX = new ArrayList<>();
        List<double[]> listY = new ArrayList<>();
        String[] titles = new String[listData.size()];
        for (int i = 0; i < listData.size(); i++) {
            titles[i] = listData.get(i).getText();
            listX.add(resobj.getTime());
            listY.add(listData.get(i).getValue());
        }
        setGraph(titles);//设置图列的个数
        try {
            if (resobj.getFlag().equals("Z") && isMonth == true) {
                Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), mActivity, rela_loading, true, resobj.getNowTime());

            } else if (resobj.getFlag().equals("S")) {
                Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), mActivity, rela_loading, false, "month", resobj.getNowTime());
            } else {
                Mf_Tools.setData(titles, listY, listX, resobj.getMaxX(), resobj.getMaxY(), resobj.getMinY(), mActivity, rela_loading, false, resobj.getNowTime());

            }
        } catch (Exception e) {
            ToastUtils.showToast(mActivity, "图表数据有异常,请您稍后再尝试!");
            return;
        }
    }

    private void setGraph(String[] titles) {
        Mf_Tools.setGraph(list, titles);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
