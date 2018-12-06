package news.dvlp.dlibrary.PullToRefresh;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.lang.ref.WeakReference;

/**
 * Created by liubaigang on 2018/12/6.
 */

public class InertiaListenerScrollView  extends NestedScrollView {
    private static final int SCROLL_TIME = 20;
    private static final int SCROLL_WHAT = 111;
    private int mLastY = 0;
    private InertiaListenerScrollView.ScrollYListener mScrollYListener;
    private InertiaListenerScrollView.ScrollHandle mHandler = new InertiaListenerScrollView.ScrollHandle(this);

    public InertiaListenerScrollView(Context context) {
        super(context);
    }

    public InertiaListenerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollYViewListener(InertiaListenerScrollView.ScrollYListener scrollYListener) {
        this.mScrollYListener = scrollYListener;
    }

    public InertiaListenerScrollView.ScrollYListener getScrollYListener() {
        return this.mScrollYListener;
    }

    protected void onScrollChanged(int l, int t, int oldL, int oldT) {
        super.onScrollChanged(l, t, oldL, oldT);
        if(this.mScrollYListener != null) {
            this.mScrollYListener.onScrollYChanged(t);
        }

    }

    public boolean onTouchEvent(MotionEvent ev) {
        switch(ev.getAction()) {
            case 1:
                if(this.mScrollYListener != null) {
                    this.mHandler.sendEmptyMessage(111);
                }
            default:
                return super.onTouchEvent(ev);
        }
    }

    private static class ScrollHandle extends Handler {
        private WeakReference<InertiaListenerScrollView> mListeningScrollViewWeakReference;

        ScrollHandle(InertiaListenerScrollView inertiaListenerScrollView) {
            this.mListeningScrollViewWeakReference = new WeakReference(inertiaListenerScrollView);
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what) {
                case 111:
                    InertiaListenerScrollView inertiaListenerScrollView = (InertiaListenerScrollView)this.mListeningScrollViewWeakReference.get();
                    int scrollY = inertiaListenerScrollView.getScrollY();
                    if(inertiaListenerScrollView.mLastY != scrollY) {
                        inertiaListenerScrollView.mLastY = scrollY;
                        inertiaListenerScrollView.mScrollYListener.onScrollYChanged(scrollY);
                        this.sendEmptyMessageDelayed(111, 20L);
                    }
                default:
            }
        }
    }

    public interface ScrollListener {
        void onScrollChanged(int var1, int var2, int var3, int var4, int var5);
    }

    public interface ScrollYListener {
        void onScrollYChanged(int var1);
    }
}
