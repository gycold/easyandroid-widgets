package com.easyandroid.widgets.imageview.preview.photoview.scrollerproxy;

import android.content.Context;

/**
 * package: com.easyandroid.widgets.imageview.preview.photoview.scrollerproxy.IcsScroller
 * author: gyc
 * description:
 * time: create at 2022/7/10 1:02
 */
public class IcsScroller extends GingerScroller {

    public IcsScroller(Context context) {
        super(context);
    }

    @Override
    public boolean computeScrollOffset() {
        return mScroller.computeScrollOffset();
    }
}