package com.brendan.touchdelegates;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class TDRelativeLayout extends RelativeLayout {
	
	private static final String TAG = "TDRelativeLayout";

	 public TDRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	 
	 protected boolean isTransformedTouchPointInView(float x, float y, View child, PointF outLocalPoint) {
		 //Log.d(TAG, "isTranformedTouchPointInView()");
		 final Rect frame = new Rect();
		 child.getHitRect(frame);
		 if(frame.contains((int)x, (int)y)) {
			 return true;
		 }
		 return false;
	 }

}
