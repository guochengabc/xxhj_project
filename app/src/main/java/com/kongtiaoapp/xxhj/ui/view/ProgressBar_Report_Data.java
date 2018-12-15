package com.kongtiaoapp.xxhj.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import com.kongtiaoapp.xxhj.R;

import java.text.DecimalFormat;

/**
 * Created by xxhj_g on 2016/12/19.
 */
public class ProgressBar_Report_Data extends ProgressBar {
    private Paint mTextPaint;
    private int mTextColor;
    private int mTextSize;
    private int mTextX = 0;
    private int mTextY;

    public ProgressBar_Report_Data(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressBar_Report_Data(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ProgressBar_Report_Data, defStyle, 0);
        mTextColor = ta.getColor(R.styleable.ProgressBar_Report_Data_textColor, 0xFF000000);
        ta.recycle();
        mTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 15.0f, getResources().getDisplayMetrics());
        mTextPaint = new Paint();
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mTextY = getMeasuredHeight() - 1;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTextX = (int) Math.ceil(getMeasuredWidth() * ((float)getProgress() / getMax()));
        canvas.drawText((new DecimalFormat("0").format((float)getProgress() / getMax() * 100)) + "%", mTextX, mTextY, mTextPaint);
    }
}

