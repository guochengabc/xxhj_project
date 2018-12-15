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
import com.kongtiaoapp.xxhj.mvp.presenter.AlterSchoolPresenter;
import com.kongtiaoapp.xxhj.mvp.view.AlterSchoolView;
import com.kongtiaoapp.xxhj.utils.emoji.ToastUtils;


/** 
 * @author 作者 Luoye 
 * @version 创建时间：2016年8月3日 下午5:26:26 
 *
 * @description 类说明 : 修改昵称页面
 */

public class AlterSchoolActivity extends BaseActivity<AlterSchoolPresenter,AlterSchoolView> implements OnClickListener ,AlterSchoolView{

	private TextView tv_submit;//提交学校相关信息
	private EditText et_name;//编辑学校的名称
	private String school;

	@Override
	protected int initContentView() {
		return R.layout.activity_alter_school;
	}

	@Override
	protected void initView() {
		tv_submit = (TextView) findViewById(R.id.tv_submit);
		tv_submit.setOnClickListener(this);
		et_name = (EditText) findViewById(R.id.et_name);
		((ImageView) findViewById(R.id.iv_back)).setOnClickListener(new OnClickListener() {
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
	protected void initData() {
		school = getIntent().getStringExtra("school");
		et_name.setText(school);
	}

	@Override
	protected AlterSchoolPresenter getPresenter() {
		return new AlterSchoolPresenter();
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_submit:
			school = et_name.getText().toString();
			if (TextUtils.isEmpty(school)) {
				ToastUtils.showToast(this, getString(R.string.school_notnull));
				return;
			}
			Intent mIntent = new Intent();
			mIntent.putExtra("school", school);
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
