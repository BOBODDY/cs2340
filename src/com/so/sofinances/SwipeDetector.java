package com.so.sofinances;

import android.view.MotionEvent;
import android.view.View;

public class SwipeDetector implements View.OnTouchListener {

    public static enum Action {
        LR, // Left to Right
        RL, // Right to Left
        TB, // Top to bottom
        BT, // Bottom to Top
        None // when no action was detected
    }

    private static final int MIN_DISTANCE = 100;
    private float downX, downY, upX, upY;
    private Action mSwipeDetected = Action.None;

    public boolean swipeDetected() {
        return mSwipeDetected != Action.None;
    }

    public Action getAction() {
        return mSwipeDetected;
    }

    public boolean onTouch(View v, MotionEvent event) {
    	int eventAction = event.getAction();
    	boolean touchResult = false;
    	
    	if(eventAction == MotionEvent.ACTION_DOWN) {
    		downX = event.getX();
            downY = event.getY();
            mSwipeDetected = Action.None;
            touchResult = false; // allow other events like Click to be processed
    	} else if(eventAction == MotionEvent.ACTION_MOVE) {
    		upX = event.getX();
            upY = event.getY();

            float deltaX = downX - upX;
            float deltaY = downY - upY;

            // horizontal swipe detection
            if (Math.abs(deltaX) > MIN_DISTANCE) {
                // left or right
                if (deltaX < 0) {
                    //Logger.show(Log.INFO,logTag, "Swipe Left to Right");
                    mSwipeDetected = Action.LR;
                    touchResult = true;
                }
                if (deltaX > 0) {
                    //Logger.show(Log.INFO,logTag, "Swipe Right to Left");
                    mSwipeDetected = Action.RL;
                    touchResult = true;
                }
            } else 

                // vertical swipe detection
                if (Math.abs(deltaY) > MIN_DISTANCE) {
                    // top or down
                    if (deltaY < 0) {
                        ////Logger.show(Log.INFO,logTag, "Swipe Top to Bottom");
                        mSwipeDetected = Action.TB;
                        touchResult = false;
                    }
                    if (deltaY > 0) {
                        ////Logger.show(Log.INFO,logTag, "Swipe Bottom to Top");
                        mSwipeDetected = Action.BT;
                        touchResult = false;
                    }
                } 
            touchResult = true;
    	}
    	
        return touchResult;
    }
}