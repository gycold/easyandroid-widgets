package com.easyandroid.widgets.textview.shape.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.easyandroid.widgets.R;
import com.easyandroid.widgets.textview.shape.builder.ShapeDrawableBuilder;
import com.easyandroid.widgets.textview.shape.styleable.ShapeLinearLayoutStyleable;

/**
 * package:com.easyandroid.widgets.textview.shape.layout.ShapeLinearLayout
 * author: gyc
 * description: 支持直接定义 Shape 背景的 LinearLayout
 * time: create at 2022/6/29 11:03
 */
public class ShapeLinearLayout extends LinearLayout {

    private static final ShapeLinearLayoutStyleable STYLEABLE = new ShapeLinearLayoutStyleable();

    private final ShapeDrawableBuilder mShapeDrawableBuilder;

    public ShapeLinearLayout(Context context) {
        this(context, null);
    }

    public ShapeLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeLinearLayout);
        mShapeDrawableBuilder = new ShapeDrawableBuilder(this, typedArray, STYLEABLE);
        typedArray.recycle();

        mShapeDrawableBuilder.intoBackground();
    }

    public ShapeDrawableBuilder getShapeDrawableBuilder() {
        return mShapeDrawableBuilder;
    }
}