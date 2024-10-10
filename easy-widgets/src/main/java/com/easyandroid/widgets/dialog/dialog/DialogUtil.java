package com.easyandroid.widgets.dialog.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.easyandroid.widgets.R;
import com.easyandroid.widgets.textview.LoadingTextView;

import java.util.HashMap;

import androidx.annotation.Nullable;

/**
 * package: com.easyandroid.widgets.dialog.dialog.DialogUtil
 * author: gyc
 * description: loading等最常见的弹框的创建管理，方便快速开发
 * time: create at 2022/7/3 23:39
 */
public class DialogUtil {


    /**
     * 创建一个默认弹框，点击返回不可取消
     *
     * @param context               Context
     * @param title                 标题
     * @param content               内容
     * @param btn1Str               button文字
     * @param positiveClickListener 点击事件
     */
    public static void createDefaultDialog(Context context, String title, String content, String btn1Str, IDialog.OnClickListener positiveClickListener) {
        createDefaultDialog(context, title, content, btn1Str, positiveClickListener, "", null);
    }

    /**
     * @param context               Context
     * @param title                 标题
     * @param content               内容
     * @param btn1Str               左边按钮
     * @param negativeClickListener 左边点击事件
     * @param btn2Str               右边按钮
     * @param positiveClickListener 右边点击事件
     */
    public static void createDefaultDialog(Context context, String title, String content, String btn1Str, IDialog.OnClickListener positiveClickListener, String btn2Str, IDialog.OnClickListener negativeClickListener) {
        EasyDialog.Builder builder = new EasyDialog.Builder(context);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        if (!TextUtils.isEmpty(content)) {
            builder.setContent(content);
        }
        if (positiveClickListener != null) {
            if (TextUtils.isEmpty(btn1Str)) {
                builder.setPositiveButton(positiveClickListener);
            } else {
                builder.setPositiveButton(btn1Str, positiveClickListener);
            }
        }
        if (negativeClickListener != null) {
            if (TextUtils.isEmpty(btn2Str)) {
                builder.setNegativeButton(negativeClickListener);
            } else {
                builder.setNegativeButton(btn2Str, negativeClickListener);
            }
        }
        builder.show();
    }

    private static HashMap<String, EasyDialog> hashMap = new HashMap<>();

    /**
     * 创建Loading dialog
     *
     * @param context Context
     */
    public static void createLoadingDialog(Context context, @Nullable String txt) {
        closeLoadingDialog(context);
        EasyDialog.Builder builder = new EasyDialog.Builder(context);
        EasyDialog dialog = builder.setDialogView(R.layout.easydialog_loading_dialog)
                .setWindowBackgroundP(0.2f)
                .setBuildChildListener(new IDialog.OnBuildListener() {
                    @Override
                    public void onBuildChildView(IDialog dialog, View view, int layoutRes) {
                        TextView tv_msg = view.findViewById(R.id.tv_msg);
                        //默认文字(加载中...)
                        if (!TextUtils.isEmpty(txt)) {
                            tv_msg.setText(txt);
                        }
                        LoadingTextView tv_loading = view.findViewById(R.id.tv_loading);
                        tv_loading.startAnim();
                    }
                })
                .setCancelableOutSide(false)
                .setAnimStyle(0)
                .setCancelable(false)
                .show();
        hashMap.put(context.getClass().getSimpleName(), dialog);

    }

    /**
     * 关闭loading dialog
     *
     * @param context Context
     */
    public static void closeLoadingDialog(Context context) {
        String dialogKey = context.getClass().getSimpleName();
        EasyDialog dialog = hashMap.get(dialogKey);
        if (dialog != null) {
            hashMap.remove(dialogKey);
            dialog.dismiss();
        }
    }
}