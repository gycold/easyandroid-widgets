package com.easyandroid.widgets.dialog.spinner;

import android.view.View;

/**
 * package: com.easyandroid.widgets.dialog.spinner.OnSpinnerItemSelectedListener
 * author: gyc
 * description: 选择监听
 * time: create at 2022/7/6 22:10
 */
public interface OnSpinnerItemSelectedListener {
  void onItemSelected(EasySpinner parent, View view, int position, long id);
}