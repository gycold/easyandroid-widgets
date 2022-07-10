package com.easyandroid.widgets.imageview.preview.photoview.gestures;

import android.view.MotionEvent;

/**
 * package: com.easyandroid.widgets.imageview.preview.photoview.gestures.GestureDetector
 * author: gyc
 * description: 手势检测
 * time: create at 2022/7/10 0:52
 */
public interface GestureDetector {

    boolean onTouchEvent(MotionEvent ev);

    boolean isScaling();

    boolean isDragging();

    void setOnGestureListener(OnGestureListener listener);
}
