package com.kongtiaoapp.xxhj.ui.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class TimeButton extends TextView implements View.OnClickListener {

	public TimeButton(Context context) {

		super(context);
		this.setOnClickListener(this);
	}

	public TimeButton(Context context, AttributeSet attrs) {

		super(context, attrs);
		this.setOnClickListener(this);
	}

	public TimeButton(Context context, AttributeSet attrs, int defStyle) {

		super(context, attrs, defStyle);
		this.setOnClickListener(this);
	}

	private long millisInFuture = 60000;
	private long countDownInterval = 1000;
	private TimeStartClickListener clickListener;
	private TimeCount timeCount;

	public long getMillisInFuture() {

		return millisInFuture;
	}

	public void setMillisInFuture(long millisInFuture) {

		this.millisInFuture = millisInFuture;
	}

	public long getCountDownInterval() {

		return countDownInterval;
	}

	public void setCountDownInterval(long countDownInterval) {

		this.countDownInterval = countDownInterval;
	}

	class TimeCount extends CountDownTimer {

		private TimeButton button;

		public TimeCount(long millisInFuture, long countDownInterval,
				TimeButton button) {

			super(millisInFuture, countDownInterval);// 参数依次为�?�时�?,和计时的时间间隔
			this.button = button;
		}

		@Override
		public void onFinish() {// 计时完毕时触�?

			button.setText("重新验证");
			button.setClickable(true);
			timeCount = null;
			// TimeButton.this.setBackgroundResource(R.drawable.shape_timebutton_back);
		}

		@Override
		public void onTick(long millisUntilFinished) {// 计时过程显示

			button.setClickable(false);
			button.setText(millisUntilFinished / 1000 + "秒");
		}
	}

	public interface TimeStartClickListener {

		void ClickStart(View v);
	}

	public void setTimeStartClickListener(TimeStartClickListener clickListener) {

		this.clickListener = clickListener;
	}

	@Override
	public void onClick(View v) {

		// TODO Auto-generated method stub
		if (clickListener != null) {
			clickListener.ClickStart(v);
		}
	}

	public void Start() {

		timeCount = new TimeCount(millisInFuture, countDownInterval, this);
		timeCount.start();
	}
}
