package com.gzfgeh.CustomRedView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.BounceInterpolator;

import com.gzfgeh.Utils;

/**
 * Description:
 * Created by guzhenfu on 2016/6/14 10:58.
 */
public class RedHotView extends View {
    private Context context;
    private int originRadius;   //初始半径
    private int originWidth;
    private int originHeight;

    private int maxMoveLength;
    private boolean isArrivedMaxMoved;

    private int curRadius;
    private int touchedPointRadius;
    private Point startPoint = new Point();
    private Point endPoint = new Point();

    private Paint paint;
    private TextPaint textPaint;
    private Paint.FontMetrics textFontMetrics;
    private int[] location;
    private boolean isTouched;
    private Triangle triangle = new Triangle();
    private String text = "";
    private int patientColor = Color.RED;
    private boolean isFirst = true;
    float downX = Float.MAX_VALUE;
    float downY = Float.MAX_VALUE;
    private Path path = new Path();
    private OnHotDismissListener onHotDismissListener;
    private int level = 0;
    private int num;

    public void setOnHotDismissListener(OnHotDismissListener onHotDismissListener) {
        this.onHotDismissListener = onHotDismissListener;
    }

    public RedHotView(Context context) {
        this(context, null);
    }

    public RedHotView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RedHotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        setBackgroundColor(Color.TRANSPARENT);
        paint = new Paint();
        paint.setColor(patientColor);
        paint.setAntiAlias(true);

        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(Utils.dipToPx(context, 12));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textFontMetrics = textPaint.getFontMetrics();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (isFirst && w>0 && h>0){
            isFirst = false;
            originWidth = w;
            originHeight = h;
            originRadius = Math.min(originWidth, originHeight) / 2;
            curRadius = originRadius;
            touchedPointRadius = originRadius;

            maxMoveLength = Utils.getHeightInPx(context) / 6;
            refreshStartPoint();
        }
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
        refreshStartPoint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
        int startCircleX, startCircleY;
        if (isTouched){
            startCircleX = startPoint.x + curRadius;
            startCircleY = startPoint.y - curRadius;
            canvas.drawCircle(startCircleX, startCircleY, curRadius, paint);
            int endCircleX = endPoint.x;
            int endCircleY = endPoint.y;
            canvas.drawCircle(endCircleX, endCircleY, originRadius, paint);
            if (!isArrivedMaxMoved){
                path.reset();
                double sin = triangle.deltaY / triangle.hypotenuse;
                double cos = triangle.deltaX / triangle.hypotenuse;

                path.moveTo((float) (startCircleX - curRadius * sin), (float)(startCircleY - curRadius * cos));
                path.lineTo((float) (startCircleX + curRadius * sin), (float)(startCircleY + curRadius * cos));
                path.quadTo((startCircleX + endCircleX) / 2, (startCircleY + endCircleY) / 2,
                        (float) (endCircleX + originRadius * sin), (float)(endCircleY + originRadius * cos));
                path.lineTo((float) (endCircleX - originRadius * sin), (float)(endCircleY - originRadius * cos));
                path.quadTo((startCircleX + endCircleX) / 2, (startCircleY + endCircleY) / 2,
                        (float) (startCircleX - curRadius * sin), (float)(startCircleY - curRadius * cos));
                canvas.drawPath(path, paint);
                float textH = -textFontMetrics.ascent - textFontMetrics.descent;
                canvas.drawText(text, endCircleX, endCircleY + textH/2, textPaint);
            }
        }else{
            if (curRadius > 0){
                startCircleX = curRadius;
                startCircleY = originHeight - curRadius;
                try {
                    num = Integer.valueOf(text);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    text = "";
                }
                if (text == null || "".equals(text)){
                    level = 0;
                    canvas.drawCircle(startCircleX, startCircleY, curRadius/2, paint);
                }else{
                    if (num < 10){
                        level = 1;
                        canvas.drawCircle(startCircleX, startCircleY, curRadius, paint);
                    }else if (num < 100 && num  >= 10){
                        level = 2;
                        canvas.drawCircle(startCircleX, startCircleY, curRadius, paint);
                    }else{
                        level = 3;
                        canvas.drawCircle(startCircleX, startCircleY, curRadius, paint);
                    }
                }
                if (curRadius == originRadius){
                    float textH = -textFontMetrics.ascent - textFontMetrics.descent;
                    if (level == 3)
                        canvas.drawText("99+", startCircleX, startCircleY + textH/2, textPaint);
                    else
                        canvas.drawText(text, startCircleX, startCircleY + textH/2, textPaint);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                isTouched = true;
                endPoint.x = (int) downX;
                endPoint.y = (int) downY;
                changeViewHeight(this, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                postInvalidate();
                downX = event.getX() + location[0];
                downY = event.getY() + location[1];
                break;

            case MotionEvent.ACTION_MOVE:
                triangle.deltaX = event.getX() - downX;
                triangle.deltaY = -1 * (event.getY() - downY);
                double distance = Math.sqrt(triangle.deltaX * triangle.deltaX + triangle.deltaY * triangle.deltaY);
                triangle.hypotenuse = distance;
                refreshCurRadiusByMoveDistance((int) distance);
                endPoint.x = (int) event.getX();
                endPoint.y = (int) event.getY();
                postInvalidate();
                break;

            case MotionEvent.ACTION_UP:
                isTouched = false;
                changeViewHeight(this, originWidth, originHeight);
                startRollBackAnimation(500);
                downX = Float.MAX_VALUE;
                downY = Float.MAX_VALUE;
                break;
        }
        return true;
    }

    private void refreshStartPoint(){
        location = new int[2];
        getLocationInWindow(location);
        location[1] = location[1] - ((Activity)context).getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        startPoint.set(location[0], location[1]);
    }

    private void changeViewHeight(View view, int width, int height){
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = width;
        lp.height = height;
        view.setLayoutParams(lp);
    }

    private void refreshCurRadiusByMoveDistance(int distance){
        if (distance > maxMoveLength){
            isArrivedMaxMoved = true;
            curRadius = 0;
        }else{
            isArrivedMaxMoved = false;
            float calcRadius = (1 - 1f * distance/maxMoveLength) * originRadius;
            float maxRadius = Utils.dipToPx(context, 2);
            curRadius = (int) Math.max(calcRadius, maxRadius);
        }
    }

    private void resetAfterDismiss(){
        setVisibility(GONE);
        text = "";
        isArrivedMaxMoved = false;
        curRadius = originRadius;
        postInvalidate();
    }

    private void startRollBackAnimation(long duration){
        ValueAnimator rollBackAnim = ValueAnimator.ofFloat(curRadius, originRadius);
        rollBackAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                curRadius = (int) value;
                postInvalidate();
            }
        });
        rollBackAnim.setInterpolator(new BounceInterpolator());
        rollBackAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                RedHotView.this.clearAnimation();
            }
        });
        rollBackAnim.setDuration(duration);
        rollBackAnim.start();
    }

    public interface OnHotDismissListener{
        void onDismiss();
    }

    class Triangle {
        double deltaX;
        double deltaY;
        double hypotenuse;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
        setVisibility(VISIBLE);
        postInvalidate();
    }
}
