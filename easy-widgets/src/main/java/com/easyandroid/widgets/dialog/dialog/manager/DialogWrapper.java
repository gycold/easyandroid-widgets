package com.easyandroid.widgets.dialog.dialog.manager;

import com.easyandroid.widgets.dialog.dialog.EasyDialog;

/**
 * package: com.easyandroid.widgets.dialog.dialog.manager.DialogWrapper
 * author: gyc
 * description: 管理多个dialog，可按照dialog的优先级依次弹出
 * time: create at 2022/7/3 22:52
 */
public class DialogWrapper {

    private EasyDialog.Builder dialog;//统一管理dialog的弹出顺序

    public DialogWrapper(EasyDialog.Builder dialog) {
        this.dialog = dialog;
    }

    public EasyDialog.Builder getDialog() {
        return dialog;
    }

    public void setDialog(EasyDialog.Builder dialog) {
        this.dialog = dialog;
    }

}