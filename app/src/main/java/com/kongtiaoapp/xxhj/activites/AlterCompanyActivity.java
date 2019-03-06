package com.kongtiaoapp.xxhj.activites;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.mvp.base.BaseActivity;
import com.kongtiaoapp.xxhj.mvp.presenter.AlterCompanyPresenter;
import com.kongtiaoapp.xxhj.mvp.view.AlterCompanyView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;


/**
 * @author 作者 Luoye
 * @version 创建时间：2016年8月3日 下午5:26:26
 * @description 类说明 : 修改昵称页面
 */

public class AlterCompanyActivity extends BaseActivity<AlterCompanyPresenter, AlterCompanyView> implements OnClickListener, AlterCompanyView {

    private TextView tv_submit;//提交
    private EditText et_name;//公司昵称
    private String company;
    private ImageView iv_back;//返回上一界面

    @Override
    protected int initContentView() {
        return R.layout.activity_alter_company;
    }

    /*初始化相关参数*/
    @Override
    protected void initView() {
        tv_submit = (TextView) findViewById(R.id.tv_submit);
        tv_submit.setOnClickListener(this);
        et_name = (EditText) findViewById(R.id.et_name);
        iv_back = ((ImageView) findViewById(R.id.iv_back));
        iv_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {//初始化数据
        company = getIntent().getStringExtra("company");
        et_name.setText(company);
    }

    @Override
    protected AlterCompanyPresenter getPresenter() {
        return new AlterCompanyPresenter();
    }

    /**
     * 设置点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_submit:
                company = et_name.getText().toString();
                if (TextUtils.isEmpty(company)) {
                    ToastUtils.showToast(this, getString(R.string.company_notnull));
                    return;
                }
                Intent mIntent = new Intent();
                mIntent.putExtra("company", company);
                // 设置结果，并进行传送
                this.setResult(RESULT_OK, mIntent);
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void setText(Object text) {

    }
}
