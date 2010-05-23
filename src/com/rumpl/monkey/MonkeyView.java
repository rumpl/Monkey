package com.rumpl.monkey;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MonkeyView extends View {

    private String mMonkey = "Touch me.";
    private int mTextY;
    
    private float circleX;
    private float circleY;
    private boolean drawCircle;
    
    private Paint mTextPaint;
    private Paint mCirclePaint;
    
    public MonkeyView(Context context) {
        super(context);
        
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.LTGRAY);
        mTextPaint.setTextSize(20);
        
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(Color.BLUE);
        
        drawCircle = false;
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            drawCircle = false;
        }
        else {
            circleX = event.getX();
            circleY = event.getY();
            drawCircle = true;
            invalidate();
        }
        
        // Why would ActivityManager.isUserAMonkey() throw a java.lang.RuntimeException ? 
        try {
            if(!ActivityManager.isUserAMonkey()) {
                mMonkey = "No monkeys here...";            
            }
            else {
                mMonkey = "You did it! You're a monkey!";
            }
        }
        catch(Exception e) {
            Log.w("Monkeys are attacking", e);
            mMonkey = "Oook!";
        }
        
        return true;
    }
    
    @Override
    protected void onSizeChanged (int w, int h, int oldw, int oldh) {
        mTextY = h / 2;
        invalidate();
    }
    
    @Override 
    protected void onDraw(Canvas canvas) {
        canvas.drawText(mMonkey, 10, mTextY, mTextPaint);

        if(drawCircle) {
            canvas.drawCircle(circleX, circleY, 20, mCirclePaint);
        }
    }
}
