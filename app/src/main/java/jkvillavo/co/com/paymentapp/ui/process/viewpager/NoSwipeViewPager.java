package jkvillavo.co.com.paymentapp.ui.process.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * ViewPager customizado que inhabilita el paso de pagina mediante gesto de swipe
 */
public class NoSwipeViewPager extends ViewPager {

    public NoSwipeViewPager ( Context context ) {

        super( context );
    }

    public NoSwipeViewPager ( Context context, AttributeSet attrs ) {

        super( context, attrs );
    }

    @Override
    public boolean onInterceptTouchEvent ( MotionEvent event ) {
        // Never allow swiping to switch between pages
        return false;
    }

    @Override
    public boolean onTouchEvent ( MotionEvent event ) {
        // Never allow swiping to switch between pages
        return false;
    }

}
