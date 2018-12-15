package com.kongtiaoapp.xxhj.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.kongtiaoapp.xxhj.R;
import com.kongtiaoapp.xxhj.bean.RunStatusBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxhj_g on 2017/9/14.
 */

public class FourRectView extends View {
    private int DEFAULT_MIN_WIDTH = 200;
    private List<RunStatusBean.StatusBean> list = new ArrayList<>();
    private float relaScore = 0;//真实分数
    private String paintContent;
    private List<RunStatusBean.CopBean> list_cop;

    public FourRectView(Context context) {
        super(context);
    }

    public FourRectView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FourRectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (!list.isEmpty() && list != null) {

            int base_p = 60;//公共内边距
            float rectWidth = getWidth() - base_p - base_p;//矩形宽度
            float rectHeight = 90;//矩形高度;
            float centerText = 10;//文字居中居中
            float centerPosition = 40;
            float arrowMore = 35;//真是分数多出的一块
            float rela_line = rectHeight + base_p;//真是分数线的开始画线头部分
            /**
             * 方法 说明 drawRect 绘制矩形 drawCircle 绘制圆形 drawOval 绘制椭圆 drawPath 绘制任意多边形
             * drawLine 绘制直线 drawPoin 绘制点
             */
            // 创建画笔
            Paint p = new Paint();
            p.setColor(getResources().getColor(R.color.a999999));// 设置红色
        /* 设置渐变色 这个矩方形的颜色是改变的, */
            Shader mShader = new LinearGradient(base_p, base_p, rectWidth + base_p, base_p,
                    new int[]{getResources().getColor(R.color.jianbian_1), getResources().getColor(R.color.jianbian_2), getResources().getColor(R.color.jianbian_3),
                            getResources().getColor(R.color.jianbian_4), getResources().getColor(R.color.jianbian_5)},
                    new float[]{(float) (0.666 * list.get(0).getScoreB() / 100 * 0.75), (float) (list.get(0).getScoreB() / 100 * 0.75), (float) (list.get(1).getScoreB() / 100 * 0.79)
                            , (float) (list.get(2).getScoreB() / 100 * 1.05), 100 * 0.88f}, Shader.TileMode.MIRROR); // 一个材质,打造出一个线性梯度沿著一条线。
            //备份     new float[]{0.3f, 0.45f, 0.71f, 0.78f, 0.88f}, Shader.TileMode.MIRROR); // 一个材质,打造出一个线性梯度沿著一条线。
            p.setShader(mShader);

            canvas.drawRect(base_p, base_p, getWidth() - base_p, rectHeight + base_p, p);
            Paint paint = new Paint();//绘线 绘制文本
            paint.setColor(getResources().getColor(R.color.a333333));
            paint.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
            paint.setStrokeWidth(3);
            Paint paintText = new Paint();//绘制文本分数
            paintText.setColor(Color.RED);
            paintText.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics()));
            paintText.setStrokeWidth(3);
            //    canvas.drawText(0 + "", base_p - 20, rectHeight + 50, paintText);//绘制分数
            if (list.get(0) != null) {
                canvas.drawLine(rectWidth * list.get(0).getScoreB() / 100 + base_p, base_p, rectWidth * list.get(0).getScoreB() / 100 + base_p, rectHeight + base_p, paint);//中区间段
                canvas.drawText(list.get(0).getStatusB(), rectWidth * list.get(0).getScoreB() / 200 + base_p - centerText, (rectHeight) / 2 + base_p + 10, paint);
                //  canvas.drawText((int) list.get(0).getScoreB() + "", rectWidth * list.get(0).getScoreB() / 100 + base_p - centerPosition, rectHeight + 50, paintText);//绘制分数
            }

            if (list.get(1).getStatusB() != null) {
                canvas.drawLine(rectWidth * list.get(1).getScoreB() / 100 + base_p, base_p, rectWidth * list.get(1).getScoreB() / 100 + base_p, rectHeight + base_p, paint);//良区间段
                canvas.drawText(list.get(1).getStatusB(), rectWidth * (list.get(1).getScoreB() + list.get(0).getScoreB()) / 200 + base_p - centerText, (rectHeight) / 2 + base_p + 10, paint);
                //  canvas.drawText((int) list.get(1).getScoreB() + "", rectWidth * list.get(1).getScoreB() / 100 + base_p - centerPosition, rectHeight + 50, paintText);
            }

            if (list.get(2).getStatusB() != null) {
                canvas.drawLine(rectWidth * list.get(2).getScoreB() / 100 + base_p, base_p, rectWidth * list.get(2).getScoreB() / 100 + base_p, rectHeight + base_p, paint);//优区间段
                canvas.drawText(list.get(2).getStatusB(), rectWidth * (list.get(2).getScoreB() + list.get(1).getScoreB()) / 200 + base_p - centerText, rectHeight / 2 + base_p + 10, paint);
                //   canvas.drawText((int) list.get(2).getScoreB() + "", rectWidth * list.get(2).getScoreB() / 100 + base_p - centerPosition, rectHeight + 50, paintText);
            }
            canvas.drawText(list.get(3).getStatusB(), (rectWidth * list.get(2).getScoreB() / 100 + (rectWidth) + 2 * base_p - 10) / 2 - centerText, rectHeight / 2 + base_p + 10, paint);
            //  canvas.drawText(100 + "", rectWidth + base_p - centerPosition, rectHeight + 50, paintText);
            Paint paintRela = new Paint();//绘制真实分数线
            paintRela.setColor(getResources().getColor(R.color.theme_color));
            paintRela.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics()));
            paintRela.setStrokeWidth(5);
            //画出真是分数的线和分数  画线顺序  横线  丿   纳
            canvas.drawLine(rectWidth * relaScore / 100 + base_p, rela_line, rectWidth * relaScore / 100 + base_p, rela_line + 20 + arrowMore, paintRela);
            canvas.drawLine(rectWidth * relaScore / 100 + base_p - 10, rela_line + 20, rectWidth * relaScore / 100 + base_p, rela_line, paintRela);
            canvas.drawLine(rectWidth * relaScore / 100 + base_p + 10, rela_line + 20, rectWidth * relaScore / 100 + base_p, rela_line, paintRela);
            canvas.drawText(relaScore + "", rectWidth * relaScore / 100 + base_p, rectHeight + base_p + arrowMore + 35, paintRela);

            //画cop和scop的实际值和国标
            if (list_cop != null) {
                //绘制实际cop的值
                RunStatusBean.CopBean bean = list_cop.get(0);
                if (bean.getCopRealContent() != null) {
                    relaScore = bean.getCopRealScore();
                    //画出真是分数的线和分数  画线顺序  横线  丿   纳      实际cop
                    canvas.drawLine(rectWidth * relaScore / 100 + base_p, rela_line, rectWidth * relaScore / 100 + base_p, rela_line + 20 + arrowMore, paintRela);
                    canvas.drawLine(rectWidth * relaScore / 100 + base_p - 10, rela_line + 20, rectWidth * relaScore / 100 + base_p, rela_line, paintRela);
                    canvas.drawLine(rectWidth * relaScore / 100 + base_p + 10, rela_line + 20, rectWidth * relaScore / 100 + base_p, rela_line, paintRela);
                    canvas.drawText(bean.getScopRealContent() + "", rectWidth * relaScore / 100 + base_p, rectHeight + base_p + arrowMore + 35, paintRela);
                    //绘制国标cop的值
                    relaScore = bean.getCopStandardScore();
                    canvas.drawLine(rectWidth * relaScore / 100 + base_p, rela_line, rectWidth * relaScore / 100 + base_p, rela_line + 20 + arrowMore, paintRela);
                    canvas.drawLine(rectWidth * relaScore / 100 + base_p - 10, rela_line + 20, rectWidth * relaScore / 100 + base_p, rela_line, paintRela);
                    canvas.drawLine(rectWidth * relaScore / 100 + base_p + 10, rela_line + 20, rectWidth * relaScore / 100 + base_p, rela_line, paintRela);
                    canvas.drawText(bean.getCopStandardContent() + "", rectWidth * relaScore / 100 + base_p, rectHeight + base_p + arrowMore + 35, paintRela);
                }
                if (bean.getScopStandardContent() != null) {
                    relaScore = bean.getScopRealScore();
                    //画出真是分数的线和分数  画线顺序  横线  丿   纳      实际cop
                    canvas.drawLine(rectWidth * relaScore / 100 + base_p, rela_line, rectWidth * relaScore / 100 + base_p, rela_line + 20 + arrowMore, paintRela);
                    canvas.drawLine(rectWidth * relaScore / 100 + base_p - 10, rela_line + 20, rectWidth * relaScore / 100 + base_p, rela_line, paintRela);
                    canvas.drawLine(rectWidth * relaScore / 100 + base_p + 10, rela_line + 20, rectWidth * relaScore / 100 + base_p, rela_line, paintRela);
                    canvas.drawText(bean.getScopRealContent() + "", rectWidth * relaScore / 100 + base_p, rectHeight + base_p + arrowMore + 35, paintRela);
                    //绘制国标cop的值
                    relaScore = bean.getScopStandardScore();
                    canvas.drawLine(rectWidth * relaScore / 100 + base_p, rela_line, rectWidth * relaScore / 100 + base_p, rela_line + 20 + arrowMore, paintRela);
                    canvas.drawLine(rectWidth * relaScore / 100 + base_p - 10, rela_line + 20, rectWidth * relaScore / 100 + base_p, rela_line, paintRela);
                    canvas.drawLine(rectWidth * relaScore / 100 + base_p + 10, rela_line + 20, rectWidth * relaScore / 100 + base_p, rela_line, paintRela);
                    canvas.drawText(bean.getScopStandardContent() + "", rectWidth * relaScore / 100 + base_p, rectHeight + base_p + arrowMore + 35, paintRela);
                }

            }


            //画图例  顺序分别为←竖线、横线、又竖线

            paintRela.setColor(getResources().getColor(R.color.jianbian_4));
            paintRela.setStrokeWidth(3);
            float hlineLength = base_p + paintContent.length() * 28;
            float top = 20;//线的上侧
            float bottom = 40;//线的下侧
            float leftLine = base_p + rectWidth - hlineLength;
            canvas.drawLine(leftLine, top, leftLine, bottom, paintRela);
            canvas.drawLine(leftLine, bottom, leftLine + hlineLength, bottom, paintRela);
            canvas.drawLine(leftLine + hlineLength, top, leftLine + hlineLength, bottom, paintRela);
            paint.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, getResources().getDisplayMetrics()));
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(paintContent, leftLine + hlineLength / 2, bottom - 4, paint);
        }
    }

    /**
     * 设置优良中差
     */
    public void setStatusB(RunStatusBean bean) {
        this.invalidate();
        list = bean.getList();
        relaScore = bean.getRelaScore();
        paintContent = bean.getPaintContent();//图例说明
        list_cop = bean.getList_cop();
    }
}
