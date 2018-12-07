package news.dvlp.dviewrefresh;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseLvAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mList;
    protected SparseArray<Integer> layoutArray ;
    protected int layoutID;

    protected LayoutInflater mInflater;
    protected ViewHolderUtils mViewHolderUtils;

    protected ActionListenter mActionListenter ;

    public BaseLvAdapter(Context mContext, int layoutid) {
        init(mContext, layoutid, null);
    }

    public BaseLvAdapter(Context mContext, int layoutid, List<T> paramList) {
        init(mContext, layoutid, paramList);
    }

    public void setActionListenter(ActionListenter mActionListenter) {
        this.mActionListenter = mActionListenter;
    }

    protected void init(Context paramContext, int layoutid, List<T> paramList) {
        this.mContext = paramContext;
        this.layoutID = layoutid;
        this.mInflater = LayoutInflater.from(paramContext);

        layoutArray = new SparseArray<>();
        addViewType(0 , layoutID);

        mViewHolderUtils = new ViewHolderUtils();

        if (paramList == null) {
            this.mList = new ArrayList<T>();
        } else {
            this.mList = paramList;
        }
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(layoutArray.get(getItemViewType(position)), parent, false);
        }

        _getView(position , convertView , mViewHolderUtils.get(convertView));
        return convertView;
    }

    public abstract void _getView(int position, View convertView, ViewHolderUtils.ViewHolder parent);


    @Override
    public int getViewTypeCount() {
        return layoutArray.size();
    }

    public void addViewType(int key , int layoutID){
        layoutArray.put(key , layoutID);
    }

    public T getItem(int paramInt) {
        return this.mList.get(paramInt);
    }

    public void add(T paramT) {
        mList.add(paramT);
        notifyDataSetChanged();
    }

    public void add(int paramInt, T paramT) {
        mList.add(paramInt, paramT);
        notifyDataSetChanged();
    }

    public void set(int paramInt, T paramT) {
        mList.set(paramInt, paramT);
        notifyDataSetChanged();
    }

    public void addAll(Collection<T> paramT) {
        mList.addAll(paramT);
        notifyDataSetChanged();
    }

    public void addAll(int paramInt, Collection<T> paramT) {
        mList.addAll(paramInt, paramT);
        notifyDataSetChanged();
    }

    public void remove(int paramInt) {
        mList.remove(paramInt);
        notifyDataSetChanged();
    }

    public void removeAll() {
        this.mList.clear();
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.mList.size();
    }

    public long getItemId(int paramInt) {
        return 0L;
    }


    public interface ActionListenter {
        void onAction(int position, String action);
    }
}
