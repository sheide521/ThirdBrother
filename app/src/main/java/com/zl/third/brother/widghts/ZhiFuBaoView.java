package com.zl.third.brother.widghts;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

/**
 * Created by longzhao on 2018/7/18.
 * 仿支付宝
 */

public class ZhiFuBaoView extends View {

    //根据数据显示的圆弧Paint
    private Paint mArcPaint;
    //内圈圆弧的Paint
    private Paint mArcInPaint;
    //文字描述的paint
    private Paint mTextPaint;
    //圆弧开始的角度
    private float startAngle = 135;
    //圆弧结束的角度
    private float endAngle = 45;
    //圆弧背景的开始和结束间的夹角大小
    private float mAngle = 270;
    //当前进度夹角大小
    private float mIncludedAngle = 0;
    //圆弧的画笔的宽度
    private float mStrokeWith = 10;
    //中心的文字描述
    private String mDes = "";
    //动画效果的数据及最大/小值
    private int mAnimatorValue, mMinValue, mMaxValue;
    //中心点的XY坐标
    private float centerX, centerY;

    public ZhiFuBaoView(Context context) {
        this(context, null);
    }

    public ZhiFuBaoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZhiFuBaoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //init();
    }

    private void initPaint() {
        //圆弧的paint
        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //抗锯齿
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(Color.parseColor("#666666"));
        //设置透明度（数值为0-255）
        mArcPaint.setAlpha(100);
        //设置画笔的画出的形状
        mArcPaint.setStrokeJoin(Paint.Join.ROUND);
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);
        //设置画笔类型
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(dp2px(mStrokeWith));

        //内圈圆弧的paint
        mArcInPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //抗锯齿
        mArcInPaint.setAntiAlias(true);
        mArcInPaint.setColor(Color.parseColor("#666666"));
        //设置透明度（数值为0-255）
        mArcInPaint.setAlpha(100);
        //设置画笔的画出的形状
        mArcInPaint.setStrokeJoin(Paint.Join.ROUND);
        mArcInPaint.setStrokeCap(Paint.Cap.ROUND);
        //设置画笔类型
        mArcInPaint.setStyle(Paint.Style.STROKE);
        mArcInPaint.setStrokeWidth(dp2px(mStrokeWith));


        //中心文字的paint
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.parseColor("#FF4A40"));
        //设置文本的对齐方式
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        //mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.dp_12));
        mTextPaint.setTextSize(dp2px(25));

    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        //初始化paint
        initPaint();
        //绘制弧度
        drawArc(canvas);
        //绘制内圈弧度
        drawInArc(canvas);
        //绘制文本
        drawText(canvas);
    }

    /**
     * 绘制文本
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        Rect mRect = new Rect();
        String mValue = String.valueOf(mAnimatorValue);
        //绘制中心的数值
        mTextPaint.getTextBounds(mValue, 0, mValue.length(), mRect);
        canvas.drawText(String.valueOf(mAnimatorValue), centerX, centerY + mRect.height(), mTextPaint);

        //绘制中心文字描述
        mTextPaint.setColor(Color.parseColor("#999999"));
        mTextPaint.setTextSize(dp2px(12));
        mTextPaint.getTextBounds(mDes, 0, mDes.length(), mRect);
        canvas.drawText(mDes, centerX, centerY + 2 * mRect.height() + dp2px(10), mTextPaint);

        //绘制最小值
        String minValue = String.valueOf(mMinValue);
        String maxValue = String.valueOf(mMaxValue);
        mTextPaint.setTextSize(dp2px(18));
        mTextPaint.getTextBounds(minValue, 0, minValue.length(), mRect);
        canvas.drawText(minValue, (float) (centerX - 0.6 * centerX - dp2px(5)), (float) (centerY + 0.75 * centerY + mRect.height() + dp2px(5)), mTextPaint);
        //绘制最大指
        mTextPaint.getTextBounds(maxValue, 0, maxValue.length(), mRect);
        canvas.drawText(maxValue, (float) (centerX + 0.6 * centerX + dp2px(5)), (float) (centerY + 0.75 * centerY + mRect.height() + dp2px(5)), mTextPaint);
    }

    /**
     * 绘制当前的圆弧
     *
     * @param canvas
     */
    private void drawArc(Canvas canvas) {
        //绘制圆弧背景
        RectF mRectF = new RectF(mStrokeWith + dp2px(5), mStrokeWith + dp2px(5), getWidth() - mStrokeWith - dp2px(5), getHeight() - mStrokeWith);
        canvas.drawArc(mRectF, startAngle, mAngle, false, mArcPaint);
        //绘制当前数值对应的圆弧
        mArcPaint.setColor(Color.parseColor("#FF4A40"));
        //根据当前数据绘制对应的圆弧
        canvas.drawArc(mRectF, startAngle, mIncludedAngle, false, mArcPaint);
    }

    /**
     * 绘制内圈的圆弧
     *
     * @param canvas
     */
    private void drawInArc(Canvas canvas) {
        //绘制圆弧背景
        RectF mRectF = new RectF(mStrokeWith + dp2px(5) + dp2px(20), mStrokeWith + dp2px(5) + dp2px(20), getWidth() - mStrokeWith - dp2px(5) - dp2px(20), getHeight() - mStrokeWith - dp2px(20));
        canvas.drawArc(mRectF, startAngle, mAngle, false, mArcInPaint);
    }

    /**
     * 为绘制弧度及数据设置动画
     *
     * @param startAngle   开始的弧度
     * @param currentAngle 需要绘制的弧度
     * @param currentValue 需要绘制的数据
     * @param time         动画执行的时长
     */
    private void setAnimation(float startAngle, float currentAngle, int currentValue, int time) {
        //绘制当前数据对应的圆弧的动画效果
        ValueAnimator progressAnimator = ValueAnimator.ofFloat(startAngle, currentAngle);
        progressAnimator.setDuration(time);
        progressAnimator.setTarget(mIncludedAngle);
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mIncludedAngle = (float) animation.getAnimatedValue();
                //重新绘制，不然不会出现效果
                postInvalidate();
            }
        });
        //开始执行动画
        progressAnimator.start();

        //中心数据的动画效果
        ValueAnimator valueAnimator = ValueAnimator.ofInt(mAnimatorValue, currentValue);
        valueAnimator.setDuration(2500);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                mAnimatorValue = (int) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();
    }

    /**
     * 设置数据
     *
     * @param minValue     最小值
     * @param maxValue     最大值
     * @param currentValue 当前绘制的值
     * @param des          描述信息
     */
    public void setValues(int minValue, int maxValue, int currentValue, String des) {
        mDes = des;
        mMaxValue = maxValue;
        mMinValue = minValue;
        //完全覆盖
        if (currentValue > maxValue) {
            currentValue = maxValue;
        }
        //计算弧度比重
        float scale = (float) currentValue / maxValue;
        //计算弧度
        float currentAngle = scale * mAngle;
        //开始执行动画
        setAnimation(0, currentAngle, currentValue, 2500);
    }

    public float dp2px(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }
}