package com.easyandroid.widgets.textview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.easyandroid.widgets.R;
import com.easyandroid.widgets.textview.builder.ButtonDrawableBuilder;
import com.easyandroid.widgets.textview.builder.ShapeDrawableBuilder;
import com.easyandroid.widgets.textview.builder.TextColorBuilder;
import com.easyandroid.widgets.textview.styleable.ShapeCheckBoxStyleable;

import androidx.appcompat.widget.AppCompatCheckBox;

/**
 * package: com.easyandroid.widgets.textview.view.ShapeCheckBox
 * author: gyc
 * description: 支持直接定义 Shape 背景的 CheckBox
 * time: create at 2022/6/29 11:34
 */
public class ShapeCheckBox extends AppCompatCheckBox {

    private static final ShapeCheckBoxStyleable STYLEABLE = new ShapeCheckBoxStyleable();

    private final ShapeDrawableBuilder mShapeDrawableBuilder;
    private final TextColorBuilder mTextColorBuilder;
    private final ButtonDrawableBuilder mButtonDrawableBuilder;

    public ShapeCheckBox(Context context) {
        this(context, null);
    }

    public ShapeCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, androidx.appcompat.R.attr.checkboxStyle);
    }

    public ShapeCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeCheckBox);
        mShapeDrawableBuilder = new ShapeDrawableBuilder(this, typedArray, STYLEABLE);
        mTextColorBuilder = new TextColorBuilder(this, typedArray, STYLEABLE);
        mButtonDrawableBuilder = new ButtonDrawableBuilder(this, typedArray, STYLEABLE);
        typedArray.recycle();

        mShapeDrawableBuilder.intoBackground();

        if (mTextColorBuilder.isTextGradientColors() || mTextColorBuilder.isTextStrokeColor()) {
            setText(getText());
        } else {
            mTextColorBuilder.intoTextColor();
        }

        mButtonDrawableBuilder.intoButtonDrawable();
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
        if (mTextColorBuilder == null) {
            return;
        }
        mTextColorBuilder.setTextColor(color);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (mTextColorBuilder != null &&
                (mTextColorBuilder.isTextGradientColors() || mTextColorBuilder.isTextStrokeColor())) {
            super.setText(mTextColorBuilder.buildTextSpannable(text), type);
        } else {
            super.setText(text, type);
        }
    }

    @Override
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        if (mButtonDrawableBuilder == null) {
            return;
        }
        mButtonDrawableBuilder.setButtonDrawable(drawable);
    }

    public ShapeDrawableBuilder getShapeDrawableBuilder() {
        return mShapeDrawableBuilder;
    }

    public TextColorBuilder getTextColorBuilder() {
        return mTextColorBuilder;
    }

    public ButtonDrawableBuilder getButtonDrawableBuilder() {
        return mButtonDrawableBuilder;
    }
}