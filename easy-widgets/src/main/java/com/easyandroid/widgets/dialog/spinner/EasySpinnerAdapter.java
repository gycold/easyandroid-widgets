package com.easyandroid.widgets.dialog.spinner;

import android.content.Context;

import java.util.List;

/**
 * package: com.easyandroid.widgets.dialog.spinner.EasySpinnerAdapter
 * author: gyc
 * description:
 * time: create at 2022/7/6 22:22
 */
public class EasySpinnerAdapter<T> extends EasySpinnerBaseAdapter {

    private final List<T> items;

    EasySpinnerAdapter(
            Context context,
            List<T> items,
            int textColor,
            int backgroundSelector,
            SpinnerTextFormatter spinnerTextFormatter,
            TextAlignment horizontalAlignment
    ) {
        super(context, textColor, backgroundSelector, spinnerTextFormatter, horizontalAlignment);
        this.items = items;
    }

    @Override
    public int getCount() {
//        return items.size() - 1;
        return items.size();
    }

    @Override
    public T getItem(int position) {
//        if (position >= selectedIndex) {
//            return items.get(position + 1);
//        } else {
//            return items.get(position);
//        }
        return items.get(position);
    }

    @Override
    public T getItemInDataset(int position) {
        return items.get(position);
    }
}