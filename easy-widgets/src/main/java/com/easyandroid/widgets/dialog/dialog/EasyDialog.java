package com.easyandroid.widgets.dialog.dialog;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.easyandroid.widgets.R;

import java.lang.reflect.Field;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * package: com.easyandroid.widgets.dialog.dialog.EasyDialog
 * author: gyc
 * description:
 * time: create at 2022/7/3 22:43
 */
public class EasyDialog extends EasyBaseDialog implements IDialog {

    private Context context;
    private EasyDialogController controller;
    private IDialog.OnBuildListener buildListener;
    private IDialog.OnDismissListener dismissListener;
    private static final String FTag = "dialogTag";

    public EasyDialog() {
        controller = new EasyDialogController(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    protected int getLayoutRes() {
        return controller.getLayoutRes();
    }

    @Override
    protected View getDialogView() {
        return controller.getDialogView();
    }

    @Override
    protected int getDialogWidth() {
        return controller.getDialogWidth();
    }

    @Override
    protected int getDialogHeight() {
        return controller.getDialogHeight();
    }

    @Override
    protected boolean isCancelableOutside() {
        return controller.isCancelableOutside();
    }

    @Override
    public boolean isCancelable() {
        return controller.isCancelable();
    }

    @Override
    public float getDimAmount() {
        return controller.getDimAmount();
    }

    @Override
    protected int getGravity() {
        return controller.getGravity();
    }

    @Override
    protected int getAnimRes() {
        return controller.getAnimRes();
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (controller == null) {
            controller = new EasyDialogController(this);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //????????????Dialog???View??????
        controller.setChildView(view);
        //????????????????????????????????????View??????????????????
        if (buildListener != null && getBaseView() != null) {
            buildListener.onBuildChildView(this, getBaseView(), getLayoutRes());
        }
    }

    /**
     * ?????? Can not perform this action after onSaveInstanceState??????
     *
     * @param manager FragmentManager
     * @param tag     tag
     */
    public void showAllowingLoss(FragmentManager manager, String tag) {
        try {
            Class cls = DialogFragment.class;
            Field mDismissed = cls.getDeclaredField("mDismissed");
            mDismissed.setAccessible(true);
            mDismissed.set(this, false);
            Field mShownByMe = cls.getDeclaredField("mShownByMe");
            mShownByMe.setAccessible(true);
            mShownByMe.set(this, true);
        } catch (Exception e) {
            //????????????show()??????
            show(manager, tag);
            return;
        }
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void dismiss() {
        //???????????????????????? getFragmentManager????????????????????????
        //Attempt to invoke virtual method 'android.app.FragmentTransaction
        //android.app.FragmentManager.beginTransaction()' on a null object reference
        if (getFragmentManager() == null) return;
        super.dismissAllowingStateLoss();
    }

    @Override
    public void onDestroy() {
        //??????dialog???dismiss
        if (dismissListener != null) {
            dismissListener.onDismiss(this);
        }
        if (controller != null) {
            controller = null;
        }
        super.onDestroy();
    }

    public static class Builder {
        private EasyDialogController.EasyParams params;
        private IDialog.OnBuildListener buildListener;
        private IDialog.OnDismissListener dismissListener;

        public Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context can't be null");
            }
            if (!(context instanceof FragmentActivity)) {
                throw new IllegalArgumentException("Context must be FragmentActivity");
            }
            params = new EasyDialogController.EasyParams();
            params.fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
            params.context = context;
        }

        /**
         * ??????DialogView
         *
         * @param layoutRes ????????????
         * @return Builder
         */
        public Builder setDialogView(@LayoutRes int layoutRes) {
            params.layoutRes = layoutRes;
            return this;
        }

        /**
         * ??????DialogView
         *
         * @param dialogView View
         * @return Builder
         */
        public Builder setDialogView(View dialogView) {
            params.dialogView = dialogView;
            return this;
        }

        /**
         * ???????????????????????????
         *
         * @param percentage 0.0f~1.0f
         * @return Builder
         */
        public Builder setScreenWidthP(float percentage) {
            params.dialogWidth = (int) (getScreenWidth((FragmentActivity) params.context) * percentage);
            return this;
        }

        /**
         * ???????????????????????????
         *
         * @param percentage 0.0f~1.0f
         * @return Builder
         */
        public Builder setScreenHeightP(float percentage) {
            params.dialogHeight = (int) (getScreenHeight((FragmentActivity) params.context) * percentage);
            return this;
        }

        /**
         * ??????Dialog?????????
         *
         * @param width ??????
         * @return Builder
         */
        public Builder setWidth(int width) {
            params.dialogWidth = width;
            return this;
        }

        /**
         * ??????Dialog?????????
         *
         * @param height ??????
         * @return Builder
         */
        public Builder setHeight(int height) {
            params.dialogHeight = height;
            return this;
        }

        /**
         * ?????????????????????
         *
         * @param percentage 0.0f~1.0f 1.0f??????????????????
         * @return Builder
         */
        public Builder setWindowBackgroundP(float percentage) {
            params.dimAmount = percentage;
            return this;
        }

        /**
         * ??????Gravity
         *
         * @param gravity Gravity
         * @return Builder
         */
        public Builder setGravity(int gravity) {
            params.gravity = gravity;
            return this;
        }

        /**
         * ??????dialog????????????????????????dialog??????
         *
         * @param cancelableOutSide true ??????dialog????????????????????????dialog??????
         * @return Builder
         */
        public Builder setCancelableOutSide(boolean cancelableOutSide) {
            params.isCancelableOutside = cancelableOutSide;
            return this;
        }

        /**
         * ?????????????????????????????????
         *
         * @param cancelable true ??????????????????????????????dialog????????????????????????
         * @return Builder
         */
        public Builder setCancelable(boolean cancelable) {
            params.cancelable = cancelable;
            return this;
        }

        /**
         * ?????????View???listener
         *
         * @param listener IDialog.OnBuildListener
         * @return Builder
         */
        public Builder setBuildChildListener(IDialog.OnBuildListener listener) {
            this.buildListener = listener;
            return this;
        }

        /**
         * ??????dialog???dismiss
         *
         * @param dismissListener IDialog.OnDismissListener
         * @return Builder
         */
        public Builder setOnDismissListener(IDialog.OnDismissListener dismissListener) {
            this.dismissListener = dismissListener;
            return this;
        }

        /**
         * ??????dialog???????????????
         *
         * @param animStyle ??????????????????
         * @return Builder
         */
        public Builder setAnimStyle(int animStyle) {
            params.animRes = animStyle;
            return this;
        }

        /**
         * ??????????????????????????????
         *
         * @param onclickListener IDialog.OnClickListener
         * @return Builder
         */
        public Builder setPositiveButton(IDialog.OnClickListener onclickListener) {
            return setPositiveButton("??????", onclickListener);
        }

        /**
         * ???????????????????????????????????????
         *
         * @param onclickListener IDialog.OnClickListener
         * @return Builder
         */
        public Builder setPositiveButton(String btnStr, IDialog.OnClickListener onclickListener) {
            params.positiveBtnListener = onclickListener;
            params.positiveStr = btnStr;
            params.showBtnRight = true;
            return this;
        }

        /**
         * ??????????????????????????????????????????????????????
         *
         * @param onclickListener IDialog.OnClickListener
         * @return Builder
         */
        public Builder setPositiveButton(String btnStr, int colorId, IDialog.OnClickListener onclickListener) {
            params.positiveBtnListener = onclickListener;
            params.positiveStr = btnStr;
            params.positiveTextColorId = colorId;
            params.showBtnRight = true;
            return this;
        }

        /**
         * ??????????????????
         *
         * @param onclickListener IDialog.OnClickListener
         * @return Builder
         */
        public Builder setNegativeButton(IDialog.OnClickListener onclickListener) {
            return setNegativeButton("??????", onclickListener);
        }

        /**
         * ???????????????????????????
         *
         * @param btnStr          ??????
         * @param onclickListener IDialog.OnClickListener
         * @return Builder
         */
        public Builder setNegativeButton(String btnStr, IDialog.OnClickListener onclickListener) {
            params.negativeBtnListener = onclickListener;
            params.negativeStr = btnStr;
            params.showBtnLeft = true;
            return this;
        }

        /**
         * ??????????????????????????????????????????
         *
         * @param btnStr          ??????
         * @param onclickListener IDialog.OnClickListener
         * @return Builder
         */
        public Builder setNegativeButton(String btnStr, int colorId, IDialog.OnClickListener onclickListener) {
            params.negativeBtnListener = onclickListener;
            params.negativeStr = btnStr;
            params.negativeTextColorId = colorId;
            params.showBtnLeft = true;
            return this;
        }

        /**
         * ????????????dialog???title
         *
         * @param title ??????
         * @return Builder
         */
        public Builder setTitle(String title) {
            params.titleStr = title;
            return this;
        }

        /**
         * ????????????dialog?????????
         *
         * @param content ??????
         * @return Builder
         */
        public Builder setContent(String content) {
            params.contentStr = content;
            return this;
        }

        private EasyDialog create() {
            EasyDialog dialog = new EasyDialog();
            params.apply(dialog.controller);
            dialog.buildListener = buildListener;
            dialog.dismissListener = dismissListener;
            return dialog;
        }

        /**
         * ??????Dialog
         *
         * @return SYDialog
         */
        public EasyDialog show() {
            if (params.layoutRes <= 0 && params.dialogView == null) {
                //???????????????????????? ??????????????????
                setDefaultOption();
            }
            EasyDialog dialog = create();
            //??????
            if (params.context == null) return dialog;
            if (params.context instanceof FragmentActivity) {
                FragmentActivity activity = (FragmentActivity) params.context;
                //??????Activity?????????????????????????????? ????????????
                boolean isRefuse = Build.VERSION.SDK_INT >= 17
                        ? activity.isFinishing() || activity.isDestroyed()
                        : activity.isFinishing();

                if (isRefuse) return dialog;
            }
            removePreDialog();
            dialog.showAllowingLoss(params.fragmentManager, FTag);
            return dialog;
        }

        /**
         * ????????????Dialog?????????
         */
        private void setDefaultOption() {
            params.cancelable = false;
            params.isCancelableOutside = false;
            params.gravity = Gravity.CENTER;
            params.layoutRes = R.layout.easydialog_lib_ui_layout_dialog_default;
            params.dimAmount = 0.5f;
            params.dialogWidth = (int) (getScreenWidth((FragmentActivity) params.context) * 0.85f);
            params.dialogHeight = WindowManager.LayoutParams.WRAP_CONTENT;
        }

        /**
         * ???????????????dialog
         */
        private void removePreDialog() {
            FragmentTransaction ft = params.fragmentManager.beginTransaction();
            Fragment prev = params.fragmentManager.findFragmentByTag(FTag);
            if (prev != null) {
                ft.remove(prev);
            }
            ft.commitAllowingStateLoss();
        }
    }
}