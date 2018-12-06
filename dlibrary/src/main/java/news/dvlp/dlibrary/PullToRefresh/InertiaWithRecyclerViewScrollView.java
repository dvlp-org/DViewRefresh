package news.dvlp.dlibrary.PullToRefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by liubaigang on 2018/12/6.
 */

public class InertiaWithRecyclerViewScrollView extends InertiaListenerScrollView{
    private int downX;
    private int downY;
    private int mTouchSlop;

    public InertiaWithRecyclerViewScrollView(Context context) {
        super(context);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public InertiaWithRecyclerViewScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch(action) {
            case 0:
                this.downX = (int)e.getRawX();
                this.downY = (int)e.getRawY();
                break;
            case 2:
                int moveY = (int)e.getRawY();
                if(Math.abs(moveY - this.downY) > this.mTouchSlop) {
                    return true;
                }
        }

        return super.onInterceptTouchEvent(e);
    }
}
