package com.zl.third.brother.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zl.third.brother.R;
import com.zl.third.brother.widghts.ZhiFuBaoView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhaolong on
 * 2017/12/20
 */

public class ZhifubaoActivity extends BaseActivity {

    @Bind(R.id.zl_view)
    ZhiFuBaoView mZlView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhifubao);
        ButterKnife.bind(this);
        mZlView.setValues(10,100,52,"df");
    }

    private void initLoad() {
    }
}
