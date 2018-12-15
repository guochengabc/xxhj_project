package com.kongtiaoapp.xxhj.ui.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by G_XXHJ on 2018/5/11.  横向gridview滑动  外层要嵌套 HorizontalScrollView
 */

public class HorizontalGridView extends GridView{

    public HorizontalGridView(Context context) {
        super(context);
    }

    public HorizontalGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /**这个是必须要设置的*/
    public void setGridViewParam(Activity context, List list){
        int size = list.size();
        int length = 100;
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        this.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        this.setColumnWidth(itemWidth); // 设置列表项宽
        this.setHorizontalSpacing(5); // 设置列表项水平间距
        this.setStretchMode(GridView.NO_STRETCH);
        this.setNumColumns(size); // 设置列数量=列表集合数
    }
}
