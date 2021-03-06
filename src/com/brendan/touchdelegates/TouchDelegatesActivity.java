package com.brendan.touchdelegates;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.TouchDelegate;
import android.view.View;

public class TouchDelegatesActivity extends Activity {
	
	private TDRelativeLayout mBtn1Holder;
	private MyTouchDelegate mBtn1;
	private TDRelativeLayout mBtn2Holder;
	private MyTouchDelegate mBtn2;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mBtn1Holder = (TDRelativeLayout) findViewById(R.id.btn1_holder);
        mBtn2Holder = (TDRelativeLayout) findViewById(R.id.btn2_holder);
        mBtn1 = (MyTouchDelegate) findViewById(R.id.btn1);
        mBtn2 = (MyTouchDelegate) findViewById(R.id.btn2);
        expandHitArea();
        
        /* Comment out expandHitArea() and uncomment the below to try
        android's TouchDelegate class:
        
        expandTouchArea(mBtn1Holder, mBtn1, 200);
        mBtn1.setDelegate(MyTouchDelegate.DELEGATE);
        expandTouchArea(mBtn2Holder, mBtn2, 200);
        mBtn2.setDelegate(MyTouchDelegate.DELEGATE);
        */
    }
    
    /*
     * Expands hit area using getHitRect
     */
    public void expandHitArea() {
    	mBtn1.setDelegate(MyTouchDelegate.MY_DELEGATE);
    	mBtn1.setHitPadding(200);
    	mBtn2.setDelegate(MyTouchDelegate.MY_DELEGATE);
    	mBtn2.setHitPadding(200);
    }
    
    /*
     * Expands touch area using touch delegates.
     * Code is from Square talk by Eric Burke.
     * http://www.youtube.com/watch?v=jF6Ad4GYjRU&t=37m4s
     */
    public static void expandTouchArea(final View bigView, final View smallView, final int extraPadding) {
    	bigView.post(new Runnable() {
    	    @Override
    	    public void run() {
    	        Rect rect = new Rect();
    	        smallView.getHitRect(rect);
    	        rect.top -= extraPadding;
    	        rect.left -= extraPadding;
    	        rect.right += extraPadding;
    	        rect.bottom += extraPadding;
    	        bigView.setTouchDelegate(new TouchDelegate(rect, smallView));
    	    }
    	});
    }
    
}