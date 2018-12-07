package news.dvlp.dviewrefresh;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import news.dvlp.dviewrefresh.recyclerview.BaseRvAdapter;
import news.dvlp.dviewrefresh.recyclerview.ViewHolder;


public class GetStoreLvApater extends BaseLvAdapter<String> {



    private TextView tv_title;
    public GetStoreLvApater(Context context, int itemLayoutid, List<String> data) {
        super(context,itemLayoutid,data);
        this.mContext = context;
    }

    @Override
    public void _getView(int position, View convertView, ViewHolderUtils.ViewHolder parent) {
        tv_title=parent.get(R.id.tv_title);

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
