package com.easyandroid.widgets.textview.shape.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.easyandroid.widgets.R;
import com.easyandroid.widgets.textview.shape.builder.ShapeDrawableBuilder;
import com.easyandroid.widgets.textview.shape.styleable.ShapeImageViewStyleable;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * package: com.easyandroid.widgets.textview.shape.view.ShapeImageView
 * author: gyc
 * description: 支持直接定义 Shape 背景的 ImageView
 * time: create at 2022/6/29 11:35
 */
public class ShapeImageView extends AppCompatImageView {

  private static final ShapeImageViewStyleable STYLEABLE = new ShapeImageViewStyleable();

  private final ShapeDrawableBuilder mShapeDrawableBuilder;

  public ShapeImageView(Context context) {
    this(context, null);
  }

  public ShapeImageView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ShapeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeImageView);
    mShapeDrawableBuilder = new ShapeDrawableBuilder(this, typedArray, STYLEABLE);
    typedArray.recycle();

    mShapeDrawableBuilder.intoBackground();
  }

  public ShapeDrawableBuilder getShapeDrawableBuilder() {
    return mShapeDrawableBuilder;
  }
}