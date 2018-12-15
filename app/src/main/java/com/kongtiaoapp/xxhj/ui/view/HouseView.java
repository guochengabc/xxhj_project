package com.kongtiaoapp.xxhj.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.HouseBean;

/**
 * Created by xxhj_g on 2017/4/11.
 */
public class HouseView extends View {
    private int mTextColor;//文本颜色
    private int mTextSize;//文本的大小
    private Paint paint;
    private String title = "";
    private int DEFAULT_MIN_WIDTH = 200;
    private int vertical_line=110;
    private String InPM25 = "", InCO2 = "", InTemp = "", OutTemp = "", OutHumidity = "";//室内PM2.5；室内CO2浓度；室内气温 室外温度；室外湿度

    public HouseView(Context context) {
        super(context);
    }

    public HouseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HouseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
     /*   TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HouseView, defStyleAttr, 0);
        // 默认设置为16sp，TypeValue也可以把sp转化为px  
        for (int i = 0; i < 2; i++) {
            int index = typedArray.getIndex(i);
            switch (index) {
                case R.styleable.HouseView_textColors:
                    mTextColor = typedArray.getColor(index, Color.WHITE);
                    break;
                case R.styleable.HouseView_textSizes:
                    mTextSize = typedArray.getDimensionPixelSize(index, (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16,getResources().getDisplayMetrics()));
                    break;
                default:
                    break;
            }
        }
        //typearray用完进行回收  特别占用内存
        typedArray.recycle();*/

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int baseLineY = getHeight() / 2 - 50;
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.whitesmoke));
        paint.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStrokeWidth(8);
        canvas.drawLine(30, getHeight() / 2, getWidth() / 2, 30, paint);
        canvas.drawLine(getWidth() / 2, 30, getWidth() - 30, getHeight() / 2, paint);
        //两条竖线
        canvas.drawLine(vertical_line, getHeight() - 20f, vertical_line, getHeight() / 2 - 10, paint);//xStart
        canvas.drawLine(getWidth() - vertical_line, getHeight() - 20f, getWidth() - vertical_line, getHeight() / 2 - 10, paint);
        //一条横线
        canvas.drawLine(vertical_line, getHeight() - 20f, getWidth() - vertical_line, getHeight() - 20f, paint);
        canvas.drawText("湿度 ：" + OutHumidity, getWidth() / 4 * 3 + 70f, 70f, paint);
        canvas.drawText("温度 ：" + OutTemp, getWidth() / 16 * 3, 70f, paint);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("PM2.5：" + InPM25, getWidth() / 3, baseLineY + 50, paint);
        canvas.drawText("CO2：" + InCO2, getWidth() / 3, baseLineY + 100f, paint);
        canvas.drawText("温度：" + InTemp, getWidth() / 3, baseLineY + 150f, paint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    private int measure(int origin) {
        int result = DEFAULT_MIN_WIDTH;
        int specMode = MeasureSpec.getMode(origin);
        int specSize = MeasureSpec.getSize(origin);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    public void setrenovate(HouseBean bean) {
        this.invalidate();
        HouseBean.ResobjBean resobj = bean.getResobj();
        InPM25 = resobj.getInPM25();
        InCO2 = resobj.getInCO2();
        InTemp = resobj.getInTemp();
        OutTemp = resobj.getOutTemp();
        OutHumidity = resobj.getOutHumidity();
    }

}
