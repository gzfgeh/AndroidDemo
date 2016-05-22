package com.gzfgeh.CustomDrawable;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * Description:
 * Created by guzhenfu on 16/5/22.
 * Email:  gzfgeh@sina.com
 * Blog:   http://blog.csdn.net/u011370933
 * Github: https://github.com/gzfgeh
 */
public class PopupList {
    private static PopupWindow popupWindow = null;

    public static final int defaultBotom = -100;

    public static PopupWindow show(Activity activity, View attachOnView, View popView, final int popShowHeight, final int popShowWidth) {
        if (popView != null && popView.getParent() != null) {
            ((ViewGroup) popView.getParent()).removeAllViews();
        }

        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);

        int location[] = new int[2];
        int x, y;
        int popHeight = 0, popWidth = 0;

        attachOnView.getLocationOnScreen(location);
        x = location[0] - (popShowWidth - attachOnView.getWidth())/2;
        y = location[1];

        int h = attachOnView.getHeight();
        int screenHeight = activity.getResources().getDisplayMetrics().heightPixels;

        if (popShowHeight == defaultBotom) {
            popHeight = screenHeight / 6;
            popHeight = Math.abs(screenHeight - (h + y)) - popHeight;
        } else if (popHeight == ViewGroup.LayoutParams.WRAP_CONTENT) {
            popHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else {
            popHeight = popShowHeight;
        }

        if (popShowWidth == ViewGroup.LayoutParams.WRAP_CONTENT) {
            popWidth = attachOnView.getWidth();
        } else {
            popWidth = popShowWidth;
        }

        popupWindow = new PopupWindow(popView, popWidth, popHeight, true);

        //这行代码时用来让PopupWindow点击区域之外消失的，这个应该是PopupWindow的一个bug。
        //但是利用这个bug可以做到点击区域外消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        //popupWindow.setAnimationStyle(R.style.PopupAnimationDown);
        popupWindow.showAtLocation(attachOnView, Gravity.NO_GRAVITY, x, h + y);
        popupWindow.update();
        return popupWindow;
    }

    public static void hide(){
        if (popupWindow != null)
            popupWindow.dismiss();
    }


}
