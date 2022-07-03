package com.easyandroid.widgets.textview.shape.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.easyandroid.widgets.R;
import com.easyandroid.widgets.textview.shape.builder.ShapeDrawableBuilder;
import com.easyandroid.widgets.textview.shape.builder.TextColorBuilder;
import com.easyandroid.widgets.textview.shape.styleable.ShapeEditTextStyleable;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * package: com.easyandroid.widgets.textview.shape.view.ShapeEditText
 * author: gyc
 * description: 支持直接定义 Shape 背景的 EditText
 * time: create at 2022/6/29 11:35
 */
public class ShapeEditText extends AppCompatEditText {

    private static final ShapeEditTextStyleable STYLEABLE = new ShapeEditTextStyleable();

    private final ShapeDrawableBuilder mShapeDrawableBuilder;
    private final TextColorBuilder mTextColorBuilder;

    public ShapeEditText(Context context) {
        this(context, null);
    }

    public ShapeEditText(Context context, AttributeSet attrs) {
        this(context, attrs, androidx.appcompat.R.attr.editTextStyle);
    }

    public ShapeEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeEditText);
        mShapeDrawableBuilder = new ShapeDrawableBuilder(this, typedArray, STYLEABLE);
        mTextColorBuilder = new TextColorBuilder(this, typedArray, STYLEABLE);
        typedArray.recycle();

        mShapeDrawableBuilder.intoBackground();

        if (mTextColorBuilder.isTextGradientColors() || mTextColorBuilder.isTextStrokeColor()) {
            setText(getText());
        } else {
            mTextColorBuilder.intoTextColor();
        }
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

    public ShapeDrawableBuilder getShapeDrawableBuilder() {
        return mShapeDrawableBuilder;
    }

    public TextColorBuilder getTextColorBuilder() {
        return mTextColorBuilder;
    }
}