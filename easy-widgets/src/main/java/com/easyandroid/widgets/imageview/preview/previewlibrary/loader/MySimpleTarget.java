package com.easyandroid.widgets.imageview.preview.previewlibrary.loader;

import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;

/**
 * package: com.easyandroid.widgets.imageview.preview.previewlibrary.loader.MySimpleTarget
 * author: gyc
 * description: 图片加载回调状态接口
 * time: create at 2022/7/10 1:42
 */
public interface MySimpleTarget {

    /**
     * Callback when an image has been successfully loaded.
     * <p>
     * <strong>Note:</strong> You must not recycle the bitmap.
     */
    void onResourceReady();

    /**
     * Callback indicating the image could not be successfully loaded.
     *
     * @param errorRes 内容
     */
    void onLoadFailed(@Nullable Drawable errorRes);

}
