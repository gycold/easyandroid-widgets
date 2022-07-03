package com.easyandroid.widgets.dialog.dialog;

import android.content.Context;
import android.view.View;

/**
 * package: com.easyandroid.widgets.dialog.dialog.IDialog
 * author: gyc
 * description: 定义弹框统一行为
 * time: create at 2022/7/3 22:23
 */
public interface IDialog {

    /**
     * 弹窗消失
     */
    void dismiss();

    /**
     * 构造dialog里的View
     */
    interface OnBuildListener {
        /**
         * @param dialog    IDialog
         * @param parent    Dialog整体View
         * @param layoutRes Dialog的布局 如果没有传入 默认是0
         */
        void onBuildChildView(IDialog dialog, View parent, int layoutRes);
    }

    /***
     * 点击事件
     */
    interface OnClickListener {
        void onClick(IDialog dialog);
    }

    /**
     * Dialog消失回调
     */
    interface OnDismissListener {
        /**
         * This method will be invoked when the dialog is dismissed.
         *
         * @param dialog the dialog that was dismissed will be passed into the
         *               method
         */
        void onDismiss(IDialog dialog);
    }

    Context getContext();
}
