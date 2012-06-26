package com.brendan.touchdelegates;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * 
 * @author Brendan
 * Example of a button that has an expanded touch area.
 */
public class MyTouchDelegate extends Button {

	private static final String TAG = "MyTouchDelegate";

	private int mHitPadding = 0; //extra touch area
	public final static int DELEGATE = 0;
	public final static int MY_DELEGATE = 1;
	private int mMode = MY_DELEGATE;

	public MyTouchDelegate(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/*
	 * Set how much we want to expand touchable area
	 */
	public void setHitPadding(int hitPadding) {
		mHitPadding = hitPadding;
	}
	
	/*
	 * Method for testing purposes only
	 */
	public void setDelegate(int delegate) {
		mMode = delegate;
	}

	/*
	 * Expand the outRect to reflect touchable area
	 */
	@Override
	public void getHitRect(Rect outRect) {
		outRect.set(getLeft() - mHitPadding, getTop() - mHitPadding, getRight() + mHitPadding, getBottom() + mHitPadding);
	}
	
	

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(mMode == DELEGATE) {
			return super.onTouchEvent(event);
		}
		final int x = (int) event.getX();
		final int y = (int) event.getY();
		final int action = event.getAction();

		if (isEnabled()) {
			switch (action & 0xFF) {
			case MotionEvent.ACTION_DOWN:
				setPressed(true);
				return true;
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
				setPressed(false);
				return false;
			}
		}
		if (action == MotionEvent.ACTION_MOVE) {
			if (!contains(x, y)) {
				setPressed(false);
				return false;
			}
			return true;
		}
		return false;
	}

	/*
	 * Returns if child view's expandable touch area contains
	 * the point.
	 */
	private boolean contains(int x, int y) {
		final Rect frame = new Rect();
		getHitRect(frame);
		if(frame.contains((int)x, (int)y)) {
			 return true;
		}
		return false;
	}
}
