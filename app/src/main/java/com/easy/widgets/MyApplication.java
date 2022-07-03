package com.easy.widgets;

import android.app.Application;

/**
 * package: com.easy.widgets.MyApplication
 * author: gyc
 * description:
 * time: create at 2022/7/4 1:20
 */
public class MyApplication extends Application {

  private static MyApplication application;

  @Override
  public void onCreate() {
    super.onCreate();
    application = this;
  }

  public static MyApplication getApplication() {
    return application;
  }
  
}