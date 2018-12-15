package com.kongtiaoapp.xxhj.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.utils.ScreenUtils;


/**
 * 用于向上平滑的dialog
 * 
 * @author Nevermore
 * 
 */
public abstract class SlideUpDialog extends Dialog {

	public Context context;
	public Dialog dialog;

	public SlideUpDialog(Context context) {
		super(context);
		this.context = context;
		initDialog();
	}

	private void initDialog() {
		dialog = new Dialog(context, R.style.transparentFrameWindowStyle);
		dialog.setContentView(initView(), new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		Window window = dialog.getWindow();
		// 设置显示动画
		window.setWindowAnimations(R.style.main_menu_animstyle);
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		wl.y = ScreenUtils.getScreenHeight(context);
		// 以下这两句是为了保证按钮可以水平满屏
		wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
		wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

		// 设置显示位置
		dialog.onWindowAttributesChanged(wl);
		// 设置点击外围解散
		dialog.setCanceledOnTouchOutside(true);
	}

	public void showDialog() {
		dialog.show();
	}

	public void dismissDialog() {
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	public void cancelDialog() {
		dialog.cancel();
	}

	public abstract View initView();
}
