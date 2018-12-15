package com.kongtiaoapp.xxhj.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.MySplashAdapter;

public class MyVersionDialog extends Dialog {

	public MyVersionDialog(Context context) {
		super(context);
	}

	public MyVersionDialog(Context context, int theme) {
		super(context, theme);
	}

	public static class Builder {
		private Context context;
		private String title;
		private MySplashAdapter adapter;
		private String positiveButtonText;
		private String negativeButtonText;
		private View contentView;
		private OnClickListener positiveButtonClickListener;
		private OnClickListener negativeButtonClickListener;

		public Builder(Context context) {
			this.context = context;
		}

		public Builder setMessage(MySplashAdapter adapter) {
			this.adapter = adapter;
			return this;
		}

		/**
		 * Set the Dialog message from resource
		 *
		 * @param
		 * @return
		 */
//		public Builder setMessage(int message) {
//			this.message = (String) context.getText(message);
//			return this;
//		}

		/**
		 * Set the Dialog title from resource
		 *
		 * @param title
		 * @return
		 */
		public Builder setTitle(int title) {
			this.title = (String) context.getText(title);
			return this;
		}

		/**
		 * Set the Dialog title from String
		 *
		 * @param title
		 * @return
		 */

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setContentView(View v) {
			this.contentView = v;
			return this;
		}

		/**
		 * Set the positive button resource and it's listener
		 *
		 * @param positiveButtonText
		 * @return
		 */
		public Builder setPositiveButton(int positiveButtonText,
				OnClickListener listener) {
			this.positiveButtonText = (String) context
					.getText(positiveButtonText);
			this.positiveButtonClickListener = listener;
			return this;
		}

		public Builder setPositiveButton(String positiveButtonText,
				OnClickListener listener) {
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			return this;
		}

		public Builder setNegativeButton(int negativeButtonText,
				OnClickListener listener) {
			this.negativeButtonText = (String) context
					.getText(negativeButtonText);
			this.negativeButtonClickListener = listener;
			return this;
		}

		public Builder setNegativeButton(String negativeButtonText,
				OnClickListener listener) {
			this.negativeButtonText = negativeButtonText;
			this.negativeButtonClickListener = listener;
			return this;
		}


		public MyVersionDialog create() {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// instantiate the dialog with the custom Theme
			final MyVersionDialog dialog = new MyVersionDialog(context, R.style.Dialog);
			View layout = inflater.inflate(R.layout.version_dialog, null);
			dialog.addContentView(layout, new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			// set the dialog title
			((TextView) layout.findViewById(R.id.title)).setText(title);
			// set the confirm button
			if (positiveButtonText != null) {
				((Button) layout.findViewById(R.id.yes))
						.setText(positiveButtonText);
				if (positiveButtonClickListener != null) {
					((Button) layout.findViewById(R.id.yes))
							.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									positiveButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_POSITIVE);
								}
							});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				layout.findViewById(R.id.yes).setVisibility(
						View.GONE);
			}
			// set the cancel button
			if (negativeButtonText != null) {
				((Button) layout.findViewById(R.id.no))
						.setText(negativeButtonText);
				if (negativeButtonClickListener != null) {
					((Button) layout.findViewById(R.id.no))
							.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									negativeButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_NEGATIVE);
								}
							});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				layout.findViewById(R.id.no).setVisibility(
						View.GONE);
			}
			// set the content message
			if (adapter != null) {
				((ListView)layout.findViewById(R.id.lv_splash)).setAdapter(adapter);
				((ListView)layout.findViewById(R.id.lv_splash)).setDivider(null);
			}
			dialog.setContentView(layout);
			dialog.setCanceledOnTouchOutside(true);
			return dialog;
		}
		public MyVersionDialog createSure(int color) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// instantiate the dialog with the custom Theme
			final MyVersionDialog dialog = new MyVersionDialog(context, R.style.Dialog);
			View layout = inflater.inflate(R.layout.version_dialog, null);
			layout.findViewById(R.id.yes).setVisibility(View.GONE);//关闭确定按钮
			dialog.addContentView(layout, new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			// set the dialog title
			TextView titles=((TextView) layout.findViewById(R.id.title));
            titles.setText(title);
            titles.setBackgroundColor(color);
			// set the confirm button
			if (positiveButtonText != null) {
				((Button) layout.findViewById(R.id.yes))
						.setText(positiveButtonText);
				if (positiveButtonClickListener != null) {
					((Button) layout.findViewById(R.id.yes))
							.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									positiveButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_POSITIVE);
								}
							});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				layout.findViewById(R.id.yes).setVisibility(
						View.GONE);
			}
			// set the cancel button
			if (negativeButtonText != null) {
				Button no=((Button) layout.findViewById(R.id.no));
						no.setText(negativeButtonText);
				LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
				layoutParams.setMargins(130,0,130,0);
				no.setLayoutParams(layoutParams);
				if (negativeButtonClickListener != null) {
					((Button) layout.findViewById(R.id.no))
							.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									negativeButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_NEGATIVE);
								}
							});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				layout.findViewById(R.id.no).setVisibility(
						View.GONE);
			}
			// set the content message
			if (adapter != null) {
				((ListView)layout.findViewById(R.id.lv_splash)).setAdapter(adapter);
				((ListView)layout.findViewById(R.id.lv_splash)).setDivider(null);
			}
			dialog.setContentView(layout);
			dialog.setCanceledOnTouchOutside(true);
			return dialog;
		}

	}
}
