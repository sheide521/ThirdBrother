package com.zl.third.brother;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.zl.third.brother.utils.Permisson.Utils;


/**
 * Created by zhenfei.wang on 2016/8/8.
 */
public class MyApplication extends MultiDexApplication {
    private final String TAG = getClass().getSimpleName();
    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        Bugly.init(getApplicationContext(), "c8f7dd49fb", false);
        Utils.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);


        // 安装tinker
        Beta.installTinker();
    }

    public static MyApplication getAppInstance() {
        return application;
    }
}
