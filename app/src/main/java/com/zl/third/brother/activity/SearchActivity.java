package com.zl.third.brother.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zl.third.brother.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhaolong on
 * 2017/12/20
 * 搜索城市
 */

public class SearchActivity extends BaseActivity {
    @Bind(R.id.rcy)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        ButterKnife.bind(this);
    }
}
