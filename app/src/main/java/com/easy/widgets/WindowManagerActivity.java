package com.easy.widgets;

import android.content.Intent;
import android.os.Build;
import android.view.View;

import com.easy.widgets.WindowManager.WindowController;
import com.easy.widgets.WindowManager.WindowUtil;

import androidx.appcompat.widget.Toolbar;

public class WindowManagerActivity extends BaseActivity {

    private static final int REQUEST_CODE = 101;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_window_manager);
    }

    @Override
    public void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        initToolBar(toolbar, "WindowManager", true);
    }

    @Override
    public void initEvents() {
        super.initEvents();
    }

    public void start_window(View view) {
        if (!WindowUtil.canOverDraw(this)) {
            //跳转到设置页面
            WindowUtil.jump2Setting(this, REQUEST_CODE);
            return;
        }
        //开启悬浮窗
        WindowController.getInstance().showThumbWindow();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return;
                if (!WindowUtil.canOverDraw(this)) {
                    toast("悬浮窗权限未开启，请在设置中手动打开");
                    return;
                }
                WindowController.getInstance().showThumbWindow();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WindowController.getInstance().destroyThumbWindow();
    }

    public void close_window(View view) {
        WindowController.getInstance().destroyThumbWindow();
    }
}
