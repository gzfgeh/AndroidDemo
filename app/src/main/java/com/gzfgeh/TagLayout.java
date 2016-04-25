package com.gzfgeh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gzfgeh.ExplosionField.ExplosionField;

import java.util.ArrayList;
import java.util.List;

public class TagLayout extends RelativeLayout {

    private ExplosionField mExplosionField;
    /**
     * tag list
     */
    private List<Tag> mTags = new ArrayList<>();

    /**
     * System Service
     */
    private LayoutInflater mInflater;
    private ViewTreeObserver mViewTreeObserber;

    /**
     * listener
     */
    private OnTagClickListener mClickListener;

    /**
     * view size param
     */
    private int mWidth;

    /**
     * layout initialize flag
     */
    private boolean mInitialized = false;

    private Tag tagAdd, tagRemove;
    private float[] endLocation;

    /**
     * custom layout param
     */
    int lineMargin;
    int tagMargin;
    int textPaddingLeft;
    int textPaddingRight;
    int textPaddingTop;
    int texPaddingBottom;
    Context context;


    /**
     * constructor
     *
     * @param ctx
     */
    public TagLayout(Context ctx) {
        super(ctx, null);
        initialize(ctx, null, 0);
    }

    /**
     * constructor
     *
     * @param ctx
     * @param attrs
     */
    public TagLayout(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        initialize(ctx, attrs, 0);
    }

    /**
     * constructor
     *
     * @param ctx
     * @param attrs
     * @param defStyle
     */
    public TagLayout(Context ctx, AttributeSet attrs, int defStyle) {
        super(ctx, attrs, defStyle);
        initialize(ctx, attrs, defStyle);
    }

    /**
     * initalize instance
     *
     * @param ctx
     * @param attrs
     * @param defStyle
     */
    private void initialize(Context ctx, AttributeSet attrs, int defStyle) {
        this.context = ctx;
        mExplosionField = ExplosionField.attach2Window((Activity)ctx);
        mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewTreeObserber = getViewTreeObserver();
        mViewTreeObserber.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!mInitialized) {
                    mInitialized = true;
                    drawTags();
                    LogUtils.i("new line -- mInitialized----" + mInitialized);
                }
            }
        });

        // get AttributeSet
        TypedArray typeArray = ctx.obtainStyledAttributes(attrs, R.styleable.TagLayout, defStyle, defStyle);
        this.lineMargin = (int) typeArray.getDimension(R.styleable.TagLayout_lineMargin, Utils.dipToPx(this.getContext(), Constants.DEFAULT_LINE_MARGIN));
        this.tagMargin = (int) typeArray.getDimension(R.styleable.TagLayout_tagMargin, Utils.dipToPx(this.getContext(), Constants.DEFAULT_TAG_MARGIN));
        this.textPaddingLeft = (int) typeArray.getDimension(R.styleable.TagLayout_textPaddingLeft, Utils.dipToPx(this.getContext(), Constants.DEFAULT_TAG_TEXT_PADDING_LEFT));
        this.textPaddingRight = (int) typeArray.getDimension(R.styleable.TagLayout_textPaddingRight, Utils.dipToPx(this.getContext(), Constants.DEFAULT_TAG_TEXT_PADDING_RIGHT));
        this.textPaddingTop = (int) typeArray.getDimension(R.styleable.TagLayout_textPaddingTop, Utils.dipToPx(this.getContext(), Constants.DEFAULT_TAG_TEXT_PADDING_TOP));
        this.texPaddingBottom = (int) typeArray.getDimension(R.styleable.TagLayout_textPaddingBottom, Utils.dipToPx(this.getContext(), Constants.DEFAULT_TAG_TEXT_PADDING_BOTTOM));
        typeArray.recycle();
    }

    /**
     * onSizeChanged
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        if (width <= 0)
            return;
        mWidth = getMeasuredWidth();
        LogUtils.i("new line -- mWidth----" + mWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTags();
    }


    /**
     * tag draw
     */
    private void drawTags() {

        if (!mInitialized) {
            return;
        }

        // clear all tag
        removeAllViews();

        // layout padding left & layout padding right
        float total = getPaddingLeft() + getPaddingRight();

        int listIndex = 1;// List Index
        int indexBottom = 1;// The Tag to add below
        int indexHeader = 1;// The header tag of this line
        int lineNum = 0;
        Tag tagPre = null;
        for (Tag item : mTags) {
            final int position = listIndex - 1;
            final Tag tag = item;

            // inflate tag layout
            View tagLayout = mInflater.inflate(R.layout.tagview_item, null);
            tagLayout.setId(listIndex);

            // tag text
            TextView tagView = (TextView) tagLayout.findViewById(R.id.tv_tag_item_contain);
            tagView.setText(tag.text);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tagView.getLayoutParams();
            params.setMargins(textPaddingLeft, textPaddingTop, textPaddingRight, texPaddingBottom);
            tagView.setLayoutParams(params);
            tagView.setTextColor(tag.tagTextColor);
            tagView.setTextSize(TypedValue.COMPLEX_UNIT_SP, tag.tagTextSize);
            tagLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null) {
                        //mExplosionField.explode(v);
                        deleteAnimator(v);
                        mClickListener.onTagClick(tag, position);
                    }
                }
            });

            // calculate　of tag layout width
