package news.dvlp.dviewrefresh;

import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolderUtils {

    /**
     * 得到视图为 view 的 viewHodler
     *
     * @param view
     * @return
     */
    public ViewHolder get(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        return viewHolder;
    }


    /**
     * viewhodler  存储 view的子 view 的索引
     *
     * @author zzz40500
     */
    public class ViewHolder {

        private SparseArray<View> viewHolder;
        private View view;

        public ViewHolder(View view) {
            this.view = view;
            viewHolder = new SparseArray<View>();
        }

        public <T extends View> T get(int id) {

            View childView = viewHolder.get(id);
            if (childView == null) {
                childView = view.findViewById(id);
                viewHolder.put(id, childView);
            }
            return (T) childView;

        }


        /**
         * 得到 view 下 id 为 id 的TextView 这里没有做类型的判断所以你要保证 id 为 id 的控件确实为 TextView类型
         *
         * @param id
         * @return
         */
        public TextView getTextView(int id) {

            return get(id);
        }

        /**
         * 直接设置文字
         *
         * @param id
         * @param text
         */
        public void setText(int id, String text) {

            getTextView(id).setText(text);
        }

        public ImageView getImageView(int id) {

            return get(id);
        }


    }

}
