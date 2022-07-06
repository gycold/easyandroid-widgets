package com.easyandroid.widgets.dialog.spinner;

import android.text.Spannable;
import android.text.SpannableString;

/**
 * package: com.easyandroid.widgets.dialog.spinner.SimpleSpinnerTextFormatter
 * author: gyc
 * description:
 * time: create at 2022/7/6 22:21
 */
public class SimpleSpinnerTextFormatter implements SpinnerTextFormatter {

  @Override
  public Spannable format(Object item) {
    return new SpannableString(item.toString());
  }
}