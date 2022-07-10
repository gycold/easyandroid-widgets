package com.easyandroid.widgets.imageview.preview.photoview.gestures;

/**
 * package: com.easyandroid.widgets.imageview.preview.photoview.gestures.OnGestureListener
 * author: gyc
 * description: 手势定义
 * time: create at 2022/7/10 0:53
 */
public interface OnGestureListener {
    void onDrag(float dx, float dy);

    void onFling(float startX, float startY, float velocityX,
                 float velocityY);

    void onScale(float scaleFactor, float focusX, float focusY);
}
