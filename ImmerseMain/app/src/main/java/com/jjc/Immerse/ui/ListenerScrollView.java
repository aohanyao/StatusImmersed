package com.jjc.Immerse.ui;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;


/**
 * Created by 江俊超 on 2016/12/27 0027.
 * <p>Gihub https://github.com/aohanyao</p>
 */

public class ListenerScrollView extends ScrollView{
    private int downY;
    private int mTouchSlop;
    private onScrollChangeListener onScrollChangeListener;

    public void setOnScrollChangeListener(ListenerScrollView.onScrollChangeListener onScrollChangeListener) {
        this.onScrollChangeListener = onScrollChangeListener;
    }

    public ListenerScrollView(Context context) {
        this(context,null);
    }

    public ListenerScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ListenerScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (onScrollChangeListener != null) {
            onScrollChangeListener.onScrollChangeListener(t);
        }

        super.onScrollChanged(l, t, oldl, oldt);
    }
    public interface onScrollChangeListener {
        void onScrollChangeListener(float scrollX);
    }
}
