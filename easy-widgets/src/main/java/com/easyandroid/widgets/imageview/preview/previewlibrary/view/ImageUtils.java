package com.easyandroid.widgets.imageview.preview.previewlibrary.view;

import android.content.Context;
import android.content.res.Resources;

/**
 * package: com.easyandroid.widgets.imageview.preview.previewlibrary.view.ImageUtils
 * author: gyc
 * description:
 * time: create at 2022/7/10 1:47
 */
public class ImageUtils {
    /**
     * 取状态栏高度
     *
     * @param mApplicationContent mApplicationContent
     * @return int
     */
    public static int getStatusBarHeight(Context mApplicationContent) {
        Resources resources = mApplicationContent.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    public static boolean isLongPressed(float lastX, float lastY, float thisX, float thisY, long lastDownTime, long thisEventTime, long longPressTime) {

        float offsetX = Math.abs(thisX - lastX);

        float offsetY = Math.abs(thisY - lastY);

        long intervalTime = thisEventTime - lastDownTime;

        if (offsetX <= 10 && offsetY <= 10 && intervalTime >= longPressTime) {

            return true;

        }
        return false;
    }
}