package com.freecreator.whiteforest.ui.views;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.Scroller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanrunqi on 2016/6/26.
 */

public class SwipeLayout extends LinearLayout {

    private Scroller scroller;

    private int mTouchSlop;

    private int rightViewWidth;

    public static final int EXPAND = 0;
    public static final int SHRINK = 1;

    Boolean isHorizontalMove;

    float startX;
    float startY;
    float curX;
    float curY;
    float lastX;


    public SwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOrientation(HORIZONTAL);
        scroller = new Scroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        View rightView = this.getChildAt(1);
        rightViewWidth = rightView.getMeasuredWidth();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:

                disallowParentsInterceptTouchEvent(getParent());
                startX = ev.getX();
                startY = ev.getY();
                isHorizontalMove =false;
                break;
            case MotionEvent.ACTION_MOVE:
                if(!isHorizontalMove){
                    curX = ev.getX();
                    curY = ev.getY();
                    float dx = curX - startX;
                    float dy = curY - startY;

                    if(dx*dx+dy*dy > mTouchSlop*mTouchSlop){

                        if (Math.abs(dy) > Math.abs(dx)){

                            allowParentsInterceptTouchEvent(getParent());

                            shrinkAllView();
                        }else{

                            isHorizontalMove = true;

                            lastX = curX;
                        }
                    }
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(isHorizontalMove){

            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if(isHorizontalMove){
                    curX = ev.getX();
                    float dX = curX-lastX;

                    lastX = curX;

                    int disX = getScrollX() + (int)(-dX);

                    if(disX<0){

                        scrollTo(0, 0);
                    }

                    else if(disX>rightViewWidth){
                        scrollTo(rightViewWidth,0);
                    }
                    else{
                        scrollTo(disX, 0);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                float endX = ev.getX();
                float dis =endX -startX;

                if(dis<0){
                    SimulateScroll(EXPAND);
                }

                else{
                    SimulateScroll(SHRINK);
                }
            default:
                break;
        }

        return true;
    }


    public void SimulateScroll(int type){
        int dx =0;
        switch (type){
            case EXPAND:
                dx = rightViewWidth-getScrollX();
                break;
            case SHRINK:
                dx = 0-getScrollX();
                break;
            default:
                break;
        }

        scroller.startScroll(getScrollX(),0,dx,0,Math.abs(dx)/2);
        invalidate();
    }

    @Override
    public void computeScroll() {

        if (scroller.computeScrollOffset()) {

            scrollTo(scroller.getCurrX(), 0);

            invalidate();
        }
    }


    static List<SwipeLayout> swipelayouts = new ArrayList<>();
    public  static void addSwipeView(SwipeLayout v){
        if(null==v){
            return;
        }
        swipelayouts.add(v);
    }
    public static void removeSwipeView(SwipeLayout v){
        if(null==v){
            return;
        }
        v.SimulateScroll(SwipeLayout.SHRINK);
    }
    private void shrinkAllView(){
        for(SwipeLayout s :swipelayouts){
            if(null==s){
                swipelayouts.remove(s);
                continue;
            }else {
                s.SimulateScroll(SwipeLayout.SHRINK);
            }

        }
    }

    private void disallowParentsInterceptTouchEvent(ViewParent parent) {
        if (null == parent) {
            return;
        }
        parent.requestDisallowInterceptTouchEvent(true);
        disallowParentsInterceptTouchEvent(parent.getParent());
    }
    private void allowParentsInterceptTouchEvent(ViewParent parent) {
        if (null == parent) {
            return;
        }
        parent.requestDisallowInterceptTouchEvent(false);
        allowParentsInterceptTouchEvent(parent.getParent());
    }
}


/*
import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;


public class SwipeLayout extends LinearLayout {

    private ViewDragHelper viewDragHelper;
    private View contentView;
    private View actionView;
    private int dragDistance;
    private final double AUTO_OPEN_SPEED_LIMIT = 800.0;
    private int draggedX;

    public SwipeLayout(Context context) {
        this(context, null);
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public SwipeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        viewDragHelper = ViewDragHelper.create(this, new DragHelperCallback());
    }

    @Override
    protected void onFinishInflate() {
        contentView = getChildAt(0);
        actionView = getChildAt(1);
        actionView.setVisibility(GONE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        dragDistance = actionView.getMeasuredWidth();
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View view, int i) {
            return view == contentView || view == actionView;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            draggedX = left;
            if (changedView == contentView) {
                actionView.offsetLeftAndRight(dx);
            } else {
                contentView.offsetLeftAndRight(dx);
            }
            if (actionView.getVisibility() == View.GONE) {
                actionView.setVisibility(View.VISIBLE);
            }
            invalidate();
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child == contentView) {
                final int leftBound = getPaddingLeft();
                final int minLeftBound = -leftBound - dragDistance;
                final int newLeft = Math.min(Math.max(minLeftBound, left), 0);
                return newLeft;
            } else {
                final int minLeftBound = getPaddingLeft() + contentView.getMeasuredWidth() - dragDistance;
                final int maxLeftBound = getPaddingLeft() + contentView.getMeasuredWidth() + getPaddingRight();
                final int newLeft = Math.min(Math.max(left, minLeftBound), maxLeftBound);
                return newLeft;
            }
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return dragDistance;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            boolean settleToOpen = false;
            if (xvel > AUTO_OPEN_SPEED_LIMIT) {
                settleToOpen = false;
            } else if (xvel < -AUTO_OPEN_SPEED_LIMIT) {
                settleToOpen = true;
            } else if (draggedX <= -dragDistance / 2) {
                settleToOpen = true;
            } else if (draggedX > -dragDistance / 2) {
                settleToOpen = false;
            }

            final int settleDestX = settleToOpen ? -dragDistance : 0;
            viewDragHelper.smoothSlideViewTo(contentView, settleDestX, 0);
            ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(viewDragHelper.shouldInterceptTouchEvent(ev)) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}

*/