package com.zl.third.brother.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.zl.third.brother.activity.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by zhaolong on
 * 2017/11/14
 * fragment的基类
 */

public class BaseFragment extends Fragment {
    protected BaseActivity bActivity;
    public boolean hidden;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        bActivity = (BaseActivity) context;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        this.hidden = hidden;
        if (hidden) {
            onPause();
        } else {
            onResume();
        }
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (bActivity == null) {
            bActivity = (BaseActivity) getActivity();
        }
    }
}

