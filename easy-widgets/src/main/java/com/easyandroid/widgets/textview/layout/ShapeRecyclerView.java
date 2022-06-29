package com.easyandroid.widgets.textview.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.easyandroid.widgets.R;
import com.easyandroid.widgets.textview.builder.ShapeDrawableBuilder;
import com.easyandroid.widgets.textview.styleable.ShapeRecyclerViewStyleable;

import androidx.recyclerview.widget.RecyclerView;

/**
 * package: com.easyandroid.widgets.textview.layout.ShapeRecyclerView
 * author: gyc
 * description: 支持直接定义 Shape 背景的 RecyclerView
 * time: create at 2022/6/29 11:03
 */
public class ShapeRecyclerView extends RecyclerView {

    private static final ShapeRecyclerViewStyleable STYLEABLE = new ShapeRecyclerViewStyleable();

    private final ShapeDrawableBuilder mShapeDrawableBuilder;

    public ShapeRecyclerView(Context context) {
        this(context, null);
    }

    public ShapeRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeRecyclerView);
        mShapeDrawableBuilder = new ShapeDrawableBuilder(this, typedArray, STYLEABLE);
        typedArray.recycle();

        mShapeDrawableBuilder.intoBackground();
    }

    public ShapeDrawableBuilder getShapeDrawableBuilder() {
        return mShapeDrawableBuilder;
    }
}