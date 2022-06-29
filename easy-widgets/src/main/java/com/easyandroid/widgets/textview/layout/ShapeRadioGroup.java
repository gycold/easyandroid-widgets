package com.easyandroid.widgets.textview.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import com.easyandroid.widgets.R;
import com.easyandroid.widgets.textview.builder.ShapeDrawableBuilder;
import com.easyandroid.widgets.textview.styleable.ShapeRadioGroupStyleable;

/**
 * package: com.easyandroid.widgets.textview.layout.ShapeRadioGroup
 * author: gyc
 * description: 支持直接定义 Shape 背景的 RadioGroup
 * time: create at 2022/6/29 11:03
 */
public class ShapeRadioGroup extends RadioGroup {

    private static final ShapeRadioGroupStyleable STYLEABLE = new ShapeRadioGroupStyleable();

    private final ShapeDrawableBuilder mShapeDrawableBuilder;

    public ShapeRadioGroup(Context context) {
        this(context, null);
    }

    public ShapeRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeRadioGroup);
        mShapeDrawableBuilder = new ShapeDrawableBuilder(this, typedArray, STYLEABLE);
        typedArray.recycle();

        mShapeDrawableBuilder.intoBackground();
    }

    public ShapeDrawableBuilder getShapeDrawableBuilder() {
        return mShapeDrawableBuilder;
    }
}