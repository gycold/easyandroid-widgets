package com.easyandroid.widgets.textview.shape.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.easyandroid.widgets.R;
import com.easyandroid.widgets.textview.shape.builder.ShapeDrawableBuilder;
import com.easyandroid.widgets.textview.shape.styleable.ShapeConstraintLayoutStyleable;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * package:com.easyandroid.widgets.textview.shape.layout.ShapeConstraintLayout
 * author: gyc
 * description: 支持直接定义 Shape 背景的 ConstraintLayout
 * time: create at 2022/6/29 11:01
 */
public class ShapeConstraintLayout extends ConstraintLayout {

    private static final ShapeConstraintLayoutStyleable STYLEABLE = new ShapeConstraintLayoutStyleable();

    private final ShapeDrawableBuilder mShapeDrawableBuilder;

    public ShapeConstraintLayout(Context context) {
        this(context, null);
    }

    public ShapeConstraintLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeConstraintLayout);
        mShapeDrawableBuilder = new ShapeDrawableBuilder(this, typedArray, STYLEABLE);
        typedArray.recycle();

        mShapeDrawableBuilder.intoBackground();
    }

    public ShapeDrawableBuilder getShapeDrawableBuilder() {
        return mShapeDrawableBuilder;
    }
}