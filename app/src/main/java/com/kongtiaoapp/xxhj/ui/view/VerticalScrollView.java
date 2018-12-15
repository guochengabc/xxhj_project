package com.kongtiaoapp.xxhj.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by xxhj_g on 2017/3/16.
 */
public class VerticalScrollView extends ScrollView {
    private boolean canScroll;

    private GestureDetector mGestureDetector;

    View.OnTouchListener mGestureListener;

    public VerticalScrollView(Context context, AttributeSet attrs) {

        super(context, attrs);

        mGestureDetector = new GestureDetector(new YScrollDetector());

        canScroll = true;

    }

    @Override

    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_UP)

            canScroll = true;

        return true;

    }

    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {

        @Override

        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            if (canScroll)

                if (Math.abs(distanceY) >= Math.abs(distanceX))

                    canScroll = true;

                else

                    canScroll = false;

            return canScroll;

        }

    }

}
