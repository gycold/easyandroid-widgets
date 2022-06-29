package com.easyandroid.widgets.textview.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.easyandroid.widgets.R;
import com.easyandroid.widgets.textview.builder.ShapeDrawableBuilder;
import com.easyandroid.widgets.textview.styleable.ShapeRelativeLayoutStyleable;

/**
 * package: com.easyandroid.widgets.textview.layout.ShapeRelativeLayout
 * author: gyc
 * description: 支持直接定义 Shape 背景的 RelativeLayout
 * time: create at 2022/6/29 11:04
 */
public class ShapeRelativeLayout extends RelativeLayout {

    private static final ShapeRelativeLayoutStyleable STYLEABLE = new ShapeRelativeLayoutStyleable();

    private final ShapeDrawableBuilder mShapeDrawableBuilder;

    public ShapeRelativeLayout(Context context) {
        this(context, null);
    }

    public ShapeRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeRelativeLayout);
        mShapeDrawableBuilder = new ShapeDrawableBuilder(this, typedArray, STYLEABLE);
        typedArray.recycle();

        mShapeDrawableBuilder.intoBackground();
    }

    public ShapeDrawableBuilder getShapeDrawableBuilder() {
        return mShapeDrawableBuilder;
    }
}