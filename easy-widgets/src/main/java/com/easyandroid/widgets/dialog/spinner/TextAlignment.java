package com.easyandroid.widgets.dialog.spinner;

/**
 * package: com.easyandroid.widgets.dialog.spinner.TextAlignment
 * author: gyc
 * description: 列表内容对齐方式
 * time: create at 2022/7/6 22:20
 */
enum TextAlignment {

    START(0),
    END(1),
    CENTER(2);

    private final int id;

    TextAlignment(int id) {
        this.id = id;
    }

    static TextAlignment fromId(int id) {
        for (TextAlignment value : values()) {
            if (value.id == id) return value;
        }
        return CENTER;
    }
}