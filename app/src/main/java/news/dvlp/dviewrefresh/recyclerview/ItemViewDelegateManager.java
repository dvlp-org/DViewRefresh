package news.dvlp.dviewrefresh.recyclerview;

import android.support.v4.util.SparseArrayCompat;

public class ItemViewDelegateManager <T>{
    private SparseArrayCompat<ItemViewDelegate<T>> delegates = new SparseArrayCompat();

    public ItemViewDelegateManager() {
    }

    public int getItemViewDelegateCount() {
        return this.delegates.size();
    }

    public ItemViewDelegateManager<T> addDelegate(ItemViewDelegate<T> delegate) {
        if (delegate != null) {
            this.delegates.put(this.delegates.size(), delegate);
        }

        return this;
    }

    public ItemViewDelegateManager<T> addDelegate(int viewType, ItemViewDelegate<T> delegate) {
        if (this.delegates.get(viewType) != null) {
            throw new IllegalArgumentException("An ItemViewDelegate is already registered for the viewType = " + viewType + ". Already registered ItemViewDelegate is " + this.delegates.get(viewType));
        } else {
            this.delegates.put(viewType, delegate);
            return this;
        }
    }

    public ItemViewDelegateManager<T> removeDelegate(ItemViewDelegate<T> delegate) {
        if (delegate == null) {
            throw new NullPointerException("ItemViewDelegate is null");
        } else {
            int indexOfDelegate = this.delegates.indexOfValue(delegate);
            if (indexOfDelegate >= 0) {
                this.delegates.remove(indexOfDelegate);
            }

            return this;
        }
    }

    public ItemViewDelegateManager<T> removeDelegate(int itemType) {
        int indexOfDelegate = this.delegates.indexOfKey(itemType);
        if (indexOfDelegate >= 0) {
            this.delegates.remove(indexOfDelegate);
        }

        return this;
    }

    public int getItemViewType(T item, int position) {
        int delegatesCount = this.delegates.size();

        for(int i = delegatesCount - 1; i >= 0; --i) {
            ItemViewDelegate<T> delegate = (ItemViewDelegate)this.delegates.valueAt(i);
            if (delegate.isForViewType(item, position)) {
                return this.delegates.keyAt(i);
            }
        }

        throw new IllegalArgumentException("No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    public void convert(ViewHolder holder, T item, int position) {
        int delegatesCount = this.delegates.size();

        for(int i = 0; i < delegatesCount; ++i) {
            ItemViewDelegate<T> delegate = (ItemViewDelegate)this.delegates.valueAt(i);
            if (delegate.isForViewType(item, position)) {
                delegate.convert(holder, item, position);
                return;
            }
        }

        throw new IllegalArgumentException("No ItemViewDelegateManager added that matches position=" + position + " in data source");
    }

    public ItemViewDelegate<T> getItemViewDelegate(int viewTyepe) {
        return (ItemViewDelegate)this.delegates.get(viewTyepe);
    }

    public int getItemViewLayoutId(int viewType) {
        return this.getItemViewDelegate(viewType).getItemViewLayoutId();
    }

    public int getItemViewType(ItemViewDelegate<T> itemViewDelegate) {
        return this.delegates.indexOfValue(itemViewDelegate);
    }
}
