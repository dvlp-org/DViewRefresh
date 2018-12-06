package news.dvlp.dviewrefresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;


import java.text.SimpleDateFormat;
import java.util.Date;

import news.dvlp.dlibrary.PullToRefresh.InertiaListenerScrollView;
import news.dvlp.dlibrary.PullToRefresh.InertiaWithRecyclerViewScrollView;
import news.dvlp.dlibrary.PullToRefresh.PullToRefreshBase;
import news.dvlp.dlibrary.PullToRefresh.TKPullToRefreshScrollView;


public class MainActivity extends AppCompatActivity {

    private View mRootView;
    /**
     * 嵌套于ScrollView的布局
     */
    private View mChildScrollView;
    public TKPullToRefreshScrollView mRefreshScrollview;

    /**
     * 本类滑动页根布局 ScrollView
     */
    private InertiaWithRecyclerViewScrollView mSvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = LayoutInflater.from(this).inflate(R.layout.fragement_fund_shop, null);
        mChildScrollView = LayoutInflater.from(this).inflate(R.layout.fragement_fund_shop_home, null);
        findViews(mRootView);
        setContentView(mRootView);
        setListeners();
    }

    protected void findViews(View view) {

        mRefreshScrollview = (TKPullToRefreshScrollView) view.findViewById(R.id.scroll_fund_shop_fragment);
        mSvContent = mRefreshScrollview.getRefreshableView();
        mSvContent.setId(R.id.nsv_common_page);
        mSvContent.addView(mChildScrollView);
        mRefreshScrollview.setPullLoadEnabled(false);

    }

    protected void setListeners() {

        mSvContent.setScrollYViewListener(new InertiaListenerScrollView.ScrollYListener() {
            @Override
            public void onScrollYChanged(int i) {

                Log.e("liu","-------scrollY-------"+i);
            }
        });

        mRefreshScrollview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<InertiaWithRecyclerViewScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<InertiaWithRecyclerViewScrollView> refreshView) {
                // requestAllData(false); // 既然是下拉刷新，那就不取缓存直接加载。
                Log.i("liu", "下拉刷新");
                refreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<InertiaWithRecyclerViewScrollView> refreshView) {
                Log.i("liu", "上拉加载");

            }
        });
        mRefreshScrollview.setOnRefreshAnimationListener(new PullToRefreshBase.OnRefreshAnimationListener<InertiaWithRecyclerViewScrollView>() {
            @Override
            public void onPullDownToRefreshStart(PullToRefreshBase<InertiaWithRecyclerViewScrollView> refreshView) {
                Log.i("liu", "下拉动画开始");

            }

            @Override
            public void onPullDownToRefreshOver(PullToRefreshBase<InertiaWithRecyclerViewScrollView> refreshView) {
                Log.i("liu", "下拉动画结束");
            }
        });

    }


    /**
     * 刷新完成,收回下拉联
     */
    public void refreshComplete() {
        mRefreshScrollview.onPullDownRefreshComplete();
        mRefreshScrollview.onPullUpRefreshComplete();
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("HH:mm:ss");
        mRefreshScrollview.setLastUpdatedLabel(format.format(new Date()));
    }
}
