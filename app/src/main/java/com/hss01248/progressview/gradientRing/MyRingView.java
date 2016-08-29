package com.hss01248.progressview.gradientRing;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
public class MyRingView  extends View {

    // ColorInt
    private int defaultColor;




    private int strokeWidth;

    private RotateAnimation animation;

    // 用于渐变
    private Paint paint;
    private int[] colors;
    private final RectF rectF = new RectF();
    private Paint startPaint;

    //private SmoothHandler smoothHandler;

    public MyRingView(Context context) {
        super(context);
        init(context, null);
    }

    public MyRingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyRingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyRingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }


    private void init(final Context context, final AttributeSet attrs) {


        do {
            final int strokeWdithDefaultValue = (int) (18 * getResources().getDisplayMetrics().density + 0.5f);
            strokeWidth = strokeWdithDefaultValue;

            defaultColor = Color.BLACK;
            if (context == null || attrs == null) {
                strokeWidth = strokeWdithDefaultValue;

                defaultColor = Color.BLACK;
                break;
            }

            /*TypedArray typedArray = null;
            try {
                typedArray = context.obtainStyledAttributes(attrs, R.styleable.MagicProgressCircle);

                strokeWidth = (int) typedArray.getDimension(R.styleable.MagicProgressCircle_mpc_stroke_width, strokeWdithDefaultValue);

                defaultColor = typedArray.getColor(R.styleable.MagicProgressCircle_mpc_default_color, Color.LTGRAY);
            } finally {
                if (typedArray != null) {
                    typedArray.recycle();
                }
            }*/


        } while (false);

        paint = new Paint();



        paint.setAntiAlias(true);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(defaultColor);


        startPaint = new Paint();
        startPaint.setColor(Color.BLUE);
        startPaint.setStyle(Paint.Style.FILL);



      //  refreshDelta();





    }




    /**
     * @param color ColorInt
     */
    public void setDefaultColor(final int color) {
        if (this.defaultColor != color) {
            this.defaultColor = color;
            paint.setColor(defaultColor);
            invalidate();
        }
    }

    public int getDefaultColor() {
        return this.defaultColor;
    }

    /**
     * @param width px
     */
    public void setStrokeWidth(final int width) {
        if (this.strokeWidth != width) {
            this.strokeWidth = width;
            // 画描边的描边变化
            paint.setStrokeWidth(width);

            // 会影响measure
            requestLayout();
        }
    }

    public int getStrokeWidth() {
        return this.strokeWidth;
    }

    private int deltaR, deltaB, deltaG;
    private int startR, startB, startG;



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        this.rectF.left = getMeasuredWidth()  - strokeWidth;
        this.rectF.top = getMeasuredHeight()/2 - strokeWidth/2;
        this.rectF.right = getMeasuredWidth()   ;
        this.rectF.bottom = getMeasuredHeight()/2 + strokeWidth/2;

    }

   /* private Paint startPaint;
    private Paint endPaint;*/







    // 目前由于SweepGradient赋值只在构造函数，无法pre allocate & reuse instead
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int restore = canvas.save();

        final int cx = getMeasuredWidth() / 2;
        final int cy = getMeasuredHeight() / 2;
        final int radius = getMeasuredWidth() / 2 - strokeWidth / 2;

        colors = new int[]{Color.WHITE,Color.BLUE};

        //渐变
        final SweepGradient sweepGradient = new SweepGradient(cx, cy, colors, null);
        paint.setShader(sweepGradient);


        canvas.drawCircle(cx, cy, radius, paint);
        canvas.restore();

        //画半弧和半圆
      /*  canvas.save();
        paint.setColor(Color.BLUE);
       // canvas.rotate((int) Math.floor(360.0f * 1) - 1+180, cx, cy);//旋转到最低点
        // canvas.drawArc(rectF, -90f, 180f, true, endPaint);
        canvas.drawArc(rectF, -90f, 180f, true, paint);
        canvas.drawCircle(rectF.centerX(),rectF.centerY(),8f,paint);*/
        canvas.save();

        int ax =  getMeasuredWidth() - strokeWidth / 2;
        int ay= cy;
        canvas.drawOval(rectF,startPaint);


        canvas.restore();


        canvas.restoreToCount(restore);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        animation = new RotateAnimation(0,359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //animation.setRepeatMode(RotateAnimation.INFINITE);
        animation.setDuration(4200);
        //animation.setFillAfter(true);
        animation.setRepeatCount(RotateAnimation.INFINITE);
        LinearInterpolator lin = new LinearInterpolator();
        animation.setInterpolator(lin);
        //ringView.setAnimation(animation);
        //this.startAnimation(animation);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clearAnimation();
    }
}
