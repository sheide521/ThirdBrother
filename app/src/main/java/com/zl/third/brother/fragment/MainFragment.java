package com.zl.third.brother.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zl.third.brother.R;
import com.zl.third.brother.listener.AppBarStateChangeListener;
import com.zl.third.brother.widghts.banner.BannerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.zl.third.brother.R.id.collapsing_toolbar_layout;

/**
 * Created by zhaolong on
 * 2017/11/14
 * 首页
 */

public class MainFragment extends BaseFragment {
    @Bind(R.id.banner_view)
    BannerView bannerView;
    @Bind(collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.appBar)
    AppBarLayout appBar;
    @Bind(R.id.ctl_toolbar)
    Toolbar ctlToolbar;

    private TabLayout tabLayout;

    public String[] titles = new String[]{
            "湖人12月19日退役科比球衣 8号24号同时升空",
            "“老司机”带你飞 这些球员来到小牛后迈向成功",
            "巴恩斯违法为为范文芳",
            "一种篮球，两种打发",
            "一种篮球，两种打发",
    };

    private String[] imageUrls = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
            "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        ButterKnife.bind(this, view);
        bActivity.setSupportActionBar(ctlToolbar);

        //        bActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //        toolbar.setNavigationIcon(getResources().getDrawable(R.mipmap.ic_launcher_round));
        //        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //                bActivity.finish();
        //            }
        //        });

        //设置展开的时候标题显示字体颜色
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        //设置折叠的时候标题显示字体颜色
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        //设置折叠时候标题对齐位置
        collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.LEFT);

        /**
         * 是否展开
         */
        appBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());
                if (state == State.EXPANDED) {
                    //展开状态
                    Toast.makeText(getActivity(), "展开", Toast.LENGTH_SHORT).show();
                    collapsingToolbarLayout.setTitle(" ");
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    Toast.makeText(getActivity(), "折叠", Toast.LENGTH_SHORT).show();
                    collapsingToolbarLayout.setTitle("我是标题");
                } else {
                    //中间状态
                    Toast.makeText(getActivity(), "中间", Toast.LENGTH_SHORT).show();
                }
            }
        });

        initData(view);
        return view;
    }

    private void initData(final View view) {
        bannerView.setNoTitleMode(false);//是否显示文字
        final List<BannerView.BannerInfo> infos = new ArrayList<>();
        for (int i = 0; i < imageUrls.length; i++) {
            infos.add(new BannerView.BannerInfo(titles[i], imageUrls[i]));
        }
        bannerView.setBannerInfos(infos, new BannerView.OnPageClickListener() {
            @Override
            public void onPageClick(int position) {
                Toast.makeText(getActivity(), infos.get(position).title, Toast.LENGTH_SHORT).show();
            }
        });

        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 4"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 5"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 6"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 7"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 8"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 9"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 10"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 11"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 12"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 13"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 14"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 15"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
