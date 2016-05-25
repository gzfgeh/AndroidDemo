package com.gzfgeh.CustomTextView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.gzfgeh.R;
import com.gzfgeh.Utils;

/**
 * Description:
 * Created by guzhenfu on 2016/4/26 15:32.
 */
public class MyTextView extends TextView {
    private String content;
    private int width;
    private Paint paint;
    private int textHeight;
    private int yPadding;
    int count;
    //记录每个字的二维数组
    int[][] position;

    private int color;
    private int size;
    private String s;
    private boolean mInitialized = false;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTextView, defStyle, 0);
        int n = array.getIndexCount();
        for (int i=0; i<n; i++){
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.CustomTextView_textColor:
                    color = array.getColor(attr, Color.BLUE);
                    break;

                case R.styleable.CustomTextView_textSize:
                    size = array.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;

                case R.styleable.CustomTextView_text:
                    s = array.getString(attr);
                    break;
            }
        }
        array.recycle();
        paint = new Paint();
        paint.setColor(color);
        paint.setTypeface(Typeface.DEFAULT);
        paint.setTextSize(size);
        Paint.FontMetrics fm = paint.getFontMetrics();// 得到系统默认字体属性
        textHeight = (int) (Math.ceil(fm.descent - fm.ascent - size/4));// 获得字体高度
        yPadding = Utils.dipToPx(this.getContext(), 10f);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                if (!mInitialized) {
                    mInitialized = true;
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    width = getMeasuredWidth();
                    setText(s);
                }
            }
        });
    }

    public void setText(String str) {
        if (str == null)
            str = "";
        if (!mInitialized) {
            ViewGroup.LayoutParams params = getLayoutParams();
            width = params.width;
            mInitialized = true;
        }
        setPosition(str);
        //重新画控件
        this.invalidate();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!TextUtils.isEmpty(content)) {
            for (int i = 0; i < count; i++) {
                canvas.drawText(String.valueOf(content.charAt(i)), position[i][0], position[i][1], paint);
            }
        }
    }

    public void setPosition(String content){
        this.content = content;
        count = content.length();
        int x = 0;
        char ch;
        position = new int[count][2];
        float textWith = 0f;
        for (int i=0; i<count; i++){
            ch = content.charAt(i);
            String str = String.valueOf(ch);
            Rect rect = new Rect();
            paint.getTextBounds(str, 0, 1, rect);
            int strwidth = rect.width();
            textWith = Math.max(strwidth, textWith);
        }

        for (int i=0; i<count; i++){
            if (i != 0){
                x += (width - count * textWith)/(count - 1);
            }
            position[i][0] = x;
            position[i][1] = textHeight;
            x += textWith;
            this.setHeight(textHeight + yPadding);
        }
    }
}
