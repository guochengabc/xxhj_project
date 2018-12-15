package com.kongtiaoapp.xxhj.ui.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.adapter.PopAdapter;
import com.kongtiaoapp.xxhj.bean.DeviceParam;
import java.util.List;

/**
 * @author lvy
 * @Description 自定义popupWindow
 * @date 2016/9/9
 */
public class MyPopupWindow extends PopupWindow {

    private Context context;
    private Window window;
    private WindowManager.LayoutParams lp;

    private View mView;
    private int width;

    private AdapterView.OnItemClickListener listener;

    private List<DeviceParam.EnumValue> data;
    private ListView listView;

    public MyPopupWindow(Context context, Window window) {
        super(context);
        this.context = context;
        this.window = window;
    }

    public void setWidthAndHeight(int width) {
        this.width = width;
    }

    public void setList(List<DeviceParam.EnumValue> data) {
        this.data = data;
    }

    public void setListener(AdapterView.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void initView() {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = layoutInflater.inflate(R.layout.dialog_sort, null);
        listView = (ListView) mView.findViewById(R.id.listView);
        listView.setAdapter(new PopAdapter(context,data));
        listView.setOnItemClickListener(listener);
        //开始设置popwindow的各种属性
        //设置view
        this.setContentView(mView);
        this.setWidth(width);
        if (data.size() > 6) {
            this.setHeight(540);
        } else {
            this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        //this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //这边需要注意
        this.setOutsideTouchable(true);
        ColorDrawable cd = new ColorDrawable(0x000000);
        setBackgroundDrawable(cd);
        //设置弹出动画
        this.setAnimationStyle(R.style.popup_animStyle);
        lp = window.getAttributes();

        //显示时背景变暗
        changeLight2Show();

        //设置关闭的监听事件，关闭后恢复背景
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                changeLight2close();
            }
        });

    }

    /**
     * 恢复背景
     */
    public void changeLight2close() {
        final ValueAnimator animation = ValueAnimator.ofFloat(0.8f, 1.0f);
        animation.setDuration(300);
        animation.start();

        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                lp.alpha = (float) valueAnimator.getAnimatedValue();
                window.setAttributes(lp);
            }
        });
    }


    /**
     * 背景变暗
     */
    public void changeLight2Show() {
        final ValueAnimator animation = ValueAnimator.ofFloat(1.0f, 0.8f);
        animation.setDuration(300);
        animation.start();

        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                lp.alpha = (float) valueAnimator.getAnimatedValue();
                window.setAttributes(lp);
            }
        });
    }
}
