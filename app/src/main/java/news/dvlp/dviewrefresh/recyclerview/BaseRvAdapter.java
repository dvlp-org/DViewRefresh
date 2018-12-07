package news.dvlp.dviewrefresh.recyclerview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;

import java.util.List;

public abstract class BaseRvAdapter <T> extends MultiItemTypeAdapter<T>  {
    public BaseRvAdapter(Context context, @LayoutRes int layoutId) {
        this(context, layoutId, (List)null);
    }

    public BaseRvAdapter(Context context, @LayoutRes final int layoutId, List<T> dataList) {
        super(context, dataList);
        this.addItemViewDelegate(new ItemViewDelegate<T>() {
            public int getItemViewLayoutId() {
                return layoutId;
            }

            public boolean isForViewType(T item, int position) {
                return true;
            }

            public void convert(ViewHolder holder, T t, int position) {
                BaseRvAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder viewHolder, T var2, int position);

    protected final int getColor(int resId) {
        return ContextCompat.getColor(mContext, resId);
    }
}
