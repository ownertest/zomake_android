package com.jaydenxiao.common.commonwidget.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Keep;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.GravityCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;

import com.jaydenxiao.common.R;

/**
 * Created by wojiushiwn on 2017/3/25.
 * desc:
 */

public class DrawerBehavior extends CoordinatorLayout.Behavior<View> {
    private static void validateGravity(int gravity) {
        if (gravity != Gravity.LEFT
                && gravity != Gravity.RIGHT
                && gravity != GravityCompat.START
                && gravity != GravityCompat.END) {
            throw new IllegalArgumentException("Only START, END, LEFT, or RIGHT gravity is supported.");
        }
    }

    private final SimpleArrayMap<View, BehaviorDelegate> delegates = new SimpleArrayMap<>();
    private final int gravity;

    @SuppressWarnings("unused") // Public API for programmatic instantiation.
    public DrawerBehavior(int gravity) {
        validateGravity(gravity);
        this.gravity = gravity;
    }

    @Keep
    @SuppressWarnings("unused") // Instantiated reflectively from layout XML.
    public DrawerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DrawerBehavior);
        int gravity =
                a.getInteger(R.styleable.DrawerBehavior_android_layout_gravity, GravityCompat.END);
        a.recycle();

        validateGravity(gravity);
        this.gravity = gravity;
    }

    private BehaviorDelegate delegate(CoordinatorLayout parent, View child) {
        BehaviorDelegate delegate = delegates.get(child);
        if (delegate == null) {
            delegate = new BehaviorDelegate(parent, child, gravity);
            delegates.put(child, delegate);
        }
        return delegate;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        return child.getVisibility() == View.GONE //
                || delegate(parent, child).onLayoutChild();
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        return delegate(parent, child).onInterceptTouchEvent(ev);
    }

    @Override public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        return delegate(parent, child).onTouchEvent(ev);
    }
}
