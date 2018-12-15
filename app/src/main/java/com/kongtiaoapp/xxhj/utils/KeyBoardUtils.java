package com.kongtiaoapp.xxhj.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

//打开或关闭软键盘
public class KeyBoardUtils {
	
	/**
	 * 打开软键盘
	 * 
	 * @param mEditText输入框
	 * @param mContext上下文
	 */
	public static void openKeybord(EditText mEditText, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
				InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	/**
	 * 关闭软键盘
	 * 
	 * @param mEditText输入框
	 * @param mContext上下文
	 */
	public static void closeKeybord(EditText mEditText, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
	}

	/**
	 * 判断键盘是否显示
	 *
	 * @param context 上下文
	 * @return {@code true}: 显示<br>{@code false}: 不显示
	 */
	public static boolean isShowSoftInput(Context context) {
		return ((InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE)).isActive();
	}
	/**
	 * 动态隐藏软键盘
	 *
	 * @param activity activity
	 */
	public static void hideSoftInput(Activity activity) {
		View view = activity.getWindow().peekDecorView();
		if (view != null) {
			InputMethodManager inputmanger = (InputMethodManager) activity
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}


}
