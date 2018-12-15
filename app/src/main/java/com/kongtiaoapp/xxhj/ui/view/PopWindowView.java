package com.kongtiaoapp.xxhj.ui.view;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.kongtiaoapp.xxhj.R;

/**
 * Created by xxhj_g on 2017/6/30.
 */

public class PopWindowView extends PopupWindow implements View.OnClickListener {
    private Context activity;
    private View view;
    private boolean isFirst = true;
    private IntentActivity intentActivity;
    public PopWindowView(View contentView, int width, int height, Activity activity) {
        super(contentView, width, height);
        this.activity = activity;
        view = contentView;
        if (isFirst) {
            isFirst = false;
            initView();
        }
    }


    private void initView() {
        LinearLayout line_bianqian = ((LinearLayout) view.findViewById(R.id.line_bianqian));
        LinearLayout line_workorder = ((LinearLayout) view.findViewById(R.id.line_workorder));
        LinearLayout line_task = ((LinearLayout) view.findViewById(R.id.line_task));
        line_bianqian.setOnClickListener(this);
        line_workorder.setOnClickListener(this);
        line_task.setOnClickListener(this);
      //  this.setAnimationStyle(R.style.AnimationMode);//自定义动画效果
       /* this.setFocusable(false);//自身获取焦点，外界失去焦点
        this.setOutsideTouchable(false);//外界触摸后  会调用this.dismiss()方法*/
    }

    public void onShow(View view) {
        this.showAsDropDown(view, Gravity.END, 0);
    }

    public void disMiss() {
        this.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.line_bianqian:
                this.dismiss();
                intentActivity.getIntents("0");//新建工单
                break;
            case R.id.line_workorder:
                this.dismiss();
                intentActivity.getIntents("1");//统计
                break;
            case R.id.line_task:
               // this.dismiss();
                break;
            default:
                break;
        }
    }

    public interface IntentActivity {
        void getIntents(String whichActivity);
    }
    public void setIntent(IntentActivity intentActivity){
        this.intentActivity=intentActivity;
    }
    public void removeIntent(){
        intentActivity=null;
    }
}
