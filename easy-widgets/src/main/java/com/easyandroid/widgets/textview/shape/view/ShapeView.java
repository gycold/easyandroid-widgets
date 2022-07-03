package com.easyandroid.widgets.textview.shape.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.easyandroid.widgets.R;
import com.easyandroid.widgets.textview.shape.builder.ShapeDrawableBuilder;
import com.easyandroid.widgets.textview.shape.styleable.ShapeViewStyleable;

/**
 * package: com.easyandroid.widgets.textview.shape.view.ShapeView
 * author: gyc
 * description: 支持直接定义 Shape 背景的 View
 * time: create at 2022/6/29 11:36
 */
public class ShapeView extends View {

    private static final ShapeViewStyleable STYLEABLE = new ShapeViewStyleable();

    private final ShapeDrawableBuilder mShapeDrawableBuilder;

    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeView);
        mShapeDrawableBuilder = new ShapeDrawableBuilder(this, typedArray, STYLEABLE);
        typedArray.recycle();

        mShapeDrawableBuilder.intoBackground();
    }

    public ShapeDrawableBuilder getShapeDrawableBuilder() {
        return mShapeDrawableBuilder;
    }
}