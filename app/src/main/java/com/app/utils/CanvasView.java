package com.app.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class CanvasView extends View{
	Paint paint;
	
	public CanvasView(Context context){
		super(context);
		paint = new Paint();
		paint.setColor(Color.YELLOW);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		paint.setStrokeWidth(3);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#F5FFFA"));
		canvas.drawCircle(100, 100, 100, paint);
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x, y;
		int action = event.getAction();
		
		switch (action) {
		
			case MotionEvent.ACTION_DOWN:
				x = event.getX();
				y = event.getY();
				Log.v("YJDOWN", String.valueOf(x) + "," + String.valueOf(y));
				break;
				
			case MotionEvent.ACTION_MOVE:
	
				x = event.getX();
				y = event.getY();
				
				Log.v("YJ", String.valueOf(x) + "," + String.valueOf(y));
				break;
			case MotionEvent.ACTION_UP:
				x = event.getX();
				y = event.getY();
				Log.v("YJUP", String.valueOf(x) + "," + String.valueOf(y));
				break;
		}
		
		
		return true;
	}


}
