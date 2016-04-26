package com.gzfgeh.CustomTag;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.gzfgeh.Constants;


public class Tag {

    public int id;
    public String text;
    public int tagTextColor;
    public float tagTextSize;
    public int layoutColor;
    public int layoutColorPress;
    public boolean isDeletable;
    public int deleteIndicatorColor;
    public float deleteIndicatorSize;
    public float radius;
    public String deleteIcon;
    public float layoutBorderSize;
    public int layoutBorderColor;
    public Drawable background;


    public Tag(String text) {
        init(0, text, Constants.DEFAULT_TAG_TEXT_COLOR,
                Constants.DEFAULT_TAG_TEXT_SIZE,
                Constants.DEFAULT_TAG_LAYOUT_COLOR,
                Constants.DEFAULT_TAG_LAYOUT_COLOR_PRESS,
                Constants.DEFAULT_TAG_IS_DELETABLE,
                Constants.DEFAULT_TAG_DELETE_INDICATOR_COLOR,
                Constants.DEFAULT_TAG_DELETE_INDICATOR_SIZE,
                Constants.DEFAULT_TAG_RADIUS,
                Constants.DEFAULT_TAG_DELETE_ICON,
                Constants.DEFAULT_TAG_LAYOUT_BORDER_SIZE,
                Constants.DEFAULT_TAG_LAYOUT_BORDER_COLOR);
    }

    private void init(int id, String text, int tagTextColor, float tagTextSize,
                      int layoutColor, int layoutColorPress, boolean isDeletable,
                      int deleteIndicatorColor,float deleteIndicatorSize, float radius,
                      String deleteIcon, float layoutBorderSize, int layoutBorderColor) {
        this.id = id;
        this.text = text;
        this.tagTextColor = tagTextColor;
        this.tagTextSize = tagTextSize;
        this.layoutColor = layoutColor;
        this.layoutColorPress = layoutColorPress;
        this.isDeletable = isDeletable;
        this.deleteIndicatorColor = deleteIndicatorColor;
        this.deleteIndicatorSize = deleteIndicatorSize;
        this.radius = radius;
        this.deleteIcon = deleteIcon;
        this.layoutBorderSize = layoutBorderSize;
        this.layoutBorderColor = layoutBorderColor;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (!(o instanceof Tag)){
            return false;
        }

        if (!TextUtils.equals(text, ((Tag) o).text) || id != ((Tag) o).id)
            return false;

        return true;
    }
}
