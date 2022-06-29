package com.easyandroid.widgets.textview.styleable;

/**
 * package: com.easyandroid.widgets.textview.styleable.ITextColorStyleable
 * author: gyc
 * description: 文本颜色 View 属性收集接口
 * time: create at 2022/6/29 10:32
 */
public interface ITextColorStyleable {

    int getTextColorStyleable();

    int getTextPressedColorStyleable();

    default int getTextCheckedColorStyleable() {
        return 0;
    }

    int getTextDisabledColorStyleable();

    int getTextFocusedColorStyleable();

    int getTextSelectedColorStyleable();

    int getTextStartColorStyleable();

    int getTextCenterColorStyleable();

    int getTextEndColorStyleable();

    int getTextGradientOrientationStyleable();

    int getTextStrokeColorStyleable();

    int getTextStrokeSizeStyleable();

}
