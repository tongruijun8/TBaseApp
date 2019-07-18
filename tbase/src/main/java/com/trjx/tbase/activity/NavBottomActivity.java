package com.trjx.tbase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.trjx.R;
import com.trjx.tbase.adapter.TViewPagerStateAdapter;
import com.trjx.tbase.fragment.TFragment;
import com.trjx.tlibs.views.TViewPager;

import java.util.ArrayList;
import java.util.List;

public abstract class NavBottomActivity extends InitActivity implements BottomNavigationView.OnNavigationItemReselectedListener, BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    protected BottomNavigationView mBottomNavView;
    protected TViewPager mViewpager;
    //ViewPager是否带滚动动画
    private boolean smoothScroll;

    //fragment数据集合
    protected List<TFragment> fragmentList = new ArrayList<>();

    private TViewPagerStateAdapter tViewPagerStateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        initWork();
        initView();
    }

    /**
     * 可以自定义布局：需要包含activity_nav_bottom 中的对应控件，负责会报异常
     *
     * @return
     */
    protected @LayoutRes
    int initLayout() {
        return R.layout.activity_nav_bottom;
    }


    @Override
    protected void initView() {

        smoothScroll = initSmoothScroll();

        mBottomNavView = findViewById(R.id.bottom_nav_view);
        mViewpager = findViewById(R.id.viewpager);
        mViewpager.setScanScroll(false);
        mViewpager.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        mBottomNavView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

//        1.取消位移动画的效果
//        activityAssist.disableShiftMode(mBottomNavView);
//        2.取消导航栏的点击效果（类似水波纹的效果）：app:itemBackground="@null"
//        3.取消导航栏的每项点击文字和图片放大的效果：我们需要在values中的demens.xml中设置相应的值

        mBottomNavView.setOnNavigationItemSelectedListener(this);
        mBottomNavView.setOnNavigationItemReselectedListener(this);
        initFragmentData();
        //添加底部菜单
        int resMenuId = initTabMenu();
        if (resMenuId == 0) {
            //默认
            resMenuId = R.menu.navigation_bottom;
        }
        mBottomNavView.inflateMenu(resMenuId);
        tViewPagerStateAdapter = new TViewPagerStateAdapter(getSupportFragmentManager());
        tViewPagerStateAdapter.setmFragments(fragmentList);
        mViewpager.setAdapter(tViewPagerStateAdapter);
        mViewpager.setOffscreenPageLimit(fragmentList.size());
        mViewpager.setScanScroll(false);
        mViewpager.addOnPageChangeListener(this);
    }


    protected abstract boolean initSmoothScroll();


    protected abstract void initFragmentData();
//    {
//        //默认
////        if (fragmentList == null || fragmentList.size() == 0) {
////            fragmentList.add(BlankFragment.newInstance("首页", ""));
////            fragmentList.add(BlankFragment.newInstance("订单", ""));
////            fragmentList.add(BlankFragment.newInstance("消息", ""));
////            fragmentList.add(BlankFragment.newInstance("我的", ""));
////        }
//    }

    protected abstract int initTabMenu();
//    {
//        return R.menu.navigation_bottom;
//    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int position = intent.getIntExtra("position", -1);
        if (position > -1) {
            skipTab(position);
        }
    }


    public void skipTab(int position){
        if (menuItem != null) {
            menuItem.setChecked(false);
        } else {
            mBottomNavView.getMenu().getItem(0).setChecked(false);
        }
        menuItem = mBottomNavView.getMenu().getItem(position);
        menuItem.setChecked(true);
        selectTabItem(position);
    }


    private boolean selectTabItem(int position) {
        if (position > -1) {
            fragmentList.get(position).initData();
            mViewpager.setCurrentItem(position, smoothScroll);
            return true;
        }
        return false;
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }

    private MenuItem menuItem;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        menuItem = item;
        int itemId = item.getItemId();
        int index = -1;
        if (itemId == R.id.nav_bottom_item1) {
            index = 0;
        } else if (itemId == R.id.nav_bottom_item2) {
            index = 1;
        } else if (itemId == R.id.nav_bottom_item3) {
            index = 2;
        } else if (itemId == R.id.nav_bottom_item4) {
            index = 3;
        }

        return selectTabItem(index);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        skipTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
