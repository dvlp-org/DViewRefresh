package news.dvlp.dviewrefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class NoScrollListView extends ListView {

	public NoScrollListView(Context context) {
		super(context);
	}

	public NoScrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoScrollListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		/*
		 * super.onMeasure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(0,
		 * 0)); int i = getMeasuredHeight() - (getListPaddingTop() +
		 * getListPaddingBottom() + getVerticalFadingEdgeLength()); i =
		 * getListPaddingTop() + getListPaddingBottom() + i * getCount();
		 * setMeasuredDimension(getMeasuredWidth(), i);
		 */
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
