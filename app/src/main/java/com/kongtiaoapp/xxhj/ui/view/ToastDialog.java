package com.kongtiaoapp.xxhj.ui.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.utils.BaseTools;


public class ToastDialog extends Dialog {

	private TextView info;
	private Button btn_ok;
	private Context context;
	private int code;

	public ToastDialog(Context context, int theme) {

		super(context, theme);
		this.context = context;
		initView();
	}

	@SuppressLint("NewApi")
	private void initView() {

		View view = View.inflate(context, R.layout.toast_dialog, null);
		this.info = (TextView) view.findViewById(R.id.info);
		this.btn_ok = (Button) view.findViewById(R.id.btn_ok);
		int a = (int) (BaseTools.getWindowsWidth((Activity) context) * 0.75);
		LayoutParams params1 = new LayoutParams(a, LayoutParams.WRAP_CONTENT);
		this.setContentView(view, params1);
		this.setCancelable(false);
	}

	public void setTextInfo(String info) {

		this.info.setText(info);
	}

	public Button getButtonOk() {

		return this.btn_ok;
	}

	public int getCode() {

		return code;
	}

	public void setCode(int code) {

		this.code = code;
	}
}
