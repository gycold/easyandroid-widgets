package com.easyandroid.widgets.textview.shape.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.easyandroid.widgets.R;
import com.easyandroid.widgets.textview.shape.builder.ShapeDrawableBuilder;
import com.easyandroid.widgets.textview.shape.styleable.ShapeFrameLayoutStyleable;

/**
 * package:com.easyandroid.widgets.textview.shape.layout.ShapeFrameLayout
 * author: gyc
 * description: 支持直接定义 Shape 背景的 FrameLayout
 * time: create at 2022/6/29 11:02
 */
public class ShapeFrameLayout extends FrameLayout {

    private static final ShapeFrameLayoutStyleable STYLEABLE = new ShapeFrameLayoutStyleable();

    private final ShapeDrawableBuilder mShapeDrawableBuilder;

    public ShapeFrameLayout(Context context) {
        this(context, null);
    }

    public ShapeFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeFrameLayout);
        mShapeDrawableBuilder = new ShapeDrawableBuilder(this, typedArray, STYLEABLE);
        typedArray.recycle();

        mShapeDrawableBuilder.intoBackground();
    }

    public ShapeDrawableBuilder getShapeDrawableBuilder() {
        return mShapeDrawableBuilder;
    }
}