//            float tagWidth = tagView.getPaint().measureText(tag.text) + textPaddingLeft + textPaddingRight + Utils.getWidthInPx(context)/16;
            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            tagLayout.measure(w, h);
            int tagWidth = tagLayout.getMeasuredWidth();
            int tagHeight = tagLayout.getMeasuredHeight();
            LogUtils.i("new line -- width----" + tagWidth);

            // tagView padding (left & right)

            LayoutParams tagParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            //add margin of each line
            tagParams.bottomMargin = lineMargin;

            if (mWidth <= total + tagWidth + Utils.dipToPx(this.getContext(), Constants.LAYOUT_WIDTH_OFFSET)) {
                //need to add in new line
                tagParams.addRule(RelativeLayout.BELOW, indexBottom);
                // initialize total param (layout padding left & layout padding right)
                total = getPaddingLeft() + getPaddingRight();
                indexBottom = listIndex;
                indexHeader = listIndex;
                lineNum++;
            } else {
                //no need to new line
                tagParams.addRule(RelativeLayout.ALIGN_TOP, indexHeader);
                //not header of the line
                if (listIndex != indexHeader) {
                    tagParams.addRule(RelativeLayout.RIGHT_OF, listIndex - 1);
                    tagParams.leftMargin = tagMargin;
                    total += tagMargin;
                    if (tagPre != null && tagPre.tagTextSize < tag.tagTextSize) {
                        indexBottom = listIndex;
                    }
                }
            }
            if (tag == tagAdd){
                endLocation = new float[2];
                endLocation[0] = total;
                endLocation[1] = tagLayout.getTop() + lineNum * (tagHeight + lineMargin);
                moveAnimator(tagLayout, tagParams, endLocation);
            }

