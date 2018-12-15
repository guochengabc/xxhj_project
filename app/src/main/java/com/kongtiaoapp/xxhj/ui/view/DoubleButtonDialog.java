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


public class DoubleButtonDialog extends Dialog {

	private TextView info;
	private Button btn_ok;
	private Button btn_cannel;
	private Context context;
	private int code;

	public DoubleButtonDialog(Context context, int theme) {

		super(context, theme);
		this.context = context;
		initView();
	}

	@SuppressLint("NewApi")
	private void initView() {

		View view = View.inflate(context, R.layout.my_dialog, null);
		this.info = (TextView) view.findViewById(R.id.info);
		this.btn_ok = (Button) view.findViewById(R.id.btn_ok);
		this.btn_cannel = (Button) view.findViewById(R.id.btn_canel);
		int a = (int) (BaseTools.getWindowsWidth((Activity) context) * 0.75);
		// int b = (int) (BaseTools.getWindowsWidth((Activity) context) * 0.25);
		LayoutParams params1 = new LayoutParams(a, LayoutParams.WRAP_CONTENT);
		this.setContentView(view, params1);
		// this.setCancelable(false);//屏蔽back键和点击对话框外围消失
	}

	public void setTextInfo(String info) {

		this.info.setText(info);
	}

	public Button getButtonOk() {

		return this.btn_ok;
	}

	public Button getButtonCannel() {

		return this.btn_cannel;

	}

	public int getCode() {

		return code;
	}

	public void setCode(int code) {

		this.code = code;
	}
}
