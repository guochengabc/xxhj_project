package com.kongtiaoapp.xxhj.hvac;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.App;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.HVAC_NewProjectDetailBean;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.base.BaseModule;
import com.kongtiaoapp.xxhj.mvp.base.BasePresenterLpl;
import com.kongtiaoapp.xxhj.mvp.base.BaseView;
import com.kongtiaoapp.xxhj.net.okhttp.ResponseXXHJListener;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;
import com.kongtiaoapp.xxhj.utils.popup.CommonPopupWindow;
import com.kongtiaoapp.xxhj.utils.popup.PopupViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProjectInfoHVACActivity extends BaseActivity<BasePresenterLpl, BaseView> implements BaseView {
    //项目信息
    @BindView(R.id.txt_project_name_value)
    TextView txt_project_name_value;
    @BindView(R.id.txt_project_type_value)
    TextView txt_project_type_value;
    @BindView(R.id.txt_project_city_value)
    TextView txt_project_city_value;
    @BindView(R.id.txt_project_architecture_value)
    TextView txt_project_architecture_value;
    @BindView(R.id.txt_project_area_value)
    TextView txt_project_area_value;
    @BindView(R.id.txt_project_area)
    TextView txt_project_area;

    //切换制冷或者供暖
    private ArrayAdapter projectTypeAdapter;
    private String[] projectType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_project_info2;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            HVAC_NewProjectDetailBean.ResobjBean.ProjectInfoBean projectInfo = (HVAC_NewProjectDetailBean.ResobjBean.ProjectInfoBean) intent.getSerializableExtra("info");
            txt_project_name_value.setText(projectInfo.getProjectName());
            if (projectInfo.getProjectType().equals("A")) {
                txt_project_type_value.setText("制冷");
            } else if (projectInfo.getProjectType().equals("B")) {
                txt_project_type_value.setText("供暖");
            }
            txt_project_architecture_value.setText(projectInfo.getBuildingType());
            txt_project_city_value.setText(projectInfo.getCity());
            String projectType = projectInfo.getProjectType();
            if (projectType.equals("B")) {
                txt_project_area.setText(getString(R.string.project_heatingArea));
                txt_project_area_value.setText(projectInfo.getHeatingArea());

            } else if (projectType.equals("A")) {
                txt_project_area.setText(getString(R.string.project_coolingArea));
                txt_project_area_value.setText(projectInfo.getCoolingArea());
            }
        }
    }

    @Override
    protected BasePresenterLpl getPresenter() {
        return new BasePresenterLpl() {
            @Override
            protected BaseModule getModel() {
                return new BaseModule() {
                    @Override
                    public void getDataForservices(Activity activity, Object data, ResponseXXHJListener listener) {

                    }
                };
            }
        };
    }

    @OnClick({R.id.iv_back, R.id.line_projectType})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.line_projectType:
                setProjectType();//设置制冷或者供暖项目类型
                break;
            default:
                break;
        }
    }

    private void setProjectType() {
        if (projectTypeAdapter == null) {
            projectType = new String[]{"制冷", "供暖"};
            projectTypeAdapter = new ArrayAdapter<String>(this,
                    R.layout.popuwindow_lv_item, projectType);
        }

        CommonPopupWindow popupWindow = new CommonPopupWindow(this, R.layout.posting_listview) {
            @Override
            protected void showWindow(PopupViewHolder view) {
                view.setArrayAdapter(R.id.posting_list, projectTypeAdapter);
                view.setOnItemClickListener(R.id.posting_list, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        dismiss();
                        if (position == 0) {
                            App.sp.setProjectTypeFinish("A");
                            //  startActivity(new Intent(Group_Project_DetailsActivity.this,Group_Project_DetailsActivity.class));
                            ToastUtils.showToast(ProjectInfoHVACActivity.this, "已经切换到夏季制冷，重新进入");
                            finish();
                        } else if (position == 1) {
                            App.sp.setProjectTypeFinish("B");
                            //此处最好不要进行修改，之前试过好多方法都是不行，暂时先这么处理
                            ToastUtils.showToast(ProjectInfoHVACActivity.this, "已经切换到冬季供暖，重新进入");
                            //  startActivity(new Intent(Group_Project_DetailsActivity.this,Group_Project_DetailsActivity.class));
                            finish();
                        }
                    }
                });
            }
        };
        popupWindow.listPopup(txt_project_type_value, projectType.length);

    }

    @Override
    public void setText(Object data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
