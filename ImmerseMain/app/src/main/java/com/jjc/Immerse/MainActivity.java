package com.jjc.Immerse;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.jaeger.library.StatusBarView;
import com.jjc.Immerse.event.ScrollChangeEvent;
import com.jjc.Immerse.ui.NoScrollViewPager;
import com.jjc.Immerse.util.DensityUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.v_status_bar)
    View vStatusBar;
    @Bind(R.id.rl_tool_bar)
    RelativeLayout rlToolBar;
    @Bind(R.id.ll_app_bar)
    LinearLayout llAppBar;
    @Bind(R.id.iv_main_status_and_scroll)
    ImageView ivMainStatusAndScroll;
    @Bind(R.id.tv_main_status_and_scroll)
    TextView tvMainStatusAndScroll;
    @Bind(R.id.ll_main_status_and_scroll)
    LinearLayout llMainStatusAndScroll;
    @Bind(R.id.iv_main_no_status)
    ImageView ivMainNoStatus;
    @Bind(R.id.tv_main_no_status)
    TextView tvMainNoStatus;
    @Bind(R.id.ll_main_no_status)
    LinearLayout llMainNoStatus;
    @Bind(R.id.iv_main_status)
    ImageView ivMainStatus;
    @Bind(R.id.tv_main_status_)
    TextView tvMainStatus;
    @Bind(R.id.ll_main_status)
    LinearLayout llMainStatus;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.vp_main)
    NoScrollViewPager vpMain;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    private int mPagerSelectPosition;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private List<Fragment> mFragments;
    private float mHomeImageHeight;
    private Context mContext;
    private int colorPrimary;
    private int colorPrimaryDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        EventBus.getDefault().register(this);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //-----------------------------修改状态栏颜色
        StatusBarUtil.setTransparentForImageViewInFragment(this, null);
       // initStastus();
        //-----------------------------修改状态栏颜色
        mHomeImageHeight = DensityUtils.dp2px(mContext, 180);
        colorPrimary = getResources().getColor(R.color.colorPrimary);
        colorPrimaryDark = getResources().getColor(R.color.colorPrimaryDark);
        initAdapter();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void initStastus() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
            int count = decorView.getChildCount();
            if (count > 0 && decorView.getChildAt(count - 1) instanceof StatusBarView) {
                decorView.removeViewAt(count - 1);
                ViewGroup rootView = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
                rootView.setPadding(0, 0, 0, 0);
            }
        }
    }

    private void initAdapter() {
        mFragments = new ArrayList<>();
        mFragments.add(ImmerseAndScrollFragment.newInstance());
        mFragments.add(NoImmerseFragment.newInstance());
        mFragments.add(ImmerseFragment.newInstance());

        mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };

        switchPager(0);
        vpMain.setAdapter(mFragmentPagerAdapter);
        vpMain.setOffscreenPageLimit(8);
        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPagerSelectPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Subscribe
    public void onEventMainThread(ScrollChangeEvent event) {
        if (mPagerSelectPosition == 0) {//当前的位置
            llAppBar.setVisibility(View.VISIBLE);
            //计算比值
            float pr = event.scrollX / mHomeImageHeight;
            //透明度
            if (pr > 1)
                pr = 1;
            if (pr < 0)
                pr = 0;
            //获得透明的
            int alpha = (int) (255 * pr);
            String num16ColorPrimary = "#" + Integer.toHexString(alpha) + "3F51B5";
            String num16ColorPrimaryDark = "#" + Integer.toHexString(alpha) + "303F9F";
            try {
                colorPrimary = Color.parseColor(num16ColorPrimary);
                colorPrimaryDark = Color.parseColor(num16ColorPrimaryDark);
            } catch (Exception e) {
                alpha = 0;
            }
            if (alpha < 0.1) {
                rlToolBar.setBackgroundColor(getResources().getColor(R.color.tran));
                vStatusBar.setBackgroundColor(getResources().getColor(R.color.tran));
                llAppBar.setVisibility(View.GONE);
            } else {
                llAppBar.setVisibility(View.VISIBLE);
                rlToolBar.setBackgroundColor(colorPrimary);
                vStatusBar.setBackgroundColor(colorPrimaryDark);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick({R.id.ll_main_status_and_scroll, R.id.ll_main_no_status, R.id.ll_main_status})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_main_status_and_scroll:
                switchPager(0);
                tvTitle.setText("");
                rlToolBar.setBackgroundColor(colorPrimary);
                vStatusBar.setBackgroundColor(colorPrimaryDark);
                break;
            case R.id.ll_main_no_status:
                llAppBar.setVisibility(View.VISIBLE);
                tvTitle.setText("不沉浸");
                rlToolBar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                vStatusBar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                switchPager(1);
                break;
            case R.id.ll_main_status:
                tvTitle.setText("");
                rlToolBar.setBackgroundColor(getResources().getColor(R.color.tran));
                vStatusBar.setBackgroundColor(getResources().getColor(R.color.tran));
                llAppBar.setVisibility(View.GONE);
                switchPager(2);
                break;
        }
    }

    private void switchPager(int position) {
        tvMainStatusAndScroll.setSelected(position == 0);
        tvMainNoStatus.setSelected(position == 1);
        tvMainStatus.setSelected(position == 2);
        vpMain.setCurrentItem(position);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }
}
