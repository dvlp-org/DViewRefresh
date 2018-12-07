package news.dvlp.dviewrefresh.recyclerview;

public interface ItemViewDelegate<T> {
    int getItemViewLayoutId();

    boolean isForViewType(T var1, int var2);

    void convert(ViewHolder var1, T var2, int var3);
}
