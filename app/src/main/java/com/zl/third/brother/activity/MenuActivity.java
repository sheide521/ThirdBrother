package com.zl.third.brother.activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.zl.third.brother.R;
import com.zl.third.brother.adapter.MenuViewPagerAdapter;
import com.zl.third.brother.fragment.MainFragment;
import com.zl.third.brother.fragment.MeFragment;
import com.zl.third.brother.fragment.NewsFragment;
import com.zl.third.brother.utils.BottomNavigationViewHelper;
import com.zl.third.brother.widghts.BottomNavigationViewEx;

import butterknife.Bind;
import butterknife.ButterKnife;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;


/**
 * Created by zhaolong on
 * 2017/11/14
 */

public class MenuActivity extends BaseActivity {
    @Bind(R.id.bv_button)
    BottomNavigationViewEx bvButton;
    @Bind(R.id.vp)
    ViewPager viewPager;
    private MenuItem menuItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        changeButtomColor();
        initView();
    }

    private void initView() {
        bvButton.enableAnimation(true);//开启或关闭点击动画(文字放大效果和图片移动效果)
        bvButton.enableShiftingMode(false);
        bvButton.enableItemShiftingMode(false);
        addBadgeAt(1, 1);

    }

    private Badge addBadgeAt(int position, int number) {
        // add badge
        return new QBadgeView(this)
                .setBadgeNumber(number)
                .setGravityOffset(25, 2, true)
                .bindTarget(bvButton.getBottomNavigationItemView(position))
                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState) {
                            Toast.makeText(MenuActivity.this, "什么东西", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    /**
     * 更改底部按钮颜色
     */
    private void changeButtomColor() {
        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        };
        int[] colors = new int[]{ContextCompat.getColor(this, R.color.all_night)
                , ContextCompat.getColor(this, R.color.app_color)};

        /**设置MenuItem的字体颜色**/
        ColorStateList csl = new ColorStateList(states, colors);
        bvButton.setItemTextColor(csl);
        bvButton.setItemIconTintList(csl);
        /**设置MenuItem默认选中项**/
        //        bvButton.getMenu().getItem(1).setChecked(true);

        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(bvButton);
        bvButton.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_nav_main:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.bottom_nav_news:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.bottom_nav_me:
                        viewPager.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bvButton.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bvButton.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //禁止ViewPager滑动
        //        viewPager.setOnTouchListener(new View.OnTouchListener() {
        //            @Override
        //            public boolean onTouch(View v, MotionEvent event) {
        //                return true;
        //            }
        //        });

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        MenuViewPagerAdapter adapter = new MenuViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MainFragment());
        adapter.addFragment(new NewsFragment());
        adapter.addFragment(new MeFragment());

        viewPager.setAdapter(adapter);
    }
}
