package com.easyandroid.widgets.dialog.spinner;

import android.text.Spannable;

/**
 * package: com.easyandroid.widgets.dialog.spinner.SpinnerTextFormatter
 * author: gyc
 * description: 文本格式
 * time: create at 2022/7/6 22:11
 */
public interface SpinnerTextFormatter<T> {
  Spannable format(T item);
}