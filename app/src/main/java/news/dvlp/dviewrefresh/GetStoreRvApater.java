package news.dvlp.dviewrefresh;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import news.dvlp.dviewrefresh.recyclerview.BaseRvAdapter;
import news.dvlp.dviewrefresh.recyclerview.ViewHolder;


public class GetStoreRvApater extends BaseRvAdapter<String> {



    private TextView tv_title;
    public GetStoreRvApater(Context context, int itemLayoutid) {
        super(context, itemLayoutid);
        this.mContext = context;
    }


    @Override
    protected void convert(ViewHolder viewHolder, final String taskBeans, final int position) {

        tv_title=viewHolder.getView(R.id.tv_title);

        if(position%6==0){
            tv_title.setTextColor(Color.parseColor("#AF9CCE"));
        }else if(position%6==1){
            tv_title.setTextColor(Color.parseColor("#928C72"));
        }else if(position%6==2){
            tv_title.setTextColor(Color.parseColor("#82C182"));
        }else if(position%6==3){
            tv_title.setTextColor(Color.parseColor("#86C0E2"));
        }else if(position%6==4){
            tv_title.setTextColor(Color.parseColor("#C66472"));
        }else if(position%6==5){
            tv_title.setTextColor(Color.parseColor("#BCBD40"));
        }

    }
}
