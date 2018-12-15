package com.kongtiaoapp.xxhj.activites;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.OverRunEvaluateBean;
import com.kongtiaoapp.xxhj.bean.RunStatusBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.OverAllEvaluatePresenter;
import com.kongtiaoapp.xxhj.mvp.view.OverAllEvaluateView;
import com.kongtiaoapp.xxhj.ui.address.ScreenUtils;
import com.kongtiaoapp.xxhj.ui.view.FourRectView;
import com.kongtiaoapp.xxhj.ui.view.Mf_Tools;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 整体运行评价   GUO_C
 */
public class OverAllEvaluateActivity extends BaseActivity<OverAllEvaluatePresenter, OverAllEvaluateView> implements OverAllEvaluateView {

    //第一张图表
    @BindView(R.id.fl_one)
    FrameLayout fl_one;
    @BindView(R.id.rela_paint)
    RelativeLayout rela_loading;
    @BindView(R.id.graph1)
    TextView graph1;
    @BindView(R.id.graph2)
    TextView graph2;
    @BindView(R.id.graph3)
    TextView graph3;
    @BindView(R.id.graph4)
    TextView graph4;
    @BindView(R.id.txt_enegry)
    TextView txt_enegry;
    @BindView(R.id.txt_notata)
    TextView txt_notata;

    //第二张图表
    @BindView(R.id.frame_down)
    FrameLayout frame_down;
    @BindView(R.id.rela_paint1)
    RelativeLayout rela_loading1;
    @BindView(R.id.graph11)//graph1-9代表的是图例
            TextView graph11;
    @BindView(R.id.graph21)
    TextView graph21;
    @BindView(R.id.graph31)
    TextView graph31;
    @BindView(R.id.graph41)
    TextView graph41;
    @BindView(R.id.txt_notata1)
    TextView txt_notata1;
    @BindView(R.id.txt_COP)
    TextView txt_COP;//cop评价
    //第三张图表
    @BindView(R.id.frame_cop)
    FrameLayout frame_cop;
    @BindView(R.id.graph_cop1)
    TextView graph_cop1;
    @BindView(R.id.graph_cop2)
    TextView graph_cop2;
    @BindView(R.id.graph_cop3)
    TextView graph_cop3;
    @BindView(R.id.graph_cop4)
    TextView graph_cop4;
    @BindView(R.id.rela_paint2)
    RelativeLayout rela_loading2;
    @BindView(R.id.txt_notata2)
    TextView txt_notata2;
    //第四张图表
    @BindView(R.id.fourView_scop)
    FourRectView fourView_scop;

    //第五张图表
    @BindView(R.id.fiveView_cop)
    FourRectView fiveView_cop;

