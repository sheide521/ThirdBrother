package com.zl.third.brother.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.zl.third.brother.R;
import com.zl.third.brother.beans.databinding.UserBean;
import com.zl.third.brother.databinding.ActivityWebBinding;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhaolong on
 * 2017/12/20
 */

public class WebActivity extends BaseActivity {
    @Bind(R.id.tv_web)
    WebView mTvWeb;

    UserBean mUserBean;
    @Bind(R.id.tv_name)
    TextView mTvName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_web);

        //        initLoad();
        ActivityWebBinding activity = DataBindingUtil.setContentView(this, R.layout.activity_web);
        ButterKnife.bind(this);
        mUserBean = new UserBean("leavesC", "123456");
        activity.setUserBean(mUserBean);
        mTvName.animate().scaleX(1.2f).scaleY(1.2f).alpha(0.5f).setDuration(3000).start();
    }

    private void initLoad() {
        mTvWeb.getSettings().setJavaScriptEnabled(true);
        mTvWeb.getSettings().setSupportZoom(true);
        mTvWeb.getSettings().setDomStorageEnabled(true);
        mTvWeb.getSettings().setAllowFileAccess(true);
        //                mTvWeb.getSettings().setPluginsEnabled(true);
        mTvWeb.getSettings().setUseWideViewPort(true);
        mTvWeb.getSettings().setBuiltInZoomControls(true);
        mTvWeb.requestFocus();
        mTvWeb.getSettings().setLoadWithOverviewMode(true);
        mTvWeb.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        String pdfUrl = "http://testh5.51skyline.com/pdfFile.aspx?id=1";
        mTvWeb.loadUrl(pdfUrl);
    }
}
