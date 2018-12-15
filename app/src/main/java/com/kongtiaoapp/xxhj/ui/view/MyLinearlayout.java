package com.kongtiaoapp.xxhj.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by xxhj_g on 2017/3/10.
 */
public class MyLinearlayout extends LinearLayout {
    private boolean isFocus = false;

    public MyLinearlayout(Context context) {
        super(context);
    }

    public MyLinearlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void getFocus(boolean isFocus) {
        this.isFocus = isFocus;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isFocus;
        //  return super.onInterceptTouchEvent(ev);
    }
}
