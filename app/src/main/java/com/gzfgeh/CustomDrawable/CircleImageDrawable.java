package com.gzfgeh.CustomDrawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import com.gzfgeh.R;

/**
 * Description:
 * Created by guzhenfu on 2016/5/19 11:27.
 */
public class CircleImageDrawable extends Drawable {
    private Bitmap bitmap;
    private Paint paint;
    private int width;
    private Context context;
    private BitmapShader bitmapShader;

    public CircleImageDrawable(Context context, Bitmap bitmap) {
        this.bitmap = bitmap;
        this.context = context;
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);
        width = Math.min(bitmap.getWidth(), bitmap.getHeight());
    }

    @Override
    public void draw(Canvas canvas) {
//        paint.setColor(context.getResources().getColor(R.color.colorAccent));
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(10f);
//        paint.setShader(null);
//        canvas.drawCircle(width / 2, width / 2, width / 2 - 20, paint);

        paint.setShader(bitmapShader);
        canvas.drawCircle(width/2, width/2, width/2 - 20, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return width;
    }

    @Override
    public int getIntrinsicHeight() {
        return width;
    }
}
