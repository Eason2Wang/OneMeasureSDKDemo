package com.tozmart.tozisdkdemo;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.jess.arms.base.App;
import com.jess.arms.di.component.AppComponent;
import com.tozmart.tozisdk.app.ToziSDK;

public class MyApplication extends Application implements App {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        ToziSDK.getInstance().attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ToziSDK.getInstance().onCreate(this);
    }

    /**
     * 在模拟环境中程序终止时会被调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        ToziSDK.getInstance().onTerminate(this);
    }

    @NonNull
    @Override
    public AppComponent getAppComponent() {
        return ((App) ToziSDK.getInstance().getmAppDelegate()).getAppComponent();
    }
}
