package com.jjc.Immerse.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * Created by 江俊超 on 2016/12/26 0026.
 * <p>Gihub https://github.com/aohanyao</p>
 */

public class NoScrollViewPager extends ViewPager{
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return true;
    }
}
