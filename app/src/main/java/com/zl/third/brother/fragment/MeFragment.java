package com.zl.third.brother.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.zl.third.brother.R;
import com.zl.third.brother.activity.LoginActivity;
import com.zl.third.brother.utils.Permisson.PermissionConstants;
import com.zl.third.brother.utils.Permisson.PermissionHelper;
import com.zl.third.brother.utils.Permisson.PermissionUtils;
import com.zl.third.brother.utils.imageLoader.ImageLoader;
import com.zl.third.brother.widghts.pullzoom.PullToZoomScrollViewEx;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhaolong on
 * 2017/11/14
 * 首页
 */

public class MeFragment extends BaseFragment {
    @Bind(R.id.listview)
    PullToZoomScrollViewEx listview;
    private ImageView iv_user_head;
    private SwitchCompat switch_compat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);
        ButterKnife.bind(this, view);
        loadViewForCode();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void loadViewForCode() {
        View headView = LayoutInflater.from(bActivity).inflate(R.layout.profile_head_view, null, false);
        View zoomView = LayoutInflater.from(bActivity).inflate(R.layout.profile_zoom_view, null, false);
        View contentView = LayoutInflater.from(bActivity).inflate(R.layout.profile_content_view, null, false);
        listview.setHeaderView(headView);
        listview.setZoomView(zoomView);
        listview.setScrollContentView(contentView);

        iv_user_head = headView.findViewById(R.id.iv_user_head);
        ImageLoader.getInstance().displayCircleImage("http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg", iv_user_head, R.mipmap.splash01);

        switch_compat = contentView.findViewById(R.id.switch_compat);

        //监听夜间模式点击事件
        switch_compat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //                showAnimation();
                //                if (b) {
                //                    bActivity.setTheme(R.style.NightTheme);
                //                } else {
                //                    bActivity.setTheme(R.style.AppTheme);
                //                }
            }
        });

        /**
         * 登陆
         */
        headView.findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        /**
         * 设置
         */
        headView.findViewById(R.id.iv_user_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionHelper.requestCamera(new PermissionHelper.OnPermissionGrantedListener() {
                    @Override
                    public void onPermissionGranted() {
//                        PhoneUtils.sendSmsSilent("10000", "sendSmsSilent");
                        Toast.makeText(getActivity(), "请求完 权限成功后。。。。", 100).show();
                    }
                });
//                startActivity(new Intent(getActivity(), SettngActivity.class));
            }
        });
    }
}
