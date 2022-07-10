package com.easyandroid.widgets.imageview.preview.previewlibrary.entity;

import android.graphics.Rect;
import android.os.Parcelable;

import androidx.annotation.Nullable;

/**
 * package: com.easyandroid.widgets.imageview.preview.previewlibrary.entity.IThumbViewInfo
 * author: gyc
 * description: 图片预览实体类接口
 * time: create at 2022/7/10 1:34
 */
public interface IThumbViewInfo extends Parcelable {

    /****
     * 图片地址
     * @return String
     * ****/
    String getUrl();

    /**
     * 记录坐标
     *
     * @return Rect
     ***/
    Rect getBounds();


    /**
     * 获取视频链接
     ***/
    @Nullable
    String getVideoUrl();

}
