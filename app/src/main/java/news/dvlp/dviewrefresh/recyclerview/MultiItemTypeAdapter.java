package news.dvlp.dviewrefresh.recyclerview;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MultiItemTypeAdapter <T> extends RecyclerView.Adapter<ViewHolder> {

    public Context mContext;
    private List<T> mDataList;
    private ItemViewDelegateManager<T> mItemViewDelegateManager;
    private MultiItemTypeAdapter.OnItemClickListener mItemClickListener;
    private MultiItemTypeAdapter.OnItemLongClickListener mItemLongClickListener;

    public MultiItemTypeAdapter(Context context) {
        this(context, (List)null);
    }

    public MultiItemTypeAdapter(Context context, List<T> dataList) {
        this.mContext = context;
        this.mDataList = (List)(dataList != null ? dataList : new ArrayList());
        this.mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    public int getItemViewType(int position) {
        return !this.useItemViewDelegateManager() ? super.getItemViewType(position) : this.mItemViewDelegateManager.getItemViewType(this.mDataList.get(position), position);
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewDelegate<T> itemViewDelegate = this.mItemViewDelegateManager.getItemViewDelegate(viewType);
        int itemViewLayoutId = itemViewDelegate.getItemViewLayoutId();
        ViewHolder viewHolder = ViewHolder.createViewHolder(parent, itemViewLayoutId);
        this.onViewHolderCreated(viewHolder);
        this.setListener(parent, viewHolder, viewType);
        return viewHolder;
    }

    private void setListener(ViewGroup parent, final ViewHolder viewHolder, int viewType) {
        if (this.isEnable(viewType)) {
            viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (MultiItemTypeAdapter.this.mItemClickListener != null) {
                        MultiItemTypeAdapter.this.mItemClickListener.onItemClick(view, viewHolder, viewHolder.getLayoutPosition());
                    }

                }
            });
            viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    return MultiItemTypeAdapter.this.mItemLongClickListener != null && MultiItemTypeAdapter.this.mItemLongClickListener.onItemLongClick(view, viewHolder, viewHolder.getLayoutPosition());
                }
            });
        }
    }

    protected void onViewHolderCreated(ViewHolder viewHolder) {
    }

    protected boolean isEnable(int viewType) {
        return true;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        this.convert(holder, this.mDataList.get(position));
    }

    private void convert(ViewHolder holder, T t) {
        this.mItemViewDelegateManager.convert(holder, t, holder.getLayoutPosition());
    }

    public int getItemCount() {
        return this.mDataList != null ? this.mDataList.size() : 0;
    }

    protected boolean useItemViewDelegateManager() {
        return this.mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }

    public List<T> getDataList() {
        return this.mDataList;
    }

    public void setDataList(List<T> datas) {
        if (this.mDataList != null) {
            this.mDataList.clear();
            this.mDataList.addAll(datas);
        }

    }

    public MultiItemTypeAdapter<T> addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        this.mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public MultiItemTypeAdapter<T> addItemViewDelegate(int viewType, ItemViewDelegate<T> itemViewDelegate) {
        this.mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
        return this;
    }

    public void setOnItemClickListener(MultiItemTypeAdapter.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(MultiItemTypeAdapter.OnItemLongClickListener listener) {
        this.mItemLongClickListener = listener;
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(View var1, RecyclerView.ViewHolder var2, int var3);
    }

    public interface OnItemClickListener {
        void onItemClick(View var1, RecyclerView.ViewHolder var2, int var3);
    }
}
