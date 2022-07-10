package com.easyandroid.widgets.imageview.preview.photoview.gestures;

import android.content.Context;
import android.os.Build;

/**
 * package: com.easyandroid.widgets.imageview.preview.photoview.gestures.VersionedGestureDetector
 * author: gyc
 * description:
 * time: create at 2022/7/10 0:59
 */
public final class VersionedGestureDetector {

    public static GestureDetector newInstance(Context context, OnGestureListener listener) {
        final int sdkVersion = Build.VERSION.SDK_INT;
        GestureDetector detector;

        if (sdkVersion < Build.VERSION_CODES.ECLAIR) {
            detector = new CupcakeGestureDetector(context);
        } else if (sdkVersion < Build.VERSION_CODES.FROYO) {
            detector = new EclairGestureDetector(context);
        } else {
            detector = new FroyoGestureDetector(context);
        }

        detector.setOnGestureListener(listener);

        return detector;
    }
}