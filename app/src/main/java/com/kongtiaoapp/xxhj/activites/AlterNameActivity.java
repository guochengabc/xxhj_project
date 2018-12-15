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
import com.kongtiaoapp.xxhj.mvp.presenter.AlterNamePresenter;
import com.kongtiaoapp.xxhj.mvp.view.AlterNameView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;


/**
 * @author 作者 Luoye
 * @version 创建时间：2016年8月3日 下午5:26:26
 * @description 类说明 : 修改昵称页面
 */

public class AlterNameActivity extends BaseActivity<AlterNamePresenter,AlterNameView> implements OnClickListener ,AlterNameView{

    private TextView tv_submit;//提交修改昵称
    private EditText et_name;//修改姓名
    private String name;

    @Override
    protected int initContentView() {
        return R.layout.activity_alter_name;
    }

    @Override
    protected void initView() {
        tv_submit = (TextView) findViewById(R.id.tv_submit);
        tv_submit.setOnClickListener(this);
        et_name = (EditText) findViewById(R.id.et_name);
        ((ImageView) findViewById(R.id.iv_back)).setOnClickListener(view -> finish());
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        name = getIntent().getStringExtra("name");
        et_name.setText(name);
    }

    @Override
    protected AlterNamePresenter getPresenter() {
        return new AlterNamePresenter();
    }

/*设置相关事件*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_submit:
                name = et_name.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.showToast(this, getString(R.string.name_notnull));
                    return;
                }
                Intent mIntent = new Intent();
                mIntent.putExtra("name", name);
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