    //第六张图表
    @BindView(R.id.sixView_enegry)
    FourRectView sixView_enegry;
    @BindView(R.id.txt_run_enegry)
    TextView txt_run_enegry;
    private List<TextView> list, list1, list2, list7, list8;
    //第七张图表
    @BindView(R.id.frame_freeze)
    FrameLayout frame_freeze;
    @BindView(R.id.rela_paint7)
    RelativeLayout rela_loading7;
    @BindView(R.id.graph_freeze1)
    TextView graph_freeze1;
    @BindView(R.id.graph_freeze2)
    TextView graph_freeze2;
    @BindView(R.id.graph_freeze3)
    TextView graph_freeze3;
    @BindView(R.id.graph_freeze4)
    TextView graph_freeze4;
    @BindView(R.id.txt_notata7)
    TextView txt_notata7;
    //第八张图表
    @BindView(R.id.frame_freeze_temp)
    FrameLayout frame_freeze_temp;
    @BindView(R.id.rela_paint8)
    RelativeLayout rela_loading8;
    @BindView(R.id.graph_freezeT1)
    TextView graph_freezeT1;
    @BindView(R.id.graph_freezeT2)
    TextView graph_freezeT2;
    @BindView(R.id.graph_freezeT3)
    TextView graph_freezeT3;
    @BindView(R.id.graph_freezeT4)
    TextView graph_freezeT4;
    @BindView(R.id.txt_notata8)
    TextView txt_notata8;
    @BindView(R.id.txt_run_temp)
    TextView txt_run_temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_over_all_evaluate;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Mf_Tools.setEvalLayoutHeight(this, fl_one, rela_loading, newConfig.orientation);
        Mf_Tools.setEvalLayoutHeight(this, frame_down, rela_loading1, newConfig.orientation);
        Mf_Tools.setLayoutHeight(this, frame_cop, rela_loading2, newConfig.orientation);
        Mf_Tools.setLayoutHeight(this, frame_freeze, rela_loading7, newConfig.orientation);
        Mf_Tools.setLayoutHeight(this, frame_freeze_temp, rela_loading8, newConfig.orientation);
    }


    @Override
    protected void initView() {
        if (ScreenUtils.isScreenOriatationPortrait(this)){
            Mf_Tools.setEvalLayoutHeight(this, fl_one, rela_loading,1);
            Mf_Tools.setEvalLayoutHeight(this, frame_down, rela_loading1, 1);
            Mf_Tools.setLayoutHeight(this, frame_cop, rela_loading2, 1);
            Mf_Tools.setLayoutHeight(this, frame_freeze, rela_loading7, 1);
            Mf_Tools.setLayoutHeight(this, frame_freeze_temp, rela_loading8, 1);
        }else{
            Mf_Tools.setEvalLayoutHeight(this, fl_one, rela_loading,2);
            Mf_Tools.setEvalLayoutHeight(this, frame_down, rela_loading1, 2);
            Mf_Tools.setLayoutHeight(this, frame_cop, rela_loading2, 2);
            Mf_Tools.setLayoutHeight(this, frame_freeze, rela_loading7, 2);
            Mf_Tools.setLayoutHeight(this, frame_freeze_temp, rela_loading8, 2);
        }
        list = new ArrayList<>();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list7 = new ArrayList<>();
        list8 = new ArrayList<>();
        list.add(graph1);
        list.add(graph2);
        list.add(graph3);
        list.add(graph4);
        list1.add(graph11);
        list1.add(graph21);
        list1.add(graph31);
        list1.add(graph41);
        list2.add(graph_cop1);
        list2.add(graph_cop2);
        list2.add(graph_cop3);
        list2.add(graph_cop4);
        list7.add(graph_freeze1);
        list7.add(graph_freeze2);
        list7.add(graph_freeze3);
        list7.add(graph_freeze4);
        list8.add(graph_freezeT1);
        list8.add(graph_freezeT2);
        list8.add(graph_freezeT3);
        list8.add(graph_freezeT4);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        presenter.onResume(this);
    }

    private void setGraph(List<TextView> lists, String[] titles) {
        Mf_Tools.setGraph(lists, titles);
    }

    @Override
    protected OverAllEvaluatePresenter getPresenter() {
        return new OverAllEvaluatePresenter();
    }

    @Override
    public void setText(Object data) {

    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        finish();
    }

    @Override
    public void setList(Object data) {
        OverRunEvaluateBean bean = (OverRunEvaluateBean) data;
        OverRunEvaluateBean.ResobjBean resobj = bean.getResobj();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        txt_notata.setText(format.format(new Date()));
        txt_notata.setTextColor(getResources().getColor(R.color.theme_color));
        txt_notata.setVisibility(View.VISIBLE);
        //第一个图表
        OverRunEvaluateBean.ResobjBean.EcEvalBean ecEval = resobj.getEcEval();
        OverRunEvaluateBean.ResobjBean.EcEvalBean.RunECBean runEC = ecEval.getRunEC();
        List<String> ecCompared = runEC.getCompared();
        List<OverRunEvaluateBean.ResobjBean.EcEvalBean.RunECBean.DataBeanXXXXXXX> listDataEC = runEC.getData();
        if (ecEval.getDesc() != null) {
            txt_enegry.setText(ecEval.getDesc());
        }
        if (listDataEC == null) {
            return;
        }
        List<String> listX = new ArrayList<>();
        List<double[]> listY = new ArrayList<>();
        String[] titles = new String[listDataEC.size()];
        for (int i = 0; i < listDataEC.size(); i++) {
            titles[i] = listDataEC.get(i).getText();
            listX.addAll(ecCompared);
            double[] dValue = listDataEC.get(i).getValue();
            listY.add(dValue);
        }
        setGraph(list, titles);//设置图列的个数
        try {
            Mf_Tools.setData(titles, listY, listX, 24, runEC.getMaxY(), runEC.getMinY(), this, rela_loading, runEC.getNowTime());
        } catch (Exception e) {
            Log.i(TAG, e.getMessage() + "===========");
            ToastUtils.showToast(this, "图表数据有异常,请您稍后再尝试!");
            return;
        }
        //第二个图表
        OverRunEvaluateBean.ResobjBean.EcEvalBean.RunSCBean runSC = ecEval.getRunSC();
        List<String> scCompared = runSC.getCompared();
        List<OverRunEvaluateBean.ResobjBean.EcEvalBean.RunSCBean.DataBeanXXXXXX> listData_SC = runSC.getData();
        if (listData_SC == null) {
            return;
        }
        List<String> listX_SC = new ArrayList<>();
        List<double[]> listY_SC = new ArrayList<>();
        String[] titles_SC = new String[listData_SC.size()];

        for (int i = 0; i < listData_SC.size(); i++) {
            titles_SC[i] = listData_SC.get(i).getText();
            listX_SC.addAll(scCompared);
            double[] dValue = listData_SC.get(0).getValue();
            listY_SC.add(dValue);
        }
        setGraph(list1, titles_SC);//设置图列的个数
        try {
            Mf_Tools.setData(titles_SC, listY_SC, listX_SC, 24, runSC.getMaxY(), runSC.getMinY(), this, rela_loading1, runSC.getNowTime());
        } catch (Exception e) {
            ToastUtils.showToast(this, "图表数据有异常,请您稍后再尝试!");
            return;
        }
        //第三个图
        OverRunEvaluateBean.ResobjBean.CopEvalBean copEval = resobj.getCopEval();
        OverRunEvaluateBean.ResobjBean.CopEvalBean.FefrigeEFFBean effBean = copEval.getFefrigeEFF();
        List<OverRunEvaluateBean.ResobjBean.CopEvalBean.FefrigeEFFBean.DataBeanXX> listData_COP = effBean.getData();
        int indicator = effBean.getIndicator();//前几个是一类，后几个是另外一类   这里面也可以理解为分2类标题
        if (copEval.getDesc() != null) {
            txt_COP.setText(copEval.getDesc());
        }
        if (listData_COP == null) {
            return;
        }
        List<String[]> listX_Third = new ArrayList<>();//第三张图表
        List<double[]> listY_Third = new ArrayList<>();
        String[] titles_Third = new String[2];
        double[] yValueCop = new double[2];
        double[] yValueScop = new double[2];
        String[] xCOP = new String[2];
        String[] xSCOP = new String[2];

        for (int i = 0; i < 2; i++) {
            titles_Third[i] = listData_COP.get(0).getSign().get(i);
            yValueScop[i] = listData_COP.get(0).getValue()[i];
            yValueCop[i] = listData_COP.get(0).getValue()[i + 2];
            xSCOP[i] = effBean.getCompared().get(i);
            xCOP[i] = effBean.getCompared().get(i + 2);
        }
        listY_Third.add(yValueScop);
        listY_Third.add(yValueCop);
        listX_Third.add(xSCOP);
        listX_Third.add(xCOP);
        setGraph(list2, titles_Third);//设置图列的个数

        try {
            Mf_Tools.setData(titles_Third, listY_Third, listX_Third, 29, effBean.getMaxY(), effBean.getMinY(), this, rela_loading2, effBean.getNowTime(), 2);
        } catch (Exception e) {
            Log.i(TAG, e.getMessage() + "============");
            ToastUtils.showToast(this, "图表数据有异常,请您稍后再尝试!");
            return;
        }
        //第四个图表
        RunStatusBean beanFour = new RunStatusBean();
        RunStatusBean.StatusBean statusBean = new RunStatusBean.StatusBean();
        RunStatusBean.StatusBean statusBean1 = new RunStatusBean.StatusBean();
        RunStatusBean.StatusBean statusBean2 = new RunStatusBean.StatusBean();
        RunStatusBean.StatusBean statusBean3 = new RunStatusBean.StatusBean();
        OverRunEvaluateBean.ResobjBean.CopEvalBean.ScopScoreBean scopScore = copEval.getScopScore();
        OverRunEvaluateBean.ResobjBean.CopEvalBean.CopScoreBean copScore = copEval.getCopScore();
        List<Float> scopScoreLimit = scopScore.getLimit();
        List<String> scopTable = scopScore.getTable();
        beanFour.setPaintContent(listData_COP.get(0).getSign().get(0));

        if (scopScoreLimit != null) {
            statusBean.setScoreB(scopScoreLimit.get(0));
            statusBean.setStatusB(scopTable.get(0));
            statusBean1.setScoreB(scopScoreLimit.get(1));
            statusBean1.setStatusB(scopTable.get(1));
            statusBean2.setScoreB(scopScoreLimit.get(2));
            statusBean2.setStatusB(scopTable.get(2));
            statusBean3.setStatusB(scopTable.get(3));
        }
        List<RunStatusBean.StatusBean> list = new ArrayList<>();
        list.add(statusBean);
        list.add(statusBean1);
        list.add(statusBean2);
        list.add(statusBean3);
        beanFour.setList(list);
        beanFour.setRelaScore((float) scopScore.getScore());
        fourView_scop.setStatusB(beanFour);
        //第五个图表
        beanFour.setPaintContent(listData_COP.get(0).getSign().get(1));
        List<Float> copScoreLimit = copScore.getLimit();
        List<String> copTable = copScore.getTable();
        if (copScoreLimit != null) {
            statusBean.setScoreB(copScoreLimit.get(0));
            statusBean.setStatusB(copTable.get(0));
            statusBean1.setScoreB(copScoreLimit.get(1));
            statusBean1.setStatusB(copTable.get(1));
            statusBean2.setScoreB(copScoreLimit.get(2));
            statusBean2.setStatusB(copTable.get(2));
            statusBean3.setStatusB(copTable.get(3));
        }
        List<RunStatusBean.StatusBean> listcop = new ArrayList<>();
        listcop.add(statusBean);
        listcop.add(statusBean1);
        listcop.add(statusBean2);
        listcop.add(statusBean3);
        beanFour.setList(listcop);
        beanFour.setRelaScore((float) copScore.getScore());
        fiveView_cop.setStatusB(beanFour);

        //第六张图表
        OverRunEvaluateBean.ResobjBean.PumpEvalBean pumpEval = resobj.getPumpEval();
        OverRunEvaluateBean.ResobjBean.PumpEvalBean.StatusBean pumpStatus = pumpEval.getStatus();
        beanFour.setPaintContent(pumpStatus.getData().get(0).getText());
        List<Float> pumpLimit = pumpStatus.getLimit();
        List<String> pumpTable = pumpStatus.getTable();
        if (pumpEval.getDesc() != null) {
            txt_run_enegry.setText(pumpEval.getDesc());
        }

        if (copScoreLimit != null) {
            statusBean.setScoreB(pumpLimit.get(0));
            statusBean.setStatusB(pumpTable.get(0));
            statusBean1.setScoreB(pumpLimit.get(1));
            statusBean1.setStatusB(pumpTable.get(1));
            statusBean2.setScoreB(pumpLimit.get(2));
            statusBean2.setStatusB(pumpTable.get(2));
            statusBean3.setStatusB(pumpTable.get(3));
        }
        List<RunStatusBean.StatusBean> listpump = new ArrayList<>();
        listpump.add(statusBean);
        listpump.add(statusBean1);
        listpump.add(statusBean2);
        listpump.add(statusBean3);
        beanFour.setList(listpump);
        beanFour.setRelaScore((float) pumpStatus.getScore());
        sixView_enegry.setStatusB(beanFour);

        //第七张图表
        OverRunEvaluateBean.ResobjBean.WtEvalBean wtEval = resobj.getWtEval();
        OverRunEvaluateBean.ResobjBean.WtEvalBean.DiffTempBean diffTemp = wtEval.getDiffTemp();//冷冻温差
        List<OverRunEvaluateBean.ResobjBean.WtEvalBean.DiffTempBean.DataBeanX> listTempDis = diffTemp.getData();
        int wtDisIndicator = diffTemp.getIndicator();//前几个是一类，后几个是另外一类   这里面也可以理解为分2类标题
        if (wtEval.getDesc() != null) {
            txt_run_temp.setText(wtEval.getDesc());
        }
        if (listTempDis == null) {
            return;
        }
        List<String[]> listX_Seven = new ArrayList<>();//第三张图表
        List<double[]> listY_Seven = new ArrayList<>();
        String[] titles_Seven = new String[2];
        double[] yValueWTcool = new double[2];
        double[] yValueWTcold = new double[2];
        String[] xCool = new String[2];
        String[] xCold = new String[2];

        for (int i = 0; i < 2; i++) {
            titles_Seven[i] = listTempDis.get(0).getSign().get(i);
            yValueWTcool[i] = listTempDis.get(0).getValue()[i];
            yValueWTcold[i] = listTempDis.get(0).getValue()[i + 2];
            xCool[i] = diffTemp.getCompared().get(i);
            xCold[i] = diffTemp.getCompared().get(i + 2);
        }
        listY_Seven.add(yValueWTcool);
        listY_Seven.add(yValueWTcold);
        listX_Seven.add(xCool);
        listX_Seven.add(xCold);
        setGraph(list7, titles_Seven);//设置图列的个数
        try {
            Mf_Tools.setData(titles_Seven, listY_Seven, listX_Seven, 29, diffTemp.getMaxY(), diffTemp.getMinY(), this, rela_loading7, diffTemp.getNowTime(), 2);
        } catch (Exception e) {
            Log.i(TAG, e.getMessage() + "============");
            ToastUtils.showToast(this, "图表数据有异常,请您稍后再尝试!");
            return;
        }

        //第八张图表
        OverRunEvaluateBean.ResobjBean.WtEvalBean.TempBean wtTemp = wtEval.getTemp();//温度
        List<OverRunEvaluateBean.ResobjBean.WtEvalBean.TempBean.DataBean> listTemp = wtTemp.getData();
        int wtIndicator = wtTemp.getIndicator();//前几个是一类，后几个是另外一类   这里面也可以理解为分2类标题
        if (listTemp == null) {
            return;
        }
        List<String[]> listX_Eight = new ArrayList<>();//第三张图表
        List<double[]> listY_Eight = new ArrayList<>();
        String[] titles_Eight = new String[2];
        double[] yValueWTcold_Temp = new double[2];
        double[] yValueWTcool_Temp = new double[2];
        String[] xCold_Temp = new String[2];
        String[] xCool_Temp = new String[2];

        for (int i = 0; i < 2; i++) {
            titles_Eight[i] = listTemp.get(0).getSign().get(i);
            yValueWTcold_Temp[i] = listTemp.get(0).getValue()[i];
            yValueWTcool_Temp[i] = listTemp.get(0).getValue()[i + 2];
            xCold_Temp[i] = wtTemp.getCompared().get(i);
            xCool_Temp[i] = wtTemp.getCompared().get(i + 2);
        }
        listY_Eight.add(yValueWTcold_Temp);
        listY_Eight.add(yValueWTcool_Temp);
        listX_Eight.add(xCold_Temp);
        listX_Eight.add(xCool_Temp);
        setGraph(list8, titles_Eight);//设置图列的个数
        try {
            Mf_Tools.setData(titles_Eight, listY_Eight, listX_Eight, 29, wtTemp.getMaxY(), wtTemp.getMinY(), this, rela_loading8, diffTemp.getNowTime(), 2);
        } catch (Exception e) {
            Log.i(TAG, e.getMessage() + "============");
            ToastUtils.showToast(this, "图表数据有异常,请您稍后再尝试!");
            return;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
