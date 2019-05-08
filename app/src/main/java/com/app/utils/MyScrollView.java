package com.app.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {    
    private OnScrollListener onScrollListener = null;    
    private int scrollY;    

    public MyScrollView(Context context) {        
    	super(context, null);
    }    

    public MyScrollView(Context context, AttributeSet attrs) {        
    	super(context, attrs, 0);    
    }    

    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {        
        super(context, attrs, defStyle);    
    }    

    /**     
      * ���ù����ӿ�     
      * @param onScrollListener     
      */    
    public void setOnScrollListener(OnScrollListener onScrollListener) {        
        this.onScrollListener = onScrollListener;    
    }    

    @Override    
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {        
        super.onScrollChanged(l, t, oldl, oldt);        
        scrollY = getScrollY();        
        if (onScrollListener != null) {            
            onScrollListener.onScroll(scrollY);        
        }

    }    

    public interface OnScrollListener{        
        /**         
          * �ص������� ����MyScrollView������Y�������         
          * @param scrollY            ��         
          */        
        public void onScroll(int scrollY);    
    }
}

