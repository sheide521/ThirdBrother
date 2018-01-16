package com.zl.third.brother.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zl.third.brother.utils.ActivityCollector;

/**
 * Created by zhaolong on
 * 2017/11/14
 * 所有Activity的基类
// */

public class BaseActivity extends AppCompatActivity{
    public Context bActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        ActivityCollector.addActivity(this);
        bActivity = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
