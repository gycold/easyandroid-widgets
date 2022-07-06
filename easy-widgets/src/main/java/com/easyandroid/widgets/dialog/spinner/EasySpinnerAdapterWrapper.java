package com.easyandroid.widgets.dialog.spinner;

import android.content.Context;
import android.widget.ListAdapter;

/**
 * package: com.easyandroid.widgets.dialog.spinner.EasySpinnerAdapterWrapper
 * author: gyc
 * description:
 * time: create at 2022/7/6 22:21
 */
public class EasySpinnerAdapterWrapper extends EasySpinnerBaseAdapter {

    private final ListAdapter baseAdapter;

    EasySpinnerAdapterWrapper(
            Context context,
            ListAdapter toWrap,
            int textColor,
            int backgroundSelector,
            SpinnerTextFormatter spinnerTextFormatter,
            TextAlignment horizontalAlignment
    ) {
        super(context, textColor, backgroundSelector, spinnerTextFormatter, horizontalAlignment);
        baseAdapter = toWrap;
    }

    @Override
    public int getCount() {
        return baseAdapter.getCount() - 1;
    }

    @Override
    public Object getItem(int position) {
        return baseAdapter.getItem(position >= selectedIndex ? position + 1 : position);
    }

    @Override
    public Object getItemInDataset(int position) {
        return baseAdapter.getItem(position);
    }
}