//            if (tag == tagRemove){
//                deleteAnimator(tagLayout);
//            }

            total += tagWidth;
            addView(tagLayout, tagParams);
            tagPre = tag;
            listIndex++;
            LogUtils.i("new line -- mWidth " + mWidth + "--other--" + total + tagWidth + Utils.dipToPx(this.getContext(), Constants.LAYOUT_WIDTH_OFFSET));
            LogUtils.i("new line -- getWidth " + tagLayout.getMeasuredWidth());
        }

    }

    private void deleteAnimator(View v){
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(v, "scaleX", 1f, 0f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(v, "scaleY", 1f, 0f);
        AnimatorSet set = new AnimatorSet();
        set.play(animator1).with(animator2);
        set.play(animator2).with(animator3);
        set.setDuration(1000);
        set.start();
    }

    private void moveAnimator(final View v,final ViewGroup.LayoutParams params, float endLocation[]) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(v, "x", 0, endLocation[0]);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(v, "y", 0, endLocation[1]);
        AnimatorSet set = new AnimatorSet();
        set.play(animator1).with(animator2);
        set.setDuration(500);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.start();

        animator2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //TagLayout.this.addView(v, params);
            }
        });
    }

    private Drawable getSelector(Tag tag) {
        if (tag.background != null) return tag.background;
        StateListDrawable states = new StateListDrawable();
        GradientDrawable gdNormal = new GradientDrawable();
        gdNormal.setColor(tag.layoutColor);
        gdNormal.setCornerRadius(tag.radius);
        if (tag.layoutBorderSize > 0) {
            gdNormal.setStroke(Utils.dipToPx(getContext(), tag.layoutBorderSize), tag.layoutBorderColor);
        }
        GradientDrawable gdPress = new GradientDrawable();
        gdPress.setColor(tag.layoutColorPress);
        gdPress.setCornerRadius(tag.radius);
        states.addState(new int[]{android.R.attr.state_pressed}, gdPress);
        //must add state_pressed first，or state_pressed will not take effect
        states.addState(new int[]{}, gdNormal);
        return states;
    }


    //public methods
    //----------------- separator  -----------------//

    /**
     * @param tag
     */
    public void addTag(Tag tag) {
        mTags.add(tag);
        tagAdd = tag;
        tagRemove = null;
        drawTags();
    }

    public void addTag(String s){
        Tag tag = new Tag(s);
        addTag(tag);
    }

    public void setTags(List<String> list){
        List<Tag> l = new ArrayList<>();
        for (String s : list){
            Tag tag = new Tag(s);
            l.add(tag);
        }
        addTags(l);
    }

    public void addTags(List<Tag> tags) {
        if (tags == null) return;
        mTags = new ArrayList<>();
        if (tags.isEmpty())
            drawTags();
        for (Tag item : tags) {
            addTag(item);
        }
    }


    public void addTags(String[] tags) {
        if (tags == null) return;
        for (String item : tags) {
            Tag tag = new Tag(item);
            addTag(tag);
        }
    }
	/*public void addTags(ArrayList<String> tags){
		if (tags==null)return;
		for(String item:tags){
			Tag tag = new Tag(item);
			addTag(tag);
		}
	}*/

    /**
     * get tag list
     *
     * @return mTags TagObject List
     */
    public List<Tag> getTagss() {
        return mTags;
    }

    public List<String> getTags(){
        List<String> list = new ArrayList<>();
        for (Tag tag : getTagss()){
            list.add(tag.text);
        }
        return list;
    }

    /**
     * remove tag
     *
     * @param position
     */
    public void remove(int position) {
        if (position < mTags.size()) {
            tagAdd = null;
            tagRemove = mTags.get(position);
            mTags.remove(position);
            drawTags();
        }
    }

    /**
     * remove all views
     */
    public void removeAll() {
        removeAllViews();
    }

    public void removeAllTags(){
        removeAll();
    }

    public int getLineMargin() {
        return lineMargin;
    }

    public void setLineMargin(float lineMargin) {
        this.lineMargin = Utils.dipToPx(getContext(), lineMargin);
    }

    public int getTagMargin() {
        return tagMargin;
    }

    public void setTagMargin(float tagMargin) {
        this.tagMargin = Utils.dipToPx(getContext(), tagMargin);
    }

    public int getTextPaddingLeft() {
        return textPaddingLeft;
    }

    public void setTextPaddingLeft(float textPaddingLeft) {
        this.textPaddingLeft = Utils.dipToPx(getContext(), textPaddingLeft);
    }

    public int getTextPaddingRight() {
        return textPaddingRight;
    }

    public void setTextPaddingRight(float textPaddingRight) {
        this.textPaddingRight = Utils.dipToPx(getContext(), textPaddingRight);
    }

    public int getTextPaddingTop() {
        return textPaddingTop;
    }

    public void setTextPaddingTop(float textPaddingTop) {
        this.textPaddingTop = Utils.dipToPx(getContext(), textPaddingTop);
    }

    public int getTexPaddingBottom() {
        return texPaddingBottom;
    }

    public void setTexPaddingBottom(float texPaddingBottom) {
        this.texPaddingBottom = Utils.dipToPx(getContext(), texPaddingBottom);
    }

    /**
     * setter for OnTagSelectListener
     *
     * @param clickListener
     */
    public void setOnTagClickListener(OnTagClickListener clickListener) {
        mClickListener = clickListener;
    }

    public interface OnTagClickListener {
        void onTagClick(Tag tag, int position);
    }

}
